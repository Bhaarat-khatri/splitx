package com.spendsense.splitx.util;

import com.spendsense.splitx.entity.User;

import java.util.UUID;

public class DummyEmailGenerator {
    public static String generateDummyEmail(User user) {
        String username = user.getName() + "_" +
                UUID.randomUUID().toString().replace("-", "").substring(0, 8);

        return username.toLowerCase() + "@dummy.com";
    }
}
