package fr.isima.injectionproject.tests; /**
 * Created by Adrien Pierreval on 03/01/2017.
 */

import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Handler;
import fr.isima.injectionproject.container.Annotations.Inject;
import fr.isima.injectionproject.services.Interfaces.IService;
import fr.isima.injectionproject.services.Interfaces.ISingletonService;
import fr.isima.injectionproject.services.Services.Service;
import fr.isima.injectionproject.services.Services.SingletonService;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

public class InjectionTest
{
    @Inject
    IService testService1;

    @Inject
    ISingletonService testService2;

    @Before
    public void before() throws Exception {
        EJBInjector.inject(this);
    }

    @Test
    public void checkProxies() {

        // Check proxies have been instantiated
        assertNotNull(testService1);
        assertNotNull(testService2);

        // Check representation
        assertTrue(Proxy.isProxyClass(testService1.getClass()));
        assertTrue(Proxy.isProxyClass(testService2.getClass()));
    }

    @Test
    public void checkInstances() {

        // Call the methods
        assertEquals("Hello from Service", testService1.doSomething());
        assertEquals("Hello from SingletonService", testService2.doSomething());

        // Get handlers
        Handler handler1 = (Handler) Proxy.getInvocationHandler(testService1);
        Handler handler2 = (Handler) Proxy.getInvocationHandler(testService2);

        // Check handlers
        assertTrue(handler1 instanceof Handler);
        assertTrue(handler2 instanceof Handler);

        // Check implementations
        assertTrue(handler1.getInstance() instanceof Service);
        assertTrue(handler2.getInstance() instanceof SingletonService);
    }
}
