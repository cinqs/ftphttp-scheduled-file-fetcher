package eu.cisong.main;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import eu.cisong.ftp.FTPSession;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class Main {
	
	@Autowired
	private FTPSession ftpSession;
	
    public static void main(String[] args) throws IOException {
    	SpringApplication.run(Main.class, args);
    	new Main().run();
    }

	private void run() throws IOException {
	}
    
}