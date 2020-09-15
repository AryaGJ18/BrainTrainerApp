package com.example.aryakrisna.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    CountDownTimer timer;
    TextView timerView;
    TextView message;
    Button playAgain;
    TextView tally;
    TextView questions;

    boolean isActive = true;

    Button option1;
    Button option2;
    Button option3;
    Button option4;

    int ans;

    int correct;
    int total;

    public int generateRandomQuestions(){
        Random rand = new Random();

        int one = rand.nextInt(21);
        final int two = rand.nextInt(21);


        questions.setText(one+ " + " + two);


        int[] options = {(one+two), rand.nextInt(21), rand.nextInt(21), rand.nextInt(21)};
        Log.i("answers", Arrays.toString(options));

        for(int i = 0; i < options.length - 1; i++){
            for(int j = i + 1; j < options.length; j++){
                if(options[i] == options[j]){
                    options[j] = rand.nextInt(21);
                }
            }
        }


        int random = rand.nextInt(4);

        Log.i("random", "" + random);

        option1.setText("" + options[random]);
        option2.setText("" + options[Math.abs(random - 1)]);
        option3.setText("" + options[Math.abs(random - 2) + (random == 1 ? 2 : 0)]);
        option4.setText("" + options[Math.abs(random - 3) + (random == 2 ? 2 : 0)]);

        return (one + two);
    }

    public void onClick(View view){
        Button option = (Button) view;
        total++;
        if(option.getText().toString().equals(Integer.toString(ans))){
            message.setText("Correct :)");
            correct++;
        }else {
            message.setText("Wrong :(");
        }
        tally.setText(correct + "/" + total);

        if(isActive){
            ans = generateRandomQuestions();
        }
    }


    public void go(View view){
        Button goButton = (Button) view;

        GridLayout layout = findViewById(R.id.gridLayout);
        layout.setVisibility(View.VISIBLE);

        RelativeLayout relativeLayout = findViewById(R.id.relative);
        relativeLayout.setVisibility(View.VISIBLE);

        goButton.setVisibility(View.GONE);

        timer.start();
        ans = generateRandomQuestions();
        Log.i( "answer",""+ans);
        message.setVisibility(View.VISIBLE);
        message.setText("");

        Log.i("Button pressed", "Success");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playAgain = findViewById(R.id.playAgain);
        message = findViewById(R.id.textView4);
        tally = findViewById(R.id.textView3);
        questions = findViewById(R.id.textView2);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        timerView = (TextView) findViewById(R.id.textView1);
        timer = new CountDownTimer(31000, 1000) {

            @Override
            public void onTick(long l) {
                String timeLeft = "0:" + l/1000;
                if(l < 10000){
                   timeLeft = "0:0" + l/1000;
                }
                timerView.setText(timeLeft);
            }

            @Override
            public void onFinish() {
                isActive = false;
                playAgain.setVisibility(View.VISIBLE);
                message.setVisibility(View.VISIBLE);
                message.setText("Done!");
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);
            }
        };
    }

    public void playAgain(View view){
        total = 0;
        correct = 0;
        tally.setText(correct + "/" + total );
        isActive = true;
        message.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
        timer.start();
        ans = generateRandomQuestions();

    }

}
