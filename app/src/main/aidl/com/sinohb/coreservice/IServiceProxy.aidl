package com.sinohb.coreservice;

interface IServiceProxy {

    /**
    * 设置远程应用Binder
    */
    void setBinder(String pkg,IBinder cb);

    /**
    * 发送给MCU数据
    */
    void sendToMcuDatas();

    /**
    * 获取数据
    */
    byte [] getDate(byte []data);

    /**
    * 请求数据
    */
    void requestData(byte group,byte command,byte[] data);


    void setDataReceiver();

}
