package me.lhfcws.commons.jodel;

import me.lhfcws.commons.jodel.conf.JodelConsts;

/**
 * @author lhfcws
 * @time 14-4-8.
 */
class SQLQueryComponents {
    public String tablename = null;
    public String where = null;
    public String field = null;
    public String operation = null;
    public String distinct = null;
    public String having = null;
    public String groupby = null;
    public String orderby = null;
    public String limit = null;
    public boolean isCount = false;

    public void clear() {
        where = null;
        field = null;
        operation = null;
        distinct = null;
        having = null;
        groupby = null;
        orderby = null;
        limit = null;
        isCount = false;
    }

    public boolean valid() {
        boolean pre_condition = operation != null && tablename != null;
        boolean field_condition = field != null && !operation.equals(JodelConsts.DELETE);
        return (
                pre_condition && field_condition
            );
    }

    public String toSql() {
        if (!valid())
            return null;

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        if (distinct != null)
            sb.append("DISTINCT ");

        if (isCount)
            sb.append("COUNT(");
        if (field != null)
            sb.append(field);
        else
            sb.append("*");
        if (isCount)
            sb.append(")");

        if (where != null)
            sb.append(where);
        if (limit != null)
            sb.append("LIMIT ").append(limit);

        return sb.toString();
    }
}
