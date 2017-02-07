package fr.isima.injectionproject.services;

import fr.isima.injectionproject.container.Preferred;
import fr.isima.injectionproject.container.Singleton;
import fr.isima.injectionproject.plugins.transaction.Transactionnal;

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
        return "Hello World";
    }

    @Transactionnal(REQUIRES)
    public void doTransaction( ) {

    }
}
