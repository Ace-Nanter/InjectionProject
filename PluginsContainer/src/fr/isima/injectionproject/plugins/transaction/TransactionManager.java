package fr.isima.injectionproject.plugins.transaction;

/**
 * Created by Adrien Pierreval on 14/02/2017.
 */

import fr.isima.injectionproject.container.IInterceptor;

import java.util.Stack;

/**
 * Manage transactions
 */
public class TransactionManager
{
    /**
     * Manage the instantiations of transaction objects
     */
    private static ThreadLocal<Stack<Transaction>> transactions = new ThreadLocal<Stack<Transaction>>() {
        @Override protected Stack<Transaction> initialValue() {
            return new Stack<Transaction>();
        }
    };

    private static int nbBegin = 0;
    private static int nbCommit = 0;
    private static int nbRollback = 0;

    public static Transaction getTransaction(IInterceptor owner, boolean askForNew) {

        Transaction transaction;

        if(askForNew || transactions.get().empty()) {

            // Create new transaction
            transaction = new Transaction();
            transaction.setOwner(owner);
            transactions.get().push(transaction);
            nbBegin++;
        }
        else {

            // Get transaction from stack
            transaction = transactions.get().peek();

        }

        return transaction;
    }

    public static void closeTransaction(boolean rollback) {

        if(rollback)
            nbRollback++;
        else
            nbCommit++;

        // Unstack the last transaction
        transactions.get().pop();
    }

    public static int getNbBegin() {
        return nbBegin;
    }

    public static int getNbCommit() {
        return nbCommit;
    }

    public static int getNbRollback() {
        return nbRollback;
    }
}
