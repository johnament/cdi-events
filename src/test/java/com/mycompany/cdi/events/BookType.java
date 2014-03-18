/*******************************************************************************
 * Copyright (c) 2014 Sparta Systems, Inc
 ******************************************************************************/

package com.mycompany.cdi.events;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by John.Ament on 3/18/14.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE,ElementType.PARAMETER,ElementType.METHOD})
public @interface BookType {
    String value() default "";
}
