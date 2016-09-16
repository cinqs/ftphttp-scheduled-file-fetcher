package eu.cisong.ftp;

import java.io.File;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import eu.cisong.file.FileChecker;
import eu.cisong.file.WildcardFilenameFilter;

public class FTPScheduler {
	private static boolean LOCAL_DIR_SCANNED;
	private boolean scanLocalDir;
	private String localDir;
	private String remoteDir;
	private String filePattern;
	private FileChecker fileChecker;
	private FTPSession ftpSession;

	protected Logger logger = Logger.getLogger(FTPScheduler.class);
	
	public void toInvoke() throws IOException{
		logger.info("invoked");
		if (LOCAL_DIR_SCANNED){
			logger.info("to scan remote folder");
			FTPFile[] ftpFiles = ftpSession.listFiles(remoteDir, filePattern);
			logger.info("Remote got " + ftpFiles.length + " to check");
			for(FTPFile ftpFile : ftpFiles) {
				if(fileChecker.check(ftpFile)) {
					logger.info("FTPFile: " + ftpFile.getName() + " already downloaded");
				} else {
					String fileName = ftpFile.getName();
					String localFilePath = localDir + File.separator + fileName;
					ftpSession.downloadFile(fileName, localFilePath);
					fileChecker.update(ftpFile);
				}
			}
		} else {
			logger.info("to scan local folder");
			scanLocalDir();
			LOCAL_DIR_SCANNED = true;
		}
	}
	
	public void scanLocalDir() {
		File localDirFile = new File(localDir);
		if(localDirFile.exists()) {
			logger.info("local dir existed, scanning");
			File[] localFiles = localDirFile.listFiles(new WildcardFilenameFilter(filePattern));
			for(File file : localFiles) {
				logger.info(file.getName());
				fileChecker.update(file);
			}
		} else {
			logger.info("local dir not existed, creating");
			localDirFile.mkdirs();
		}
	}
	
	public void onInit(){
		LOCAL_DIR_SCANNED = !scanLocalDir;
	}
	
	public void onDestroy(){
		
	}

	public boolean isScanLocalDir() {
		return scanLocalDir;
	}

	public void setScanLocalDir(boolean scanLocalDir) {
		this.scanLocalDir = scanLocalDir;
	}

	public String getLocalDir() {
		return localDir;
	}

	public void setLocalDir(String localDir) {
		this.localDir = localDir;
	}

	public String getRemoteDir() {
		return remoteDir;
	}

	public void setRemoteDir(String remoteDir) {
		this.remoteDir = remoteDir;
	}

	public String getFilePattern() {
		return filePattern;
	}

	public void setFilePattern(String filePattern) {
		this.filePattern = filePattern;
	}

	public FileChecker getFileChecker() {
		return fileChecker;
	}

	public void setFileChecker(FileChecker fileChecker) {
		this.fileChecker = fileChecker;
	}

	public FTPSession getFtpSession() {
		return ftpSession;
	}

	public void setFtpSession(FTPSession ftpSession) {
		this.ftpSession = ftpSession;
	}
}
