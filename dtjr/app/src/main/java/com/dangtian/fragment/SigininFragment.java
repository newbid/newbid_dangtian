package com.dangtian.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dangtian.activity.R;

/**
 * Created by Administrator on 2015/9/24.
 */
public class SigininFragment extends FragmentBase {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_investment_payment, container, false);
    }
}