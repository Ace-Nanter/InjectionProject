package fr.isima.injectionproject.tests;

import fr.isima.injectionproject.container.Annotations.Inject;
import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.plugins.transaction.Transaction;
import fr.isima.injectionproject.plugins.transaction.TransactionManager;
import fr.isima.injectionproject.services.Interfaces.ITransactionalService;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Adrien Pierreval on 17/01/2017.
 */

public class TransactionalTest
{
    @Inject
    ITransactionalService testTransactionService;

    private int nbBegin;
    private int nbCommit;
    private int nbRollback;

    @Before
    public void before() throws Exception {

        EJBInjector.inject(this);

        // Get stats
        nbBegin = TransactionManager.getNbBegin();
        nbCommit = TransactionManager.getNbCommit();
        nbRollback = TransactionManager.getNbRollback();
    }

    @Test
    public void checkProxy() {

        // Check proxy has been instantiated
        assertNotNull(testTransactionService);

        // Check representation
        assertTrue(Proxy.isProxyClass(testTransactionService.getClass()));
    }

    @Test
    public void checkRequiresBeginCommit() throws Exception {

        // Call the method
        assertEquals("Hello from TransactionalService", testTransactionService.doTransactionRequires(false));

        // Check stats
        assertEquals(nbBegin + 1, TransactionManager.getNbBegin());
        assertEquals(nbCommit + 1, TransactionManager.getNbCommit());
        assertEquals(nbRollback, TransactionManager.getNbRollback());
    }

    @Test
    public void checkRequiresBeginRollback() throws Exception {
        // Call the method
        try {
            assertEquals("Hello from TransactionalService", testTransactionService.doTransactionRequires(true));
        }
        catch(Exception e) {
            assertEquals("This is an exception", e.getMessage());
        }


        // Check stats
        assertEquals(nbBegin + 1, TransactionManager.getNbBegin());
        assertEquals(nbCommit, TransactionManager.getNbCommit());
        assertEquals(nbRollback + 1, TransactionManager.getNbRollback());
    }


    /*

    // tester service imbriqués et seconde transaction
    @Test
    public void testRequiresRequires() {
        long 	b = Transaction.numberOfBegin,
                r = Transaction.numberOfCommit,
                c = Transaction.numberOfRollback;
        assertTrue(service != null);
        service.transactionalMethod4();
        assertTrue(Transaction.numberOfBegin==b+1);
        assertTrue(Transaction.numberOfRollback==r);
        assertTrue(Transaction.numberOfCommit==c+1);
    }


    // begin & rollback
    @Test
    public void testRequiresRequiresNew() {
        long 	b = Transaction.numberOfBegin,
                r = Transaction.numberOfCommit,
                c = Transaction.numberOfRollback;
        assertTrue(service != null);
        service.transactionalMethod2();
        assertTrue(Transaction.numberOfBegin==b+2);
        assertTrue(Transaction.numberOfRollback==r);
        assertTrue(Transaction.numberOfCommit==c+2);
    }
*/


}
