package com.hisilicon.android.hidisplaymanager;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * HiDisPlayerManager 操作类。
 * Created by lWX454814 on 2017/12/18.
 */

public class HiDisplayManager {

    private static String TAG = "HiVideoPlayer_HiDisplayerManager";

    private static String HIDISPLAYERMANAGER_NAME = "com.hisilicon.android.hidisplaymanager.HiDisplayManager";

    private Class hiDisPlayManagerCls = null;
    private Object hiDisPlayManagerObj = null;

    public HiDisplayManager() {
        try {
            this.hiDisPlayManagerCls = Class.forName(HiDisplayManager.HIDISPLAYERMANAGER_NAME);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "get HiDisPlayManager class error !");
        }
        if (null == this.hiDisPlayManagerCls) {
            return;
        }
        try {
            this.hiDisPlayManagerObj = this.hiDisPlayManagerCls.newInstance();
        } catch (InstantiationException e) {
            Log.e(TAG, "get HiDisPlayManager object error !");
        } catch (IllegalAccessException e) {
            Log.e(TAG, "get HiDisPlayManager object error !");
        }
    }


    private boolean hasHiDisplayManager() {
//        return this.mHiDisplayManager != null;
        return this.hiDisPlayManagerCls != null && (this.hiDisPlayManagerObj != null);
    }


    private Method getMethod(String name, Class<?>... parameterTypes) {
        if (!this.hasHiDisplayManager()) {
            return null;
        }
        Method method = null;
        try {
            method = this.hiDisPlayManagerCls.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "get Method error !");
        }
        return method;
    }

    private Object invoke(Method method, Object o, Object... args) {
        if (method == null || o == null) {
            return null;
        }
        Object result = null;
        try {
            result = method.invoke(o, args);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "invoke Method error !");
        } catch (InvocationTargetException e) {
            Log.e(TAG, "invoke Method error !");
        }
        return result;
    }

    public Object getDisplayCapability() {
//        return this.mHiDisplayManager.getDisplayCapability();
        Method method = this.getMethod("getDisplayCapability");
        return this.invoke(method, this.hiDisPlayManagerObj);
    }

    public void reset3DMode() {
//        this.mHiDisplayManager.reset3DMode();
        Method method = this.getMethod("reset3DMode");
        invoke(method, this.hiDisPlayManagerObj);
    }

    public int setRightEyeFirst(int priority) {
//        return this.mHiDisplayManager.setRightEyeFirst(priority);
        Method method = this.getMethod("setRightEyeFirst", int.class);
        Object result = this.invoke(method, this.hiDisPlayManagerObj, priority);
        return getIntResult(result);
    }

    public int getRightEyeFirst() {
//        return mHiDisplayManager.getRightEyeFirst();
        Method method = this.getMethod("getRightEyeFirst");
        Object result = this.invoke(method, this.hiDisPlayManagerObj);
        return getIntResult(result);
    }

    public int set3DMode(int mode, int videoFps) {
//        return this.mHiDisplayManager.set3DMode(mode, videoFps);
        Method method = this.getMethod("set3DMode", int.class, int.class);
        Object result = this.invoke(method, this.hiDisPlayManagerObj, mode, videoFps);
        return getIntResult(result);
    }

    public int get3DMode() {
//        return this.mHiDisplayManager.get3DMode();
        Method method = this.getMethod("get3DMode");
        Object result = this.invoke(method, this.hiDisPlayManagerObj);
        return getIntResult(result);
    }

    public int setFmt(int fmt) {
//        return mHiDisplayManager.setFmt(fmt);
        Method method = this.getMethod("setFmt", int.class);
        Object result = this.invoke(method, this.hiDisPlayManagerObj, fmt);
        return this.getIntResult(result);
    }

    public int getFmt() {
//        return this.mHiDisplayManager.getFmt();
        Method method = this.getMethod("getFmt");
        Object result = this.invoke(method, this.hiDisPlayManagerObj);
        return getIntResult(result);
    }

    public int getstereoDepth() {
//        return this.mHiDisplayManager.getstereoDepth();
        Method method = this.getMethod("getstereoDepth");
        Object result = this.invoke(method, this.hiDisPlayManagerObj);
        int res = getIntResult(result);
        return res <= 0 ? 0 : res;
    }

    public int setStereoDepth(int depth) {
//        return mHiDisplayManager.setStereoDepth(depth);
        Method method = this.getMethod("setStereoDepth", int.class);
        Object result = this.invoke(method, this.hiDisPlayManagerObj, depth);
        return getIntResult(result);
    }

    private int getIntResult(Object result) {
        if (result == null) {
            return -1;
        }
        if (result instanceof Integer) {
            Integer integer = (Integer) result;
            return integer.intValue();
        }

        return -1;
    }

}
