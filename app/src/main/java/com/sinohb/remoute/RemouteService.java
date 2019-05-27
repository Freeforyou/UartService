package com.sinohb.remoute;

import android.os.IBinder;
import android.os.RemoteException;

import com.sinohb.coreservice.IServiceProxy;
import com.sinohb.coreservice.interfaces.IMcuDataReceiver;
import com.sinohb.coreservice.interfaces.ITranslateDataReceiver;

import java.util.HashMap;
import java.util.Map;


public class RemouteService extends IServiceProxy.Stub {


    /**
     * @param pkg       远程APK包名
     * @param appId     远程APK ID
     * @param keepAlive 是否保持在线状态
     * @param cb        远程Binder检测是否掉线
     * @throws RemoteException
     */
    @Override
    public void setBinder(String pkg, byte appId, boolean keepAlive, IBinder cb) throws RemoteException {
        if (pkg != null && cb != null) {
            remouteDeathObserverMap.put(pkg, new RemouteDeathObserver(pkg, cb, appId, getCallingPid(), getCallingUid(), keepAlive));
        }
    }

    @Override
    public void sendToMcuDatas(String pkg, byte groupId, byte commandId, byte length, byte position, byte[] datas) throws RemoteException {

    }

    @Override
    public byte[] getCoreDate(String pkg, byte domain, byte type, byte[] data) throws RemoteException {
        return new byte[0];
    }

    @Override
    public void setCoreData(String pkg, byte groupId, byte commandId, byte[] data) throws RemoteException {

    }

    @Override
    public void setObserverId(String pkg, byte groupId, byte commandId) throws RemoteException {

    }

    @Override
    public void setMcuDataReceiver(String pkg, IMcuDataReceiver receiver) throws RemoteException {

    }

    @Override
    public void removeDataReceiver(String pkg, IMcuDataReceiver receiver) throws RemoteException {

    }

    @Override
    public void translateData(String pkg, byte from, byte to, byte domain, byte dataType, byte[] data) throws RemoteException {

    }

    @Override
    public void setTranslateDataReceiver(String pkg, ITranslateDataReceiver receiver) throws RemoteException {

    }

    @Override
    public void removeTranslateDataReceiver(String pkg, ITranslateDataReceiver receiver) throws RemoteException {

    }


    Map<String, RemouteDeathObserver> remouteDeathObserverMap = new HashMap<String, RemouteDeathObserver>();

    public class RemouteDeathObserver implements IBinder.DeathRecipient {

        private String callPkg;
        private IBinder callback;
        private int pid;
        private int uid;
        private boolean keepAlive;
        private byte appId;

        public RemouteDeathObserver(String pkg, IBinder cb, byte appId, int pid, int uid, boolean keepAlive) {
            this.callPkg = pkg;
            this.callback = cb;
            this.pid = pid;
            this.uid = uid;
            this.keepAlive = keepAlive;
            this.appId = appId;
            if (callback != null) {
                try {
                    callback.linkToDeath(this, 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        public String getCallPkg() {
            return callPkg;
        }

        public int getPid() {
            return pid;
        }

        public int getUid() {
            return uid;
        }

        public byte getAppId() {
            return appId;
        }

        public boolean getKeepAlive(){
            return keepAlive;
        }

        @Override
        public void binderDied() {
            synchronized (RemouteDeathObserver.class) {
                if (callback != null) {
                    callback.unlinkToDeath(this, 0);
                }
                remouteDeathObserverMap.remove(callPkg);
            }
        }
    }
}
