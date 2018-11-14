package com.adair.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.adair.util.constant.MemoryConstant;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate: " + MemoryConstant.convert(565558L));
        Log.e(TAG, "onCreate: " + MemoryConstant.convert(56544645645645L, MemoryConstant.MB));
    }

}
