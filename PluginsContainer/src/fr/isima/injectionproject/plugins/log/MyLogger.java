package fr.isima.injectionproject.plugins.log;

import fr.isima.injectionproject.container.Singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrien Pierreval on 24/01/2017.
 */

/**
 * Manage the log
 */
@Singleton /* Singleton pour éviter de faire plusieurs instances du logger */
public class MyLogger
{
    // Liste de logs pour chaque méthode
    private List<String> logList = new ArrayList<String>();

    public boolean contains(String log) {
        return logList.contains(log);
    }

    public void add(String log) {
        logList.add(log);
    }

    public MyLogger getLog()
    {
//TODO : to implement ?
        return null;
    }
}
