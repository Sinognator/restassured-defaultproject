package br.com.api.model;

public class AuthRequest {
    public String username;
    public String password;

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
