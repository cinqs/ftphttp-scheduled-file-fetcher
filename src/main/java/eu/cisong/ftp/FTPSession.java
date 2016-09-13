package eu.cisong.ftp;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPHTTPClient;
import org.apache.log4j.Logger;

public class FTPSession {
	private String ftpHost;
	private int ftpPort;
	private String ftpUsername;
	private String ftpPassword;
	
	private FTPClient ftpClient;
	
	private Logger logger = Logger.getLogger(FTPSession.class);
	
	@SuppressWarnings("unused")
	public void onInit() throws SocketException, IOException{
		if(true){
			ftpClient = new FTPClient();
		} else {
			ftpClient = new FTPHTTPClient("proxy.host", 3128, "proxyUser", "proxyPWD");
		}
		
		ftpClient.connect(ftpHost);
		ftpClient.login(ftpUsername, ftpPassword);
		logger.debug(ftpClient.getReplyString());
		System.out.println("shit");
	}
	
	public void onDestroy(){}
	
	public String getFtpHost() {
		return ftpHost;
	}
	public void setFtpHost(String ftpHost) {
		this.ftpHost = ftpHost;
	}
	public int getFtpPort() {
		return ftpPort;
	}
	public void setFtpPort(int ftpPort) {
		this.ftpPort = ftpPort;
	}
	public String getFtpUsername() {
		return ftpUsername;
	}

	public void setFtpUsername(String ftpUsername) {
		this.ftpUsername = ftpUsername;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}
}
