package com.example.project3_alarmclock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAlarmActivity extends AppCompatActivity {
    @BindView(R.id.toolBarAdd)
    Toolbar toolBarAdd;
    @BindView(R.id.addAlarm)
    Button addAlarm;
    @BindView(R.id.time_Picker)
    TimePicker timePicker;
    @BindView(R.id.activityName)
    TextView activityName;
    @BindView(R.id.name_Alarm)
    EditText name_Alarm;
    // addScreen true if user press "+" button, false if user press edit in popup menu
    private boolean addScreen;
    // this is alarm object need to edit
    private Alarm alarmEdit;
    // intent was sent from AlarmMainActivity
    private Intent intentInfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);
        initView();
    }

    // TODO: this initialize view for this  activity
    private void initView() {
        // Set back icon
        toolBarAdd.setNavigationIcon(R.drawable.ic_back);
        setScreen();
        backPressed();
    }

    // TODO: this process when user press back "<" button
    private void backPressed() {
        toolBarAdd.setNavigationOnClickListener(view -> {
            // set result cancel for on back button press
            setResult(Constants.RESULT_CANCEL);
            onBackPressed();
        });
    }

    // TODO: this set screen's text for activity
    private void setScreen() {
        // get intent information from main activity to set screen for this activity
        intentInfor = getIntent();
        String screenType = intentInfor.getStringExtra("screenType");
        assert screenType != null;
        if (screenType.contains("add")) {
            // if text contain "add" then set text for this screen add
            activityName.setText(R.string.add);
            addAlarm.setText(R.string.add);
            addScreen = true;
        } else if (screenType.contains("edit")) {
            // else set edit screen
            try {
                // get alarm object from intent
                alarmEdit = (Alarm) Objects.requireNonNull(intentInfor.getExtras()).getSerializable("AlarmEdit");
            } catch (Exception e) {
                Log.e("setScreen exception", e.getMessage() + " cause: " + e.getCause());
            }
            // set hour and minute for the time picker with time information from edit alarm
            // this setHour and setMinute require API 23 and upper
            if (alarmEdit != null) {
                timePicker.setHour(alarmEdit.getmHour());
                timePicker.setMinute(alarmEdit.getmMinute());
                // set alarm name
                name_Alarm.setText(alarmEdit.getmAlarmName());
                // set text activity's title
                activityName.setText(R.string.edit);
                // set text for the button
                addAlarm.setText(R.string.edit);
            }
            // assign addScreen
            addScreen = false;
        }
    }

    // TODO: process when user press ADD or EDIT button
    @OnClick(R.id.addAlarm)
    public void onClick(View v) {

        Intent intent = new Intent(this, MainActivity.class);
        // create alarm from time picker
        Alarm alarm = initAlarm();

        if (addScreen) {
            // at the adding time each alarm object is assigned for one id to manage
            // this id is unique, using system current time in millisecond.
            //  This id would be use for pendingIntent to manage multiple PendingIntent,
            alarm.setmId((int) System.currentTimeMillis());
            intent.putExtra("Alarm", alarm);
            // set result to AlarmMainActivity with intent
            setResult(RESULT_OK, intent);
            // finish method is requires if this Activity was started by startActivityForResult
            finish();

        } else {
            // in this get information from intent including alarm object and it's position
            // get the time and alarm'name changed and set to the edit alarm
            int position = Objects.requireNonNull(intentInfor.getExtras()).getInt("position");

            String name = alarm.getmAlarmName();
            int hour = alarm.getmHour();
            int minute = alarm.getmMinute();

            alarmEdit.setmAlarmName(name);
            alarmEdit.setmHour(hour);
            alarmEdit.setmMinute(minute);

            // sending it back by Bundle, this Bundle should be used if
            // we need to transfer a big data, this transfer all detail at a time
            Bundle bundle = new Bundle();
            bundle.putSerializable("Alarm", alarmEdit);
            bundle.putInt("position", position);

            intent.putExtras(bundle);
            // set result for this activity
            setResult(RESULT_OK, intent);
            // finish method is requires if this Activity was started by startActivityForResult
            finish();
        }
    }

    // TODO:  this return alarm from timePicker set toggle on by default
    private Alarm initAlarm() {
        // set toggle on by default, 1 is on and 0 is off
        int toggleOn = 1;
        Alarm alarm;
        String name1 = null;
        // get current time from timePicker
        int hour = 0;
        int minute = 0;

        try {
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
            // get name for alarm from EditText
            String name = name_Alarm.getText().toString();

            if (name.length() == 0) {
                // if alarm'name is not inputted set the EditText'hint for alarm's name by default
                name1 = name_Alarm.getHint().toString();
            } else {
                name1 = name_Alarm.getText().toString();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        // initialize and assign alarm values
        alarm = new Alarm(hour, minute, name1, toggleOn);
        return alarm;
    }
}

