package com.adaptionsoft.games.trivia.enums;

public enum QuestionEnum {
	
	POP("Pop"), SCIENCE("Science"),SPORTS("Sports"),ROCK("Rock");

    private String code;

    QuestionEnum(final String code) {
        this.code = code;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return this.code;
    }

}
