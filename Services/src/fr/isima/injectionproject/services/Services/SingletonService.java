package fr.isima.injectionproject.services.Services;

import fr.isima.injectionproject.container.Annotations.Preferred;
import fr.isima.injectionproject.container.Annotations.Singleton;
import fr.isima.injectionproject.plugins.transaction.Transactionnal;
import fr.isima.injectionproject.services.Interfaces.ISingletonService;

import static fr.isima.injectionproject.plugins.transaction.TransactionnalStrategy.REQUIRES;

/**
 * Created by Adrien Pierreval on 07/02/2017.
 */
@Preferred
@Singleton
public class SingletonService implements ISingletonService
{
    public String doSomething()
    {
        return "Hello from " + this.getClass().getSimpleName();
    }

    @Transactionnal(REQUIRES)
    public void doTransaction( ) {

    }
}
