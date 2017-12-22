package com.sean.partner.login.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sean on 2016/9/13.
 *
 */
public class UserGuideFragment  extends Fragment {

    final static String LAYOUT_ID = "layoutid";

    public static UserGuideFragment newInstance(int layoutId) {
        UserGuideFragment pane = new UserGuideFragment();
        Bundle args = new Bundle();
        args.putInt(LAYOUT_ID, layoutId);
        pane.setArguments(args);
        return pane;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(getArguments().getInt(LAYOUT_ID, -1), container, false);
        return rootView;
    }
}
