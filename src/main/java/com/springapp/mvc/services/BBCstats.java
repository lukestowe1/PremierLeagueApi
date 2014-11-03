package com.springapp.mvc.services;

import com.springapp.mvc.dataStructures.TableEntry;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by ciaran on 02/11/14.
 */
public class BBCstats {

    private HashMap<Integer, TableEntry> bbcTable = new HashMap<Integer, TableEntry>();
    private final String urlOfTable = "http://www.bbc.com/sport/football/tables";

    public boolean getBBCdata() {
        try {
            Document doc = Jsoup.connect(urlOfTable).get();
            Elements teamNames = doc.getElementsByAttribute("data-team-slug");
            Elements points = doc.getElementsByAttributeValue("class","points").not("th");
            int count =0;
            for (Element name : teamNames ){
                String club = name.attr("data-team-slug");
                int numPoints = Integer.parseInt(points.get(count).text());
                TableEntry tableEntry = new TableEntry(club,numPoints);
                bbcTable.put(count+1, tableEntry);
                count++;
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    public HashMap<Integer, TableEntry> getBbcTable(){
        return bbcTable;
    }
}
