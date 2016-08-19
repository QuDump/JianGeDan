package com.qudump.jiangedan.event;

/**
 * Created by dell on 2016/8/18.
 */
public class AttitudeEvent {
    private long id;
    private boolean isLike;
    private EVENT_SOURCE source;

    public AttitudeEvent(long id, boolean isLike, EVENT_SOURCE source) {
        this.id = id;
        this.isLike = isLike;
        this.source = source;
    }

    public long getId() {
        return id;
    }

    public boolean isLike() {
        return isLike;
    }

    public EVENT_SOURCE getSource() {
        return source;
    }
}
