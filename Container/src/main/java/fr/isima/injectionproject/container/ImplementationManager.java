package fr.isima.injectionproject.container;

import fr.isima.injectionproject.container.Annotations.Preferred;
import fr.isima.injectionproject.container.Exceptions.NoImplementationException;
import fr.isima.injectionproject.container.Exceptions.SeveralImplementationException;
import org.reflections.Reflections;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adrien Pierreval on 08/02/2017.
 */

/**
 * Manage the implementations
 */
public class ImplementationManager
{
    private static Reflections reflections = null;

    /**
     * Get the Reflections object. Instantiate if necessary
     * @return Reflections instance
     */
    private static Reflections getReflections() {
        if(reflections == null) {
            reflections = new Reflections("fr.isima.injectionproject");
        }

        return reflections;
    }

    /**
     * Get the implementation corresponding to a field
     * @param givenInterface Interface for which we are looking an implementation
     * @return The implementation found or null if there is a problem
     * @throws NoImplementationException Thrown in case there is no implementation
     * @throws SeveralImplementationException Thrown in case there are several implementation
     */
    protected static Class<?> getImplementation(Class<?> givenInterface) throws NoImplementationException, SeveralImplementationException
    {
        Class<?> implementation;

        // Get all possible implementations
        Set<?> possibleImplementations = getReflections().getSubTypesOf(givenInterface);

        // If no implementation
        if(possibleImplementations.isEmpty()) {
            throw new NoImplementationException();
        }
        // There are several implementation
        else if(possibleImplementations.size() > 1) {

            Set<Object> preferredImplementations = new HashSet<>();

            // Get all preferred implementations
            for(Object i : possibleImplementations) {
                if(((Class<?>) i).isAnnotationPresent(Preferred.class)) {
                    preferredImplementations.add(i);
                }
            }

            if(preferredImplementations.isEmpty() || preferredImplementations.size() > 1) {
                throw new SeveralImplementationException();
            }
            else {
                // Only one preferred implementation
                implementation = (Class<?>) preferredImplementations.iterator().next();
            }
        }
        else {
            implementation = (Class<?>) possibleImplementations.iterator().next();
        }

        return implementation;
    }
}
