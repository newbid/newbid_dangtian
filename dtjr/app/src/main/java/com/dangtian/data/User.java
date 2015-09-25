package com.dangtian.data;

import java.util.List;

/**
 * Created by Administrator on 2015/9/23.
 */
public class User {
    Long userId;
    String username;
    String name;
    String tuijianUserid;
    String email;
    String openId;
    boolean isFirst;
    boolean usernameStatus;
    String lastLoginTime;
    String avatar;
    UserInfo userInfo;
    List<Partner> partnerships;
}