/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easydatabase.Constants;

/**
 *
 * @author Shreyas Patil
 */
public interface Operation {
    //For ORDER BY
    public static final int ORDER_ASCENDING = 01;
    public static final int ORDER_DESCENDING = 10;
    
    //For Query
    public static final String QUERY_SELECT = "SELECT";
    public static final String QUERY_INSERT = "INSERT";
    public static final String QUERY_UPDATE = "UPDATE";
    public static final String QUERY_DELETE = "DELETE";
}
