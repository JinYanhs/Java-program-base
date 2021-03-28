package com.glod.callback.callBackAsync;

/**
 * @description: 用户信息
 * @author: Glod
 * @date: 2021/3/28
 */
public class UserInfo {
    private String userName;
    private String des;
    private Integer age;

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", des='" + des + '\'' +
                ", age=" + age +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
