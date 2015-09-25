package com.dangtian.data;
import java.util.Map;
/**
 * Created by Administrator on 2015/9/24.
 */
public class Task {
    private UrlEnum taskId;
    private Map params;

    public Task() {
        super();
    }

    public Task(UrlEnum taskId, Map params) {
        super();
        this.taskId = taskId;
        this.params = params;
    }

    public UrlEnum getTaskId() {
        return taskId;
    }

    public void setTaskId(UrlEnum taskId) {
        this.taskId = taskId;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }
}
