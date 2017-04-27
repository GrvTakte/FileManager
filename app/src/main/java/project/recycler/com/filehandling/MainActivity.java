package project.recycler.com.filehandling;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity{

    private static final int REQUEST_PICK_FILE = 1;
    public final static String EXTRA_FILE_PATH = "file_path";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button fileManager = (Button) findViewById(R.id.button_file_manager);
        Button folderManager = (Button) findViewById(R.id.button_file_manager_folder);
        Button extCard = (Button) findViewById(R.id.button_ext_card);
        Button buttonVideo = (Button) findViewById(R.id.button_video);


        fileManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getParent());
                intent.setDataAndType(uri, "text/*");
                startActivity(Intent.createChooser(intent, "Open folder"));
                Toast.makeText(getApplicationContext(),uri.toString(),Toast.LENGTH_LONG).show();

            }
        });


        folderManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

                String extStore = System.getenv("EXTERNAL_STORAGE");
                //String extStore = System.getenv("ext_card");

                File f_exts = new File(extStore);
                Uri uri = Uri.parse(extStore);
                intent.setDataAndType(uri, "text/*");
                startActivity(Intent.createChooser(intent, "Open"));
                Toast.makeText(getApplicationContext(),extStore,Toast.LENGTH_LONG).show();

            }
        });


        extCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getParent()+"/ext_card/");
                intent.setDataAndType(uri, "*/*");
                startActivityForResult(intent, 10);
                Toast.makeText(getApplicationContext(),uri.toString(),Toast.LENGTH_LONG).show();

                /*Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 10); */
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
        if(resultCode == RESULT_OK && requestCode == 10){
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
                ll.addView(myButton, lp); */

            }catch(IOException e){
                e.printStackTrace();
            }
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
