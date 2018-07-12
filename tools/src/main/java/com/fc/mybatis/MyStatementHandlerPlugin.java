package com.fc.mybatis;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

/**
 * @author fangcong on 2018/7/10.
 */
@Intercepts({
    @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})
})
public class MyStatementHandlerPlugin implements Interceptor {

    private Integer limit;

    private String dbType;

    private static final String TABLE_ALIAS_NAME = "temp_table_name_x";

    private static final String SQL_NAME = "delegate.boundSql.sql";

    /**
     * 限制查询返回最多返回50条数据
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);

        // 分离代理对象
        while (metaObject.hasGetter("h")) {
            Object obj = metaObject.getValue("h");
            metaObject = SystemMetaObject.forObject(obj);
        }

        // 分离最后一个对象的代理目标类
        while (metaObject.hasGetter("target")) {
            Object obj = metaObject.getValue("target");
            metaObject = SystemMetaObject.forObject(obj);
        }

        // 取出即将执行的SQL
        String sql = (String)metaObject.getValue(SQL_NAME);
        // 判断是否是mysql库且是否被插件重写过
        String limitSql;
        if ("mysql".equals(this.dbType) && !sql.contains(TABLE_ALIAS_NAME)) {
            sql = sql.trim();
            limitSql = "select * from (" + sql + ")" + TABLE_ALIAS_NAME + " limit " + limit;
            System.err.println("limitSql:" + limitSql);
            metaObject.setValue(SQL_NAME, limitSql);
        }
        // 进入下一责任链
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String limit = properties.getProperty("limit", "50");
        this.limit = Integer.parseInt(limit);
        this.dbType = properties.getProperty("dbType", "mysql");
    }
}
