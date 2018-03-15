package uk.ac.napier.fitassistant;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalorieFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner activity;
    private EditText height, weight, age;
    private RadioGroup gender;
    private TextView bmrResult, tdeeResult, gainResult, maintainResult, loseResult;
    private double multiplier = 0;
    private long tdee = 0;

    public CalorieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_calorie, container, false);
        Button tdeeCalc = (Button) v.findViewById(R.id.tdee_btn);
        height = (EditText) v.findViewById(R.id.editHeight);
        weight = (EditText) v.findViewById(R.id.editWeight);
        age = (EditText) v.findViewById(R.id.editAge);
        gender = (RadioGroup) v.findViewById(R.id.radioGroup2);
        bmrResult = (TextView) v.findViewById(R.id.bmrResult);
        tdeeResult = (TextView) v.findViewById(R.id.tdeeResult);
        gainResult = (TextView) v.findViewById(R.id.gainResult);
        maintainResult = (TextView) v.findViewById(R.id.maintainResult);
        loseResult = (TextView) v.findViewById(R.id.loseResult);
        Spinner activity = (Spinner)v.findViewById(R.id.spinnerCalorie);

        //add items to spinner programatically
        activity.setOnItemSelectedListener(this);
        List<String> spinnerItems = new ArrayList<String>();
        spinnerItems.add("Little or no exercise.");
        spinnerItems.add("Light exercise/sports 1-3 days/week.");
        spinnerItems.add("Moderate exercise/sports 3-5 days/week.");
        spinnerItems.add("Hard exercise/sports 6-7 days a week.");
        spinnerItems.add("Very hard exercise/sports and physical job.");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerItems);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activity.setAdapter(dataAdapter);


        tdeeCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                calculate();
            }
        });

        return v;
    }


    //sets the multiplier for the TDEE formula
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        hideKeyboard();
        String item = parent.getItemAtPosition(position).toString();
        if(item.equals("Little or no exercise.")){
            multiplier = 1.2;
        }
        else if(item.equals("Light exercise/sports 1-3 days/week.")){
            multiplier = 1.375;
        }
        else if(item.equals("Moderate exercise/sports 3-5 days/week.")){
            multiplier = 1.55;
        }
        else if(item.equals("Hard exercise/sports 6-7 days a week.")){
            multiplier = 1.72;
        }
        else if(item.equals("Very hard exercise/sports and physical job.")){
            multiplier = 1.9;
        }
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //method to calculate TDEE
    //checks if required fields are empty
    private void calculate(){
        long bmr = 0;
        long gain = 0;
        long lose = 0;
        String h = height.getText().toString();
        String w = weight.getText().toString();
        String a = age.getText().toString();
        RadioGroup g = gender;

        if(h != null && w != null && a != null && !"".equals(h) && !"".equals(w) && !"".equals(a) ) {
            int radioID = g.getCheckedRadioButtonId();
            View radioGender = g.findViewById(radioID);
            int position = g.indexOfChild(radioGender);

            String strGender = "";
            if (position == 0) {
                strGender = "female";
            } else {
                strGender = "male";
            }
            float heightFloat = Float.parseFloat(h);
            float weightFloat = Float.parseFloat(w);
            float ageFloat = Float.parseFloat(a);
            if (strGender.startsWith("m")) {
                bmr = Math.round((10 * weightFloat) + (6.25 * heightFloat) - (5 * ageFloat) - 161);
            } else {
                bmr = Math.round((10 * weightFloat) + (6.25 * heightFloat) - (5 * ageFloat) + 5);
            }
            tdee = Math.round(bmr*multiplier);
            gain = Math.round(tdee+500);
            lose = Math.round(tdee-500);
            bmrResult.setText(((Long) bmr).toString() + getString(R.string.kcal_string));
            tdeeResult.setText(((Long) tdee).toString() + getString(R.string.kcal_string));
            gainResult.setText(((Long) gain).toString() + getString(R.string.kcal_string));
            loseResult.setText(((Long) lose).toString() + getString(R.string.kcal_string));
            maintainResult.setText(((Long) tdee).toString() + getString(R.string.kcal_string));
        }
        else
        {
            Toast.makeText(getActivity(), "Please enter Weight, Height and Age", Toast.LENGTH_LONG).show();
        }
    }

    //hides keyboard when button is pressed
    public void hideKeyboard() {
        // Check if no view has focus:
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }


    }
}


