package com.sshgui.demo.Device;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by xp110 on 2018/9/11.
 */
@Mapper
public interface SshMapper {

    @Select("select command from command_manage where id=#{id}")
    String getCmd(@Param("id") String id);

    @Select("select * from command_manage")
    List<CommandDO> getAllCommand();

    @Select("select * from command_manage where id=#{id}")
    CommandDO getCommandById(@Param("id") String id);

    @Select("select * from command_manage where command like '%${str}%' or type like'%${str}%'")
    List<CommandDO> selectCommand(@Param("str") String str);

    @Insert("insert into command_manage (command,number,name,type) values(#{command},#{number},#{name},#{type}) ")
    void insertCommand(@Param("command") String command,@Param("number") String number,@Param("name") String name,@Param("type") String type);

    @Update("update command_manage set command=#{command},number=#{number},name=#{name},type=#{type} where id=#{id}")
    void updateCommand(@Param("command") String command,@Param("number") String number,@Param("name") String name,@Param("type") String type,@Param("id") String id);

}
