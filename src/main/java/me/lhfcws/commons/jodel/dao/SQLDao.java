package me.lhfcws.commons.jodel.dao;

import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.*;

import org.apache.commons.dbutils.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 提供一些基础的访问数据库(Database Access Object)操作
 *
 * @author wangkai/arber
 */
public class SQLDao {
    private Log LOG = LogFactory.getLog(SQLDao.class);
    protected QueryRunner queryRunner;

    public SQLDao(DataSource ds) {
    }

    /**
     * 根据条件获取单个对象
     *
     * @param <T>
     * @param sql
     * @param handler
     * @return
     * @throws SQLException
     */
    public <T> T querySingleObj(String sql, ResultSetHandler<T> handler) throws SQLException {
        T t = queryRunner.query(sql, handler);
        return t;
    }

    /**
     * 执行查询，将每行的结果保存到Bean中，然后将所有Bean保存到List中
     *
     * @param <T>
     * @param sql
     * @param handler
     * @return
     * @throws SQLException
     */
    public <T> List<T> queryObjects(String sql, ResultSetHandler<List<T>> handler) throws SQLException {
        List<T> list = queryRunner.query(sql, handler);
        return list;
    }

    public Long count(String sql, ResultSetHandler<Long> handler) throws SQLException {
        Long result = queryRunner.query(sql, handler);
        return result;
    }
}
