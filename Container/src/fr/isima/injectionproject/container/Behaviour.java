package fr.isima.injectionproject.container;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Adrien Pierreval on 14/02/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Behaviour
{
    Class<? extends IInterceptor> interceptor();
}