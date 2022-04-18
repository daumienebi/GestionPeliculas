package es.daumienebi.gestionpeliculas.controllers;

import java.time.DateTimeException;
import java.time.LocalDate;


public class DataValidator {

	public static boolean isValidDate(int day, int month, int year) {
		//checks if the date format is correct
		boolean valida = false;		
		try {
			LocalDate.of(year, month, day);
			valida = true;
		} catch (DateTimeException ex) {
			valida = false;
		}
		return valida;		
	}
	
	public static boolean isNumeric(String valor) {
		//checks if a given value is numeric
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
