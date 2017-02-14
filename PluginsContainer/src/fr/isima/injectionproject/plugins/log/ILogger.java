package fr.isima.injectionproject.plugins.log;

/**
 * Created by Adrien Pierreval on 14/02/2017.
 */
public interface ILogger
{
    public int size();

    public boolean contains(String log);

    public void add(String log);

}
