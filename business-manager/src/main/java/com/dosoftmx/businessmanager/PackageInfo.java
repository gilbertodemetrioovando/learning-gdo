package com.dosoftmx.businessmanager;

import static java.lang.annotation.ElementType.PACKAGE;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface PackageInfo.
 *
 */
@Target(PACKAGE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PackageInfo {

}