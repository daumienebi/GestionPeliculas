package es.daumienebi.gestionpeliculas.config;

/**
 * Java model for the "app.properties" values
 */

public class Configuration {
	
	//model for the app properties
	public static String ip;
	public static String db_name;
	public static String db_user;
	public static String db_password;
	public static String port;
	// {1} -> English 
	// {2} -> Spanish
	public static int language = 1;
	
	//{1} to use default connection,
	//{0} to use the user configuration (from the ConfigUI)
	//{-1} if its not configured - (the user cannot access the menu options without a Database connection)
	public static int use_default_connection = -1; 
	
	//getters
	public static String getIp() {
		return ip;
	}
	public static String getDb_user() {
		return db_user;
	}
	public static String getDb_password() {
		return db_password;
	}
	
	public static String getDb_Name() {
		return db_name;
	}
	public static String getPort() {
		return port;
	}
	public static int getUseDefaultConnection() {
		return use_default_connection;
	}
	
	public static int getLanguage() {
		return language;
	}
	
}
