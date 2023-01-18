package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore firestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firestore =FirebaseFirestore.getInstance();



        Button calculateBtn = (Button) findViewById(R.id.calculateButton);
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText etWeight = (EditText) findViewById(R.id.editTextWeight);
                float weight = Integer.parseInt(etWeight.getText().toString());
                EditText etHeight = (EditText) findViewById(R.id.editTextHeight);
                float cm_height = Integer.parseInt(etHeight.getText().toString());
                float m_height = cm_height / 100;
                float bmi = weight / (m_height * m_height);
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                float bmi_decimal = Float.valueOf(decimalFormat.format(bmi));
                String bmiClass = "";
                Intent i_next = new Intent(MainActivity.this, BMICalculator.class);
                i_next.putExtra("a", bmi_decimal);
                startActivity(i_next);

                if(bmi_decimal<18.50){
                    bmiClass = "underweight";
                }
                else if(18.5<bmi_decimal && bmi_decimal<24.9){
                    bmiClass = "normal";
                }
                else if(25.0<bmi_decimal && bmi_decimal<29.9){
                    bmiClass = "overweight";
                }
                else if(30.0<bmi_decimal && bmi_decimal<34.9){
                    bmiClass = "obesity-1";
                }
                else if(35.0<bmi_decimal && bmi_decimal<39.9){
                    bmiClass = "obesity-2";
                }
                else if(40<=bmi_decimal){
                    bmiClass = "obesity-3";
                }

                AppUser appUsers = new AppUser(weight, m_height, bmi, bmiClass);
                firestore.collection("users").add(appUsers).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();

                    }
                });



            }
        });
    }

}


