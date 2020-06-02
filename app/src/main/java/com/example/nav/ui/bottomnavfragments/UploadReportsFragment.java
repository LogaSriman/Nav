package com.example.nav.ui.bottomnavfragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.nav.MainActivity;
import com.example.nav.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UploadReportsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UploadReportsFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UploadReportsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static UploadReportsFragment newInstance(String param1, String param2) {
        UploadReportsFragment fragment = new UploadReportsFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_reports, container, false);

        Button button = view.findViewById(R.id.opendate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getActivity().getSupportFragmentManager(), "date picker");
            }
        });

        ImageSlider imageSlider = view.findViewById(R.id.slider_upload);

        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.file1));
        slideModels.add(new SlideModel(R.drawable.file2));
        slideModels.add(new SlideModel(R.drawable.file3));
        slideModels.add(new SlideModel(R.drawable.file4));
        slideModels.add(new SlideModel(R.drawable.file5));

        imageSlider.setImageList(slideModels, true);
        Button uploadButton = (Button) view.findViewById(R.id.uploadButton);
        uploadButton.setOnClickListener(this);
        return view;
    }

    public void openHomePage() {
        Intent myIntent = new Intent(getActivity(), MainActivity.class);
        getActivity().startActivity(myIntent);
    }

    @Override
    public void onClick(View v) {
        //do what you want to do when button is clicked
        switch (v.getId()) {
            case R.id.uploadButton:
                Toast.makeText(getActivity(), "Uploaded Report successfully", Toast.LENGTH_SHORT).show();
                openHomePage();
                break;
        }
    }

}
