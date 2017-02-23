package fr.isima.injectionproject.plugins.transaction;

/**
 * Created by Adrien Pierreval on 24/01/2017.
 */
public enum TransactionnalStrategy
{
    REQUIRES,               // Keep current transaction
    REQUIRES_NEW            // Create a new transaction
}
