package com.demo.cfxsoap.config;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.security.auth.callback.CallbackHandler;
import javax.xml.ws.Endpoint;


import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.common.ConfigurationConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.demo.cfxsoap.annotation.WebServiceEndpoint;
import com.demo.cfxsoap.soapservice.SoapService;
import com.demo.cfxsoap.support.ClientKeystorePasswordCallback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ApplicationConfig 
//implements BeanFactoryPostProcessor 
{

	@Value("${service.contextPath}")
	private String contextPath;
	@Value("${service.endpointUrl}")
	private String endpointUrl;
	@Value("${service.wsdlLocation}")
	private String wsdlLocation;
	
	/* Datos keystore */
	@Value("${keystore.alias}")
	private String keystoreAlias;
	@Value("${keystore.password}")
	private String keystorePassword;
	@Value("${keystore.file}")
	private String keystoreFile;
	@Value("${keystore.type}")
	private String keystoreType;
	
	/* Datos truststore */
	@Value("${truststore.alias}")
	private String truststoreAlias;
	@Value("${truststore.password}")
	private String truststorePassword;
	@Value("${truststore.file}")
	private String truststoreFile;
	@Value("${truststore.type}")
	private String truststoreType;

	@Bean
	public ServletRegistrationBean<CXFServlet> cxfServlet() {
		return new ServletRegistrationBean<>(new CXFServlet(), contextPath);
	} 

	@Bean(name = Bus.DEFAULT_BUS_ID)  
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public LoggingOutInterceptor loggingOutFeature() {

		LoggingOutInterceptor loggingFeature = new LoggingOutInterceptor();
		loggingFeature.setPrettyLogging(true);

		return loggingFeature;
	}

	@Bean
	public LoggingInInterceptor loggingInFeature() {

		LoggingInInterceptor loggingFeature = new LoggingInInterceptor();
		loggingFeature.setPrettyLogging(true);

		return loggingFeature;
	}

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), new SoapService());
		endpoint.publish("/SoapService1");
		endpoint.getOutInterceptors().add(this.wss4jOut());
		endpoint.getInInterceptors().add(this.wss4jIn());
		endpoint.getOutInterceptors().add(loggingOutFeature());
		endpoint.getInInterceptors().add(loggingInFeature());
		return endpoint;
	}

// 	Configuracion para registrarmultiples servicios SOAP, de momento inactiva
//	
//	@Override
//	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//		try {
//			generateServiceBeans(beanFactory);
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	public void generateServiceBeans(final ConfigurableListableBeanFactory beanFactory) throws ClassNotFoundException {
//
//		List<Class<?>> clazzList = findAllWebServiceEndpointClasses("com.demo.cfxsoap");
//
//		log.info("Detected " + clazzList.size() + " SOAP WebServices interfaces");
//
//		Bus bus = beanFactory.getBean(Bus.class);
//
//		for (Class<?> clazz : clazzList) {
//			WebServiceEndpoint wsEndpoint = clazz.getAnnotation(WebServiceEndpoint.class);
//			beanFactory.registerSingleton("endpoint" + clazz.getSimpleName(),
//					generateEndpointBean(bus, clazz, wsEndpoint));
//		}
//
//	}
//	
//	private List<Class<?>> findAllWebServiceEndpointClasses(String packageName) throws ClassNotFoundException {
//		final List<Class<?>> result = new LinkedList<>();
//		final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(
//				false);
//		provider.addIncludeFilter(new AnnotationTypeFilter(WebServiceEndpoint.class));
//		for (BeanDefinition beanDefinition : provider.findCandidateComponents(packageName)) {
//			result.add(Class.forName(beanDefinition.getBeanClassName()));
//		}
//
//		return result;
//	}
//
//	private <T> T generateEndpointBean(final Bus bus, Class<T> clazz, final WebServiceEndpoint endpointConfig) {
//		JaxWsServerFactoryBean endpointBean = new JaxWsServerFactoryBean();
//		endpointBean.setAddress(endpointConfig.value());
//		endpointBean.setBus(bus);
//		endpointBean.setServiceClass(clazz);
//		endpointBean.getOutInterceptors().add(this.wss4jOut());
//		
//		return (T) endpointBean.create();
//	}

	/* WSS4JOutInterceptor para firmar respuesta del server/redeban (simular firma redeban)*/
	/*
	 * Algoritmo firma
	 * 
	 * RS512 (RSASSA-PKCS-v1_5 usando SHA-512).
	 * Clave de Firma: Utilizar la llave privada de Redeban.
	 * 
	 * Verificación: Se realiza mediante el certificado público asociado (se verificara por Openpay con el JKS de Redeban)
	 * 
	 * */
	/**
	 * 
	 * @return
	 */
	public WSS4JOutInterceptor wss4jOut() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ConfigurationConstants.ACTION,
				ConfigurationConstants.SIGNATURE + " " + ConfigurationConstants.TIMESTAMP);
		properties.put("signingProperties", wss4jOutProperties());
		properties.put(ConfigurationConstants.SIG_PROP_REF_ID, "signingProperties");
		properties.put(ConfigurationConstants.SIG_KEY_ID, "DirectReference");
		properties.put(ConfigurationConstants.USER, keystoreAlias);
		properties.put(ConfigurationConstants.SIGNATURE_PARTS,
				"{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body");
		properties.put(ConfigurationConstants.PW_CALLBACK_REF, clientKeystorePasswordCallback());
		properties.put(ConfigurationConstants.SIG_ALGO, "http://www.w3.org/2001/04/xmldsig-more#rsa-sha512"); //RS512 (RSASSA-PKCS-v1_5 usando SHA-512).
		properties.put(ConfigurationConstants.SIG_DIGEST_ALGO, "http://www.w3.org/2001/04/xmlenc#sha512"); //Algoritmo de Digestión: SHA-512.
		properties.put(ConfigurationConstants.ENABLE_SIGNATURE_CONFIRMATION, "false");
		WSS4JOutInterceptor interceptor = new WSS4JOutInterceptor(properties);
		return interceptor;
	}

	public Properties wss4jOutProperties() {
		Properties properties = new Properties();
		properties.put("org.apache.wss4j.crypto.merlin.provider", "org.apache.wss4j.common.crypto.Merlin");
		properties.put("org.apache.wss4j.crypto.merlin.keystore.type", keystoreType);
		properties.put("org.apache.wss4j.crypto.merlin.keystore.password", keystorePassword);
		properties.put("org.apache.wss4j.crypto.merlin.keystore.alias", keystoreAlias);
		properties.put("org.apache.wss4j.crypto.merlin.keystore.file", keystoreFile);
		return properties;
	}

	public CallbackHandler clientKeystorePasswordCallback() {
		Map<String, String> passwords = new HashMap<>();
		passwords.put(keystoreAlias, keystorePassword);
		return new ClientKeystorePasswordCallback(passwords);
	}


	/*2.1 request - WSS4JInInterceptor para verificar request de Openpay*/
	/*
	 * Algoritmo firma: RSA con SHA-512 (RS512 - RSASSA-PKCS-v1_5).
	 * 
	 * Clave de Firma: Utilizar certificado privado (.p12). - (se usara p12 de openpay para firmar)
	 * 
	 * Verificación de request que recibe Redeban: Se realiza mediante el certificado público asociado (verificara con el JKS/Cer de Openpay)
	 * 
	 * */
	public WSS4JInInterceptor wss4jIn() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ConfigurationConstants.ACTION,
				ConfigurationConstants.SIGNATURE + " " + ConfigurationConstants.TIMESTAMP);
		properties.put("signingProperties", wss4jInProperties());
		properties.put(ConfigurationConstants.SIG_PROP_REF_ID, "signingProperties");
		properties.put(ConfigurationConstants.SIG_KEY_ID, "DirectReference");
		properties.put(ConfigurationConstants.SIGNATURE_PARTS,
				"{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body");
		properties.put(ConfigurationConstants.SIG_ALGO, "http://www.w3.org/2001/04/xmldsig-more#rsa-sha512"); // RSA con SHA-512 (RS512 - RSASSA-PKCS-v1_5).
		properties.put(ConfigurationConstants.SIG_DIGEST_ALGO, "http://www.w3.org/2001/04/xmlenc#sha512"); //Algoritmo de Digestión: SHA-512.
		properties.put(ConfigurationConstants.ENABLE_SIGNATURE_CONFIRMATION, "false");
		WSS4JInInterceptor interceptor = new WSS4JInInterceptor(properties);
		return interceptor;
	}

	public Properties wss4jInProperties() {
		Properties properties = new Properties();
		properties.put("org.apache.wss4j.crypto.merlin.provider", "org.apache.wss4j.common.crypto.Merlin");
		properties.put("org.apache.wss4j.crypto.merlin.keystore.type", truststoreType);
		properties.put("org.apache.wss4j.crypto.merlin.keystore.password", truststorePassword);
		properties.put("org.apache.wss4j.crypto.merlin.keystore.alias", truststoreAlias);
		properties.put("org.apache.wss4j.crypto.merlin.keystore.file", truststoreFile);
		return properties;
	}
//	
//
//	private boolean validateSignature(Node signatureNode, Node bodyTag, PublicKey publicKey) {
//	    boolean signatureIsValid = false;
//	    try {
//	        // Create a DOM XMLSignatureFactory that will be used to unmarshal the
//	        // document containing the XMLSignature
//	        String providerName = System.getProperty
//	                ("jsr105Provider", "org.jcp.xml.dsig.internal.dom.XMLDSigRI");
//	        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM",
//	                (Provider) Class.forName(providerName).newInstance());
//
//	        // Create a DOMValidateContext and specify a KeyValue KeySelector
//	        // and document context
//	        DOMValidateContext valContext = new DOMValidateContext(new X509KeySelector(publicKey), signatureNode);
//	        valContext.setIdAttributeNS((Element) bodyTag, "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "Id");
//
//	        // Unmarshal the XMLSignature.
//	        XMLSignature signature = fac.unmarshalXMLSignature(valContext);
//	        // Validate the XMLSignature.
//	        signatureIsValid = signature.validate(valContext);
//
//	    } catch (Exception ex) {
//	        log.error("An Error Raised while Signature Validation");
//	        log.error("Cause: " + ex.getCause());
//	        log.error("Message: " + ex.getMessage());
//	    }
//
//	    return signatureIsValid;
//	}
	
}