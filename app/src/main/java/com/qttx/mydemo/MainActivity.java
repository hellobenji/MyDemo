package com.qttx.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.joybar.librarycalendar.data.CalendarDate;
import com.joybar.librarycalendar.fragment.CalendarViewFragment;
import com.joybar.librarycalendar.fragment.CalendarViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  CalendarViewPagerFragment.OnPageChangeListener,

        CalendarViewFragment.OnDateClickListener,   CalendarViewFragment.OnDateCancelListener {
    /*日历集成*/

    private TextView tv_date;

    private boolean isChoiceModelSingle = true;

    private List<CalendarDate> mListDate = new ArrayList<>();
    private Button   btn_starttonewactivity;





    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tv_date = (TextView) findViewById(R.id.tv_date);
        btn_starttonewactivity = (Button)findViewById(R.id.btn_starttonewactivity);

        initFragment();
        btn_starttonewactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,NewCalendarActivity.class);
                startActivity(intent);
            }
        });

    }



    private void initFragment(){

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction tx = fm.beginTransaction();

        // Fragment fragment = new CalendarViewPagerFragment();

        Fragment fragment = CalendarViewPagerFragment.newInstance(isChoiceModelSingle);

        tx.replace(R.id.fl_content, fragment);

        tx.commit();

    }





    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
  //注释掉以后可以取消菜单按钮

        /*getMenuInflater().inflate(R.menu.menu_im, menu);*/

        return true;

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_single:

                isChoiceModelSingle = true;

                initFragment();

                break;

            case R.id.menu_multi:

                isChoiceModelSingle = true;

                initFragment();

                break;

            default:

                break;

        }

        return true;

    }

    @Override

    public void onDateClick(CalendarDate calendarDate) {



        int year = calendarDate.getSolar().solarYear;

        int month = calendarDate.getSolar().solarMonth;

        int day = calendarDate.getSolar().solarDay;

        if (isChoiceModelSingle) {

            tv_date.setText(year + "-" + month + "-" + day);

        } else {

            //System.out.println(calendarDate.getSolar().solarDay);

            mListDate.add(calendarDate);

            tv_date.setText(listToString(mListDate));

        }



    }



    @Override

    public void onDateCancel(CalendarDate calendarDate) {

        int count = mListDate.size();

        for (int i = 0; i < count; i++) {

            CalendarDate date = mListDate.get(i);

            if (date.getSolar().solarDay == calendarDate.getSolar().solarDay) {

                mListDate.remove(i);

                break;

            }

        }

        tv_date.setText(listToString(mListDate));

    }



    @Override

    public void onPageChange(int year, int month) {

        tv_date.setText(year + "-" + month);

        mListDate.clear();

    }



    private static String listToString(List<CalendarDate> list) {

        StringBuffer stringBuffer = new StringBuffer();

        for (CalendarDate date : list) {

            stringBuffer.append(date.getSolar().solarYear + "-" + date.getSolar().solarMonth + "-" + date.getSolar().solarDay).append(" ");

        }

        return stringBuffer.toString();

    }



}
