package com.example.project3_alarmclock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.security.keystore.StrongBoxUnavailableException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class DataBaseManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAM = "db_alarm";
    private static final String TABLE_NAME = "alarm";
    private static final String KEY_ID = "id";
    private static final String KEY_HOUR = "hour";
    private static final String KEY_MINUTE = "minute";
    private static final String KEY_NAM = "alarm_name";
    private static final String KEY_TOGGLE = "toggle";

    private static final String CREATE_TABLE_ALARM = "CREATE TABLE " + TABLE_NAME + " ("
            + KEY_ID + "INTEGER, "
            + KEY_HOUR + "INTEGER, "
            + KEY_MINUTE + "INTEGER, "
            + KEY_NAM + "TEXT, "
            + KEY_TOGGLE + "INTEGER )";

    // TODO: this is data base constructor
    DataBaseManager(@Nullable Context context) {
        super(context, DATABASE_NAM, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_ALARM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(String.format("DROP TABLE IF EXISTS %s", CREATE_TABLE_ALARM));
        onCreate(sqLiteDatabase);
    }

    void addAlarm(Alarm alarm) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(KEY_ID, alarm.getmId());
            values.put(KEY_HOUR, alarm.getmHour());
            values.put(KEY_MINUTE, alarm.getmMinute());
            values.put(KEY_NAM, alarm.getmAlarmName());
            values.put(KEY_TOGGLE, alarm.getmHour());
            db.insert(TABLE_NAME, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void update(Alarm alarm) {
        String where = KEY_ID + " = " + alarm.getmId();
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(KEY_ID, alarm.getmId());
            values.put(KEY_HOUR, alarm.getmHour());
            values.put(KEY_MINUTE, alarm.getmMinute());
            values.put(KEY_NAM, alarm.getmAlarmName());
            values.put(KEY_TOGGLE, alarm.getmOnOff());
            db.update(TABLE_NAME, values, where, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void delete(int alarmId) {
        String where = KEY_ID + " = " + alarmId;
         try (SQLiteDatabase db = this.getWritableDatabase()) {
             db.delete(TABLE_NAME, where, null);
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    ArrayList<Alarm> getAlarmList() {
        ArrayList<Alarm> alarmArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    Alarm alarm = new Alarm(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2),
                            cursor.getString(3), cursor.getInt(4));
                    alarmArrayList.add(alarm);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "getAlarmList: exception cause " + e.getCause() + " message " + e.getMessage());
        }
        return alarmArrayList;
    }
}
