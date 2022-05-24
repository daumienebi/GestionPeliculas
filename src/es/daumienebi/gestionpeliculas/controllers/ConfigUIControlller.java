package es.daumienebi.gestionpeliculas.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import es.daumienebi.gestionpeliculas.config.Configuration;
import es.daumienebi.gestionpeliculas.config.DefaultConfiguration;
import es.daumienebi.gestionpeliculas.utils.TextFieldValidatorUtil;
import es.daumienebi.gestionpeliculas.utils.TranslatorUtil;

import java.net.URISyntaxException;
import java.net.URL;

public class ConfigUIControlller {
	private static final String FILENAME = "../config/app";
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
				prop.setProperty("ip",Configuration.ip);
				prop.setProperty("port",Configuration.port);
				prop.setProperty("db_user",Configuration.db_user);
				prop.setProperty("db_password",Configuration.db_password);
				prop.setProperty("db_name",Configuration.db_name);
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
		//Load the configurations and save it in the Configuration.java file
		ResourceBundle.clearCache();
		//ResourceBundle.clearCache(this.getClass().getClassLoader());
		ResourceBundle bundle = ResourceBundle.getBundle("app");
		if(bundle != null) {
			Configuration.ip = bundle.getString("ip");
			Configuration.db_name = bundle.getString("db_name");
			Configuration.db_user = bundle.getString("db_user");
			Configuration.db_password = bundle.getString("db_password");
			Configuration.use_default_connection = TextFieldValidatorUtil.isNumeric(bundle.getString("use_default_connection")) ? Integer.valueOf(bundle.getString("use_default_connection")) : 0;
			Configuration.lang_id = TextFieldValidatorUtil.isNumeric(bundle.getString("lang_id")) ? Integer.valueOf(bundle.getString("lang_id")) : 0;
		}		
	}
	
	public void translate() {
		if(TranslatorUtil.bundle != null) {
			TranslatorUtil.translateConfigUI(DefaultConfiguration.lang_id);
		}
	}
}
