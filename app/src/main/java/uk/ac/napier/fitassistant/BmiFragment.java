package uk.ac.napier.fitassistant;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        Button bmiCalc = (Button)v.findViewById(R.id.bmiCalc);

        bmiCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcBMI();
            }
        });
        return v;
    }

    public void calcBMI(){
        String h = height.getText().toString();
        String w = weight.getText().toString();

        //checks if the entered value is empty
        if(h != null && w != null && !"".equals(h) && !"".equals(w) ){

            float heightFloat = Float.parseFloat(h)/100;
            float weightFloat = Float.parseFloat(w);

            float bmi = weightFloat/(heightFloat*heightFloat);

            result.setText(((Float) bmi).toString());

        }
    }

}
