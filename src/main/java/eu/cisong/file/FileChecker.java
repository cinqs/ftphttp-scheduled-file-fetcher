package eu.cisong.file;

import java.io.File;
import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import eu.cisong.file.DBFileMapper.DBFile;

public class FileChecker {
	
	private DataSource dataSource;
	private JdbcTemplate jt;
	
	protected Logger logger = Logger.getLogger(FileChecker.class);
	
	public void onInit(){
		jt.execute("CREATE TABLE IF NOT EXISTS file_list(id BIGINT auto_increment, name varchar(255), last_modified date)");
	}
	
	public boolean check(File file){
		String sql = "SELECT * FROM file_list WHERE name=? and last_modified=?";
		List<DBFile> dbFileList = jt.query(sql, new Object[] {file.getName(), new Date(file.lastModified())}, new DBFileMapper());
		
		return dbFileList.size() == 1? true:false;
	}
	
	public boolean check(FTPFile file){
		String sql = "SELECT * FROM file_list WHERE name=? and last_modified=?";
		List<DBFile> dbFileList = jt.query(sql, new Object[] {file.getName(), new Date(file.getTimestamp().getTimeInMillis())}, new DBFileMapper());
		
		return dbFileList.size() == 1? true:false;
	}
	
	public void update(File file){
		if(check(file)){
			logger.warn("File: " + file.getName() + " is already existed");
		} else {
			String sql = "INSERT INTO file_list values (null, ?, ?)";
			jt.update(sql, new Object[] {file.getName(), new Date(file.lastModified())});
		}
	}
	
	public void update(FTPFile file){
		if(check(file)){
			logger.warn("File: " + file.getName() + " is already existed");
		} else {
			String sql = "INSERT INTO file_list values (null, ?, ?)";
			jt.update(sql, new Object[] {file.getName(), new Date(file.getTimestamp().getTimeInMillis())});
		}
	}
	
	public List<DBFile> listDBFile(){
		String sql = "SELECT * FROM file_list";
		return jt.query(sql, new DBFileMapper());
	}
	
	public void onDestroy(){}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jt = new JdbcTemplate(dataSource);
	}
	
	public void dropTable(){
		jt.execute("DROP TABLE IF EXISTS file_list");
	}
}
