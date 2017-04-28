package project.recycler.com.filehandling;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button buttonVideo = (Button) findViewById(R.id.button_video);
        Button buttonPdf = (Button) findViewById(R.id.button_pdf);
        Button buttonGame = (Button) findViewById(R.id.button_game);


        Button buttonMovies = (Button) findViewById(R.id.button_movies);
        Button buttonMusic = (Button) findViewById(R.id.button_music);
        Button buttonNews = (Button) findViewById(R.id.button_news_paper);



        buttonGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                PackageManager manager = getPackageManager();
                try {
                    i = manager.getLaunchIntentForPackage("com.king.candycrushsaga");
                    if (i == null)
                        throw new PackageManager.NameNotFoundException();
                    i.addCategory(Intent.CATEGORY_LAUNCHER);
                    startActivity(i);
                } catch (PackageManager.NameNotFoundException e) {

                }
            }
        });

        buttonPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getParent()+"/ext_card");
                intent.setDataAndType(uri,"file/pdf");
                startActivityForResult(intent,10);
            }
        });

        buttonVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getParent()+"/ext_card");
                intent.setDataAndType(uri,"video/*");
                startActivityForResult(intent,20);

            }
        });

        buttonMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri  = Uri.parse(Environment.getExternalStorageDirectory().getParent()+"/ext_card/Movies");
                intent.setDataAndType(uri,"file/video");
                startActivityForResult(intent,30);
            }
        });

        buttonMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getParent()+"/ext_card/Music");
                intent.setDataAndType(uri,"file/audio");
                startActivityForResult(intent,40);
            }
        });

        buttonNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getParent()+"/ext_card/NewsPaper");
                intent.setDataAndType(uri,"file/pdf");
                startActivityForResult(intent,50);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

       if (resultCode == RESULT_OK && requestCode == 10){
           Uri uriPdf = data.getData();
           Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
           pdfIntent.setDataAndType(uriPdf,"application/pdf");
           startActivity(pdfIntent);
       }

       if (resultCode == RESULT_OK && requestCode == 20){
           Uri uriVideo = data.getData();
           Intent tostart = new Intent(Intent.ACTION_VIEW);
           tostart.setDataAndType(uriVideo,"video/*");
           startActivity(tostart);

       }

       if(resultCode == RESULT_OK && requestCode== 30){
            Uri uriVidio = data.getData();
           Intent videoIntent = new Intent(Intent.ACTION_VIEW);
           videoIntent.setDataAndType(uriVidio,"application/video");
           startActivity(videoIntent);
       }

       if(resultCode == RESULT_OK && requestCode == 40){
            Uri uriAudio = data.getData();
           Intent audioIntent = new Intent(Intent.ACTION_VIEW);
           audioIntent.setDataAndType(uriAudio,"application/audio");
           startActivity(audioIntent);
       }

       if(resultCode == RESULT_OK && requestCode == 50){
           Uri uriPDF = data.getData();
           Intent pDfIntent = new Intent(Intent.ACTION_VIEW);
           pDfIntent.setDataAndType(uriPDF,"application/pdf");
           startActivity(pDfIntent);
       }

    }

}
