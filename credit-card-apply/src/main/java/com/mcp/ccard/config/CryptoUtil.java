/*

 * Application: Credit Card Apply Service
 */
package com.mcp.ccard.config;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * The Class CryptoUtil.
 */
@Component
public class CryptoUtil {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(CryptoUtil.class);

	/** The secret. */
	@Value("${crypto.secretKey}")
	private String secret;

	/** The transformation. */
	@Value("${crypto.transformation}")
	private String transformation;
	
	/** The msg digest. */
	@Value("${crypto.msgDigest}")
	private String msgDigest;
	
	/** The algorithm. */
	@Value("${crypto.algorithm}")
	private String algorithm;

	/**
	 * Gets the secret key spec method.
	 *
	 * @return the secret key spec
	 */
	public SecretKeySpec getSecretKeySpec() {
		byte[] key = null;
		SecretKeySpec secretKey = null;
		MessageDigest sha = null;
		try {
			key = secret.getBytes(StandardCharsets.UTF_8);
			sha = MessageDigest.getInstance(msgDigest);
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, algorithm);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		} 
		return secretKey;
	}

	/**
	 * Encrypt Method.
	 *
	 * @param strToEncrypt the str to encrypt
	 * @return the string
	 */
	public String encrypt(String strToEncrypt) {
		try {
			Cipher cipher = Cipher.getInstance(transformation);
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKeySpec());
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Decrypt Method.
	 *
	 * @param strToDecrypt the str to decrypt
	 * @return the string
	 */
	public String decrypt(String strToDecrypt) {
		try {
			Cipher cipher = Cipher.getInstance(transformation);
			cipher.init(Cipher.DECRYPT_MODE, getSecretKeySpec());
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
