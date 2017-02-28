package fr.isima.injectionproject.container;

import fr.isima.injectionproject.container.Annotations.Singleton;
import fr.isima.injectionproject.container.Exceptions.ImpossibleAllocationException;

import java.util.HashMap;

/**
 * Created by Adrien Pierreval on 07/02/2017.
 */

/**
 * Manage the instances of the services.
 */
public class InstanceManager
{
    /**
     * Contains the instances for Singleton class.
     */
    private static HashMap<Class<?>, Object> singletonInstances = new HashMap<>();

    /**
     * Get instance of the implementation required
     * @param implementation Implementation required
     * @return The instance of the implementation required
     * @throws ImpossibleAllocationException If something goes wrong
     */
    protected static Object getInstance(Class<?> implementation) throws Exception
    {
        Object instance;
        // If Singleton
        if (implementation.isAnnotationPresent(Singleton.class)) {
            // If it is not already instantiated, we instantiate and put the instance in a HashMap
            if (!singletonInstances.containsKey(implementation)) {
                instance = implementation.newInstance();
                singletonInstances.put(implementation, instance);
            } else {
                instance = singletonInstances.get(implementation);
            }
        }
        // No Singleton annotation found, normal proceed
        else {
            instance = implementation.newInstance();
        }

        return instance;
    }
}
