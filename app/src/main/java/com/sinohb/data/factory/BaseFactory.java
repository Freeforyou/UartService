package com.sinohb.data.factory;

public interface BaseFactory {
    void onReceiver(byte group, byte command, byte[] datas);
}
