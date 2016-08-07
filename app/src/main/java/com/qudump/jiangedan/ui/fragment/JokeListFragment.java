package com.qudump.jiangedan.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qudump.jiangedan.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by qidong on 2016/8/6.
 */
public class JokeListFragment extends Fragment {

    @Bind(R.id.rv_content)
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke_list,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    private void init() {

    }
}
