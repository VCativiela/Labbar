import java.util.Scanner;


public class Labb1StefanKarlssonV2 {

    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
//array för att deklarera de olika tidsperioderna:
        String[] timeArray = {"00 - 01", "01 - 02", "02 - 03", "03 - 04", "04 - 05", "05 - 06", "06 - 07", "07 - 08",
                "08 - 09", "09 - 10", "10 - 11", "11 - 12", "12 - 13", "13 - 14", "14 - 15",
                "15 - 16", "16 - 17", "17 - 18", "18 - 19", "19 - 20", "20 - 21", "21 - 22", "22 - 23", "23 - 00"};
//array för att lagra priserna
        int[] priceInput = new int[24];

        HourlyRate hourlyRate[] = new HourlyRate[priceInput.length];
        Object[] tidOchPris = {timeArray, priceInput};


        boolean checkProgram = true;

        while (checkProgram) {
            boolean checkProgramTwo = true;
//metod för startmeny
            StartMenu();

            if (s.hasNext()) {
                int choice = s.nextInt();
//if statement för inmatning 1:
                if (choice == 1) {
                    System.out.println("Input price for every hour span (full öre):");

                    for (int i = 0; i < priceInput.length; i++) {
                        System.out.print(timeArray[i] + ": ");
                        priceInput[i] = s.nextInt();
                    }

                    // HÄR SKA VI MATA IN CLASS SOM innefattar array med både PRIS & TID!

                    TimePriceInput(timeArray, priceInput, hourlyRate);


//else if statement för inmatning 2:
                } else if (choice == 2) {

                    while (checkProgramTwo) {
                        checkProgramTwo = true;
//metod för att få fram meny under input 2:
                        MenuTwo();

                        int choiceMenuTwo = s.nextInt();
                        double sum = 0;

                        if (choiceMenuTwo == 1) {
                            double average = GetAverage(hourlyRate, sum);
                            System.out.println("Average price: " + average + " öre.");
                            System.out.println("");

                        } else if (choiceMenuTwo == 2) {
                            MinAndMax(priceInput);
                            int minPrice = priceInput[0];
                            System.out.println("Lowest price: " + minPrice + " öre.");
                        } else if (choiceMenuTwo == 3) {
                            MinAndMax(priceInput);
                            int maxPrice = priceInput[23];
                            System.out.println("Highest price: " + maxPrice + " öre.");
                        } else {

                        }


                    }
                }

            }
        }


    }

    private static void StartMenu() {
        System.out.println("Electric prices");
        System.out.println("========");
        System.out.println("1. Input");
        System.out.println("2. Min, Max & Average");
        System.out.println("3. Sort");
        System.out.println("4. Best charging time (4h)");
        System.out.println("e. End");
    }
    private static void MenuTwo() {
        System.out.println("Min, Max & Average");
        System.out.println("==================");
        System.out.println("1. Average:");
        System.out.println("2. Lowest:");
        System.out.println("3. Highest:");
        System.out.println("4. Return:");
    }

    //metod för att få medelvärde
    private static double GetAverage(HourlyRate[] priceTime, double sumTwo) {
        for (int i = 0; i < priceTime.length; i++) {
            sumTwo += priceTime[i].getPrice();
        }
        double average = sumTwo / priceTime.length;
        return average;
    }

    //metod för att hålla ihop tid & pris
    private static void TimePriceInput(String[] timeArray, int[] price, HourlyRate[] priceTime) {
        for (int i = 0; i < price.length; i++) {
            priceTime[i] = new HourlyRate(price[i], timeArray[i]);
//            priceTime[i].time = timeArray[i];
//            priceTime[i].setPrice(price[i]);

        }
    }


    //bubble sort för tiden
    public static void TimeSort(HourlyRate hourlyRate[]) {
        HourlyRate[] hourlyRateTwo = hourlyRate.clone();

        boolean sortTime = true;

        while (sortTime) {
            sortTime = false;
            for (int i = 0; i < hourlyRateTwo.length - 1; i++) {
                if (hourlyRateTwo[i].getPrice() > hourlyRateTwo[i + 1].getPrice()) {
                    HourlyRate temp = hourlyRateTwo[i + 1];
                    hourlyRateTwo[i + 1] = hourlyRateTwo[i];
                    hourlyRate[i] = temp;
                    sortTime = true;

                }
            }
        }
    }

    //bubble sort för min & max pris
    public static int[] MinAndMax(int[] price) {
        boolean sortingBubble = true;

        while (sortingBubble) {
            sortingBubble = false;
            for (int i = 0; i < price.length - 1; i++) {
                if (price[i] > price[i + 1]) {
                    int temp = price[i + 1];
                    price[i + 1] = price[i];
                    price[i] = temp;
                    sortingBubble = true;
                }
            }
        }
        return price;
    }
}
