package Zad2;

public class AdRequest {
    private String id;
    private String category;
    private double floorBid;
    private String keywords;
    
    public AdRequest(String id, String category, double floorBid, String keywords) {
        this.id = id;
        this.category = category;
        this.floorBid = floorBid;
        this.keywords = keywords;
    }
    
    @Override
    public String toString() {
        return String.format("%s [%s] (floor=%.2f): %s", id, category, floorBid, keywords);
    }
    
    public String getId() {
        return id;
    }
    
    public String getKeywords() {
        return keywords;
    }
    
    public double getFloorBid() {
        return floorBid;
    }
    
    public String getCategory() {
        return category;
    }
}
