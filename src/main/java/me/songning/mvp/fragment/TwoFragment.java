package me.songning.mvp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.songning.mvp.R;


public class TwoFragment extends Fragment {
    public static TwoFragment newInstance(String s){
        TwoFragment homeFragment = new TwoFragment();
        Bundle bundle = new Bundle();

        homeFragment.setArguments(bundle);
        return homeFragment;
}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);


        return view;
    }
}