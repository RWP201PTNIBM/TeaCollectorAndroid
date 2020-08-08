package lk.mad.teacollecting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AddToList extends AppCompatActivity {
    Date convertedDate;
    ListView list;
    EditText txtPlucker;
    TextView txtNewVistName, txtNewDateView;
    ConnnectionClass connnectionClass;
    ProgressDialog progressDialog;
    SimpleAdapter ADAhere;
    int pathid,supplierid,collectpnt_id,driver_id;
    String drivername;
    String pathnametext,date_n;
    ArrayList<Supplier> Supplier = new ArrayList<Supplier>();
    ThreeColumn_ListAdapter adapter;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_list);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        connnectionClass = new ConnnectionClass();
        progressDialog = new ProgressDialog(this);
        list = (ListView) findViewById(R.id.listPluckers);
//        Add = (Button) findViewById(R.id.btnAdd);
        txtNewVistName = (TextView) findViewById(R.id.txtNewVistName);
        txtNewDateView = (TextView) findViewById(R.id.txtNewDateView);
        txtPlucker = (EditText) findViewById(R.id.txtPlucker);
          date_n = new SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(new Date());   // get Current Date in this

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = dateFormat.format(calendar.getTime());

        txtNewDateView.setText(date_n);
        drivername = getIntent().getExtras().getString("name");
        pathid = getIntent().getExtras().getInt("pathid");
        driver_id= getIntent().getExtras().getInt("driver_id");
        calendar=Calendar.getInstance();



        AddToList.DoAddList DoAddList = new AddToList.DoAddList();
        DoAddList.execute();
//        Add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AddToList.DoAddList DoAddList = new AddToList.DoAddList();
//                DoAddList.execute();
//            }
//        });
        txtPlucker.addTextChangedListener(new  TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                AddToList.this.adapter.getFilter().filter(cs);
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) { }

            @Override
            public void afterTextChanged(Editable arg0) {}
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View convertView, int position, long id) {

                supplierid = adapter.getItem(position).getSup_id();
                AddToList.DoAddVisit DoAddVisit = new AddToList.DoAddVisit();
                DoAddVisit.execute();
            }

        });
    }
    public class DoAddList extends AsyncTask<String, String, String> {
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
                        String query = "select * from supplier";
                        String query2 = "select path_name from path WHERE path_id='" + pathid + "'";

                        Statement stmt = con.createStatement();
                        Statement stmt2 = con.createStatement();

                        ResultSet rs = stmt.executeQuery(query);
                        ResultSet rs2 = stmt2.executeQuery(query2);

                        List<Map<String, String>> data = null;
                        data = new ArrayList<Map<String, String>>();

                        while (rs.next()) {
                            Supplier supplier = new Supplier(rs.getInt(1),rs.getString(2),rs.getString(3));
                            Supplier.add(supplier);
                        }
                        adapter =  new ThreeColumn_ListAdapter(AddToList.this,R.layout.list_adapter_view, Supplier);


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
            list.setAdapter(adapter);
//            list.setAdapter(ADAhere);
            txtNewVistName.setText(pathnametext);
        }
    }
    public class DoAddVisit extends AsyncTask<String, String, String> {

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
                    String query2 = "select cp_id from supplier WHERE supplier_id='" + supplierid + "'";

                    Statement stmt2 = con.createStatement();

                    ResultSet rs2 = stmt2.executeQuery(query2);

                    while (rs2.next()) {
                        collectpnt_id = rs2.getInt("cp_id");
                    }
                    String query4 = "INSERT INTO `visit`(`date`, `status`, `driver_id`, `cp_id`, `supplier_id`) VALUES ('" + date + "',0,'" + driver_id + "','" + collectpnt_id + "','" + supplierid + "')";
                    Statement stmt4 = con.createStatement();
                    stmt4.executeUpdate(query4);
                }
            } catch (Exception ex) {
                isSucess = false;
                z = "Exceptions" + ex;
            }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.hide();


        }
    }
    private Date currentdate() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        return cal.getTime();
    }
}