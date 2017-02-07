package fr.isima.injectionproject.services;

/**
 * Created by Adrien Pierreval on 14/01/2017.
 */
public class Service2 implements IPreferredImplemenService, ISeveralImplemService
{
    public String doSomething()
    {
        return "Hello World";
    }
}
