/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easydatabase;

import com.easydatabase.Query.Condition;
import com.easydatabase.Query.DeleteQuery;
import com.easydatabase.Query.InsertQuery;
import com.easydatabase.Query.SelectQuery;
import com.easydatabase.Constants.ColumnType;
import com.easydatabase.Constants.DatabaseDriver;
import com.easydatabase.Query.UpdateQuery;
import com.easydatabase.Query.Values;
import com.easydatabase.event.DatabaseEvent;
import com.easydatabase.event.DatabaseListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shreyas Patil
 */
public class EasyDatabase implements DatabaseDriver{
    
    private String driverName;
    private String host;
    private String username;
    private String password;
    
    private Connection connection;
    
    private DatabaseListener databaseListener;
    
    /**
     *
     * @param driverName
     * @param host
     * @param database
     * @param username
     * @param password
     */
    public EasyDatabase(String driverName, String host, String database, String username, String password){
        try{
            Class.forName(driverName);
            
            String con = "" + getDatabasePath(driverName,host,database);
                       
            connection = DriverManager.getConnection(con, username, password);          
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private String getDatabasePath(String driverName, String host, String database){
        String name = "";
        
        if(driverName.equals(MYSQL)){
            name = "jdbc:mysql://" + host + "/" + database;
        }
        else if(driverName.equals(ORACLE)){
            name = "jdbc:oracle:thin:@" + host + ":"+database;
        }
        else if(driverName.equals(ACCESS)){
            name = "jdbc:ucanaccess://"+host;
        }
        
        return name;
    }
    
    /**
     *
     */
    public void close(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(EasyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @param databaseListener
     */
    public void setDatabaseListener(DatabaseListener databaseListener){
        this.databaseListener = databaseListener;
    }
    
    
    private boolean isListenerRegistered(){
        if(databaseListener != null){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     *
     * @param query
     * @return resultSet
     */
    public ResultSet select(SelectQuery query){
        String sqlQuery = "select ";
        ResultSet resultSet = null;
        Condition condition = null;
        if(query.isAll()){
            sqlQuery = sqlQuery + "* from "+query.getTableName();
        }else{
            for(int i=0; i<query.getTotalColumns(); i++){
                if(i==0){
                    sqlQuery = sqlQuery + ""+query.getColumnName(i);
                }else{
                    sqlQuery = sqlQuery + ","+query.getColumnName(i);
                }
            }
            sqlQuery = sqlQuery + " from "+query.getTableName();   
        } 
        condition = query.getConditionObj();
        if(condition != null){
            sqlQuery = sqlQuery + " " + condition.getQuery();
        }
        try{
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            if(condition != null){
                if(condition.getQuery().trim().length() != 0){

                    for(int i=0 ; i<condition.getParameterSize(); i++){
                        int index = i+1;

                        switch(condition.getType(i)){

                        case ColumnType.INT : statement.setInt(index, Integer.parseInt(condition.getValue(i).toString())); break;
                        case ColumnType.SHORT: statement.setShort(index, Short.parseShort(condition.getValue(i).toString())); break;
                        case ColumnType.FLOAT : statement.setFloat(index, Float.parseFloat(condition.getValue(i).toString())); break;
                        case ColumnType.DOUBLE : statement.setDouble(index, Double.parseDouble(condition.getValue(i).toString())); break;
                        case ColumnType.LONG : statement.setLong(index, Long.parseLong(condition.getValue(i).toString())); break;
                        case ColumnType.STRING : statement.setString(index,condition.getValue(i).toString()); break;
                        case ColumnType.DATE : statement.setDate(index, (Date) condition.getValue(i)); break;
                        case ColumnType.OBJECT : statement.setObject(index, condition.getValue(i)); break;
                        case ColumnType.TIME : statement.setTime(index, (Time) condition.getValue(i)); break;
                        case ColumnType.URL : statement.setURL(index, (URL) condition.getValue(i)); break;

                        default: throw new Exception("Exception : Type Not Found "+condition.getType(i));
                        }
                    }
                }
            }
            resultSet = statement.executeQuery();
            
             if(databaseListener != null){
                    databaseListener.onSuccess(new DatabaseEvent("Query Executed Successfully","SELECT"));  
             }
            
             
            }catch(SQLException e){
                if(databaseListener != null){
                    databaseListener.onFailed(new DatabaseEvent(e.getMessage(),"SELECT",e));    
                }
                          
            } catch (Exception ex) {
                 if(databaseListener != null){
                    databaseListener.onFailed(new DatabaseEvent(ex.getMessage(),"SELECT",ex));    
                }
            }    
       
        
        
        
        return resultSet;
    }
    
    /**
     *
     * @param query
     */
    public void delete(DeleteQuery query){
        String sqlQuery = "delete from "+query.getTableName();
        ResultSet resultSet = null;
        Condition condition = null;
        
        condition = query.getConditionObj();
        if(condition != null){
            sqlQuery = sqlQuery + " " + condition.getQuery();
        }
        try{
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            if(condition != null){
                if(condition.getQuery().trim().length() != 0){

                    for(int i=0 ; i<condition.getParameterSize(); i++){
                        int index = i+1;

                        switch(condition.getType(i)){

                        case ColumnType.INT : statement.setInt(index, Integer.parseInt(condition.getValue(i).toString())); break;
                        case ColumnType.SHORT: statement.setShort(index, Short.parseShort(condition.getValue(i).toString())); break;
                        case ColumnType.FLOAT : statement.setFloat(index, Float.parseFloat(condition.getValue(i).toString())); break;
                        case ColumnType.DOUBLE : statement.setDouble(index, Double.parseDouble(condition.getValue(i).toString())); break;
                        case ColumnType.LONG : statement.setLong(index, Long.parseLong(condition.getValue(i).toString())); break;
                        case ColumnType.STRING : statement.setString(index,condition.getValue(i).toString()); break;
                        case ColumnType.DATE : statement.setDate(index, (Date) condition.getValue(i)); break;
                        case ColumnType.OBJECT : statement.setObject(index, condition.getValue(i)); break;
                        case ColumnType.TIME : statement.setTime(index, (Time) condition.getValue(i)); break;
                        case ColumnType.URL : statement.setURL(index, (URL) condition.getValue(i)); break;

                        default: throw new Exception("Exception : Type Not Found "+condition.getType(i));
                        }
                    }
                }
            }
            int result = statement.executeUpdate();
            
             if(databaseListener != null){
                    databaseListener.onSuccess(new DatabaseEvent("Query Executed Successfully. "+result+" rows affected","DELETE"));  
             }
            
             
            }catch(SQLException e){
                if(databaseListener != null){
                    databaseListener.onFailed(new DatabaseEvent(e.getMessage(),"DELETE",e));    
                }
                          
            } catch (Exception ex) {
                 if(databaseListener != null){
                    databaseListener.onFailed(new DatabaseEvent(ex.getMessage(),"DELETE",ex));    
                }
            }
    }
    
    /**
     *
     * @param query
     */
    public void insert(InsertQuery query){
        Values data = query.getValues();
        if(data == null){
            databaseListener.onFailed(new DatabaseEvent("VALUES can't be null","INSERT"));
            try {
                throw new NullPointerException("Values should't be null");
            } catch (NullPointerException ex) {
                Logger.getLogger(EasyDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        String sqlQuery = "insert into "+query.getTableName()+" (";
        for(int i=0; i<data.getSize(); i++){
            if(i==0)
                sqlQuery = sqlQuery + data.getName(i);
            else
                sqlQuery = sqlQuery + ","+data.getName(i);
        }
        sqlQuery = sqlQuery + ")" + " values(";
        for(int i=0; i<data.getSize(); i++){
            if(i==0)
                sqlQuery = sqlQuery + "?";
            else
                sqlQuery = sqlQuery + ",?";
        }
        
        sqlQuery = sqlQuery + ")";
            
        try{
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            
            for(int i=0; i<data.getSize(); i++){
                int index = i+1;
                
                switch(data.getType(i)){
                         
                    case ColumnType.INT : statement.setInt(index, Integer.parseInt(data.getValue(i).toString())); break;
                    case ColumnType.SHORT: statement.setShort(index, Short.parseShort(data.getValue(i).toString())); break;
                    case ColumnType.FLOAT : statement.setFloat(index, Float.parseFloat(data.getValue(i).toString())); break;
                    case ColumnType.DOUBLE : statement.setDouble(index, Double.parseDouble(data.getValue(i).toString())); break;
                    case ColumnType.LONG : statement.setLong(index, Long.parseLong(data.getValue(i).toString())); break;
                    case ColumnType.STRING : statement.setString(index,data.getValue(i).toString()); break;
                    case ColumnType.DATE : statement.setDate(index, (Date) data.getValue(i)); break;
                    case ColumnType.OBJECT : statement.setObject(index, data.getValue(i)); break;
                    case ColumnType.TIME : statement.setTime(index, (Time) data.getValue(i)); break;
                    case ColumnType.URL : statement.setURL(index, (URL) data.getValue(i)); break;
                    
                   default: throw new Exception("Exception : Type Not Found "+data.getType(i));
                 
                }
            }
            if(statement.executeUpdate() > 0){
                if(isListenerRegistered()){
                    databaseListener.onSuccess(new DatabaseEvent("Insertion Successful","INSERT"));
                }
            }else{
                if(isListenerRegistered()){
                    databaseListener.onFailed(new DatabaseEvent("Insertion Not Successful","INSERT"));
                }
            }
        }catch(Exception e){
            if(isListenerRegistered()){
                databaseListener.onFailed(new DatabaseEvent(e.getMessage(),"INSERT"));
            }
            e.printStackTrace();
            
        } 
    }
    
    /**
     *
     * @param query
     */
    public void update(UpdateQuery query){
        String sqlQuery = "update "+query.getTableName() + " set ";
        Condition condition = null;
        Values values = query.getValues();
        
        ArrayList valueList = new ArrayList();
        ArrayList<String> typeList = new ArrayList();
        
        int valLength;
        
        if(values.getSize() != 0){
            valLength = values.getSize();
            for(int i=0; i<values.getSize(); i++){
                if(i==0){
                    sqlQuery = sqlQuery + "" + values.getName(i) + " = ?";
                }else{
                    sqlQuery = sqlQuery + ", " + values.getName(i) + " = ?";
                }
                valueList.add(values.getValue(i));
                typeList.add(values.getType(i));
            }                 
        }
        else{
            databaseListener.onFailed(new DatabaseEvent("VALUES can't be null","UPDATE")); 
            try {
                throw new NullPointerException("Values should't be null");
            } catch (NullPointerException ex) {
                Logger.getLogger(EasyDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
            
        condition = query.getConditionObj();
        int conValLength;
        if(condition != null){
            conValLength = condition.getParameterSize();
            sqlQuery = sqlQuery + " " + condition.getQuery();
            
            for(int i=0; i<conValLength; i++){
                valueList.add(condition.getValue(i));
                typeList.add(condition.getType(i));
            }
        }      
        
        try{
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
  
            for(int i=0 ; i<valueList.size(); i++){
                int index = i+1;

                switch(typeList.get(i)){

                case ColumnType.INT : statement.setInt(index, Integer.parseInt(valueList.get(i).toString())); break;
                case ColumnType.SHORT: statement.setShort(index, Short.parseShort(valueList.get(i).toString())); break;
                case ColumnType.FLOAT : statement.setFloat(index, Float.parseFloat(valueList.get(i).toString())); break;
                case ColumnType.DOUBLE : statement.setDouble(index, Double.parseDouble(valueList.get(i).toString())); break;
                case ColumnType.LONG : statement.setLong(index, Long.parseLong(valueList.get(i).toString())); break;
                case ColumnType.STRING : statement.setString(index,valueList.get(i).toString()); break;
                case ColumnType.DATE : statement.setDate(index, (Date) valueList.get(i)); break;
                case ColumnType.OBJECT : statement.setObject(index, valueList.get(i)); break;
                case ColumnType.TIME : statement.setTime(index, (Time) valueList.get(i)); break;
                case ColumnType.URL : statement.setURL(index, (URL) valueList.get(i)); break;

                default: throw new Exception("Exception : Type Not Found "+condition.getType(i));
                }
            }
           
            int result = statement.executeUpdate();
            
             if(databaseListener != null){
                    databaseListener.onSuccess(new DatabaseEvent("Query Executed Successfully. "+result+" rows affected","UPDATE"));  
             }
            
             
            }catch (Exception ex) {
                 if(databaseListener != null){
                    databaseListener.onFailed(new DatabaseEvent(ex.getMessage(),"UPDATE",ex));    
                }
            }        
    }

    /**
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    public ResultSet executeQuery(String sql) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        return resultSet;
    }
    
    /**
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    public int executeUpdate(String sql) throws SQLException{
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(sql);
        
        return result;
    }
    
    /**
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    public boolean execute(String sql) throws SQLException{
        Statement statement = connection.createStatement();
        boolean result = statement.execute(sql);
        
        return result;
    }
    
    /**
     *
     * @return Statement
     * @throws SQLException
     */
    public Statement getStatement() throws SQLException{
        return (connection.createStatement());
    }
    
    /**
     *
     * @param sql This returns PreparedStatement form given SQL String
     * @return
     * @throws SQLException
     */
    public PreparedStatement getPreparedStatement(String sql) throws SQLException{
        return (connection.prepareStatement(sql));
    }
}
