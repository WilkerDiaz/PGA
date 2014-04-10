package com.abc.ws.bean;

public class SingleClassInfo {

    private String name;
    private long id;
    private String link;
    private String target;
	
    public SingleClassInfo() {
	}
    
    public SingleClassInfo(String name, long id, String link, String target) {
		this.name = name;
		this.id = id;
		this.link = link;
		this.target = target;
	}

	public String getNameFormatted() {
		
		return getName().replace(" ", "-");
	}
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	
}
