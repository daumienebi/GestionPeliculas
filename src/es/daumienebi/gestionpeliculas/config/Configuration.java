package es.daumienebi.gestionpeliculas.config;

/**
 * Java model for the "connection.properties" values
 */

public class Configuration {
	
	private String  ip;
	private String  db_user;
	private String  db_password;
	private String  port;
	
	public Configuration(String ip, String db_user, String db_password, String port) {
		super();
		this.ip = ip;
		this.db_user = db_user;
		this.db_password = db_password;
		this.port = port;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDb_user() {
		return db_user;
	}
	public void setDb_user(String db_user) {
		this.db_user = db_user;
	}
	public String getDb_password() {
		return db_password;
	}
	public void setDb_password(String db_password) {
		this.db_password = db_password;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	
}
