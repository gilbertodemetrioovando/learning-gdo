package com.demo.cfxsoap.client.config;

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
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.common.ConfigurationConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.demo.cfxsoap.client.annotation.WebServiceEndpoint;
import com.demo.cfxsoap.client.service.SoapServiceClient;
import com.demo.cfxsoap.soapservice.SoapService;
import com.demo.cfxsoap.client.support.ClientKeystorePasswordCallback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ApplicationClientConfig 
//implements BeanFactoryPostProcessor 
{
	
	@Value("${service.url}")
	private String serviceUrl;
	
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
	
	/**
	 * Servicio Cliente
	 */
	@Bean(name = "soapClientService")
	public SoapService soapClientService() {
		JaxWsProxyFactoryBean jaxWsProxyFactory = new JaxWsProxyFactoryBean();
		jaxWsProxyFactory.setServiceClass(SoapService.class);
		jaxWsProxyFactory.setAddress(serviceUrl);
		log.info("Consumiendo servicio de " + serviceUrl);
		jaxWsProxyFactory.getInInterceptors().add(wss4jIn());
		jaxWsProxyFactory.getOutInterceptors().add(wss4jOut());
		jaxWsProxyFactory.getOutInterceptors().add(loggingOutFeature());
		jaxWsProxyFactory.getInInterceptors().add(loggingInFeature());
		return (SoapService) jaxWsProxyFactory.create();
	}

	
	/*2.2 response - WSS4JInInterceptor para verificar response de redeban*/
	/*
	 * Algoritmo firma: RS512 (RSASSA-PKCS-v1_5 usando SHA-512).
	 * 
	 * Clave de Firma: Utilizar la llave privada de Redeban. - dato de redeban
	 * 
	 * Verificación de firma en response que recibimos de Redeban: Se realiza mediante el certificado público asociado (verificara con el JKS/Cer de Redeban)
	 * 
	 * */
	public WSS4JInInterceptor wss4jIn() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ConfigurationConstants.ACTION,
				ConfigurationConstants.SIGNATURE + " " + ConfigurationConstants.TIMESTAMP);
		properties.put("signingProperties", wss4jInProperties());
		properties.put(ConfigurationConstants.SIG_PROP_REF_ID, "signingProperties");
		properties.put(ConfigurationConstants.SIG_KEY_ID, "DirectReference"); //Referencia de Token: Direct Reference.
		properties.put(ConfigurationConstants.SIGNATURE_PARTS,
				"{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body");
		properties.put(ConfigurationConstants.SIG_ALGO, "http://www.w3.org/2001/04/xmldsig-more#rsa-sha512"); //RS512 (RSASSA-PKCS-v1_5 usando SHA-512).
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


	/* 2.1 request WSS4JOutInterceptor para firmar request a Redeban*/
	/*
	 * Algoritmo firma: RSA con SHA-512 (RS512 - RSASSA-PKCS-v1_5).
	 * 
	 * Clave de Firma: Utilizar certificado privado (.p12) - extraido del jks de Openpay
	 * 
	 * Verificación de firma en request que recibe Redeban: Se realiza mediante el certificado público asociado (verificara con el JKS/Cer de Openpay)
	 * 
	 * */
	public WSS4JOutInterceptor wss4jOut() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ConfigurationConstants.ACTION,
				ConfigurationConstants.SIGNATURE + " " + ConfigurationConstants.TIMESTAMP);
		properties.put("signingProperties", wss4jOutProperties());
		properties.put(ConfigurationConstants.SIG_PROP_REF_ID, "signingProperties");
		properties.put(ConfigurationConstants.SIG_KEY_ID, "DirectReference");//Referencia de Token: Direct Reference.
		properties.put(ConfigurationConstants.USER, keystoreAlias);
		properties.put(ConfigurationConstants.SIGNATURE_PARTS,
				"{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body");
		properties.put(ConfigurationConstants.PW_CALLBACK_REF, clientKeystorePasswordCallback());
		properties.put(ConfigurationConstants.SIG_ALGO, "http://www.w3.org/2001/04/xmldsig-more#rsa-sha512"); //RSA con SHA-512 (RS512 - RSASSA-PKCS-v1_5).
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

}