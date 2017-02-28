package fr.isima.injectionproject.services.Services;

import fr.isima.injectionproject.container.Annotations.Preferred;
import fr.isima.injectionproject.plugins.log.Log;
import fr.isima.injectionproject.services.Interfaces.ILoggedService;

/**
 * Created by acena on 28/02/2017.
 */
@Preferred
@Log
public class LoggedService implements ILoggedService {

    @Override
    public String doSomething1() {
        return "Hello from " + getClass().getSimpleName() + " 1";
    }

    @Override
    public String doSomething2() {
        return "Hello from " + getClass().getSimpleName() + " 2";
    }
}
