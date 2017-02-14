package fr.isima.injectionproject.tests;

import fr.isima.injectionproject.container.*;
import fr.isima.injectionproject.services.INoImplemService;
import org.junit.Test;

/**
 * Created by Adrien Pierreval on 10/01/2017.
 */
public class NoImplementationTest
{
    @Inject
    INoImplemService testNoImplem;

    public NoImplementationTest() {

    }

    @Test(expected = NoImplementationException.class)
    public void noClassFoundTest() throws Exception {
        EJBInjector.inject(this);
    }
}
