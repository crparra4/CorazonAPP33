package com.example.corazonapp

import android.os.StrictMode
import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException


class ConnectioB {
    private val ip = "25.53.177.168:3306";
    private val db = "appcorazon";
    private val username = "aplicacion2";
    private val password ="1234567";

    fun dbConn(): Connection?{
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var conn : Connection? = null
        val connString : String
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance()
            connString = "jdbc:jtds:sqlserver://$ip;databaseName= $db ; user= $username;password=$password;"
            conn = DriverManager.getConnection(connString)
        }catch (ex: SQLException){
            Log.e("error" , ex.message!!)
        }catch (ex1: SQLException){
            Log.e("Error" , ex1.message!!)
        }catch (ex2: SQLException){
            Log.e("Error" , ex2.message!!)
        }
        return conn
    }
}