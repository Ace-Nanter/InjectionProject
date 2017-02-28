package fr.isima.injectionproject.tests;

import fr.isima.injectionproject.container.Annotations.Inject;
import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Exceptions.NoImplementationException;
import fr.isima.injectionproject.services.Interfaces.INoImplemService;
import org.junit.Test;

/**
 * Created by Adrien Pierreval on 10/01/2017.
 */
public class NoImplementationTest
{
    @Inject
    INoImplemService testNoImplem;

    @Test(expected = NoImplementationException.class)
    public void checkException() throws Exception {
        EJBInjector.inject(this);
    }
}
