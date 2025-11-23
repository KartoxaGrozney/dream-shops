package com.kartoxa.dreamshops.service.user;

import com.kartoxa.dreamshops.dto.UserDto;
import com.kartoxa.dreamshops.model.User;
import com.kartoxa.dreamshops.request.CreateUserRequest;
import com.kartoxa.dreamshops.request.UserUpdateRequest;

public interface IUserService {


    User getUserById(Long userId);


    User createUser(CreateUserRequest request);

    User updateUser(UserUpdateRequest request, Long userId);

    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);
}
