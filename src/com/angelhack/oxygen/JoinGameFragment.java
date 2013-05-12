
package com.angelhack.oxygen;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;
import com.angelhack.oxygen.utilities.ITitleable;

public class JoinGameFragment extends SherlockFragment implements ITitleable{
    
	
	private Activity activity;
	private Handler mHandler;
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.game, container, false);
        return v;
    }
    
    
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
    	activity = getActivity();
    	mHandler = new Handler();
    	
    	
    	//view.findViewById(R.id.snapshot_btn).setOnClickListener(Listener);
    	//view.findViewById(R.id.autoupdate_btn).setOnClickListener(Listener);
    	
    	
    }
    
    
    
    private OnClickListener Listener = new OnClickListener() { 
		
	    public void onClick(View v) {
	    	
	    	if (v instanceof Button){
	    		switch (v.getId()){
	    		
	    		}
	    	}
	    }
    };
   

    @Override
    public void onResume(){
        super.onResume();
        getActivity().setTitle(getTitle());
    }
    
    @Override
    public int getTitle(){
    	return R.string.game;
    }

    
    
}
