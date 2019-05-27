package com.sinohb.coreservice.interfaces;

interface ITranslateDataReceiver {
    void onReceiver(byte from,byte to,byte domain,byte dataType,inout byte[]data);
}