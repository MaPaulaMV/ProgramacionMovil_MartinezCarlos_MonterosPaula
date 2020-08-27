package app.ejemplo.carlos.proyecto2.Intros;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.ejemplo.carlos.proyecto2.LogInActivity;
import app.ejemplo.carlos.proyecto2.R;


public class Intro4 extends Fragment {

    TextView siguiente;
    TextView atras;
    ViewPager viewPager;


    public Intro4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_intro4, container, false);
        viewPager = getActivity().findViewById(R.id.viewPager);
        siguiente = v.findViewById(R.id.slidecomenzar);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intro4.this.getActivity(), LogInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return v;

    }

}
