package edu.neu.csye6200.labs.lab2;

import edu.neu.csye6200.labs.lab2.abstractDerived.AbstractExplosion;
import edu.neu.csye6200.labs.lab2.abstractDerived.GrenadeA;
import edu.neu.csye6200.labs.lab2.abstractDerived.GunShotA;
import edu.neu.csye6200.labs.lab2.concreteDerived.Explosion;
import edu.neu.csye6200.labs.lab2.concreteDerived.Grenade;
import edu.neu.csye6200.labs.lab2.concreteDerived.GunShot;
import edu.neu.csye6200.labs.lab2.interfaceDerived.Explodable;
import edu.neu.csye6200.labs.lab2.interfaceDerived.GrenadeI;
import edu.neu.csye6200.labs.lab2.interfaceDerived.GunShotI;

public class ExplosionObjectModel {
	
	public void triggerExplosion(Object explosion) {
		if (explosion instanceof Explosion) {
            ((Explosion) explosion).explode();
        } else if (explosion instanceof AbstractExplosion) {
            ((AbstractExplosion) explosion).explode();
        } else if (explosion instanceof Explodable) {
            ((Explodable) explosion).explode();
        } else {
            System.out.println("Unknown explosion type!");
        }
	}
	
	public static void demo() {
		
		ExplosionModel.demo();
		
		AbstractExplosionModel.demo();
		
		ExplodableModel.demo();		
		
		System.out.println("\nDemonstrating Polymorphism & Loose Coupling for ExplosionObjectModel");
		ExplosionObjectModel explosionObjectModel = new ExplosionObjectModel();
		Object explosion = new Explosion();
		explosionObjectModel.triggerExplosion(explosion);
		Object gunShot = new GunShot();
		explosionObjectModel.triggerExplosion(gunShot);
		Object grenade = new Grenade();
		explosionObjectModel.triggerExplosion(grenade);
		Object gunshotA = new GunShotA();
		explosionObjectModel.triggerExplosion(gunshotA);
		Object grenadeA = new GrenadeA();
		explosionObjectModel.triggerExplosion(grenadeA);
		Object gunshotI = new GunShotI();
		explosionObjectModel.triggerExplosion(gunshotI);
		Object grenadeI = new GrenadeI();
		explosionObjectModel.triggerExplosion(grenadeI);
	}
	

}
