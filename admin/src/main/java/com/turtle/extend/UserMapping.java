package com.turtle.extend;

import com.turtle.dto.UserDto;
import com.turtle.entity.sql.User;
import com.turtle.util.BaseMapping;
import org.mapstruct.Mapper;

/**
 * @author lijiayu
 * @date 2020/6/24
 * @description
 */
@Mapper(componentModel = "spring")
public interface UserMapping extends BaseMapping<User, UserDto> {

}
