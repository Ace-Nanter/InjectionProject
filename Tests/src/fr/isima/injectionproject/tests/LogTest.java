package fr.isima.injectionproject.tests;


import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Inject;
import fr.isima.injectionproject.plugins.log.ILogger;
import fr.isima.injectionproject.services.IService;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Adrien Pierreval on 17/01/2017.
 */
public class LogTest
{
    @Inject
    ILogger logger;

    @Inject
    IService testObj;

    public LogTest() { }

    @Test
    public void test() throws Exception {

        EJBInjector.inject(this);

        // Check proxy have been instantiated
        assertNotNull(testObj);
        assertNotNull(logger);

        // Call the method
        assertEquals("Hello World", testObj.doSomething());

        // Check log
        assertTrue(logger.contains("Service - Before : doSomething"));
        assertTrue(logger.contains("Service - After : doSomething"));
        assertEquals(2, logger.size());
    }
}
