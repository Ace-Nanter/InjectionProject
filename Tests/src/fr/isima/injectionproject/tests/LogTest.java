package fr.isima.injectionproject.tests;


import fr.isima.injectionproject.container.Annotations.Inject;
import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Handler;
import fr.isima.injectionproject.plugins.log.ILogger;
import fr.isima.injectionproject.services.Interfaces.IService;
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

    Handler handler;

    @Before
    public void before() throws Exception {

        EJBInjector.inject(this);

        handler = (Handler) Proxy.getInvocationHandler(testService);

        // Call the method
        assertEquals("Hello from Service", testService.doSomething());
    }

    @Test
    public void checkProxy() {

        // Check proxies have been instantiated
        assertNotNull(testService);
        assertNotNull(logger);

        // Check representation
        assertTrue(Proxy.isProxyClass(testService.getClass()));
        assertTrue(Proxy.isProxyClass(logger.getClass()));
    }

    @Test
    public void checkInstances() {

        // Check handler
        assertTrue(handler instanceof Handler);

        // Check instance
        assertTrue(handler.getInstance() instanceof Service);
    }

    @Test
    public void checkLog() {

        // Check log
        assertTrue(logger.contains("Service - Before : doSomething"));
        assertTrue(logger.contains("Service - After : doSomething"));
    }
}
