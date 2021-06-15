  package com.example.project3_alarmclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

  public class MainActivity implements AlarmAdapter.CallBack {
      @BindView(R.id.openAdd)
      Button button;
      @BindView(R.id.alarmView)
      RecyclerView recyclerView;
      // this to manage data base
      private DataBaseManager mDataBaseManager;
      // this to manage Alarm adapter like ArrayList
      private AlarmAdapter mAlarmAdapter;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          ButterKnife.bind(this);
          initView();
      }

      // TODO: this initialize view for activity
      private void initView() {
          // set layout for recycle view
          //hasFixedSize true if adapter changes cannot affect the size of the RecyclerView
          recyclerView.setHasFixedSize(true);
          // this layout can be vertical or horizontal by change the second param
          // of LinearLayoutManager, and display up to down by set the third param false
          LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
          recyclerView.setLayoutManager(layoutManager);
          importData();
          // set adapter for recycle view
          recyclerView.setAdapter(mAlarmAdapter);
      }

      //TODO: processing when user click on "+" button start new intent with request code
      @OnClick(R.id.openAdd)
      public void onOpenAddAlarm(View view) {
          Intent intent = new Intent(getApplicationContext(), AddAlarmActivity.class);
          intent.putExtra("screenType", "add");
          startActivityForResult(intent, Constants.REQUEST_ADD);
      }

      //TODO: Receive return data form Add or Edit activity to process add or edit alarm
      @Override
      protected void onActivityResult(int requestCode, int resultCode, Intent data) {
          super.onActivityResult(requestCode, resultCode, data);
          Alarm alarm;
          // check if request code and result code the same sent and return result
          if (requestCode == Constants.REQUEST_ADD && resultCode == RESULT_OK) {
              // get data and set a new alarm by function setAlarm
              alarm = (Alarm) data.getSerializableExtra("Alarm");
              // this check if the setting time already exist
              boolean containAlarm = checkAlarm(alarm);
              if (!containAlarm) {
                  // add alarm to adapter
                  mAlarmAdapter.add(alarm);
                  // refresh adapter
                  mAlarmAdapter.notifyDataSetChanged();
                  // add it to database
                  mDataBaseManager.addAlarm(alarm);
                  // set new PendingIntent
                  assert alarm != null;
                  setAlarm(alarm, 0);
              }
          } else if (requestCode == Constants.REQUEST_EDIT && resultCode == RESULT_OK) {
              // get alarm object from AddAlarmActivity
              alarm = (Alarm) data.getSerializableExtra("Alarm");
              // this check if the setting time already exist
              boolean containAlarm = checkAlarm(alarm);
              if (!containAlarm) {
                  // get alarm's position
                  int position = Objects.requireNonNull(data.getExtras()).getInt("position");
                  // update alarm at position
                  mAlarmAdapter.updateAlarm(alarm, position);
                  // this help refresh display
                  mAlarmAdapter.notifyDataSetChanged();
                  // update alarm to database
                  assert alarm != null;
                  mDataBaseManager.update(alarm);
                  // if alarm.getOnOff ==1 set alarm else not
                  if (alarm.getmOnOff() == 1) {
                      // get data and set a new alarm by function setAlarm with flag update current because
                      // this PendingIntent has already existed
                      setAlarm(alarm, PendingIntent.FLAG_UPDATE_CURRENT);
                  }
              }
          }
      }

      // TODO: this function is a function of callBack interface which was created in alarm adapter
      // TODO: process edit or delete based on user option
      @Override
      public void onMenuAction(Alarm alarm, MenuItem item, int position) {
          switch (item.getItemId()) {
              case R.id.edit:
                  // send edit intent to AddAlarmActivity to edit this would return alarm and position
                  Intent intent = new Intent(this, AddAlarmActivity.class);
                  // put screenType to set display for AddAlarmActivity
                  intent.putExtra("screenType", "edit");
                  // put alarm need to edit
                  intent.putExtra("AlarmEdit", alarm);
                  // put alarm's position
                  intent.putExtra("position", position);
                  // this start AddAlarmActivity and change the screen and change detail return
                  // one result on "onActivityResult" about
                  startActivityForResult(intent, Constants.REQUEST_EDIT);
                  break;
              case R.id.delete:
                  // when user click edit remove alarm
                  mAlarmAdapter.removeAlarm(position);
                  // refresh
                  mAlarmAdapter.notifyDataSetChanged();
                  // get alarm id to delete alarm in database
                  int alarmId = alarm.getmId();
                  // delete alarm from database
                  mDataBaseManager.delete(alarmId);
                  // cancel pendingIntent
                  deleteCancel(alarm);
                  break;
          }
      }

      //TODO: Processing timed information transmission for AlarmReceiver
      // when toggle button click on set alarm on
      @Override
      public void startAlarm(Alarm alarm) {
          alarm.setmOnOff(1);
          // update database
          mDataBaseManager.update(alarm);
          // set PendingIntent for this alarm
          setAlarm(alarm, 0);
      }

      //TODO: Send information of appointment time to cancel to AlarmReceiver
      // when user click cancel toggle button
      // set alarm off
      @Override
      public void cancelAlarm(Alarm timeItem) {
          timeItem.setmOnOff(0);
          // update database
          mDataBaseManager.update(timeItem);
          // cancel this Alarm PendingIntent
          deleteCancel(timeItem);
          // if alarm is triggered and ringing, send this alarm detail to AlarmReceiver
          // then AlarmReceiver send detail to service to stop music
          sendIntent(timeItem);
      }

      @Override
      protected void onDestroy() {
          super.onDestroy();
      }

      // TODO: this check if Alarm have already existed
      private boolean checkAlarm(Alarm alarm) {
          boolean contain = false;
          for (Alarm alar : mAlarmAdapter.getmAlarm()) {
              if (alar.getmHour() == alarm.getmHour() && alar.getmMinute() == alarm.getmMinute()) {
                  contain = true;
                  break;
              }
          }
          if (contain) {
              Toast.makeText(this, "You have already set this Alarm", Toast.LENGTH_SHORT).show();
          }
          return contain;
      }

      // TODO: import data from dataBase and create AlarmAdapter
      private void importData() {
          // if alarmAdapter null it's means data have not imported, yet or database is empty
          if (mAlarmAdapter == null) {
              // initialize database manager
              mDataBaseManager = new DataBaseManager(this);
              // get Alarm ArrayList from database
              ArrayList<Alarm> arrayList = mDataBaseManager.getAlarmList();
              // create Alarm adapter to display detail through RecyclerView
              mAlarmAdapter = new AlarmAdapter(arrayList, this);
          }
      }

      // TODO: this sends intent to AlarmReceiver
      private void sendIntent(Alarm alarm) {
          // intent1 to send to AlarmReceiver
          Intent intent1 = new Intent(MainActivity.this, AlarmReceiver.class);
          // put intent type Constants.ADD_INTENT or Constants.OFF_INTENT
          intent1.putExtra("intentType", Constants.OFF_INTENT);
          // put alarm'id to compare with pendingIntent'id in AlarmService
          intent1.putExtra("AlarmId", alarm.getmId());
          // this sent broadCast right a way
          sendBroadcast(intent1);
      }

      // TODO: this sets pendingIntent for alarm
      private void setAlarm(Alarm alarm, int flags) {
          // this set alarm based on TimePicker so we need to set Calendar like the
          // trigger time
          // get instant of Calendar
          Calendar myCalendar = Calendar.getInstance();
          Calendar calendar = (Calendar) myCalendar.clone();
          // set current hour for calendar
          calendar.set(Calendar.HOUR_OF_DAY, alarm.getmHour());
          // set current minute
          calendar.set(Calendar.MINUTE, alarm.getmMinute());
          // set current second for calendar
          calendar.set(Calendar.SECOND, 0);
          // plus one day if the time set less than the the Calendar current time
          if (calendar.compareTo(myCalendar) <= 0) {
              calendar.add(Calendar.DATE, 1);
          }
          // get id of alarm and set for PendingIntent to multiply multiple PendingIntent for cancel
          // time, this also put into PendingIntent to compare with the cancel Alarm's id=
          int alarmId = alarm.getmId();
          // make intent to broadCast
          Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
          // put intent type to check which intent trigger add or cancel
          intent.putExtra("intentType", Constants.ADD_INTENT);
          // put id to intent
          intent.putExtra("PendingId", alarmId);
          // this pendingIntent include alarm id  to manage
          PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this, alarmId,
                  intent, flags);
          // create alarm manager ALARM_SERVICE
          AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
          assert alarmManager != null;
          alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                  calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
      }

      // TODO:  this cancel pendingIntent of the alarm
      private void deleteCancel(Alarm alarm) {
          AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
          // get alarm id
          int alarmId = alarm.getmId();
          // create intent
          Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
          // this retrieve the pendingIntent was set
          PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this, alarmId, intent, 0);
          // cancel this pendingIntent
          assert alarmManager != null;
          alarmManager.cancel(alarmIntent);
      }
  }