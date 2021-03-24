package me.reinaldy.ylobby.groups.manager;

import java.lang.reflect.Field;

public final class ReflectionUtils {

	public static void setValue(String field, Class<?> clazz, Object instance, Object value) {
		try {
			Field f = clazz.getDeclaredField(field);
			f.setAccessible(true);
			f.set(instance, value);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public static Object getValue(String field, Class<?> clazz, Object instance) {
		try {
			Field f = clazz.getDeclaredField(field);
			f.setAccessible(true);
			return f.get(instance);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public static void setValue(String field, Object instance, Object value) {
		try {
			Field f = instance.getClass().getDeclaredField(field);
			f.setAccessible(true);
			f.set(instance, value);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public static Object getValue(String field, Object instance) {
		try {
			Field f = instance.getClass().getDeclaredField(field);
			f.setAccessible(true);
			return f.get(instance);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

}

