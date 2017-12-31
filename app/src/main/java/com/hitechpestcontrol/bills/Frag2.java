package com.hitechpestcontrol.bills;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;


public class Frag2 extends Fragment implements SearchView.OnQueryTextListener, View.OnLongClickListener, CustAdapter.OnClickingRow{
    private RecyclerView rcl;
    public CustAdapter ada;
    private Toolbar toolbar;
    private ImageView emptyImage;
    private String[] columns = {"DATE", "NAME", "TREATMENT", "AMOUNT"};
    private ArrayList<Model> mod = new ArrayList<>();
    private String MainQuery = null;
    private Context mContext;
    private boolean is_in_action_mode = false;


    public Frag2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag2_layout, container, false);
        rcl = (RecyclerView) view.findViewById(R.id.RList);
        emptyImage = (ImageView) view.findViewById(R.id.empty);
        View view1 = getActivity().findViewById(R.id.toolbar);
        toolbar = (Toolbar)view1;
        getInformation();
        //cr.moveToNext();
        if(mod.isEmpty()){
            rcl.setVisibility(View.GONE);
            emptyImage.setVisibility(View.VISIBLE);
        }else {
            rcl.setVisibility(View.VISIBLE);
            emptyImage.setVisibility(View.GONE);
            ada = new CustAdapter(getActivity(), mod);
            ada.setClickListner(this);
            ada.notifyDataSetChanged();
            rcl.setAdapter(ada);
            rcl.setItemAnimator(new DefaultItemAnimator());
            rcl.setLayoutManager(new LinearLayoutManager(getActivity()));
            rcl.setHasFixedSize(true);
        }
        return view;
    }


    public void RefreshList(ArrayList<Model> tempMod) {
        mod = tempMod;
        ada.notifyDataSetChanged();
    }

    public ArrayList<Model> getModelList()
    {
        return mod;
    }

    public CustAdapter getAdapter()
    {
        return ada;
    }

    public void setSearchQuery(String query)
    {
        MainQuery = query;
    }

    public void getInformation()
    {
        DatabaseHelp mDbHelper = new DatabaseHelp(getContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        //Cursor cr = db.rawQuery("Select date, name, treatment, amount from MainTable", null);
        Cursor cr = db.rawQuery("SELECT * FROM MainTable ORDER BY date(Date) DESC", null);
        cr.moveToFirst();
        if(cr.getCount()==0)
            Log.d("Fuck! It's ", "empty!");
        else {
            int i = 0;
            do {
                mod.add(new Model(Integer.parseInt(cr.getString(0)),
                        cr.getString(1),
                        cr.getString(2),
                        cr.getString(3),
                        cr.getString(4),
                        Integer.parseInt(cr.getString(5))));
                i++;
            } while (cr.moveToNext());
        }
        cr.close();
        db.close();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        //MenuItem popupItem = menu.findItem(R.id.action_popup);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search");
        searchView.setMaxWidth(Integer.MAX_VALUE);

        super.onCreateOptionsMenu(menu, inflater);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //Log.d("Looking for: ", newText);
        ada.getFilter().filter(newText);
        return true;
    }

    @Override
    public boolean onLongClick(View v) {
        Log.d("From Frag2:", "OnLongClick");
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.main_menu);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            is_in_action_mode = false;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void RowItemLongClicked(View v, int position) {
        if(is_in_action_mode==false) {
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.context_menu);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            is_in_action_mode=true;
        }else{
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.main_menu);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            is_in_action_mode=false;
        }
        //Toast.makeText(mContext, "Fragment 2 implementation", Toast.LENGTH_SHORT).show();
    }
}

