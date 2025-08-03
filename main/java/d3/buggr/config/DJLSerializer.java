package d3.buggr.config;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class DJLSerializer<T> implements ObjectSerializer<T> {

    private Class<T> clazz;

    public DJLSerializer(Class<T> clazz){
        this.clazz = clazz;
    }

    @Override
    public boolean supports(Class<? super T> type) {
        return clazz.isAssignableFrom(type);
    }

    @Override
    public void serialize(T object, SerializationData data, GenericsDeclaration generics) {

        try {
            Class objectClass = object.getClass();
            Field[] fields = objectClass.getDeclaredFields();
            for (Field field : fields) {
                String name = field.getName();
                Object value = field.get(object);

                data.add(name, value);
            }
        } catch (IllegalAccessException e) {
            System.out.println("Cannot serialze object.");
        }

    }

    @Override
    public T deserialize(DeserializationData data, GenericsDeclaration generics) {

        try {

            @SuppressWarnings("unchecked")
            Class<T> type = (Class<T>) generics.getType();

            Constructor<?>[] constructors = type.getDeclaredConstructors();
            Constructor<?> getConstructor = null;

            for (Constructor<?> constructor : constructors) {
                if (constructor.getParameterCount() > 0) {
                    getConstructor = constructor;
                    break;
                }
            }

            if (getConstructor == null) {
                throw new RuntimeException("No suitable constructor found.");
            }

            Class<?>[] parameterType = getConstructor.getParameterTypes();
            Object[] parameters = new Object[parameterType.length];

            for (int i = 0; i < parameterType.length; i++) {
                String name = parameterType[i].getSimpleName().toLowerCase();
                Object value = data.get(name, parameterType[i]);
                parameters[i] = value;
            }

            T instance = (T) getConstructor.newInstance(parameters);

            Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                Object value = data.get(name, field.getType());
                field.set(instance, value);
            }

            return instance;

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("ERROR WHILE TRYING TO DESERIALIZE OBJECT");
        }

        return null;
    }

    public Class<T> getClazz() {
        return this.clazz;
    }
}
