package es.daumienebi.gestionpeliculas.config;

/**
 * Java model for the "app.properties" values
 */

public class Configuration {
	
	
	public static String ip = "192.168.56.102";
	public static String port = "3306";
	public static String db_name = "moviedb";
	public static String db_user = "root";
	public static String db_password = "";
	public static int lang_id = 0;
	public static String actor_image_server = "http://192.168.56.102/moviemanagement_images/actors/";
	public static String movie_image_server = "http://192.168.56.102/moviemanagement_images/movies/";
	public static String ftp_server = "192.168.56.102";
	public static String ftp_user = "moviedb";
	public static String ftp_password = "moviedb";
	public static int use_default_connection = 1;
	/*
	//model for the app properties
	public static String ip;
	public static String db_name;
	public static String db_user;
	public static String db_password;
	public static String port;
	// {1} -> English   or directly save the locale
	// {2} -> Spanish
	public static int lang_id = 1;
	
	//{1} to use default connection,
	//{0} to use the user configuration (from the ConfigUI)
	//{-1} if its not configured - (the user cannot access the menu options without a Database connection)
	public static int use_default_connection = -1; 
	*/
	
	public static void setIp(String ip) {
		Configuration.ip = ip;
	}
	public static void setDb_name(String db_name) {
		Configuration.db_name = db_name;
	}
	public static void setDb_user(String db_user) {
		Configuration.db_user = db_user;
	}
	public static void setDb_password(String db_password) {
		Configuration.db_password = db_password;
	}
	public static void setPort(String port) {
		Configuration.port = port;
	}
	public static void setLang_id(int lang_id) {
		Configuration.lang_id = lang_id;
	}
	public static void setUse_default_connection(int use_default_connection) {
		Configuration.use_default_connection = use_default_connection;
	}
		
}
