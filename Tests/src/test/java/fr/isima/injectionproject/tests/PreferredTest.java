package fr.isima.injectionproject.tests;

import fr.isima.injectionproject.container.Annotations.Inject;
import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Handler;
import fr.isima.injectionproject.services.Interfaces.IPreferredImplemenService;
import fr.isima.injectionproject.services.Services.Service;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * Created by Adrien Pierreval on 14/01/2017.
 */
public class PreferredTest
{
    @Inject
    IPreferredImplemenService testPreferredService;

    @Before
    public void before() throws Exception {
        EJBInjector.inject(this);
    }

    @Test
    public void checkProxy() {

        // Check proxy have been instantiated
        assertNotNull(testPreferredService);

        // Check representation
        assertTrue(Proxy.isProxyClass(testPreferredService.getClass()));
    }

    @Test
    public void checkInstance() {

        // Call the method
        assertEquals("Hello from Service", testPreferredService.doSomething());

        // Get outerHandler
        Handler handler = (Handler) Proxy.getInvocationHandler(testPreferredService);

        // Check outerHandler
        assertTrue(handler instanceof Handler);

        // Check implementation instantiated
        assertTrue(handler.getInstance() instanceof Service);
    }
}
