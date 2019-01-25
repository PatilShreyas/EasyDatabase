/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easydatabase.Query;

import com.easydatabase.*;
import com.easydatabase.Constants.ColumnType;
import java.util.ArrayList;

/**
 *
 * @author Shreyas Patil
 */
public class Values implements ColumnType{
    
    private ArrayList<String> nameList;
    private ArrayList<Object> valueList;
    private ArrayList<String> typeList;
    private int size = 0;
    
    public Values(){
        nameList = new ArrayList();
        valueList = new ArrayList();
        typeList = new ArrayList();
    }
    
    public void put(String name, Object value, String type){
        nameList.add(name);
        valueList.add(value);
        typeList.add(type);
        
        size++;
    }
    
    public int getSize(){
        return size;
    }
    
    public String getName(int index){
        return nameList.get(index);
    }
    
    public Object getValue(int index){
        return valueList.get(index);
    }
    
    public String getType(int index){
        return typeList.get(index);
    }
    
    public void clear(){
        nameList.clear();
        valueList.clear();
        typeList.clear();
    }
}
