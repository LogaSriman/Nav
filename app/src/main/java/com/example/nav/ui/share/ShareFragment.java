package com.example.nav.ui.share;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.nav.ListdataActivity;
import com.example.nav.R;
import com.example.nav.ui.home.HomeFragment;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;
    ListView listView;
    String[] reportNames = {"Medical Report 1", "Medical Report 2", "Medical Report 3"};
    int[] reportImages = {R.drawable.medicalreportimage1, R.drawable.medicalreportimage1, R.drawable.medicalreportimage1};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);

        listView = root.findViewById(R.id.listviewShare);
        ShareFragment.CustomAdapter customAdapter = new ShareFragment.CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),fruitNames[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), ListdataActivity.class);
                intent.putExtra("name", reportNames[i]);
                intent.putExtra("image", reportImages[i]);
                startActivity(intent);

            }
        });

        final TextView textView = root.findViewById(R.id.text_share);
        shareViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return reportImages.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data, null);
            //getting view in row_data
            TextView name = view1.findViewById(R.id.reports);
            ImageView image = view1.findViewById(R.id.images);

            name.setText(reportNames[i]);
            image.setImageResource(reportImages[i]);
            return view1;
        }
    }
}
