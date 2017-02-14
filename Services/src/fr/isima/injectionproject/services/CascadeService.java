package fr.isima.injectionproject.services;


import fr.isima.injectionproject.container.Inject;
import fr.isima.injectionproject.plugins.log.Log;

/**
 * Created by Adrien Pierreval on 14/01/2017.
 */
public class CascadeService implements ICascadeService
{
    @Inject
    IService service;

    @Log
    public IService getService() {
        return service;
    }

    public String doSomething() {
        return "Hello World";
    }
}
