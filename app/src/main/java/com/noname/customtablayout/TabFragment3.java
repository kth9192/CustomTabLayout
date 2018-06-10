package com.noname.customtablayout;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noname.customtablayout.databinding.TabFragment1Binding;

public class TabFragment3 extends Fragment {
    private static String TAG = TabFragment3.class.getName();

    private TabFragment1Binding tabFragment1Binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        tabFragment1Binding = DataBindingUtil.inflate(inflater, R.layout.tab_fragment1, container, false);

        View view = tabFragment1Binding.getRoot();

        return view;
    }

}
