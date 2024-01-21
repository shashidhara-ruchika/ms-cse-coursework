package edu.neu.csye6200.assignments.assignment5.factory;

import edu.neu.csye6200.assignments.assignment5.entity.Item;
import edu.neu.csye6200.assignments.assignment5.entity.ServiceItem;

public class ServiceItemFactory extends AbstractItemFactory {
	
	private static final ServiceItemFactory factoryInstance = new ServiceItemFactory();
	
	private ServiceItemFactory() {
		
	}
	
	public static ServiceItemFactory getInstance() {
		return factoryInstance;
	}
	
	
	public ServiceItem getServiceItem(String csvString) {
		return new ServiceItem(csvString);
	}
	
	@Override
	public Item getItem(String csvString) {
		ServiceItem serviceItem = getServiceItem(csvString);
		System.out.print(serviceItem + "\tobject returned from Service Item Factory");
		return getServiceItem(csvString);
	}

}
