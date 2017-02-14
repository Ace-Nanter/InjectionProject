package fr.isima.injectionproject.tests;

import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Handler;
import fr.isima.injectionproject.container.Inject;
import fr.isima.injectionproject.services.IPreferredImplemenService;
import fr.isima.injectionproject.services.Service;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * Created by Adrien Pierreval on 14/01/2017.
 */
public class PreferredTest
{
    @Inject
    IPreferredImplemenService testObj;

    public PreferredTest() {

    }

    @Test
    public void PreferredTestInjection() throws Exception {
        EJBInjector.inject(this);

        // Check proxy have been instantiated
        assertNotNull(testObj);

        // Call the method
        assertEquals("Hello World", testObj.doSomething());

        // Check the good implementation has been instantiated
        Handler handler = (Handler) Proxy.getInvocationHandler(testObj);

        // Check handlers
        assertTrue(handler instanceof Handler);

        // Check implementation instantiated
        assertTrue(handler.getInstance() instanceof Service);
    }
}
