package edu.neu.csye6200.labs.lab2;

/**
 * 
 * @author Ruchika Sharma
 * 
 */
/*
 * ** Everytime a new GitHub repo is created, do not use the existing repo until informed
 

 LAB Exercise: (to be completed and demonstrated as an assignment)

1. Create THREE APIs:

a. Explosion concrete class, b. AbstractExplosion abstract class, c. Explodable interface;

2. Derive TWO classes from EACH API (inheritance):

a. GunShot and Grenade, GunShotA and GrenadeA, GunShotI and GrenadeI;

NOTE: EACH OF THESE CLASSES MUST ALSO OPERATE IDENTICALLY (identical console output) WHEN INVOKED USING THE Object API.

3. Create THREE models to demonstrate polymorphism and loose coupling:

a. ExplosionModel, AbstractExplosionModel, ExplodableModel;

4.Create a FOURTH model, ExplosionObjectModel, 
to demonstrate that ALL of your explosion objects created from ALL explosion classes 
(derived from your THREE APIs i.e., GunShot and Grenade, GunShotA and GrenadeA, GunShotI and GrenadeI) 
ALSO work with the Object API
 */

public class Driver {
	public static void main(String[] args) {
		System.out.println("============Main Execution Start===================\n\n");
		
		ExplosionObjectModel.demo();

		System.out.println("\n\n============Main Execution End===================");
	}

}
