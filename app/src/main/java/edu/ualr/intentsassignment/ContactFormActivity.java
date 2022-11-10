// https://github.com/UALR-MobileAppsDevelopmentCourse/assignment-07-mamccorkle
//
//  UALR - MAD - F22 - Assignment 7 - Android Contacts App
//
//  Project: UALR - Mobile Application Development - F22 - Assignment 7 - Android Contacts App
//  Created by: Mark McCorkle on 20221105
//  Based on: Code Provided by Dr Ivan Rodriguez-Conde
//
//  IDE:
//     Android Studio Chipmunk | 2021.2.1 Patch 2
//     Build #AI-212.5712.43.2112.8815526, built on July 10, 2022
//     Runtime version: 11.0.12+0-b1504.28-7817840 x86_64
//     VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
//     macOS 11.5.2
//     GC: G1 Young Generation, G1 Old Generation
//     Memory: 2048M
//     Cores: 8
//     Registry: external.system.auto.import.disabled=true
//
package edu.ualr.intentsassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.ualr.intentsassignment.databinding.ActivityMainFormBinding;

public class ContactFormActivity extends AppCompatActivity {
    // TODO 06. Create a new method that reads the values in the several EditText elements of the layout and then uses the Contact class to send those data to ContactInfoActivity

    // Add the binder:
    private ActivityMainFormBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        // TODO 02. Define the basic skeleton of the ContactFormActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
        binding = ActivityMainFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initComponent();
    }

    private void initComponent() {

        binding.btnSaveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveButtonClicked(v);
            }
        });

    }

    public void onSaveButtonClicked( View v ) {
        Intent intent = new Intent( this, ContactInfoActivity.class );
        startActivity( intent );
    }

}