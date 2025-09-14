package com.namegame;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * For creating the a name list json for a new user.
 * Applies the user's preferences to the master names.json.
 */

public class NameList {
    ArrayList<Name> names = new ArrayList<>();
    private String username;
    private char genderPreference;
    private boolean ignoreRare;

    /**
     * Constructor for a name list/queue. Requires the 
     * user's choices for name filtering, and the user's id/name for memory reasons.
     * */
    public NameList(String username, int gender, int ignoreRare) {

        System.out.println("Creating a name list for " + username);

        this.username = username;
        
        if (gender == 0) {
            this.genderPreference = 'm';
            System.out.println("Using only male names");
        } else if (gender == 1) {
            this.genderPreference = 'f';
            System.out.println("Using only female names");
        } else {
            this.genderPreference = 'b';
            System.out.println("Using names for both genders");
        }

        if (ignoreRare == 0) {
            this.ignoreRare = true;
            System.out.println("Ignoring rare names");
        } else {
            this.ignoreRare = false;
            System.out.println("Using all names");
        }

        try {
            readJSONList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writeNameList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // create personal name queue;
        // loop through the entire name list
        // for every name, check that it matches the choices
        // if yes, add the name to the queue

        // read the json to create the name list
    }

    private void readJSONList() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File("desktop\\src\\main\\java\\com\\namegame\\names.json"));
        
        Iterator<Map.Entry<String, JsonNode>> allNames = jsonNode.fields();


        while (allNames.hasNext()) {
            Map.Entry<String, JsonNode> nameEntry = allNames.next();
            int id = Integer.valueOf(nameEntry.getKey());
            JsonNode attributes = nameEntry.getValue();

            String name = attributes.get("name").asText();
            double males = attributes.get("count_m").asDouble();
            double females = attributes.get("count_f").asDouble();
            double first = attributes.get("first_ratio").asDouble();
            double gender = attributes.get("g_ratio").asDouble();
            //System.out.println(name);

            Name newName;
            
            if (this.genderPreference == 'b' && (!this.ignoreRare)) {
                System.out.println("Include all");
                newName = new Name(id, name, males, females, gender, first, false);
            }
            // gender preference
            else if ((this.genderPreference == 'm' && gender < 0.25) || (this.genderPreference == 'f' && gender > 0.75)) {
                System.out.println("Not matching gender");
                newName = new Name(id, name, males, females, gender, first, true);
                // add to the array
            } else if (this.ignoreRare && (males + females < 11)) {
                System.out.println("Not matching rarity");
                newName = new Name(id, name, males, females, gender, first, true);
            } else {
                newName = new Name(id, name, males, females, gender, first, false);
            }

            names.add(newName);
            
        }
    }

    private void writeNameList() throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();

        File filu = new File(username + "_namelist.json");
        try {
            mapper.writeValue(filu, names);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
