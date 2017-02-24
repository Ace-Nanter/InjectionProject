package fr.isima.injectionproject.container;

import java.lang.reflect.Method;

/**
 * Created by Adrien Pierreval on 14/02/2017.
 */
public interface IInterceptor
{
    void before(Object obj, Method method, Object... params);

    void after(Object obj, Method method, Object result, Exception e, Object... params);
}

