package fr.isima.injectionproject.services.Services;

import fr.isima.injectionproject.container.Annotations.Preferred;
import fr.isima.injectionproject.container.Annotations.Singleton;
import fr.isima.injectionproject.services.Interfaces.ISingletonService;

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
}
