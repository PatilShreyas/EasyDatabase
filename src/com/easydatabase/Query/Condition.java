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
public class Condition implements Operation, ColumnType{ 
    
    private String conQuery = "where";
    private int c=0;
    private int params;
    
    private ArrayList<Object> conValue;
    private ArrayList<String> conType;
    
    Condition(){
        conValue = new ArrayList();
        conType = new ArrayList();
    }
    
    public Condition equalTo(String columnName, Object value, String type){
        conQuery = conQuery + " " + columnName + " = ?";
        params++;
        
        conValue.add(value);
        conType.add(type);
        
        return this;
    }
    
    public Condition greaterThan(String columnName, Object value, String type){
        conQuery = conQuery + " " + columnName + " > ?";
        params++;
        
        conValue.add(value);
        conType.add(type);
        
        return this;
    }
    
    public Condition smallerThan(String columnName, Object value, String type){
        conQuery = conQuery + " " + columnName + " < ?";
        params++;
        
        conValue.add(value);
        conType.add(type);
        
        return this;
    }
    
    public Condition greaterThanOrEquals(String columnName, Object value, String type){
        conQuery = conQuery + " " + columnName + " >= ?";
        params++;
        
        conValue.add(value);
        conType.add(type);
        
        return this;
    }
    
    public Condition smallerThanOrEquals(String columnName, Object value, String type){
        conQuery = conQuery + " " + columnName + " <= ?";
        params++;
        
        conValue.add(value);
        conType.add(type);
        
        return this;
    }
    
    public Condition like(String columnName, Object value){
        conQuery = conQuery + " " + columnName + " LIKE ?";
        params++;
        
        conValue.add(value);
        conType.add(STRING);
        
        return this;
    }
    
    public Condition orderBy(String columnName, int order){
       String orderBy = " order by " + columnName + " ";
       
       if(order == Operation.ORDER_DESCENDING)
           orderBy = orderBy + "desc"; 
       
       conQuery = conQuery + " " + orderBy;
       
       return this;
    }
    
    public Condition AND(){
        conQuery = conQuery + " AND ";
        
        return this;
    }
    
     public Condition OR(){
        conQuery = conQuery + " OR ";
        
        return this;
    }

    @Override
    public String toString() {
        if(conQuery.equals("where")){
            return "";
        }else{
            return conQuery; 
        }
    }
    
    public String getQuery(){
        return conQuery;
    }
    
    public void setQuery(String sql){
        conQuery = sql;
    }
    
    public void clear(){
        conQuery = "where";      
        conType.clear();
        conValue.clear();
    }
    
    public String getType(int index){
        return conType.get(index);
    }
    
    public Object getValue(int index){
        return conValue.get(index);
    }
    
    public int getParameterSize(){
        return params;
    }
     
    public static Condition getCondition(){
        return (new Condition());
    }
       
}
