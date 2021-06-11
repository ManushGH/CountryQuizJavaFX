package edu.uga.cs1302.quiz;

public class Question {
    private String question;
    private int limit;
    private boolean right;
    private int options;
    private QuestionCollection questionCollection = new QuestionCollection();

    public String questionText() {
        questionCollection.scan();
        limit = (int)(Math.random() *194);
        return question = "On which continent is " + questionCollection.country.get(limit);
    }

    public boolean checkAnswer(String answer) {
        right = false;
        if (answer.equalsIgnoreCase(questionCollection.continent.get(limit))){
            right = true;
        }
        return right;
    }

    public String option1(String country) {
        limit = questionCollection.country.indexOf(country);
        return questionCollection.continent.get(limit);
    }

    public String option2() {
        options = (int)(Math.random()*194);
        while (questionCollection.continent.get(limit).equalsIgnoreCase(questionCollection.continent.get(options))) {
            options = (int)(Math.random()*194);
        }
        return questionCollection.continent.get(options);
    }

    public String option3() {
        int opt3 = (int)(Math.random()*194);
        while (questionCollection.continent.get(limit).equalsIgnoreCase(questionCollection.continent.get(opt3)) ||
                questionCollection.continent.get(options).equalsIgnoreCase(questionCollection.continent.get(opt3))) {
            opt3 = (int)(Math.random()*194);
        }
        return questionCollection.continent.get(opt3);
    }

    public int getLimit() {
        return limit;
    }
}
