package fr.isima.injectionproject.plugins.log;

import fr.isima.injectionproject.container.Annotations.Behaviour;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Adrien Pierreval on 24/01/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Behaviour(interceptor = LogInterceptor.class)
public @interface Log
{
}
