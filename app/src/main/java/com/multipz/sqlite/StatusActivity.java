package com.multipz.sqlite;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StatusActivity extends AppCompatActivity {
    TextView txt_set;
    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        txt_set = (TextView) findViewById(R.id.txt_set);
        btn_save = (Button) findViewById(R.id.btn_save);

        String contain = getIntent().getStringExtra("contain");
        txt_set.setText(contain);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", txt_set.getText().toString());
                Toast.makeText(StatusActivity.this, "copy", Toast.LENGTH_SHORT).show();

                clipboard.setPrimaryClip(clip);
            }
        });

    }
}
