package com.poshmaals.userInformation.payloads.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;

}
