package com.example.nav.ui.bottomnavfragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nav.MainActivity;
import com.example.nav.R;
import com.example.nav.RegistrationPageActivity;
import com.example.nav.util.RetroFitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddDependentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddDependentsFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "Add dependents Fragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddDependentsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddDependentsFragment newInstance(String param1, String param2) {
        AddDependentsFragment fragment = new AddDependentsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void openHomePage() {
        Intent myIntent = new Intent(getActivity(), MainActivity.class);
        getActivity().startActivity(myIntent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_dependents, container, false);
        Button addDependentsButton = (Button) view.findViewById(R.id.addDependentsButton);
        addDependentsButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        //do what you want to do when button is clicked
        switch (v.getId()) {
            case R.id.addDependentsButton:
                validateAddDependent(v);
                break;
        }
    }

    public void validateAddDependent(View v) {
        EditText name = v.getRootView().findViewById(R.id.name_add);
        EditText gender = v.getRootView().findViewById(R.id.gender_add);
        EditText age = v.getRootView().findViewById(R.id.age_add);
        EditText email = v.getRootView().findViewById(R.id.email_add);//Copy to username and make it unavailable to edit
        EditText mobile = v.getRootView().findViewById(R.id.mobile_add);
        EditText relationship = v.getRootView().findViewById(R.id.relationship_add);

        if ((name.length() == 0) && (gender.length() == 0) && (age.length() == 0) && (email.length() == 0) &&
                (mobile.length() == 0) && (relationship.length() == 0)) {
            relationship.setError("Enter Relationship");
            name.setError("Enter Name");
            gender.setError("Enter Gender");
            age.setError("Enter Age");
            email.setError("Enter Email ID");
            mobile.setError("Enter Mobile");
            Log.d(TAG, "Login error");
        } else if (name.length() == 0) {
            name.setError("Enter Name");
            Log.d(TAG, "Login error");
        } else if (gender.length() == 0) {
            gender.setError("Enter Gender");
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
        } else if (relationship.length() == 0) {
            relationship.setError("Enter Relationship");
            Log.d(TAG, "Login error");
        } else {
            addDependent(name, gender, age, email, mobile, relationship);
        }
    }

    private void addDependent(EditText name, EditText gender, EditText age, EditText email, EditText mobile, EditText relationship) {
        String nameVal = name.getText().toString();
        String relationshipVal = relationship.getText().toString();
        String genderVal = gender.getText().toString();
        String ageVal = age.getText().toString();
        String emailVal = email.getText().toString();
        String mobileVal = mobile.getText().toString();

        JSONObject jsonParams = new JSONObject();

        try {
            jsonParams.put("name", nameVal);
            jsonParams.put("gender", genderVal);
            jsonParams.put("age", ageVal);
            jsonParams.put("email", emailVal);
            jsonParams.put("mobileNumber", new Long(mobileVal));
            jsonParams.put("relationship", relationshipVal);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams.toString());
        Call<ResponseBody> call = RetroFitClient.getMyInstance().getApi().addDependent(body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    //Check response,
                    if (response.code() == 500) {
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
                    } else {
                        Log.d(TAG, "Add Dependents " + response.body().string());
                        Toast.makeText(getActivity(), "Added Dependents Successfully!", Toast.LENGTH_SHORT).show();
                        openHomePage();
                    }
                } catch (IOException e) {
                    Log.e("Error in add dependents", e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), "Added Dependents Unsuccessful", Toast.LENGTH_LONG).show();
                Log.e("Error", t.getMessage());
                t.printStackTrace();
            }
        });

    }
}

