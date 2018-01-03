package com.hitechpestcontrol.bills;

import android.app.DialogFragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ConfirmDialog extends DialogFragment implements View.OnClickListener {
    private EditText ed;
    private Button btnOk, btnCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.confirm_dialog, null);
        ed = (EditText)view.findViewById(R.id.ConfirmDialogContent);
        btnOk = (Button)view.findViewById(R.id.ConfirmDialogOk);
        btnCancel = (Button)view.findViewById(R.id.ConfirmDialogCancel);
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().setTitle("Clear All!");
        setCancelable(false);
        return view;
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.ConfirmDialogCancel)
            getDialog().dismiss();
        else if(v.getId()==R.id.ConfirmDialogOk){
            String str =null;
            str = ed.getText().toString();
            Log.d("ConfirmDialog: ", str);
            if(str.equals("[AoW]Spongebob[RWW] wants all of it deleted.")){
                DatabaseHelp mDbHelper = new DatabaseHelp(getActivity());
                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                db.execSQL("DROP Table MainTable");
                db.execSQL("DROP Table AccountsTable");

                mDbHelper.onCreate(db);

                db.close();
            } else{
                Toast.makeText(getActivity(), "Invalid pass!", Toast.LENGTH_LONG).show();
            }
            getDialog().dismiss();
        }
    }
}
