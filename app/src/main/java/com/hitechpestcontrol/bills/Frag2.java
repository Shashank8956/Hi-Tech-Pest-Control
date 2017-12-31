package com.hitechpestcontrol.bills;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
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
import android.widget.RelativeLayout;
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
    private int selectedPosition = -1;
    private RelativeLayout oldRel=null;
    private boolean is_in_action_mode = false;
    private View view;


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

        view = inflater.inflate(R.layout.frag2_layout, container, false);
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
        switch (item.getItemId()) {
            case android.R.id.home :    toolbar.getMenu().clear();
                                        toolbar.inflateMenu(R.menu.main_menu);
                                        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                                        selectedPosition = -1;
                                        if(oldRel!=null)
                                            oldRel.setBackgroundColor(Color.TRANSPARENT);
                                        is_in_action_mode = false;
                                        break;
            case R.id.context_delete :  if(selectedPosition!=-1)
                                            deleteSelectedItem(mod.get(selectedPosition));
                                        break;

            case R.id.context_edit :    if(selectedPosition!=-1)
                                            editSelectedItem(mod.get(selectedPosition));
                                        break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void RowItemLongClicked(View v, int position, RelativeLayout rel) {

        if(is_in_action_mode==false) {

            rel.setBackgroundColor(Color.parseColor("#c1c1c1"));
            oldRel = rel;
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.context_menu);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            selectedPosition = position;
            is_in_action_mode=true;
        }else{
            if(oldRel!=null) {
                oldRel.setBackgroundColor(Color.TRANSPARENT);
                oldRel = null;
            }
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.main_menu);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            selectedPosition = -1;
            is_in_action_mode=false;
        }
        //Toast.makeText(mContext, "Fragment 2 implementation", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void RowItemClicked(View v, int position, RelativeLayout rel) {
        if (oldRel != null) {
            oldRel.setBackgroundColor(Color.TRANSPARENT);
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.main_menu);
            is_in_action_mode = false;
            selectedPosition = -1;
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            oldRel = null;
        } else {
            android.app.FragmentManager manager = getActivity().getFragmentManager();

            Bundle args = new Bundle();
            args.putInt("_BILL", mod.get(position).getBill());
            args.putString("_DATE", mod.get(position).getDate());
            args.putString("_NAME", mod.get(position).getName());
            args.putString("_TREATMENT", mod.get(position).getTreat());
            args.putString("_CONTACT", mod.get(position).getContact());
            args.putInt("_AMOUNT", mod.get(position).getAmount());
            ListDetailsDialog newFragment = new ListDetailsDialog();
            newFragment.setArguments(args);
            newFragment.show(getActivity().getFragmentManager(), "TAG");
            Toast.makeText(mContext, "Item Clicked!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteSelectedItem(final Model mo){
        String content = "Do you really want to delete the selected item?";
        String title = "Delete record";
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(content).setTitle(title);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                mod.remove(selectedPosition);
                ada.notifyDataSetChanged();

                DatabaseHelp mDbHelper = new DatabaseHelp(getContext());
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                db.execSQL("DELETE from MainTable WHERE Bill = '"+mo.getBill()+"'");
                db.execSQL("DELETE from AccountTable WHERE Bill = '"+mo.getBill()+"'");
                db.close();

                oldRel.setBackgroundColor(Color.TRANSPARENT);
                oldRel = null;
                toolbar.getMenu().clear();
                toolbar.inflateMenu(R.menu.main_menu);
                is_in_action_mode = false;
                selectedPosition = -1;
                ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

                Snackbar snackbar = Snackbar
                        .make(view, "Message is deleted", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                /*DatabaseHelp mDbHelper = new DatabaseHelp(getContext());
                                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                                Cursor cr = db.rawQuery("INSERT into MainTable", null);
                                cr.close();
                                db.close();*/

                                Snackbar snackbar1 = Snackbar.make(view, "Message is restored!", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });

                snackbar.show();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                oldRel.setBackgroundColor(Color.TRANSPARENT);
                oldRel = null;
                toolbar.getMenu().clear();
                toolbar.inflateMenu(R.menu.main_menu);
                is_in_action_mode = false;
                selectedPosition = -1;
                ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        Toast.makeText(mContext, mo.getName()+ "Item Deleted!", Toast.LENGTH_SHORT).show();
    }

    public void editSelectedItem(Model mo){
        Toast.makeText(mContext, mo.getName()+ "Item edited!", Toast.LENGTH_SHORT).show();
    }

}

