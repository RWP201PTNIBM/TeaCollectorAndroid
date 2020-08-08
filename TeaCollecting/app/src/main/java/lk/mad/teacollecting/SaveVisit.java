package lk.mad.teacollecting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SaveVisit extends AppCompatActivity {

    TextView txtPathName, txtDate, txtSupplierName;
    EditText txtTeaBags, txtWeight;
    Button btnok;
    String supplier_name, date_to, pathnametext;
    int path_id, supplier_id;
    ConnnectionClass connnectionClass;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_visit);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        connnectionClass = new ConnnectionClass();
        progressDialog = new ProgressDialog(this);
        txtPathName = (TextView) findViewById(R.id.txtPathNameView);
        txtDate = (TextView) findViewById(R.id.txtDateView);
        txtSupplierName = (TextView) findViewById(R.id.txtSupNameView);
        txtTeaBags = (EditText) findViewById(R.id.txtbag);
        txtWeight = (EditText) findViewById(R.id.txtkilogram);
        btnok = (Button) findViewById(R.id.btnOk);
        date_to = new SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(new Date());   // get Current Date in this
        txtDate.setText(date_to);
        supplier_name = getIntent().getExtras().getString("supplier_Name");
        supplier_id = getIntent().getExtras().getInt("supplierid");
        path_id = getIntent().getExtras().getInt("pathid");
        txtSupplierName.setText(supplier_name + "  " + supplier_id);

        SaveVisit.GetPathName GetPathName = new SaveVisit.GetPathName();
        GetPathName.execute();


        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveVisit.Doregister Doregister = new SaveVisit.Doregister();
                Doregister.execute();
            }
        });
    }

    public class GetPathName extends AsyncTask<String, String, String> {
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
                    String query2 = "select path_name from path WHERE path_id='" + path_id + "'";

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
            txtPathName.setText(pathnametext);
        }
    }

    public class Doregister extends AsyncTask<String, String, String> {

        int teaBags = Integer.parseInt(txtTeaBags.getText().toString());
        double teaWeight = Double.parseDouble(txtWeight.getText().toString());
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
                        String query = "insert into collection_log(`supplier_id`,`weight`, `no_of_bags`, `status`, `visit_id`) values('" + supplier_id + "','" + teaWeight + "','" + teaBags + "',0,1)";
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);
                        z = "Register successfull";
                        isSucess = true;
                    }
                } catch (Exception ex) {
                    isSucess = false;
                    z = "Exceptions" + ex;
                }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {
            if (isSucess) {
                Toast.makeText(getBaseContext(), "" + z, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SaveVisit.this, startCollecting.class);
                startActivity(intent);
            }
            progressDialog.hide();
        }
    }



}