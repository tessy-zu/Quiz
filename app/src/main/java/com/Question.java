package com;

/**
 * Created by user on 11/9/17.
 */

public class Question {

    private int textId;
    private boolean answer;

    public Question(int textId, boolean answer) {
        this.textId = textId;
        this.answer = answer;
    }

    public int getTextId() {
        return this.textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public boolean answer() {
        return this.answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
