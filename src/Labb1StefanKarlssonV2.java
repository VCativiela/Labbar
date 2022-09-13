import java.util.Arrays;
import java.util.Scanner;


public class Labb1StefanKarlssonV2 {

    static Scanner s = new Scanner(System.in);
    static int choice;

    public static void main(String[] args) {
        String[] timeArray = {"00 - 01", "01 - 02", "02 - 03", "03 - 04", "04 - 05", "05 - 06", "06 - 07", "07 - 08",
                "08 - 09", "09 - 10", "10 - 11", "11 - 12", "12 - 13", "13 - 14", "14 - 15",
                "15 - 16", "16 - 17", "17 - 18", "18 - 19", "19 - 20", "20 - 21", "21 - 22", "22 - 23", "23 - 00"};
        int[] priceInput = new int[24];

        HourlyRate hourlyRate[] = new HourlyRate[priceInput.length];

        boolean checkProgram = true;
        while (checkProgram) {
            StartMenu();

            if (s.hasNext()) {
                choice = s.nextInt();
                if (choice == 1) {
                    System.out.println("Input price for every hour span (full öre):");

                    for (int i = 0; i < priceInput.length; i++) {
                        System.out.print(timeArray[i] + ": ");
                        priceInput[i] = s.nextInt();
                    }
                    TimePriceInput(timeArray, priceInput, hourlyRate);
                } else if (choice == 2) {
                    double sum = 0;
                    double average = GetAverage(hourlyRate, sum);
                    System.out.println("Average: " + average + " öre.");
                    System.out.println("");
                    MinAndMaxAndSort(hourlyRate);
                } else if (choice == 3) {
                    MinAndMaxAndSort(hourlyRate);
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


    private static double GetAverage(HourlyRate[] priceTime, double sumTwo) {
        for (int i = 0; i < priceTime.length; i++) {
            sumTwo += priceTime[i].getPrice();
        }
        return sumTwo / priceTime.length;
    }

    private static void TimePriceInput(String[] timeArray, int[] price, HourlyRate[] priceTime) {
        for (int i = 0; i < price.length; i++) {
            priceTime[i] = new HourlyRate();
            priceTime[i].time = timeArray[i];
            priceTime[i].SetPrice(price[i]);
        }
    }


    public static void MinAndMaxAndSort(HourlyRate[] hourlyRate) {
        boolean sort = true;
        HourlyRate[] hourlyRateCopy = Arrays.copyOf(hourlyRate, hourlyRate.length);
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
        if (choice == 2) {
            for (int i = 0; i < 1; i++) {
                System.out.println("(Lowest) Between: " + hourlyRate[0].time + " the price is " + hourlyRate[0].price + " öre.");
                System.out.println("");
                System.out.println("(Highest) Between: " + hourlyRate[hourlyRate.length - 1].time + " the price is " + hourlyRate[hourlyRate.length - 1].price + " öre.");
                System.out.println("");
            }
        } else if (choice == 3) {
            for (int i = 0; i < hourlyRate.length; i++) {
                System.out.println("Between hours: " + hourlyRateCopy[i].time + " the cost is: " + hourlyRateCopy[i].price + " öre.");
            }
        }
    }

}
