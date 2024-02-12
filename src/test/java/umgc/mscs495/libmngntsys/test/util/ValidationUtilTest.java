package umgc.mscs495.libmngntsys.test.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import umgc.mscs495.libmngntsys.utils.ValidationUtil;

/**
 * JUnit test for APIs defined in ValidationUtil.java.
 * @author jimiewang
 * @CreateDate 01/31/2024
 */
public class ValidationUtilTest {
	ValidationUtil validationUtil = new ValidationUtil();
	
	@Test
	public void digitsValidationTest() {
		System.out.println("check digital input string test");
		boolean suc  = validationUtil.isDigits("");
		assertEquals(suc, false);
		suc  = validationUtil.isDigits("234234w93l3o");
		assertEquals(suc, false);
		suc  = validationUtil.isDigits("34543454334");
		assertEquals(suc, true);
		System.out.println("check digital input string test completed");
	}
	
	@Test
	public void validEmailAddressTest() {
		System.out.println("check valid email address test");
		boolean suc  = validationUtil.validateEamil("");
		assertEquals(suc, false);
		suc  = validationUtil.validateEamil("234234w93l3o");
		assertEquals(suc, false);
		suc  = validationUtil.validateEamil("yu.jsif@fjie");
		assertEquals(suc, false);
		suc  = validationUtil.validateEamil("yu.jsif@fjie.@jf");
		assertEquals(suc, false);
		suc  = validationUtil.validateEamil("@fjie.com");
		assertEquals(suc, false);
		suc  = validationUtil.validateEamil("yu.jsif@.com");
		assertEquals(suc, false);
		suc  = validationUtil.validateEamil("yu.jsif@#fjios.com");
		assertEquals(suc, false);
		suc  = validationUtil.validateEamil("yu.jsif@yahoo.com");
		assertEquals(suc, true);
		System.out.println("check valid email address test completed");
		
	}
	
	@Test
	public void alphanumericValidationTest() {
		System.out.println("check alphanumeric input string test");
		boolean suc  = validationUtil.isAlphanumeric("");
		assertEquals(suc, false);
		suc  = validationUtil.isAlphanumeric("234234w93l3o!");
		assertEquals(suc, false);
		suc  = validationUtil.isAlphanumeric("234#234w93l3o");
		assertEquals(suc, false);
		suc  = validationUtil.isAlphanumeric("2342@34w93l3o");
		assertEquals(suc, false);
		suc  = validationUtil.isAlphanumeric("23423=4w93l3o");
		assertEquals(suc, false);
		suc  = validationUtil.isAlphanumeric("234234w93l3o!");
		assertEquals(suc, false);
		suc  = validationUtil.isAlphanumeric("234*234w93l3o");
		assertEquals(suc, false);
		suc  = validationUtil.isAlphanumeric("234&234w93l3o");
		assertEquals(suc, false);
		suc  = validationUtil.isAlphanumeric("234$234w93l3o");
		assertEquals(suc, false);
		suc  = validationUtil.isAlphanumeric("34543454334");
		assertEquals(suc, true);
		System.out.println("check alphanumeric input string test completed");
	}
	
}
