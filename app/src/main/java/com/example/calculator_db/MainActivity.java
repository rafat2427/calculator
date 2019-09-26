package com.example.calculator_db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button sub_bt;
    private Button mul_bt;
    private Button div_bt;
    private Button advanced_bt;
    private TextView result_tv;
    private EditText num1_et, num2_et;

    double result;
    float num1,num2;
    Calculations cal;
    long id=0,lid;

    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add_bt = findViewById(R.id.add_bt);
        sub_bt= findViewById(R.id.sub_bt);
        mul_bt= findViewById(R.id.mul_bt);
        div_bt= findViewById(R.id.div_bt);
        advanced_bt= findViewById(R.id.advanced_bt);


        result_tv= findViewById(R.id.Result_tv);
        num1_et =  findViewById(R.id.Num1_et);
        num2_et =  findViewById(R.id.Num2_et);

        cal= new Calculations();
        reff = FirebaseDatabase.getInstance().getReference().child("Calculations");
        /*reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    id=(dataSnapshot.getChildrenCount());
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

         */




        add_bt.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                String snum1, snum2;
                snum1= num1_et.getText().toString();
                snum2= num2_et.getText().toString();

                if(snum1.isEmpty()){
                    Toast.makeText(MainActivity.this, "No Number Entered in first field", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(snum2.isEmpty()){
                    Toast.makeText(MainActivity.this, "No Number Entered in Second field", Toast.LENGTH_SHORT).show();
                    return;
                }


                num1= Float.parseFloat(snum1);
                num2= Float.parseFloat(snum2);
                result =  num1 + num2;
                result_tv.setText(String.valueOf(result));

                insertData("+");

            }
        } );

        sub_bt.setOnClickListener( new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                String snum1, snum2;
                snum1= num1_et.getText().toString();
                snum2= num2_et.getText().toString();

                if(snum1.isEmpty()){
                    Toast.makeText(MainActivity.this, "No Number Entered in first field", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(snum2.isEmpty()){
                    Toast.makeText(MainActivity.this, "No Number Entered in Second field", Toast.LENGTH_SHORT).show();
                    return;
                }


                num1= Float.parseFloat(snum1);
                num2= Float.parseFloat(snum2);
                result =  num1 - num2;
                result_tv.setText(String.valueOf(result));
                insertData("-");

            }
        } );


        mul_bt.setOnClickListener( new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                String snum1, snum2;
                snum1= num1_et.getText().toString();
                snum2= num2_et.getText().toString();

                if(snum1.isEmpty()){
                    Toast.makeText(MainActivity.this, "No Number Entered in first field", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(snum2.isEmpty()){
                    Toast.makeText(MainActivity.this, "No Number Entered in Second field", Toast.LENGTH_SHORT).show();
                    return;
                }


                num1= Float.parseFloat(snum1);
                num2= Float.parseFloat(snum2);
                result =  num1 * num2;
                result_tv.setText(String.valueOf(result));

                insertData("*");

            }
        } );


        div_bt.setOnClickListener( new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                String snum1, snum2;
                snum1= num1_et.getText().toString();
                snum2= num2_et.getText().toString();

                if(snum1.isEmpty()){
                    Toast.makeText(MainActivity.this, "No Number Entered in first field", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(snum2.isEmpty()){
                    Toast.makeText(MainActivity.this, "No Number Entered in Second field", Toast.LENGTH_SHORT).show();
                    return;
                }


                num1= Float.parseFloat(snum1);
                num2= Float.parseFloat(snum2);
                result =  num1 / num2;
                result_tv.setText(String.valueOf(result));

                insertData("/");

            }
        } );

        advanced_bt.setOnClickListener( new View.OnClickListener(){


            @Override
            public void onClick(View view) {


                open_advanced_activity();
            }
        } );

    }

    public void open_advanced_activity(){

        Intent intent = new Intent(this, scientific_calc.class);
        startActivity(intent);
    }


   void insertData(String opn){
        cal.setNumber1(num1);
        cal.setNumber2(num2);
        cal.setOperator(opn);
        cal.setReslt(result);

        reff.push().setValue(cal);
        //lid= reff.child(String.valueOf(id+1)).wait();
        //reff.child(String.valueOf(id+1)).setValue(cal);

        Toast.makeText(MainActivity.this,"Data inserted successfully" , Toast.LENGTH_SHORT ).show();
    }
}