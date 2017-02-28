package fr.isima.injectionproject.tests;

import fr.isima.injectionproject.container.Annotations.Inject;
import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Exceptions.SeveralImplementationException;
import fr.isima.injectionproject.services.Interfaces.ISeveralImplemService;
import org.junit.Test;

/**
 * Created by Adrien Pierreval on 10/01/2017.
 */
public class SeveralImplementationTest
{
    @Inject
    ISeveralImplemService service;

    @Test(expected = SeveralImplementationException.class)
    public void checkException() throws Exception {
        EJBInjector.inject(this);
    }
}
