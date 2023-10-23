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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import io.github.astrapi69.collection.array.ArrayFactory;
import io.github.astrapi69.collection.list.ListFactory;
import io.github.astrapi69.test.object.Member;
import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.PremiumMember;
import io.github.astrapi69.test.object.PrimitiveArrays;
import io.github.astrapi69.test.object.enumtype.Gender;

/**
 * The unit test class for the class {@link ReflectionExtensions}
 */
public class ReflectionExtensionsTest
{

	/**
	 * Test method for {@link ReflectionExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ReflectionExtensions.class);
	}

	/**
	 * Test method for {@link ReflectionExtensions#copyOfArray(Object)}
	 */
	@Test
	public void testCopyOfArray()
	{
		Object expected;
		Object actual;

		expected = new Person();
		actual = ReflectionExtensions.copyOfArray(expected);
		assertNull(actual);
		// new scenario ...
		expected = new boolean[] { false, true };
		actual = ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals((boolean[])expected, (boolean[])actual);
		// new scenario ...
		expected = new byte[] { 1, 2 };
		actual = ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals((byte[])expected, (byte[])actual);
		// new scenario ...
		expected = new char[] { 1, 2 };
		actual = ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals((char[])expected, (char[])actual);
		// new scenario ...
		expected = new short[] { 1, 2 };
		actual = ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals((short[])expected, (short[])actual);
		// new scenario ...
		expected = new int[] { 1, 2 };
		actual = ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals((int[])expected, (int[])actual);
		// new scenario ...
		expected = new long[] { 1, 2 };
		actual = ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals((long[])expected, (long[])actual);
		// new scenario ...
		expected = new float[] { 1.0f, 2.0f };
		actual = ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals((float[])expected, (float[])actual, 0);
		// new scenario ...
		expected = new double[] { 1.0d, 2.0d };
		actual = ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals((double[])expected, (double[])actual, 0);

		expected = new Double[] { 1.0d, 2.0d };
		actual = ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals((Double[])expected, (Double[])actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#copyOfEnumValue(Object, Class)}
	 */
	@Test
	public void testCopyOfEnumValue()
	{
		Object actual;
		Object expected;
		// test new scenario ...
		actual = ReflectionExtensions.copyOfEnumValue(Gender.FEMALE, Gender.class);
		expected = Gender.FEMALE;
		assertEquals(actual, expected);
		// test new scenario ...
		actual = ReflectionExtensions.copyOfEnumValue(Person.builder().build(), Person.class);
		expected = null;
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link ReflectionExtensions#copyArray(Object[])}
	 */
	@Test
	public void testCopyArray()
	{
		Integer[] actual;
		Integer[] expected;
		expected = ArrayFactory.newArray(1, 2, 3);
		actual = ReflectionExtensions.copyArray(expected);
		assertTrue(Arrays.deepEquals(actual, expected));
	}

	/**
	 * Test method for {@link ReflectionExtensions#copyOfArray(Object)}
	 */
	@Test
	public void testCopyBooleanArray()
	{
		boolean[] actual;
		boolean[] expected;
		expected = ArrayFactory.newBooleanArray(false, true, true);
		actual = (boolean[])ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals(actual, expected);
	}


	/**
	 * Test method for {@link ReflectionExtensions#copyFieldValue(Object, Object, String)}.
	 *
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method.
	 */
	@Test
	public void testCopyFieldValueWithFieldName()
		throws NoSuchFieldException, SecurityException, IllegalAccessException
	{
		String expected;
		String actual;
		final Person alex = Person.builder().name("Alex").build();
		final Person nik = Person.builder().name("Nik").build();
		expected = "Alex";
		ReflectionExtensions.copyFieldValue(alex, nik, "name");
		actual = nik.getName();
		assertEquals(expected, actual);
	}


	/**
	 * Test method for {@link ReflectionExtensions#copyFieldValue(Object, Object, Field)}
	 * 
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method.
	 */
	@Test
	public void testCopyFieldValueWithField()
		throws NoSuchFieldException, SecurityException, IllegalAccessException
	{
		String expected;
		String actual;
		Person person;
		Person anotherPerson;
		Field declaredField;
		// new scenario ...
		expected = "Alex";
		person = Person.builder().name(expected).build();
		anotherPerson = Person.builder().name("Nik").build();
		declaredField = ReflectionExtensions.getDeclaredField(person, "name");
		ReflectionExtensions.copyFieldValue(person, anotherPerson, declaredField);
		actual = anotherPerson.getName();
		assertEquals(expected, actual);
		// new scenario ...
		expected = null;
		person = Person.builder().name(expected).build();
		anotherPerson = Person.builder().name("Nik").build();
		declaredField = ReflectionExtensions.getDeclaredField(person, "name");
		ReflectionExtensions.copyFieldValue(person, anotherPerson, declaredField);
		actual = anotherPerson.getName();
		assertEquals(expected, actual);
		// new scenario with final field ...
		Licht wohnZimmer;
		Licht kueche;

		wohnZimmer = Licht.builder().an(true).build();
		kueche = Licht.builder().build();
		declaredField = ReflectionExtensions.getDeclaredField(wohnZimmer, "an");
		ReflectionExtensions.copyFieldValue(wohnZimmer, kueche, declaredField);
		assertEquals(kueche.isAn(), false);
	}

	/**
	 * Test method for {@link ReflectionExtensions#firstCharacterToUpperCase(String)}.
	 */
	@Test
	public void testFirstCharacterToUpperCase()
	{
		String expected;
		String actual;
		actual = ReflectionExtensions.firstCharacterToUpperCase("name");

		expected = "Name";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getAllDeclaredFieldNames(Class)}
	 */
	@Test
	public void testGetAllDeclaredFieldNamesWithListOfIgnoreFields()
	{
		int expected;
		int actual;
		String[] allDeclaredFieldnames;

		allDeclaredFieldnames = ReflectionExtensions.getAllDeclaredFieldNames(Person.class);
		expected = 6;
		actual = allDeclaredFieldnames.length;
		assertEquals(expected, actual);

		allDeclaredFieldnames = ReflectionExtensions.getAllDeclaredFieldNames(Member.class);
		expected = 9;
		actual = allDeclaredFieldnames.length;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getAllDeclaredFieldNames(Class, String...)}
	 */
	@Test
	public void testGetAllDeclaredFieldNames()
	{
		int expected;
		int actual;
		String[] allDeclaredFieldnames;

		allDeclaredFieldnames = ReflectionExtensions.getAllDeclaredFieldNames(Person.class,
			ReflectionExtensions.getDefaultIgnoreFieldNames());
		expected = 5;
		actual = allDeclaredFieldnames.length;
		assertEquals(expected, actual);

		allDeclaredFieldnames = ReflectionExtensions.getAllDeclaredFieldNames(Member.class,
			ReflectionExtensions.getDefaultIgnoreFieldNames());
		expected = 7;
		actual = allDeclaredFieldnames.length;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getAllDeclaredFieldNames(Class, List)}
	 */
	@Test
	public void testGetAllDeclaredFieldNamesWithIgnoreFields()
	{
		int expected;
		int actual;
		String[] allDeclaredFieldnames;

		allDeclaredFieldnames = ReflectionExtensions.getAllDeclaredFieldNames(Person.class,
			ListFactory.newArrayList("serialVersionUID", "name"));
		expected = 4;
		actual = allDeclaredFieldnames.length;
		assertEquals(expected, actual);

		allDeclaredFieldnames = ReflectionExtensions.getAllDeclaredFieldNames(Member.class,
			ListFactory.newArrayList("dateofbirth", "name"));
		expected = 7;
		actual = allDeclaredFieldnames.length;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getAllDeclaredFieldNames(Class, String...)}
	 */
	@Test
	public void testGetAllDeclaredFieldNamesWithVarargs()
	{
		int expected;
		int actual;
		String[] allDeclaredFieldnames;

		allDeclaredFieldnames = ReflectionExtensions.getAllDeclaredFieldNames(Person.class,
			"serialVersionUID", "name", "$jacocoData");
		expected = 4;
		actual = allDeclaredFieldnames.length;
		assertEquals(expected, actual);

		allDeclaredFieldnames = ReflectionExtensions.getAllDeclaredFieldNames(Member.class,
			"dateofbirth", "name", "$jacocoData");
		expected = 7;
		actual = allDeclaredFieldnames.length;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getAllDeclaredFields(Class, String...)}
	 */
	@Test
	public void testGetAllDeclaredFields()
	{
		int expected;
		int actual;
		Field[] allDeclaredFields;

		allDeclaredFields = ReflectionExtensions.getAllDeclaredFields(Person.class,
			ReflectionExtensions.getDefaultIgnoreFieldNames());
		expected = 5;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);

		allDeclaredFields = ReflectionExtensions.getAllDeclaredFields(Member.class,
			ReflectionExtensions.getDefaultIgnoreFieldNames());
		expected = 7;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);

		allDeclaredFields = ReflectionExtensions.getAllDeclaredFields(PremiumMember.class,
			ReflectionExtensions.getDefaultIgnoreFieldNames());
		expected = 8;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getDefaultIgnoreFieldNames()}
	 */
	@Test
	public void testGetDefaultIgnoreFieldNames()
	{
		String[] expected;
		String[] actual;
		actual = ReflectionExtensions.getDefaultIgnoreFieldNames();
		expected = new String[] { "serialVersionUID", "$jacocoData" };
		assertArrayEquals(actual, expected);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getDeclaredField(Class, String)}.
	 *
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 */
	@Test
	public void testGetDeclaredFieldClassOfQString() throws NoSuchFieldException, SecurityException
	{
		String expected;
		String actual;

		Field declaredField = ReflectionExtensions.getDeclaredField(Person.class, "name");
		assertNotNull(declaredField);
		expected = "name";
		actual = declaredField.getName();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getDeclaredFieldNames(Class)}
	 */
	@Test
	public void testGetDeclaredFieldNames()
	{
		String[] declaredFieldNames = ReflectionExtensions.getDeclaredFieldNames(Person.class);
		List<String> fieldNames = Arrays.asList(declaredFieldNames);
		assertNotNull(fieldNames);

		assertTrue(fieldNames.contains("serialVersionUID"));
		assertTrue(fieldNames.contains("name"));
		assertTrue(fieldNames.contains("nickname"));
		assertTrue(fieldNames.contains("gender"));
		assertTrue(fieldNames.contains("about"));
		assertTrue(fieldNames.contains("married"));
	}

	/**
	 * Test method for {@link ReflectionExtensions#getDeclaredFieldNames(Class, List)}
	 */
	@Test
	public void testGetDeclaredFieldNamesWithIgnoreFields()
	{
		String[] declaredFieldNames = ReflectionExtensions.getDeclaredFieldNames(Person.class,
			ListFactory.newArrayList("serialVersionUID", "name"));
		List<String> fieldNames = Arrays.asList(declaredFieldNames);
		assertNotNull(fieldNames);

		assertTrue(fieldNames.contains("nickname"));
		assertTrue(fieldNames.contains("gender"));
		assertTrue(fieldNames.contains("about"));
		assertTrue(fieldNames.contains("married"));
	}

	/**
	 * Test method for {@link ReflectionExtensions#getDeclaredFieldNames(Class, List)}
	 */
	@Test
	public void testGetDeclaredFieldNamesWithVarargs()
	{
		String[] declaredFieldNames = ReflectionExtensions.getDeclaredFieldNames(Person.class,
			"serialVersionUID", "name");
		List<String> fieldNames = Arrays.asList(declaredFieldNames);
		assertNotNull(fieldNames);

		assertTrue(fieldNames.contains("nickname"));
		assertTrue(fieldNames.contains("gender"));
		assertTrue(fieldNames.contains("about"));
		assertTrue(fieldNames.contains("married"));
	}

	/**
	 * Test method for {@link ReflectionExtensions#getDeclaredFields(Class, List)}
	 */
	@Test
	public void testGetDeclaredFieldsWithIgnoreFields()
	{
		int expected;
		int actual;
		Field[] allDeclaredFields;

		allDeclaredFields = ReflectionExtensions.getDeclaredFields(Person.class,
			ListFactory.newArrayList("serialVersionUID"));
		expected = 5;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);

		allDeclaredFields = ReflectionExtensions.getDeclaredFields(Person.class,
			ListFactory.newArrayList("serialVersionUID", "married"));
		expected = 4;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);

		allDeclaredFields = ReflectionExtensions.getDeclaredFields(Member.class,
			ListFactory.newArrayList("serialVersionUID", "$jacocoData"));
		expected = 2;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);

		allDeclaredFields = ReflectionExtensions.getDeclaredFields(Member.class,
			ListFactory.newArrayList("serialVersionUID", "dateofMarriage", "$jacocoData"));
		expected = 1;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getDeclaredFields(Class, String...)}
	 */
	@Test
	public void testGetDeclaredFieldsWithVarargs()
	{
		int expected;
		int actual;
		Field[] allDeclaredFields;

		allDeclaredFields = ReflectionExtensions.getDeclaredFields(Person.class, "serialVersionUID",
			"$jacocoData");
		expected = 5;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);

		allDeclaredFields = ReflectionExtensions.getDeclaredFields(Person.class, "serialVersionUID",
			"married", "$jacocoData");
		expected = 4;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);

		allDeclaredFields = ReflectionExtensions.getDeclaredFields(Member.class, "serialVersionUID",
			"$jacocoData");
		expected = 2;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);

		allDeclaredFields = ReflectionExtensions.getDeclaredFields(Member.class, "serialVersionUID",
			"dateofMarriage", "$jacocoData");
		expected = 1;
		actual = allDeclaredFields.length;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getDeclaredField(Object, String)}.
	 *
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 */
	@Test
	public void testGetDeclaredFieldTString() throws NoSuchFieldException, SecurityException
	{
		String expected;
		String actual;

		final Person alex = Person.builder().name("Alex").build();
		Field declaredField = ReflectionExtensions.getDeclaredField(alex, "name");
		assertNotNull(declaredField);
		expected = "name";
		actual = declaredField.getName();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getFieldNames(Class)}
	 */
	@Test
	public void testGetFieldNames()
	{
		List<String> fieldNames = ReflectionExtensions.getFieldNames(Person.class);
		assertNotNull(fieldNames);

		assertTrue(fieldNames.contains("serialVersionUID"));
		assertTrue(fieldNames.contains("name"));
		assertTrue(fieldNames.contains("nickname"));
		assertTrue(fieldNames.contains("gender"));
		assertTrue(fieldNames.contains("about"));
		assertTrue(fieldNames.contains("married"));
	}


	/**
	 * Test method for {@link ReflectionExtensions#getFieldNames(Class, List)}
	 */
	@Test
	public void testGetFieldNamesIgnore()
	{
		List<String> fieldNames = ReflectionExtensions.getFieldNames(Person.class,
			ListFactory.newArrayList("serialVersionUID"));
		assertNotNull(fieldNames);

		assertTrue(fieldNames.contains("name"));
		assertTrue(fieldNames.contains("nickname"));
		assertTrue(fieldNames.contains("gender"));
		assertTrue(fieldNames.contains("about"));
		assertTrue(fieldNames.contains("married"));
	}

	/**
	 * Test method for {@link ReflectionExtensions#getFieldNames(Class, String...)}
	 */
	@Test
	public void testGetFieldNamesIgnoreVarargs()
	{
		List<String> fieldNames = ReflectionExtensions.getFieldNames(Person.class,
			"serialVersionUID");
		assertNotNull(fieldNames);

		assertTrue(fieldNames.contains("name"));
		assertTrue(fieldNames.contains("nickname"));
		assertTrue(fieldNames.contains("gender"));
		assertTrue(fieldNames.contains("about"));
		assertTrue(fieldNames.contains("married"));
	}

	/**
	 * Test method for {@link ReflectionExtensions#getFieldValue(Object, String)}
	 *
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method.
	 */
	@Test
	public void testGetFieldValueObject()
		throws NoSuchFieldException, SecurityException, IllegalAccessException
	{
		String expected;
		String actual;
		final Person person = Person.builder().name("Alex").build();
		expected = "Alex";
		actual = (String)ReflectionExtensions.getFieldValue(person, "name");
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#getMethodNames(Class)}.
	 */
	@Test
	public void testGetMethodNames()
	{
		List<String> methodNames = Arrays.asList(ReflectionExtensions.getMethodNames(Person.class));
		assertNotNull(methodNames);

		assertTrue(methodNames.contains("builder"));
		assertTrue(methodNames.contains("getNickname"));
		assertTrue(methodNames.contains("getName"));
	}


	/**
	 * Test method for
	 * {@link ReflectionExtensions#getMethodNamesWithPrefixFromFieldNames(java.util.List, String)}.
	 */
	@Test
	public void testGetMethodNamesWithPrefixFromFieldNames()
	{
		List<String> fieldNames = ReflectionExtensions.getFieldNames(Person.class);
		assertNotNull(fieldNames);
		Map<String, String> methodNames = ReflectionExtensions
			.getMethodNamesWithPrefixFromFieldNames(fieldNames, "get");
		assertNotNull(methodNames);

		assertNotNull(methodNames.get("serialVersionUID"));
		assertNotNull(methodNames.get("gender"));
		assertNotNull(methodNames.get("name"));
		assertNotNull(methodNames.get("nickname"));
		assertNotNull(methodNames.get("about"));
		assertNotNull(methodNames.get("married"));
	}

	/**
	 * Test method for {@link ReflectionExtensions#getModifiers(java.lang.reflect.Field)}.
	 *
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 */
	@Test
	public void testGetModifiers() throws NoSuchFieldException, SecurityException
	{
		String expected;
		String actual;

		Field declaredField = ReflectionExtensions.getDeclaredField(Person.class, "name");
		List<String> modifiers = ReflectionExtensions.getModifiers(declaredField);

		assertNotNull(modifiers);
		assertFalse(modifiers.isEmpty());
		expected = "private";
		actual = modifiers.get(0);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#setFieldValue(Object, Field, Object)}
	 *
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method.
	 */
	@Test
	public void testSetFieldValueObjectWithField()
		throws NoSuchFieldException, SecurityException, IllegalAccessException
	{
		String expected;
		String actual;
		final Person person = Person.builder().name("Alex").build();
		expected = "Leo";
		Field nameField = ReflectionExtensions.getDeclaredField(person, "name");
		ReflectionExtensions.setFieldValue(person, nameField, "Leo");
		actual = person.getName();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ReflectionExtensions#setFieldValue(Class, String, Object)}.
	 *
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access a method.
	 */
	@Test
	public void testSetFieldValueWithClass()
		throws NoSuchFieldException, SecurityException, IllegalAccessException
	{
		String expected;
		String actual;

		ReflectionExtensions.setFieldValue(StaticBox.class, "value", "Leo");
		actual = StaticBox.getValue();
		expected = "Leo";
		assertEquals(expected, actual);

		ReflectionExtensions.setFieldValue(StaticBox.class, "value", (Object)null);
		actual = StaticBox.getValue();
		expected = null;
		assertEquals(expected, actual);
	}


	/**
	 * Test method for {@link ReflectionExtensions#setFieldValue(Object, Object, Field)}
	 *
	 * @throws IllegalAccessException
	 *             is thrown if an illegal on create an instance or access of a method
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists.
	 * @throws SecurityException
	 *             is thrown if a security manager says no.
	 */
	@Test
	public void testSetFieldValueWithField() throws IllegalAccessException, NoSuchFieldException,
		SecurityException, InvocationTargetException, InstantiationException, NoSuchMethodException
	{
		String expected;
		String actual;
		Person person;
		Optional<Person> destination;
		Field declaredField;


		expected = "Alex";
		person = Person.builder().name(expected).build();
		destination = InstanceFactory.newInstance(Person.class);
		assertTrue(destination.isPresent());
		Person destinationPerson = destination.get();
		declaredField = ReflectionExtensions.getDeclaredField(destinationPerson, "name");
		ReflectionExtensions.setFieldValue(person, destinationPerson, declaredField);
		actual = destinationPerson.getName();
		assertEquals(expected, actual);

		person.setGender(Gender.FEMALE);
		declaredField = ReflectionExtensions.getDeclaredField(destinationPerson, "gender");
		ReflectionExtensions.setFieldValue(person, destinationPerson, declaredField);
		assertEquals(destinationPerson.getGender(), person.getGender());

		PrimitiveArrays primitiveArrays = PrimitiveArrays.builder()
			.booleanArray(new boolean[] { false, true }).build();
		PrimitiveArrays dest = PrimitiveArrays.builder().booleanArray(new boolean[] { false, true })
			.build();
		declaredField = ReflectionExtensions.getDeclaredField(dest, "booleanArray");
		ReflectionExtensions.setFieldValue(primitiveArrays, dest, declaredField);
		assertArrayEquals(primitiveArrays.getBooleanArray(), dest.getBooleanArray());

	}

}
