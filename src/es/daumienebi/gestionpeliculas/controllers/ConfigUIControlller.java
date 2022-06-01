package es.daumienebi.gestionpeliculas.controllers;

import java.io.File;
import java.io.FileInputStream;
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
	private static final String PATH = "./app.properties";
	//private static final String DEV_PATH = "./app.properties";
	
	public void saveConfig() {
		FileInputStream file1 ;
		Properties prop = null;
		//get the props
		try(InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("app.properties")){
			prop = new Properties();
			
			//PROD 
			file1 = new FileInputStream(PATH);
			prop.load(file1);
			
			//DEV
			//prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();			
		}
		
		URL resourseUrl = this.getClass().getClassLoader().getResource("app.properties");
		File output_file;
		try{
			//DEV
			//output_file = new File(resourseUrl.toURI());
			
			//PROD
			output_file = new File(PATH);
			
			try(OutputStream outputStream = new FileOutputStream(output_file);){
				prop.setProperty("ip",Configuration.ip);
				prop.setProperty("port",Configuration.port);
				prop.setProperty("db_user",Configuration.db_user);
				prop.setProperty("db_password",Configuration.db_password);
				prop.setProperty("db_name",Configuration.db_name);
				prop.setProperty("ftp_server",Configuration.ftp_server);
				prop.setProperty("actor_image_server",Configuration.actor_image_server);
				prop.setProperty("movie_image_server",Configuration.movie_image_server);
				prop.setProperty("ftp_password",Configuration.ftp_password);
				prop.setProperty("ftp_user",Configuration.ftp_user);
				prop.setProperty("lang_id",String.valueOf(Configuration.lang_id));
				prop.setProperty("use_default_connection",String.valueOf(Configuration.use_default_connection));
				prop.store(outputStream, null);
				
				//store it with the Configuration object			
		}catch (IOException e) {
			e.printStackTrace();		
		} 
		}catch (Exception e) {
			e.printStackTrace();
		}				
}
	
	public void loadConfig() {
		//Load the configurations and save it in the Configuration.java file
		ResourceBundle.clearCache();
		//ResourceBundle.clearCache(this.getClass().getClassLoader());
		ResourceBundle bundle = ResourceBundle.getBundle("app");
		if(bundle != null) {
			Configuration.ip = bundle.getString("ip");
			Configuration.db_name = bundle.getString("db_name");
			Configuration.db_user = bundle.getString("db_user");
			Configuration.db_password = bundle.getString("db_password");
			Configuration.movie_image_server = bundle.getString("movie_image_server");
			Configuration.actor_image_server = bundle.getString("actor_image_server");
			Configuration.ftp_password = bundle.getString("ftp_password");
			Configuration.ftp_server = bundle.getString("ftp_server");
			Configuration.ftp_user = bundle.getString("ftp_user");
			Configuration.port = bundle.getString("port");
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
