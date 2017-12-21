package com.hitechpestcontrol.bills;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    private RecyclerView rclAcc;
    public CustAdapterAccounts ada;
    public ArrayList<AccountsModel> mod = new ArrayList<AccountsModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //View view = inflater.inflate(R.layout.frag2_layout, container, false);
        rclAcc = (RecyclerView) findViewById(R.id.RListAct2);

        getInformation();
        ada = new CustAdapterAccounts(getApplicationContext(), mod);
        ada.notifyDataSetChanged();
        rclAcc.setAdapter(ada);
        rclAcc.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // Get the Intent that started this activity and extract the string
        //Intent intent = getIntent();
        //String message = "hahahaha";

        // Capture the layout's TextView and set the string as its text
        //TextView textView = (TextView) findViewById(R.id.txtAct2);
        //textView.setText(message);
    }

    public void getInformation()
    {
        DatabaseHelp mDbHelper = new DatabaseHelp(getApplicationContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //Cursor cr = db.rawQuery("Select date, name, treatment, amount from MainTable", null);
        Cursor cr = db.rawQuery("SELECT * FROM AccountTable WHERE strftime('%m', DATE) = '01'", null);
        cr.moveToFirst();
        if(cr.getCount()==0)
            Log.d("Fuck! It's ", "empty!");
        else {
            do {
                String str = "Arrivederci!";

                mod.add(new AccountsModel(Integer.parseInt(cr.getString(0)),
                        Integer.parseInt(cr.getString(4)),
                        Integer.parseInt(cr.getString(3)),
                        Integer.parseInt(cr.getString(5)),
                        Integer.parseInt(cr.getString(2)),str));
            } while (cr.moveToNext());
        }
        cr.close();
        db.close();
    }

}
