package com.application.electronic_book.service.user;

import com.application.electronic_book.entity.User;
import com.application.electronic_book.model.user.UserAuthModel;
import com.application.electronic_book.model.user.UserModel;
import com.application.electronic_book.model.user.UserRegistrationModel;
import com.application.electronic_book.model.user.UserTokenModel;
import com.application.electronic_book.service.basic_interfaces.*;

public interface UserService extends
        Create<UserModel, UserRegistrationModel>,
        Read<UserModel, User>,
        Delete,
        ToModelConverter<UserModel, User>
{
    UserTokenModel login(UserAuthModel userAuthModel);
}
