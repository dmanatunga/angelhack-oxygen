package com.angelhack.oxygen;


import java.util.HashMap;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.angelhack.oxygen.service.ChordApiDemoService;
import com.angelhack.oxygen.service.ChordApiDemoService.ChordServiceBinder;
import com.angelhack.oxygen.service.ChordApiDemoService.IChordServiceListener;
import com.angelhack.oxygen.utilities.ITitleable;
import com.angelhack.oxygen.utilities.MenuFragment;
import com.angelhack.oxygen.utilities.Transitions;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public class CoreActivity extends SlidingFragmentActivity implements IChordServiceListener {
	private final String TAG = "[Oxygen]";
	private final String TAGClass = "CoreActivity: ";

	private Fragment menu;

	private HashMap<String, String> mPlayerIdMap;
	
	public String mPlayerName;
	public boolean mIsHost;
	public String mHostNode;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		Log.v(TAG, TAGClass + "onCreate");

		setContentView(R.layout.content_frame);
		setBehindContentView(R.layout.menu_frame);

		startService();
        bindChordService();
        
		menu = new MenuFragment();
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame, menu)
		.commit();

		if(savedInstanceState == null) {
			Transitions.transitionToHome(this);
			setSlidingActionBarEnabled(true);
		}
		else{

			enableSlidingMenu();

		}

		mPlayerIdMap = new HashMap<String, String>(); 
		getSlidingMenu().setBehindOffsetRes(R.dimen.show_content);

	}

	public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {

		if (item.getItemId() == android.R.id.home) {
			toggle(); 
			if(getSlidingMenu().isMenuShowing() && menu.isAdded()){
				setTitle(((ITitleable)menu).getTitle());
			}
			return true;
		}
		return false;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}


	public void showContent(){
		getSlidingMenu().showContent();
	}

	public void enableSlidingMenu(){

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
	}

    
    
	// **********************************************************************
	// Using Service
	// **********************************************************************
	private ChordApiDemoService mChordService = null;

	private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            Log.d(TAG, TAGClass + "onServiceConnected()");
            ChordServiceBinder binder = (ChordServiceBinder)service;
            mChordService = binder.getService();
            try {
                mChordService.initialize(CoreActivity.this);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //refreshInterfaceType();
            Transitions.gameFragment.setService(mChordService);
            //mChannelTestFragment.setService(mChordService);
            //mDataTestFragment.setService(mChordService);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            Log.i(TAG, TAGClass + "onServiceDisconnected()");
            mChordService = null;
        }
    };
 
    public void bindChordService() {
        Log.i(TAG, TAGClass + "bindChordService()");
        if (mChordService == null) {
            Intent intent = new Intent(
                    "com.angelhack.oxygen.service.ChordApiDemoService.SERVICE_BIND");
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        }
    }
    
    private void unbindChordService() {
        Log.i(TAG, TAGClass + "unbindChordService()");

        if (null != mChordService) {
            unbindService(mConnection);
        }
        mChordService = null;
    }

    private void startService() {
        Log.i(TAG, TAGClass + "startService()");
        Intent intent = new Intent("com.angelhack.oxygen.ChordApiDemoService.SERVICE_START");
        startService(intent);
    }

    private void stopService() {
        Log.i(TAG, TAGClass + "stopService()");
        Intent intent = new Intent("com.angelhack.oxygen.ChordApiDemoService.SERVICE_STOP");
        stopService(intent);
    }
    
	@Override
	public void onReceiveMessage(String node, String channel, String type, String message) {
		// TODO Auto-generated method stub;
		if (type.equalsIgnoreCase("name")) {
			// String name = node + ":" + message;
			String name = message;
			mPlayerIdMap.put(node, name);
            Transitions.gameFragment.onPublicChannelNodeJoined(name);
		} else if (type.equalsIgnoreCase("host-name")) {
			//String name = node + ":" + message;
			String name = message;
			mPlayerIdMap.put(node, name);
            Transitions.gameFragment.onPublicChannelNodeJoined(name);
            mHostNode = node;
		} else {
			Log.e(TAG, TAGClass + "Received unknown message type formatted message" + type);
		}
	}

	@Override
	public void onFileWillReceive(String node, String channel, String fileName,
			String exchangeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFileProgress(boolean bSend, String node, String channel,
			int progress, String exchangeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFileCompleted(int reason, String node, String channel,
			String exchangeId, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNodeEvent(String node, String channel, boolean bJoined) {
		if (bJoined) {
            if (channel.equals(mChordService.getPublicChannel())) {
                //Transitions.gameFragment.onPublicChannelNodeJoined(node);
                mChordService.sendData(channel, "name", mPlayerName.getBytes(), node);
            } 
            return;
        }

        if (channel.equals(mChordService.getPublicChannel())) {
            Transitions.gameFragment.onPublicChannelNodeLeaved(mPlayerIdMap.get(node));
            //mDataTestFragment.onNodeLeaved(node, channel);
        } 
	}

	@Override
	public void onNetworkDisconnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdateNodeInfo(String nodeName, String ipAddress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectivityChanged() {
		// TODO Auto-generated method stub
		
	}
}


