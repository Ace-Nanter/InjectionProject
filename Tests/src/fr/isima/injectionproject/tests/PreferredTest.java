package fr.isima.injectionproject.tests;

import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Inject;
import fr.isima.injectionproject.services.IPreferredImplemenService;
import fr.isima.injectionproject.services.Service;
import org.junit.Test;

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
    public void PreferredTestInjection() {
        try {
            EJBInjector.inject(this);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        assertNotNull(testObj);
        assertTrue(testObj instanceof Service);
        assertEquals("Hello World", testObj.doSomething());
    }
}
