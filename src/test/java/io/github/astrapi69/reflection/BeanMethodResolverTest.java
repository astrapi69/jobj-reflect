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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.Television;

/**
 * The unit test class for the class {@link BeanMethodResolver}
 */
class BeanMethodResolverTest
{

	/**
	 * Test method for {@link BeanMethodResolver}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(BeanMethodResolver.class);
	}

	/**
	 * Test method for {@link BeanMethodResolver#isGetter(Method)}
	 */
	@Test
	void isGetter() throws NoSuchMethodException
	{
		boolean actual;

		actual = BeanMethodResolver.isGetter(Person.class.getMethod("getMarried"));
		assertTrue(actual);
		actual = BeanMethodResolver.isGetter(Television.class.getMethod("isOn"));
		assertTrue(actual);
	}

	/**
	 * Test method for {@link BeanMethodResolver#isSetter(Method)}
	 */
	@Test
	void isSetter() throws NoSuchMethodException
	{
		boolean actual;
		actual = BeanMethodResolver.isSetter(Person.class.getMethod("setMarried", Boolean.class));
		assertTrue(actual);
		actual = BeanMethodResolver.isSetter(Television.class.getMethod("setOn", boolean.class));
		assertTrue(actual);
	}

	/**
	 * Test method for {@link BeanMethodResolver#isGetterMethod(Method)}
	 */
	@Test
	void isGetterMethod() throws NoSuchMethodException
	{
		boolean actual;
		actual = BeanMethodResolver.isGetterMethod(Person.class.getMethod("getMarried"));
		assertTrue(actual);
		actual = BeanMethodResolver.isGetterMethod(Television.class.getMethod("isOn"));
		assertFalse(actual);
	}
}