package in.tushar.singaporeboothallocation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Booth extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;
    public static final String TAG = "Booth-----";
    ListView listView;
    String[] data;
    ArrayAdapter<String> arrayAdapter;
    String[] listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_booth);
        listView = findViewById(R.id.list);
        csvRead();
//        arrayAdapter = new ArrayAdapter<String>(this,R.layout.my_custom_list,R.layout.m);
        listView.setAdapter(arrayAdapter);
    }

    public void csvRead() {
        try {
            InputStream inputStream = getAssets().open("booth.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String csvLine;
            Boolean fistLine = true;
            while ((csvLine = br.readLine()) != null) {
                if (fistLine) {
                    fistLine = false;
                    continue;
                }
                data = csvLine.split(",");
                Log.e(TAG, String.valueOf(">>>>" + data.length));
                try {
                    String name = data[1];
                    Log.e(TAG, String.valueOf(">>>>" + data[0] + "," + data[1]+" , "+data[2]));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
