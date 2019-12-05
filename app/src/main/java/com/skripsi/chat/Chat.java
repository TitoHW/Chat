package com.skripsi.chat;

public class Chat  {

    private String username, message;

    public Chat(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public Chat() {
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }


}
