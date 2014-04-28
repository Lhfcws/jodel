package me.lhfcws.commons.jodel.model;

import java.util.HashMap;

/**
 * @author lhfcws
 * @time 14-4-27.
 */
public class AdvMap<K, V> extends HashMap<K, V> {
    public void setDefault(K key, V value) {
        if (!containsKey(key))
            put(key, value);
    }
}
