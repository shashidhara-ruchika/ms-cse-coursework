package edu.neu.csye6200.labs.lab2.interfaceDerived;

public class GrenadeI implements Explodable {

	@Override
	public void explode() {
		System.out.println("GrenadeI [** SPLATTER **] !!!");
	}

}
