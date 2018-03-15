package uk.ac.napier.fitassistant;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private HomeFragment homeFragment;
    private CalcFragment calcFragment;
    private ShopFragment shopFragment;
    private ProgressFragment progressFragment;
    private BmiFragment bmiFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        homeFragment = new HomeFragment();
        calcFragment = new CalcFragment();
        shopFragment = new ShopFragment();
       // progressFragment = new ProgressFragment();
        bmiFragment = new BmiFragment();
        setFragment(homeFragment);



        //switches between fragments via the bottom navigation
        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        mMainNav.setItemBackgroundResource(R.color.colorNav);
                        setFragment(homeFragment);
                        return true;

                    case  R.id.nav_calc:
                        mMainNav.setItemBackgroundResource(R.color.colorNav);
                        setFragment(calcFragment);
                        return true;

                   /* case R.id.nav_prog:
                        mMainNav.setItemBackgroundResource(R.color.colorProg);
                        setFragment(progressFragment);
                        return true;*/

                    case R.id.nav_shop:
                        mMainNav.setItemBackgroundResource(R.color.colorNav);
                        setFragment(shopFragment);
                        return true;

                    default:
                        return false;

                }
            }

        });
    }


    //method to set the fragment
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
