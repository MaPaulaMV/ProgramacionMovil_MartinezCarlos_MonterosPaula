package app.ejemplo.carlos.proyecto2.Intros;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.ejemplo.carlos.proyecto2.IntroAdapter;
import app.ejemplo.carlos.proyecto2.R;

public class Intro extends AppCompatActivity {

    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        viewPager = findViewById(R.id.viewPager);
        IntroAdapter adapter= new IntroAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
}
