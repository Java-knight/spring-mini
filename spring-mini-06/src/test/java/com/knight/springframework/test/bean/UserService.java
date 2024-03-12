package com.knight.springframework.test.bean;

/**
 * @desc
 * @author knight
 * @date 2023/7/23
 */
public class UserService {

    private String uid;

    private String company;

    private String location;

    private UserDao userDao;

    public String findUserInfo() {
        return userDao.findUserName(uid) + ", " + company + ", " + location;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
