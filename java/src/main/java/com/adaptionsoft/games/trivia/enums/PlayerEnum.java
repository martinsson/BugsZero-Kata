package com.adaptionsoft.games.trivia.enums;

public enum PlayerEnum {
	
	CHET("Chet"), PAT("Pat"),SUE("Sue");

    private String code;

    PlayerEnum(final String code) {
        this.code = code;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return this.code;
    }

}
