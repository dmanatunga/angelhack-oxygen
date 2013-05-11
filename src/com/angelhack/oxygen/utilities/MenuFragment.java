
package com.angelhack.oxygen.utilities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;
import com.angelhack.oxygen.R;


public class MenuFragment extends SherlockFragment implements ITitleable {
    
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
    }
 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.menu, container,false);
               
        return v;
    }
    
    @Override
    public void onResume(){
        super.onResume();
        getActivity().setTitle(getTitle());
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        
    }
    
    @Override
    public int getTitle() {
        return R.string.app_name;
    }
    
    

   
}
