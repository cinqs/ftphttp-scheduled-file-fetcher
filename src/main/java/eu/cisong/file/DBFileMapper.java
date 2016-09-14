package eu.cisong.file;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DBFileMapper implements RowMapper {

	@Override
	public DBFile mapRow(ResultSet rs, int rn) throws SQLException {
		DBFile df = new DBFile();
		df.setId(rs.getLong("id"));
		df.setName(rs.getString("name"));
		df.setLastModified(rs.getDate("last_modified"));
		return df;
	}
	
	class DBFile {
		private long id;
		private String name;
		private Date lastModified;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Date getLastModified() {
			return lastModified;
		}
		public void setLastModified(Date lastModified) {
			this.lastModified = lastModified;
		}
	}

}
