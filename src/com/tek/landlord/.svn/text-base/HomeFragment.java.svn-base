package com.tek.landlord;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FinancialSummaryFragment financialSummaryFragment = new FinancialSummaryFragment();
        NewsfeedFragment newsfeedFragment = new NewsfeedFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.financial_summary_container, financialSummaryFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.news_feed_container, newsfeedFragment).commit();

        return inflater.inflate(R.layout.home, container, false);
    }
}
