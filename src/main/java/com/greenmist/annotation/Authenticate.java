package com.greenmist.annotation;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by eckob on 10/29/2016.
 */

@Target(ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Authenticate {

}