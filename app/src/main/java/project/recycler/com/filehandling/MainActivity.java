package project.recycler.com.filehandling;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity{

    private static final int REQUEST_PICK_FILE = 1;
    public final static String EXTRA_FILE_PATH = "file_path";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button buttonVideo = (Button) findViewById(R.id.button_video);
        Button buttonPdf = (Button) findViewById(R.id.button_pdf);
        Button buttonGame = (Button) findViewById(R.id.button_game);




      /*  extCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getParent()+"/ext_card/"); */
                //intent.setDataAndType(uri, "*/*");
               /* startActivityForResult(intent, 10);
                Toast.makeText(getApplicationContext(),uri.toString(),Toast.LENGTH_LONG).show();

                /*Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 10);
            }
        }); */

        buttonGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                PackageManager manager = getPackageManager();
                try {
                    i = manager.getLaunchIntentForPackage("com.snakegame.freesnakegames");
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


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
       /* if(resultCode == RESULT_OK && requestCode == 10){
            try {
                Uri uriSound = data.getData();
               final MediaPlayer mp = new MediaPlayer();
                mp.setDataSource(uriSound.toString());
                mp.prepare();
               mp.start();

               /* Button myButton = new Button(this);
                myButton.setText("Add Me");

                LinearLayout ll = (LinearLayout)findViewById(R.id.buttonlayout);
                LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                ll.addView(myButton, lp);

            }catch(IOException e){
                e.printStackTrace();
            }
        } */

       if (resultCode == RESULT_OK && requestCode == 10){
           Uri uriPdf = data.getData();
           Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
           pdfIntent.setDataAndType(uriPdf,"application/pdf");
           startActivity(pdfIntent);
       }

        if (resultCode == RESULT_OK && requestCode == 20){

                Uri uriVideo = data.getData();

                /* VideoView videoView = (VideoView) findViewById(R.id.video_view);
                videoView.setVideoURI(uriVideo);
                MediaController vidControl = new MediaController(this);
                vidControl.setAnchorView(videoView);
                videoView.setMediaController(vidControl);
                videoView.start(); */

          Intent tostart = new Intent(Intent.ACTION_VIEW);
            tostart.setDataAndType(uriVideo,"video/*");
            startActivity(tostart);


        }
    }

}
