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

    // Add the binder:
    private ActivityMainInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO 04. Define the basic skeleton of the ContactInfoActivity. Inflate the layout defined
        //          in the first step to display the GUI elements on screen.
        binding = ActivityMainInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // TODO 07. Create a new method that reads the contact info coming from ContactFormActivity
        //          and use it to populate the several TextView elements in the layout.
        Contact contact = getIntent().getParcelableExtra(ContactFormActivity.EXTRA_CONTACT);
        binding.tvName.setText(contact.getFullName());
        binding.tilTelephoneNumber.setText(contact.getPhoneNumber());
        binding.tilEmail.setText(contact.getEmailAddress());
        binding.tilAddress.setText(contact.getAddress());
        binding.tilWebsite.setText(contact.getWebsite());

        initComponent();
    }

    private void initComponent() {

        binding.chpCall.setOnClickListener(this::onCallChipClicked);

        binding.chpText.setOnClickListener(this::onTextChipClicked);

        binding.chpEmail.setOnClickListener(this::onEmailChipClicked);

        binding.chpMap.setOnClickListener(this::onMapChipClicked);

        binding.chpWeb.setOnClickListener(this::onWebChipClicked);
    }

    public void onCallChipClicked( View v ) {
        // TODO 08. Create a new method that invokes a Phone Dialer app, using as parameter the
        //          phone number included in the contact info received from ContactFormActivity in
        //          the previous step
        Uri number = Uri.parse(getResources().getString(R.string.tel) + binding.tilTelephoneNumber.getText());
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

    public void onTextChipClicked( View v ) {
        // TODO 09. Create a new method that invokes a Messages app, using as parameter the phone
        //          number included in the contact info received from ContactFormActivity in the
        //          7th step
        String smsUri = getResources().getString(R.string.txt) + binding.tilTelephoneNumber.getText();
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(smsUri));
        intent.putExtra(getResources().getString(R.string.txt_body), getResources().getString(R.string.txt_msg));
        startActivity(intent);
    }

    public void onEmailChipClicked( View v ) {
        // TODO 11. Create a new method that invokes an Email app, using as parameter the email
        //          address included in the contact info received from ContactFormActivity in the
        //          7th step
        String emailSubject = getResources().getString(R.string.email_subject);
        String emailText = getResources().getString(R.string.email_text);
        String[] emailReceiverList = {getResources().getString(R.string.email_to)};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, emailReceiverList);
        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        startActivity(Intent.createChooser(intent, getResources().getString(R.string.email_chooser)));
    }

    public void onMapChipClicked( View v ) {
        // TODO 10. Create a new method that invokes a Maps app, using as parameter the address
        //          included in the contact info received from ContactFormActivity in the 7th step
        String place = binding.tilAddress.getText().toString();
        String placeUri = String.format("geo:0,0?q=(%s)", place);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(placeUri));
        startActivity(intent);
    }

    public void onWebChipClicked( View v ) {
        // TODO 12. Create a new method that invokes an Web Browser app, using as parameter the web
        //          url included in the contact info received from ContactFormActivity in the 7th
        //          step
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, binding.tilWebsite.getText());
        startActivity(intent);
    }

}
