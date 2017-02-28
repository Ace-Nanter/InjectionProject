package fr.isima.injectionproject.plugins.log;

import fr.isima.injectionproject.container.Annotations.Preferred;
import fr.isima.injectionproject.container.Annotations.Singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrien Pierreval on 24/01/2017.
 */

/**
 * Manage the log
 */
@Preferred
@Singleton // Singleton pour éviter de faire plusieurs instances du logger
public class Logger implements ILogger
{
    // Liste de logs pour chaque méthode
    private List<String> logList = new ArrayList<>();

    @Override
    public int size() {
        return logList.size();
    }

    @Override
    public boolean contains(String log) {
        return logList.contains(log);
    }

    @Override
    public void add(String log) {
        logList.add(log);
    }

    @Override
    public void clear() {
        logList.clear();
    }
}
