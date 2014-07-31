package com.test.mapsdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {

	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		initDemoPoint();
	}

	private void init() {
		MapFragment mf = (MapFragment) getFragmentManager().findFragmentById(
				R.id.map);
		map = mf.getMap();
		map.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker m) {
				m.showInfoWindow();
				return true;
			}
		});
		map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

			@Override
			public void onInfoWindowClick(Marker m) {
				m.hideInfoWindow();
				startActivity(new Intent(MainActivity.this,
						DetailActivity.class));
			}
		});
		map.setInfoWindowAdapter(new InfoWindowAdapter() {

			@Override
			public View getInfoWindow(final Marker marker) {
				return null;
			}

			@Override
			public View getInfoContents(Marker marker) {
				TextView tv = new TextView(MainActivity.this);
				tv.setText("this marker position is "
						+ marker.getPosition().toString());
				tv.setBackgroundColor(Color.GREEN);
				tv.setTextColor(Color.BLACK);
				return tv;
			}
		});
	}
	
	/**
	 * 初始化一些demo点
	 */
	private void initDemoPoint() {
		final LatLng ll = new LatLng(37.339085, -121.8914807);
		MarkerOptions mo = new MarkerOptions();
		mo.position(ll);
		map.addMarker(mo);
		
		//move to marker position.
		CameraUpdate cu = CameraUpdateFactory.newLatLng(ll);
		map.animateCamera(cu);
		
	}
}
