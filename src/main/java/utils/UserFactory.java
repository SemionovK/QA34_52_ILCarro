package utils;

import dto.UserLombok;

public class UserFactory {
   public static UserLombok positiveUser(){
        UserLombok user = UserLombok.builder()
                .username("bruno1@gmail.com")
                .password("QAZ123!lnk")
                .build();
        return user;
   }
}
