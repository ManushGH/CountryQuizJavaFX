package edu.uga.cs1302.quiz;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class QuizResult {
    LinkedList<String> results = new LinkedList<String>();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = simpleDateFormat.format(new Date());
    String resultingString = "Results:-";
    LinkedList<String> read = new LinkedList<String>();
    LinkedList<String> reference = new LinkedList<String>();

    public void setResults (String result) {
        results.add(0,"[" +date + "] " + result);
    }

    public String getResults (){
        readFromFile();
        for(String item: results){
            resultingString += ("\n"+item);
        }
        for (String item: read) {
            resultingString += ("\n"+item);
        }
        return resultingString;
    }

    public void writeToFile () {
        try {
            FileOutputStream fos = new FileOutputStream("quizzes.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            results.addAll(reference);
            oos.writeObject(results);
//            oos.writeObject(reference);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void readFromFile () {
        try {
            FileInputStream fis = new FileInputStream("quizzes.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            read = (LinkedList<String>)ois.readObject();
            reference = (LinkedList<String>) read.clone();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
