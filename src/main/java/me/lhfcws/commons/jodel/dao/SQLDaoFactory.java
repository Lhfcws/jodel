package me.lhfcws.commons.jodel.dao;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SQLDaoFactory {
	private Log LOG = LogFactory.getLog(SQLDaoFactory.class);
    public static final String DB_DRIVER = "org.gjt.mm.mysql.Driver";

    private static Map<String, SQLDao> dataSourceMap = new HashMap<String, SQLDao>();

    public static SQLDao getDaoBaseInstance(String dbString) {
        if (!dataSourceMap.containsKey(dbString)) {
            DataSource ds = createComboPooledInstance(dbString);
            if (ds == null)
                return null;

            dataSourceMap.put(dbString, new SQLDao(ds));
        }

        return dataSourceMap.get(dbString);
    }

    public static SQLDao getDaoBaseInstance(Connector connector) {
        String dbString = connector.args();
        if (!dataSourceMap.containsKey(dbString)) {
            DataSource ds = createComboPooledInstance(dbString);
            if (ds == null)
                return null;

            dataSourceMap.put(dbString, new SQLDao(ds));
        }

        return dataSourceMap.get(dbString);
    }

    private static ComboPooledDataSource createComboPooledInstance(
            String dbString) {
        try {
            String[] paras = dbString.split("\\|");
            if (paras.length < 3) {
                System.err.println(dbString
                        + ", Not enough MySQL db arguments!");
                return null;
            }
            int maxPoolSize = 1;
            if (paras.length == 4) { // 设置最大poolsize
                maxPoolSize = Integer.parseInt(paras[3]);
            }

            ComboPooledDataSource ds = new ComboPooledDataSource();
            // 设置JDBC的Driver类
            ds.setDriverClass(DB_DRIVER); // 参数由 Config 类根据配置文件读取
            // 设置JDBC的URL
            ds.setJdbcUrl(paras[0]); // "jdbc:mysql://summba-dev3:3306/db_summba_goo5?useUnicode=true&characterEncoding=utf-8");
            // 设置数据库的登录用户名
            ds.setUser(paras[1]);
            // 设置数据库的登录用户密码
            ds.setPassword(paras[2]);
            // 设置连接池的最大连接数
            ds.setMaxPoolSize(maxPoolSize);
            // 设置连接池的最小连接数
            ds.setMinPoolSize(1);
            ds.setInitialPoolSize(1);
            ds.setAcquireIncrement(1);
            ds.setIdleConnectionTestPeriod(60);
            ds.setTestConnectionOnCheckin(true);
            ds.setMaxIdleTime(600);
            ds.setMaxStatements(50);
            return ds;
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            return null;
        }
    }
}
