package fr.isima.injectionproject.plugins.log;

/**
 * Created by Adrien Pierreval on 14/02/2017.
 */
public interface ILogger
{
    int size();

    boolean contains(String log);

    void add(String log);

    void clear();
}
