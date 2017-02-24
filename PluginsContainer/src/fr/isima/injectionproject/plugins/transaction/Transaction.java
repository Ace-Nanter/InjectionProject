package fr.isima.injectionproject.plugins.transaction;

import fr.isima.injectionproject.container.Annotations.Preferred;
import fr.isima.injectionproject.container.IInterceptor;

/**
 * Created by acena on 24/02/2017.
 */

@Preferred
public class Transaction implements ITransaction {

    /**
     * Defines which interceptor owns the transaction and then can close it
     */
    private IInterceptor owner = null;

    public boolean isOwner(IInterceptor interceptor) {
        return owner == interceptor;
    }

    public void setOwner(IInterceptor interceptor) {
        this.owner = interceptor;
    }

    @Override
    public void begin() {

    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }
}
