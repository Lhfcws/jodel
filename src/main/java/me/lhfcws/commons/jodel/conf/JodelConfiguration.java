package me.lhfcws.commons.jodel.conf;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lhfcws
 * @time 14-2-18.
 */
@Deprecated
class JodelConfiguration {
    private static final int STRING = 0;
    private static final int INT = 1;
    private static final int FLOAT = 2;
    private static final int BOOLEAN = 3;

    private static final int _SIZE = 4;

    public ArrayList<ConcurrentHashMap> conf = new ArrayList<ConcurrentHashMap>();
    private static JodelConfiguration _instance = null;

    public static JodelConfiguration getInstance() {
        if (_instance == null) {
            _instance = new JodelConfiguration();
        }
        return _instance;
    }

    private JodelConfiguration() {
        conf.add(new ConcurrentHashMap<String, String>());
        conf.add(new ConcurrentHashMap<String, Integer>());
        conf.add(new ConcurrentHashMap<String, Float>());
        conf.add(new ConcurrentHashMap<String, Boolean>());
    }

    public void set(String key, String value) {
        conf.get(STRING).put(key, value);
    }

    public String get(String key) {
        return (String)(conf.get(STRING).get(key));
    }

    public void setInt(String key, int value) {
        Integer v = new Integer(value);
        conf.get(INT).put(key, v);
    }

    public int getInt(String key) {
        Object obj = conf.get(INT).get(key);
        if (obj == null)
            return 0;
        Integer v = (Integer)obj;
        int value = v;
        return value;
    }

    public void setFloat(String key, float value) {
        Float v = new Float(value);
        conf.get(FLOAT).put(key, v);
    }

    public float getFloat(String key) {
        Object obj = conf.get(FLOAT).get(key);
        if (obj == null)
            return 0;
        Float v = (Float)obj;
        float value = v;
        return value;
    }

    public void setBoolean(String key, boolean value) {
        Boolean v = new Boolean(value);
        conf.get(BOOLEAN).put(key, v);
    }

    public boolean getBoolean(String key) {
        Object obj = conf.get(BOOLEAN).get(key);
        if (obj == null)
            return false;
        Boolean v = (Boolean)obj;
        boolean value = v;
        return value;
    }
}
