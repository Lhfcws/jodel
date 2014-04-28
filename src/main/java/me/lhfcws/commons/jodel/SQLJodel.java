package me.lhfcws.commons.jodel;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import me.lhfcws.commons.jodel.dao.Connector;
import me.lhfcws.commons.jodel.dao.JDBCConnector;
import me.lhfcws.commons.jodel.dao.SQLDao;
import me.lhfcws.commons.jodel.dao.SQLDaoFactory;
import me.lhfcws.commons.jodel.handler.SQLResultSetHandler;
import me.lhfcws.commons.jodel.model.JodelSQLEntry;

public class SQLJodel implements Jodel {
    private SQLDao dao;
    private Connector connector = null;
    private String cacheSql = null;
    private SQLQueryComponents components = null;

    public SQLJodel(JDBCConnector connector) {
        this.connector = connector;
        dao = SQLDaoFactory.getDaoBaseInstance(connector);
    }

    @Override
    public Connector getConnector() {
        return connector;
    }

    @Override
    public void setConnector(JDBCConnector connector) {
        this.connector = connector;
        dao = SQLDaoFactory.getDaoBaseInstance(connector);
    }

    private SQLDao getDao() {
        SQLDao _dao = SQLDaoFactory.getDaoBaseInstance(connector);
        return _dao;
    }

    @Override
    public List<JodelSQLEntry> query(String sql) {
        SQLDao dao = getDao();
        return null;
    }

    @Override
    public List<JodelSQLEntry> select() throws SQLException {
        return dao.queryObjects(components.toSql(), SQLResultSetHandler.commonListRSHandler);
    }

    @Override
    public JodelSQLEntry find() throws SQLException {
        this.limit(1);
        return dao.querySingleObj(components.toSql(), SQLResultSetHandler.commonSingleRSHandler);
    }

    @Override
    public long count() throws SQLException {
        this.components.isCount = true;
        return dao.count(components.toSql(), SQLResultSetHandler.commonCountRSHandler);
    }

//	@Override
//	public boolean save(Map<String, String> data) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean delete() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean add(Map<String, String> data, boolean replace) {
//		// TODO Auto-generated method stub
//		return false;
//	}

    @Override
    public Jodel table(String tablename) {
        this.components.tablename = tablename;
        return this;
    }

    @Override
    public Jodel field(String fields) {
        this.components.field = fields;
        return this;
    }

    @Override
    public Jodel join(String joinStr) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Jodel limit(int offset, int length) {
        this.components.limit = String.format("%d,%d", offset, length);
        return this;
    }

    @Override
    public Jodel limit(int offset) {
        this.components.limit = String.format("%d", offset);
        return this;
    }

    @Override
    public Jodel order(String str) {
        this.components.orderby = str;
        return this;
    }

    @Override
    public Jodel order(Map<String, String> orderMap) {
        if (orderMap != null && !orderMap.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            boolean start = true;
            for (Map.Entry<String, String> entry : orderMap.entrySet()) {
                String _scend = entry.getValue().toUpperCase();
                if (!_scend.equals("ASC") && !_scend.equals("DESC"))
                    return this;
                if (!start)
                    sb.append(", ");
                start = false;
                sb.append(entry.getKey()).append(" ").append(_scend);
            }
            this.order(sb.toString());
        }
        return this;
    }

    @Override
    public Jodel group(String str) {
        this.components.groupby = str;
        return this;
    }

    @Override
    public Jodel having(String str) {
        this.components.having = str;
        return this;
    }

    @Override
    public Jodel where(Map<String, String> condition) {
        if (condition != null && !condition.isEmpty()) {
            boolean start = false;
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : condition.entrySet()) {
                if (start)
                    sb.append(" AND ");
                sb.append(entry.getKey()).append(" = ").append(entry.getValue());
                start = true;
            }
            this.where(sb.toString());
        }
        return this;
    }

    @Override
    public Jodel where(String condition) {
        this.components.where = condition;
        return this;
    }

    @Override
    public Jodel distinct() {
        this.components.distinct = " DISTINCT ";
        return this;
    }

    @Override
    public String getLastSQL() {
        return cacheSql;
    }

    private void clearSQL() {
        cacheSql = components.toSql();
        components.clear();
    }
}
