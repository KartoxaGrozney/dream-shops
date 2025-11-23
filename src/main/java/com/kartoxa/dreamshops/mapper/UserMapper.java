package com.kartoxa.dreamshops.mapper;

import com.kartoxa.dreamshops.dto.UserDto;
import com.kartoxa.dreamshops.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderMapper.class, CartMapper.class})
public interface UserMapper {

    @Mapping(source = "orders", target = "orders")
    @Mapping(source = "cart", target = "cart")
    UserDto userToUserDto(User user);

}