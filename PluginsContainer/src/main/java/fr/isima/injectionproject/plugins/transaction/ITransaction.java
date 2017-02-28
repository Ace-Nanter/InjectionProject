package fr.isima.injectionproject.plugins.transaction;

/**
 * Created by acena on 24/02/2017.
 */
public interface ITransaction {

    void begin();

    void commit();

    void rollback();

}
