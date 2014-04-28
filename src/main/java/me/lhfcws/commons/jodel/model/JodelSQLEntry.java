package me.lhfcws.commons.jodel.model;

import java.util.*;

public class JodelSQLEntry extends AdvMap<String, Object>{
    private List<String> __columnNames = new LinkedList<String>();

    public Integer getInt(String key) {
        Object result = get(key);
        if (null == result)
            return null;
        try {
            return (Integer)result;
        } catch (Exception e) {
            return null;
        }
    }

    public Double getDouble(String key) {
        Object result = get(key);
        if (null == result)
            return null;
        try {
            return (Double)result;
        } catch (Exception e) {
            return null;
        }
    }

    public String getString(String key) {
        return (String)get(key);
    }

    public List<String> columnNames() {
        return __columnNames;
    }

    public void addColumn(String column) {
        __columnNames.add(column);
    }

    public void setColumnNames(List<String> columnNames) {
        this.__columnNames.clear();
        this.__columnNames.addAll(columnNames);
    }
}
