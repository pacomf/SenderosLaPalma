package com.jelcaf.pacomf.patealapalma.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.fragment.SenderoDetailFragment;
import com.jelcaf.pacomf.patealapalma.fragment.SenderoListFragment;


/**
 * An activity representing a list of Senderos. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link SenderoDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link com.jelcaf.pacomf.patealapalma.fragment.SenderoListFragment} and the item details
 * (if present) is a {@link com.jelcaf.pacomf.patealapalma.fragment.SenderoDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link com.jelcaf.pacomf.patealapalma.fragment.SenderoListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class SenderoListActivity extends ActionBarActivity
      implements SenderoListFragment.Callbacks {

   /**
    * Whether or not the activity is in two-pane mode, i.e. running on a tablet
    * device.
    */
   private boolean mTwoPane;

   private SenderoListFragment senderoListFragment;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_sendero_list);

      // Toolbar Support
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      if (findViewById(R.id.sendero_detail_container) != null) {
         // The detail container view will be present only in the
         // large-screen layouts (res/values-large and
         // res/values-sw600dp). If this view is present, then the
         // activity should be in two-pane mode.
         mTwoPane = true;

         // In two-pane mode, list items should be given the
         // 'activated' state when touched.
         ((SenderoListFragment) getSupportFragmentManager()
               .findFragmentById(R.id.sendero_list))
               .setActivateOnItemClick(true);
      }

      // TODO: If exposing deep links into your app, handle intents here.
   }



   /**
    * Callback method from {@link SenderoListFragment.Callbacks}
    * indicating that the item with the given ID was selected.
    */
   @Override
   public void onItemSelected(Long id) {
      if (mTwoPane) {
         // In two-pane mode, show the detail view in this activity by
         // adding or replacing the detail fragment using a
         // fragment transaction.
         Bundle arguments = new Bundle();
         arguments.putLong(SenderoDetailFragment.ARG_ITEM_ID, id);
         SenderoDetailFragment fragment = new SenderoDetailFragment();
         fragment.setArguments(arguments);
         getSupportFragmentManager().beginTransaction()
               .replace(R.id.sendero_detail_container, fragment)
               .commit();

      } else {
         // In single-pane mode, simply start the detail activity
         // for the selected item ID.
         Intent detailIntent = new Intent(this, SenderoDetailActivity.class);
         detailIntent.putExtra(SenderoDetailFragment.ARG_ITEM_ID, id);
         startActivity(detailIntent);
      }
   }
}
