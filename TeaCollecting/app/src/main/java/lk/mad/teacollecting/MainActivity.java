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
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    EditText name, email, pass;
    Button register;
    ProgressDialog progressDialog;
    ConnnectionClass connnectionClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        name = (EditText) findViewById(R.id.txtName);
        email = (EditText) findViewById(R.id.txtEmail);
        pass = (EditText) findViewById(R.id.txtPass);
        register = (Button) findViewById(R.id.btnRegister);

        connnectionClass = new ConnnectionClass();

        progressDialog = new ProgressDialog(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Doregister doregister = new Doregister();
                doregister.execute();
            }
        });
    }

    public class Doregister extends AsyncTask<String, String, String> {

        String namestr = name.getText().toString();
        String emailStr = email.getText().toString();
        String passStr = pass.getText().toString();
        String z = "";
        boolean isSucess = false;

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading ......");
        }

        @Override
        protected String doInBackground(String... strings) {
            if (namestr.trim().equals("") || emailStr.trim().equals("") || passStr.trim().equals(""))
                z = "Please enter all fields....";
            else {
                try {
                    Connection con = connnectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {
                        String query = "insert into android values('" + namestr + "','" + emailStr + "','" + passStr + "')";
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);
                        z = "Register successfull";
                        isSucess = true;
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
            if (isSucess) {
                Toast.makeText(getBaseContext(), "" + z, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, LoginForm.class);
                startActivity(intent);

            }
            progressDialog.hide();
        }
    }
}