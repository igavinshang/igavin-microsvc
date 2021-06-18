package org.igavin.microsvc.dao.mapper;

import org.igavin.microsvc.dao.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserDO getByName(String name);

    UserDO getById(Long id);

}
