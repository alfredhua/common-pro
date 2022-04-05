package com.common.mybatis.intercept.method;

import com.common.mybatis.entity.SqlParamInfo;
import com.common.mybatis.entity.TableInfo;
import com.common.mybatis.util.MapperUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.jdbc.SQL;

public class ListByIdsAbstractBoundSql extends AbstractBoundSql{


    @Override
    public SqlParamInfo getSqlParamInfo(String mapperClassName, Object[] args) {
        if (args.length>1) {
            Object arg = args[1];
            if (ObjectUtils.isEmpty(arg)){
                throw new RuntimeException("deleteByIds the params is null");
            }
            TableInfo tableInfo = MapperUtils.getTableInfo(mapperClassName);
            SQL sql = new SQL().FROM(tableInfo.getTableName())
                    .SELECT(getQueryColumns(tableInfo))
                    .WHERE("id in ("+getIds(arg)+")");
            return new SqlParamInfo(sql.toString(),null);
        }
        throw new RuntimeException("listByIds params error");
    }
}