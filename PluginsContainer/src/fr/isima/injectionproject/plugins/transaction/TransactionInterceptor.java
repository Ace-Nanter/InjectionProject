package fr.isima.injectionproject.plugins.transaction;

import fr.isima.injectionproject.container.IInterceptor;

import java.lang.reflect.Method;

/**
 * Created by Adrien Pierreval on 14/02/2017.
 */
public class TransactionInterceptor implements IInterceptor
{
    private Transaction transaction;

    @Override
    public void before(Object obj, Method method, Object... params)
    {
        // Get requirements
        boolean askForNew;

        try {
            Method serviceMethod = obj.getClass().getMethod(method.getName(), method.getParameterTypes());
            Transactional transactionalAnnotation = serviceMethod.getDeclaredAnnotation(Transactional.class);
            askForNew = transactionalAnnotation.value() == TransactionalStrategy.REQUIRES_NEW;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            askForNew = false;
        }

        // Get transaction
        transaction = TransactionManager.getTransaction(this, askForNew);
    }

    @Override
    public void after(Object obj, Method method, Object result, Throwable exception, Object... params) {

        if(exception != null) {
            // There has been an exception
            transaction.rollback();
            TransactionManager.closeTransaction(true);
        }
        else {
            // If this interceptor owns the transaction
            if(transaction.isOwner(this)) {

                // Commit
                transaction.commit();

                // Close
                TransactionManager.closeTransaction(false);
            }
        }
    }
}
