package uk.ac.napier.fitassistant;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BmiFragment extends Fragment {

    private EditText height;
    private EditText weight;
    private TextView result;


    public BmiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_bmi, container, false);
        height = (EditText) v.findViewById(R.id.height);
        weight = (EditText) v.findViewById(R.id.weight);
        result = (TextView) v.findViewById(R.id.result);
        Button bmiCalc = (Button) v.findViewById(R.id.bmiCalc);

        bmiCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                calcBMI();
            }
        });
        return v;
    }
//checks if input fields are empty and calculates BMI
    public void calcBMI() {
        String h = height.getText().toString();
        String w = weight.getText().toString();

        //checks if the entered value is empty
        if (h != null && w != null && !"".equals(h) && !"".equals(w)) {

            float heightFloat = Float.parseFloat(h) / 100;
            float weightFloat = Float.parseFloat(w);

            float bmi = Math.round(weightFloat / (heightFloat * heightFloat));

            result.setText(((Float) bmi).toString());

        }
    }
//hides the keyboard when button is pressed
    public void hideKeyboard() {
        // Check if no view has focus:
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }


    }
}

