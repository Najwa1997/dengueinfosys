package com.example.dengueinfosys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class DengueInfo2 extends AppCompatActivity {
    GridLayout mainGrid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dengue_info2);

        mainGrid = (GridLayout) findViewById(R.id.infoGrid);


        //Set Event
        setSingleEvent(mainGrid);
        //setToggleEvent (mainGrid);
    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item for Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see, all child item is CardView, so we just cast object as CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(DengueInfo2.this, "State : True", Toast.LENGTH_SHORT).show();
                    } else {
                        //change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(DengueInfo2.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see, all child item is CardView, so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI == 0) {
                        Intent intent = new Intent(DengueInfo2.this, AboutDengue.class);
                        startActivity(intent);

                    } else if (finalI == 1) {
                        Intent intent = new Intent(DengueInfo2.this, PreventDengue.class);
                        startActivity(intent);

                    } else if (finalI == 2) {
                        Intent intent = new Intent(DengueInfo2.this, DengueSymptom.class);
                        startActivity(intent);
                    } else if (finalI == 3) {
                        Intent intent = new Intent(DengueInfo2.this, DengueInfoActivity.class);
                        startActivity(intent);
                    }



                }
            });
        }
    }
}