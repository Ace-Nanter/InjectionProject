package fr.isima.injectionproject.tests; /**
 * Created by Adrien Pierreval on 03/01/2017.
 */

import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Handler;
import fr.isima.injectionproject.container.Inject;
import fr.isima.injectionproject.services.IService;
import fr.isima.injectionproject.services.ISingletonService;
import fr.isima.injectionproject.services.Service;
import fr.isima.injectionproject.services.SingletonService;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

public class InjectionTest
{
    @Inject
    IService testObj1;

    @Inject
    ISingletonService testObj2;

    @Before
    public void before() throws Exception {
        EJBInjector.inject(this);
    }

    @Test
    public void test() {

        // Check proxys have been instantiated
        assertNotNull(testObj1);
        assertNotNull(testObj2);

        // Call the method
        assertEquals("Hello from Service", testObj1.doSomething());
        assertEquals("Hello from SingletonService", testObj2.doSomething());

        // Check the good implementation has been instantiated
        Handler handler1 = (Handler) Proxy.getInvocationHandler(testObj1);
        Handler handler2 = (Handler) Proxy.getInvocationHandler(testObj2);

        // Check handlers
        assertTrue(handler1 instanceof Handler);
        assertTrue(handler2 instanceof Handler);

        // Check implementations
        assertTrue(handler1.getInstance() instanceof Service);
        assertTrue(handler2.getInstance() instanceof SingletonService);
    }
}
