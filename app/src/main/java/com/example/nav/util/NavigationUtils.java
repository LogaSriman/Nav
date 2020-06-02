package com.example.nav.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nav.LoginActivity;
import com.example.nav.MainActivity;
import com.example.nav.RegistrationPageActivity;

public class NavigationUtils extends AppCompatActivity {
    private static final String TAG = "NavigationUtils";

    public NavigationUtils() {

    }

    public static void makeToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
