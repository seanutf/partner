package com.sean.partner.meta;

import cn.bmob.v3.BmobObject;

/**
 * Created by sean on 2017/11/28.
 *
 */

public class DateMeta extends BmobObject {

    public static int DATE_STATUS_TODO_DONGING_DONE = 0;
    public static int DATE_STATUS_TDONGING = 1;
    public static int DATE_STATUS_DONE = 2;

    long id;
    User user;
    int status;

}
