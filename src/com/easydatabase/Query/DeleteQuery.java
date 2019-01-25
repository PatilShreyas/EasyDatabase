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
public class DeleteQuery implements ColumnType, Operation{
    
    private ArrayList<String> columnName;
    private String tableName;
    private Condition condition;
    private static DeleteQuery query = new DeleteQuery();
    
    private DeleteQuery(){
        columnName = new ArrayList();
    }
    
    public static DeleteQuery fromTable(String tableName){
        query.tableName = tableName;
        return query;
    }
    
    
    public DeleteQuery where(Condition condition){
        query.condition = condition;
        return query;
    }
    
    public static Condition getCondition(){
        return (new Condition());
    }
    
    
    public String getTableName(){
        return query.tableName;
    }
    
    public String getColumnName(int index){
        return query.columnName.get(index);
    }
    
    public int getTotalColumns(){
        return query.columnName.size();
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
}
