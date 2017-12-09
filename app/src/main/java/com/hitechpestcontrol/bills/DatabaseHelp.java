package com.hitechpestcontrol.bills;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelp extends SQLiteOpenHelper {

    private static final String name = "HPC!!";
    private static final int version = 5;

    public DatabaseHelp(Context context)
    {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        Log.d("Create Table!","");
        String sqlMain= "CREATE table MainTable("+
                "BILL integer primary key,"+
                "DATE text,"+
                "NAME text,"+
                "TREATMENT text,"+
                "CONTACT integer,"+
                "AMOUNT integer)";

        String sqlAccounts = "CREATE table AccountTable("+
                "BILL integer primary key,"+
                "DATE text,"+
                "AMOUNT integer,"+
                "CHEMICAL integer,"+
                "TRAVEL integer,"+
                "PROFIT integer)";

        db.execSQL(sqlMain);
        db.execSQL(sqlAccounts);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_ENTRIES1 = "DROP table MainTable";
        String SQL_DELETE_ENTRIES2 = "DROP table AccountTable";
        Log.d("Upgrade!","");
        db.execSQL(SQL_DELETE_ENTRIES1);
        db.execSQL(SQL_DELETE_ENTRIES2);
        onCreate(db);

    }


    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
