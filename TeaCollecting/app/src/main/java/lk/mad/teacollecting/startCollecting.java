package lk.mad.teacollecting;

import androidx.fragment.app.FragmentActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class startCollecting extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    ConnnectionClass connnectionClass;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_collecting);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.startMap);
        connnectionClass = new ConnnectionClass();
        progressDialog = new ProgressDialog(this);
        mapFragment.getMapAsync(this);
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoomLevel = 11.0f;
//         Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(7.940096, 81.019539);
        LatLng weee = new LatLng(7.940976, 80.9981483);

        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Polonnaruwa"));
        mMap.addMarker(new MarkerOptions().position(weee).title("Marker in Polonnaruwa Wewa"));
//        This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(weee));


        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}