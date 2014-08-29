package com.cust.android.screencap;

import java.io.File;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.Log;

public class ScreenCapReceiver extends BroadcastReceiver {

	private static final String LOG_TAG = "ScreenCapReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		// Temp code, should not in main thread
		Log.d(LOG_TAG, "generate file name");
                //Checking external storage
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			mExternalStorageWriteable = false;
		} else {
			mExternalStorageWriteable = false;
		}

		if (!mExternalStorageWriteable)
	            return;

		File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "SCRCAP_"+System.currentTimeMillis()+".png");
		Log.d(LOG_TAG, "Capture screen to : " + file.getAbsolutePath());
		
		ScreenCap.captureScreenToFile(file.getAbsolutePath());
		Log.d(LOG_TAG, "screen captured");
		
        //MTP re-scan the directory to show on windows explorer immediately
    	//MediaScannerConnection.scanFile(this.getApplicationContext(), new String[] { outputFile.getAbsolutePath() }, null, null);
	}

}
