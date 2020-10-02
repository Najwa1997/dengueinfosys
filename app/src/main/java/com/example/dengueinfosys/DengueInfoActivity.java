package com.example.dengueinfosys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DengueInfoActivity extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dengue_info);

        mainGrid = (GridLayout)findViewById(R.id.infoGrid);

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
                        Toast.makeText(DengueInfoActivity.this,"State : True", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        //change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(DengueInfoActivity.this,"State : False", Toast.LENGTH_SHORT).show();
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
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1B5S4f5tTpeduxFmrVeOTDbLgrhnLhulc?usp=sharing"));
                        startActivity(browserIntent);
                    }
                    else if (finalI == 1)
                    {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1ADaCN09xrpQu7xY5m8F8G8YkqaI2AQnm?usp=sharing"));
                        startActivity(browserIntent);
                    }
                    else if (finalI == 2)
                    {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1drl-AjuwKddbkwXuekbbK5GIbSHXZK7N?usp=sharing"));
                        startActivity(browserIntent);
                    }
                    else if (finalI == 3)
                    {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/14xDkI6ZlW0dqxTqjLDjFTNlpni2b6Q9c?usp=sharing"));
                        startActivity(browserIntent);
                    }
                    else if (finalI == 4)
                    {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1QxGRiByGy1Et63Z9ZzUHlpTZDUQzGqTz?usp=sharing"));
                        startActivity(browserIntent);
                    }
                    else if (finalI == 5)
                    {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1hEqYvH53sP5Bm3Je_DHy4O4ow9O-G0d7?usp=sharing"));
                        startActivity(browserIntent);
                    }
                    else if (finalI == 6)
                    {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1gPKnrnptE5XHtgq_QqQjcczrysucL0eH?usp=sharing"));
                        startActivity(browserIntent);
                    }
                    else if (finalI == 7)
                    {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1dQhpyGMOdjMg-tv7u9ZPvAmCfkrAmSw0?usp=sharing"));
                        startActivity(browserIntent);
                    }
                    else if (finalI == 8)
                    {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1UYGF-ywVcovMu-AZvR18FdUStxBMo6Lw?usp=sharing"));
                        startActivity(browserIntent);
                    }
                    else if (finalI == 9)
                    {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1GWWwF1mcQoLDPvCvFAD8WmFV7uS7CfvE?usp=sharing"));
                        startActivity(browserIntent);
                    }
                    else if (finalI == 10)
                    {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1Cfqrg9cUsCBg4dAlvzO-2Ep8dlL2MLbH?usp=sharing"));
                        startActivity(browserIntent);
                    }
                    else if (finalI == 11)
                    {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1a4T54Fwtn1KTm86FguE9GS9YWWwF8Z_K?usp=sharing"));
                        startActivity(browserIntent);
                    }
                    else if (finalI == 12)
                    {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1LdyhlpIeXAIPvtyIPD8sec7AtKLU5dLT?usp=sharing"));
                        startActivity(browserIntent);
                    }
                    else if (finalI == 13)
                    {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/13kmmVVRpGxSgMm34SdDFIiQv88QuZmVl?usp=sharing"));
                        startActivity(browserIntent);
                    }
                    else if (finalI == 14)
                    {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1RdYEp9cOBXjagH61PXI3kyUs5jnsBxiB?usp=sharing"));
                        startActivity(browserIntent);
                    }
                    else if (finalI == 15)
                    {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1UJPL7_OMVvK0nrHjpN7rD8gUh_XGEus6?usp=sharing"));
                        startActivity(browserIntent);
                    }

                }
            });
        }
    }

}
