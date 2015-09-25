package com.dangtian.framework;

/**
 * Created by user on 2015/7/1.
 */
public interface WebCallback {
    public void init() ;

    public void refresh(Object... args);

    public void setRefresh();

}