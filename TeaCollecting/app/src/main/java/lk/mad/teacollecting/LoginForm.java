package lk.mad.teacollecting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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

public class LoginForm extends AppCompatActivity {
    EditText name, pass;
    TextView txtForget;
    Button login;
    ProgressDialog progressDialog;
    ConnnectionClass connnectionClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        name = (EditText) findViewById(R.id.txtEmail);
        txtForget = (TextView) findViewById(R.id.forgetpassowrdText);
        pass = (EditText) findViewById(R.id.txtPass);
        login = (Button) findViewById(R.id.btnlogin);

        connnectionClass = new ConnnectionClass();

        progressDialog = new ProgressDialog(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginForm.DoLogin doregister = new LoginForm.DoLogin();
                doregister.execute();
            }
        });
        txtForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0728887599"));
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() { }


    public class DoLogin extends AsyncTask<String, String, String> {

        String namestr = name.getText().toString();
        String passStr = pass.getText().toString();
        String z = "";
        boolean isSucess = false;

        String nme, pas;
        int pathid, driver_id;

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading ......");
        }

        @Override
        protected String doInBackground(String... strings) {
            if (namestr.trim().equals("") || passStr.trim().equals(""))
                z = "Please enter all fields....";
            else {
                try {
                    Connection con = connnectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {
//                        String query="select * from  android where name('"+namestr+"','"+emailStr+"','"+passStr+"')";
                        String query = " select * from driver where username='" + namestr + "' and password = '" + passStr + "'";

                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            driver_id = rs.getInt(1);
                            nme = rs.getString(4);
                            pas = rs.getString(5);
                            pathid = rs.getInt(9);

                            new Driver(driver_id, nme, pathid);

                            if (nme.equals(namestr) && pas.equals(passStr)) {

                                isSucess = true;
                                z = "Login successfull";

                            } else
                                isSucess = false;

                        }
                    }
                } catch (Exception ex) {
                    isSucess = false;
                    z = "Exceptions" + ex;
                }
            }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {
//            Toast.makeText(getBaseContext(), "" + z, Toast.LENGTH_LONG).show();

            if (isSucess) {
                Intent intent = new Intent(LoginForm.this, MainMenu.class);
                intent.putExtra("driver_id", driver_id);
                intent.putExtra("name", namestr);
                intent.putExtra("pathid", pathid);

                startActivity(intent);
            } else {
                Toast.makeText(getBaseContext(), "Invalid Login Credintials Please Try Again", Toast.LENGTH_LONG).show();
            }

            progressDialog.hide();
        }
    }
}