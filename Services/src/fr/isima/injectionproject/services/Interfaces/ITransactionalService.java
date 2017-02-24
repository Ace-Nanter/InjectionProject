package fr.isima.injectionproject.services.Interfaces;

/**
 * Created by Adrien Pierreval on 24/01/2017.
 */
public interface ITransactionalService
{
    String doTransactionRequires(boolean launchException) throws Exception;

    String doTransactionNew(boolean launchException) throws Exception;

    String launchTransaction(boolean require, boolean launchException) throws Exception;
}
