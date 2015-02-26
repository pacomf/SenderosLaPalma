package com.jelcaf.pacomf.patealapalma;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.jelcaf.pacomf.patealapalma.dummy.DummyContent;

/**
 * A fragment representing a single Sendero detail screen.
 * This fragment is either contained in a {@link SenderoListActivity}
 * in two-pane mode (on tablets) or a {@link SenderoDetailActivity}
 * on handsets.
 */
public class SenderoDetailFragment extends Fragment {
   /**
    * The fragment argument representing the item ID that this fragment
    * represents.
    */
   public static final String ARG_ITEM_ID = "item_id";

   /**
    * The dummy content this fragment is presenting.
    */
   private DummyContent.DummyItem mItem;

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
         mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
      }
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_sendero_detail, container, false);

      // Show the dummy content as text in a TextView.
      if (mItem != null) {
         ((TextView) rootView.findViewById(R.id.sendero_detail)).setText(mItem.content);
      }

      return rootView;
   }
}
