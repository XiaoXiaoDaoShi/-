package test;

import People.Manager;
import People.Personal;
import People.Position;
import bonus.CountBonus;
import bonus.MouthBonus;
import bonus.TeamBonus;

public class Test {

	public static void main(String[] args) {
		Position personal = new Personal(10086.3,100.2);
		Position manager = new Manager(12345.6);
		
		personal = new MouthBonus(personal);
		
		personal = new CountBonus(personal);
		
		manager = new TeamBonus(manager);
		
		System.out.println(personal.getDescription());
		System.out.println(personal.gain());
		System.out.println(manager.getDescription());
		System.out.println(manager.gain());
	}

}
