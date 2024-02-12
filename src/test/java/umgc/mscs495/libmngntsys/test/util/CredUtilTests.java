package umgc.mscs495.libmngntsys.test.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import umgc.mscs495.libmngntsys.utils.CredUtil;

/**
 * JUnit test for APIs defined in CredUtil.java.
 * @author jimiewang
 * @CreateDate 01/23/2024
 */
public class CredUtilTests {
	CredUtil credUtil = new CredUtil();
	
	@Test
	public void doDecryptionTest() {
		System.out.println("decryption test");
		try {
			String decryptedVal = credUtil.decrypt(credUtil.getPropValue(credUtil.getConfigFileFullPath(), "test"), 
									credUtil.getPropValue(credUtil.getConfigFileFullPath(), "loginkey"));
			assertEquals(decryptedVal, "CMIS495");
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("decryption test completed");
	}
	
	@Test
	public void getConfigFileTest() {
		System.out.println("get config file test");
		try {
		String configFlPath = credUtil.getConfigFileFullPath();
		assertEquals(credUtil.getConfigFileFullPath(), configFlPath);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("get config file test completed");
	}
	
	@Test
	public void getPropValueTest() {
		System.out.println("get config property value test");
		try {
			String keyValue = credUtil.getPropValue(credUtil.getConfigFileFullPath(), "loginkey");
			assertEquals(keyValue, "K+rccnWCZ9xaFzw2Ipmo8Q==");
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("get config property value test completed");
	}
	
	@Test
	public void validateFileNameTest() {
		System.out.println("validate File Name test");
		try {
			boolean suc = credUtil.validateInputFileName(credUtil.getConfigFileDir(), CredUtil.CONFIG_FILE_NAME);
			assertEquals(suc, true);
			suc = credUtil.validateInputFileName(credUtil.getConfigFileDir(), "fakefile.txt");
			assertEquals(suc, false);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("validate File Name test completed");
	}
	
	
}
