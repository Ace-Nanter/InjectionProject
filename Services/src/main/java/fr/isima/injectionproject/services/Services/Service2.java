package fr.isima.injectionproject.services.Services;

import fr.isima.injectionproject.services.Interfaces.IPreferredImplemenService;
import fr.isima.injectionproject.services.Interfaces.ISeveralImplemService;

/**
 * Created by Adrien Pierreval on 14/01/2017.
 */
public class Service2 implements IPreferredImplemenService, ISeveralImplemService
{
    public String doSomething()
    {
        return "Hello from " + this.getClass().getSimpleName();
    }
}
