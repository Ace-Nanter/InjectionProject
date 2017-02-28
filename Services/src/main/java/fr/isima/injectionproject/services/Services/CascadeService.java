package fr.isima.injectionproject.services.Services;


import fr.isima.injectionproject.container.Annotations.Inject;
import fr.isima.injectionproject.services.Interfaces.ICascadeService;
import fr.isima.injectionproject.services.Interfaces.IService;

/**
 * Created by Adrien Pierreval on 14/01/2017.
 */
public class CascadeService implements ICascadeService
{
    @Inject
    IService service;

    public IService getInnerService() {
        return service;
    }

    public String doSomething()
    {
        return "Hello from " + this.getClass().getSimpleName();
    }
}
