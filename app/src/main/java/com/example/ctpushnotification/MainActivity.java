package com.example.ctpushnotification;


import androidx.appcompat.app.AppCompatActivity;
import com.clevertap.android.sdk.CleverTapAPI;

import android.app.NotificationManager;
import android.os.Bundle;
import com.clevertap.android.sdk.CleverTapAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        clevertapDefaultInstance.setDebugLevel(3);

        HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
//Update pre-defined profile properties
        profileUpdate.put("Name", "Jim Kim");
        profileUpdate.put("Email", "jim@gmail.com");
//Update custom profile properties
        profileUpdate.put("Plan Type", "Gold");
        profileUpdate.put("Favorite Food", "Pizza");
        // each of the below mentioned fields are optional
// if set, these populate demographic information in the Dashboard
        // each of the below mentioned fields are optional
// if set, these populate demographic information in the Dashboard

        profileUpdate.put("Identity", 610276032);                    // String or number
        profileUpdate.put("Phone", "+141555951234");                 // Phone (with the country code, starting with +)
        profileUpdate.put("Gender", "M");                           // Can be either M or F
        profileUpdate.put("Employed", "Y");                         // Can be either Y or N
        profileUpdate.put("Education", "Graduate");                 // Can be either Graduate, College or School
        profileUpdate.put("Married", "Y");                          // Can be either Y or N
        profileUpdate.put("DOB", new Date());                       // Date of Birth. Set the Date object to the appropriate value first
        profileUpdate.put("Tz", "Asia/Kolkata");                    //an abbreviation such as "PST", a full name such as "America/Los_Angeles",
        //or a custom ID such as "GMT-8:00"
        profileUpdate.put("Photo", "www.foobar.com/image.jpeg");    // URL to the Image

// optional fields. controls whether the user will be sent email, push etc.
        profileUpdate.put("MSG-email", false);                      // Disable email notifications
        profileUpdate.put("MSG-push", true);                        // Enable push notifications
        profileUpdate.put("MSG-sms", false);                        // Disable SMS notifications
        profileUpdate.put("MSG-dndPhone", true);                  // Opt out phone                                                                    number from SMS                                                                  notifications
        profileUpdate.put("MSG-dndEmail", true);                  // Opt out phone                                                                    number from SMS                                                                  notifications
        ArrayList<String> stuff = new ArrayList<String>();
        stuff.add("bag");
        stuff.add("shoes");
        profileUpdate.put("MyStuff", stuff);                        //ArrayList of Strings

        String[] otherStuff = {"Jeans","Perfume"};
        profileUpdate.put("MyStuff", otherStuff);                   //String Array

        clevertapDefaultInstance.onUserLogin(profileUpdate);
        //clevertapDefaultInstance.pushProfile(profileUpdate);
        // event without properties
        clevertapDefaultInstance.pushEvent("Product viewed");
        // event with properties
        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put("Product Name", "Casio Chronograph Watch");
        prodViewedAction.put("Category", "Mens Accessories");
        prodViewedAction.put("Price", 59.99);
        prodViewedAction.put("Date", new java.util.Date());

        clevertapDefaultInstance.pushEvent("Product viewed", prodViewedAction);
        CleverTapAPI.createNotificationChannel(getApplicationContext(),"1234","My channel","Your Channel Description",NotificationManager.IMPORTANCE_MAX,true);


    }
}