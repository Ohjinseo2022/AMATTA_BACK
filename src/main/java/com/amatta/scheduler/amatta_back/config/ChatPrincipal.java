package com.amatta.scheduler.amatta_back.config;

import lombok.Getter;
import lombok.Setter;

import java.security.Principal;

@Getter
@Setter
public class ChatPrincipal implements Principal {
    private String name;

    public ChatPrincipal(String username) {
        this.name = username;
    }
}
