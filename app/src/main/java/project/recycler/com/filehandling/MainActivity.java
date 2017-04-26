package project.recycler.com.filehandling;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PICK_FILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button fileManager = (Button) findViewById(R.id.button_file_manager);
        Button folderManager = (Button) findViewById(R.id.button_file_manager_folder);
        Button extCard = (Button) findViewById(R.id.button_ext_card);

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
                startActivity(Intent.createChooser(intent, "Open"));
                Toast.makeText(getApplicationContext(),uri.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
