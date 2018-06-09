package com.sean.partner.global.meta;

import cn.bmob.v3.BmobUser;

/**
 * Created by sean on 2016/9/8.
 */
public class User extends BmobUser{
    /*  BmobUser除了从BmobObject继承的属性外，还有几个特定的属性：
        username: 用户的用户名（必需）。
        password: 用户的密码（必需）。
        email: 用户的电子邮件地址（可选）。
        emailVerified:邮箱认证状态（可选）。
        mobilePhoneNumber：手机号码（可选）。
        mobilePhoneNumberVerified：手机号码的认证状态（可选）。
    * */

    private Boolean sex;        //性别
    private String avatar;      //头像
    private Integer age;        //年龄
    private Long createTime;    //加入时间
    private Integer level;      //等级
    private Integer score;      //积分
    private String desc;        //签名
    private String addr;        //地址
    private int friendCount;    //好友数量
    private String contactNumber;  //联系方式，可能是电话、微信号、QQ号、其他方式
    //未确定数据类型和待扩充的信息
//    private String realName;    //真实姓名
//    private String idCard;      //身份证号
//    private String following;   //关注的人
//    private String followed;    //被谁关注
//    private String group;       //群
//    private String interest;    //兴趣


    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(int friendCount) {
        this.friendCount = friendCount;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
