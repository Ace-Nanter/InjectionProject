package fr.isima.injectionproject.plugins.transaction;

import fr.isima.injectionproject.container.Annotations.Behaviour;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Adrien Pierreval on 24/01/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Behaviour(interceptor = TransactionInterceptor.class)
public @interface Transactional
{
    TransactionalStrategy value();
}
