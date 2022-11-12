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

import java.util.Objects;

import edu.ualr.intentsassignment.databinding.ActivityMainFormBinding;
import edu.ualr.intentsassignment.model.Contact;

public class ContactFormActivity extends AppCompatActivity {

    // Add the binder:
    private ActivityMainFormBinding binding;

    //
    public static final String EXTRA_CONTACT = "ContactData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO 02. Define the basic skeleton of the ContactFormActivity. Inflate the layout defined
        //          in the first step to display the GUI elements on screen.
        binding = ActivityMainFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initComponent();
    }

    private void initComponent() {
        binding.btnSaveContact.setOnClickListener(this::onSaveButtonClicked);
    }

    public void onSaveButtonClicked( View v ) {
        // Create the intent:
        Intent intent = new Intent( this, ContactInfoActivity.class );

        // TODO 06. Create a new method that reads the values in the several EditText elements of
        //          the layout and then uses the Contact class to send those data to
        //          ContactInfoActivity
        Contact contact = new Contact(
                                        Objects.requireNonNull(binding.tietFirstName.getText()).toString(),
                                        Objects.requireNonNull(binding.tietLastName.getText()).toString(),
                                        Objects.requireNonNull(binding.tietTelephoneNumber.getText()).toString(),
                                        Objects.requireNonNull(binding.tietEmail.getText()).toString(),
                                        Objects.requireNonNull(binding.tietAddress.getText()).toString(),
                                        Objects.requireNonNull(binding.tietWebsite.getText()).toString()
                                     );
        intent.putExtra(EXTRA_CONTACT, contact);

        // Start the new activity and send the data at the same time:
        startActivity( intent );
    }
}