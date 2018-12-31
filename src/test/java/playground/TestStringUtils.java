package playground;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.nn.utils.StringUtils;

public class TestStringUtils {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLowerCaseFirstLetter() {

		final String PASCAL_CASE_STRING = "CustomerName";
		final String LOWER_CAMEL_CASE_STRING = "departmentName";
		final String SINGLE_CHAR_STRING = "S";
		final String EMPTY_STRING = "";
		final String NULL_STRING = null;

		assertEquals("Error converting pascal case to lower camel case", "customerName",
				StringUtils.lowerCaseFirstLetter(PASCAL_CASE_STRING));
		assertEquals("Error converting lower camel case to lower camel case", "departmentName",
				StringUtils.lowerCaseFirstLetter(LOWER_CAMEL_CASE_STRING));
		assertEquals("Error converting single character string to lower camel case", "s",
				StringUtils.lowerCaseFirstLetter(SINGLE_CHAR_STRING));
		assertEquals("Error converting null string to lower camel case", "",
				StringUtils.lowerCaseFirstLetter(NULL_STRING));
		assertEquals("Error converting empty string to lower camel case", "",
				StringUtils.lowerCaseFirstLetter(EMPTY_STRING));
	}

	@Test
	public void testEncloseStringWithDoubleQuotes() {
		final String NON_EMPTY_STRING = "John";
		final String EMPTY_STRING = "";
		final String NULL_STRING = null;

		assertEquals("Error adding quotes to a non-empty string", "\"John\"",
				StringUtils.encloseStringWithDoubleQuotes(NON_EMPTY_STRING));
		assertEquals("Error adding quotes to an empty string", "\"\"",
				StringUtils.encloseStringWithDoubleQuotes(EMPTY_STRING));
		assertEquals("Error adding quotes to a null string", "\"\"",
				StringUtils.encloseStringWithDoubleQuotes(NULL_STRING));
	}
}
