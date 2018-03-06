package uk.ac.napier.fitassistant;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by vikto on 3/3/2018.
 */

public class Communicator extends FragmentPagerAdapter {
    public Communicator(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BmiFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
