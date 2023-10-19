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
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
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
import java.util.stream.Collectors;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

import io.github.astrapi69.lang.ClassExtensions;
import io.github.astrapi69.lang.ClassType;
import io.github.astrapi69.lang.ObjectExtensions;
import lombok.NonNull;

/**
 * The class {@link ReflectionExtensions} provides utility methods for the java reflection API
 */
public final class ReflectionExtensions
{
	private static final Logger log = Logger.getLogger(ReflectionExtensions.class.getName());

	private ReflectionExtensions()
	{
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
	 * Copy the given array object and return a copy of it.
	 *
	 * @param <T>
	 *            the generic type
	 * @param source
	 *            the source
	 * @return the copy of the given array object
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] copyArray(final @NonNull T[] source)
	{
		T[] copyOfArray = (T[])copyOfArray(source);
		return copyOfArray;
	}

	/**
	 * Copy the given array object over reflection and return a copy of it
	 *
	 * @param source
	 *            the array object
	 * @return the new array object that is a copy of the given array object
	 */
	public static Object copyOfArray(Object source)
	{
		if (!source.getClass().isArray())
		{
			return null;
		}
		Object destinationArray = null;
		Class<?> arrayType = source.getClass().getComponentType();
		if (arrayType.isPrimitive())
		{
			if ("boolean".equals(arrayType.getName()))
			{
				destinationArray = Arrays.copyOf((boolean[])source, Array.getLength(source));
			}
			if ("byte".equals(arrayType.getName()))
			{
				destinationArray = Arrays.copyOf((byte[])source, Array.getLength(source));
			}
			if ("char".equals(arrayType.getName()))
			{
				destinationArray = Arrays.copyOf((char[])source, Array.getLength(source));
			}
			if ("short".equals(arrayType.getName()))
			{
				destinationArray = Arrays.copyOf((short[])source, Array.getLength(source));
			}
			if ("int".equals(arrayType.getName()))
			{
				destinationArray = Arrays.copyOf((int[])source, Array.getLength(source));
			}
			if ("long".equals(arrayType.getName()))
			{
				destinationArray = Arrays.copyOf((long[])source, Array.getLength(source));
			}
			if ("float".equals(arrayType.getName()))
			{
				destinationArray = Arrays.copyOf((float[])source, Array.getLength(source));
			}
			if ("double".equals(arrayType.getName()))
			{
				destinationArray = Arrays.copyOf((double[])source, Array.getLength(source));
			}
		}
		else
		{
			destinationArray = Array.newInstance(arrayType, Array.getLength(source));
			for (int i = 0; i < Array.getLength(source); i++)
			{
				Array.set(destinationArray, i, Array.get(source, i));
			}
		}
		return destinationArray;
	}

	/**
	 * Copy the given original object to the given destination object. This also works on private
	 * fields
	 *
	 * @param <ORIGINAL>
	 *            the generic type of the source object
	 * @param <DESTINATION>
	 *            the generic type of the target object
	 * @param source
	 *            the original object
	 * @param target
	 *            the destination object
	 * @param field
	 *            the field
	 * @return true if the field is null or final otherwise false
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 */
	public static <ORIGINAL, DESTINATION> boolean copyFieldValue(final @NonNull ORIGINAL source,
		final @NonNull DESTINATION target, final @NonNull Field field) throws IllegalAccessException
	{
		field.setAccessible(true);
		Object newValue = field.get(source);
		if (Modifier.isFinal(field.getModifiers()))
		{
			return true;
		}
		ReflectionExtensions.setFieldValue(target, field, newValue);
		return false;
	}

	/**
	 * Copies the field value of the given source object to the given target object.
	 *
	 * @param <T>
	 *            the generic type of the object
	 * @param source
	 *            the source
	 * @param target
	 *            the target
	 * @param fieldName
	 *            the field name
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method.
	 */
	public static <T> void copyFieldValue(final @NonNull T source, final @NonNull T target,
		final @NonNull String fieldName)
		throws NoSuchFieldException, SecurityException, IllegalAccessException
	{
		setFieldValue(source, target, getDeclaredField(source, fieldName));
	}

	/**
	 * Sets the field value of the given source object over the field
	 *
	 * @param <T>
	 *            the generic type
	 * @param source
	 *            the source object
	 * @param target
	 *            the target object
	 * @param sourceField
	 *            the source field
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method
	 * @throws SecurityException
	 *             is thrown if a security manager says no
	 */
	public static <T> void setFieldValue(final @NonNull T source, final @NonNull T target,
		final @NonNull Field sourceField) throws IllegalAccessException
	{
		sourceField.setAccessible(true);
		final Object sourceValue = sourceField.get(source);
		setFieldValue(target, sourceField, sourceValue);
	}

	/**
	 * Sets the sourceField value of the given target object
	 *
	 * @param <T>
	 *            the generic type
	 * @param target
	 *            the target object
	 * @param sourceField
	 *            the source field
	 * @param sourceValue
	 *            the new value to set to the target
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method
	 */
	public static <T> void setFieldValue(final T target, final Field sourceField,
		final Object sourceValue) throws IllegalAccessException
	{
		sourceField.setAccessible(true);
		Class<?> fieldType = sourceField.getType();
		ClassType classType = ObjectExtensions.getClassType(fieldType);
		switch (classType)
		{
			case ARRAY :
				sourceField.set(target, copyOfArray(sourceValue));
				break;
			case ENUM :
				sourceField.set(target, copyOfEnumValue(sourceValue, fieldType));
				break;
			default :
				sourceField.set(target, sourceValue);
				break;
		}
	}

	/**
	 * Copy the given enum object over reflection and return a copy of it
	 *
	 * @param value
	 *            the enum object
	 * @param fieldType
	 *            the type of the given field value
	 * @return the new enum object that is a copy of the given enum object
	 */
	@SuppressWarnings("unchecked")
	public static Object copyOfEnumValue(Object value, Class<?> fieldType)
	{
		ClassType classType = ObjectExtensions.getClassType(fieldType);
		if (classType.equals(ClassType.ENUM))
		{
			Enum<?> enumValue = (Enum<?>)value;
			String name = enumValue.name();
			return Enum.valueOf(fieldType.asSubclass(Enum.class), name);
		}
		return null;
	}

	/**
	 * Gets the field value of the given source object over the field name.
	 *
	 * @param <T>
	 *            the generic type
	 * @param source
	 *            the source
	 * @param fieldName
	 *            the field name
	 * @return the field value
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method.
	 */
	public static <T> Object getFieldValue(final @NonNull T source, final @NonNull String fieldName)
		throws NoSuchFieldException, SecurityException, IllegalAccessException
	{
		final Field sourceField = getDeclaredField(source, fieldName);
		sourceField.setAccessible(true);
		return sourceField.get(source);
	}

	/**
	 * Sets the field value of the given class object over the field name. This method is for set
	 * static fields from a class
	 *
	 * @param <T>
	 *            the generic type
	 * @param cls
	 *            The class
	 * @param fieldName
	 *            the field name
	 * @param newValue
	 *            the new value
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method.
	 */
	public static <T> void setFieldValue(final @NonNull Class<?> cls,
		final @NonNull String fieldName, final Object newValue)
		throws NoSuchFieldException, SecurityException, IllegalAccessException
	{
		final Field sourceField = getDeclaredField(cls, fieldName);
		sourceField.setAccessible(true);
		sourceField.set(null, newValue);
	}

	/**
	 * Gets all field names from the given class as an String list.
	 *
	 * @param cls
	 *            The class object to get the field names.
	 *
	 * @return Gets all field names from the given class as an String list.
	 */
	public static List<String> getFieldNames(final @NonNull Class<?> cls)
	{
		return Arrays.stream(cls.getDeclaredFields()).filter(ReflectionExtensions::isNotSynthetic)
			.map(Field::getName).collect(Collectors.toList());
	}

	/**
	 * Gets all field names from the given class as an String list minus the given ignored field
	 * names
	 *
	 * @param cls
	 *            The class object to get the field names
	 * @param ignoreFieldNames
	 *            a list with field names that shell be ignored
	 *
	 * @return Gets all field names from the given class as an String list minus the given ignored
	 *         field names
	 */
	public static List<String> getFieldNames(final @NonNull Class<?> cls,
		List<String> ignoreFieldNames)
	{
		return Arrays.stream(cls.getDeclaredFields()).filter(ReflectionExtensions::isNotSynthetic)
			.map(Field::getName).filter(name -> !ignoreFieldNames.contains(name))
			.collect(Collectors.toList());
	}

	/**
	 * Gets all field names from the given class as an String list minus the given optional array of
	 * ignored field names
	 *
	 * @param cls
	 *            The class object to get the field names
	 * @param ignoreFieldNames
	 *            a optional array with the field names that shell be ignored
	 *
	 * @return Gets all field names from the given class as an String list minus the given optional
	 *         array of ignored field names
	 */
	public static List<String> getFieldNames(final @NonNull Class<?> cls,
		String... ignoreFieldNames)
	{
		return getFieldNames(cls, Arrays.asList(ignoreFieldNames));
	}

	/**
	 * Gets all the declared field names from the given class object.
	 *
	 * Note: without the field names from any superclasses
	 *
	 * @param cls
	 *            the class object
	 * @return all the declared field names from the given class as an String array
	 */
	public static String[] getDeclaredFieldNames(final @NonNull Class<?> cls)
	{
		return Arrays.stream(cls.getDeclaredFields()).filter(ReflectionExtensions::isNotSynthetic)
			.map(Field::getName).toArray(String[]::new);
	}

	/**
	 * Gets all the declared field names from the given class object minus the given ignored field
	 * names
	 *
	 * Note: without the field names from any superclasses
	 *
	 * @param cls
	 *            the class object
	 * @param ignoreFieldNames
	 *            a optional array with the field names that shell be ignored
	 * @return all the declared field names from the given class as an String array minus the given
	 *         optional array of ignored field names
	 */
	public static String[] getDeclaredFieldNames(final @NonNull Class<?> cls,
		String... ignoreFieldNames)
	{
		return getDeclaredFieldNames(cls, Arrays.asList(ignoreFieldNames));
	}

	/**
	 * Gets all the declared field names from the given class object minus the given ignored field
	 * names
	 *
	 * Note: without the field names from any superclasses
	 *
	 * @param cls
	 *            the class object
	 * @param ignoreFieldNames
	 *            a list with field names that shell be ignored
	 * @return all the declared field names from the given class as an String array minus the given
	 *         ignored field names
	 */
	public static String[] getDeclaredFieldNames(final @NonNull Class<?> cls,
		List<String> ignoreFieldNames)
	{
		return Arrays.stream(cls.getDeclaredFields()).filter(ReflectionExtensions::isNotSynthetic)
			.map(Field::getName).filter(name -> !ignoreFieldNames.contains(name))
			.toArray(String[]::new);
	}

	/**
	 * Checks if the given {@link Field} is not synthetic
	 *
	 * @param field
	 *            the field
	 * @return true, if the given {@link Field} is not synthetic otherwise false
	 */
	public static boolean isNotSynthetic(@NonNull Field field)
	{
		return !field.isSynthetic();
	}

	/**
	 * Gets all method names from the given class as an String array.
	 *
	 * @param cls
	 *            The class object to get the method names.
	 *
	 * @return Gets all method names from the given class as an String array.
	 */
	public static String[] getMethodNames(final @NonNull Class<?> cls)
	{
		final Method[] methods = cls.getDeclaredMethods();
		final String[] methodNames = new String[methods.length];
		for (int i = 0; i < methods.length; i++)
		{
			methodNames[i] = methods[i].getName();
		}
		return methodNames;
	}

	/**
	 * Generates a Map with the fieldName as key and the method as value. Concatenates the given
	 * prefix and the field name and puts the result into the map.
	 *
	 * @param fieldNames
	 *            A list with the field names.
	 * @param prefix
	 *            The prefix for the method name.
	 *
	 * @return the method names with prefix from field names
	 */
	public static Map<String, String> getMethodNamesWithPrefixFromFieldNames(
		final @NonNull List<String> fieldNames, final String prefix)
	{
		final Map<String, String> fieldNameMethodMapper = new HashMap<>();
		for (final String fieldName : fieldNames)
		{
			final String firstCharacterToUpperCasefieldName = firstCharacterToUpperCase(fieldName);
			final String methodName = prefix + firstCharacterToUpperCasefieldName;
			fieldNameMethodMapper.put(fieldName, methodName);
		}
		return fieldNameMethodMapper;
	}

	/**
	 * Sets the first character from the given string to upper case and returns it. Example:<br>
	 * Given fieldName: userName <br>
	 * Result: UserName
	 *
	 * @param fieldName
	 *            The String to modify.
	 * @return The modified string.
	 */
	public static String firstCharacterToUpperCase(final @NonNull String fieldName)
	{
		String firstCharacter = fieldName.substring(0, 1);
		firstCharacter = firstCharacter.toUpperCase();
		final char[] fc = firstCharacter.toCharArray();
		final char[] fn = fieldName.toCharArray();
		fn[0] = fc[0];
		return new String(fn);
	}

	/**
	 * Gets the modifiers from the given Field as a list of String objects.
	 *
	 * @param field
	 *            The field to get the modifiers.
	 * @return A list with the modifiers as String objects from the given Field.
	 */
	public static List<String> getModifiers(final @NonNull Field field)
	{
		final String modifiers = Modifier.toString(field.getModifiers());
		final String[] modifiersArray = modifiers.split(" ");
		return Arrays.asList(modifiersArray);
	}

	/**
	 * Creates a new instance from the same type as the given object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @return the new instance
	 */
	@SuppressWarnings("unchecked")
	public static <T> Optional<T> newInstance(final @NonNull T object)
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
				return newInstance((Class<T>)object.getClass());
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
	 * @return the new instance
	 */
	public static <T> Optional<T> newInstance(final @NonNull Class<T> clazz)
	{
		Optional<T> newInstance = Optional.empty();
		Optional<T> optionalNewInstance;
		optionalNewInstance = forceNewInstanceWithClass(clazz);
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

	/**
	 * Factory method for create a new instance from the given {@link String} object that represents
	 * the fully qualified name of the class that have to be instantiated. <br>
	 *
	 * @param <T>
	 *            the generic type
	 * @param fullyQualifiedClassName
	 *            The fully qualified name of the class
	 * @return an {@link Optional} object that contains the new instance or is empty if the attempt
	 *         to instantiate failed
	 */
	@SuppressWarnings("unchecked")
	public static <T> Optional<T> newInstance(final @NonNull String fullyQualifiedClassName)
	{
		try
		{
			Class<T> aClass = (Class<T>)ClassExtensions.forName(fullyQualifiedClassName);
			return newInstance(aClass);
		}
		catch (ClassNotFoundException e)
		{
			return Optional.empty();
		}
	}

	private static <T> Optional<T> forceNewInstanceWithClass(final @NonNull Class<T> clazz)
	{

		Optional<T> optionalNewInstance = Optional.empty();
		try
		{
			optionalNewInstance = Optional.of(newInstanceWithClass(clazz));
		}
		catch (Exception | Error e)
		{
			log.log(Level.INFO, "Failed to create new instance with method Class.newInstance()", e);
		}
		return optionalNewInstance;
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
	 * @throws IllegalAccessException
	 *             is thrown if the class or its default constructor is not accessible
	 * @throws InstantiationException
	 *             is thrown if this {@code Class} represents an abstract class, an interface, an
	 *             array class, a primitive type, or void; or if the class has no default
	 *             constructor; or if the instantiation fails for some other reason
	 * @throws NoSuchMethodException
	 *             is thrown if a matching method is not found
	 * @throws InvocationTargetException
	 *             is thrown if the underlying constructor throws an exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstanceWithClass(final @NonNull Class<T> clazz)
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
				return clazz.getDeclaredConstructor().newInstance();
		}
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

	/**
	 * Gets the {@link Field} that match to the given field name that exists in the given object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @param fieldName
	 *            the field name
	 * @return the declared field
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 */
	public static <T> Field getDeclaredField(@NonNull final T object,
		final @NonNull String fieldName) throws NoSuchFieldException, SecurityException
	{
		return getDeclaredField(object.getClass(), fieldName);
	}

	/**
	 * Gets the {@link Field} that match to the given field name that exists in the given class.
	 *
	 * @param cls
	 *            the class object
	 * @param fieldName
	 *            the field name
	 * @return the declared field
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 */
	public static Field getDeclaredField(final @NonNull Class<?> cls,
		final @NonNull String fieldName) throws NoSuchFieldException, SecurityException
	{
		return cls.getDeclaredField(fieldName);
	}

	/**
	 * Gets all the declared fields including all fields from all super classes from the given class
	 * object minus the given ignored fields
	 *
	 * @param cls
	 *            the class object
	 * @param ignoreFieldNames
	 *            an optional array with field names that shell be ignored
	 * @return all the declared fields minus the given ignored field names
	 */
	public static Field[] getAllDeclaredFields(final @NonNull Class<?> cls,
		final String... ignoreFieldNames)
	{
		return getAllDeclaredFields(cls, Arrays.asList(ignoreFieldNames));
	}

	/**
	 * Gets the default field names that can be always ignored
	 * 
	 * @return the default field names that can be always ignored
	 */
	public static String[] getDefaultIgnoreFieldNames()
	{
		return new String[] { "serialVersionUID", "$jacocoData" };
	}

	/**
	 * Gets all the declared fields including all fields from all super classes from the given class
	 * object minus the given ignored fields
	 *
	 * @param cls
	 *            the class object
	 * @param ignoreFieldNames
	 *            a list with field names that shell be ignored
	 * @return all the declared fields minus the given ignored field names
	 */
	public static Field[] getAllDeclaredFields(final @NonNull Class<?> cls,
		List<String> ignoreFieldNames)
	{
		Field[] declaredFields = getDeclaredFields(cls, ignoreFieldNames);
		Class<?> superClass = cls.getSuperclass();
		if (superClass != null && superClass.equals(Object.class))
		{
			return declaredFields;
		}
		List<Field> fields = new ArrayList<>(Arrays.asList(declaredFields));
		while (superClass != null
			&& !(superClass.getSuperclass() != null && superClass.equals(Object.class)))
		{
			fields.addAll(Arrays.asList(getDeclaredFields(superClass, ignoreFieldNames)));
			superClass = superClass.getSuperclass();
		}
		return fields.toArray(newArrayInstance(Field.class, fields.size()));
	}

	/**
	 * Gets all the declared field names including all fields from all super classes from the given
	 * class object
	 *
	 * @param cls
	 *            the class object
	 * @return all the declared field names
	 */
	public static String[] getAllDeclaredFieldNames(final @NonNull Class<?> cls)
	{
		return Arrays.stream(getAllDeclaredFields(cls)).map(Field::getName).toArray(String[]::new);
	}

	/**
	 * Gets all the declared field names including all fields from all super classes from the given
	 * class object minus the given optional array of ignored field names
	 *
	 * @param cls
	 *            the class object
	 * @param ignoreFieldNames
	 *            an optional array with the field names that shell be ignored
	 * @return all the declared field names minus the given optional array of ignored field names
	 */
	public static String[] getAllDeclaredFieldNames(final @NonNull Class<?> cls,
		String... ignoreFieldNames)
	{
		return getAllDeclaredFieldNames(cls, Arrays.asList(ignoreFieldNames));
	}

	/**
	 * Gets all the declared field names including all fields from all super classes from the given
	 * class object minus the given ignored field names
	 *
	 * @param cls
	 *            the class object
	 * @param ignoreFieldNames
	 *            a list with field names that shell be ignored
	 * @return all the declared field names minus the given ignored field names
	 */
	public static String[] getAllDeclaredFieldNames(final @NonNull Class<?> cls,
		List<String> ignoreFieldNames)
	{
		Field[] allDeclaredFields = getAllDeclaredFields(cls, ignoreFieldNames);
		return Arrays.stream(allDeclaredFields).map(Field::getName)
			.filter(name -> !ignoreFieldNames.contains(name)).toArray(String[]::new);
	}

	/**
	 * Gets the declared fields from the given class minus the given ignored field names
	 *
	 * @param cls
	 *            the class object
	 * @param ignoreFieldNames
	 *            a list with field names that shell be ignored
	 * @return the declared {@link Field} from the given class minus the given ignored field names
	 * @throws SecurityException
	 *             is thrown if a security manager says no
	 */
	public static Field[] getDeclaredFields(final @NonNull Class<?> cls,
		List<String> ignoreFieldNames) throws SecurityException
	{
		Field[] declaredFields = cls.getDeclaredFields();
		return Arrays.stream(declaredFields)
			.filter(field -> !ignoreFieldNames.contains(field.getName())).toArray(Field[]::new);
	}

	/**
	 * Gets the declared fields from the given class minus the given optional array of ignored field
	 * names
	 *
	 * @param cls
	 *            the class object
	 * @param ignoreFieldNames
	 *            a list with field names that shell be ignored
	 * @return the declared {@link Field} from the given class minus the given optional array of
	 *         ignored field names
	 * @throws SecurityException
	 *             is thrown if a security manager says no
	 */
	public static Field[] getDeclaredFields(final @NonNull Class<?> cls, String... ignoreFieldNames)
		throws SecurityException
	{
		return getDeclaredFields(cls, Arrays.asList(ignoreFieldNames));
	}

}
