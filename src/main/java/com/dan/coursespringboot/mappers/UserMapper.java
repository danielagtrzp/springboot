package com.dan.coursespringboot.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.dan.coursespringboot.dtos.UserMsDto;
import com.dan.coursespringboot.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    //User to UserMsDto
    @Mappings({
        @Mapping(source = "email",target = "emailadress"),
        @Mapping(source = "role",target = "rolename")
    })
    UserMsDto userToUserMsDto(User user);

    //List<User> to List<UserMsDto>
    List<UserMsDto> usersToUsersMsDto(List<User> users);

}
