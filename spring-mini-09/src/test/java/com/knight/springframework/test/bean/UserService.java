package com.knight.springframework.test.bean;

import com.knight.springframework.beans.BeansException;
import com.knight.springframework.beans.factory.BeanClassLoaderAware;
import com.knight.springframework.beans.factory.BeanFactory;
import com.knight.springframework.beans.factory.BeanFactoryAware;
import com.knight.springframework.beans.factory.BeanNameAware;
import com.knight.springframework.context.ApplicationContext;
import com.knight.springframework.context.ApplicationContextAware;

/**
 * @desc
 * @author knight
 * @date 2023/7/23
 */
public class UserService {


    private String uid;

    private String company;

    private String location;

    private IUserDao userDao;

    public String findUserInfo() {
        return userDao.findUserName(uid) + ", " + company + ", " + location;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
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
