package fr.isima.injectionproject.services;

import fr.isima.injectionproject.container.Preferred;

/**
 * Created by Adrien Pierreval on 01/02/2017.
 */
@Preferred
public class Service3 implements ISeveralImplemService
{
    public String doSomething()
    {
        return "Hello World";
    }
}