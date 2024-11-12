package com.example.mini_amazon.model;

public class UserStorage {
    private static UserStorage instance;
    private static User currentUser;

    // 私有构造函数，防止外部实例化
    private UserStorage() {}

    // 获取单例实例
    public static UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }

    // 设置当前用户
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    // 获取当前用户
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void removeCurrentUser() {
        currentUser = null;
    }
}