package fr.isima.injectionproject.services.Services;

import fr.isima.injectionproject.container.Annotations.Preferred;
import fr.isima.injectionproject.services.Interfaces.ISeveralImplemService;

/**
 * Created by Adrien Pierreval on 01/02/2017.
 */
@Preferred
public class Service3 implements ISeveralImplemService
{
    public String doSomething()
    {
        return "Hello from " + this.getClass().getSimpleName();
    }
}