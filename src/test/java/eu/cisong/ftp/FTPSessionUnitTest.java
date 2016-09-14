package eu.cisong.ftp;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FTPSessionUnitTest {
	
	private static ConfigurableApplicationContext ctx;
	private static FTPSession ftpSession;
	
	@BeforeClass
	public static void initAll(){
		ctx = new ClassPathXmlApplicationContext("beans.xml");
		ftpSession = ctx.getBean("ftpSession", FTPSession.class);
	}
	
	@Test
	public void testListFile() throws IOException{
		assertTrue(ftpSession.listFiles("/snapshot", "*.gz").length > 0);
	}
	
	@Test
	public void testDownloadFile() throws IOException {
		FTPFile[] ftpFiles = ftpSession.listFiles("/snapshot", "*.gz");
		
		File localDir = new File("/tmp/filefetcher/");
		FileUtils.deleteDirectory(localDir);
		localDir.mkdirs();
		
		for(FTPFile ftpFile : ftpFiles) {
			String fileName = ftpFile.getName();
			String localFilePath = "/tmp/filefetcher/" + fileName;
			
			ftpSession.downloadFile(fileName, localFilePath);
		}
		
		File[] files = localDir.listFiles();
		assertTrue(files.length == ftpFiles.length);
	}
}
