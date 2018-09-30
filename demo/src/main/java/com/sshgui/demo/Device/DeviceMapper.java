package com.sshgui.demo.Device;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by xp110 on 2018/9/11.
 */
@Mapper
public interface DeviceMapper {

    @Select("select * from device where id=#{id}")
    DeviceDO getDeviceById(@Param("id") String id);

    @Select("select * from device")
    List<DeviceDO> getAllDevice();

    //模糊查询  搜索
    @Select("select * from device where device_name like '%${str}%' or device_describe like'%${str}%'")
    List<DeviceDO> selectDevice(@Param("str") String str);
}
