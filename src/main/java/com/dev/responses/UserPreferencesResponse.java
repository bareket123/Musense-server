package com.dev.responses;

import com.dev.Models.UserPreferencesModel;
import com.dev.objects.UserPreferences;

public class UserPreferencesResponse extends BasicResponse{
    UserPreferencesModel userPreferences;


    public UserPreferencesResponse(UserPreferencesModel userPreferences) {
        this.userPreferences = userPreferences;
    }

    public UserPreferencesResponse(boolean success, Integer errorCode, UserPreferencesModel userPreferences) {
        super(success, errorCode);
        this.userPreferences = userPreferences;
    }

    public UserPreferencesModel getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(UserPreferencesModel userPreferences) {
        this.userPreferences = userPreferences;
    }
}
