package com.demo.cfxsoap.config;

import java.util.LinkedList;
import java.util.List;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.demo.cfxsoap.annotation.WebServiceEndpoint;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ApplicationConfig 
implements BeanFactoryPostProcessor 
{


	@Bean
	public ServletRegistrationBean<CXFServlet> cxfServlet() {
		return new ServletRegistrationBean<>(new CXFServlet(), "/soap-api/*");
	} 

	@Bean(name = Bus.DEFAULT_BUS_ID)  
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public LoggingFeature loggingFeature() {
		
		LoggingFeature loggingFeature = new LoggingFeature();
		loggingFeature.setPrettyLogging(true);
		
		return loggingFeature;
	}

//	@Bean
//	public Endpoint endpoint() {
//		EndpointImpl endpoint = new EndpointImpl(springBus(), new SoapService());
//		endpoint.publish("/SoapService1");
//		return endpoint;
//	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		try {
			generateServiceBeans(beanFactory);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void generateServiceBeans(final ConfigurableListableBeanFactory beanFactory) throws ClassNotFoundException {

		List<Class<?>> clazzList = findAllWebServiceEndpointClasses("com.demo.cfxsoap");

		log.info("Detected " + clazzList.size() + " SOAP WebServices interfaces");

		Bus bus = beanFactory.getBean(Bus.class);

		for (Class<?> clazz : clazzList) {
			WebServiceEndpoint wsEndpoint = clazz.getAnnotation(WebServiceEndpoint.class);
			beanFactory.registerSingleton("endpoint" + clazz.getSimpleName(),
					generateEndpointBean(bus, clazz, wsEndpoint));
		}

	}
	
	private List<Class<?>> findAllWebServiceEndpointClasses(String packageName) throws ClassNotFoundException {
		final List<Class<?>> result = new LinkedList<>();
		final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(
				false);
		provider.addIncludeFilter(new AnnotationTypeFilter(WebServiceEndpoint.class));
		for (BeanDefinition beanDefinition : provider.findCandidateComponents(packageName)) {
			result.add(Class.forName(beanDefinition.getBeanClassName()));
		}

		return result;
	}

	private <T> T generateEndpointBean(final Bus bus, Class<T> clazz, final WebServiceEndpoint endpointConfig) {
		JaxWsServerFactoryBean endpointBean = new JaxWsServerFactoryBean();
		endpointBean.setAddress(endpointConfig.value());
		endpointBean.setBus(bus);
		endpointBean.setServiceClass(clazz);
		return (T) endpointBean.create();
	}

}