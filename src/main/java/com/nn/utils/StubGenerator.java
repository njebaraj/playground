package com.nn.utils;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nn.domain.Employee;

public class StubGenerator {

	private static final Logger log = LogManager.getLogger(MethodHandles.lookup().lookupClass());

	public static void main(String[] args) {

		List<Employee> employees = getEmployees();

		for (Employee emp : employees) {
			log.info(generateStub(emp));
		}
	}

	/**
	 * Generate Java code for test stub
	 * 
	 * Eg. The following Java code is automatically generated for an employee object.
	 *
	 * Employee employee = new Employee();
	 * 
	 *	employee.setFirstName("Andy");
	 *	employee.setLastName("Anderson");
     *	employee.setBirthDate(LocalDate.parse("2000-01-01"));
	 *	employee.setSalary(72000.0);
	 * 
	 * @param obj
	 * @return generated test stub Java code
	 */
	public static String generateStub(Object obj) {

		final Class cls = obj.getClass();
		final String objectName = getObjectName(cls.getSimpleName());

		StringBuilder sb = new StringBuilder();

		// Format object instantiation code
		sb.append(cls.getSimpleName()).append(" ").append(objectName).append(" = ")
		.append("new ").append(cls.getSimpleName()).append("();");
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());

		// Get all getters
		var availableGetters = getAllGetters(cls);
		
		Method[] allMethods = cls.getDeclaredMethods();

		for (Method method : allMethods) {
			if (isSetter(method.getName())) {
				// Get setters
				final String setter = method.getName();
				String matchingGetter = getMatchingGetterForSetter(setter, availableGetters);
				try {
					Method getterMethod = cls.getMethod(matchingGetter, null);
					Object getterRetObj = getterMethod.invoke(obj, null);
					sb.append(getObjectName(cls.getSimpleName()) + "." + setter + "(" + formatArguments(getterRetObj, getterMethod.getReturnType()) + ");");
					sb.append(System.lineSeparator());
				}
				catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
					log.error(e);
				}
			}
		}

		return sb.toString();
	}

	private static List<Employee> getEmployees() {

		var employees = new ArrayList<Employee>();

		Employee emp1 = new Employee();
		emp1.setFirstName("Andy");
		emp1.setLastName("Anderson");
		emp1.setBirthDate(LocalDate.of(2000, Month.JANUARY, 1));
		emp1.setSalary(72000.00f);
		employees.add(emp1);

		return employees;
	}

	private static boolean isGetter(String name) {
		return (name != null && name.startsWith("get"));
	}

	private static boolean isSetter(String name) {
		return (name != null && name.startsWith("set"));
	}

	private static List<String> getAllGetters(Class clazz) {

		var allGetters = new ArrayList<String>();

		if (clazz != null) {
			for (Method method : clazz.getDeclaredMethods()) {
				if (isGetter(method.getName())) {
					allGetters.add(method.getName());
				}
			}
		}

		return allGetters;
	}

	private static String getMatchingGetterForSetter(final String setter, final List<String> availableGetters) {

		if (isSetter(setter)) {
			final String getter = setter.replaceFirst("set", "get");
			if (availableGetters != null && availableGetters.contains(getter)) {
				return getter;
			}
		}

		return "";
	}

	public static String getObjectName(String className) {
		return StringUtils.lowerCaseFirstLetter(className);
	}

	/**
	 * Formats arguments based on a given type
	 * 
	 * @param obj
	 * @param type
	 * @return
	 */
	public static String formatArguments(Object obj, Class type) {

		if (obj == null) {
			return "";
		}
		else if (type == null) {
			return obj.toString();
		}

		switch (type.getName()) {
			case "java.lang.String" : 
				return StringUtils.encloseStringWithDoubleQuotes(obj.toString());
			case "java.time.LocalDate" :
				return "LocalDate.parse(" +  StringUtils.encloseStringWithDoubleQuotes(obj.toString()) + ")";
		}

		return obj.toString();
	}
}
