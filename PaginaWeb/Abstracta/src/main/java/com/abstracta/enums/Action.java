package com.abstracta.enums;

public enum Action {
	
	ACCOUNT_CONFIRM("ACC"),
	WISHLIST_ERROR("WLE"),
	WISHLIST_ADDED("WLA"),
    WISHLIST_UPDATED("WLU");
 
    private String code;
 
    Action(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
	
}
