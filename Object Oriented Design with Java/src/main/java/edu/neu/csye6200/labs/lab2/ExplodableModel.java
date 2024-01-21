package edu.neu.csye6200.labs.lab2;

import edu.neu.csye6200.labs.lab2.interfaceDerived.Explodable;
import edu.neu.csye6200.labs.lab2.interfaceDerived.GrenadeI;
import edu.neu.csye6200.labs.lab2.interfaceDerived.GunShotI;

public class ExplodableModel {
	
	public void triggerExplosion(Explodable explodable) {
		explodable.explode();
	}
	
	public static void demo() {
		System.out.println("\nDemonstrating Polymorphism & Loose Coupling for ExplodableModel");
		ExplodableModel explodableModel = new ExplodableModel();
		Explodable gunshotExplodable = new GunShotI();
		explodableModel.triggerExplosion(gunshotExplodable);
		Explodable grenadeExplodable = new GrenadeI();
		explodableModel.triggerExplosion(grenadeExplodable);
		
	}
}
