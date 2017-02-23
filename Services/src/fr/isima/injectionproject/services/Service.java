package fr.isima.injectionproject.services;


import fr.isima.injectionproject.container.Preferred;
import fr.isima.injectionproject.plugins.log.Log;
import fr.isima.injectionproject.plugins.transaction.Transactionnal;

import static fr.isima.injectionproject.plugins.transaction.TransactionnalStrategy.REQUIRES;

/**
 * Created by Adrien Pierreval on 03/01/2017.
 */
@Preferred
@Log
public class Service implements IService, IPreferredImplemenService, ISeveralImplemService
{
    /*
    @Inject
    IEntityManager em;
    */


    public String doSomething()
    {
        return "Hello from " + this.getClass().getSimpleName();
    }

    @Transactionnal(REQUIRES)
    public void doTransaction( ) {  }
}
