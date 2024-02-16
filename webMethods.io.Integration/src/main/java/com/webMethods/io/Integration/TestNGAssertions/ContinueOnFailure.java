/**
 * Implementation for custom annotation to support test execution continuation after soft assertion fail and dependsOnMethods TestNG attribute.
 * 
 * @author altsh
 * @version 1.0
 * @since 21st August 2023 
 **/

package com.webMethods.io.Integration.TestNGAssertions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ContinueOnFailure
{

}