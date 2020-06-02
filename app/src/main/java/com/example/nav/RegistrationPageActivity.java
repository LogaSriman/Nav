package com.example.nav;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nav.util.RetroFitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RegistrationPageActivity extends AppCompatActivity {

    private static final String TAG = "RegistrationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_action);

        Spinner spinner = (Spinner)findViewById(R.id.spinner_gender);
        String defaultText ="Select Gender";
        ArrayList<String> myItems = new ArrayList<String>();
        myItems.add(0,defaultText);
        myItems.add("Male");
        myItems.add("Female");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, myItems);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setPrompt("Select gender");
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("Select Gender")){
                    //do nothing
                }else{
                    String item = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(RegistrationPageActivity.this, "Selected:" + item, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button registrationButton = (Button) findViewById(R.id.registrationButton);

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean validationSuccessful = validateData();
                if (validationSuccessful) {
                    Log.d(TAG, "Registration success");
                    validateRegistration();
                } else {
                    Log.d(TAG, "Registration error");
                    Toast.makeText(RegistrationPageActivity.this, "Please correct the errors", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void validateRegistration() {
        //registerUser();
        final EditText firstname = findViewById(R.id.firstname);
        final EditText lastname = findViewById(R.id.lastname);
        final Spinner gender = findViewById(R.id.spinner_gender);
        final EditText age = findViewById(R.id.age);
        final EditText email = findViewById(R.id.email);//Copy to username and make it unavailable to edit
        final EditText mobile = findViewById(R.id.mobile);
        final EditText username = findViewById(R.id.username);
        final EditText pwd = findViewById(R.id.password);

        if ((firstname.length() == 0) && (lastname.length() == 0) && (gender.getSelectedItem().toString().length() == 0) && (age.length() == 0) && email.length() == 0 &&
                (mobile.length() == 0) && (username.length() == 0) && (pwd.length() == 0)) {
            firstname.setError("Enter First Name");
            lastname.setError("Enter Last Name");
            gender.setPrompt("Enter Gender");
            age.setError("Enter Age");
            email.setError("Enter Email ID");
            mobile.setError("Enter Mobile");
            username.setError("Enter Username");
            pwd.setError("Enter Password");
            Log.d(TAG, "Login error");
        } else if (firstname.length() == 0) {
            pwd.setError("Enter First Name");
            Log.d(TAG, "Login error");
        } else if (email.length() == 0) {
            lastname.setError("Enter Last Name");
            Log.d(TAG, "Login error");
        } else if (pwd.length() == 0) {
            gender.setPrompt("Enter Gender");
            Log.d(TAG, "Login error");
        } else if (email.length() == 0) {
            age.setError("Enter Age");
            Log.d(TAG, "Login error");
        } else if (email.length() == 0) {
            email.setError("Enter Email");
            Log.d(TAG, "Login error");
        } else if (mobile.length() == 0) {
            mobile.setError("Enter Mobile");
            Log.d(TAG, "Login error");
        } else if (username.length() == 0) {
            username.setError("Enter User name");
            Log.d(TAG, "Login error");
        } else if (pwd.length() == 0) {
            pwd.setError("Enter Password");
            Log.d(TAG, "Login error");
        } else {
            registerUser(firstname,lastname,gender,age,email,mobile,username,pwd);
        }
    }

    private void registerUser(EditText firstname, EditText lastname, Spinner gender, EditText age, EditText email, EditText mobile,EditText username,  EditText pwd) {
        String firstnameVal = firstname.getText().toString();
        String lastnameVal = lastname.getText().toString();
        String genderVal = gender.getSelectedItem().toString();
        String ageVal = age.getText().toString();
        String emailVal = email.getText().toString();
        String mobileVal = mobile.getText().toString();
        String userNameVal = firstname.getText().toString();
        String pwdVal = lastname.getText().toString();

        JSONObject jsonParams = new JSONObject();

        try {
            jsonParams.put("firstname", firstnameVal);
            jsonParams.put("lastname", lastnameVal);
            jsonParams.put("gender", genderVal);
            jsonParams.put("age", ageVal);
            jsonParams.put("email", emailVal);
            jsonParams.put("mobileNumber", new Long(mobileVal));
            jsonParams.put("username", userNameVal);
            jsonParams.put("password", pwdVal);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams.toString());
        Call<ResponseBody> call = RetroFitClient.getMyInstance().getApi().registerUser(body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    //Check response,
                    if (response.code() == 500) {
                        Toast.makeText(RegistrationPageActivity.this, "Invalid Email ID / Password", Toast.LENGTH_LONG).show();
                        openLoginPage();
                    } else {
                        Log.d(TAG, "Register user" + response.body().string());
                        Toast.makeText(RegistrationPageActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                        openHomePage();
                    }
                } catch (IOException e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RegistrationPageActivity.this, "Registration Unsuccessful", Toast.LENGTH_LONG).show();
                Log.e("Error", t.getMessage());
                t.printStackTrace();
            }
        });

    }

    public boolean validateData() {
        boolean status = true;
        //Validations
        return status;
    }

    private void openLoginPage() {
        Log.d(TAG, "Go to Login screen");
        Intent intent3 = new Intent(this, LoginActivity.class);
        startActivity(intent3);
    }

    public void openHomePage() {
        Log.d(TAG, "Go to home screen");
        Intent intent3 = new Intent(this, MainActivity.class);
        startActivity(intent3);
    }
}
