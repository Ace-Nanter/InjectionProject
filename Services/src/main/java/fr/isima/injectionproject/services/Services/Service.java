package fr.isima.injectionproject.services.Services;


import fr.isima.injectionproject.container.Annotations.Preferred;
import fr.isima.injectionproject.plugins.log.Log;
import fr.isima.injectionproject.services.Interfaces.IPreferredImplemenService;
import fr.isima.injectionproject.services.Interfaces.IService;
import fr.isima.injectionproject.services.Interfaces.ISeveralImplemService;

/**
 * Created by Adrien Pierreval on 03/01/2017.
 */
@Preferred
public class Service implements IService, IPreferredImplemenService, ISeveralImplemService
{
    @Override
    public String doSomething()
    {
        return "Hello from " + this.getClass().getSimpleName();
    }

    @Log
    @Override
    public String doSomethingLogged() { return "Hello from " + this.getClass().getSimpleName(); }
}
