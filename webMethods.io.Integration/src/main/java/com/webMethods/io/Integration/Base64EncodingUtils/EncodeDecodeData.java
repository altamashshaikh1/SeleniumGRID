/**
 * This Java class serves as a utility for encoding and decoding base64 strings, as well as converting normal strings to base64 representations. 
 * It provides essential methods to facilitate these operations in a convenient and efficient manner.The `encodeToBase64` method allows users to encode a given string into its base64 equivalent, which is particularly useful for scenarios such as data serialization and transmission.
 * Conversely, the `decodeFromBase64` method enables the decoding of a base64-encoded string back to its original form, ensuring the integrity and accuracy of data retrieval.
 * 
 * @author altsh
 * @version 1.0
 * @since 21st August 2023 
 **/

package com.webMethods.io.Integration.Base64EncodingUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import com.webMethods.io.Integration.FailuresAndExceptionsHandling.CommonExceptionHandling;

public class EncodeDecodeData
{
	/** HELPER METHOD TO  ENCODE SIMPLE TEXT TO BASE64 ENCODING **/
	public static String encodeToBase64(String plainText) 
	{
		String encodedString = "";

		try 
		{
			byte[] bytes = plainText.getBytes(StandardCharsets.UTF_8);
			encodedString = Base64.getEncoder().encodeToString(bytes);
		}
		catch (Exception exception)
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("Encode text to Base64 string.", exception.getMessage());
		}

		return encodedString;

	}

	/** HELPER METHOD TO  DECODE BASE 64 STRING TO SIMPLE TEXT **/
	public static String decodeFromBase64(String base64Text) 
	{
		String decodedString = "";

		try 
		{
			byte[] decodedBytes = Base64.getDecoder().decode(base64Text);
			decodedString = new String(decodedBytes, StandardCharsets.UTF_8);
		} 
		catch (Exception exception)
		{
			CommonExceptionHandling.captureExceptionAndTerminateTest("Decode Base64 string to string.", exception.getMessage());
		}

		return decodedString;

	}
}