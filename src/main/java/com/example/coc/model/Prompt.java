package com.example.coc.model;

public class Prompt {

    private String text;
    private String violation;
    private int severityScore;

    public Prompt() {
        this.violation = "No violation detected by Drools rules.";
        this.severityScore = 0;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getViolation() {
        return violation;
    }

    public void setViolation(String violation) {
        this.violation = violation;
    }

    public int getSeverityScore() {
        return severityScore;
    }

    public void setSeverityScore(int severityScore) {
        this.severityScore = severityScore;
    }

    @Override
    public String toString() {
        return "Prompt{" +
               "text='" + text + '\'' +
               ", violation='" + violation + '\'' +
               ", severityScore=" + severityScore +
               '}';
    }
} 