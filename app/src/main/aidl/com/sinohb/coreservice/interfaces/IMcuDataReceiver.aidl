package com.sinohb.coreservice.interfaces;

interface IMcuDataReceiver {
    void onReceiver(byte groupId,byte commandId,byte length,byte position,inout byte [] datas);
}
