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


//    @RequestMapping(value = "/sse-handler", method = RequestMethod.GET)
//    public SseEmitter handle (String username){
//        System.out.println("////////////////////////in handler ///////////////////////////////////////////////////////////////////////////////////");
//        SseEmitter sseEmitter = null;
//        User user=persist.getUserByUsername(username);
//        if (user!=null){
//            sseEmitter=this.emitterMap.get(username);
//            System.out.println(emitterMap);
//            if (sseEmitter==null){
//                sseEmitter=new SseEmitter(10L * MINUTE);
//                this.emitterMap.put(username,sseEmitter);
//            }
//
//        }
//
//        return sseEmitter;
//    }
    @RequestMapping (value = "/sse-handler", method = RequestMethod.GET)
    public SseEmitter handle (String token, String friendUsername) {
        User user = persist.getUserByToken(token);
        SseEmitter sseEmitter = null;
        if (user != null) {
            sseEmitter = new SseEmitter(10L * MINUTE);
            String key = createKey(token, friendUsername);
            System.out.println(key);
            this.emitterMap.put(key, sseEmitter);
        }
        return sseEmitter;
    }
    private String createKey (String userToken, String friendUsername) {
        return String.format("%S_%S", userToken, friendUsername);
    }

//    public void deleteSongFromPlaylist (int songId,String token) {
//         SseEmitter songEmitter=emitterMap.get(token);
//        if (songEmitter != null) {
//            try {
//                songEmitter.send(songId);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }else {
//            System.out.println("\n");
//            System.out.println("**********************************************this is nullllllll********************************************");
//            System.out.println("\n");
//        }
//    }
    public void notifyFriend(String userToken,String username){
        String key = createKey(userToken, username);
        System.out.println(key);
        SseEmitter friendRequest = this.emitterMap.get(key);
      //  SseEmitter songEmitter=emitterMap.get(username);
        if (friendRequest != null) {
            try {
                friendRequest.send("it workinggg ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("\n");
            System.out.println("**********************************************this is nullllllll********************************************");
            System.out.println("\n");
        }
    }



//
//    @RequestMapping(value = "connect")
//    public SseEmitter handle(@PathVariable String userId) {
//        SseEmitter emitter = new SseEmitter();
//        this.emitters.put(userId, emitter);
//        emitter.onCompletion(() -> this.emitters.remove(userId));
//        emitter.onTimeout(() -> this.emitters.remove(userId));
//        return emitter;
//    }
//
//    @PostMapping("/follower/{userId}")
//    public void addFollower(@PathVariable String userId) {
//        SseEmitter emitter = this.emitters.get(userId);
//        if (emitter != null) {
//            try {
//                emitter.send("You have a new follower!");
//            } catch (Exception e) {
//                this.emitters.remove(userId);
//            }
//        }
//    }





}
