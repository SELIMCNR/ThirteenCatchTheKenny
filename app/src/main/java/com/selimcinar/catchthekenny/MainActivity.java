package com.selimcinar.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Global tanımlamalar
    TextView scoreText;
    TextView timeText;
    int score;
    ImageView imageView;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;

    ImageView[] imageArray;
    Runnable runnable; //Her saniyede olacak işlemlerde kullanılır
    Handler handler; //runnableyi yönetir.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Başlatma tanımları idleri alma
        timeText = (TextView) findViewById(R.id.timeText);


        scoreText = (TextView) findViewById(R.id.scoreText);

        imageView=findViewById(R.id.imageView);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);

        //Dizi tanımlamak ve değer eklemek
        imageArray=new ImageView[]{imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};

        //resimleri gizleme
        hideImages();

        score = 0;


        //Zamanlayıcı geri sayım yapar
        new CountDownTimer(60000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                //Her bir saniyede ne olacak
                timeText.setText("Time: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                //Bitince ne olacak
                //timeTexte yazı yaz
                timeText.setText("Time Off");
                //runnable'ı handler ile durdur.
                handler.removeCallbacks(runnable);
                //Resimleri gizle
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
               
                //Uyarı mesajları gösterme
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart ?");
                alert.setMessage("Are you sure to restart game ?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //restart yeniden başlatma
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Game Over !",Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
            }
        }.start();

    }
    public void ıncreaseScore (View view){
        //Tıklanınca skoru artır
        score++;
        scoreText.setText("Score "+score);
    }
    public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                //Resimleri gizleme
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                //Rastgele sayı oluşturur,Random.Ve resimleri görünür yapar.
                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);//buradaki rakam azaltılarak yeni zor seviyeler yapılabilir.

            }
        };
            handler.post(runnable);

    }


}
