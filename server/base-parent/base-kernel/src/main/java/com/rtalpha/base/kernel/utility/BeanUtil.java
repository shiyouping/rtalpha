package com.rtalpha.base.kernel.utility;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;

/**
 * @author Ricky Shi
 * @since Jul 24, 2017
 */
public class BeanUtil {

	private final static MyBeanUtilsBean myBean = new MyBeanUtilsBean();
	private final static BeanUtilsBean2 defaultBean = new BeanUtilsBean2();

	private BeanUtil() {
	}

	/**
	 * Delegate of {@linkplain BeanUtilsBean#copyProperties(Object, Object)}.
	 * 
	 * @param destination
	 *            Destination bean whose properties are modified
	 * @param source
	 *            Source bean whose properties are retrieved
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static void copyAllProperties(final Object destination, final Object source)
			throws IllegalAccessException, InvocationTargetException {
		defaultBean.copyProperties(destination, source);
	}

	/**
	 * See {@linkplain BeanUtilsBean#copyProperties(Object, Object)}. The only
	 * difference is that this implementation only copies non-null properties
	 * instead of all properties from source bean
	 * 
	 * @param destination
	 *            Destination bean whose properties are modified
	 * @param source
	 *            Source bean whose properties are retrieved
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static void copyNonnullProperties(final Object destination, final Object source)
			throws IllegalAccessException, InvocationTargetException {
		myBean.copyProperties(destination, source);
	}

	private static class MyBeanUtilsBean extends BeanUtilsBean2 {
		@Override
		public void copyProperty(Object bean, String name, Object value)
				throws IllegalAccessException, InvocationTargetException {

			if (value == null) {
				return;
			}

			super.copyProperty(bean, name, value);
		}
	}
}
