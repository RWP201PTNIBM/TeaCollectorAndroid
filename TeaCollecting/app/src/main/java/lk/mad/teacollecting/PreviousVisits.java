package lk.mad.teacollecting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PreviousVisits extends AppCompatActivity {

    ConnnectionClass connnectionClass;
    ProgressDialog progressDialog;
    TextView txtNewVistPName, txtNewDatePView, txtSelectDate;
    EditText txtVisitPlucker;
    int pathid;
    String drivername;
    String pathnametext;
    ListView listVisitPluckers;
    private Calendar calendar;
    private String date;
    DatePickerDialog.OnDateSetListener setListener;
Four_cloumn_list_adpter adapter1;
ArrayList<VisitDetails> VisitDetails = new ArrayList<VisitDetails>();

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
        txtVisitPlucker = (EditText) findViewById(R.id.txtVisitPlucker);
        listVisitPluckers = (ListView) findViewById(R.id.listVisitPluckers);
        txtSelectDate = (TextView) findViewById(R.id.txtSelectDate);

        calendar=Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        txtSelectDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        PreviousVisits.this, setListener,year,month,day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                date = year+"-"+month+"-"+dayOfMonth;
                txtSelectDate.setText(date);

                PreviousVisits.DoPreviiosVisits DoPreviiosVisits = new PreviousVisits.DoPreviiosVisits();
                DoPreviiosVisits.execute();
            }
        };

        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());   // get Current Date in this
        txtNewDatePView.setText(date_n);
        drivername = getIntent().getExtras().getString("name");
        pathid = getIntent().getExtras().getInt("pathid");
        PreviousVisits.DoPrevoisList DoPrevoisList = new PreviousVisits.DoPrevoisList();
        DoPrevoisList.execute();
        txtVisitPlucker.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (adapter1 != null){
                    adapter1.getFilter().filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
    public class DoPreviiosVisits extends AsyncTask<String, String, String> {
        String z = "";
        boolean isSucess = false;

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading ......");
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                VisitDetails = new ArrayList<VisitDetails>();
                Connection con = connnectionClass.CONN();
                if (con == null) {
                    z = "Please check your internet connection";
                } else {
                    String query;
                    if(date != null) {
                        query = "SELECT s.supplier_id, cl.no_of_bags, s.supplier_name, cl.weight FROM supplier s,collection_log cl WHERE s.supplier_id = cl.supplier_id AND cl.date BETWEEN TIMESTAMP('"+date+" 00:00:00') AND TIMESTAMP('"+date+" 23:59:59')";
                        // AND cl.date  BETWEEN TIMESTAMP('2020-08-14 00:00:00') AND TIMESTAMP('2020-08-14 23:59:59')
                    }
                    else
                    {
                        query = "SELECT s.supplier_id, cl.no_of_bags, s.supplier_name, cl.weight FROM supplier s,collection_log cl WHERE s.supplier_id = cl.supplier_id";
                    }

                    Statement stmt = con.createStatement();

                    ResultSet rs = stmt.executeQuery(query);

                    while (rs.next()) {

                        VisitDetails visitDetails = new VisitDetails(rs.getInt("supplier_id"),rs.getInt("no_of_bags"),rs.getString("supplier_name"),rs.getDouble("weight"));
                        VisitDetails.add(visitDetails);
                    }
                    adapter1 =  new Four_cloumn_list_adpter(PreviousVisits.this,R.layout.list_adapter_four_view, VisitDetails);
                }
            } catch (Exception ex) {
                isSucess = false;
                z = "Exceptions" + ex;
            }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {
            listVisitPluckers.setAdapter(adapter1);
        }
    }
}