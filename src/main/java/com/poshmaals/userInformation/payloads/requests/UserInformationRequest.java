package com.poshmaals.userInformation.payloads.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationRequest {
    private String firstName;
    private String lastName;
    private String address;

}


