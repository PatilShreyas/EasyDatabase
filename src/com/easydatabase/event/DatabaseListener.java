/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easydatabase.event;

/**
 *
 * @author Shreyas Patil
 */
public interface DatabaseListener {
    public void onSuccess(DatabaseEvent e);
    public void onFailed(DatabaseEvent e);
}
