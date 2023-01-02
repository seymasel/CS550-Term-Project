package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BMICalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            TextView final_bmi = (TextView) findViewById(R.id.textViewBMIResult);
            TextView bmi_class = (TextView) findViewById(R.id.BmiCategory);
            Float value = extras.getFloat("a");
            final_bmi.setText(String.valueOf(value));
            if(value<18.50){
                bmi_class.setText("Your Bmi class is underweight");
            }
            else if(18.5<value && value<24.9){
                bmi_class.setText("Your Bmi class is normal");
            }
            else if(25.0<value && value<29.9){
                bmi_class.setText("Your Bmi class is overweight");
            }
            else if(30.0<value && value<34.9){
                bmi_class.setText("Your Bmi class is obesity-1");
            }
            else if(35.0<value && value<39.9){
                bmi_class.setText("Your Bmi class is obesity-2");
            }
            else if(40<=value){
                bmi_class.setText("Your Bmi class is obesity-3(extreme obesity)");
            }
        }
        Button calculate_again = (Button) findViewById(R.id.CalculateAgain);
        calculate_again.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i_back = new Intent(BMICalculator.this, MainActivity.class);
                startActivity(i_back);

            }

        });


    }
}