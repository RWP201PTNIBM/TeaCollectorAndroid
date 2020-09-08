package lk.mad.teacollecting;
//192.168.8.100.
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnnectionClass {
    String classs = "com.mysql.jdbc.Driver";
//192.168.0.109
//    String url = "jdbc:mysql://127.0.0.1:3306/androidtest?";
//GRANT ALL ON *.* to root@'192.168.43.236' IDENTIFIED BY '';

    String url = "jdbc:mysql://192.168.43.236/tea_collector_db";
    String un = "root";
    String password = "";

    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {

            Class.forName(classs);

            conn = DriverManager.getConnection(url, un, password);


            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERRO SQL", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO CLASS NOT", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO COMMON", e.getMessage());
        }
        return conn;
    }
}
