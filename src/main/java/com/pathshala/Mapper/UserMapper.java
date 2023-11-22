package com.pathshala.Mapper;

import com.pathshala.dao.UserEntity;
import com.pathshala.dto.UserModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserModel mapUserEntityToUserModel(UserEntity user);

    UserEntity mapUserModelToUserEntity(UserModel userModel);

    List<UserEntity> mapUserModelsToUserEntities(List<UserModel> userModelList);

    List<UserModel> mapUserEntitiesToUerModels(List<UserEntity> userEntityList);

}
