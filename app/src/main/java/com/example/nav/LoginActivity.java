package com.example.nav;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nav.util.NavigationUtils;
import com.example.nav.util.RetroFitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    NavigationUtils nav = new NavigationUtils();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        Log.d(TAG, "Login screen");
        Button submitButton = (Button) findViewById(R.id.submitButton);
        TextView signupButton = (TextView) findViewById(R.id.signupButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                validateLogin();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegistrationPage();
            }
        });
    }

    public void validateLogin() {
        final EditText pwd = findViewById(R.id.password);
        final EditText email = findViewById(R.id.email);

        if ((email.length() == 0) && (pwd.length() == 0)) {
            email.setError("Enter Email ID");
            pwd.setError("Enter Password");
            Log.d(TAG, "Login error");
        } else if (pwd.length() == 0) {
            pwd.setError("Enter Password");
            Log.d(TAG, "Login error");
        } else if (email.length() == 0) {
            email.setError("Enter Email ID");
            Log.d(TAG, "Login error");
        } else {
            String emailValue = email.getText().toString();
            String pwdValue = pwd.getText().toString();
            checkUserInformation(emailValue,pwdValue);
        }
    }

    private void checkUserInformation(String username, String password) {
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("username", username);
            jsonParams.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams.toString());
        Call<ResponseBody> call = RetroFitClient.getMyInstance().getApi().loginUser(body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    if(response.code() == 500){
                        Toast.makeText(LoginActivity.this, "Invalid Email ID / Password", Toast.LENGTH_LONG).show();
                        openLoginPage();
                    }else {
                        Log.d(TAG, "Login " + response.body().string());
                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        openHomePage();
                    }
                } catch (IOException e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_LONG).show();
                Log.e("Error", t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private void openLoginPage() {
        Log.d(TAG, "Go to Login screen");
        Intent intent3 = new Intent(this, LoginActivity.class);
        startActivity(intent3);
    }

    public void openRegistrationPage() {
        Log.d(TAG, "Go to Registration screen");
        Intent intent2 = new Intent(this, RegistrationPageActivity.class);
        startActivity(intent2);
    }

    public void openHomePage() {
        Log.d(TAG, "Go to home screen");
        Intent intent3 = new Intent(this, MainActivity.class);
        startActivity(intent3);
    }
}
