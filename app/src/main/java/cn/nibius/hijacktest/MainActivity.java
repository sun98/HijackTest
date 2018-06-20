package cn.nibius.hijacktest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {
    private TextView textUP;
    public static Map<String, String> up = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("nib", "main onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent serviceIntent = new Intent(getApplicationContext(), HijackService.class);
        startService(serviceIntent);
        Button btnReset = findViewById(R.id.button);
        textUP=findViewById(R.id.textView);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HijackService.isHijacked = false;
                update();
            }
        });


    }

    private void update() {
        final StringBuilder record = new StringBuilder();
        for (Map.Entry<String, String> en : up.entrySet()) {
            record.append(en.getKey()).append(": ").append(en.getValue()).append("\n");
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textUP.setText(record.toString());
            }
        });
    }
}
