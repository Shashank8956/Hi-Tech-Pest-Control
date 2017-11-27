package com.hitechpestcontrol.bills;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelp extends SQLiteOpenHelper {

    private static final String name = "HPC!!";
    private static final int version = 1;

    public DatabaseHelp(Context context)
    {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sqlMain= "CREATE table MainTable("+
                "BILL integer primary key,"+
                "DATE integer,"+
                "NAME text,"+
                "TREATMENT text,"+
                "CONTACT integer,"+
                "AMOUNT integer)";

        String sqlAccounts = "CREATE table AccountTable("+
                "BILL integer primary key,"+
                "DATE text primary key"+
                "AMOUNT integer,"+
                "CHEMICAL integer,"+
                "TRAVEL integer,"+
                "PROFIT integer)";

        db.execSQL(sqlMain);
        db.execSQL(sqlAccounts);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_ENTRIES = "DROP table MainTable";
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }


    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
