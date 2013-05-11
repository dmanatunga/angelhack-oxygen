
package com.angelhack.oxygen.utilities;


import android.support.v4.app.Fragment;

import com.angelhack.oxygen.CoreActivity;
import com.angelhack.oxygen.GameFragment;
import com.angelhack.oxygen.R;


/**
 * Singleton class to manage transitions of the active content
 * displayed on the app. 
 */
public class Transitions {
      private final static int GAME = R.string.game;

	  private final static GameFragment gameFragment = new GameFragment();

      private final static int HOME = GAME;
  
	  public static void transitionToHome(CoreActivity  activity){
          switchFragment(HOME, activity);
      }

    
    private static void switchFragment(int fragmentName, CoreActivity activity){
        Fragment fragment = getFragment(fragmentName);
        
        activity.getSupportFragmentManager().beginTransaction()
            .replace(R.id.content, fragment)
            .addToBackStack(null).commit();
        activity.showContent();
        String title = activity.getResources().getString(((ITitleable)fragment).getTitle());
        activity.setTitle(title);
    }
    
    private static Fragment getFragment(int fragmentInx) {
        Fragment newFragment = null;
        
        switch (fragmentInx) {
        case GAME:
            newFragment = gameFragment;
            break;
        }
        return newFragment;
    }
}
