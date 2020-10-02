package com.example.dengueinfosys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AdminPage extends AppCompatActivity {

    GridLayout mainGrid;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

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
                        Toast.makeText(AdminPage.this,"State : True", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        //change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(AdminPage.this,"State : False", Toast.LENGTH_SHORT).show();
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
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1upbLv7EtQbhrB6fSVo2ILVbmBBS_WUUn?usp=sharing"));
                        startActivity(browserIntent);
                    }
                    else if (finalI == 1)
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
        startActivity(new Intent(AdminPage.this, StartPage.class));
    }

}