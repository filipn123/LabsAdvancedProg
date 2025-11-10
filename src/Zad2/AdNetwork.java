package Zad2;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;


public class AdNetwork {
    private ArrayList<Ad> ads;
    static final double x = 5.0;
    static final double y = 100.0;
    
    public AdNetwork() {
        this.ads = new ArrayList<>();
    }
    
    public void readAds(BufferedReader in) throws IOException {
        String input = in.readLine();
        while(!input.isEmpty()) {
            String[] line = input.split(" ");
            String id = line[0];
            String category = line[1];
            double bidValue = Double.parseDouble(line[2]);
            double ctr = Double.parseDouble(line[3]);
            StringBuilder description = new StringBuilder();
            for(int i = 4; i < line.length; i++) {
                description.append(line[i]).append(" ");
            }
            String desc = description.toString();
            Ad ad = new Ad(id, category, bidValue, ctr, desc);
            ads.add(ad);
            input = in.readLine();
        }
    }
    
    public void placeAds(BufferedReader in, int k, PrintWriter out) throws IOException {
        String[] line = in.readLine().split(" ");
        String id = line[0];
        String category = line[1];
        double bidValue = Double.parseDouble(line[2]);
        StringBuilder description = new StringBuilder();
        for(int i = 3; i < line.length; i++) {
            description.append(line[i]).append(" ");
        }
        String desc = description.toString();
        AdRequest adRequest = new AdRequest(id, category, bidValue, desc);
        
        List<Ad> filteredAds = ads.stream().filter(ad -> ad.getBidValue() >= bidValue).collect(Collectors.toList());
        
        Map<Ad, Double> scores = new HashMap<>();
        for(Ad ad : filteredAds) {
            double totalScore = relevanceScore(ad, adRequest) + x * ad.getBidValue() + y * ad.getCtr();
            scores.put(ad, totalScore);
        }
        
        List<Ad> topKAds = new ArrayList<>(scores.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(k).map(Map.Entry::getKey).collect(Collectors.toList()));
        Collections.sort(topKAds);
        
        out.printf("Top ads for request %s:%n", id);
        for(Ad ad : topKAds) {
            out.println(ad);
        }
    }
    
    private int relevanceScore(Ad ad, AdRequest req) {
        int score = 0;
        if (ad.getCategory().equalsIgnoreCase(req.getCategory())) score += 10;
        String[] adWords = ad.getContent().toLowerCase().split("\\s+");
        String[] keywords = req.getKeywords().toLowerCase().split("\\s+");
        for (String kw : keywords) {
            for (String aw : adWords) {
                if (kw.equals(aw)) score++;
            }
        }
        return score;
    }
}