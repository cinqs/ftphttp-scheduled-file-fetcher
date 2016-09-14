package eu.cisong.file;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eu.cisong.file.DBFileMapper.DBFile;

public class FileCheckerUnitTest {

	private static FileChecker fileChecker;
	
	@BeforeClass
	public static void init(){
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		fileChecker = ctx.getBean("fileChecker", FileChecker.class);
	}
	
	@Test
	public void testCheck() throws IOException{
		File file = new File("/tmp/fetcher/tmp.xml");
		file.mkdirs();
		file.createNewFile();
		assertFalse(fileChecker.check(file));
		
		fileChecker.update(file);
		assertTrue(fileChecker.check(file));
		file.delete();
	}
	
	@Test
	public void listFile(){
		for(DBFile dbFile: fileChecker.listDBFile()) {
			System.out.println(dbFile.getName());
		}
	}
	
	@AfterClass
	public static void destroy(){
		fileChecker.dropTable();
	}
}
