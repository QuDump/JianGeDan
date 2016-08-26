package com.qudump.jiangedan.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qudump.jiangedan.R;
import com.qudump.jiangedan.event.NumberEvent;
import com.qudump.rxbus.RxBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dell on 2016/8/26.
 */
public class ObservableFragment extends Fragment {

    @Bind(R.id.tv_post)
    TextView tvPost;

    private int number = 1;
    private String num = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_observable,container,false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @OnClick(R.id.send_normal)
    void sendNormal(){

        num = num + number + ",";
        tvPost.setText(num);
        RxBus.getDefault().post(new NumberEvent(number++));
    }
}
