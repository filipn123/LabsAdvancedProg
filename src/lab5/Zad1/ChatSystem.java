package lab5.Zad1;

import java.util.*;

public class ChatSystem {
    private TreeMap<String, ChatRoom> rooms;
    private TreeSet<String> users;
    
    public ChatSystem() {
        this.rooms = new TreeMap<>();
        this.users = new TreeSet<>();
    }
    
    public void addRoom(String roomName) {
        rooms.put(roomName, new ChatRoom(roomName));
    }
    
    public void removeRoom(String roomName) {
        rooms.remove(roomName);
    }
    
    public ChatRoom getRoom(String roomName) throws NoSuchRoomException {
        ChatRoom room = rooms.get(roomName);
        if (room == null) {
            throw new NoSuchRoomException(roomName);
        }
        return room;
    }
    
    public void register(String userName) {
        users.add(userName);
        rooms.values().stream().min(Comparator.comparingInt(ChatRoom :: numUsers).thenComparing(ChatRoom :: getName)).ifPresent(r -> r.addUser(userName));
    }
    
    public void registerAndJoin(String userName, String roomName) {
        users.add(userName);
        rooms.get(roomName).addUser(userName);
    }
    
    public void joinRoom (String userName, String roomName) {
        check(userName, roomName);
        rooms.get(roomName).addUser(userName);
    }
    
    public void leaveRoom (String userName, String roomName) {
        check(userName, roomName);
        rooms.get(roomName).removeUser(userName);
    }
    
    private void check(String userName, String roomName) {
        if(!users.contains(userName)) {
            try {
                throw new NoSuchUserException(userName);
            } catch (NoSuchUserException e) {
                return;
            }
        }
        if(rooms.get(roomName) == null) {
            try {
                throw new NoSuchRoomException(roomName);
            } catch (NoSuchRoomException e) {
                return;
            }
        }
    }
    
    public void followFriend (String userName, String friendName) {
        if(!users.contains(friendName)) {
            try {
                throw new NoSuchUserException(friendName);
            } catch (NoSuchUserException e) {
                return;
            }
        }
        users.add(userName);
        
        rooms.values().stream().filter(r -> r.hasUser(friendName)).forEach(r -> r.addUser(userName));
    }
}

