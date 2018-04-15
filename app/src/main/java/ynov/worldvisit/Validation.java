package ynov.worldvisit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;

public class Validation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);

        final CalendarView calendarView = (CalendarView) findViewById(R.id.calendar); // get the reference of CalendarView
        TextView day = (TextView) findViewById(R.id.dayVisit);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        //on récupère le numéro du jour dans la semaine
        int cDay=c.get(Calendar.DAY_OF_WEEK);
        String dayOfWeek = new String();
        switch (cDay){
            case 0:
                dayOfWeek = "Dim";
            case 1:
                dayOfWeek = "Lun";
            case 2:
                dayOfWeek = "Mar";
            case 3:
                dayOfWeek = "Mer";
            case 4:
                dayOfWeek = "Jeu";
            case 5:
                dayOfWeek = "Ven";
            case 6:
                dayOfWeek = "Sam";
        }
        String displayedDay = dayOfWeek+". "+dayOfMonth+" "+month+1+". "+year;
        day.setText(displayedDay);
        // perform setOnDateChangeListener event on CalendarView
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                long selectedDate = calendarView.getDate();
            }
        });
    }

    public void backToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        /*intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);*/
        startActivity(intent);
    }
}
