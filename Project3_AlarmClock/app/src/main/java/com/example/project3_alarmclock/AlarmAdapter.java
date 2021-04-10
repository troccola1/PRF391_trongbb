package com.example.project3_alarmclock;

import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmAdapter extends RecyclerView.Adapter {
    private ArrayList<Alarm> mAlarm;
    private CallBack mCallBack;

    public interface CallBack {
        // The callback handles logic for the eddit and delete menus
        void onMenuAction(Alarm alarm, MenuItem item, int position);

        // Callback handles logic start alarm
        void startAlarm(Alarm timeItem);

        // The callback handles logic cancel alarm
        void cancelAlarm(Alarm timeItem);
    }

    AlarmAdapter(ArrayList<Alarm> mAlarm, CallBack mCallBack) {
        this.mAlarm = mAlarm;
        this.mCallBack = mCallBack;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.mylist, parent, false);
        return new TimeViewHolder(view, mCallBack);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TimeViewHolder) {
            TimeViewHolder timeViewHolder = (TimeViewHolder) holder;
            timeViewHolder.bindView(mAlarm.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mAlarm.size();
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener,
            PopupMenu.OnMenuItemClickListener {

        @BindView(R.id.time_Alarm)
        TextView time;  // this displays time alarm
        @BindView(R.id.alarm_Name)
        TextView title; // this displays alarm title
        @BindView(R.id.toggle_Alarm)
        ToggleButton toggleButton;  // toggle button to set and cancel alarm

        private TimeViewHolder(View itemView, CallBack CallBack) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            // register callback
            mCallBack = CallBack;
            // setting long click, display menu for each item
            itemView.setOnCreateContextMenuListener(this);
        }

        // TODO: onclick toggle button
        @OnClick(R.id.toggle_Alarm)
        void onToggleClicked() {
            boolean isChecked = toggleButton.isChecked();
            if (isChecked) {
                // change time' text color if toggle state have changed
                time.setTextColor(Color.rgb(255, 51, 51));
                // start alarm when toggle on, getPosition method support by RecyclerView
                mCallBack.startAlarm(mAlarm.get(getLayoutPosition()));
            } else {
                time.setTextColor(Color.rgb(155, 155, 155));
                // cancel alarm when toggle off
                mCallBack.cancelAlarm(mAlarm.get(getLayoutPosition()));
            }
        }

        // TODO: this bind view when from mAlarms arrayList
        private void bindView(Alarm alarm) {
            // set time's text
            time.setText(getStringFromTime(alarm));
            // set title's text
            title.setText(alarm.getmAlarmName());
            // set color state for toggle button  and time's text
            int onOff = alarm.getmOnOff();
            switch (onOff) {
                case 1:
                    toggleButton.setChecked(true);
                    // this set color for time' text green if toggle on on the bind view time
                    time.setTextColor(Color.rgb(255, 51, 51));
                    break;
                case 0:
                    toggleButton.setChecked(false);
                    // else set text color little black
                    time.setTextColor(Color.rgb(155, 155, 155));
                    break;
            }
        }

        // TODO: return string from Alarm
        private String getStringFromTime(Alarm alarm) {
            int minute = alarm.getmMinute(); // minute of alarm
            int hourSource = alarm.getmHour(); // hour of alarm
            int hour; // this hold time for time at AM, PM format
            String hour_x; // append 0 in front of hour if hour less than 10
            String minute_x; // append 0 in front of minute if minute less than 10
            String format;      // format for alarm

            if (hourSource == 0) {
                hour = hourSource + 12;
                format = "AM";
            } else if (hourSource == 12) {
                hour = hourSource;
                format = "PM";
            } else if (hourSource > 12) {
                hour = hourSource - 12;
                format = "PM";
            } else {
                hour = hourSource;
                format = "AM";
            }

            if (hour < 10) {
                hour_x = "0" + hour;
            } else {
                hour_x = "" + hour;
            }

            if (minute < 10) {
                minute_x = "0" + minute;
            } else {
                minute_x = "" + minute;
            }
            return hour_x + " : " + minute_x + "    " + format;
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view,
                                        ContextMenu.ContextMenuInfo contextMenuInfo) {
            PopupMenu popup = new PopupMenu(view.getContext(), view);
            popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());
            popup.setOnMenuItemClickListener(this);
            popup.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            // on item clicked use this callback to check which function is called
            // delete or edit
            int position = getLayoutPosition();
            mCallBack.onMenuAction(mAlarm.get(position), menuItem, position);
            return false;
        }
    }
    // TODO: this add alarm to adapter
    void add(Alarm alarm) {
        mAlarm.add(alarm);
    }

    // TODO: add alarm to the position remove one at position
    void updateAlarm(Alarm alarm, int position) {
        mAlarm.remove(position);
        mAlarm.add(position, alarm);
    }

    // TODO:  remove alarm from mAlarms ArrayList
    void removeAlarm(int position) {
        try {
            mAlarm.remove(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO: get Alarm ArrayList
    ArrayList<Alarm> getmAlarm() {
        return mAlarm;
    }
}