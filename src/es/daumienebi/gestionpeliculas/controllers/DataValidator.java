package es.daumienebi.gestionpeliculas.controllers;

import java.time.DateTimeException;
import java.time.LocalDate;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DataValidator {

	public static boolean fechaValida(int dia, int mes, int anho) {
		//para comprobar si el formato de la fecha es correcta
		boolean valida = false;
		
		try {
			LocalDate.of(anho, mes, dia);
			valida = true;
		} catch (DateTimeException ex) {
			valida = false;
		}
		
		return valida;
		
	}
	
	public static boolean esNumerico(String valor) {
		//comprueba si un valor dado es numerico
		boolean numerico;
		
		try {
			int num = Integer.parseInt(valor);
			numerico = true;
		}catch(NumberFormatException ex) {
			numerico = false;
		}
		
		return numerico;
	}
	
	public static boolean enBlanco(JTextField x) {
		//comprobar si un TextField esta en blanco o no
		
		if(x.getText().isEmpty()) {
			return true;
		}else
			return false;
	}
		
	public static boolean esDniValido(String dni) {
		if(dni.length()==9) {
			return true;
		}else
			return false;
	}
	
	public static boolean enBlanco2 (Object object) {
		JTextField txtField = null;
		JTextArea  txtArea = null;
		if(object.equals(txtField) && txtField.getText().isEmpty()) {
			return true;
		}else
			if(object.equals(txtArea) && txtArea.getText().isEmpty()) {
				return true;
			}
		return false;
	}
}
