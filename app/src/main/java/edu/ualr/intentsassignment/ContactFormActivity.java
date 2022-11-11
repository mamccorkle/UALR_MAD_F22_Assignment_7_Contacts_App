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
import edu.ualr.intentsassignment.model.Contact;

public class ContactFormActivity extends AppCompatActivity {
    // TODO 06. Create a new method that reads the values in the several EditText elements of the layout and then uses the Contact class to send those data to ContactInfoActivity

    // Add the binder:
    private ActivityMainFormBinding binding;

    //
    public static final String EXTRA_CONTACT = "ContactData";

//    // !!REMOVE!! \\
//    public static final String EXTRA_FIRST_NAME = "first_name";
//    public static final String EXTRA_LAST_NAME  = "last_name";
//    public static final String EXTRA_PHONE      = "phone";
//    public static final String EXTRA_EMAIL      = "email";
//    public static final String EXTRA_ADDRESS    = "address";
//    public static final String EXTRA_WEBSITE    = "website";

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
        // Create the intent:
        Intent intent = new Intent( this, ContactInfoActivity.class );

        // Set the data for testing purposes:
        binding.tietFirstName.setText("Mark");
        binding.tietLastName.setText("McCorkle");
        binding.tietTelephoneNumber.setText("8707772341");
        binding.tietEmail.setText("me@you.com");
        binding.tietAddress.setText("123 Main Street");
        //binding.tietWebsite.setText("https://www.decyple.com");
        binding.tietWebsite.setText("www.decyple.com");

        // Add the data that is to be sent:

        //// Hardcoded:
        //intent.putExtra(EXTRA_FIRST_NAME, "Mark");
        //intent.putExtra(EXTRA_LAST_NAME, "McCorkle");
        //intent.putExtra(EXTRA_PHONE, "501-999-2313");
        //intent.putExtra(EXTRA_EMAIL, "me@you.com");
        //intent.putExtra(EXTRA_ADDRESS , "123 Main Street");
        //intent.putExtra(EXTRA_WEBSITE, "www.decyple.com");

        //// From the user:
        //intent.putExtra(EXTRA_FIRST_NAME, binding.tietFirstName.getText().toString());
        //intent.putExtra(EXTRA_LAST_NAME, binding.tietLastName.getText().toString());
        //intent.putExtra(EXTRA_PHONE, binding.tietTelephoneNumber.getText().toString());
        //intent.putExtra(EXTRA_EMAIL, binding.tietEmail.getText().toString());
        //intent.putExtra(EXTRA_ADDRESS , binding.tietAddress.getText().toString());
        //intent.putExtra(EXTRA_WEBSITE, binding.tietWebsite.getText().toString());

        // Via Class object:
        Contact contact = new Contact(
                                        binding.tietFirstName.getText().toString(),
                                        binding.tietLastName.getText().toString(),
                                        binding.tietTelephoneNumber.getText().toString(),
                                        binding.tietEmail.getText().toString(),
                                        binding.tietAddress.getText().toString(),
                                        binding.tietWebsite.getText().toString()
                                     );
        intent.putExtra(EXTRA_CONTACT, contact);

        // Start the new activity and send the data at the same time:
        startActivity( intent );
    }

}