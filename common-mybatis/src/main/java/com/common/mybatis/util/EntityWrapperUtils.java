package com.common.mybatis.util;

import com.common.mybatis.entity.Condition;
import com.common.mybatis.enums.ConditionEnum;
import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;
import java.util.Map;

public class EntityWrapperUtils {


    private static final Map<ConditionEnum,String> map=new HashMap<>(8);

    static {
        map.put(ConditionEnum.eq,"=");
        map.put(ConditionEnum.le,"<=");
        map.put(ConditionEnum.lt,"<");
        map.put(ConditionEnum.gt,">");
        map.put(ConditionEnum.ge,">=");
    }

    public static SQL getWhereSql(Condition condition, SQL sql){
        if (map.containsKey(condition.getCondition())){
            return sql.WHERE(condition.getColumn()+" "+map.get(condition.getCondition())+" ? ");
        }
        if (condition.getCondition().equals(ConditionEnum.like)){
            return sql.WHERE(condition.getColumn()+" like CONCAT('%',?,'%') ");
        }
        return sql;
    }

}
