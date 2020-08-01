package lk.mad.teacollecting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    Button path, visits, newVisits, startCollecting;
    TextView txtname;
    int pathid;
    String drivername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        txtname = (TextView) findViewById(R.id.txtname);
        path = (Button) findViewById(R.id.btnPath);
        visits = (Button) findViewById(R.id.btnVisits);
        newVisits = (Button) findViewById(R.id.btnNeWVisit);
        startCollecting = (Button) findViewById(R.id.btnstrtcol);
        drivername = getIntent().getExtras().getString("name");
        pathid = getIntent().getExtras().getInt("pathid");

        txtname.setText("Hello " + drivername);

        path.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, MapsActivity.class);
                intent.putExtra("name", drivername);
                intent.putExtra("pathid", pathid);
                startActivity(intent);
            }
        });

        visits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, PreviousVisits.class);
                intent.putExtra("name", drivername);
                intent.putExtra("pathid", pathid);
                startActivity(intent);
            }
        });

        newVisits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, AddToList.class);
                intent.putExtra("name", drivername);
                intent.putExtra("pathid", pathid);
                startActivity(intent);
            }
        });

        startCollecting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}