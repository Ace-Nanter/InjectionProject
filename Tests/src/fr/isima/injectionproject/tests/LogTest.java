package fr.isima.injectionproject.tests;


import fr.isima.injectionproject.container.*;
import fr.isima.injectionproject.plugins.log.MyLogger;
import fr.isima.injectionproject.services.ICascadeService;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Adrien Pierreval on 17/01/2017.
 */
public class LogTest
{
    @Inject
    MyLogger logger;

    @Inject
    ICascadeService testLog;

    public LogTest() {

    }


    @Test
    public void TestInjection() throws SeveralImplementationException, ImpossibleAllocationException, NoImplementationException
    {

        EJBInjector.inject(this);


        // On effectue l'action
        testLog.getService();

        // Test que le log a agi
        assertTrue(logger.getLog().contains("Service"));
    }
}
