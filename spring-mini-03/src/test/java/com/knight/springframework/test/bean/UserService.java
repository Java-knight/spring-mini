package com.knight.springframework.test.bean;

/**
 * @desc
 * @author knight
 * @date 2023/7/23
 */
public class UserService {

    private String name;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void findUserInfo() {
        System.out.println("find user info." + name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserService{");
        sb.append("name=").append(name);
        sb.append('}');
        return sb.toString();
    }
}
