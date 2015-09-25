package com.dangtian.framework;

/**
 * Created by user on 2015/7/1.
 */
public interface NetCallback {
    void onSuccess();

    void onFailure(String errorMsg);
}