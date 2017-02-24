package fr.isima.injectionproject.plugins.log;

import fr.isima.injectionproject.container.IInterceptor;
import fr.isima.injectionproject.container.Annotations.Inject;

import java.lang.reflect.Method;

/**
 * Created by Adrien Pierreval on 24/01/2017.
 */
public class LogInterceptor implements IInterceptor
{
    @Inject
    ILogger log;

    @Override
    public void before(Object obj, Method method, Object... params) {
        log.add(obj.getClass().getSimpleName() + " - Before : " + method.getName());
    }

    @Override
    public void after(Object obj, Method method, Object result, Throwable e, Object... params) {
        log.add(obj.getClass().getSimpleName() + " - After : " + method.getName());
    }
}
