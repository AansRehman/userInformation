package com.poshmaals.userInformation.mappers;


import com.poshmaals.userInformation.models.User;
import com.poshmaals.userInformation.payloads.requests.UserInformationRequest;
import com.poshmaals.userInformation.payloads.responses.UserInformationResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // Convert UserInformationRequest to UserInformation
    public User toUserInformation(UserInformationRequest request) {
        if (request == null) {
            return null;
        }
        return new User(
                null,
                request.getFirstName(),
                request.getLastName(),
                request.getAddress()
        );

    }

    // Convert UserInformation to UserInformationResponse
    public UserInformationResponse toUserInformationResponse(User user) {
        if (user == null) {
            return null;
        }
        return new UserInformationResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getAddress()
        );
    }
}
