package com.pengdu.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


/**
 * Created by Administrator on 2017/6/30.
 */
public class SysActivity extends AppCompatActivity {
    private LinearLayout wp_come;
    private LinearLayout wp_go;
    private RelativeLayout wp_type;
    private RelativeLayout wp_weight;
    private PopupWindow popupWindow;
    private PopupWindow popupDatePicWindow;
    private Button btn_cancle;
    private Button btn_command;
    private TextView number_price;
    private NumberPicker number;
    private TextView text_weight;
    private int weight;
    private TextView text_privi;
    private TextView cancle;
    private AlertDialog dialog;
    private EditText phonenumber;
    private Button sendpwd;
    private LinearLayout custom_dialog;
    private RelativeLayout wp_gettime;
    private DatePicker date_picker;
    private TimePicker time_picker;
    private TextView gettime_wp;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private Button btn_command2;
    private Button btn_cancle2;
    private TextView text_plus;
    private PopupWindow popupPlusWindow;
    private NumberPicker numberplus;
    private int price_plus;
    private Button btn_command3;
    private Button btn_cancle3;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private   ImageView image_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sys_activity);

        wp_come = (LinearLayout) findViewById(R.id.wp_gocome);
        wp_type = (RelativeLayout) findViewById(R.id.wp_type);
        wp_go = (LinearLayout) findViewById(R.id.wp_go);
        wp_weight = (RelativeLayout) findViewById(R.id.wp_weight);
        text_weight = (TextView) findViewById(R.id.text_weight);
        text_privi = (TextView) findViewById(R.id.text_privilege);
        wp_gettime = (RelativeLayout) findViewById(R.id.wp_gettime);
        gettime_wp = (TextView) findViewById(R.id.gettime_wp);
        text_plus = (TextView) findViewById(R.id.text_plus);
        image_back = (ImageView) findViewById(R.id.image_back);


        image_back.setOnClickListener(new MyListener());
        text_privi.setOnClickListener(new MyListener());
        wp_weight.setOnClickListener(new MyListener());
        wp_go.setOnClickListener(new MyListener());
        wp_come.setOnClickListener(new MyListener());
        wp_type.setOnClickListener(new MyListener());
        wp_gettime.setOnClickListener(new MyListener());
        text_plus.setOnClickListener(new MyListener());
    }


    public class MyListener implements View.OnClickListener {

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.wp_gocome:
                    startActivity(AddressActivity.class);
                    break;
                case R.id.wp_go:
                    startActivity(AddressActivity.class);
                    break;
                case R.id.wp_type:
                    startActivity(Wp_typeActivity.class);
                    break;
                case R.id.wp_weight:
                    initPopupWindow();
                    break;
                case R.id.btn_cancle:
                    popupWindow.dismiss();
                    text_weight.setText("5公斤以下");
                    break;
                case R.id.btn_command:
                    popupWindow.dismiss();
                    text_weight.setText("" + weight + "公斤");
                    break;
                case R.id.text_privilege:
                    initDialog();
                    break;
                case R.id.text_cancle:
                    dialog.dismiss();
                    break;
                case R.id.wp_gettime:
                    initDateWindow();
                    break;
                case R.id.send_check:
                    sendpwd.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
                    break;
                case R.id.btn_command2:
                    gettime_wp.setText("" + year + "年" + (month + 1) + "月" + day + "号" + hour + "时" + minute + "分");
                    popupDatePicWindow.dismiss();
                    break;
                case R.id.btn_cancle2:
                    popupDatePicWindow.dismiss();
                    gettime_wp.setText("立即取件");
                    break;
                case R.id.text_plus:
                    initPopwinPlus();
                    break;
                case R.id.btn_command3:
                    text_plus.setText("加价" + price_plus + "元");
                    popupPlusWindow.dismiss();
                    break;
                case R.id.btn_cancle3:
                    popupPlusWindow.dismiss();
                    text_plus.setText("我要加价(>20公斤)");
                    break;
                case R.id.image_back:
                    startActivity(MainActivity.class);
                    break;
            }

        }

    }

    private void initPopwinPlus() {
        //设置contentView
        View contentView = LayoutInflater.from(SysActivity.this).inflate(R.layout.popwin_plus, null);
        popupPlusWindow = new PopupWindow(contentView,
                ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);

        popupPlusWindow.setContentView(contentView);
        popupPlusWindow.setFocusable(true); // 设置PopupWindow可获得焦点
        popupPlusWindow.setTouchable(true); // 设置PopupWindow可触摸

        numberplus = contentView.findViewById(R.id.number_plus);
        btn_command3 = contentView.findViewById(R.id.btn_command3);
        btn_cancle3 = contentView.findViewById(R.id.btn_cancle3);

        numberplus.setMinValue(5);
        numberplus.setMaxValue(10000);
        numberplus.setWrapSelectorWheel(false);

        numberplus.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int newval) {
                price_plus = newval;

            }
        });

        btn_command3.setOnClickListener(new MyListener());
        btn_cancle3.setOnClickListener(new MyListener());
//显示popupwindow
        View rootview = LayoutInflater.from(SysActivity.this).inflate(R.layout.activity_main, null);
        popupPlusWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    private void initDateWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(SysActivity.this).inflate(R.layout.popdate_picker, null);
        popupDatePicWindow = new PopupWindow(contentView,
                ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);

        popupDatePicWindow.setContentView(contentView);
        popupDatePicWindow.setFocusable(true); // 设置PopupWindow可获得焦点
        popupDatePicWindow.setTouchable(true); // 设置PopupWindow可触摸

        date_picker = contentView.findViewById(R.id.date_picker);
        time_picker = contentView.findViewById(R.id.time_picker);
        btn_command2 = contentView.findViewById(R.id.btn_command2);
        btn_cancle2 = contentView.findViewById(R.id.btn_cancle2);

        btn_command2.setOnClickListener(new MyListener());
        btn_cancle2.setOnClickListener(new MyListener());

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        minute = c.get(Calendar.MINUTE);

        //初始化组件
        date_picker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                SysActivity.this.year = i;
                SysActivity.this.month = i1;
                SysActivity.this.day = i2;
                showDate(year, month, day, hour, minute);
            }
        });

        time_picker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                SysActivity.this.hour = i;
                SysActivity.this.minute = i1;
                showDate(year, month, day, hour, minute);
            }
        });


        //显示popupwindow
        View rootview = LayoutInflater.from(SysActivity.this).inflate(R.layout.activity_main, null);
        popupDatePicWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

    }

    //
    private void showDate(int year, int month, int day, int hour, int minute) {

        gettime_wp.setText("" + year + "年" + (month + 1) + "月" + day + "号" + hour + "时" + minute + "分");

    }

    private void initPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(SysActivity.this).inflate(R.layout.popup, null);
        popupWindow = new PopupWindow(contentView,
                ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);
        //设置各个控件的点击响应
        popupWindow.setContentView(contentView);
        popupWindow.setFocusable(true); // 设置PopupWindow可获得焦点
        popupWindow.setTouchable(true); // 设置PopupWindow可触摸
        number = (NumberPicker) contentView.findViewById(R.id.number);
        number.setMinValue(5);
        number.setMaxValue(20);
        number.setWrapSelectorWheel(false);

        number.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldval, int newval) {
                number_price.setText("" + (newval - 5) * 2);
                weight = newval;

            }
        });


        //点击事件
        btn_cancle = (Button) contentView.findViewById(R.id.btn_cancle);
        btn_command = (Button) contentView.findViewById(R.id.btn_command);
        number_price = contentView.findViewById(R.id.number_price);

        btn_cancle.setOnClickListener(new MyListener());
        btn_command.setOnClickListener(new MyListener());
        number_price.setOnClickListener(new MyListener());

        //显示popupwindow
        View rootview = LayoutInflater.from(SysActivity.this).inflate(R.layout.activity_main, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

    }

    private void initDialog() {
        custom_dialog = (LinearLayout) LayoutInflater.from(SysActivity.this).inflate(R.layout.custom_dialog, null);
        dialog = new AlertDialog.Builder(this).setView(custom_dialog).create();
        dialog.show();
        cancle = custom_dialog.findViewById(R.id.text_cancle);
        phonenumber = custom_dialog.findViewById(R.id.edit_phonenub);
        sendpwd = custom_dialog.findViewById(R.id.send_check);
        sendpwd.setOnClickListener(new MyListener());
        phonenumber.setOnClickListener(new MyListener());
        cancle.setOnClickListener(new MyListener());


    }

    private void startActivity(Class<?> targetClass) {
        Intent intent = new Intent(this,targetClass);
        startActivity(intent);
    }



}
