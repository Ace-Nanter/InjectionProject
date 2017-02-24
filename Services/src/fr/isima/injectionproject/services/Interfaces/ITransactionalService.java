package fr.isima.injectionproject.services.Interfaces;

/**
 * Created by Adrien Pierreval on 24/01/2017.
 */
public interface ITransactionalService
{
    void doTransactionRequires(boolean launchException) throws Exception;

    void doTransactionNew(boolean launchException) throws Exception;

    void launchTransaction(boolean require, boolean launchException) throws Exception;
}
