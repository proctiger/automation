package uol.affiliated.commons.test;

import java.lang.reflect.Method;

import com.google.common.base.Preconditions;

abstract class ReflectionUtil {

	public static Method getMethod(Class<?> clazz, String name) {
		Preconditions.checkNotNull(clazz, "Impossible to read a null class");
		Preconditions.checkNotNull(name, "Impossible to a null method");
		while (clazz != Object.class) {
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method: methods) {
				if (method.getName().equals(name)) {
					method.setAccessible(true);
					return method;
				}
			}
			clazz = clazz.getSuperclass();
		}
		return null;
	}
}