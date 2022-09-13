import java.util.Arrays;
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
                    double sum = 0;
                    double average = GetAverage(hourlyRate, sum);
                    System.out.println("Average: " + average + " öre.");
                    System.out.println("");
                    MinAndMax(hourlyRate);
                } else if (choice == 3) {

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



    //metod för att få medelvärde
    private static double GetAverage(HourlyRate[] priceTime, double sumTwo) {
        for (int i = 0; i < priceTime.length; i++) {
            sumTwo += priceTime[i].getPrice();
        }
        return sumTwo / priceTime.length;
    }

    //metod för att hålla ihop tid & pris
    private static void TimePriceInput(String[] timeArray, int[] price, HourlyRate[] priceTime) {
        for (int i = 0; i < price.length; i++) {
            priceTime[i] = new HourlyRate();
            priceTime[i].time = timeArray[i];
            priceTime[i].SetPrice(price[i]);
        }
    }

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

    //sort för min & max pris
    public static void MinAndMax(HourlyRate[] hourlyRate) {
        boolean sort = true;

        while (sort) {
            sort = false;
            for (int i = 0; i < hourlyRate.length - 1; i++) {
                if (hourlyRate[i].getPrice() > hourlyRate[i + 1].getPrice()) {
                    HourlyRate temp = hourlyRate[i + 1];
                    hourlyRate[i + 1] = hourlyRate[i];
                    hourlyRate[i] = temp;
                    sort = true;
                }
            }
        }
        for (int i = 0; i <1 ; i++) {
            System.out.println("(Lowest) Between: " + hourlyRate[0].time + " the price is " + hourlyRate[0].price +  " öre.");
            System.out.println("");
            System.out.println("(Highest) Between: " + hourlyRate[hourlyRate.length-1].time + " the price is " + hourlyRate[hourlyRate.length-1].price +  " öre.");
            System.out.println("");


        }
    }
    public static void ArraySorter(HourlyRate[] hourlyRate) {

        HourlyRate[] hourlyRateCopy = Arrays.copyOf(hourlyRate, hourlyRate.length)
        boolean sort = true;

        while (sort) {
            sort = false;
            for (int i = 0; i < hourlyRate.length - 1; i++) {
                if (hourlyRateCopy[i].getPrice() > hourlyRateCopy[i + 1].getPrice()) {
                    HourlyRate temp = hourlyRateCopy[i + 1];
                    hourlyRateCopy[i + 1] = hourlyRateCopy[i];
                    hourlyRateCopy[i] = temp;
                    sort = true;
                }
            }
            for (int i = 0; i < hourlyRateCopy.length; i++) {
                System.out.println("Between hours: " + hourlyRateCopy[i].time + "the cost is: " + hourlyRateCopy[i].price + " öre.");
            }
        }

}
