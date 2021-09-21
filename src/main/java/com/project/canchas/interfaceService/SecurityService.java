package com.project.canchas.interfaceService;

public interface SecurityService {

    boolean isAuthenticated();

    void autoLogin(String username, String password);
}
