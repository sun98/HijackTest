package cn.nibius.hijacktest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class HijackReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            Log.i("nib", "receiver onReceive: ");
            Intent serviceIntent = new Intent(context, HijackService.class);
            context.startService(serviceIntent);
        }
    }
}
