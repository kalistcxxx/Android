//package com.corp.k.androidos.android.view.customviews;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.GoogleMapOptions;
//import com.google.android.gms.maps.MapView;
//import com.google.android.gms.maps.OnMapReadyCallback;
//
///**
// * Created by hoangtuan on 1/17/17.
// */
//public class CustomMap extends MapView {
//
//    public CustomMap(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public CustomMap(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//    }
//
//    public CustomMap(Context context, GoogleMapOptions options) {
//        super(context, options);
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        int action = event.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                // Disallow ScrollView to intercept touch events.
//                if(event.getPointerCount() == 2) {
//                    this.getParent().requestDisallowInterceptTouchEvent(true);
//                }
//                break;
//            case MotionEvent.ACTION_MOVE:
//                // Disallow ScrollView to intercept touch events.
//                if(event.getPointerCount() == 2) {
////                    if(getMapAsync();!=null) {
////                        getMap().getUiSettings().setScrollGesturesEnabled(true);
////                    }
//                    getMapAsync(new OnMapReadyCallback() {
//                        @Override
//                        public void onMapReady(GoogleMap googleMap) {
//                            googleMap.getUiSettings().setScrollGesturesEnabled(true);
//                        }
//                    });
//                    this.getParent().requestDisallowInterceptTouchEvent(true);
//                }else{
//                    return true;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                // Allow ScrollView to intercept touch events.
////                if(getMap()!=null) {
////                    getMap().getUiSettings().setScrollGesturesEnabled(false);
////                }
//                getMapAsync(new OnMapReadyCallback() {
//                    @Override
//                    public void onMapReady(GoogleMap googleMap) {
//                        googleMap.getUiSettings().setScrollGesturesEnabled(false);
//                    }
//                });
//                this.getParent().requestDisallowInterceptTouchEvent(false);
//                break;
//        }
//
//        // Handle MapView's touch events.
//        super.dispatchTouchEvent(event);
//        return true;
//    }
//}
