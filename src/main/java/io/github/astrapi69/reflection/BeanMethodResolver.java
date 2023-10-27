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

import java.lang.reflect.Method;

import lombok.NonNull;

/**
 * The class {@link BeanMethodResolver} provides utility methods for resolve method types of java
 * beans
 */
public final class BeanMethodResolver
{

	private BeanMethodResolver()
	{
	}

	/**
	 * Resolves if the given method is a getter method and starts with 'get'
	 *
	 * @param method
	 *            the {@link Method} object
	 * @return {@code true} if method is a getter Method and starts with 'get' otherwise false
	 */
	public static boolean isGetter(Method method)
	{
		return isGetterMethod(method) || isBooleanGetterMethod(method);
	}

	/**
	 * Resolves if the given method is a getter method and starts with 'get'
	 *
	 * @param method
	 *            the {@link Method} object
	 * @return {@code true} if method is a getter Method and starts with 'get' otherwise false
	 */
	public static boolean isGetterMethod(final @NonNull Method method)
	{
		if (method.getParameterTypes().length == 0)
		{
			String name = method.getName();
			Class<?> returnType = method.getReturnType();
			return name.startsWith("get") && name.length() > 3
				&& Character.isUpperCase(name.charAt(3)) && returnType != Void.TYPE;
		}
		return false;
	}

	/**
	 * Resolves if the given Method is a getter Method that returns a boolean value and starts with
	 * 'is'
	 *
	 * @param method
	 *            method to test
	 * @return {@code true} if method is a getter Method that returns a boolean value and starts
	 *         with 'is' otherwise false
	 */
	public static boolean isBooleanGetterMethod(final @NonNull Method method)
	{
		if (method.getParameterTypes().length == 0)
		{
			String name = method.getName();
			Class<?> returnType = method.getReturnType();
			return name.startsWith("is") && name.length() > 2
				&& Character.isUpperCase(name.charAt(2))
				&& (returnType == Boolean.TYPE || returnType == Boolean.class);
		}
		return false;
	}

	/**
	 * Resolves if the given method is a setter method and starts with 'set'
	 *
	 * @param method
	 *            the {@link Method} object
	 * @return {@code true} if method is a setter Method and starts with 'set' otherwise false
	 */
	public static boolean isSetter(final @NonNull Method method)
	{
		if (method.getParameterTypes().length == 1)
		{
			String name = method.getName();
			return name.startsWith("set") && name.length() > 3
				&& Character.isUpperCase(name.charAt(3));
		}
		return false;
	}

}
