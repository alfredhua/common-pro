package com.common.mybatis.intercept.method;

import com.common.mybatis.entity.SqlParamInfo;
import com.common.mybatis.entity.TableInfo;
import com.common.mybatis.util.MapperUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

public class QueryByIdAbstractBoundSql extends AbstractBoundSql{

    @Override
    public SqlParamInfo getSqlParamInfo(String mapperClassName, Object[] args) { if (args.length>1) {
            TableInfo tableInfo = MapperUtils.getTableInfo(mapperClassName);
            String s = new SQL().FROM(tableInfo.getTableName()) .SELECT(getQueryColumns(tableInfo)).WHERE("id=?").toString();
            List<ParameterMapping> parameterMappingList=new ArrayList<>();
            parameterMappingList.add(new ParameterMapping.Builder(getConfiguration(args),"id",Long.class).build());
            return new SqlParamInfo(s,parameterMappingList);
        }
        throw new RuntimeException("queryById error");
    }
}
