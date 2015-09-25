package com.dangtian.service;

import android.util.Log;


import com.dangtian.data.Task;
import com.dangtian.data.UrlEnum;
import com.dangtian.data.User;
import com.dangtian.framework.HttpParam;
import com.dangtian.framework.NetCallback;
import com.dangtian.framework.ServiceAction;
import com.dangtian.framework.WebCallback;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by user on 2015/6/13.
 */
public class MainService {
    private static MainService mInstance;
    private User user;
    private Map<Task, WebCallback> mScreenMap;
    private List<WebCallback> CallbackList;

    private MainService() {
        mScreenMap = new HashMap<>();
        CallbackList = new ArrayList<WebCallback>();
    }

    public static MainService getInstance() {
        if (mInstance == null)
            mInstance = new MainService();
        return mInstance;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean addTask(WebCallback webCallbackIntance, Task task) {
        if (webCallbackIntance == null || task == null)
            return false;
        mScreenMap.put(task, webCallbackIntance);
        doTask(task);
        return true;
    }

    public boolean addCallback(WebCallback webCallbackIntance) {
        if (webCallbackIntance == null)
            return false;
        CallbackList.add(webCallbackIntance);
        return true;
    }

    public boolean removeCallback(WebCallback webCallbackIntance) {
        if (webCallbackIntance == null)
            return false;
        return CallbackList.remove(webCallbackIntance);
    }

    private void doTask(final Task task) {
        HttpParam param = new HttpParam();
        if (task.getParams() != null) {
//            param = param.addBasicParams().addParam(task.getParams());
        } else {
//            param = param.addBasicParams();
        }
        UrlEnum type = (UrlEnum) task.getTaskId();
    }

}
