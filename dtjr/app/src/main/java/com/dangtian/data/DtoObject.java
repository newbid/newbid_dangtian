package com.dangtian.data;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by asus on 2015/9/24.
 */
public class DtoObject implements Serializable {
    @Override
    public String toString() {

        return new Gson().toJson(this);
    }
}
