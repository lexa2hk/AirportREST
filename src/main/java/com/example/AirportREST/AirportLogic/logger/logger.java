package com.example.AirportREST.AirportLogic.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public interface logger extends Serializable {
    public static void log(String message) throws IOException {
        System.out.println(message);
        File file = new File("src/main/java/com/example/AirportREST/AirportLogic/EventGenerator/logs.txt");
        if (file.createNewFile()){
            System.out.println("File is created!");
        }
        else{
            System.out.println("File already exists.");
        }
        try {
            String checkerRegex = "[A-Z][A-Z][0-9][0-9][0-9][0-9][' '][i][s].+";
            Pattern pattern = Pattern.compile(checkerRegex);
            Matcher matcher = pattern.matcher(message);

            if (Pattern.matches(checkerRegex, message) && matcher.find()) {
                Date date = new Date();
                FileWriter writer = new FileWriter(file, true);
                writer.write(message + " " + date.toString() + "\n");
                writer.close();
            }
        }
        catch (PatternSyntaxException pse){
            System.out.println("Description: " + pse.getMessage());
        }


        //[A-Z][A-Z][0-9][0-9][0-9][0-9][' '][i][s].+

    }
}
