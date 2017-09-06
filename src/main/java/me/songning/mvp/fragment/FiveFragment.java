package me.songning.mvp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.songning.mvp.R;


public class FiveFragment extends Fragment {
    public static FiveFragment newInstance(String s){
        FiveFragment homeFragment = new FiveFragment();
        Bundle bundle = new Bundle();

        homeFragment.setArguments(bundle);
        return homeFragment;
}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_five, container, false);




        return view;
    }


}