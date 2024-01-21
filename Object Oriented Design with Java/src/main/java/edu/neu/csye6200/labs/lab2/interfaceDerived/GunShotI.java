package edu.neu.csye6200.labs.lab2.interfaceDerived;

public class GunShotI implements Explodable {

	@Override
	public void explode() {
		System.out.println("GunShotI [** BANG **] !!!");
	}

}
