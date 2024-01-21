package edu.neu.csye6200.exams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Final6200Fall2023 {
    public static final int MAJOR = 2;
    public static final int MINOR = 1;
    public static final String VERSION = MAJOR + "." + MINOR;

    private String[] a = {
            "2,1.99,Skim Milk",
            "4,2.99,Tropicana OJ",
            "3,1.49,Whole Wheat Bread",
            "1,0.99,Candy Corn"
    };

    private interface ItemAPI {
        int getId();
        void setId(int id);
        double getPrice();
        void setPrice(double price);
        String getName();
        void setName(String name);
        String toCSVString();
    }

    static class ConvertUtil {
        static int convertToInteger(String value) {
        	try {
        		return Integer.parseInt(value.trim());
        	} catch(Exception e) {
        		e.printStackTrace();
        		return 0;
        	}
        }

        static double convertToDouble(String value) {
        	try {
        		return Double.parseDouble(value.trim());
	        } catch(Exception e) {
	    		e.printStackTrace();
	    		return 0;
	    	}
        }
    }

    interface ItemFactoryAPI {
        ItemAPI createItem(String csvString);
    }

    public static class ItemFactory implements ItemFactoryAPI {
        @Override
        public ItemAPI createItem(String csvString) {
            String[] parts = csvString.split(",");
            int id = ConvertUtil.convertToInteger(parts[0]);
            double price = ConvertUtil.convertToDouble(parts[1]);
            String name = parts[2].trim();
            return new Item(id, price, name);
        }
    }

    public static enum ItemEnumSingletonFactory implements ItemFactoryAPI {
		INSTANCE {
			@Override
			public ItemAPI createItem(String itemCSVData) {
				String[] parts = itemCSVData.split(",");
	            int id = ConvertUtil.convertToInteger(parts[0]);
	            double price = ConvertUtil.convertToDouble(parts[1]);
	            String name = parts[2].trim();
	            return new Item(id, price, name);
			}
		}

	}

    public static class Item implements ItemAPI, Comparable<Item> {
        private int id;
        private double price;
        private String name;

        public Item(int id, double price, String name) {
            this.id = id;
            this.price = price;
            this.name = name;
        }

        @Override
        public int compareTo(Item other) {
            return Double.compare(other.price, this.price);
        }

        @Override
        public int getId() {
            return id;
        }

        @Override
        public void setId(int id) {
            this.id = id;
        }

        @Override
        public double getPrice() {
            return price;
        }

        @Override
        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toCSVString() {
            return String.format("%d,%.2f,%s", id, price, name);
        }

        @Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("\nItem ");
			sb.append("# ").append(id);
			sb.append(" $ ").append(price);
			sb.append(" ").append(name);
			return sb.toString();
		}
    }
    
    public void demoStream() {
		System.out.println("\n\t" + Final6200Fall2023.class.getName() + ".demoStream() ... ");

		{
			System.out.println("\n\t" + "Ia (5 POINTS): Stream CSV Strings to SHOW ORIGINAL CSV Strings");
 
			System.out.println(Arrays.stream(a).collect(Collectors.joining("\n")));

			System.out.println();
		}
		{
			System.out.println("\n\t" + "Ib (5 POINTS): Stream CSV Strings to Sort and SHOW CSV Strings");
			System.out.println(Arrays.stream(a)
	                .sorted()
	                .collect(Collectors.joining("\n")));

			System.out.println();
		}

		{
			System.out.println("\n\t" + "II (10 POINTS): Stream CSV Strings to produce Item ");
			System.out.println(a.length + " items");
			ItemFactory itemFactory = new ItemFactory();
			System.out.println(Arrays.stream(a)
	                .map(itemFactory::createItem)
	                .collect(Collectors.toList()));

			System.out.println();
		}

		{
			System.out.println("\n\t" + "III (10 POINTS): Stream CSV Strings to produce Item with Singleton Factory ");
			System.out.println(a.length + " items");
			
			System.out.println(Arrays.stream(a)
	                .map(ItemEnumSingletonFactory.INSTANCE::createItem)
	                .collect(Collectors.toList()));

			System.out.println();
		}

		{
			System.out.println("\n\t" + "IV (10 POINTS): Stream SORTED CSV Strings to produce Item  ");
			System.out.println(a.length + " items");
			ItemFactory itemFactory = new ItemFactory();
			System.out.println(Arrays.stream(a)
	                .sorted()
	                .map(itemFactory::createItem)
	                .collect(Collectors.toList()));

			System.out.println();
		}

		{
			System.out.println("\n\t" + "V (10 POINTS): Stream CSV Strings to produce SORTED Item List ");
			System.out.println(a.length + " items");
			ItemFactory itemFactory = new ItemFactory();
			List<ItemAPI> itemList = Arrays.stream(a)
	                .map(itemFactory::createItem)
	                .sorted()
	                .collect(Collectors.toList());
	        itemList.forEach(System.out::println);

			System.out.println();
		}

		System.out.println("\n\t" + Final6200Fall2023.class.getName() + ".demoStream() ... done!");
	}

    public static void demo() {
        System.out.println("\n\t" + Final6200Fall2023.class.getName() + ".demo() version ["+VERSION+"]... ");

        Final6200Fall2023 obj = new Final6200Fall2023();
        obj.demoStream();
        System.out.println("\n\t" + Final6200Fall2023.class.getName() + ".demo() version ["+VERSION+"]... done!");
    }

    
}
