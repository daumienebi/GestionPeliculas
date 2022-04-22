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

	private void saveConfig() {
		Properties prop = null;
		//get the props
		try(InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("app.properties")){
			prop = new Properties();
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();			
		}
		
		URL resourseUrl = this.getClass().getClassLoader().getResource("app.properties");
		File file;
		try{
			file = new File(resourseUrl.toURI());
			try(OutputStream outputStream = new FileOutputStream(file);){
				prop.setProperty("ip",Configuration.getIp());
				prop.setProperty("port",Configuration.getPort());
				prop.setProperty("db_user",Configuration.getDb_user());
				prop.setProperty("db_password",Configuration.getDb_password());
				prop.store(outputStream, null);
				
				//store it with the Configuration object
			
		}catch (IOException e) {
			e.printStackTrace();		
		} 
		}catch (URISyntaxException e) {
			e.printStackTrace();
		}				
}

	private void loadConfig() {
		ResourceBundle.clearCache();
		//ResourceBundle.clearCache(this.getClass().getClassLoader());
		ResourceBundle resourceBundle = ResourceBundle.getBundle("app");
		
	}
}
