package eu.cisong.ftp;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

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
		assertTrue(ftpSession.listFiles("/snapshot", null).length > 0);
	}
}
