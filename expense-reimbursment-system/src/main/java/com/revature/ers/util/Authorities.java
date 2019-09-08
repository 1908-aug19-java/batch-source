package com.revature.ers.util;

public enum Authorities {

	EMPLOYEE("EMPLOYEE"),
	MANAGER("MANAGER");

    private final String name;

    /**
     * @param name
     */
    private Authorities(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
