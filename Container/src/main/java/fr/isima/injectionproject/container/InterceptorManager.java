package fr.isima.injectionproject.container;

import fr.isima.injectionproject.container.Annotations.Behaviour;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;

/**
 * Created by Adrien Pierreval on 14/02/2017.
 */
public class InterceptorManager
{
    private static HashSet<Annotation> getAnnotations(Object instance, Method method) throws Exception {

        HashSet<Annotation> annotations = new HashSet<>();

        // Service annotations
        for(Annotation annotation : instance.getClass().getDeclaredAnnotations()) {
            annotations.add(annotation);
        }

        // Get method of service
        Method serviceMethod = instance.getClass().getMethod(method.getName(), method.getParameterTypes());

        // Method annotations
        for(Annotation annotation : serviceMethod.getDeclaredAnnotations()) {
            annotations.add(annotation);
        }

        return annotations;
    }

    protected static HashSet<IInterceptor> getInterceptors(Object instance, Method method) throws Exception {

        HashSet<IInterceptor> interceptors = new HashSet<>();

        HashSet<Annotation> annotations = getAnnotations(instance, method);

        // Get annotations with interceptors
        for(Annotation annotation : annotations) {

            // Get behaviour annotation
            Behaviour behaviourAnnotation = annotation.annotationType().getDeclaredAnnotation(Behaviour.class);

            if(behaviourAnnotation != null) {

                // Get the interceptor class
                Class<? extends IInterceptor> interceptorClass = behaviourAnnotation.interceptor();

                // Instantiate interceptor
                IInterceptor interceptor = interceptorClass.newInstance();

                // Inject services into interceptor
                EJBInjector.inject(interceptor);

                interceptors.add(interceptor);
            }
        }

        return interceptors;
    }


}
