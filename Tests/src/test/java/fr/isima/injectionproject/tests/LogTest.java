package fr.isima.injectionproject.tests;


import fr.isima.injectionproject.container.Annotations.Inject;
import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Handler;
import fr.isima.injectionproject.plugins.log.ILogger;
import fr.isima.injectionproject.services.Interfaces.ILoggedService;
import fr.isima.injectionproject.services.Interfaces.IService;
import fr.isima.injectionproject.services.Services.LoggedService;
import fr.isima.injectionproject.services.Services.Service;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * Created by Adrien Pierreval on 17/01/2017.
 */
public class LogTest
{
    @Inject
    ILogger logger;

    @Inject
    IService testService;

    @Inject
    ILoggedService testLoggedService;

    Handler handler1;
    Handler handler2;

    @Before
    public void before() throws Exception {

        EJBInjector.inject(this);

        handler1 = (Handler) Proxy.getInvocationHandler(testService);
        handler2 = (Handler) Proxy.getInvocationHandler(testLoggedService);

        // Call method to instantiate
        assertEquals("Hello from Service", testService.doSomething());
        assertEquals("Hello from LoggedService 1", testLoggedService.doSomething1());

        // Clear log
        logger.clear();
    }

    @Test
    public void checkProxy() {

        // Check proxies have been instantiated
        assertNotNull(testService);
        assertNotNull(testLoggedService);
        assertNotNull(logger);

        // Check representations
        assertTrue(Proxy.isProxyClass(testService.getClass()));
        assertTrue(Proxy.isProxyClass(testLoggedService.getClass()));
        assertTrue(Proxy.isProxyClass(logger.getClass()));
    }

    @Test
    public void checkInstances() {

        // Check handlers
        assertTrue(handler1 instanceof Handler);
        assertTrue(handler2 instanceof Handler);

        // Check instance
        assertTrue(handler1.getInstance() instanceof Service);
        assertTrue(handler2.getInstance() instanceof LoggedService);
    }

    @Test
    public void checkLoggedMethod() {

        // Verify logger is empty
        assertEquals(0, logger.size());

        // Call logged method
        assertEquals("Hello from Service", testService.doSomethingLogged());

        // Check log
        assertTrue(logger.contains("Service - Before : doSomethingLogged"));
        assertTrue(logger.contains("Service - After : doSomethingLogged"));

        assertEquals(2, logger.size());
    }

    @Test
    public void checkLoggedService() {

        // Verify logger is empty
        assertEquals(0, logger.size());

        // Call method from logged service
        assertEquals("Hello from LoggedService 1", testLoggedService.doSomething1());
        assertEquals("Hello from LoggedService 2", testLoggedService.doSomething2());

        // Check log
        assertTrue(logger.contains("LoggedService - Before : doSomething1"));
        assertTrue(logger.contains("LoggedService - After : doSomething1"));
        assertTrue(logger.contains("LoggedService - Before : doSomething2"));
        assertTrue(logger.contains("LoggedService - After : doSomething2"));

        assertEquals(4, logger.size());
    }
}
