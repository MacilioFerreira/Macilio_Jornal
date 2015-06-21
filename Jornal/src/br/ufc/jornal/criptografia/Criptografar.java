package br.ufc.jornal.criptografia;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografar {

	private static MessageDigest md;
	
	public Criptografar() {
		// TODO Auto-generated constructor stub
	}
	
	public String criptografar(String senha){

		String convertida = null;
		
		try {
			
			// Obtendo instância do algoritmo MD5
			md = MessageDigest.getInstance("MD5");			
			
			// Passando a senha como uma sequência de bytes para a criptografia 
			byte[] converter = md.digest(senha.getBytes());
			
			try {
				
				//Convetendo os bytes para uma string de retorno no padrão UTF-8
				convertida = new String(converter, "UTF-8");
			
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convertida;
	}
}
