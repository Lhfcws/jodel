package me.lhfcws.commons.jodel;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import me.lhfcws.commons.jodel.dao.Connector;
import me.lhfcws.commons.jodel.dao.JDBCConnector;
import me.lhfcws.commons.jodel.model.JodelSQLEntry;

/**
 * Jodel API wrapper.
 * @author lhfcws
 *
 */
public interface Jodel {
    public void setConnector(JDBCConnector connector);
    public Connector getConnector();
	public List<JodelSQLEntry> query(String sql);
	public List<JodelSQLEntry> select() throws SQLException;
	public JodelSQLEntry find() throws SQLException;
	public long count() throws SQLException;
//	public boolean save(Map<String, String> data);
//	public boolean delete();
//	public boolean add(Map<String, String> data, boolean replace);
	
	public Jodel table(String tablename);
	public Jodel field(String fields);
	public Jodel join(String joinStr);
	public Jodel limit(int offset, int length);
	public Jodel limit(int offset);
	public Jodel order(String str);
	public Jodel order(Map<String, String> orderMap);
	public Jodel group(String str);
	public Jodel having(String str);
	public Jodel where(Map<String, String> condition);
	public Jodel where(String condition);
	public Jodel distinct();
	
	public String getLastSQL();
}
