package fr.isima.injectionproject.plugins.log;

import fr.isima.injectionproject.container.Annotations.Inject;
import fr.isima.injectionproject.container.IInterceptor;

import java.lang.reflect.Method;

/**
 * Created by Adrien Pierreval on 24/01/2017.
 */
public class LogInterceptor implements IInterceptor
{
    @Inject
    ILogger log;

    public void before(Object obj, Method method, Object... params) {
        log.add(obj.getClass().getSimpleName() + " - Before : " + method.getName());
    }

    public void after(Object obj, Method method, Object... params) {
        log.add(obj.getClass().getSimpleName() + " - After : " + method.getName());
    }
}
