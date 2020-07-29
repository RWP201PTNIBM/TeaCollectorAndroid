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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddToList extends AppCompatActivity {

    ListView list;
    EditText pluckerId;
    Button Add;
    ConnnectionClass connnectionClass;
    ProgressDialog progressDialog;
    SimpleAdapter ADAhere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_list);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        list = (ListView) findViewById(R.id.listPluckers);
        Add = (Button) findViewById(R.id.btnAdd);
        connnectionClass = new ConnnectionClass();
        progressDialog = new ProgressDialog(this);
        AddToList.DoAddList DoAddList = new AddToList.DoAddList();
        DoAddList.execute();
//        Add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AddToList.DoAddList DoAddList = new AddToList.DoAddList();
//                DoAddList.execute();
//            }
//        });

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
                        String query = "select * from driver";

                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);

                        List<Map<String, String>> data = null;
                        data = new ArrayList<Map<String, String>>();

                        while (rs.next()) {
                            Map<String, String> datanum = new HashMap<String, String>();
                            datanum.put("A", rs.getString(4).toString());
                            data.add(datanum);
                        }
                        String[] fromwhere = { "A" };

                        int[] viewswhere = { R.id.lblteapluckcer };
                        ADAhere = new SimpleAdapter(AddToList.this, data,
                                R.layout.listtemplate, fromwhere, viewswhere);
                        while (rs.next()) {
                            z += rs.getString(4).toString() + "\n";

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
            list.setAdapter(ADAhere);
        }
    }
}