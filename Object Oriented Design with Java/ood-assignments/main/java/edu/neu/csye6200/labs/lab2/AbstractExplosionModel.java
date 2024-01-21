package edu.neu.csye6200.labs.lab2;

import edu.neu.csye6200.labs.lab2.abstractDerived.AbstractExplosion;
import edu.neu.csye6200.labs.lab2.abstractDerived.GunShotA;
import edu.neu.csye6200.labs.lab2.abstractDerived.GrenadeA;

public class AbstractExplosionModel {
	
	public void triggerExplosion(AbstractExplosion abstractExplosion) {
		abstractExplosion.explode();
	}
	
	public static void demo() {
		System.out.println("\nDemonstrating Polymorphism & Loose Coupling for AbstractExplosionModel");
		AbstractExplosionModel abstractExplosionModel = new AbstractExplosionModel();
		AbstractExplosion gunshotAbstarct = new GunShotA();
		abstractExplosionModel.triggerExplosion(gunshotAbstarct);
		AbstractExplosion grenAbstractExplosion = new GrenadeA();
		abstractExplosionModel.triggerExplosion(grenAbstractExplosion);
		
	}

}
