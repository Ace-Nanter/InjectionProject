package fr.isima.injectionproject.tests;

import fr.isima.injectionproject.container.Annotations.Inject;
import fr.isima.injectionproject.container.EJBInjector;
import fr.isima.injectionproject.services.Interfaces.ITransactionalService;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Adrien Pierreval on 17/01/2017.
 */

public class TransactionalTest
{
    @Inject
    ITransactionalService testTransactionService;

    private void getStats() {
        // Get transaction stats
    }

    private void checkStats() {
        // Check stats are correct after execution
    }

    @Before
    public void before() throws Exception {

        EJBInjector.inject(this);
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

        // Get stats
        getStats();

        // Call the method
        testTransactionService.doTransactionRequires(false);

        // Check
        checkStats();
    }

/*
    // begin & commit
    @Test
    public void testBeginCommit() {


        long 	b = Transaction.numberOfBegin,
                r = Transaction.numberOfCommit,
                c = Transaction.numberOfRollback;


        assertTrue(service != null);
        service.transactionalMethod(1);
        assertTrue(Transaction.numberOfBegin==b+1);
        assertTrue(Transaction.numberOfRollback==r);
        assertTrue(Transaction.numberOfCommit==c+1);
    }

    // begin & rollback
    @Test
    public void testBeginRollback() {
        long 	b = Transaction.numberOfBegin,
                r = Transaction.numberOfCommit,
                c = Transaction.numberOfRollback;
        assertTrue(service != null);
        service.transactionalMethod(0);
        assertTrue(Transaction.numberOfBegin==b+1);
        assertTrue(Transaction.numberOfRollback==r+1);
        assertTrue(Transaction.numberOfCommit==c);
    }

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
