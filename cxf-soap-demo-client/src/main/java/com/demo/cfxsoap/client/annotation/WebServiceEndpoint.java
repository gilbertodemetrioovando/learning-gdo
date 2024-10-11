package com.demo.cfxsoap.client.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface WebServiceEndpoint {
	String value();
}