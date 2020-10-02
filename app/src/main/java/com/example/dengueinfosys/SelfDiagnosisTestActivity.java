package com.example.dengueinfosys;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelfDiagnosisTestActivity extends AppCompatActivity {
    private Button B_Start, B_Record, B_MoreSym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_diagnosis_test);

        B_Start =findViewById(R.id.B_Start);
        B_Record=findViewById(R.id.B_Record);
        B_MoreSym=findViewById(R.id.B_MoreSym);

        B_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test1();
            }
        });

        B_Record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                record();
            }
        });

        B_MoreSym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoactivity();

            }
        });

    }

    private void videoactivity() {
        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }

    private void record() {
        Intent intent = new Intent(this, MainRecord.class);
        startActivity(intent);
    }


    public void  test1()
    {
        Intent intent = new Intent(this, Test1.class);
        startActivity(intent);
    }
}
