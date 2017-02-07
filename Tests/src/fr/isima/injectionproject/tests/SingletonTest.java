package fr.isima.injectionproject.tests;

import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Inject;
import fr.isima.injectionproject.container.Singleton;
import fr.isima.injectionproject.services.ISingletonService;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * Created by Adrien Pierreval on 10/01/2017.
 */
public class SingletonTest
{
    @Inject
    @Singleton
    ISingletonService testObj1;

    @Inject
    @Singleton
    ISingletonService testObj2;

    public SingletonTest() {

    }


    @Test
    public void firstTest() {

        boolean flag = true;

        try {
            // Inject objects
            EJBInjector.inject(this);

            // Verify that objects are not null
            assertNotNull(testObj1);
            assertNotNull(testObj2);

            // Verify it is the same instance
            assertSame(testObj1, testObj2);
        }
        catch(Exception e) {
            flag = false;
        }

        assertTrue(flag);
    }
}
