package com.jelcaf.pacomf.patealapalma.fragment;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.SenderosConstants;
import com.jelcaf.pacomf.patealapalma.activity.SenderoDetailWithImageActivity;
import com.jelcaf.pacomf.patealapalma.adapter.CommentAdapter;
import com.jelcaf.pacomf.patealapalma.binding.dao.Comment;
import com.jelcaf.pacomf.patealapalma.binding.dao.Geo;
import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;
import com.jelcaf.pacomf.patealapalma.login.LoginMethods;
import com.jelcaf.pacomf.patealapalma.network.Request;
import com.jelcaf.pacomf.patealapalma.views.CustomDialogRating;
import com.jelcaf.pacomf.patealapalma.views.CustomMapView;
import com.jelcaf.pacomf.patealapalma.views.CustomPopUpComments;
import com.jelcaf.pacomf.patealapalma.views.CustomPopUpMap;
import com.jelcaf.pacomf.patealapalma.views.SimpleDataView;
import com.jelcaf.pacomf.patealapalma.views.Utilities;
import com.mobandme.android.bind.Binder;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

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

   SimpleDataView mTime;
   SimpleDataView mWater;
   ListView listView;
   TextView noComments;
   Button moreCommentsBTN;
   CommentAdapter commentAdapter;
   SenderoDetailFragment fragment;

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

      this.fragment = this;

      if (getArguments().containsKey(ARG_ITEM_ID)) {
         // Load the dummy content specified by the fragment
         // arguments. In a real-world scenario, use a Loader
         // to load content from a content provider.
          mSendero = Sendero.getByIdServer(getArguments().getString(SenderoDetailFragment.ARG_ITEM_ID));
          Request.senderoGET(getActivity(), this, mSendero.getServerId(), LoginMethods.getIdFacebook(getActivity()));
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

        ImageView l1 = (ImageView) rootView.findViewById(R.id.leaf1);
        ImageView l2 = (ImageView) rootView.findViewById(R.id.leaf2);
        ImageView l3 = (ImageView) rootView.findViewById(R.id.leaf3);
        ImageView l4 = (ImageView) rootView.findViewById(R.id.leaf4);
        ImageView l5 = (ImageView) rootView.findViewById(R.id.leaf5);
        Utilities.setRating(getActivity(), mSendero.getRating().intValue(), l1, l2, l3, l4, l5);

        LinearLayout ratingLL = (LinearLayout) rootView.findViewById(R.id.ratingLL);

        ratingLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogRating.showDialog(getActivity(), fragment, mSendero.getServerId(), mSendero.getUserRating());
            }
        });

        map = (CustomMapView) rootView.findViewById(R.id.map);
        View clickMap = rootView.findViewById(R.id.clickMap);
        final List<Geo> coordinatesSendero = mSendero.coordinates();
        final List<Geo> coordinatesWaterPoints = mSendero.waterPoints();

        clickMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopUpMap customPopUpMap = CustomPopUpMap.newInstance();
                customPopUpMap.setParams(getActivity(), coordinatesSendero.get(0).getLatitud(), coordinatesSendero.get(0).getLongitud(), coordinatesSendero, coordinatesWaterPoints);
                customPopUpMap.show(getActivity().getFragmentManager(), null);
            }
        });

        initMap(coordinatesSendero.get(0).getLatitud(), coordinatesSendero.get(0).getLongitud(), coordinatesSendero.get(coordinatesSendero.size() - 1).getLatitud(), coordinatesSendero.get(coordinatesSendero.size() - 1).getLongitud());

        ButtonRectangle comoLlegarBTN = (ButtonRectangle) rootView.findViewById(R.id.btn_como_llegar);
        comoLlegarBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Location senderoLocation = new Location("sendero");
                    senderoLocation.setLatitude(mSendero.geoStart().getLatitud());
                    senderoLocation.setLongitude(mSendero.geoStart().getLongitud());
                    com.jelcaf.pacomf.patealapalma.network.Utilities.howToGoToSendero(getActivity(), ((SenderoDetailWithImageActivity) getActivity()).getCurrentLocation(), senderoLocation);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        moreCommentsBTN = (Button) rootView.findViewById(R.id.showMoreComments);
        moreCommentsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopUpComments.newInstance(fragment, mSendero.getServerId()).show(getActivity().getFragmentManager(), null);
            }
        });

        listView = (ListView)rootView.findViewById(R.id.listViewComments);
        noComments = (TextView) rootView.findViewById(R.id.no_comments);
        setList();

        mWater = (SimpleDataView) rootView.findViewById(R.id.sendero_water_view);
        mWater.setValue(SenderosConstants.WaterFormat.format(SenderosConstants.WATER_BY_KM *
                mSendero
                        .getLength()) + " litros");

        mTime = (SimpleDataView) rootView.findViewById(R.id.sendero_time);
        mTime.setValue(SenderosConstants.timeConversion(mSendero.getLength() *
              SenderosConstants.getSecondsByDifficulty(mSendero.getDifficulty(),
                    getActivity().getApplicationContext())));
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

    public void setList(){

        if ((listView == null) || (noComments == null) || (moreCommentsBTN==null))
            return;

        final List<Comment> commentsList = mSendero.comments();

        if (commentsList.isEmpty()){
            listView.setVisibility(View.GONE);
            moreCommentsBTN.setVisibility(View.GONE);
            noComments.setVisibility(View.VISIBLE);
            noComments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomPopUpComments.newInstance(fragment, mSendero.getServerId()).show(getActivity().getFragmentManager(), null);
                }
            });
        } else {
            commentAdapter = new CommentAdapter(getActivity(), commentsList, true);
            listView.setAdapter(commentAdapter);
            //setListViewHeightBasedOnChildren(listView);
        }
    }

    public void uploadFragment (){
        final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.detach(fragment);
        ft.attach(fragment);
        ft.commit();
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
