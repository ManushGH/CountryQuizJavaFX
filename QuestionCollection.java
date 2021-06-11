package edu.uga.cs1302.quiz;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;


public class QuestionCollection {
    protected static ArrayList<String> country = new ArrayList<String>();
    protected static ArrayList<String> continent = new ArrayList<String>();

    public QuestionCollection() {
    }

    public static void scan() {
        try(Reader map = new FileReader("C:\\Users\\shahm\\Documents\\IdeaProjects\\Udemy\\Project5\\src\\edu\\uga\\cs1302\\quiz\\country_continent.csv");
            CSVParser parser = CSVFormat.DEFAULT.parse(map)) {
            for(CSVRecord record : parser) {
                country.add(record.get(0));
                continent.add(record.get(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}