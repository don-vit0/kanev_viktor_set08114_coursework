package uk.ac.napier.fitassistant;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalcFragment extends Fragment {

    public CalcFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v =  inflater.inflate(R.layout.fragment_calc, container, false);
        Button bmi_button = (Button)v.findViewById(R.id.bmi_button);
        Button calorie_button = (Button) v.findViewById(R.id.calorie_button);
        Button weightC_button = (Button) v.findViewById(R.id.conv_button);


        //Onclick listeners for the buttons to switch fragments
         bmi_button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Fragment bmiFragment = new BmiFragment();
                  FragmentTransaction transaction = getFragmentManager().beginTransaction();
                  transaction.replace(R.id.main_frame, bmiFragment).addToBackStack(null);
                  transaction.commit();
              }
           });

         calorie_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Fragment calorieFragment = new CalorieFragment();
                 FragmentTransaction transaction = getFragmentManager().beginTransaction();
                 transaction.replace(R.id.main_frame, calorieFragment).addToBackStack(null);
                 transaction.commit();
             }
         });

         weightC_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Fragment weightConvertFragment = new MacroFragment();
                 FragmentTransaction transaction = getFragmentManager().beginTransaction();
                 transaction.replace(R.id.main_frame, weightConvertFragment).addToBackStack(null);
                 transaction.commit();
             }
         });




        return v;
    }
}
