package com.knight.springframework.test.bean;

/**
 * @desc
 * @author knight
 * @date 2023/7/23
 */
public class UserService {

    private String uid;

    private UserDao userDao;

    public String findUserInfo() {
        return userDao.findUserName(uid);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
