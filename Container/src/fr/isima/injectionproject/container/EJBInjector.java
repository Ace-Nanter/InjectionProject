package fr.isima.injectionproject.container;

import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Adrien Pierreval on 24/01/2017.
 */
public class EJBInjector
{

    private static HashMap<Class<?>, Object> singletonInstances = new HashMap<>();
    private static Reflections reflections = new Reflections("fr.isima.injectionproject.services");


    public static void inject(Object o)
            throws NoImplementationException,
            SeveralImplementationException, ImpossibleAllocationException {

        ArrayList<Field> fieldsToInject = EJBInjector.getAnnotatedFields(o);



        // For every field annotated
        for(Field field : fieldsToInject) {

            Object implemToInstantiate = null;

            // Set accessibility to the field
            field.setAccessible(true);

            // Get implementation
            implemToInstantiate = getImplementation(field);

            // Instanciation
            if(implemToInstantiate != null) {
                try {
                    Object instance = InstanceManager.getInstance((Class<?>) implemToInstantiate);
                    field.set(o, instance);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    throw new ImpossibleAllocationException();
                }
            }

            // Launch recursive call for cascade
            try
            {
                inject(field.get(o));
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
                throw new ImpossibleAllocationException();
            }

            // Set accessibility to the field
            field.setAccessible(false);
        }
    }

    /**
     * Get Fields to inject from an object
     * @param o Object to analyse
     * @return The fields to inject
     */
    private static ArrayList<Field> getAnnotatedFields(Object o) {

        ArrayList<Field> annotatedFields = new ArrayList<>();

        for(Field field : o.getClass().getDeclaredFields()) {
            if(field.getAnnotation(Inject.class) != null) {
                annotatedFields.add(field);
            }
        }

        return annotatedFields;
    }

    /**
     * Get the implementation corresponding to a field
     * @param field Field to find the implementation
     * @return The implementation found or null if there is a problem
     * @throws NoImplementationException Thrown in case there is no implementation
     * @throws SeveralImplementationException Thrown in case there are several implementation
     */
    private static Class<?> getImplementation(Field field) throws NoImplementationException, SeveralImplementationException
    {
        Class<?> implementation = null;

        // Get implementations
        Class<?> interfaceType = field.getType();
        Set<?> possibleImplementations = reflections.getSubTypesOf(interfaceType);

        // If no implementation
        if(possibleImplementations.isEmpty()) {
            throw new NoImplementationException();
        }
        // There are several implementation
        else if(possibleImplementations.size() > 1) {

            Set<Object> preferredImplementations = new HashSet<>();

            // Get all preferred implementations
            for(Object implem : possibleImplementations) {
                if(((Class<?>) implem).isAnnotationPresent(Preferred.class)) {
                    preferredImplementations.add(implem);
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
