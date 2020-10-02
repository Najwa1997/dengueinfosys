package com.example.dengueinfosys.Interface;

import com.google.android.gms.maps.model.LatLng;

import com.example.dengueinfosys.MyLatLng;

import java.util.List;

public interface IOnLoadLocationListener {
    void onLoadLocationSuccess(List<MyLatLng> latLngs);
    void onLoadLocationFailed(String message);
}