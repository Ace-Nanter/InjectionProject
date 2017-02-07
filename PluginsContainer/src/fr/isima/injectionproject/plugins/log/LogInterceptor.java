package fr.isima.injectionproject.plugins.log;

import fr.isima.injectionproject.container.Inject;

import java.lang.reflect.Method;

/**
 * Created by Adrien Pierreval on 24/01/2017.
 */
public class LogInterceptor implements IInterceptor
{
    @Inject
    MyLogger log;

    public void before(Object obj, Method method, Object... params) { }

    public void after(Object obj, Method method, Object... params) { }
}
