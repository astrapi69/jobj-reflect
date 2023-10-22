/**
 * The MIT License
 *
 * Copyright (C) 2022 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

import io.github.astrapi69.lang.ClassExtensions;
import io.github.astrapi69.lang.ClassType;
import lombok.NonNull;

/**
 * The class {@link InstanceFactory} provides utility methods for create new instances
 */
public final class InstanceFactory
{

	private static final Logger log = Logger.getLogger(InstanceFactory.class.getName());

	/**
	 * Factory method for create a new instance from the given {@link String} object that represents
	 * the fully qualified name of the class that have to be instantiated. <br>
	 *
	 * @param <T>
	 *            the generic type
	 * @param fullyQualifiedClassName
	 *            The fully qualified name of the class
	 * @param initArgs
	 *            an optional array of objects to be passed as arguments to the constructor call
	 * @return an {@link Optional} object that contains the new instance or is empty if the attempt
	 *         to instantiate failed
	 */
	@SuppressWarnings("unchecked")
	public static <T> Optional<T> newInstance(final @NonNull String fullyQualifiedClassName,
		Object... initArgs)
	{
		try
		{
			Class<T> aClass = (Class<T>)ClassExtensions.forName(fullyQualifiedClassName);
			return newInstance(aClass, initArgs);
		}
		catch (ClassNotFoundException e)
		{
			return Optional.empty();
		}
	}

	/**
	 * Creates a new array instance from the same type as the given {@link Class} and the given
	 * length
	 *
	 * @param <T>
	 *            the generic type
	 * @param cls
	 *            the component type class object
	 * @param length
	 *            the length of the array
	 * @return the new array instance
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] newArrayInstance(final @NonNull Class<T> cls, final int length)
	{
		return (T[])Array.newInstance(cls, length);
	}

	/**
	 * Creates a new array instance of the given array {@link Class} and the given length
	 *
	 * @param cls
	 *            the array class object
	 * @param length
	 *            the length of the array
	 * @return the new array instance of the given array {@link Class} and the given length
	 */
	public static Object newArray(final @NonNull Class<?> cls, final int length)
	{
		if (!cls.isArray())
		{
			return null;
		}
		Object destinationArray = null;
		Class<?> arrayType = cls.getComponentType();
		if (arrayType.isPrimitive())
		{
			if ("boolean".equals(arrayType.getName()))
			{
				destinationArray = new boolean[length];
			}
			if ("byte".equals(arrayType.getName()))
			{
				destinationArray = new byte[length];
			}
			if ("char".equals(arrayType.getName()))
			{
				destinationArray = new char[length];
			}
			if ("short".equals(arrayType.getName()))
			{
				destinationArray = new short[length];
			}
			if ("int".equals(arrayType.getName()))
			{
				destinationArray = new int[length];
			}
			if ("long".equals(arrayType.getName()))
			{
				destinationArray = new long[length];
			}
			if ("float".equals(arrayType.getName()))
			{
				destinationArray = new float[length];
			}
			if ("double".equals(arrayType.getName()))
			{
				destinationArray = new double[length];
			}
		}
		else
		{
			destinationArray = Array.newInstance(cls, length);
		}
		return destinationArray;
	}

	/**
	 * Creates a new empty array instance from the given source array the length of the given source
	 * array
	 *
	 * @param <T>
	 *            the generic type
	 * @param source
	 *            the source array
	 * @return the new empty array instance
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] newEmptyArrayInstance(final @NonNull T[] source)
	{
		return (T[])newArrayInstance(source.getClass().getComponentType(), 0);
	}

	/**
	 * Creates a new instance from the same type as the given object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @param initArgs
	 *            an optional array of objects to be passed as arguments to the constructor call
	 * @return the new instance
	 */
	@SuppressWarnings("unchecked")
	public static <T> Optional<T> newGenericInstance(final @NonNull T object, Object... initArgs)
	{
		Class<?> clazz = object.getClass();
		ClassType classType = ClassExtensions.getClassType(clazz);
		switch (classType)
		{
			case MAP :
				if (clazz.equals(LinkedHashMap.class))
				{
					return Optional.of((T)new LinkedHashMap<>());
				}
				if (clazz.equals(TreeMap.class))
				{
					return Optional.of((T)new TreeMap<>());
				}
				return Optional.of((T)new HashMap<>());
			case COLLECTION :
				if (clazz.equals(HashSet.class))
				{
					return Optional.of((T)new HashSet<>());
				}
				if (clazz.equals(LinkedHashSet.class))
				{
					return Optional.of((T)new LinkedHashSet<>());
				}
				if (clazz.equals(LinkedList.class))
				{
					return Optional.of((T)new LinkedList<>());
				}
				return Optional.of((T)new ArrayList<>());
			case ARRAY :
				int length = Array.getLength(object);
				return Optional.of((T)Array.newInstance(clazz.getComponentType(), length));
			default :
				return newInstance((Class<T>)object.getClass(), initArgs);
		}
	}

	/**
	 * Factory method for create a new instance from the same type as the given {@link Class}. First
	 * try is over the class and second try is with objenesis. <br>
	 * <br>
	 * Note: if non of the tries no instance could created null will be returned.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the Class object
	 * @param initArgs
	 *            an optional array of objects to be passed as arguments to the constructor call
	 * @return the new instance
	 */
	public static <T> Optional<T> newInstance(final @NonNull Class<T> clazz, Object... initArgs)
	{
		Optional<T> newInstance = Optional.empty();
		Optional<T> optionalNewInstance;
		optionalNewInstance = forceNewInstanceWithClass(clazz, initArgs);
		if (optionalNewInstance.isPresent())
		{
			return optionalNewInstance;
		}
		optionalNewInstance = forceNewInstanceWithObjenesis(clazz);
		if (optionalNewInstance.isPresent())
		{
			return optionalNewInstance;
		}
		return newInstance;
	}

	private static <T> Optional<T> forceNewInstanceWithObjenesis(final @NonNull Class<T> clazz)
	{

		Optional<T> optionalNewInstance = Optional.empty();
		try
		{
			optionalNewInstance = Optional.of(newInstanceWithObjenesis(clazz));
		}
		catch (Exception | Error e)
		{
			log.log(Level.INFO,
				"Failed to create new instance with Objenesis ObjectInstantiator.newInstance()", e);
		}
		return optionalNewInstance;
	}

	/**
	 * Creates a new instance from the same type as the given {@link Class}
	 *
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the Class object
	 * @return the new instance
	 */
	public static <T> T newInstanceWithObjenesis(final @NonNull Class<T> clazz)
	{
		Objenesis objenesis = new ObjenesisStd();
		ObjectInstantiator<T> instantiator = objenesis.getInstantiatorOf(clazz);
		return instantiator.newInstance();
	}

	private static <T> Optional<T> forceNewInstanceWithClass(final @NonNull Class<T> clazz,
		Object... initArgs)
	{

		Optional<T> optionalNewInstance = Optional.empty();
		try
		{
			optionalNewInstance = Optional.of(newInstanceWithClass(clazz, initArgs));
		}
		catch (Exception | Error e)
		{
			log.log(Level.INFO, "Failed to create new instance with method Class.newInstance()", e);
		}
		return optionalNewInstance;
	}

	/**
	 * Creates a new instance from the same type as the given {@link Class}
	 *
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the Class object
	 * @param initArgs
	 *            an optional array of objects to be passed as arguments to the constructor call
	 * @return the new instance
	 * @throws InstantiationException
	 *             is thrown if this {@code Class} represents an abstract class, an interface, an
	 *             array class, a primitive type, or void; or if the class has no default
	 *             constructor; or if the instantiation fails for some other reason
	 * @throws IllegalAccessException
	 *             is thrown if the class or its default constructor is not accessible
	 * @throws NoSuchMethodException
	 *             is thrown if a matching method is not found
	 * @throws InvocationTargetException
	 *             is thrown if the underlying constructor throws an exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstanceWithClass(final @NonNull Class<T> clazz, Object... initArgs)
		throws InstantiationException, IllegalAccessException, NoSuchMethodException,
		InvocationTargetException
	{
		ClassType classType = ClassExtensions.getClassType(clazz);
		switch (classType)
		{
			case MAP :
				if (clazz.equals(Map.class))
				{
					return (T)new HashMap<>();
				}
			case COLLECTION :
				if (clazz.equals(Set.class))
				{
					return (T)new HashSet<>();
				}
				if (clazz.equals(List.class))
				{
					return (T)new ArrayList<>();
				}
				if (clazz.equals(Queue.class))
				{
					return (T)new LinkedList<>();
				}
			case ARRAY :
				int length = 3;
				return (T)Array.newInstance(clazz.getComponentType(), length);
			case ANNOTATION :
			case ANONYMOUS :
			case DEFAULT :
			case ENUM :
			case INTERFACE :
			case LOCAL :
			case MEMBER :
			case PRIMITIVE :
			case SYNTHETIC :
			default :
				return clazz.getDeclaredConstructor(getParameterTypes(initArgs))
					.newInstance(initArgs);
		}
	}

	/**
	 * Get an {@link Class} object array of the given {@link Object} array
	 *
	 * @param parameterTypes
	 *            the parameter types
	 * @return the {@link Class} object array
	 */
	public static Class<?>[] getParameterTypes(Object... parameterTypes)
	{
		if (parameterTypes.length == 0)
		{
			return new Class<?>[] { };
		}
		Class<?>[] parameterTypeClasses = new Class[parameterTypes.length];
		for (int i = 0; i < parameterTypes.length; i++)
		{
			parameterTypeClasses[i] = parameterTypes[i].getClass();
		}
		return parameterTypeClasses;
	}
}
