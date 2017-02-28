package fr.isima.injectionproject.services.Services;

import fr.isima.injectionproject.container.Annotations.Inject;
import fr.isima.injectionproject.container.Annotations.Preferred;
import fr.isima.injectionproject.plugins.transaction.Transactional;
import fr.isima.injectionproject.plugins.transaction.TransactionalStrategy;
import fr.isima.injectionproject.services.Interfaces.ITransactionalService;

/**
 * Created by acena on 24/02/2017.
 */

@Preferred
public class TransactionalService implements ITransactionalService {

    @Inject
    ITransactionalService innerService;

    @Override
    @Transactional(value = TransactionalStrategy.REQUIRES)
    public String doTransactionRequires(boolean launchException) throws Exception {
        if(launchException) {
            throw new Exception("This is an exception");
        }

        return "Hello from " + this.getClass().getSimpleName();
    }

    @Override
    @Transactional(value = TransactionalStrategy.REQUIRES_NEW)
    public String doTransactionNew(boolean launchException) throws Exception {
        if(launchException) {
            throw new Exception("This is an exception");
        }

        return "Hello from " + this.getClass().getSimpleName();
    }

    @Override
    @Transactional(value = TransactionalStrategy.REQUIRES)
    public String launchTransaction(boolean require, boolean launchException) throws Exception {
        try {
            if(require) {
                innerService.doTransactionRequires(launchException);
            }
            else {
                innerService.doTransactionNew(launchException);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return "Hello from " + this.getClass().getSimpleName();
    }


}
