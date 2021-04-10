package com.example.project3_alarmclock;

import java.io.Serializable;

public class Alarm implements Serializable {
    private int mId;
    private int mHour;
    private int mMinute;
    private String mAlarmName;
    private int mOnOff;

    Alarm(int mId,int mHour, int mMinute, String mAlarmName, int mOnOff) {
        this.setmId(mId);
        this.setmHour(mHour);
        this.setmMinute(mMinute);
        this.setmAlarmName(mAlarmName);
        this.setmOnOff(mOnOff);
    }

    Alarm(int mHour, int mMinute, String mAlarmName, int mOnOff) {
        this.setmHour(mHour);
        this.setmMinute(mMinute);
        this.setmAlarmName(mAlarmName);
        this.setmOnOff(mOnOff);
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmHour() {
        return mHour;
    }

    public void setmHour(int mHour) {
        this.mHour = mHour;
    }

    public int getmMinute() {
        return mMinute;
    }

    public void setmMinute(int mMinute) {
        this.mMinute = mMinute;
    }

    public String getmAlarmName() {
        return mAlarmName;
    }

    public void setmAlarmName(String mAlarmName) {
        this.mAlarmName = mAlarmName;
    }

    public int getmOnOff() {
        return mOnOff;
    }

    public void setmOnOff(int mOnOff) {
        this.mOnOff = mOnOff;
    }
}
