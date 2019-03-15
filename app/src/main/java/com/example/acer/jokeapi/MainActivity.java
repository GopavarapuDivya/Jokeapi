package com.example.acer.jokeapi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText et;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=findViewById(R.id.edit);
        b=findViewById(R.id.button);

    }
    public void submit(View view)
    {
        String s=et.getText().toString();
        if(s.isEmpty())
        {
           AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
           alertDialog.setTitle("alert Box");
           alertDialog.setMessage("you havent entered  any number");
           alertDialog.setCancelable(false);
           alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i)
               {
                   dialogInterface.cancel();
                   finish();
               }
           });
            AlertDialog dialog = alertDialog.create();
            dialog.show();

        }

    else {
            int in= Integer.parseInt(et.getText().toString());
            if (in>100)
            {
                Toast.makeText(this, "Enter from only 1 to 100", Toast.LENGTH_SHORT).show();
               return;
            }
            else
                {
                Intent i = new Intent(MainActivity.this, ResultsActivity.class);
                i.putExtra("data", s);
                startActivity(i);
            }

        }
    }

    }


