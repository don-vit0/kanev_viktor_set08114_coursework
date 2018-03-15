package uk.ac.napier.fitassistant;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment {
    ArrayList<String> shoppingList = null;
    ArrayAdapter<String> adapter = null;
    ListView list = null;

    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_shopp, container, false);
        shoppingList = getArrayVal(getContext());
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, shoppingList);
        list = (ListView) v.findViewById(R.id.shoppingListView);
        list.setAdapter(adapter);

        //removes item when it is clicked on it and prompts question
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if (selectedItem.trim().equals(shoppingList.get(position).trim())) {
                    removeElement(selectedItem, position);
                } else {
                    Toast.makeText(getContext(),"Error Removing Element", Toast.LENGTH_LONG).show();
                }
            }
        });
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.shopping_items, menu);
    }

    //implements add button do add items to the list
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_add){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Add Item");
            final EditText input = new EditText(getActivity());
            builder.setView(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    shoppingList.add(input.getText().toString());
                    storeArrayVal(shoppingList, getContext());
                    list.setAdapter(adapter);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
            return true;
        }
        //implements clear button to clear all records
        if(id == R.id.action_clear){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Clear Entire List");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    shoppingList.clear();
                    list.setAdapter(adapter);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //creating persistent records of the input
    public static void storeArrayVal(ArrayList<String> inArrayList, Context context)
    {
        Set<String> WhatToWrite = new HashSet<String>(inArrayList);
        SharedPreferences WordSearchPutPrefs = context.getSharedPreferences("dbArrayValues", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = WordSearchPutPrefs.edit();
        prefEditor.putStringSet("myArray", WhatToWrite);
        prefEditor.commit();
    }

    public static ArrayList getArrayVal( Context dan)
    {
        SharedPreferences WordSearchGetPrefs = dan.getSharedPreferences("dbArrayValues", Activity.MODE_PRIVATE);
        Set<String> tempSet = new HashSet<String>();
        tempSet = WordSearchGetPrefs.getStringSet("myArray", tempSet);
        return new ArrayList<String>(tempSet);
    }

    //method to remove element
    public void removeElement(String selectedItem, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Remove " + selectedItem + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                shoppingList.remove(position);
                Collections.sort(shoppingList);
                storeArrayVal(shoppingList, getContext());
                list.setAdapter(adapter);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
