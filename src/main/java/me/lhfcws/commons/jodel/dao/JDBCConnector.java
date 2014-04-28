package me.lhfcws.commons.jodel.dao;

import me.lhfcws.commons.jodel.conf.JodelConsts;

/**
 * @author lhfcws
 * @time 14-4-28.
 */
public class JDBCConnector extends Connector {

    @Override
    protected String combineArgs() {
        return String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf-8|%s|%s",
                params.get(JodelConsts.PARAM_HOST), params.get(JodelConsts.PARAM_PORT),
                params.get(JodelConsts.PARAM_SELECT_DB), params.get(JodelConsts.PARAM_USERNAME),
                params.get(JodelConsts.PARAM_PASSWORD));
    }
}
