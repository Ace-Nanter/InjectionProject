package fr.isima.injectionproject.container;

import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adrien Pierreval on 24/01/2017.
 */
public class EJBInjector
{


    public static void inject(Object o)
            throws NoImplementationException,
            SeveralImplementationException, ImpossibleAllocationException {

        ArrayList<Field> fieldsToInject = EJBInjector.getAnnotatedFields(o);

        Reflections reflections = new Reflections("fr.isima.injectionproject.services");

        // For every field annotated
        for(Field field : fieldsToInject) {

            Object implemToInstantiate = null;

            // Set accessibility to the field
            field.setAccessible(true);

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
                    implemToInstantiate = preferredImplementations.iterator().next();
                }
            }
            else {
                implemToInstantiate = possibleImplementations.iterator().next();
            }


            // Instanciation
            if(implemToInstantiate != null) {
                try {
                    // Check if singleton
                    if(((Class<?>) implemToInstantiate).isAnnotationPresent(Singleton.class)) {

                    }
                    else {

                    }
                    field.set(o, ((Class<?>) implemToInstantiate).newInstance());
                }
                catch (IllegalAccessException e) {
                    throw new ImpossibleAllocationException();
                } catch (InstantiationException e) {
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
            }

            // Set accessibility to the field
            field.setAccessible(false);
        }
    }

    private static ArrayList<Field> getAnnotatedFields(Object o) {

        ArrayList<Field> annotatedFields = new ArrayList<>();

        for(Field field : o.getClass().getDeclaredFields()) {
            if(field.getAnnotation(Inject.class) != null) {
                annotatedFields.add(field);
            }
        }

        return annotatedFields;
    }
}
