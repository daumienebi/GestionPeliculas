package es.daumienebi.gestionpeliculas.config;

/**
 * Java model for the "connection.properties" values
 */

public class Configuration {
	
	public static String ip;
	public static String db_user;
	public static String db_password;
	public static String port;
	
	public static int use_default_connection = -1; //{1} to use default connection, {0} to not use it and {-1} if its not configured
	
	public static String getIp() {
		return ip;
	}
	public static String getDb_user() {
		return db_user;
	}
	public static String getDb_password() {
		return db_password;
	}
	public static String getPort() {
		return port;
	}
	public static int getUseDefaultConnection() {
		return use_default_connection;
	}
	
	public static Configuration getDefaultConfig() {
		return null;
	}
	
}
