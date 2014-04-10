package com.abstracta.enums;

public enum Catalog {
	
	BOX("b"),
	TYPE("t"),
	SECTION("s"),
	DEPARTMENT("d"),
	HOME("h"),
    ITEM("i");
 
    private String code;
 
    Catalog(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
