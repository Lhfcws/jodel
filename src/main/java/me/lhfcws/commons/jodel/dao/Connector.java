package me.lhfcws.commons.jodel.dao;

import me.lhfcws.commons.jodel.conf.JodelConsts;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lhfcws
 * @time 14-2-18.
 */
public abstract class Connector {
    protected ConcurrentHashMap<String, String> params = new ConcurrentHashMap<String, String>();
    protected String cacheArgs = null;
    protected boolean isUpdated = true;

    public Connector() {
    }

    public void setHost(String hostname) {
        params.put(JodelConsts.PARAM_HOST, hostname);
        isUpdated = true;
    }

    public void setPort(String port) {
        isUpdated = true;
        params.put(JodelConsts.PARAM_PORT, port);
    }

    public void setPort(int port) {
        String portStr = Integer.toString(port);
        isUpdated = true;
        params.put(JodelConsts.PARAM_PORT, portStr);
    }

    public void setUsername(String username) {
        isUpdated = true;
        params.put(JodelConsts.PARAM_USERNAME, username);
    }

    public void setPassword(String password) {
        isUpdated = true;
        params.put(JodelConsts.PARAM_PASSWORD, password);
    }

    protected abstract String combineArgs();

    public String args() {
        if (isUpdated) {
            String args = combineArgs();
            cacheArgs = args;
            isUpdated = false;
        }
        return cacheArgs;
    }
}
