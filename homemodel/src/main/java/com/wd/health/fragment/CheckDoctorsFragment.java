package com.wd.health.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.health.R;
import com.wd.health.R2;


public class CheckDoctorsFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R2.layout.fragment_check_doctors, container, false);
        TextView viewById = inflate.findViewById(R.id.check_doctors);
        Bundle bundle = new Bundle();
        String position = bundle.getString("position");
        viewById.setText(position);
        return inflate;
    }

}
