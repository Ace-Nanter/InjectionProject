package fr.isima.injectionproject.tests;

import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Inject;
import fr.isima.injectionproject.services.CascadeService;
import fr.isima.injectionproject.services.ICascadeService;
import fr.isima.injectionproject.services.Service;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Adrien Pierreval on 14/01/2017.
 */
public class CascadeTest
{
    @Inject
    ICascadeService testCascade;
    EJBInjector container;

    public CascadeTest() {

    }


    @Test
    public void TestInjection() {
        try {
            EJBInjector.inject(this);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // Test first level
        assertNotNull(testCascade);
        assertTrue(testCascade instanceof CascadeService);
        assertEquals("Hello World", testCascade.doSomething());

        // Test second level
        assertNotNull(testCascade.getService());
        assertTrue(testCascade.getService() instanceof Service);
        assertEquals("Hello World", testCascade.getService().doSomething());
    }
}
