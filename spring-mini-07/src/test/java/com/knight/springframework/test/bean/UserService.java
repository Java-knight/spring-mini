package com.knight.springframework.test.bean;

import com.knight.springframework.beans.factory.DisposableBean;
import com.knight.springframework.beans.factory.InitializingBean;

/**
 * @desc
 * @author knight
 * @date 2023/7/23
 */
public class UserService implements InitializingBean, DisposableBean {

    private String uid;

    private String company;

    private String location;

    private UserDao userDao;

    public String findUserInfo() {
        return userDao.findUserName(uid) + ", " + company + ", " + location;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("execute UserService#destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("execute UserService#afterPropertiesSet");
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
