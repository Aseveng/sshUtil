package com.sshgui.demo.Device;

/**
 * Created by xp110 on 2018/9/11.
 */
//设备类型
public  class DeviceDO {


    public long id;
    public String device_describe;
    public String device_name;
    public int port_number;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescribe() {
        return device_describe;
    }

    public void setDescribe(String device_describe) {
        this.device_describe = device_describe;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public int getPort_number() {
        return port_number;
    }

    public void setPort_number(int port_number) {
        this.port_number = port_number;
    }
}
