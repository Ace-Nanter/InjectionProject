package fr.isima.injectionproject.container;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Adrien Pierreval on 24/01/2017.
 */
public class EJBInjector
{

    private static HashMap<Class<?>, Object> singletonInstances = new HashMap<>();

    public static void inject(Object o) throws Exception
    {

        ArrayList<Field> fieldsToInject = EJBInjector.getAnnotatedFields(o);

        // For every field annotated
        for(Field field : fieldsToInject) {

            // Set accessibility to the field
            field.setAccessible(true);

            try {
                // Give proxy to the field
                field.set(o, getProxy(field.getType()));
            }
            catch(IllegalAccessException e) {
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

    private static Object getProxy(Class<?> givenInterface) throws Exception
    {
        Class<?> implementation = ImplementationManager.getImplementation(givenInterface);

        return Proxy.newProxyInstance(givenInterface.getClassLoader(),
                new Class[] { givenInterface },
                new Handler(implementation));
    }
}
