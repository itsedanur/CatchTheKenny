package com.example.catchthekenny;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TextView scoreText;
    TextView timeText;
    int score;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView10;
    Handler handler;
    Runnable runnable;

    ImageView[] imageArray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

      timeText = (TextView)  findViewById(R.id.timeText);
      scoreText= (TextView) findViewById(R.id.scoreText);


        imageView1= findViewById(R.id.imageView1);
         imageView2 = findViewById(R.id.imageView2);
         imageView3= findViewById(R.id.imageView3);
         imageView4= findViewById(R.id.imageView4);
         imageView5= findViewById(R.id.imageView5);
        imageView6= findViewById(R.id.imageView6);
         imageView7= findViewById(R.id.imageView7);
        imageView8= findViewById(R.id.imageView8);
       imageView10= findViewById(R.id.imageView10);

       imageArray = new ImageView[] {imageView1, imageView2 , imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView10};

        hideImages();

       score = 0;


        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time:" +millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {

                timeText.setText("Time off");
                handler.removeCallbacks(runnable);
                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //restart

                        Intent intent = getIntent();
                        finish();  // güncel aktiviteyi bitirir
                        startActivity(intent); // kendi aktivitenizi baştan başlatırsınız

                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"GAME OVER" ,Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();

            }
        }.start();

    }

        public void increaseScore (View view){


        score++;  // score += 1;
        scoreText.setText("Score:" +score);
        }


        public void hideImages(){

        handler = new Handler();
        runnable= new Runnable() {
            @Override
            public void run() {
                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i= random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);


                handler.postDelayed(this,500); // 0.5 saniye sonra tekrar çalıştır

            }
        };
        handler.post(runnable); // sayaç başlat




        }
    }
