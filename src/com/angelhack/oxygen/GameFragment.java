
package com.angelhack.oxygen;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.angelhack.oxygen.adapter.NodeListAdapter;
import com.angelhack.oxygen.service.ChordApiDemoService;
import com.angelhack.oxygen.utilities.ITitleable;
import com.samsung.chord.ChordManager;

public class GameFragment extends SherlockFragment implements ITitleable, OnClickListener {
	private final String TAG = "[Oxygen]";
	private final String TAGClass = "GameFragment: ";
			
	private Activity activity;
	
	private Button mHostButton;
	private Button mJoinButton;
	
	private EditText mPlayerNameEditText;
	
	private String mPlayerName;
	
	private ListView mPlayerListView;
	private NodeListAdapter mPlayerListAdapter = null;
	
    private boolean bStartedChord = false;
    ChordApiDemoService mChordService = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.game, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
    	activity = getActivity();
    	
    	mPlayerNameEditText = (EditText)view.findViewById(R.id.player_name_input);
    	
    	mHostButton = (Button)view.findViewById(R.id.host_button);
    	mHostButton.setOnClickListener(this);
    	
    	mJoinButton = (Button)view.findViewById(R.id.join_button);
    	mJoinButton.setOnClickListener(this);
    	
    	mPlayerListView = (ListView)view.findViewById(R.id.players_listView);
    	mPlayerListAdapter = new NodeListAdapter(activity.getApplicationContext());
    	mPlayerListView.setAdapter(mPlayerListAdapter);
    }
    
    @Override
    public void onResume(){
        super.onResume();
        getActivity().setTitle(getTitle());
    }
    
    @Override
    public int getTitle(){
    	return R.string.game;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.host_button:
				mPlayerName = mPlayerNameEditText.getText().toString();
				((CoreActivity)activity).mPlayerName = mPlayerName;
				((CoreActivity)activity).mIsHost = true;
				startChord();
				if (bStartedChord) {
					// Send message with name to all
					mChordService.sendDataToAll(mChordService.getPublicChannel(), "host-name", mPlayerName.getBytes());
					mHostButton.setEnabled(false);
				}
				break;
			case R.id.join_button:
				mPlayerName = mPlayerNameEditText.getText().toString();
				((CoreActivity)activity).mPlayerName = mPlayerName;
				((CoreActivity)activity).mIsHost = false;
				startChord();
				if (bStartedChord) {
					// Send message with name to all
					mChordService.sendDataToAll(mChordService.getPublicChannel(), "name", mPlayerName.getBytes());
					mJoinButton.setEnabled(false);
				}
				break;			
		}
	}

	public void startChord() {
        int nError = mChordService.start(ChordManager.INTERFACE_TYPE_WIFI);
        if (ChordManager.ERROR_NONE == nError) {
            
        	bStartedChord = true;
            
        } else if (ChordManager.ERROR_INVALID_INTERFACE == nError) {
            Toast.makeText(activity, "Invalid connection", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity, "Fail to start", Toast.LENGTH_SHORT).show();
        }
    }

    public void stopChord() {
        if (!bStartedChord)
            return;
        
        mChordService.stop();
        bStartedChord = false;
    }
    
    public void setService(ChordApiDemoService chordService) {
        mChordService = chordService;
    }
    
    public void onPublicChannelNodeJoined(String nodeName) {
        Log.d(TAG, TAGClass + "[Public]Node join : " + nodeName);
        mPlayerListAdapter.addNode(nodeName);
    }

    public void onPublicChannelNodeLeaved(String nodeName) {
        Log.d(TAG, TAGClass + "[Public]Node leave : " + nodeName);
        mPlayerListAdapter.removeNode(nodeName);
    }

    
}
