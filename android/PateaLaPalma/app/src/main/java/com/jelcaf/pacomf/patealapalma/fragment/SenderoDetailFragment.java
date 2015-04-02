package com.jelcaf.pacomf.patealapalma.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.binding.dao.Geo;
import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;
import com.jelcaf.pacomf.patealapalma.views.CustomMapView;
import com.jelcaf.pacomf.patealapalma.views.CustomPopUpMap;
import com.mobandme.android.bind.Binder;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.PathOverlay;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a single Sendero detail screen.
 * This fragment is either contained in a {@link com.jelcaf.pacomf.patealapalma.activity.SenderoListActivity}
 * in two-pane mode (on tablets) or a {@link com.jelcaf.pacomf.patealapalma.activity.SenderoDetailActivity}
 * on handsets.
 */
public class SenderoDetailFragment extends Fragment {
   /**
    * The fragment argument representing the item ID that this fragment
    * represents.
    */
   public static final String ARG_ITEM_ID = "item_id";

   /**
    * The sendero content this fragment is presenting.
    */
   private Sendero mSendero;

   View rootView;
   Binder myModelBinder;
   CustomMapView map;
   ObservableScrollView scrollViewParent;

    public SenderoDetailFragment() {
    }


   /**
    * Mandatory empty constructor for the fragment manager to instantiate the
    * fragment (e.g. upon screen orientation changes).
    */
   public void setArguments(ObservableScrollView parentScroll) {
       scrollViewParent = parentScroll;
   }

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      if (getArguments().containsKey(ARG_ITEM_ID)) {
         // Load the dummy content specified by the fragment
         // arguments. In a real-world scenario, use a Loader
         // to load content from a content provider.
         mSendero = Sendero.load(Sendero.class, getArguments().getLong(ARG_ITEM_ID));

      }
   }

   @Override
   public void onResume() {
      super.onResume();

      //Use this code to start the data binding process.
      //  In this case execute the binding between your Model Class and your app UI.
      myModelBinder.bind();
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      rootView = inflater.inflate(R.layout.fragment_sendero_detail, container, false);

       initializeViews(rootView);

       myModelBinder = new Binder.Builder()
            .setSource(mSendero)
            .setDestination(rootView)
            .build();

      return rootView;
   }

    protected void initializeViews(View rootView){
        map = (CustomMapView) rootView.findViewById(R.id.map);
        View clickMap = rootView.findViewById(R.id.clickMap);
        // TODO: Coger las Coordenadas del Sendero
        List<Geo> coordinatesSendero = new ArrayList<>();
        coordinatesSendero.add(new Geo(28.712428, -17.859723, null ,null));
        coordinatesSendero.add(new Geo(28.716313, -17.906388, null, null));
        coordinatesSendero.add(new Geo(28.740699, -17.941578, null, null));
        coordinatesSendero.add(new Geo(28.763726, -17.953509, null, null));
        coordinatesSendero.add(new Geo(28.780879, -17.963293, null, null));
        coordinatesSendero.add(new Geo(28.801113, -17.960375, null, null));
        coordinatesSendero.add(new Geo(28.822246, -17.954195, null, null));
        coordinatesSendero.add(new Geo(28.833224, -17.938746, null, null));
        coordinatesSendero.add(new Geo(28.854651, -17.913597, null, null));

        final List<Geo> coordinates = coordinatesSendero;

        clickMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopUpMap customPopUpMap = CustomPopUpMap.newInstance();
                customPopUpMap.setParams(getActivity(), coordinates.get(0).getLatitud(), coordinates.get(0).getLongitud(), coordinates);
                customPopUpMap.show(getActivity().getFragmentManager(), null);
            }
        });

        initMap(coordinates.get(0).getLatitud(), coordinates.get(0).getLongitud(), coordinates.get(coordinates.size()-1).getLatitud(), coordinates.get(coordinates.size()-1).getLongitud());
    }

    protected void initMap(double latStart, double lonStart, double latEnd, double lonEnd){
        map.setArguments(scrollViewParent);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        GeoPoint startPoint = new GeoPoint(latStart, lonStart);
        GeoPoint endPoint = new GeoPoint(latEnd, lonEnd);
        Drawable myCurrentLocationMarker = this.getResources().getDrawable(R.drawable.marker);
        OverlayItem myLocationOverlayItem = new OverlayItem("La Palma", "Islas Canarias", startPoint);
        myLocationOverlayItem.setMarker(myCurrentLocationMarker);
        final ArrayList<OverlayItem> items = new ArrayList<>();
        items.add(myLocationOverlayItem);
        myLocationOverlayItem = new OverlayItem("La Palma", "Islas Canarias", endPoint);
        myLocationOverlayItem.setMarker(myCurrentLocationMarker);
        items.add(myLocationOverlayItem);
        DefaultResourceProxyImpl resourceProxy = new DefaultResourceProxyImpl(getActivity());
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
        map.invalidate();
        IMapController mapController = map.getController();
        mapController.setZoom(11);
        mapController.setCenter(startPoint);
    }
}
