package com.sean.partner.meta;

import android.os.Parcel;
import android.os.Parcelable;

import com.sean.partner.utils.StringUtils;

/**
 * Created by sean on 2017/11/26.
 *
 */

public class PUser implements Parcelable{

    long userId;
    String userName;
    String password;
    String mobilePhoneNumber;
    boolean mobilePhoneNumberVerified;

    private boolean sex;        //性别
    private String avatar;      //头像
    private int age;        //年龄
    private long createTime;    //加入时间
    private int level;      //等级
    private int score;      //积分
    private String desc;        //签名
    private String addr;        //地址
    private String following;   //关注的人
    private String followed;    //被谁关注
    private String group;       //群
    private String interest;    //兴趣

    //未确定数据类型和待扩充的信息
//    private String realName;    //真实姓名
//    private String idCard;      //身份证号
    //email: 用户的电子邮件地址（可选）。
    //emailVerified:邮箱认证状态（可选）。

    // todo 优化项 用户登陆后采用系统默认配发的id值，未登录则采用设备id
    public boolean isUserLogIn(){
        if(userId >= 0 && StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)){
            return true;
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        boolean[] arrBoolean = new boolean[]{sex, mobilePhoneNumberVerified};

        dest.writeBooleanArray(arrBoolean);
        dest.writeInt(age);
        dest.writeInt(level);
        dest.writeInt(score);
        dest.writeLong(userId);
        dest.writeLong(createTime);
        dest.writeString(userName);
        dest.writeString(password);
        dest.writeString(mobilePhoneNumber);
        dest.writeString(avatar);
        dest.writeString(desc);
        dest.writeString(addr);
        dest.writeString(following);
        dest.writeString(followed);
        dest.writeString(group);
        dest.writeString(interest);
    }

    // 反序列过程：必须实现Parcelable.Creator接口，并且对象名必须为CREATOR
    // 读取Parcel里面数据时必须按照成员变量声明的顺序，Parcel数据来源上面writeToParcel方法，读出来的数据供逻辑层使用
    public static final Parcelable.Creator<PUser> CREATOR = new Creator<PUser>() {

        @Override
        public PUser createFromParcel(Parcel source) {
            return new PUser(source);
        }

        @Override
        public PUser[] newArray(int size) {
            return new PUser[size];
        }
    };

    public PUser(){};

    private PUser (Parcel in){
        boolean[] arrBoolean = new boolean[]{sex, mobilePhoneNumberVerified};


        in.readBooleanArray(arrBoolean);
        age = in.readInt();
        level = in.readInt();
        score = in.readInt();
        userId = in.readLong();
        createTime = in.readLong();
        userName = in.readString();
        password = in.readString();
        mobilePhoneNumber = in.readString();
        avatar = in.readString();
        desc = in.readString();
        addr = in.readString();
        following = in.readString();
        followed = in.readString();
        group = in.readString();
        interest = in.readString();

    }
}
