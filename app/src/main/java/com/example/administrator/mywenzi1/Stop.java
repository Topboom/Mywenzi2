package com.example.administrator.mywenzi1;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Stop extends AppCompatActivity{
    private TextView textView3;
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop);
        button=(Button)findViewById(R.id.button);
        textView3=(TextView)findViewById(R.id.textView3);
        final String i=getIntent().getStringExtra("name");
        textView3.setText("感谢您为世界消灭了"+i+"只蚊子~");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Stop.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
