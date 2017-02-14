package fr.isima.injectionproject.tests;

import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Handler;
import fr.isima.injectionproject.container.Inject;
import fr.isima.injectionproject.services.CascadeService;
import fr.isima.injectionproject.services.ICascadeService;
import fr.isima.injectionproject.services.IService;
import fr.isima.injectionproject.services.Service;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * Created by Adrien Pierreval on 14/01/2017.
 */
public class CascadeTest
{
    @Inject
    ICascadeService testCascade;

    public CascadeTest() {  }



    @Test
    public void test() throws Exception {
        EJBInjector.inject(this);

        /*------- First level -------*/

        // Check proxy have been instantiated
        assertNotNull(testCascade);

        // Call the method
        assertEquals("Hello World", testCascade.doSomething());

        // Check the good implementation has been instantiated
        Handler handler1 = (Handler) Proxy.getInvocationHandler(testCascade);

        // Check handlers
        assertTrue(handler1 instanceof Handler);

        // Check implementation
        assertTrue(handler1.getInstance() instanceof CascadeService);


        /*------- Second level -------*/

        CascadeService service = (CascadeService) handler1.getInstance();
        IService serviceInjected = service.getService();

        // Check proxy have been instantiated
        assertNotNull(serviceInjected);

        // Call the method
        assertEquals("Hello World", serviceInjected.doSomething());

        // Check the good implementation has been instantiated
        Handler handler2 = (Handler) Proxy.getInvocationHandler(serviceInjected);

        // Check handlers
        assertTrue(handler2 instanceof Handler);

        // Check implementation
        assertTrue(handler2.getInstance() instanceof Service);
    }
}
