package com.jelcaf.pacomf.patealapalma.views;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.facebook.widget.ProfilePictureView;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.adapter.CommentAdapter;
import com.jelcaf.pacomf.patealapalma.binding.dao.Comment;
import com.jelcaf.pacomf.patealapalma.binding.dao.Geo;
import com.jelcaf.pacomf.patealapalma.login.LoginMethods;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.PathOverlay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Paco on 01/04/2015.
 */
public class CustomPopUpMap extends DialogFragment {

    private double latitudeCenter, longitudeCenter;
    private List<Geo> coordinates, waterpoints;
    private Context ctx;

    public static CustomPopUpMap newInstance() {
        return new CustomPopUpMap();
    }

    public void setParams (Context ctx, double latitudeCenter, double longitudeCenter, List<Geo> coordinates, List<Geo> waterpoints){
        this.latitudeCenter = latitudeCenter;
        this.longitudeCenter = longitudeCenter;
        this.coordinates = coordinates;
        this.waterpoints = waterpoints;
        this.ctx = ctx;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Remove the default background
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Inflate the new view with margins and background
        View v = inflater.inflate(R.layout.popup_layout_map, container, false);

        MapView map = (MapView) v.findViewById(R.id.map);

        initMap(map);

        drawPoints(map);

        return v;
    }

    protected void initMap(MapView map){
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        GeoPoint startPoint = new GeoPoint(latitudeCenter, longitudeCenter);
        IMapController mapController = map.getController();
        mapController.setZoom(15);
        mapController.setCenter(startPoint);
    }

    protected void drawPoints(MapView map){
        if ((coordinates != null) && (!coordinates.isEmpty())){
            DefaultResourceProxyImpl resourceProxy = new DefaultResourceProxyImpl(ctx);
            final ArrayList<OverlayItem> items = new ArrayList<>();
            Drawable myCurrentLocationMarker;
            OverlayItem myLocationOverlayItem;
            PathOverlay pathOverlay = new PathOverlay(Color.RED, ctx);
            int index = 0;
            for (Geo geo: coordinates){
                if ((index == 0) || (index==coordinates.size()-1)) {
                    myLocationOverlayItem = new OverlayItem("La Palma", "Islas Canarias", new GeoPoint(geo.getLatitud(), geo.getLongitud()));
                    myCurrentLocationMarker = this.getResources().getDrawable(R.drawable.marker);
                    myLocationOverlayItem.setMarker(myCurrentLocationMarker);
                    items.add(myLocationOverlayItem);
                }
                pathOverlay.addPoint(new GeoPoint(geo.getLatitud(), geo.getLongitud()));
                index++;
            }

            for (Geo geo: waterpoints){
                myLocationOverlayItem = new OverlayItem("La Palma", "Islas Canarias", new GeoPoint(geo.getLatitud(), geo.getLongitud()));
                myCurrentLocationMarker = this.getResources().getDrawable(R.drawable.water_bottle);
                myLocationOverlayItem.setMarker(myCurrentLocationMarker);
                items.add(myLocationOverlayItem);
            }

            ItemizedIconOverlay<OverlayItem> currentLocationOverlay = new ItemizedIconOverlay<>(items,
                    new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                        public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                            return true;
                        }
                        public boolean onItemLongPress(final int index, final OverlayItem item) {
                            return true;
                        }
                    }, resourceProxy);

            map.getOverlays().add(currentLocationOverlay);
            map.getOverlays().add(pathOverlay);
            map.invalidate();
        }
    }


}
