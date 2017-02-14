package fr.isima.injectionproject.tests;

import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Handler;
import fr.isima.injectionproject.container.Inject;
import fr.isima.injectionproject.container.Singleton;
import fr.isima.injectionproject.services.ISingletonService;
import fr.isima.injectionproject.services.SingletonService;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * Created by Adrien Pierreval on 10/01/2017.
 */
public class SingletonTest
{
    @Inject
    @Singleton
    ISingletonService testObj1;

    @Inject
    @Singleton
    ISingletonService testObj2;

    public SingletonTest() {

    }

    @Test
    public void test() throws Exception {

        // Inject objects
        EJBInjector.inject(this);

        // Verify proxy has been instantiated
        assertNotNull(testObj1);
        assertNotNull(testObj2);

        // Call methods
        assertEquals("Hello Singleton", testObj1.doSomething());
        assertEquals("Hello Singleton", testObj2.doSomething());

        // Check instances are the same
        Handler handler1 = (Handler) Proxy.getInvocationHandler(testObj1);
        Handler handler2 = (Handler) Proxy.getInvocationHandler(testObj2);

        // Verify implementations
        assertTrue(handler1.getInstance() instanceof SingletonService);
        assertTrue(handler2.getInstance() instanceof SingletonService);

        // Verify singleton has been respected
        assertSame(handler1.getInstance(), handler2.getInstance());
    }
}
