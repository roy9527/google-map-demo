package com.test.mapsdemo;

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
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.MapActivity;

public class DetailActivity extends MapActivity {
	private GoogleMap map;
	MapView mv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		mv = (MapView) findViewById(R.id.mapView);
		mv.onCreate(savedInstanceState);
		map = mv.getMap();
		init();
		initDemoPoint();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mv.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mv.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mv.onResume();
	}

	private void init() {
//		MapFragment mf = (MapFragment) getFragmentManager().findFragmentById(
//				R.id.map);
//		map = mf.getMap();
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
				startActivity(new Intent(DetailActivity.this,
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
				TextView tv = new TextView(DetailActivity.this);
				tv.setText("this marker position is "
						+ marker.getPosition().toString());
				tv.setBackgroundColor(Color.GREEN);
				tv.setTextColor(Color.BLACK);
				return tv;
			}
		});
	}

	/**
	 * ��ʼ��һЩdemo��
	 */
	private void initDemoPoint() {
		final LatLng ll = new LatLng(37.339085, -121.8914807);
		MarkerOptions mo = new MarkerOptions();
		mo.position(ll);
		map.addMarker(mo);

		// move to marker position.
		CameraUpdate cu = CameraUpdateFactory.newLatLng(ll);
		map.animateCamera(cu);

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
