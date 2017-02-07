package fr.isima.injectionproject.tests;

import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.container.Inject;
import fr.isima.injectionproject.services.IService;
import fr.isima.injectionproject.services.Service;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Adrien Pierreval on 17/01/2017.
 */
public class TransactionnalTest
{
    @Inject
    IService testObjTransactionnal;

    public TransactionnalTest() {

    }

    @Test
    public void test() {
        try {
            EJBInjector.inject(this);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        assertTrue(testObjTransactionnal instanceof Service);

        // Lancement de la transaction
        testObjTransactionnal.doTransaction();

        // TODO : faire un assert pour tester le bon déroulement de la transaction
        assertTrue(false);
    }


}
