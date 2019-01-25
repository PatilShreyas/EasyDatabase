/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easydatabase.Query;

import com.easydatabase.Constants.ColumnType;
import com.easydatabase.Constants.Operation;
import java.util.ArrayList;

/**
 *
 * @author Shreyas Patil
 */
public class UpdateQuery implements ColumnType, Operation{
    
    private String tableName;
    private Condition condition;
    private Values values;
    private static UpdateQuery query = new UpdateQuery();
    
    private UpdateQuery(){   
        values = new Values();
    }
    
    public static UpdateQuery update(String tableName){
        query.tableName = tableName;
        return query;
    }

    public UpdateQuery set(Values values){
        query.values = values;
        return query;
    }
    
    public UpdateQuery where(Condition condition){
        query.condition = condition;
        return query;
    }
    
    public static Condition getCondition(){
        return (new Condition());
    }
    
    public String getTableName(){
        return query.tableName;
    }
    

    
    public String getConditionQuery(){
        if(query.condition != null)
            return query.condition.toString();
        else
            return "";
    }
    
    public Condition getConditionObj(){
        return query.condition;
    }
    
    public Values getValues(){
        return query.values;
    }
}
