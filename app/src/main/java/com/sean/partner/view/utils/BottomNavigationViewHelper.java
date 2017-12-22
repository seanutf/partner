package com.sean.partner.view.utils;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by sean on 2017/12/22.
 *
 * 使用BottomNavigationView时，当item数大于3个默认的显示效果：
 * 1. items不再均分屏幕的宽度，选中item占据很大空间，其余item占据剩余空间
 * 2. 点击某个item，会改变item的宽度用于显示动画效果
 * 3. 其他未选中item不显示文字
 *
 * 此类用于去除上述默认效果，items均分屏幕，显示文字
 * 代码来自：http://blog.csdn.net/aiynmimi/article/details/72967585#comments
 */

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationHelper";

    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e(TAG, "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Unable to change value of shift mode", e);
        }
    }
}
