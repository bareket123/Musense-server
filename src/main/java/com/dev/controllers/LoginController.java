package com.dev.controllers;

import com.dev.Models.UserDetailsModel;
import com.dev.objects.User;
import com.dev.responses.BasicResponse;
import com.dev.responses.LoginResponse;
import com.dev.utils.Persist;
import com.dev.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;

import static com.dev.utils.Constants.EVENT_ADDED_NEW_OFFER;
import static com.dev.utils.Constants.MINUTE;
import static com.dev.utils.Errors.*;

@RestController
public class LoginController {


    @Autowired
    private Utils utils;

    @Autowired
    private Persist persist;

    @Qualifier("entityManagerFactory")
    @Autowired
    private Object object;

    @RequestMapping(value = "sign-up")
    public BasicResponse signUp (String username, String password,String email,String picture) {
        BasicResponse basicResponse = new BasicResponse();
        boolean success = false;
        Integer errorCode = null;
        if (username != null) {
            if (password != null) {
                if (utils.isStrongPassword(password)) {
                    User fromDb = persist.getUserByUsername(username);
                    if (fromDb == null) {
                     User toAdd=new User(username,utils.createHash(username,password),email,picture);
                        persist.saveUser(toAdd);
                        success = true;
                    } else {
                        errorCode = ERROR_USERNAME_ALREADY_EXISTS;
                    }
                } else {
                    errorCode = ERROR_WEAK_PASSWORD;
                }
            } else {
                errorCode = ERROR_MISSING_PASSWORD;
            }
        } else {
            errorCode = ERROR_MISSING_USERNAME;
        }
        basicResponse.setSuccess(success);
        basicResponse.setErrorCode(errorCode);
        return basicResponse;
    }

    @RequestMapping (value = "login")
    public BasicResponse login (String username, String password) {
        BasicResponse basicResponse = new BasicResponse();
        boolean success = false;
        Integer errorCode = null;
        if (username != null) {
            if (password != null) {
                String token = utils.createHash(username, password);
                User fromDb = persist.getUserByUsernameAndToken(username, token);
                if (fromDb != null) {
                    success = true;
                    basicResponse = new LoginResponse(token);
                } else {
                    errorCode = ERROR_WRONG_LOGIN_DETAILS;
                }
            } else {
                errorCode = ERROR_MISSING_PASSWORD;
            }
        } else {
            errorCode = ERROR_MISSING_USERNAME;
        }
        basicResponse.setSuccess(success);
        basicResponse.setErrorCode(errorCode);
        return basicResponse;
    }
    @RequestMapping(value = "get-users-size" , method = RequestMethod.GET)
      public int getUsersSize(){
        return persist.getAllUsers().size();
    }
    @RequestMapping(value = "get-user-details-by-token" , method = RequestMethod.GET)
    public UserDetailsModel getUsernameByToken(String token){
        return persist.getUsernameByToke(token);
    }








}
