package edu.neu.csye6200.exams;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;
/**
 * 100 TOTAL POINTS
 * PeriodicTable including solid, liquid and gaseous elements in nature
 *
 * https://pubchem.ncbi.nlm.nih.gov/periodic-table/
 *
 * 30 POINTS DEDUCTED IF SUBMITTED LATE OR INCORRECT.
 *
 * Given PeriodicTable Outer class code fragment,
 * COMPLETE demo method and COMPLETE THE DEVELOPMENT the following:
 *
 * 		20 POINTS:inner class Element implements inner interface ElementAPI,
 * 		15 POINTS:inner class ElementFactory implements inner interface ElementFactoryAPI,
 * 		15 POINTS:inner class ElementFactory object is returned by ElementEagerSingletonFactory
 * 		15 POINTS:inner class ElementFactory object is returned by ElementLazySingletonFactory
 *
 *
 * 35 POINTS: Sort all elements in PeriodicTable Outer class
 * by DEFAULT ORDER (PRICE), Atomic Number, Symbol, Name and Description
 *
 * MUST Use Lambda for sort, foreEach methods.
 *
 * @author dpeters
 *
 */
public class PeriodicTableMidTermsolution {
    private List<String> elementCSVList = null;
    private List<ElementAPI> elementList = null;
    private List<ElementFactoryAPI> factoryList = null;
    /**
     * object initialization block
     * executed with each constructor
     */
    {
        elementCSVList = new ArrayList<>(Arrays.asList(
                "2,3.49,Helium,He,NOBEL GAS",
                "1,3.99,Hydrogen,H,NONMETAL GAS",
                "80,72.99,Mercury,Hg,METAL LIQUID",
                "8,0.99,Oxygen,O,NONMETAL GAS",
                "7,0.49,Nitrogen,N,NONMETAL GAS",
                "47,89.99,Silver,Ag,METAL SOLID",
                "35,5.99,Bromine,Br,HALOGEN LIQUID",
                "29,55.99,Copper,Cu,METAL SOLID",
                "4,12.99,Potassium,K,ALKALI METAL SOLID",
                "26,42.99,Iron,Fe,METAL SOLID",
                "3,32.99,Lithium,Li,ALKALI METAL SOLID",
                "11,0.49,Sodium,Na,ALKALI METAL SOLID",
                "6,14.99,Carbon,C,NONMETAL SOLID",
                "9,2.49,Flourine,F,HALOGEN GAS",
                "17,1.29,Chlorine,Cl,HALOGEN GAS",
                "79,99.99,Gold,Au,METAL SOLID"
        ));
        elementList = new ArrayList<>();
        factoryList = new ArrayList<>();
    }

    /**
     * TODO: method to add ElementAPI object to PeriodicTable
     * @param b	ElementAPI object
     */

    public void add (ElementAPI b)
    {
        this.elementList.add(b);
    }

    /**
     * TODO: method to add ElementFactoryAPI object to PeriodicTable
     * @param f		ElementFactoryAPI object
     */
    public void add (ElementFactoryAPI f)
    {
        this.factoryList.add(f);
    }

    /**
     * use supplied ElementFactoryAPI factory to create ElementAPI objects
     * @param n		number of ElementAPI objects to create using each factory
     */
    public void create(int n)
    {
        for (ElementFactoryAPI f : factoryList)
        {
            for (String csv : elementCSVList)
            {
                int i = n;
                while (i-- > 0) {
                    this.add(f.getObject(csv));
                }
            }
        }
    }

    /**
     * TODO: method to sort all elements in PeriodicTable
     * @param c	Comparator
     */

    public void sort(Comparator<ElementAPI> c)
    {
        this.elementList.sort(c);
    }

    /**
     * TODO: method to return a String representation of the PeriodicTable state
     * MUST USE LAMBDA and List forEach method
     */
    @Override
    public String toString() {
        return "PeriodicTableMidTermSolution{" +
                "elementList=" + elementList +
                "}\n";
    }
    /**
     * TODO: inner class ConvertUtil used to convert String representations of numbers
     * with exception handling for INVALID String representation
     *
     * @author dpeters
     *
     */

    public static  class ConvertUtil
    {
        public static int convertToInt(String number)
        {
            try {
                return Integer.parseInt(number);
            }
            catch (NumberFormatException e) {
                return 0; //handling exceptions for INVALID String representation
            }
        }
    }

    /**
     * TODO: Element Factory API inner interface
     */
    public interface ElementFactoryAPI {


         ElementAPI getObject(String csvString);
    }


    /**
     * TODO: ElementFactory inner class creates Element
     */

    public static class ElementFactory implements ElementFactoryAPI
    {

        @Override
        public ElementAPI getObject(String csvString) {

            // "2,3.49,Helium,He,NOBEL GAS",
            String[] data = csvString.split(",");

            // default values of all attributes
            int atomicNumber = 0;
            double price = 0;
            String name = "";
            String symbol = "";
            String description = "";

            // Parsing the csv string to get values of all attributes.
            try {


                atomicNumber = ConvertUtil.convertToInt(data[0]);
                price = Double.parseDouble(data[1]);
                name = data[2];
                symbol = data[3];
                description = data[4];

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

           return new Element( atomicNumber,  price,  name,  symbol,  description);
        }
    }


    /**
     * TODO: ElementEagerSingletonFactory inner class creates Element factory
     */

    public static class ElementEagerSingletonFactory  {

        private static ElementFactoryAPI instance = new ElementFactory(); //Eager singleton

        private ElementEagerSingletonFactory() {}


        /**
         * @return the single instance .
         */
        public static synchronized ElementFactoryAPI getInstance() {
            return instance;
        }

    }


    /**
     * TODO: ElementLazySingletonFactory inner class creates Element factory
     */
    public static class ElementLazySingletonFactory  {

        private static ElementFactoryAPI instance = null; //Eager singleton

        public ElementLazySingletonFactory() {


        }


        /**
         * @return the single instance .
         */
        public static synchronized ElementFactoryAPI getInstance() {

            if (instance == null )
                instance =  new ElementFactory();
            return instance;
        }

    }


    /**
     * TODO: inner interface ElementAPI is API Element class implements
     * @author dpeters
     *
     */
    private interface ElementAPI {
        final static DecimalFormat f = new DecimalFormat("###0.00"); // 0=ALWAYS,#=OPTIONAL
        /**
         * TODO: API
         */

        int getAtomicNumber();
        String getSymbol();
        double getPrice();
        String getName();
        String getDescription();

        /**
         * @return String representation of Element object
         */
        default String myStringState() {
            StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());

            sb.append(String.format(" # %3d", getAtomicNumber()));
            sb.append(String.format(" %2s", getSymbol()));
            sb.append(" $ ").append(f.format(getPrice()));
            sb.append(String.format(" %10s", getName()));
            sb.append(String.format(" %20s", getDescription()));
            sb.append("\n");

            return sb.toString();
        }
    }

    /**
     * TODO: Element inner class is an ElementAPI item
     *
     * @author dpeters
     *
     */
    private static class Element implements ElementAPI, Comparable<Element>{

        int atomicNumber;
        double price ;
        String name ;


        String symbol;
        String description ;

        public Element(int atomicNumber, double weight, String name, String symbol, String description) {
            this.atomicNumber = atomicNumber;
            this.price = weight;
            this.name = name;
            this.symbol = symbol;
            this.description = description;
        }


        public int getAtomicNumber() {
            return atomicNumber;
        }

        public void setAtomicNumber(int atomicNumber) {
            this.atomicNumber = atomicNumber;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }



        @Override
        public String toString() {
            return myStringState();
        }

        @Override
        public int compareTo(Element o) {
            return Double.compare(getPrice(), o.getPrice());
        }
    }

    /**
     * demonstrate the use of this PeriodicTable class
     */
    public static void demo() {
        System.out.println("\n\t" + PeriodicTableMidTermsolution.class.getName() + ".demo()...");

        PeriodicTableMidTermsolution pTable = new PeriodicTableMidTermsolution();
        System.out.println(pTable);

        /**
         * TODO: add factories using Singletons (Eager and Lazy implementations)
         */
        pTable.add(ElementEagerSingletonFactory.getInstance());
        pTable.add(ElementLazySingletonFactory.getInstance());


        /**
         * create elements using added factories
         */
        pTable.create(1);
        System.out.println("created elements...");
        System.out.println(pTable);
        System.out.println();

        /**
         * TODO: Sort elements
         */
        System.out.println("SORT PeriodicTable elements in DEFAULT order...");
        pTable.sort(null);
        System.out.println(pTable);

        System.out.println("SORT PeriodicTable elements by AtomicNumber...");
        Comparator<ElementAPI> c = (a,b) -> Integer.compare(a.getAtomicNumber(), b.getAtomicNumber());
        pTable.sort(c);
        System.out.println(pTable);

        System.out.println("SORT PeriodicTable elements by Symbol...");
        c = (a,b) -> CharSequence.compare(a.getSymbol(), b.getSymbol());
        pTable.sort(c);
        System.out.println(pTable);

        System.out.println("PeriodicTable Book elements by NAME...");
        c = (a,b) -> CharSequence.compare(a.getName(), b.getName());
        pTable.sort(c);
        System.out.println(pTable);

        System.out.println("PeriodicTable Book elements by DESCRIPTION...");
        c = (a,b) -> CharSequence.compare(a.getDescription(), b.getDescription());
        pTable.sort(c);
        System.out.println(pTable);

        System.out.println("\n\t" + PeriodicTableMidTermsolution.class.getName() + ".demo()... done!");
    }

    
        
    }
