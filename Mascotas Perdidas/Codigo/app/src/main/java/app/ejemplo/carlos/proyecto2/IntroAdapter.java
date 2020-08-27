package app.ejemplo.carlos.proyecto2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import app.ejemplo.carlos.proyecto2.Intros.Intro1;
import app.ejemplo.carlos.proyecto2.Intros.Intro2;
import app.ejemplo.carlos.proyecto2.Intros.Intro3;
import app.ejemplo.carlos.proyecto2.Intros.Intro4;

public class IntroAdapter extends FragmentPagerAdapter {


    public IntroAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Intro1();
            case 1:
                return new Intro2();
            case 2:
                return new Intro3();
            case 3:
                return new Intro4();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }
}
