package com.common.mybatis.intercept.method;

import com.common.mybatis.entity.SqlParamInfo;
import com.common.mybatis.entity.TableInfo;
import com.common.mybatis.entity.WarpBoundSqlSqlSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringJoiner;

public abstract class AbstractBoundSql {

    public static Map<Class<?>, JdbcType> jdbcTypeMap=new HashMap<>(16);

    static {
        jdbcTypeMap.put(Long.class,JdbcType.BIGINT);
        jdbcTypeMap.put(Integer.class,JdbcType.INTEGER);
        jdbcTypeMap.put(String.class,JdbcType.VARCHAR);
        jdbcTypeMap.put(LocalDateTime.class,JdbcType.TIME);
        jdbcTypeMap.put(LocalDate.class,JdbcType.DATE);
    }

    public Configuration getConfiguration(Object[] args){
          MappedStatement mappedStatement = (MappedStatement) args[0];
          return mappedStatement.getConfiguration();
     }

     public abstract SqlParamInfo getSqlParamInfo(String mapperClassName, Object[] args) throws  IllegalAccessException;

     public void executeNewSql(String mapperClassName,Object[] args) throws IllegalAccessException {
          SqlParamInfo sqlParamInfo = this.getSqlParamInfo(mapperClassName, args);
          MappedStatement mappedStatement = (MappedStatement)args[0];
          BoundSql oldBoundSql = mappedStatement.getBoundSql(args[1]);
          BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), sqlParamInfo.getSql(),
                  sqlParamInfo.getParameterMappings()==null?oldBoundSql.getParameterMappings():sqlParamInfo.getParameterMappings(),
                  oldBoundSql.getParameterObject());
          // copy原始MappedStatement的各项属性
          MappedStatement.Builder builder = new MappedStatement.Builder(
                  mappedStatement.getConfiguration(), mappedStatement.getId(),
                  new WarpBoundSqlSqlSource(newBoundSql),
                  mappedStatement.getSqlCommandType());

          builder.cache(mappedStatement.getCache()).databaseId(mappedStatement.getDatabaseId())
                  .fetchSize(mappedStatement.getFetchSize())
                  .flushCacheRequired(mappedStatement.isFlushCacheRequired())
                  .keyColumn(StringUtils.join(mappedStatement.getKeyColumns(), ','))
                  .keyGenerator(mappedStatement.getKeyGenerator())
                  .keyProperty(StringUtils.join(mappedStatement.getKeyProperties(), ','))
                  .lang(mappedStatement.getLang()).parameterMap(mappedStatement.getParameterMap())
                  .resource(mappedStatement.getResource()).resultMaps(mappedStatement.getResultMaps())
                  .resultOrdered(mappedStatement.isResultOrdered())
                  .resultSets(StringUtils.join(mappedStatement.getResultSets(), ','))
                  .resultSetType(mappedStatement.getResultSetType()).statementType(mappedStatement.getStatementType())
                  .timeout(mappedStatement.getTimeout()).useCache(mappedStatement.isUseCache());
          MappedStatement newMappedStatement = builder.build();
          // 将新生成的MappedStatement对象替换到参数列表中
          args[0] = newMappedStatement;
     }


     public String getQueryColumns(TableInfo tableInfo){
         StringJoiner stringJoiner=new StringJoiner(",");
         tableInfo.getFieldInfoList().forEach(fieldInfo -> {
             stringJoiner.add(fieldInfo.getColumnName() + " as "+ fieldInfo.getFiledName());
         });
         return stringJoiner.toString();
     }

     public String getIds(Object arg){
          if (arg instanceof MapperMethod.ParamMap){
               MapperMethod.ParamMap map = (MapperMethod.ParamMap) arg;
               Object idsObject = map.get("ids");
               String ids="";
               if (idsObject instanceof HashSet){
                    HashSet idSet=(HashSet) idsObject;
                    for (Object id:idSet){
                         ids=ids+id+",";
                    }
               }
               return ids.substring(0,ids.length()-1);
          }
          return "";
     }
}