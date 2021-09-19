package com.example.tada;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DB extends SQLiteOpenHelper {

    //database table and column information
    static String dbName="tadaDB.db";
    static String tabName="tadaRec";
    static String col1="date", col2="empName", col3="travelCost",col4="lunchCost", col5="instrumentCost", col6="paidOrUnpaid";
    public static Context con;

    SQLiteDatabase db= this.getWritableDatabase();

    public DB(Context context) {
        super(context,dbName,null,1);
        this.con=context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String q="CREATE TABLE "+tabName+" ("+col1+" varchar(10),"+col2+" varchar(30),"+col3+" float,"+col4+" float,"+col5+" float,"+col6+" varchar(7));";
        Log.v("sql",q);

        sqLiteDatabase.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String q="DROP TABLE IF EXISTS "+tabName;
        Log.v("sql1",q);

        sqLiteDatabase.execSQL(q);
        onCreate(sqLiteDatabase);
    }


    //---------------Define Function

    //insert rec into Database
    public String insertRecIntoDB(String date, String name, String travel, String lunch, String instru, String paid) {
        String q1="INSERT INTO "+tabName+" ("+col1+", "+col2+", "+col3+", "+col4+", "+col5+", "+col6+") VALUES "+" ('"+date+"', '"+name+"', "+travel+", "+lunch+", "+instru+", '"+paid+"');";
        Log.v("sql1",q1);

        try {
            db.execSQL(q1);
            return "Data Recorded";
        }
        catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }

    //pick Name Record
    public Cursor getEmpNameFromDB(){
        Cursor cu=null;
        String q1="SELECT "+col2+" FROM "+ tabName;
        Log.v("sql1",q1);

        try{
            cu=db.rawQuery(q1,null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cu;
    }

    //pick all record
    public Cursor getHistoryFromDB(){
        Cursor cu=null;
        String q1="SELECT * FROM "+ tabName;
        Log.v("sql1",q1);

        try{
            cu=db.rawQuery(q1,null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cu;
    }

}

