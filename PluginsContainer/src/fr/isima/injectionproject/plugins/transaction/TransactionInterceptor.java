package fr.isima.injectionproject.plugins.transaction;

import fr.isima.injectionproject.container.IInterceptor;

import java.lang.reflect.Method;

/**
 * Created by Adrien Pierreval on 14/02/2017.
 */
public class TransactionInterceptor implements IInterceptor
{
    @Override
    public void before(Object obj, Method method, Object... params)
    {
        // TODO : before method transactional
    }

    @Override
    public void after(Object obj, Method method, Object... params)
    {
        // TODO : after method transactional
    }
}
