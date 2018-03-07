package com.sean.partner.module.main.activity.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sean.partner.R;
import com.sean.partner.module.main.home.view.fragment.HomePagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActivityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 *
 */
public class ActivityFragment extends Fragment {


    TabLayout tabLayout;
    ViewPager pager;
    FragmentActivity activity;

    List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.string.title_tab_recommend, R.layout.layout_content_home_recommend));
        pageModels.add(new PageModel(R.string.title_tab_friend, R.layout.layout_content_home_recommend));
        pageModels.add(new PageModel(R.string.title_tab_new, R.layout.layout_content_home_recommend));
        pageModels.add(new PageModel(R.string.title_tab_near, R.layout.layout_content_home_recommend));
    }

    private OnFragmentInteractionListener mListener;

    public ActivityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment ActivityFragment.
     */
    public static ActivityFragment newInstance() {
        return new ActivityFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActivity() != null){
            activity = getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_content_home, null);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tl_home);
        pager = (ViewPager) rootView.findViewById(R.id.vp_home);
        tabLayout.setupWithViewPager(pager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        pager.setAdapter(new FragmentPagerAdapter(activity.getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                //PageModel pageModel = pageModels.get(position);
                return HomePagerFragment.newInstance();
            }

            @Override
            public int getCount() {
                return pageModels.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getString(pageModels.get(position).titleRes);
            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class PageModel {
        @LayoutRes
        int layoutRes;
        @StringRes
        int titleRes;

        PageModel(@StringRes int titleRes, @LayoutRes int layoutRes) {
            this.layoutRes = layoutRes;
            this.titleRes = titleRes;
        }
    }
}
