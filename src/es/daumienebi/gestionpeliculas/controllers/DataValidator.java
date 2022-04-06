package es.daumienebi.gestionpeliculas.controllers;

import java.time.DateTimeException;
import java.time.LocalDate;


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
}
