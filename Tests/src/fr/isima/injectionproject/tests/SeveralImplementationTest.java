package fr.isima.injectionproject.tests;

import fr.isima.injectionproject.container.*;
import fr.isima.injectionproject.services.ISeveralImplemService;
import org.junit.Test;

/**
 * Created by Adrien Pierreval on 10/01/2017.
 */
public class SeveralImplementationTest
{

    // TODO : faire plusieurs classes de test : une pour chaque cas. De cette manière on peut mieux gérer les exceptions au cas par cas

    @Inject
    ISeveralImplemService service;

    public SeveralImplementationTest() { }


    @Test(expected = SeveralImplementationException.class)
    public void severalImplementationTest() throws Exception {
        EJBInjector.inject(this);
    }
}
