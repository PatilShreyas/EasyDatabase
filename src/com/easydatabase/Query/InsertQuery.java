/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easydatabase.Query;

import com.easydatabase.Constants.ColumnType;
import java.util.ArrayList;

/**
 *
 * @author Shreyas Patil
 */
public class InsertQuery implements ColumnType{
    private String tableName;
    private static InsertQuery query;
    private Values values;
    
    private InsertQuery(){
        
    }
    public static InsertQuery into(String tableName){
        query = new InsertQuery();
        query.tableName = tableName;
        return query;
    }
    public InsertQuery values(Values values){
        query.values = values; 
        
        return query;
    }
    
  
    public void clear(){
        query.values.clear();
        query.tableName = "";
    }
    
    public Values getValues(){
        return query.values;
    }
    
    public String getTableName(){
        return query.tableName;
    }
}
