package utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class AES {

	private static SecretKeySpec secretKey;
	private static byte[] key;
	private static String decryptedString;
	private static String encryptedString;
    private static final String password = "iIuI3BexQNsTNqTFmkP/KQ=="; //Password to generate the secret key (DO NOT CHANGE)

	private static void setKey(String myKey) {

		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); // use only first 128 bit
			secretKey = new SecretKeySpec(key, "AES");

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String getDecryptedString() {
		return decryptedString;
	}

	private static void setDecryptedString(String decryptedString) {
		AES.decryptedString = decryptedString;
	}

	private static String getEncryptedString() {
		return encryptedString;
	}

	private static void setEncryptedString(String encryptedString) {
		AES.encryptedString = encryptedString;
	}

    //Encrypt - Decrypt methods (Public Interface)

	public static String encrypt(String strToEncrypt) {

        AES.setKey(password);

		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			setEncryptedString(Base64.encodeBase64String(cipher
					.doFinal(strToEncrypt.getBytes("UTF-8"))));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString()); // We should change it for a proper Logger
		}
		return encryptedString;
	}

	public static String decrypt(String strToDecrypt) {

        AES.setKey(password);

		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			setDecryptedString(new String(cipher.doFinal(Base64
					.decodeBase64(strToDecrypt))));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString()); // We should change it for a proper Logger
		}
		return decryptedString;
	}

}