package fr.isima.injectionproject.tests; /**
 * Created by Adrien Pierreval on 03/01/2017.
 */

import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Inject;
import fr.isima.injectionproject.services.IService;
import fr.isima.injectionproject.services.Service;
import org.junit.Test;

import static org.junit.Assert.*;

public class InjectionTest
{
    @Inject
    IService testObj;
    EJBInjector container;

    public InjectionTest()
    {

    }

    @Test
    public void test() {

        Boolean flag = true;

        try {
            EJBInjector.inject(this);
            assertNotNull(testObj);
            assertTrue(testObj instanceof Service);
            assertEquals("Hello World", testObj.doSomething());
        }
        catch(Exception e) {
            flag = false;
        }

        assertTrue(flag);
    }
}
