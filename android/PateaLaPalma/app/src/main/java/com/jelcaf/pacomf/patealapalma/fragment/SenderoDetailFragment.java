package com.jelcaf.pacomf.patealapalma.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.adapter.CommentAdapter;
import com.jelcaf.pacomf.patealapalma.binding.dao.Comment;
import com.jelcaf.pacomf.patealapalma.binding.dao.Geo;
import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;
import com.jelcaf.pacomf.patealapalma.login.LoginMethods;
import com.jelcaf.pacomf.patealapalma.views.CustomMapView;
import com.jelcaf.pacomf.patealapalma.views.CustomPopUpComments;
import com.jelcaf.pacomf.patealapalma.views.CustomPopUpMap;
import com.mobandme.android.bind.Binder;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.Date;
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
        final List<Geo> coordinatesSendero = new ArrayList<>();
        coordinatesSendero.add(new Geo(28.712428, -17.859723, null ,null));
        coordinatesSendero.add(new Geo(28.716313, -17.906388, null, null));
        coordinatesSendero.add(new Geo(28.740699, -17.941578, null, null));
        coordinatesSendero.add(new Geo(28.763726, -17.953509, null, null));
        coordinatesSendero.add(new Geo(28.780879, -17.963293, null, null));
        coordinatesSendero.add(new Geo(28.801113, -17.960375, null, null));
        coordinatesSendero.add(new Geo(28.822246, -17.954195, null, null));
        coordinatesSendero.add(new Geo(28.833224, -17.938746, null, null));
        coordinatesSendero.add(new Geo(28.854651, -17.913597, null, null));
        // TODO: Coger las coordenadas de los WaterPoints
        final List<Geo> coordinatesWaterPoints = new ArrayList<>();
        coordinatesWaterPoints.add(new Geo(28.780879, -17.963293, null, null));
        coordinatesWaterPoints.add(new Geo(28.801113, -17.960375, null, null));
        coordinatesWaterPoints.add(new Geo(28.822246, -17.954195, null, null));
        coordinatesWaterPoints.add(new Geo(28.833224, -17.938746, null, null));

        clickMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopUpMap customPopUpMap = CustomPopUpMap.newInstance();
                customPopUpMap.setParams(getActivity(), coordinatesSendero.get(0).getLatitud(), coordinatesSendero.get(0).getLongitud(), coordinatesSendero, coordinatesWaterPoints);
                customPopUpMap.show(getActivity().getFragmentManager(), null);
            }
        });

        initMap(coordinatesSendero.get(0).getLatitud(), coordinatesSendero.get(0).getLongitud(), coordinatesSendero.get(coordinatesSendero.size() - 1).getLatitud(), coordinatesSendero.get(coordinatesSendero.size() - 1).getLongitud());

        Button moreCommentsBTN = (Button) rootView.findViewById(R.id.showMoreComments);
        moreCommentsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopUpComments.newInstance().show(getActivity().getFragmentManager(), null);
            }
        });

        ListView listView = (ListView)rootView.findViewById(R.id.listViewComments);
        TextView noComments = (TextView) rootView.findViewById(R.id.no_comments);
        setList(listView, noComments, moreCommentsBTN);

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

    void setList(ListView listView, TextView noComments, Button moreComments){

        // TODO: Recuperar Listas de Comentarios del Sendero en cuestion
        ArrayList<Comment> commentsList = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            commentsList.add(new Comment(null, LoginMethods.getIdFacebook(getActivity()), "Paco", "Un comentario muy bonito y de lo boniuto que es es superior y asi para qued uro mas tiempo", new Date(), 0, null));
        }

        if (commentsList.isEmpty()){
            listView.setVisibility(View.GONE);
            moreComments.setVisibility(View.GONE);
            noComments.setVisibility(View.VISIBLE);
            noComments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomPopUpComments.newInstance().show(getActivity().getFragmentManager(), null);
                }
            });
        } else {
            CommentAdapter commentAdapter = new CommentAdapter(getActivity(), commentsList, true);
            listView.setAdapter(commentAdapter);
            //setListViewHeightBasedOnChildren(listView);
        }
    }

    // Para adaptar el tamano de la lista a 3 comentarios (o los que devuelva el getCount()
    /*public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }*/
}
