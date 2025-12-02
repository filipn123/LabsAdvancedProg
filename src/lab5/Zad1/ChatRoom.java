package lab5.Zad1;
import java.util.TreeSet;

public class ChatRoom {
    private String name;
    private TreeSet<String> users;
    
    public ChatRoom(String name) {
        this.name = name;
        this.users = new TreeSet<>();
    }
    
    public void addUser(String username) {
        this.users.add(username);
    }
    
    public void removeUser(String username) {
        this.users.remove(username);
    }
    
    public boolean hasUser(String username) {
        return this.users.contains(username);
    }
    
    public int numUsers() {
        return this.users.size();
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        if(users.isEmpty()) {
            sb.append("EMPTY\n");
        }
        else {
            for (String user : users) {
                sb.append(user).append("\n");
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}
