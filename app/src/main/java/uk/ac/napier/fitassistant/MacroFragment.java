package uk.ac.napier.fitassistant;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MacroFragment extends Fragment {

    SeekBar seekProtein, seekCarb, seekFat;
    TextView proteinPercentage, carbPercentage, fatPercentage, resultProtein, resultFat, resultCarb;
    EditText editCalories;

     public MacroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v =  inflater.inflate(R.layout.fragment_macro_calc, container, false);
        seekProtein = (SeekBar) v.findViewById(R.id.seekProtein);
        seekCarb = (SeekBar) v.findViewById(R.id.seekCarb);
        seekFat = (SeekBar) v.findViewById(R.id.seekFat);
        resultProtein = (TextView) v.findViewById(R.id.resultProtein);
        resultCarb = (TextView) v.findViewById(R.id.resultCarb);
        resultFat = (TextView) v.findViewById(R.id.resultFat);
        editCalories = (EditText) v.findViewById(R.id.editCalories);
        proteinPercentage = (TextView) v.findViewById(R.id.proteinPercentage);
        carbPercentage = (TextView) v.findViewById(R.id.carbPercentage);
        fatPercentage = (TextView) v.findViewById(R.id.fatPercentage);

        //enables the seekbars only after input is given
        editCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekProtein.setEnabled(true);
                seekFat.setEnabled(true);
                seekCarb.setEnabled(true);
            }
        });

        //adjusts the sliders on to another all three sharing one max value(100)
        seekProtein.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                hideKeyboard();
                String c = editCalories.getText().toString();
                if (c != null && !"".equals(c)) {
                    seekCarb.setProgress(seekCarb.getMax() - seekProtein.getProgress() - seekFat.getProgress());
                    seekFat.setProgress(seekFat.getMax() - seekCarb.getProgress() - seekProtein.getProgress());
                    int calories = Integer.parseInt(c);
                    int value = seekProtein.getProgress();
                    int calculated = (((value*calories)/100)/4);
                    resultProtein.setText(((Integer) calculated).toString() + getString(R.string.gramsString));
                    proteinPercentage.setText(((Integer) value).toString() + getString(R.string.perc));
                }
                //disables the seekbars if there is no input
                else {
                    Toast.makeText(getActivity(),"Please enter calories!", Toast.LENGTH_SHORT).show();
                    seekProtein.setEnabled(false);
                    seekFat.setEnabled(false);
                    seekCarb.setEnabled(false);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        seekCarb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                hideKeyboard();
                String c = editCalories.getText().toString();
                if (c != null && !"".equals(c)) {
                    seekFat.setProgress(seekFat.getMax() - seekCarb.getProgress() - seekProtein.getProgress());
                    seekProtein.setProgress(seekProtein.getMax() - seekCarb.getProgress() - seekFat.getProgress());
                    int calories = Integer.parseInt(c);
                    int value = seekCarb.getProgress();
                    int calculated = (((value*calories)/100)/4);
                    resultCarb.setText(((Integer) calculated).toString() + getString(R.string.gramsString));
                    carbPercentage.setText(((Integer) value).toString() + getString(R.string.perc));
                }
                else {
                    Toast.makeText(getActivity(),"Please enter calories!", Toast.LENGTH_SHORT).show();
                    seekProtein.setEnabled(false);
                    seekFat.setEnabled(false);
                    seekCarb.setEnabled(false);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekFat.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                hideKeyboard();
                String c = editCalories.getText().toString();
                if (c != null && !"".equals(c)) {
                    seekProtein.setProgress(seekProtein.getMax() - seekCarb.getProgress() - seekFat.getProgress());
                    seekCarb.setProgress(seekCarb.getMax() - seekProtein.getProgress() - seekFat.getProgress());
                    int calories = Integer.parseInt(c);
                    int value = seekFat.getProgress();
                    int calculated = (((value*calories)/100)/9);
                    resultFat.setText(((Integer) calculated).toString() + getString(R.string.gramsString));
                    fatPercentage.setText(((Integer) value).toString() + getString(R.string.perc));
                }
                else {
                    Toast.makeText(getActivity(),"Please enter calories!", Toast.LENGTH_SHORT).show();
                    seekProtein.setEnabled(false);
                    seekFat.setEnabled(false);
                    seekCarb.setEnabled(false);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        return v;
    }

    //hides keyboard when button is clicked
    public void hideKeyboard() {
        // Check if no view has focus:
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }


    }
}
