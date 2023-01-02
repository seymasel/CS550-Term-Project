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


/*

        //Realm.init(this);
        //Realm realm = Realm.getDefaultInstance();
        //DataModal user = realm.createObject(DataModal.class);
        //user.setWeight(2.0);
        //user.setHeight(42.0);
        //realm.beginTransaction();

        //realm.commitTransaction();
        //mongoClient = new MongoClient() {
        //}
        //Realm.init(this);

       // RealmConfiguration config = new RealmConfiguration.Builder().name("default.realm").build();
        //Realm.setDefaultConfiguration(config);



import android.content.DialogInterface;


import android.widget.TextView;

    private void addDataToDatabase(Double height, Double weight, Double BmiScore, String BmiClass) {

        // on below line we are creating
        // a variable for our modal class.
        DataModal modal = new DataModal();

        // on below line we are getting id for the course which we are storing.
        Number id = realm.where(DataModal.class).max("id");

        // on below line we are
        // creating a variable for our id.
        long nextId;

        // validating if id is null or not.
        if (id == null) {
            // if id is null
            // we are passing it as 1.
            nextId = 1;
        } else {
            // if id is not null then
            // we are incrementing it by 1
            nextId = id.intValue() + 1;
        }
        // on below line we are setting the
        // data entered by user in our modal class.
        modal.setId(nextId);
        modal.setHeight(height);
        modal.setWeight(weight);
        modal.setBmiScore(BmiScore);
        modal.setBmiClass("normal");

        // on below line we are calling a method to execute a transaction.
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // inside on execute method we are calling a method
                // to copy to real m database from our modal class.
                realm.copyToRealm(modal);
            }
        });


    }*/





   /* ConnectionString connectionString = new ConnectionString("mongodb+srv://seymasel:123@cluster0.mot9e4e.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    MongoClient mongoClient = (MongoClient) MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("ProjectDatabase.BMI");

*/