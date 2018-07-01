package io.github.allaudin.demo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import io.github.allaudin.coconut.CoconutValidator;
import io.github.allaudin.demo.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean areFieldsValid = CoconutValidator.validateLayout(binding.getRoot());
                if (areFieldsValid) {
                    Snackbar.make(binding.getRoot(), R.string.all_ok, Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });
    } // onCreate
}
