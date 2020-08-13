package lk.mad.teacollecting;

import androidx.fragment.app.FragmentActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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


public class startCollecting extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    ConnnectionClass connnectionClass;
    ProgressDialog progressDialog;
    ListView startListView;
    ThreeColumn_ListAdapter adapter;
    ArrayList<Supplier> Supplier = new ArrayList<Supplier>();
    ArrayList<Location> listLanLong = new ArrayList<Location>();
    int supplierid, pathid, driver_id;
    String supplier_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_collecting);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.startMap);
        connnectionClass = new ConnnectionClass();
        progressDialog = new ProgressDialog(this);
        mapFragment.getMapAsync(this);
        startListView = (ListView) findViewById(R.id.startMapList);
        startCollecting.DSupplierList DSupplierList = new startCollecting.DSupplierList();
        DSupplierList.execute();
        pathid = getIntent().getExtras().getInt("pathid");
        driver_id = getIntent().getExtras().getInt("driver_id");

        startListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View convertView, int position, long id) {
                supplierid = adapter.getItem(position).getSup_id();
                supplier_Name = adapter.getItem(position).getSup_Name();
                Intent intent = new Intent(startCollecting.this, SaveVisit.class);
                intent.putExtra("driver_id", driver_id);
                intent.putExtra("pathid", pathid);
                intent.putExtra("supplierid", supplierid);
                intent.putExtra("supplier_Name", supplier_Name);
                startActivity(intent);
            }
        });


    }

    public class DSupplierList extends AsyncTask<String, String, String> {
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
                    String query = "select s.* from supplier s,collection_point cp, visit v  WHERE cp.path_id='" + pathid + "' and cp.cp_id = v.cp_id and v.date = curdate() and cp.cp_id = s.cp_id and s.supplier_id = v.supplier_id";
                    String query2 = "select cp.* from collection_point cp, visit v, path p WHERE cp.path_id='" + pathid + "' and cp.cp_id = v.cp_id and v.date = curdate()";

                    Statement stmt = con.createStatement();
                    Statement stmt2 = con.createStatement();

                    ResultSet rs = stmt.executeQuery(query);
                    ResultSet rs2 = stmt2.executeQuery(query2);


                    while (rs.next()) {
                        Supplier supplier = new Supplier(rs.getInt(1), rs.getString(2), rs.getString(3));
                        Supplier.add(supplier);
                    }

                    while (rs2.next()) {
                        Location locate = new Location(Double.parseDouble(rs2.getString(4)), Double.parseDouble(rs2.getString(3)), rs2.getString(2));
                        listLanLong.add(locate);
                    }
                    adapter = new ThreeColumn_ListAdapter(startCollecting.this, R.layout.list_adapter_view, Supplier);

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
            float zoomLevel = 9.0f;

            startListView.setAdapter(adapter);

            for (int i = 0; i < listLanLong.size(); i++) {
                Log.e("", "--------jjj");
                LatLng sydny = new LatLng(listLanLong.get(i).getLat(), listLanLong.get(i).getLon());
                mMap.addMarker(new MarkerOptions().position(sydny).title(listLanLong.get(i).getName()));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydny));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydny, zoomLevel));

            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        float zoomLevel = 11.0f;

//         Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(7.940096, 81.019539);
//        LatLng weee = new LatLng(7.940976, 80.9981483);
//
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Polonnaruwa"));
//        mMap.addMarker(new MarkerOptions().position(weee).title("Marker in Polonnaruwa Wewa"));
////        This goes up to 21
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(weee));
//
//
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}