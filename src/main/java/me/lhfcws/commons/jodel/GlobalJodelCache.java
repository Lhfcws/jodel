package me.lhfcws.commons.jodel;

import me.lhfcws.commons.jodel.dao.Connector;
import me.lhfcws.commons.jodel.dao.JDBCConnector;
import me.lhfcws.commons.jodel.dao.SQLDaoFactory;
import me.lhfcws.commons.jodel.model.AdvMap;

/**
 * @author lhfcws
 * @time 14-4-28.
 */
class GlobalJodelCache {
    private static GlobalJodelCache cache = null;
    private AdvMap<String, Jodel> cacheMap;

    private GlobalJodelCache() {
        cacheMap = new AdvMap<String, Jodel>();
    }

    public static GlobalJodelCache getInstance() {
        if (null == cache)
            cache = new GlobalJodelCache();
        return cache;
    }

    public void clear() {
        cacheMap.clear();
    }

    public void save(Jodel jodel) {
        cacheMap.put(jodel.getConnector().args(), jodel);
    }

    public boolean has(Connector connector) {
        return cacheMap.containsKey(connector.args());
    }

    public Jodel get(Connector connector) {
        return cacheMap.get(connector.args());
    }

    public Jodel getSQLJodel(Connector connector) {
        if (has(connector))
            return cacheMap.get(connector.args());
        else
            return new SQLJodel((JDBCConnector)connector);
    }
}
