package com.example.tarea1_martinezcarlos_monterospaula;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText num1 = (EditText) findViewById(R.id.num1);
        final EditText num2 = (EditText) findViewById(R.id.num2);
        final TextView res = (TextView) findViewById(R.id.res);
        Button suma = (Button) findViewById(R.id.suma);

        suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                if(num1.getText().toString().equals("") || num2.getText().toString().equals("") ){
                    Toast.makeText(getApplicationContext(), "DEBES INGRESAR LOS DOS NUMEROS :)", Toast.LENGTH_LONG).show();
                }
                else{
                    int nume1 = Integer.parseInt(num1.getText().toString());
                    int nume2 = Integer.parseInt(num2.getText().toString());
                    int resp = nume1 + nume2;
                    Log.e("ENTRA", String.valueOf(resp));
                    res.setText("RESULTADO:" + String.valueOf(resp));
                    inputMethodManager.hideSoftInputFromWindow(num1.getWindowToken(), 0);
                }

            }
        });

    }


}
