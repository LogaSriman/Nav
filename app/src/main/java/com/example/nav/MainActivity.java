package com.example.nav;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";
    private AppBarConfiguration mAppBarConfiguration;
    private AppBarConfiguration mbottomAppBarConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_suggestions, R.id.nav_share)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        mbottomAppBarConfig = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_suggestions, R.id.nav_share)
                .setDrawerLayout(drawer)
                .build();
        NavController bottomNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, bottomNavController, mbottomAppBarConfig);
        NavigationUI.setupWithNavController(bottomNavigationView, bottomNavController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                Toast.makeText(MainActivity.this, "Logged Out!", Toast.LENGTH_SHORT).show();
                openLoginPage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void openLoginPage() {
        Intent intent3 = new Intent(this, LoginActivity.class);
        startActivity(intent3);
    }

    public void openHomePage() {
        Intent intent3 = new Intent(this, MainActivity.class);
        startActivity(intent3);

                              /* // Call<Object> call = RetroFitClient.getMyInstance().getApi().getDetails();
                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                EditText pwd = findViewById(R.id.password);
                EditText email = findViewById(R.id.email);
                String username = email.getText().toString();
                String password = pwd.getText().toString();
                username = "admin@gmail.com";
                password = "admin";

                //JSON
                /*JSONObject paramObject = new JSONObject();
                try {
                    paramObject.put("email", "bbbb@gamil.com");
                    paramObject.put("name", "dddd");
                    paramObject.put("age", 21);
                    paramObject.put("mobileNumber", new BigInteger(String.valueOf(1234567890)));
                    paramObject.put("gender", "m");
                    paramObject.put("relationship", "father");
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

// Call<Object> call = RetroFitClient.getMyInstance().getApi().postDetails(paramObject.toString());

    }
}
