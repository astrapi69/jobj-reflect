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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import io.github.astrapi69.collection.array.ArrayFactory;
import io.github.astrapi69.collection.list.ListFactory;
import io.github.astrapi69.collection.map.MapFactory;
import io.github.astrapi69.collection.pair.KeyValuePair;
import io.github.astrapi69.collection.set.SetFactory;
import io.github.astrapi69.test.object.A;
import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.PremiumMember;
import io.github.astrapi69.test.object.enumeration.Gender;
import io.github.astrapi69.test.object.factory.TestObjectFactory;

/**
 * The unit test class for the class {@link InstanceFactory}
 */
class InstanceFactoryTest
{

	/**
	 * Test method for {@link InstanceFactory}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(InstanceFactory.class);
	}

	/**
	 * Test method for {@link InstanceFactory#newEmptyArrayInstance(Object[])}
	 */
	@Test
	public void testNewEmptyArrayInstance()
	{
		Person[] expected;
		Person[] actual;
		Person[] people;

		people = ArrayFactory.newArray(Person.builder().build());
		actual = InstanceFactory.newEmptyArrayInstance(people);
		assertTrue(actual.length == 0);
		expected = ArrayFactory.newArray();
		assertArrayEquals(expected, actual);
	}

	/**
	 * Test method for {@link InstanceFactory#newArray(Class, int)}
	 */
	@Test
	public void testNewArray()
	{
		Object expected;
		Object actual;
		int length;

		length = 2;

		expected = new Person();
		actual = ReflectionExtensions.copyOfArray(expected);
		assertNull(actual);

		// new scenario ...
		actual = InstanceFactory.newArray(boolean.class, length);
		assertNull(actual);
		// new scenario ...
		expected = InstanceFactory.newArray(boolean[].class, length);
		actual = ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals((boolean[])expected, (boolean[])actual);
		// new scenario ...
		expected = InstanceFactory.newArray(byte[].class, length);
		actual = ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals((byte[])expected, (byte[])actual);
		// new scenario ...
		expected = InstanceFactory.newArray(char[].class, length);
		actual = ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals((char[])expected, (char[])actual);
		// new scenario ...
		expected = InstanceFactory.newArray(short[].class, length);
		actual = ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals((short[])expected, (short[])actual);
		// new scenario ...
		expected = InstanceFactory.newArray(int[].class, length);
		actual = ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals((int[])expected, (int[])actual);
		// new scenario ...
		expected = InstanceFactory.newArray(long[].class, length);
		actual = ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals((long[])expected, (long[])actual);
		// new scenario ...
		expected = InstanceFactory.newArray(float[].class, length);
		actual = ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals((float[])expected, (float[])actual, 0);
		// new scenario ...
		expected = InstanceFactory.newArray(double[].class, length);
		actual = ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals((double[])expected, (double[])actual, 0);
		// new scenario ...
		expected = InstanceFactory.newArray(Double[].class, length);
		actual = ReflectionExtensions.copyOfArray(expected);
		assertArrayEquals((Object[])expected, (Object[])actual);
	}

	/**
	 * Test method for {@link InstanceFactory#newArrayInstance(Class, int)}
	 */
	@Test
	public void testNewArrayInstance()
	{
		Integer[] actual;
		Integer[] expected;

		actual = InstanceFactory.newArrayInstance(Integer.class, 3);
		expected = ArrayFactory.newArray(null, null, null, null);
		assertTrue(Arrays.deepEquals(actual, expected));
	}

	/**
	 * Test method for {@link InstanceFactory#newOptionalInstance(Class, Object...)}
	 */
	@Test
	public void testNewInstanceClassOfT()
	{
		Optional<Person> expected;
		Optional<Person> actual;
		Class<Person> clazz;
		String name;
		String nickname;
		Gender gender;
		String about;
		Boolean married;
		// new scenario ...
		clazz = Person.class;
		actual = InstanceFactory.newOptionalInstance(clazz);
		assertNotNull(actual);
		expected = Optional.of(new Person());
		assertEquals(expected, actual);
		// new scenario ...
		name = "Foo";
		nickname = "man";
		gender = Gender.MALE;
		about = "";
		married = false;
		actual = InstanceFactory.newOptionalInstance(clazz, about, gender, married, name, nickname);
		assertNotNull(actual);
		expected = Optional.of(new Person(about, gender, married, name, nickname));
		assertEquals(expected, actual);

		Map<String, Object> allTestObjectsInMap = TestObjectFactory.getAllTestObjectsInMap();
		allTestObjectsInMap.entrySet().stream().forEach(stringObjectEntry -> {
			Object object = stringObjectEntry.getValue();
			Class<?> aClass = object.getClass();
			Optional<?> optional = InstanceFactory.newOptionalInstance(aClass);
			assertNotNull(optional);
			assertTrue(optional.isPresent());
		});
	}

	/**
	 * Test method for {@link InstanceFactory#newOptionalInstance(String, Object...)}
	 */
	@Test
	public void testNewInstanceClassOfStringWithInvalidClassName()
	{
		Optional<Person> expected;
		Optional<Person> actual;
		String fullyQualifiedClassName;
		// new scenario ...
		fullyQualifiedClassName = "no.qualified.class";
		actual = InstanceFactory.newOptionalInstance(fullyQualifiedClassName);
		assertNotNull(actual);
		expected = Optional.empty();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link InstanceFactory#newInstance(String, Object...)}
	 */
	@Test
	public void testNewInstanceClassNameOfStringWithPerson()
	{
		Person expected;
		Person actual;
		String fullyQualifiedClassName;
		String name;
		String nickname;
		Gender gender;
		String about;
		Boolean married;
		// new scenario ...
		fullyQualifiedClassName = "io.github.astrapi69.test.object.Person";
		actual = InstanceFactory.newInstance(fullyQualifiedClassName);
		assertNotNull(actual);
		expected = new Person();
		assertEquals(expected, actual);
		// new scenario ...
		fullyQualifiedClassName = "io.github.astrapi69.test.object.Person";
		name = "Foo";
		nickname = "man";
		gender = Gender.MALE;
		about = "";
		married = false;
		actual = InstanceFactory.newInstance(fullyQualifiedClassName, about, gender, married, name,
			nickname);
		assertNotNull(actual);
		expected = new Person(about, gender, married, name, nickname);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link InstanceFactory#newInstance(Class, Object...)}
	 */
	@Test
	public void testNewInstanceClassOfStringWithPerson()
	{
		Person expected;
		Person actual;
		String name;
		String nickname;
		Gender gender;
		String about;
		Boolean married;
		// new scenario ...
		actual = InstanceFactory.newInstance(Person.class);
		assertNotNull(actual);
		expected = new Person();
		assertEquals(expected, actual);
		// new scenario ...
		name = "Foo";
		nickname = "man";
		gender = Gender.MALE;
		about = "";
		married = false;
		actual = InstanceFactory.newInstance(Person.class, about, gender, married, name, nickname);
		assertNotNull(actual);
		expected = new Person(about, gender, married, name, nickname);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link InstanceFactory#newInstance(String, Object...)}
	 */
	@Test
	public void testNewInstanceClassOfStringWithPremiumMember()
	{
		PremiumMember expected;
		PremiumMember actual;
		String fullyQualifiedClassName;
		String name;
		String nickname;
		Gender gender;
		String about;
		Boolean married;
		String credits;
		Date dateofbirth;
		Date dateofMarriage;
		// new scenario ...
		fullyQualifiedClassName = "io.github.astrapi69.test.object.PremiumMember";
		actual = InstanceFactory.newInstance(fullyQualifiedClassName);
		assertNotNull(actual);
		expected = new PremiumMember();
		assertEquals(expected, actual);
		// new scenario ...
		name = "Foo";
		nickname = "man";
		gender = Gender.MALE;
		about = "";
		married = false;
		dateofbirth = new Date();
		dateofMarriage = new Date();
		credits = "";
		actual = InstanceFactory.newInstance(fullyQualifiedClassName, about, gender, married, name,
			nickname, dateofbirth, dateofMarriage, credits);
		assertNotNull(actual);
		expected = new PremiumMember(about, gender, married, name, nickname, dateofbirth,
			dateofMarriage, credits);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link InstanceFactory#newInstanceWithClass(Class, Object...)}
	 *
	 * @throws IllegalAccessException
	 *             is thrown if the class or its default constructor is not accessible.
	 * @throws InstantiationException
	 *             is thrown if this {@code Class} represents an abstract class, an interface, an
	 *             array class, a primitive type, or void; or if the class has no default
	 *             constructor; or if the instantiation fails for some other reason.
	 * @throws NoSuchMethodException
	 *             is thrown if a matching method is not found
	 * @throws InvocationTargetException
	 *             is thrown if the underlying constructor throws an exception
	 */
	@Test
	public void testNewInstanceWithSetClass() throws InstantiationException, IllegalAccessException,
		NoSuchMethodException, InvocationTargetException
	{
		Set expected;
		Set actual;

		final Class<Set> clazz = Set.class;
		actual = InstanceFactory.newInstanceWithClass(clazz);
		assertNotNull(actual);
		expected = new HashSet();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link InstanceFactory#newInstanceWithClass(Class, Object...)}
	 *
	 * @throws IllegalAccessException
	 *             is thrown if the class or its default constructor is not accessible.
	 * @throws InstantiationException
	 *             is thrown if this {@code Class} represents an abstract class, an interface, an
	 *             array class, a primitive type, or void; or if the class has no default
	 *             constructor; or if the instantiation fails for some other reason.
	 * @throws NoSuchMethodException
	 *             is thrown if a matching method is not found
	 * @throws InvocationTargetException
	 *             is thrown if the underlying constructor throws an exception
	 */
	@Test
	public void testNewInstanceWithListClass() throws InstantiationException,
		IllegalAccessException, NoSuchMethodException, InvocationTargetException
	{
		List expected;
		List actual;

		final Class<List> clazz = List.class;
		actual = InstanceFactory.newInstanceWithClass(clazz);
		assertNotNull(actual);
		expected = new ArrayList();
		assertEquals(expected, actual);
	}


	/**
	 * Test method for {@link InstanceFactory#newInstanceWithClass(Class, Object...)}
	 *
	 * @throws IllegalAccessException
	 *             is thrown if the class or its default constructor is not accessible.
	 * @throws InstantiationException
	 *             is thrown if this {@code Class} represents an abstract class, an interface, an
	 *             array class, a primitive type, or void; or if the class has no default
	 *             constructor; or if the instantiation fails for some other reason.
	 * @throws NoSuchMethodException
	 *             is thrown if a matching method is not found
	 * @throws InvocationTargetException
	 *             is thrown if the underlying constructor throws an exception
	 */
	@Test
	public void testNewInstanceWithQueueClass() throws InstantiationException,
		IllegalAccessException, NoSuchMethodException, InvocationTargetException
	{
		Queue expected;
		Queue actual;

		final Class<Queue> clazz = Queue.class;
		actual = InstanceFactory.newInstanceWithClass(clazz);
		assertNotNull(actual);
		expected = new LinkedList();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link InstanceFactory#newInstanceWithClass(Class, Object...)}
	 *
	 * @throws IllegalAccessException
	 *             is thrown if the class or its default constructor is not accessible.
	 * @throws InstantiationException
	 *             is thrown if this {@code Class} represents an abstract class, an interface, an
	 *             array class, a primitive type, or void; or if the class has no default
	 *             constructor; or if the instantiation fails for some other reason.
	 * @throws NoSuchMethodException
	 *             is thrown if a matching method is not found
	 * @throws InvocationTargetException
	 *             is thrown if the underlying constructor throws an exception
	 */
	@Test
	public void testNewInstanceWithMapClass() throws InstantiationException, IllegalAccessException,
		NoSuchMethodException, InvocationTargetException
	{
		Map expected;
		Map actual;

		final Class<Map> clazz = Map.class;
		actual = InstanceFactory.newInstanceWithClass(clazz);
		assertNotNull(actual);
		expected = new HashMap();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link InstanceFactory#newOptionalInstance(Class, Object...)}
	 */
	@Test
	public void testNewOptionalInstanceClassOfTArray()
	{
		Optional<int[]> expected;
		Optional<int[]> actual;

		final Class<int[]> intArrayClass = int[].class;
		actual = InstanceFactory.newOptionalInstance(intArrayClass);
		assertNotNull(actual);
		expected = Optional.of(ArrayFactory.newIntArray(0, 0, 0));
		assertArrayEquals(actual.get(), expected.get());
	}

	/**
	 * Test method for {@link InstanceFactory#newGenericOptionalInstance(Object, Object...)}
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testNewGenericInstanceT()
	{
		Optional<?> expected;
		Optional<?> actual;
		A a;
		KeyValuePair<String, Integer> firstEntry;
		Map<String, Integer> map;
		Map<String, Integer> mapActual;
		Map<String, Integer> mapExpected;
		List<A> list;
		List<A> listActual;
		List<A> listExpected;
		Set<A> set;
		Set<A> setActual;
		Set<A> setExpected;

		a = new A();
		actual = InstanceFactory.newGenericOptionalInstance(a);
		assertNotNull(actual);
		expected = Optional.of(new A());
		assertEquals(expected, actual);
		expected = Optional.of(A.builder().build());
		assertEquals(expected, actual);
		// new scenario with object array...
		Integer[] integerArray = ArrayFactory.newArray(1, 2, 3);
		actual = InstanceFactory.newGenericOptionalInstance(integerArray);
		assertNotNull(actual);
		assertTrue(actual.isPresent());
		assertTrue(actual.get() instanceof Integer[]);
		Integer[] integerArrayActual = (Integer[])actual.get();
		Integer[] integerArrayExpected = ArrayFactory.newArray(null, null, null, null);
		assertTrue(Arrays.deepEquals(integerArrayActual, integerArrayExpected));
		// new scenario with a primitive array...
		int[] intArray = ArrayFactory.newIntArray(1, 2, 3);
		actual = InstanceFactory.newGenericOptionalInstance(intArray);
		assertNotNull(actual);
		assertTrue(actual.isPresent());
		assertTrue(actual.get() instanceof int[]);
		int[] intArrayActual = (int[])actual.get();
		int[] intArrayExpected = ArrayFactory.newIntArray(0, 0, 0);
		assertTrue(Arrays.equals(intArrayActual, intArrayExpected));
		// new scenario with hashmap ...
		firstEntry = KeyValuePair.<String, Integer> builder().key("foo").value(1).build();
		map = MapFactory.newHashMap(ListFactory.newArrayList(firstEntry));
		actual = InstanceFactory.newGenericOptionalInstance(map);
		assertNotNull(actual);
		assertTrue(actual.isPresent());
		assertTrue(actual.get() instanceof Map);
		mapActual = (Map<String, Integer>)actual.get();
		mapExpected = MapFactory.newHashMap(ListFactory.newArrayList());
		assertEquals(mapActual, mapExpected);
		// new scenario with linked hashmap ...
		map = MapFactory.newLinkedHashMap(ListFactory.newArrayList(firstEntry));
		actual = InstanceFactory.newGenericOptionalInstance(map);
		assertNotNull(actual);
		assertTrue(actual.isPresent());
		assertTrue(actual.get() instanceof Map);
		mapActual = (Map<String, Integer>)actual.get();
		mapExpected = MapFactory.newLinkedHashMap(ListFactory.newArrayList());
		assertEquals(mapActual, mapExpected);
		// new scenario with tree hashmap ...
		map = MapFactory.newTreeMap(ListFactory.newArrayList(firstEntry));
		actual = InstanceFactory.newGenericOptionalInstance(map);
		assertNotNull(actual);
		assertTrue(actual.isPresent());
		assertTrue(actual.get() instanceof Map);
		mapActual = (Map<String, Integer>)actual.get();
		mapExpected = MapFactory.newTreeMap(ListFactory.newArrayList());
		assertEquals(mapActual, mapExpected);
		// new scenario with array List ...
		list = ListFactory.newArrayList(a);
		actual = InstanceFactory.newGenericOptionalInstance(list);
		assertNotNull(actual);
		assertTrue(actual.isPresent());
		assertTrue(actual.get() instanceof List);
		listActual = (List<A>)actual.get();
		listExpected = ListFactory.newArrayList();
		assertEquals(listActual, listExpected);
		// new scenario with linked List ...
		list = ListFactory.newLinkedList(a);
		actual = InstanceFactory.newGenericOptionalInstance(list);
		assertNotNull(actual);
		assertTrue(actual.isPresent());
		assertTrue(actual.get() instanceof List);
		listActual = (List<A>)actual.get();
		listExpected = ListFactory.newLinkedList();
		assertEquals(listActual, listExpected);
		// new scenario with HashSet ...
		set = SetFactory.newHashSet(a);
		actual = InstanceFactory.newGenericOptionalInstance(set);
		assertNotNull(actual);
		assertTrue(actual.isPresent());
		assertTrue(actual.get() instanceof Set);
		setActual = (Set<A>)actual.get();
		setExpected = SetFactory.newHashSet();
		assertEquals(setActual, setExpected);
		// new scenario with linked HashSet ...
		set = SetFactory.newLinkedHashSet(a);
		actual = InstanceFactory.newGenericOptionalInstance(set);
		assertNotNull(actual);
		assertTrue(actual.isPresent());
		assertTrue(actual.get() instanceof Set);
		setActual = (Set<A>)actual.get();
		setExpected = SetFactory.newLinkedHashSet();
		assertEquals(setActual, setExpected);
	}

}