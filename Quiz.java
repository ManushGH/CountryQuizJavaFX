package edu.uga.cs1302.quiz;

import java.util.ArrayList;

public class Quiz {
    ArrayList<String> currentQuestions = new ArrayList<String>();
    ArrayList<String> countryInQuestion = new ArrayList<String>();;
    Question question = new Question();
    QuestionCollection questionCollection = new QuestionCollection();

    public void setCurrentQuestions() {
        for (int i = 0; i < 7; i++) {
            String check = question.questionText();
            if (!currentQuestions.contains(check)) {
                currentQuestions.add(check);
                countryInQuestion.add(questionCollection.country.get(question.getLimit()));
            }
        }
    }
}
