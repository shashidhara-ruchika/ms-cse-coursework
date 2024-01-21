package edu.neu.csye6200.labs.lab2;

import edu.neu.csye6200.labs.lab2.concreteDerived.Explosion;
import edu.neu.csye6200.labs.lab2.concreteDerived.Grenade;
import edu.neu.csye6200.labs.lab2.concreteDerived.GunShot;

public class ExplosionModel {
	
	public void triggerExplosion(Explosion explosion) {
		explosion.explode();
	}
	
	public static void demo() {
		System.out.println("\nDemonstrating Polymosphism & Loose Coupling for ExplosionModel");
		ExplosionModel explosionModel = new ExplosionModel();
		Explosion explosionConcrete = new Explosion();
		explosionModel.triggerExplosion(explosionConcrete);
		Explosion gunshotConcrete = new GunShot();
		explosionModel.triggerExplosion(gunshotConcrete);
		Explosion grenadeConcrete = new Grenade();
		explosionModel.triggerExplosion(grenadeConcrete);
		
	}
}
