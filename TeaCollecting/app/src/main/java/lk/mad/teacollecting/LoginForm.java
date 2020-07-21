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
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginForm extends AppCompatActivity {
    EditText name, pass;
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
    }

    public class DoLogin extends AsyncTask<String, String, String> {

        String namestr = name.getText().toString();
        String passStr = pass.getText().toString();
        String z = "";
        boolean isSucess = false;

        String nme, pas;

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
                        String query = " select * from android where email='" + namestr + "' and pass = '" + passStr + "'";

                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            nme = rs.getString(2);
                            pas = rs.getString(3);


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
            Toast.makeText(getBaseContext(), "" + z, Toast.LENGTH_LONG).show();

            if (isSucess) {
                Intent intent = new Intent(LoginForm.this, MainMenu.class);

                intent.putExtra("name", namestr);

                startActivity(intent);
            }


            progressDialog.hide();
        }
    }
}