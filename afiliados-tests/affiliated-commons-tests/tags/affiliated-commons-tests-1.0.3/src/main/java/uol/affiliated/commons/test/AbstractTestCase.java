package uol.affiliated.commons.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Assert;

public abstract class AbstractTestCase {

	public void inject(Object object, String attributeName, Object value) {
		Assert.assertNotNull(object);
		Assert.assertNotNull("Attribute name is mandatory", attributeName);
		Assert.assertTrue("Attribute name is mandatory", attributeName.length() > 0);
		Class<?> clazz1 = object.getClass();
		boolean flag = true;
		while (clazz1 != Object.class) {
			Field[] fields = clazz1.getDeclaredFields();
			for (Field field : fields) {
				if (field.getName() == attributeName) {
					field.setAccessible(true);
					try {
						field.set(object, value);
					} catch (Exception e) {
						Assert.fail(e.getLocalizedMessage());
					}
					flag = false;
					break;
				}
			}
			clazz1 = clazz1.getSuperclass();
		}
		if (flag) {
			Assert.fail(String.format("No attribute found with '%s' name. No attribution done.", attributeName));
		}
	}

	public void autowire(Object object, Object value) {
		Assert.assertNotNull(object);
		Assert.assertNotNull("Impossible to autowire a null object", value);
		Class<?> clazz1 = object.getClass(), clazz2 = value.getClass();
		boolean flag = true;
		while (clazz1 != Object.class) {
			Field[] fields = clazz1.getDeclaredFields();
			for (Field field : fields) {
				if (field.getType().isAssignableFrom(clazz2)) {
					field.setAccessible(true);
					try {
						field.set(object, value);
					} catch (Exception e) {
						Assert.fail(e.getLocalizedMessage());
					}
					flag = false;
					break;
				}
			}
			clazz1 = clazz1.getSuperclass();
		}
		if (flag) {
			Assert.fail(String.format("No %s type found. No attribution done.", value.getClass().getName()));
		}
	}

	public Method getMethod(Class<?> clazz, String name) {
		Assert.assertNotNull("Impossible to read a null class", clazz);
		Assert.assertNotNull("Impossible to a null method", name);
		return ReflectionUtil.getMethod(clazz, name);
	}
}