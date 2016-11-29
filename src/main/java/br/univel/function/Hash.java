package br.univel.function;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe de encriptação de String para Sha-256
 * 
 * @author LucasMedeiros
 *
 */

public class Hash {

	public String hashSHA256(String texto) throws NoSuchAlgorithmException{
		MessageDigest m=MessageDigest.getInstance("SHA-256");
	    m.update(texto.getBytes(),0,texto.length());
	    return new BigInteger(1,m.digest()).toString(16);
	}
}
