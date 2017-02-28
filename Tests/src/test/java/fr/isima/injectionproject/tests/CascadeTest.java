package fr.isima.injectionproject.tests;

import fr.isima.injectionproject.container.Annotations.Inject;
import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Handler;
import fr.isima.injectionproject.services.Interfaces.ICascadeService;
import fr.isima.injectionproject.services.Interfaces.IService;
import fr.isima.injectionproject.services.Services.CascadeService;
import fr.isima.injectionproject.services.Services.Service;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * Created by Adrien Pierreval on 14/01/2017.
 */
public class CascadeTest
{
    @Inject
    ICascadeService testCascadeService;

    Handler outerHandler;
    IService testInnerService;

    @Before
    public void before() throws Exception {
        EJBInjector.inject(this);

        // Get outerHandler
        outerHandler = (Handler) Proxy.getInvocationHandler(testCascadeService);

        // Call method
        assertEquals("Hello from CascadeService", testCascadeService.doSomething());

        // Get inner instance
        CascadeService service = (CascadeService) outerHandler.getInstance();
        testInnerService = service.getInnerService();
    }

    @Test
    public void checkProxy() {

        // Check proxy has been instantiated
        assertNotNull(testCascadeService);

        // Check representation
        assertTrue(Proxy.isProxyClass(testCascadeService.getClass()));
    }

    @Test
    public void checkOuterInstance() {

        // Check outerHandler
        assertTrue(outerHandler instanceof Handler);

        // Check implementation
        assertTrue(outerHandler.getInstance() instanceof CascadeService);
    }

    @Test
    public void checkInnerProxy() {

        // Check proxy has been instantiated
        assertNotNull(testInnerService);

        // Check representation
        assertTrue(Proxy.isProxyClass(testInnerService.getClass()));
    }

    @Test
    public void checkInnerInstance() {

        // Call the method
        assertEquals("Hello from Service", testInnerService.doSomething());

        // Check outerHandler
        Handler innerHandler = (Handler) Proxy.getInvocationHandler(testInnerService);

        // Check handler
        assertTrue(innerHandler instanceof Handler);

        // Check implementation
        assertTrue(innerHandler.getInstance() instanceof Service);
    }
}
