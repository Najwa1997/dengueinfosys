package com.example.dengueinfosys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Test1 extends AppCompatActivity {

    private TextView countLabel;
    private ImageView questionImage;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = {
            {"q_fever","Yes","No","Not Sure"},
            {"q_headache","Yes","No","Not Sure"},
            {"q_skin_rashes","Yes","No","Not Sure"},
            {"q_diarrhea","Yes","No","Not Sure"},
            {"q_nausea","Yes","No","Not Sure"},
            {"q_eye_pain","Yes","No","Not Sure"},
            {"q_muscle_pain","Yes","No","Not Sure"},
            {"q_aedes","Yes","No","Not Sure"},


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        countLabel = findViewById(R.id.countLabel);
        questionImage = findViewById(R.id.questionImage);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);

        //create quizArray from quizData
        for (int i = 0; i < quizData.length; i++) {
            //prepare array
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); //Image Name
            tmpArray.add(quizData[i][1]); //Right Answer
            tmpArray.add(quizData[i][2]); //Choice1
            tmpArray.add(quizData[i][3]); //Choice2

            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz() {
        //update quizCountLabel.
        countLabel.setText("Q" + quizCount);

        // Generate random number between 0 and 4 (quizArray's size -1)
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        // Pick one quiz set
        ArrayList<String> quiz = quizArray.get(randomNum);

        // Set Image and Right Answer
        // Array format: {"Image Name", "Right Answer", "Choice1")
        questionImage.setImageResource(
                getResources().getIdentifier(quiz.get(0), "drawable", getPackageName()));
        rightAnswer = quiz.get(1);

        // Remove "Image Name" from quiz and shuffle Choices.
        quiz.remove(0);
        Collections.shuffle(quiz);

        // Set Choices
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));

        // Remove this quiz from quizArray
        quizArray.remove(randomNum);
    }

    public void checkAnswer(View view) {

        // Get pushed button
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if (btnText.equals(rightAnswer)) {
            // Correct!!
            alertTitle = "Okay! Next Question :)";
            rightAnswerCount++;

        } else {
            // Wrong!!
            alertTitle = "Okay! Next Question :)";
        }

        // Create Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (quizArray.size() < 1) {
                    // quizArray is empty
                    showResult();
                } else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    public void showResult() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Your Result");
        builder.setMessage(rightAnswerCount +"/8 \nIf your result reveal a score that is more than 7, you are advised to check yourself at nearest hospital/clinic. \nThank you and have a nice day :)");
        builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recreate();
            }
        });
        builder.setNeutralButton("Nearby Hospital/Clinic", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Test1.this,NearbyHCActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }

}