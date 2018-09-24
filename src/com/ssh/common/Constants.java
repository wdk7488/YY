package com.ssh.common;

public class Constants {
	public static enum Dict{
		
		SEX("SEX","性别"),
		RELATION("RELATION","关系"),
		RACESYSTEM("RACESYSTEM","种族制度"),
		PANDA("PANDA","熊猫常量"),
		GENEALOGY("GENEALOGY","族谱常量");
		
		
		private Dict(String value,String name){
            this.value=value;
            this.name=name;
        }
        private final String value;
        private final String name;
        
        
        public String getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
	}
	
	public static enum RACESYSTEM{
		MATERNAL(0,"母系"),
		PATERNAL(1,"父系");
		
		private RACESYSTEM(Integer value,String name){
            this.value=value;
            this.name=name;
        }
        private final Integer value;
        private final String name;
        
        
        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
	}
	
	
	public static enum RELATION{
		UNKOWN(0,"无关"),
		FATHER(1,"父亲"), MOTHER(2,"母亲"),
		GRANDFATHER(3,"爷爷"),GRANDMOTHER(4,"奶奶"),
		MGRANDFATHER(5,"外公"),MGRANDMOTHER(6,"外婆"),
		SON(7,"儿子"),DAUGHTER(8,"女儿"),
		GRANDSON(9,"孙子"),GRANDDAUGHTER(10,"孙女"),
		MGRANDSON(11,"外孙"),MGRANDDAUGHTER(12,"外孙女"),;
		
		private RELATION(Integer value, String name){
			this.value = value;
			this.name = name;
		}
		private final Integer value;
		private final String name;
		
		public String getName(Integer value) {
			RELATION[] relations = RELATION.values();
			for(RELATION relation: relations){
				if(relation.getValue() == value){
					return relation.getName();
				}
			}
            return null;
        }
		
		public Integer getValue() {
            return value;
        }
		
        public String getName() {
            return name;
        }
        
	}
	
	public static enum SEX{
		WOMAN(0,"女"), MAN(1,"男"), UNKNOWN(2,"未知");
		private SEX(Integer value, String name){
			this.value = value;
			this.name = name;
		}
		private final Integer value;
		private final String name;
		
		public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
        
	}
	
	public static enum PANDA{
		
		WILD("99999","野生");
		
		private PANDA(String value,String name){
            this.value=value;
            this.name=name;
        }
        private final String value;
        private final String name;
        
        
        public String getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
	}
	
	public static enum GENEALOGY{
		
		UNKOWN("0","父母未知");
		
		private GENEALOGY(String value,String name){
            this.value=value;
            this.name=name;
        }
        private final String value;
        private final String name;
        
        
        public String getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
	}
}
