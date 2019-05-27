package com.sinohb.coreservice;

import com.sinohb.coreservice.interfaces.IMcuDataReceiver;
import com.sinohb.coreservice.interfaces.ITranslateDataReceiver;

interface IServiceProxy {
    /**
    * 设置远程应用Binder
    */
    void setBinder(String pkg,byte pkgId,boolean keepAlive,IBinder cb);

    /**
    * 发送给MCU数据
    */
    void sendToMcuDatas(String pkg,byte groupId,byte commandId,byte length,byte position,inout byte [] datas);

    /**
    * 获取CoreService数据
    */
    byte [] getCoreDate(String pkg,byte domain,byte type,inout byte []data);

    /**
    * 向CoreService设置数据（音量、亮度、音场）
    */
    void setCoreData(String pkg,byte groupId,byte commandId,inout byte[] data);

    /**
    * 设置监听数据的ID
    */
    void setObserverId(String pkg,byte groupId,byte commandId);

    /**
    * 设置MCU数据监听
    */
    void setMcuDataReceiver(String pkg,in IMcuDataReceiver receiver);

    /**
    * 注销监听
    */
    void removeDataReceiver(String pkg,in IMcuDataReceiver receiver);

    /**
    * 转发其他应用数据
    * from:发送的应用ID
    * to:接收的应用ID
    * domain:发送数据的内容例如：ID3消息等
    * dataType：数据格式
    */
    void translateData(String pkg,byte from,byte to,byte domain,byte dataType,inout byte[]data);

    void setTranslateDataReceiver(String pkg,in ITranslateDataReceiver receiver);

    void removeTranslateDataReceiver(String pkg,in ITranslateDataReceiver receiver);
}
