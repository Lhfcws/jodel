package me.lhfcws.commons.jodel.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import me.lhfcws.commons.jodel.model.JodelSQLEntry;
import org.apache.commons.dbutils.ResultSetHandler;


/*
 * 基本的ResultSetHandler的封装。
 * @author Lhfcws(wenjie)
 */
public class SQLResultSetHandler {
	public static ResultSetHandler<List<JodelSQLEntry>> commonListRSHandler = new ResultSetHandler<List<JodelSQLEntry>> () {
		@Override
		public List<JodelSQLEntry> handle(ResultSet rs) throws SQLException {
			List<JodelSQLEntry> list = new ArrayList<JodelSQLEntry>();
			
			int colnum = rs.getMetaData().getColumnCount();
			
			while (rs.next()) {
				JodelSQLEntry row = new JodelSQLEntry();
				/* Get the columns' name, try to use rs.getMetaData() */
				for (int i = 1; i <= colnum; i++) {
                    row.addColumn(rs.getMetaData().getColumnName(i));
                    row.put(rs.getMetaData().getColumnName(i), rs.getString(i));
				}

				list.add(row);
			}
			
			return list;
		}
	};

    public static ResultSetHandler<JodelSQLEntry> commonSingleRSHandler = new ResultSetHandler<JodelSQLEntry> () {
        @Override
        public JodelSQLEntry handle(ResultSet rs) throws SQLException {
            JodelSQLEntry row = new JodelSQLEntry();

            int colnum = rs.getMetaData().getColumnCount();

            if (rs.next()) {
				/* Get the columns' name, try to use rs.getMetaData() */
                for (int i = 1; i <= colnum; i++) {
                    row.addColumn(rs.getMetaData().getColumnName(i));
                    row.put(rs.getMetaData().getColumnName(i), rs.getString(i));
                }
            }

            return row;
        }
    };

    public static ResultSetHandler<Long> commonCountRSHandler = new ResultSetHandler<Long>(){

        @Override
        public Long handle(ResultSet rs) throws SQLException {
            if(rs.next()){
                return rs.getLong(1); //或者rs.getLong("count");
            }
            return 0L;
        }

    };
}
