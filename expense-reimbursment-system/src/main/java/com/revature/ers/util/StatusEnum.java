package com.revature.ers.util;

public enum StatusEnum {

	PENDING("PENDING"),
	APPROVED("APPROVED");

    private final String name;

    /**
     * @param name
     */
    private StatusEnum(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
