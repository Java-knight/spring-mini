package com.knight.springframework.test.bean;


import java.util.Random;

/**
 * @author knight
 * @desc
 * @date 2024/4/22
 */
public class UserService implements IUserService {

    @Override
    public String findUserInfo() {
        try {
           Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "knight, shopee, shenzhen";
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "register user: " + userName + " success";
   }
}
