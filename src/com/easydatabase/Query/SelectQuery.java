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
public class SelectQuery implements ColumnType, Operation{
    
    private ArrayList<String> columnName;
    private String tableName;
    private boolean isAll = false;
    private Condition condition;
    private static SelectQuery query = new SelectQuery();
    
    private SelectQuery(){
        columnName = new ArrayList();
    }
    
    public SelectQuery fromTable(String tableName){
        query.tableName = tableName;
        return query;
    }
    
    public static SelectQuery select(String...columnName){
        if(columnName.length == 0){
            query.isAll = true;           
        }else{
            for(int i=0; i<columnName.length; i++){
                query.columnName.add(columnName[i]);
            }
        }
        return query;
    }
    
    public SelectQuery where(Condition condition){
        query.condition = condition;
        return query;
    }
    
    public static Condition getCondition(){
        return (new Condition());
    }
    
    public boolean isAll(){
        return query.isAll;
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
