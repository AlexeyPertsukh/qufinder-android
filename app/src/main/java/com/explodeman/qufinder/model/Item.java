package com.explodeman.qufinder.model;

public class Item {
  private String question;
  private String answer;

  public Item(String question, String answer) {
    this.question = question;
    this.answer = answer;
  }

  public String getQuestion() {
    return question;
  }

  public String getAnswer() {
    return answer;
  }
}
