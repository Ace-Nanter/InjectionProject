package fr.isima.injectionproject.tests;

import fr.isima.injectionproject.container.Annotations.Inject;
import fr.isima.injectionproject.services.Interfaces.IService;

/**
 * Created by Adrien Pierreval on 17/01/2017.
 */
public class TransactionnalTest
{
    @Inject
    IService service;
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
