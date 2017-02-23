package fr.isima.injectionproject.container;

import fr.isima.injectionproject.container.Exceptions.ImpossibleAllocationException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashSet;

/**
 * Created by Adrien Pierreval on 07/02/2017.
 */
public class Handler implements InvocationHandler
{
    /**
     * Implementation the handler has to instantiate
     */
    private Class<?> implementation;

    /**
     * Instance of the object
     */
    private Object instance = null;
    // TODO : membre static singleton qui va instancier l'object. Quand une méthode est rappelée, si l'objet est déjà là, on ne va pas réinstancier. de cette manière on pourra check les implémentations si c'est les bonnes.

    /**
     * Default constructor. Private for singleton requirement.
     */
    public Handler(Class<?> implementation) {
        this.implementation = implementation;
    }

    public Object getInstance()
    {
        return instance;
    }

    /**
     * Called each time for each method of a proxy.
     * @param proxy Proxy used.
     * @param method Method called.
     * @param args Arguments used for the method.
     * @return Result of the method.
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        HashSet<IInterceptor> interceptors;

        // TODO : instanciate object, check annotation log, do before, do method, do after.

        // Instanciation of the desired service
        if(implementation != null) {
            try {
                instance = InstanceManager.getInstance(implementation);

                // Cascade injection
                EJBInjector.inject(instance);
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new ImpossibleAllocationException();
            }
        }

        // Get interceptors
        interceptors = InterceptorManager.getInterceptors(instance, method);

        for(IInterceptor interceptor : interceptors) {
            interceptor.before(instance, method, args);
        }

        Object methodReturn = method.invoke(instance, args);

        for(IInterceptor interceptor : interceptors) {
            interceptor.after(instance, method, args);
        }

        return methodReturn;
    }
}
