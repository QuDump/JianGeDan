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
import rx.functions.Action1;

/**
 * Created by dell on 2016/8/26.
 */
public class TopSubscriberFragment extends Fragment {
    @Bind(R.id.tv_received)
    TextView tvReceived;

    private String number="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_subscriber,container,false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        RxBus.getDefault().toObservable(NumberEvent.class).subscribe(new Action1<NumberEvent>() {
            @Override
            public void call(NumberEvent numberEvent) {
                number = number + numberEvent.getNum() + ",";
                tvReceived.setText(number);
            }
        });
    }
}
