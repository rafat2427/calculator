package com.example.calculator_db;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
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


public class scientific_calc extends AppCompatActivity {


    private Button Sin_bt;
    private Button Cos_bt;
    private Button Tan_bt;
    private Button Cot_bt;
    private Button Pow_bt;
    private Button Main_bt;
    private TextView result2_tv;
    private EditText num21_et, num22_et;

    double result;
    float num1,num2;
    Calculations cal_s;
    //long sid=0;

    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientific_calc);



        Sin_bt = findViewById(R.id.sin_bt);
        Cos_bt= findViewById(R.id.cos_bt);
        Tan_bt= findViewById(R.id.tan_bt);
        Cot_bt= findViewById(R.id.cot_bt);
        Pow_bt= findViewById(R.id.pow_bt);
        Main_bt= findViewById(R.id.main_bt);


        result2_tv= findViewById(R.id.res2_tv);
        num21_et =  findViewById(R.id.no1_et);


        cal_s= new Calculations();
        reff = FirebaseDatabase.getInstance().getReference().child("Scientific Calculations");
        /*reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    sid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

         */



        Sin_bt.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                String num;
                num= num21_et.getText().toString();

                if(num.isEmpty()){
                    Toast.makeText(scientific_calc.this, "No Number Entered in first field", Toast.LENGTH_SHORT).show();
                    return;
                }


                num1= Float.parseFloat(num);
                result =  Math.sin(num1);
                result2_tv.setText(String.valueOf(result));

                insertData("sin()");

            }
        } );

        Cos_bt.setOnClickListener( new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                String num;
                num= num21_et.getText().toString();

                if(num.isEmpty()){
                    Toast.makeText(scientific_calc.this, "No Number Entered in first field", Toast.LENGTH_SHORT).show();
                    return;
                }


                num1= Float.parseFloat(num);
                result =  Math.cos(num1);
                result2_tv.setText(String.valueOf(result));
                insertData("cos()");

            }
        } );


        Tan_bt.setOnClickListener( new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                String num;
                num= num21_et.getText().toString();

                if(num.isEmpty()){
                    Toast.makeText(scientific_calc.this, "No Number Entered in first field", Toast.LENGTH_SHORT).show();
                    return;
                }


                num1= Float.parseFloat(num);
                result = Math.tan(num1);
                result2_tv.setText(String.valueOf(result));
                insertData("tan()");

            }
        } );


        Cot_bt.setOnClickListener( new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                String num;
                num= num21_et.getText().toString();
                Double a,b;
                if(num.isEmpty()){
                    Toast.makeText(scientific_calc.this, "No Number Entered in first field", Toast.LENGTH_SHORT).show();
                    return;
                }


                num1= Float.parseFloat(num);
                a =  Math.cos(num1);
                b =  Math.sin(num1);

                result = a/b ;

                result2_tv.setText(String.valueOf(result));


                insertData("cot()");

            }
        } );

        Pow_bt.setOnClickListener( new View.OnClickListener(){


            String snum21, snum22;

            @Override
            public void onClick(View view) {
                snum21= num21_et.getText().toString();

                if(snum21.isEmpty()){
                    Toast.makeText(scientific_calc.this, "No Number Entered in first field", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder alert= new AlertDialog.Builder(scientific_calc.this);

                alert.setTitle("Power");
                alert.setMessage("Enter the power of "+ snum21);
                final EditText input= new EditText(scientific_calc.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);

                alert.setView(input);
                alert.setIcon(R.drawable.calculator);
                alert.setNegativeButton("Okay", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        snum22= input.getText().toString();


                        if(snum22.isEmpty()){
                            Toast.makeText(scientific_calc.this, "No Number Entered in Second field", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        num1= Float.parseFloat(snum21);
                        num2= Float.parseFloat(snum22);
                        result = Math.pow(num1, num2);
                        result2_tv.setText(String.valueOf(result));


                        cal_s.setNumber2(num2);
                        insertData("power");

                    }
                });

                alert.show();

            }
        } );
        Main_bt.setOnClickListener( new View.OnClickListener(){


            @Override
            public void onClick(View view) {


                open_main_activity();
            }
        } );


    }
    public void open_main_activity(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    void insertData(String opn){
        cal_s.setNumber1(num1);
        cal_s.setOperator(opn);
        cal_s.setReslt(result);

        reff.push().setValue(cal_s);
        //reff.child(String.valueOf(sid+1)).setValue(cal_s);

        Toast.makeText(scientific_calc.this,"Data inserted successfully" , Toast.LENGTH_SHORT ).show();
    }
}
