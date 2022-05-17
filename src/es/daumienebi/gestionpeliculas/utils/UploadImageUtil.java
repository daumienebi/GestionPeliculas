package es.daumienebi.gestionpeliculas.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import org.apache.commons.collections4.sequence.DeleteCommand;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import com.fasterxml.jackson.databind.util.ObjectBuffer;

import es.daumienebi.gestionpeliculas.config.DefaultConfiguration;
 

//https://stackoverflow.com/questions/55886229/how-to-solve-exception-with-org-apache-commons-net-ftp-ftpclient

public class UploadImageUtil {
	
	private static FTPClient ftpClient = new FTPClient();
	private static String uniqueFileName= LocalDateTime.now().toLocalDate() + "-" + LocalDateTime.now().toLocalTime().getHour()
			+"_"+ LocalDateTime.now().toLocalTime().getMinute()+ "_" + LocalDateTime.now().toLocalTime().getSecond();
	private static double IMAGE_MAX_SIZE = 5.0;
	
	private static FTPClient connect() {
		String server = DefaultConfiguration.ftp_server;
        int port = 21;
        String user = DefaultConfiguration.ftp_user;
        String pass = DefaultConfiguration.ftp_password;
        try { 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            //return ftpClient;
        }catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return ftpClient;
	}
	
    private static boolean validFile(File imgFile) {   	
    	try {
    		File localFile = imgFile;
            Path imgPath  = Paths.get(localFile.getAbsolutePath());
            System.out.println(imgPath);
            long bytes = Files.size(imgPath);
            double kilobytes = (bytes/1024);
            double mb = (kilobytes/1024);
            System.out.println("Image size in megabytes " + mb);
            if(mb < IMAGE_MAX_SIZE) {
            	return true;
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return false;
    }
    
    /**
     * 
     * @param imgFile The selected image file to be uploaded
     * @return returns an array of objects, the first index [0] indicates if the image has been uploaded successfully
     * and the second index [1] contains the file name in the server.
     */
    public static Object[] uploadActorImage(File imgFile) {
    	Object [] result = new Object[2];
    	String ex = "."+getImageExtension(imgFile.getName());
        String routeInServer = "actors/" + uniqueFileName + ex;
        result [0] = uploadImage(imgFile, routeInServer);
        result [1] = uniqueFileName+ex;//the image name in the server
        return result;
    }
    
    /**
     * 
     * @param imgFile The selected image file to be uploaded
     * @return returns an array of objects, the first index [0] indicates if the image has been uploaded successfully
     * and the second index [1] contains the file name in the server.
     */
    public static Object[] uploadMovieImage(File imgFile) {
    	Object [] result = new Object[2];
    	String ex = "."+getImageExtension(imgFile.getName());
        String routeInServer = "movies/" + uniqueFileName + ex;
        result [0] = uploadImage(imgFile, routeInServer);
        result [1] = uniqueFileName+ex;//the image name in the server
        return result;   	
    }
    
    private static boolean uploadImage(File imgFile,String routeInServer) {
    	boolean uploaded = false;
    	try {
    		connect();
            File localFile = imgFile;
            if(validFile(localFile) && localFile != null) {
                InputStream inputStream = new FileInputStream(localFile);

                uploaded = ftpClient.storeFile(routeInServer, inputStream);
                inputStream.close();
                if (uploaded) {
                    System.out.println("The first file is uploaded successfully.");
                }else {
                	System.out.println("Error uploading the image.");
                }
            }else {
				JOptionPane.showMessageDialog(null,"The image exceeds the permited size, the max image size is " + IMAGE_MAX_SIZE +" MB","Error",JOptionPane.INFORMATION_MESSAGE);
            	System.out.println("The image exceeds the permited size");
            }
            
		}catch (IOException ex) {
		    System.out.println("Error: " + ex.getMessage());
		    ex.printStackTrace();
		}finally {
		    try {
		        if (ftpClient.isConnected()) {
		            ftpClient.logout();
		            ftpClient.disconnect();
		        }
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }
		}
    	return uploaded;
    }
    
    public static void deleteImage() {
    	//maybe delete the image from the server after deleting an actor or movie
    }
    
    public static String getImageExtension(String filename) {
        return FilenameUtils.getExtension(filename);
    }
    
    private static void test() {
    	String server = DefaultConfiguration.ftp_server;
        int port = 21;
        String user = DefaultConfiguration.ftp_user;
        String pass = DefaultConfiguration.ftp_password;
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File("C:\\Users\\Usuario\\Desktop\\img\\minion1.jpg");
 
            String firstRemoteFile = "actors/minion12.jpg";
            InputStream inputStream = new FileInputStream(firstLocalFile);
 
            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }
            
            /*
            // APPROACH #2: uploads second file using an OutputStream
            File secondLocalFile = new File("E:/Test/Report.doc");
            String secondRemoteFile = "test/Report.doc";
            inputStream = new FileInputStream(secondLocalFile);
 
            System.out.println("Start uploading second file");
            OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
            byte[] bytesIn = new byte[4096];
            int read = 0;
 
            while ((read = inputStream.read(bytesIn)) != -1) {
                outputStream.write(bytesIn, 0, read);
            }
            inputStream.close();
            outputStream.close();
 
            boolean completed = ftpClient.completePendingCommand();
            if (completed) {
                System.out.println("The second file is uploaded successfully.");
            }
            */
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
