import java.util.Arrays;
import java.util.Scanner;

public class Labb1StefanKarlssonV2 {

    static Scanner scanner = new Scanner(System.in);

    static String[] timeArray = {"00 - 01", "01 - 02", "02 - 03", "03 - 04", "04 - 05", "05 - 06", "06 - 07", "07 - 08",
            "08 - 09", "09 - 10", "10 - 11", "11 - 12", "12 - 13", "13 - 14", "14 - 15",
            "15 - 16", "16 - 17", "17 - 18", "18 - 19", "19 - 20", "20 - 21", "21 - 22", "22 - 23", "23 - 00"};

    public static void main(String[] args) {
        HourlyRate hourlyRate[] = new HourlyRate[timeArray.length];
        int choice;
        boolean checkProgram = true;
        while (checkProgram) {
            startMenu();

            if (scanner.hasNext()) {
                choice = scanner.nextInt();
                if (choice == 1) {
                    System.out.println("Input price for every hour span (full öre):");

                    for (int i = 0; i < timeArray.length; i++) {
                        System.out.print(timeArray[i] + ": ");
                        hourlyRate[i] = timePriceInput(timeArray[i], scanner.nextInt());
                    }
                } else if (choice == 2) {
                    HourlyRate[] sortedHourlyRate = sortHourlyRateByPrice(hourlyRate);
                    System.out.println("(Lowest) Between: " + sortedHourlyRate[0].getTime() + " the price is " + sortedHourlyRate[0].getPrice() + " öre.");
                    System.out.println("");
                    System.out.println("(Highest) Between: " + sortedHourlyRate[hourlyRate.length - 1].getTime() + " the price is " + sortedHourlyRate[sortedHourlyRate.length - 1].getPrice() + " öre.");
                    System.out.println("");
                    double average = getAverage(hourlyRate);
                    System.out.println("Average: " + average + " öre.");
                    System.out.println("");
                } else if (choice == 3) {
                    HourlyRate[] sortedHourlyRate = sortHourlyRateByPrice(hourlyRate);
                    for (int i = 0; i < hourlyRate.length; i++) {
                        System.out.println("Between hours: " + sortedHourlyRate[i].getTime() + " the cost is: " + sortedHourlyRate[i].getPrice() + " öre.");
                    }
                } else if (choice == 4) {
                    HourlyRate[] bestChargingRange = calculateBestChargingRange(hourlyRate);
                    System.out.println("Best range:");
                    for (int i = 0; i < bestChargingRange.length; i++) {
                        System.out.println("Between hours: " + bestChargingRange[i].getTime() + " the cost is: " + bestChargingRange[i].getPrice() + " öre.");
                    }
                }
            }
        }
    }

    private static void startMenu() {
        System.out.println("Electric prices");
        System.out.println("========");
        System.out.println("1. Input");
        System.out.println("2. Min, Max & Average");
        System.out.println("3. Sort");
        System.out.println("4. Best charging time (4h)");
        System.out.println("e. End");
    }

    private static double getAverage(HourlyRate[] priceTime) {
        double sumTwo = 0;
        for (int i = 0; i < priceTime.length; i++) {
            sumTwo += priceTime[i].getPrice();
        }
        return sumTwo / priceTime.length;
    }

    private static HourlyRate timePriceInput(String timeSpan, int price) {
        HourlyRate priceTime = new HourlyRate();
        priceTime.setTime(timeSpan);
        priceTime.setPrice(price);
        return priceTime;
    }

    private static HourlyRate[] sortHourlyRateByPrice(HourlyRate[] hourlyRate) {
        boolean sort = true;
        HourlyRate[] sortedHourlyRate = Arrays.copyOf(hourlyRate, hourlyRate.length);

        while (sort) {
            sort = false;
            for (int i = 0; i < sortedHourlyRate.length - 1; i++) {
                if (sortedHourlyRate[i].getPrice() > sortedHourlyRate[i + 1].getPrice()) {
                    HourlyRate temp = sortedHourlyRate[i + 1];
                    sortedHourlyRate[i + 1] = sortedHourlyRate[i];
                    sortedHourlyRate[i] = temp;
                    sort = true;
                }
            }
        }

        return sortedHourlyRate;
    }

    private static HourlyRate[] calculateBestChargingRange(HourlyRate[] hourlyRate) {

        HourlyRate[] bestChargingRange = new HourlyRate[4];
        double priceSum = 0;
        int cheapestRangeFirstIndex = 0;

        //Initialize priceSum with the sum of the first range.
        //We will use this as default value to compare with the rest of the ranges
        for (int i = 0; i < 4; i++) {
            priceSum = priceSum + hourlyRate[i].getPrice();
        }

        for (int i = 1; i < hourlyRate.length - 3; i++) {
            double rangeSum = hourlyRate[i].getPrice();

            //Get the sum of the current element and the next 3 elements
            for (int j = i + 1; j < i + 4; j++){
                rangeSum = rangeSum + hourlyRate[j].getPrice();
            }

            if (rangeSum < priceSum){
                priceSum = rangeSum;
                cheapestRangeFirstIndex = i;
            }
        }

        bestChargingRange[0] = hourlyRate[cheapestRangeFirstIndex];
        bestChargingRange[1] = hourlyRate[cheapestRangeFirstIndex + 1];
        bestChargingRange[2] = hourlyRate[cheapestRangeFirstIndex + 2];
        bestChargingRange[3] = hourlyRate[cheapestRangeFirstIndex + 3];

        return bestChargingRange;
    }
}
