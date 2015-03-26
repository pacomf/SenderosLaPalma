package com.jelcaf.pacomf.patealapalma.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.SenderosConstants;
import com.jelcaf.pacomf.patealapalma.adapter.SenderoAdapter;
import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;

import java.util.List;

/**
 * A list fragment representing a list of Senderos. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link com.jelcaf.pacomf.patealapalma.fragment.SenderoDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class SenderoListFragment extends ListFragment {

   /**
    * The serialization (saved instance state) Bundle key representing the
    * activated item position. Only used on tablets.
    */
   private static final String STATE_ACTIVATED_POSITION = "activated_position";

   /**
    * The fragment's current callback object, which is notified of list item
    * clicks.
    */
   private Callbacks mCallbacks = sDummyCallbacks;

   /**
    * The current activated item position. Only used on tablets.
    */
   private int mActivatedPosition = ListView.INVALID_POSITION;


   /**
    * Senderos adapter
    */
   private SenderoAdapter adapter;

   private boolean recommended;

   /**
    * A callback interface that all activities containing this fragment must
    * implement. This mechanism allows activities to be notified of item
    * selections.
    */
   public interface Callbacks {
      /**
       * Callback for when an item has been selected.
       */
      public void onItemSelected(Long id);
   }

   /**
    * A dummy implementation of the {@link Callbacks} interface that does
    * nothing. Used only when this fragment is not attached to an activity.
    */
   private static Callbacks sDummyCallbacks = new Callbacks() {
      @Override
      public void onItemSelected(Long id) {
      }
   };

   /**
    * Mandatory empty constructor for the fragment manager to instantiate the
    * fragment (e.g. upon screen orientation changes).
    */
   public SenderoListFragment() {
   }

   public List<Sendero> getRecommendedSenderos() {
      createSenderosIfNotExists();

      if (this.recommended) {
         return new Select()
               .from(Sendero.class)
               .where("server_id < 5")
               .execute();
      }
      return new Select()
            .from(Sendero.class)
            .execute();
   }

   private void createSenderosIfNotExists() {
      List<Sendero> senderos = new Select()
            .from(Sendero.class)
            .execute();

      if (senderos.size() != 0) {
         return;
      }

      for (int i = 1; i < 10; i++) {
         Sendero sendero = new Sendero();
         sendero.setServerId(i);
         sendero.setName("Sendero " + i);
         sendero.save();
      }

   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.sendero_list, null);
      return v;
   }

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      ActiveAndroid.initialize(getActivity());

      Bundle args = getArguments();
      if (args != null && args.getBoolean(SenderosConstants.Arguments.RECOMMENDED, false)) {
         this.recommended = true;
      }

      adapter = new SenderoAdapter(getActivity(), getRecommendedSenderos());
      setListAdapter(adapter);
   }

   @Override
   public void onViewCreated(View view, Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);

      // Restore the previously serialized activated item position.
      if (savedInstanceState != null
            && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
         setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
      }
   }

   @Override
   public void onAttach(Activity activity) {
      super.onAttach(activity);

      // Activities containing this fragment must implement its callbacks.
      if (!(activity instanceof Callbacks)) {
         throw new IllegalStateException("Activity must implement fragment's callbacks.");
      }

      mCallbacks = (Callbacks) activity;
   }

   @Override
   public void onDetach() {
      super.onDetach();

      // Reset the active callbacks interface to the dummy implementation.
      mCallbacks = sDummyCallbacks;
   }

   @Override
   public void onListItemClick(ListView listView, View view, int position, long id) {
      super.onListItemClick(listView, view, position, id);

      mCallbacks.onItemSelected(((Sendero)getListAdapter().getItem(position)).getId());
   }

   @Override
   public void onSaveInstanceState(Bundle outState) {
      super.onSaveInstanceState(outState);
      if (mActivatedPosition != ListView.INVALID_POSITION) {
         // Serialize and persist the activated item position.
         outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
      }
   }

   /**
    * Turns on activate-on-click mode. When this mode is on, list items will be
    * given the 'activated' state when touched.
    */
   public void setActivateOnItemClick(boolean activateOnItemClick) {
      // When setting CHOICE_MODE_SINGLE, ListView will automatically
      // give items the 'activated' state when touched.
      getListView().setChoiceMode(activateOnItemClick
            ? ListView.CHOICE_MODE_SINGLE
            : ListView.CHOICE_MODE_NONE);
   }

   private void setActivatedPosition(int position) {
      if (position == ListView.INVALID_POSITION) {
         getListView().setItemChecked(mActivatedPosition, false);
      } else {
         getListView().setItemChecked(position, true);
      }

      mActivatedPosition = position;
   }
}
