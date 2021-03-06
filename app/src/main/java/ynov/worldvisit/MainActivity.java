package ynov.worldvisit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import ynov.worldvisit.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private Button btnStore, btnGetall;
    private EditText etname;
    private DatabaseHelper databaseHelper;
    private TextView tvnames;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        tvnames = (TextView) findViewById(R.id.tvnames);

        btnStore = (Button) findViewById(R.id.btnstore);
        btnGetall = (Button) findViewById(R.id.btnget);
        etname = (EditText) findViewById(R.id.etname);

        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*databaseHelper.addpaysDetail(etname.getText().toString());*/
                etname.setText("");
                Toast.makeText(MainActivity.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
            }
        });
        btnGetall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList = databaseHelper.getAllpaysList();
                tvnames.setText("");
                for (int i = 0; i < arrayList.size(); i++){
                    tvnames.setText(tvnames.getText().toString()+", "+arrayList.get(i));
                }
            }
        });
    }

    public void addCountry(View view) {
        Intent intent = new Intent(this, Research.class);
        /*intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);*/
        startActivity(intent);
    }
}