package app.ejemplo.carlos.proyecto2.Intros;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.ejemplo.carlos.proyecto2.R;


public class Intro2 extends Fragment {

    TextView siguiente;
    TextView atras;
    ViewPager viewPager;


    public Intro2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_intro2, container, false);
        viewPager = getActivity().findViewById(R.id.viewPager);
        siguiente = v.findViewById(R.id.slide2siguiente);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });
        atras= v.findViewById(R.id.slide2back);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        return v;
    }





}
