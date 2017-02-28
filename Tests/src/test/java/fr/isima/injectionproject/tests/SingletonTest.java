package fr.isima.injectionproject.tests;

import fr.isima.injectionproject.container.Annotations.Inject;
import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Handler;
import fr.isima.injectionproject.services.Interfaces.IService;
import fr.isima.injectionproject.services.Interfaces.ISingletonService;
import fr.isima.injectionproject.services.Services.Service;
import fr.isima.injectionproject.services.Services.SingletonService;
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

    Handler[] handlers;

    @Before
    public void before() throws Exception {

        EJBInjector.inject(this);

        // Get handlers
        handlers = new Handler[4];

        handlers[0] = (Handler) Proxy.getInvocationHandler(testSingleton1);
        handlers[1] = (Handler) Proxy.getInvocationHandler(testSingleton2);
        handlers[2] = (Handler) Proxy.getInvocationHandler(testNonSingleton1);
        handlers[3] = (Handler) Proxy.getInvocationHandler(testNonSingleton2);

        // Call methods
        assertEquals("Hello from SingletonService", testSingleton1.doSomething());
        assertEquals("Hello from SingletonService", testSingleton2.doSomething());
        assertEquals("Hello from Service", testNonSingleton1.doSomething());
        assertEquals("Hello from Service", testNonSingleton2.doSomething());
    }

    @Test
    public void checkProxies() throws Exception {

        // Check proxies have been instantiated
        assertNotNull(testSingleton1);
        assertNotNull(testSingleton2);
        assertNotNull(testNonSingleton1);
        assertNotNull(testNonSingleton2);

        // Check representations
        assertTrue(Proxy.isProxyClass(testSingleton1.getClass()));
        assertTrue(Proxy.isProxyClass(testSingleton2.getClass()));
        assertTrue(Proxy.isProxyClass(testNonSingleton1.getClass()));
        assertTrue(Proxy.isProxyClass(testNonSingleton2.getClass()));
    }

    @Test
    public void checkInstances() {

        // Verify implementations
        assertTrue(handlers[0].getInstance() instanceof SingletonService);
        assertTrue(handlers[1].getInstance() instanceof SingletonService);
        assertTrue(handlers[2].getInstance() instanceof Service);
        assertTrue(handlers[3].getInstance() instanceof Service);
    }

    @Test
    public void verifySingleton() {

        // Verify singleton has been respected
        assertSame(handlers[0].getInstance(), handlers[1].getInstance());
    }

    @Test
    public void verifyNonSingleton() {

        // Verify singleton is not working every time
        assertNotSame(handlers[2].getInstance(), handlers[3].getInstance());
    }
}
