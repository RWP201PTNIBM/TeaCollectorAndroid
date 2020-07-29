package lk.mad.teacollecting;

import androidx.fragment.app.FragmentActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ConnnectionClass connnectionClass;
    ProgressDialog progressDialog;
    ArrayList<Location> listLanLong = new ArrayList<Location>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        connnectionClass = new ConnnectionClass();
        progressDialog = new ProgressDialog(this);
        mapFragment.getMapAsync(this);
        MapsActivity.DoAddList doregister = new MapsActivity.DoAddList();
        doregister.execute();
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
                    String query = "select * from collection_point";

                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);


                    while (rs.next()) {
                        Location locate = new Location(Double.parseDouble(rs.getString(4)), Double.parseDouble(rs.getString(3)));
                        listLanLong.add(locate);
                    }


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
            float zoomLevel = 10.0f;

            for (int i = 0; i < listLanLong.size(); i++) {
                Log.e("", "--------jjj");
                LatLng sydny = new LatLng(listLanLong.get(i).getLat(), listLanLong.get(i).getLon());
                mMap.addMarker(new MarkerOptions().position(sydny).title("Marker in Polonnaruwa " + i));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydny));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydny, zoomLevel));

            }

        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoomLevel = 11.0f;
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(7.940096, 81.019539);
//        LatLng weee = new LatLng(7.940976, 80.9981483);
//
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Polonnaruwa"));
//        mMap.addMarker(new MarkerOptions().position(weee).title("Marker in Polonnaruwa Wewa"));
        //This goes up to 21
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(weee));


//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}