package ynov.worldvisit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;

import ynov.worldvisit.database.DatabaseHelper;

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
        int wDay=c.get(Calendar.DAY_OF_WEEK);
        String dayOfWeek = new String();
        String Month = new String();
        switch (wDay){
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
        switch (month){
            case 0:
                Month = "Janvier";
            case 1:
                Month = "Fevrier";
            case 2:
                Month = "Mars";
            case 3:
                Month = "Avril";
            case 4:
                Month = "Mai";
            case 5:
                Month = "Juin";
            case 6:
                Month = "Juillet";
            case 7:
                Month = "Août";
            case 8:
                Month = "Septembre";
            case 9:
                Month = "Octobre";
            case 10:
                Month = "Novembre";
            case 11:
                Month = "Décembre";
        }

        // Traitement de l'affichage du jours et du mois (qui ne marche pas ...)
        String displayedDay = dayOfWeek+". "+dayOfMonth+" "+Month+". "+year;
        day.setText(displayedDay);
        // perform setOnDateChangeListener event on CalendarView
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int Month, int dayOfMonth) {
                long selectedDate = calendarView.getDate();
            }
        });
    }

    public void backToHome(View view) {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        /*intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);*/
        startActivity(intent);
    }
}
