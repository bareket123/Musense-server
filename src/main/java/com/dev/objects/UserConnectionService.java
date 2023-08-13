package com.dev.objects;

public interface UserConnectionService {
    UserConnection sendFriendRequest(Long userId, Long friendId);
    void acceptFriendRequest(Long connectionId);
    void declineFriendRequest(Long connectionId);

}
