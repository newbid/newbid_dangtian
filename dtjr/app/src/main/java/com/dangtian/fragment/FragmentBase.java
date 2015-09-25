package com.dangtian.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.dangtian.framework.WebCallback;
import com.dangtian.service.MainService;


/**
 * Created by user on 2015/6/15.
 */
public abstract class FragmentBase extends Fragment implements WebCallback {
    public Activity mActivity;
    private boolean needFresh;
    protected String moneyFormat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        moneyFormat = getString(R.string.money_format);
        needFresh = false;
        Activity mActivity = getActivity();
    }


    @Override
    public void onResume() {
        super.onResume();
        MainService.getInstance().addCallback(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MainService.getInstance().removeCallback(this);
    }

    protected void initView(View view) {
    }

    protected void refreshView(View view) {

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        refreshView(view);
    }


    @Override
    public void setRefresh() {
        needFresh = true;
    }

    public void resetReflash() {
        needFresh = false;
    }

    protected void makeToast(@StringRes int id) {
        if (mActivity != null)
            Toast.makeText(mActivity, id, Toast.LENGTH_SHORT).show();
    }


    protected void makeToast(String string) {
        if (mActivity != null)
            Toast.makeText(mActivity, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refresh(Object... args){

    }
    @Override
    public void init(){}

}
