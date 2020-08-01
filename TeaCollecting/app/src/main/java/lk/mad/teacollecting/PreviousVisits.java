package lk.mad.teacollecting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PreviousVisits extends AppCompatActivity {

    ConnnectionClass connnectionClass;
    ProgressDialog progressDialog;
    TextView txtNewVistPName, txtNewDatePView;
    int pathid;
    String drivername;
    String pathnametext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_visits);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        connnectionClass = new ConnnectionClass();
        progressDialog = new ProgressDialog(this);
        txtNewVistPName = (TextView) findViewById(R.id.txtNewVistPName);
        txtNewDatePView = (TextView) findViewById(R.id.txtNewDatePView);

        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());   // get Current Date in this
        txtNewDatePView.setText(date_n);
        drivername = getIntent().getExtras().getString("name");
        pathid = getIntent().getExtras().getInt("pathid");
        PreviousVisits.DoPrevoisList DoPrevoisList = new PreviousVisits.DoPrevoisList();
        DoPrevoisList.execute();
    }
    public class DoPrevoisList extends AsyncTask<String, String, String> {

        String z = "";
        boolean isSucess = false;


        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading ......");
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                Connection con = connnectionClass.CONN();
                if (con == null) {
                    z = "Please check your internet connection";


                } else {
                    String query2 = "select path_name from path WHERE path_id='" + pathid + "'";

                    Statement stmt2 = con.createStatement();

                    ResultSet rs2 = stmt2.executeQuery(query2);

                    while (rs2.next()) {
                        pathnametext = rs2.getString("path_name");
                    }

                }
            } catch (Exception ex) {
                isSucess = false;
                z = "Exceptions" + ex;
            }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {
            txtNewVistPName.setText(pathnametext);

        }
    }

}