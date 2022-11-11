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

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import edu.ualr.intentsassignment.databinding.ActivityMainInfoBinding;
import edu.ualr.intentsassignment.model.Contact;

public class ContactInfoActivity extends AppCompatActivity {
    // TODO 07. Create a new method that reads the contact info coming from ContactFormActivity and use it to populate the several TextView elements in the layout.
    // TODO 08. Create a new method that invokes a Phone Dialer app, using as parameter the phone number included in the contact info received from ContactFormActivity in the previous step
    // TODO 09. Create a new method that invokes a Messages app, using as parameter the phone number included in the contact info received from ContactFormActivity in the 7th step
    // TODO 10. Create a new method that invokes a Maps app, using as parameter the address included in the contact info received from ContactFormActivity in the 7th step
    // TODO 11. Create a new method that invokes an Email app, using as parameter the email address included in the contact info received from ContactFormActivity in the 7th step
    // TODO 12. Create a new method that invokes an Web Browser app, using as parameter the web url included in the contact info received from ContactFormActivity in the 7th step

    // Add the binder:
    private ActivityMainInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_contact);
        // TODO 04. Define the basic skeleton of the ContactInfoActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
        binding = ActivityMainInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        Intent intent = getIntent();
//
//        String EXTRA_FIRST_NAME = intent.getStringExtra(ContactFormActivity.EXTRA_FIRST_NAME);
//        String EXTRA_LAST_NAME  = intent.getStringExtra(ContactFormActivity.EXTRA_LAST_NAME);
//        String EXTRA_PHONE      = intent.getStringExtra(ContactFormActivity.EXTRA_PHONE);
//        String EXTRA_EMAIL      = intent.getStringExtra(ContactFormActivity.EXTRA_EMAIL);
//        String EXTRA_ADDRESS    = intent.getStringExtra(ContactFormActivity.EXTRA_ADDRESS);
//        String EXTRA_WEBSITE    = intent.getStringExtra(ContactFormActivity.EXTRA_WEBSITE);
//
//        binding.tvName.setText(EXTRA_FIRST_NAME + " " + EXTRA_LAST_NAME);
//        binding.tilTelephoneNumber.setText(EXTRA_PHONE);
//        binding.tilEmail.setText(EXTRA_EMAIL);
//        binding.tilAddress.setText(EXTRA_ADDRESS);
//        binding.tilWebsite.setText(EXTRA_WEBSITE);

        Contact contact = getIntent().getParcelableExtra(ContactFormActivity.EXTRA_CONTACT);
        binding.tvName.setText(contact.getFirstName() + " " + contact.getLastName());
        binding.tilTelephoneNumber.setText(contact.getPhoneNumber());
        binding.tilEmail.setText(contact.getEmailAddress());
        binding.tilAddress.setText(contact.getAddress());
        binding.tilWebsite.setText(contact.getWebsite());

        initComponent();
    }

    private void initComponent() {

        binding.chpCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCallChipClicked(v);
            }
        });

        binding.chpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTextChipClicked(v);
            }
        });

        binding.chpEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmailChipClicked(v);
            }
        });

        binding.chpMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMapChipClicked(v);
            }
        });

        binding.chpWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWebChipClicked(v);
            }
        });
    }

    public void onCallChipClicked( View v ) {
        Uri number = Uri.parse("tel:" + binding.tilTelephoneNumber.getText());
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

    public void onTextChipClicked( View v ) {
        String smsUri = "smsto:501-432-4129";
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(smsUri));
        intent.putExtra("sms_body", "Have you done last week's homework?");
        startActivity(intent);
    }

    public void onEmailChipClicked( View v ) {
        String emailSubject = "Assignment 7 - Android Contacts App";
        String emailText = "Hi, hope all is well.\n\nI would like to set up a meeting next week. We will need to discuss Assignment 7 on Tue. at 9:00am. I am having trouble understanding how this email thing works...\n\nThanks,\nMM";
        String emailReceiverList[] = {"irconde@ualr.edu"};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, emailReceiverList);
        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        startActivity(Intent.createChooser(intent, "To complete action choose:"));
    }

    public void onMapChipClicked( View v ) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, binding.tilAddress.getText());
        startActivity(intent);
    }

    public void onWebChipClicked( View v ) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, binding.tilWebsite.getText());
        startActivity(intent);
    }

}
