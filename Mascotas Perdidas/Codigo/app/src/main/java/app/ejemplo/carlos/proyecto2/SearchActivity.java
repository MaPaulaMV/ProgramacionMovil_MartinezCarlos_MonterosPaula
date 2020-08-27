package app.ejemplo.carlos.proyecto2;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import app.ejemplo.carlos.proyecto2.Fragments.DeportivosFragment;
import app.ejemplo.carlos.proyecto2.Fragments.DocsFragment;
import app.ejemplo.carlos.proyecto2.Fragments.TecFragment;

public class SearchActivity extends AppCompatActivity implements  DeportivosFragment.OnFragmentInteractionListener,
        DocsFragment.OnFragmentInteractionListener, TecFragment.OnFragmentInteractionListener {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    Button bfecha;
    EditText efecha;
    private int dia, mes,anio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BUSCAR");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static Fragment newInstance(int sectionNumber) {

            Fragment fragment = null;
            switch (sectionNumber){
                case 1: fragment = new DocsFragment();
                        break;
                case 2:
                    fragment = new DeportivosFragment();
                    break;
                case 3:
                    fragment = new TecFragment();
                    break;
               /* case 4:
                    fragment = new EduFragment();
                    break;
                case 5:
                    fragment = new PersonalesFragment();
                    break;
                case 6:
                    fragment = new PrendasFragment();
                    break;
                case 7:
                    fragment = new OtrosFragment();
                    break;*/
            }
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_search, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case  0:
                    return  "PERDIDOS";
                case  1:
                    return  "HERIDOS";
                case  2:
                    return  "ADOPTAR";
                /*case  3:
                    return  "MATERIAL EDUCATIVO";
                case  4:
                    return  "OBJETOS PERSONALES";
                case  5:
                    return  "PRENDAS DE VESTIR";
                case  6:
                    return  "OTROS";*/
            }
            return  null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent miIntenr = new Intent(this,menumain.class);
        miIntenr.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(miIntenr);
        finish();
    }


}
