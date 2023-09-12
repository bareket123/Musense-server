package com.dev.controllers;

import com.dev.objects.Song;
import com.dev.objects.User;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import static com.dev.utils.Constants.MINUTE;

@Controller
public class LiveUpdateController {

    @Autowired
    Persist persist;
    private Map<String,SseEmitter> emitterMap = new HashMap<>();

        @RequestMapping (value = "/sse-handler", method = RequestMethod.GET)
        public SseEmitter handle (String token, String friendUsername) {
            System.out.println("token: "+token+"friend: "+friendUsername);
            User user = persist.getUserByToken(token);
            SseEmitter sseEmitter = null;
            if (user != null && friendUsername!=null) {
                sseEmitter = new SseEmitter(10L * MINUTE);
                String key = createKey(token, friendUsername);
                System.out.println("from connections: "+key);
                this.emitterMap.put(key, sseEmitter);
                System.out.println("this is map: "+ emitterMap+"\n");
            }else {
                System.out.println("friend is null or the user is null ");
            }
            return sseEmitter;
        }
        private String createKey (String userToken, String friendUsername) {
            return String.format("%S_%S", userToken, friendUsername);
        }

        public void notifyFriend(String userToken,String username){
            String key = createKey(userToken, username);
            System.out.println("from notify: "+key);
            System.out.println("the current map: "+emitterMap);
            SseEmitter friendRequest = this.emitterMap.get(key);
          //  SseEmitter songEmitter=emitterMap.get(username);
            if (friendRequest != null ) {
                try {
                    // Format the SSE message correctly
                    String sseMessage = "data: it workinggg\n\n"; // Note the newline character \n after the message data

                    // Send the formatted SSE message
                    friendRequest.send(sseMessage);

                    System.out.println("SSE message sent successfully.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println("\n");
                System.out.println("**********************************************this is nullllllll********************************************");
                System.out.println("\n");
            }
        }







}
