package npc;

public enum LevelOfChallenge {
	
	BASIC("Basic") ,
	WILD("Wild") ,
	BRAVE("Brave"),
	HEROIC("Heroic"),
	EPIC("Epic");
	
	LevelOfChallenge(String level){
		this.level = level;
	}
	
	private String level;
	
	public String getLevelChallenge(){
		return level;
		
	}
	
}
