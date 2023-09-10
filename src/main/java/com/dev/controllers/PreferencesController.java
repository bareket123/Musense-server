package com.dev.controllers;

import com.dev.Models.UserPreferencesModel;
import com.dev.objects.User;
import com.dev.objects.UserPreferences;
import com.dev.responses.BasicResponse;
import com.dev.responses.UserPreferencesResponse;
import com.dev.utils.Errors;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PreferencesController {
    @Autowired
    Persist persist;

    @RequestMapping(value = "send-user-preferences")
    public BasicResponse sendUserPreferences(String token,String genre,String artist1,String artist2,String favoriteSong ){
        BasicResponse basicResponse;
        User user=persist.getUserByToken(token);
        if (user!=null){
            if (genre!=null&& artist1!=null&&artist2!=null&&favoriteSong!=null){
                UserPreferences userPreferences=new UserPreferences(user,genre,artist1,artist2,favoriteSong);
                persist.addUserPreferences(userPreferences);
                basicResponse=new BasicResponse(true,null);
            }else {
                basicResponse=new BasicResponse(false, Errors.ERROR_IN_ANSWERS_DETAILS);
            }
        }else {
            basicResponse=new BasicResponse(false,Errors.ERROR_USER_NOT_FOUND);
        }
        return basicResponse;
    }
    @RequestMapping ("get-user-preferences")
    public BasicResponse getUserPreferences(String token){
        BasicResponse basicResponse;
      User user=persist.getUserByToken(token);
      if (user!=null){
          UserPreferences userPreferences=persist.getUserPreferences(user);
          if (userPreferences!=null){
              UserPreferencesModel userPreferencesModel =new UserPreferencesModel(userPreferences);
              basicResponse=new UserPreferencesResponse(true,null,userPreferencesModel);
          }else {
              basicResponse=new BasicResponse(false,Errors.ERROR_WRONG_ANSWERS_DETAILS);
          }
      }else {
          basicResponse=new BasicResponse(false,Errors.ERROR_USER_NOT_FOUND);
      }
     return basicResponse;
    }
@RequestMapping("delete-answers")
    public BasicResponse deleteUserPreferences(String token){
        BasicResponse basicResponse;
        User user=persist.getUserByToken(token);
        if (user!=null){
            UserPreferences userPreferences=persist.getUserPreferences(user);
            if (userPreferences!=null){
                persist.deleteUserPreferences(userPreferences);
                basicResponse=new BasicResponse(true,null);
            }else{
                basicResponse=new BasicResponse(false,Errors.ERROR_WRONG_ANSWERS_DETAILS);
            }

        }else{
            basicResponse=new BasicResponse(false,Errors.ERROR_USER_NOT_FOUND);
        }


        return basicResponse;
}

}
