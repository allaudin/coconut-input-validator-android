package io.github.allaudin.demo;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import io.github.allaudin.coconut.CoconutValidator;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewGroup root = findViewById(R.id.content);
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean areFieldsValid = CoconutValidator.areFieldsValidRecursive(root);
                if (areFieldsValid) {
                    Snackbar.make(root, R.string.all_ok, Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });
    } // onCreate
}
