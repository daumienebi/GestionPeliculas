package es.daumienebi.gestionpeliculas.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import es.daumienebi.gestionpeliculas.config.Configuration;

import java.net.URISyntaxException;
import java.net.URL;

public class ConfigUIControlller {

	private void saveConfig(Configuration config) {
		Properties prop = null;
		//get the props
		try(InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("connection.properties")){
			prop = new Properties();
			prop.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		URL resourseUrl = this.getClass().getClassLoader().getResource("connection.properties");
		File file;
		try{
			file = new File(resourseUrl.toURI());
			try(OutputStream outputStream = new FileOutputStream(file);){
				prop.setProperty("ip",config.getIp());
				prop.setProperty("port",config.getPort());
				prop.setProperty("db_user",config.getDb_user());
				prop.setProperty("db_password",config.getDb_password());
				prop.store(outputStream, null);
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		} 
		}catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
}

	private void loadConfig() {
		ResourceBundle.clearCache();
		//ResourceBundle.clearCache(this.getClass().getClassLoader());
		ResourceBundle resourceBundle = ResourceBundle.getBundle("connection");
		
		
	}
}
