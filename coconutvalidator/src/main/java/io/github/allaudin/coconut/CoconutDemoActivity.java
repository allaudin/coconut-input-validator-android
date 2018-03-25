package io.github.allaudin.coconut;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

public class CoconutDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coconut_demo);
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoconutValidator.areFieldsValidRecursive(
                        (ViewGroup) findViewById(R.id.content));
            }
        });
    } // onCreate

} // CoconutDemoActivity
