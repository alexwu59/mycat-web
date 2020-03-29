package io.mycat.dao.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 更灵活的通用型分页查询，执行Native SQL 动态SQL 查询条件由 DynaQueryCondHanlder来负责生成
 * 
 * @author Leader us
 */
public class PowerNativeSQLPagedQuery extends PagedQuery {
    protected static Logger log = LoggerFactory.getLogger(PowerNativeSQLPagedQuery.class);

    /**
     * SQL部分，通常是不带动态参数的完整SQL，以 where 1==1结束
     */
    private String sql;

    /**
     * 负责生成动态查询条件的Handler
     */
    private DynaQueryCondHanlder condHandler;

    public PowerNativeSQLPagedQuery() {

    }

    /**
     * 设置 SQL部分，通常是不带动态参数的完整SQL，以 where 1==1结束
     * 
     * @param sql
     * @return
     */
    public PowerNativeSQLPagedQuery withSQL(String sql) {
        this.sql = sql;
        return this;
    }

    public PowerNativeSQLPagedQuery withConditionHandler(DynaQueryCondHanlder handler) {
        this.condHandler = handler;
        return this;
    }

    /**
     * 生成Native SQL,可配合参数Map执行
     * 
     * @return SQL
     */
    public String buildSQLNoPage() {
        String condition = condHandler.genCondtions(this.queryParams);
        if (condition != null && condition.length() > 0) {
            return sql + ' ' + condition;
        }
        return sql;

    }

}