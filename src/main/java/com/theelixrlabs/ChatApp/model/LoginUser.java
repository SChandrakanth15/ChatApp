package com.theelixrlabs.ChatApp.model;

import com.theelixrlabs.ChatApp.constants.LoginUserContants;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The LoginUser class represents the user entity used for login authentication.
 * It includes fields for username, password, and account activation status.
 */
@Data
@Document(collection = LoginUserContants.LOGIN_USERS_COLLECTION_NAME)
public class LoginUser {
    @Id
    private String id;
    private String username;
    private String password;
    private boolean isActive;
}
