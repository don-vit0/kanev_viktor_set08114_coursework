package uk.ac.napier.fitassistant;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private HomeFragment homeFragment;
    private CalcFragment calcFragment;
    private ShopFragment shopFragment;
    private ProgressFragment progressFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        homeFragment = new HomeFragment();
        calcFragment = new CalcFragment();
        shopFragment = new ShopFragment();
        progressFragment = new ProgressFragment();
        setFragment(homeFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(homeFragment);
                        return true;

                    case  R.id.nav_calc:
                        mMainNav.setItemBackgroundResource(R.color.colorCalc);
                        setFragment(calcFragment);
                        return true;

                    case R.id.nav_prog:
                        mMainNav.setItemBackgroundResource(R.color.colorProg);
                        setFragment(progressFragment);
                        return true;

                    case R.id.nav_shop:
                        mMainNav.setItemBackgroundResource(R.color.colorShop);
                        setFragment(homeFragment);
                        return true;

                    default:
                        return false;

                }
            }

        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
