package utils;

import dto.UserLombok;
import net.datafaker.Faker;

public class UserFactory {
    static Faker faker = new Faker();

    public static UserLombok positiveUser(){
        UserLombok user = UserLombok.builder()
                .username(faker.internet().emailAddress())
                .password("Qwerty123!")
                .build();
        return user;
    }
}
