package com.dev.Models;

import com.dev.objects.User;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchFriendModel extends UserDetailsModel {

    private  boolean isFollowing;


    public SearchFriendModel(User current ,boolean isFollowing ) {
        super(current);
        this.isFollowing= isFollowing;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }

}
