package npc;

public class FactoryNPC {
	
	public static FactoryNPC factory;
	
	
	protected FactoryNPC() {
	}
	
	public static FactoryNPC getFactory(){
		
		if (factory == null) 
			factory = new FactoryNPC();
			return factory;
	}	
}
