package com.jelcaf.pacomf.patealapalma.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;
import com.mobandme.android.bind.Binder;

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

   /**
    * Mandatory empty constructor for the fragment manager to instantiate the
    * fragment (e.g. upon screen orientation changes).
    */
   public SenderoDetailFragment() {
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

      myModelBinder = new Binder.Builder()
            .setSource(mSendero)
            .setDestination(rootView)
            .build();

      return rootView;
   }
}
