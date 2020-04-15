package com.endpoint.fastone.singletone;

import android.content.Context;

import com.endpoint.fastone.models.UserModel;
import com.endpoint.fastone.preferences.Preferences;

public class UserSingleTone {

    private static UserSingleTone instance = null;
    private UserModel userModel;

    private UserSingleTone() {
    }

    public static UserSingleTone getInstance()
    {
        if (instance ==null)
        {
            instance = new UserSingleTone();
        }
        return instance;
    }


    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
    public void clear(Context context)
    {
        this.userModel=null;
        Preferences preferences = Preferences.getInstance();
        preferences.ClearUserData(context);

    }

}
