package fr.isima.injectionproject.plugins.transaction;

/**
 * Created by Adrien Pierreval on 24/01/2017.
 */
public @interface Transactionnal
{
    TransactionnalStrategy value();
}
