package fr.isima.injectionproject.tests;

import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Handler;
import fr.isima.injectionproject.container.Inject;
import fr.isima.injectionproject.container.Singleton;
import fr.isima.injectionproject.services.IService;
import fr.isima.injectionproject.services.ISingletonService;
import fr.isima.injectionproject.services.Service;
import fr.isima.injectionproject.services.SingletonService;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * Created by Adrien Pierreval on 10/01/2017.
 */
public class SingletonTest
{
    @Inject
    ISingletonService testSingleton1;

    @Inject
    ISingletonService testSingleton2;

    @Inject
    IService testNonSingleton1;

    @Inject
    IService testNonSingleton2;

    @Before
    public void before() throws Exception {
        EJBInjector.inject(this);
    }

    @Test
    public void test() throws Exception {

        // Verify everything has been instantiated
        assertNotNull(testSingleton1);
        assertNotNull(testSingleton2);
        assertNotNull(testNonSingleton1);
        assertNotNull(testNonSingleton2);

        // Call methods
        assertEquals("Hello from SingletonService", testSingleton1.doSomething());
        assertEquals("Hello from SingletonService", testSingleton2.doSomething());
        assertEquals("Hello from Service", testNonSingleton1.doSomething());
        assertEquals("Hello from Service", testNonSingleton2.doSomething());

        // Check handlers
        Handler handler1 = (Handler) Proxy.getInvocationHandler(testSingleton1);
        Handler handler2 = (Handler) Proxy.getInvocationHandler(testSingleton2);
        Handler handler3 = (Handler) Proxy.getInvocationHandler(testNonSingleton1);
        Handler handler4 = (Handler) Proxy.getInvocationHandler(testNonSingleton2);

        // Verify implementations
        assertTrue(handler1.getInstance() instanceof SingletonService);
        assertTrue(handler2.getInstance() instanceof SingletonService);
        assertTrue(handler3.getInstance() instanceof Service);
        assertTrue(handler4.getInstance() instanceof Service);

        // Verify singleton has been respected
        assertSame(handler1.getInstance(), handler2.getInstance());

        // Verify singleton is not working every time
        assertNotSame(handler3.getInstance(), handler4.getInstance());
    }
}
