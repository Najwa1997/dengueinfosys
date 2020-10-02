package com.example.dengueinfosys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    GridLayout mainGrid;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid = (GridLayout)findViewById(R.id.mainGrid);
        firebaseAuth = FirebaseAuth.getInstance();


        //Set Event
        setSingleEvent(mainGrid);
        //setToggleEvent (mainGrid);
    }
    private void setToggleEvent (GridLayout mainGrid) {
        //Loop all child item for Main Grid
        for (int i = 0; i <mainGrid.getChildCount(); i++)
        {
            //You can see, all child item is CardView, so we just cast object as CardView
            final CardView cardView = (CardView)mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1)
                    {
                        //change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(MainActivity.this,"State : True", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        //change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(MainActivity.this,"State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i <mainGrid.getChildCount(); i++)
        {
            //You can see, all child item is CardView, so we just cast object to CardView
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if (finalI == 0)
                   {
                       Intent intent = new Intent(MainActivity.this,HotspotsActivity.class);
                       startActivity(intent);
                   }
                   else if (finalI == 1)
                   {
                       Intent intent = new Intent(MainActivity.this,DengueInfo2.class);
                       startActivity(intent);
                   }
                   else if (finalI == 2)
                   {
                       Intent intent = new Intent(MainActivity.this,NearbyHCActivity.class);
                       startActivity(intent);
                   }
                   else if (finalI == 3)
                   {
                       Intent intent = new Intent(MainActivity.this,SelfDiagnosisTestActivity.class);
                       startActivity(intent);
                   }
                   else if (finalI == 4)
                   {
                       Logout();
                   }


                }
            });
        }
    }




    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(MainActivity.this, StartPage.class));
    }

}
