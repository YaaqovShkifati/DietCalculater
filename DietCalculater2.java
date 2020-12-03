/**
 * @auther Yaaqov Shkifati
 * @Since 02-20-2020
 * @verison 2.0 
 * Description: In this program, we are creating a calculator that
 * would measure how much the Basal Metabolic Rate(BMR) and the Total Energy
 * Expenditure(TEE). BMR is the total amount of calories that is burnt when a
 * person is asleep and the TEE is the total amount that will approximate
 * physical activity. Our objective in this program is to create a diet
 * calculator that would measure the BMR; by entering the gender, height in feet
 * and inches, age in years, weight in pounds. However, the BMR formula is in
 * measures of the metric- system, so would need first to write a method that
 * would convert weight from pounds to kilograms(kg) and other method from feet
 * and inches to centimeters (cm). Then will write another two methods that
 * would calculate the formulas for BMR and TEE. When writing method TEE we will
 * need to use if statements that would set the conditions of the person
 * activity levels. We are measuring the persons activity level by 3 sets; 1 is
 * light activity (BMR * 1.53), 2 is moderately active (BMR * 1.76), and 3
 * vigorously active (BMR * 2.25). The results would give us the total amount of
 * daily calories person burnt given the conditions of the BMR and TEE.
 *
 * I have chosen to use the trailer method to write this program. This method is
 * effective when you use the a method name while. While is a type of loop which
 * does repeatedly executes a target statement as long as a given condition is
 * true. I have chosen my condition if gender equals the character 'M' which
 * represents male or if character 'F' represents female or when gender does not
 * equal to character 'X' to exit the loop.
 *
 * The most difficult part when writing this program for me was choosing the
 * best way to structure this program. There are soo many different ways in fact
 * i wrote it in two different ways with both resulting the correct output. The
 * first version i had everything read and printed out in my main method inside
 * the while loop but, i realized that main was way too big. What i did in the
 * second version i just printed the out the results at the end of each method.
 * Which gave the same results it just looked a lot better with the main not
 * being soo compacted. For my personal opinion i prefer programs that are more
 * science related programs because not only my skills in programing would
 * improve but, also educates me on that topic.
 *
 */
package dietcalculater2;

import java.util.Scanner;
import java.io.PrintStream;

public class DietCalculater2 {

    public static PrintStream results;

    public static void main(String[] args) throws Exception {
        char gender;
        int age, actLevel, feet, inches;
        double heightCm, weightIbs, weightkg, BMR, TEE;
        boolean isWeightPostive;

        Scanner sc = new Scanner(System.in);
        results = new PrintStream("diet2.txt");

        promptUser();

        gender = sc.next().charAt(0);

        while (gender == 'M' || gender == 'F' || !(gender == 'X')) {

            System.out.print("Enter the patient age:");

            age = sc.nextInt();

            System.out.print("Enter the patient height (in ft and inches):");

            feet = sc.nextInt();
            inches = sc.nextInt();

            System.out.print("Enter the patient weight (in pounds):");

            weightIbs = sc.nextDouble();

            if (weightIbs > 0) {
                isWeightPostive = true;

            } else if (weightIbs < 0) {
                isWeightPostive = false;
                System.out.println(" Please enter a non-negtive weight value");
                weightIbs = sc.nextDouble();
            }

            System.out.print("Enter the patient's activity level( 1 for "
                    + "sedentary 2 for active, 3 for vigorous):");

            actLevel = sc.nextInt();

            
            heightCm = convertTOCM(feet, inches);

            weightkg = convertToKg(weightIbs);

            BMR = bmrCalculator(weightkg, heightCm, age, gender);

            TEE = tEECalculater(BMR, actLevel);
            
           
            if(gender =='M'){
               System.out.println("\nGender: Male");
               results.println("\nGender: Male");
            }
           else if(gender =='F'){
                System.out.println("\nGender:Female");
                results.println("\nGender: Female");
            }

            System.out.printf("Age: %d \n", age);
            results.printf("Age: %d \n", age);

            System.out.printf("Height: %d feet %d inches \n", feet, inches);
            results.printf("Height: %d feet %d inches \n", feet, inches);

            System.out.printf("Weight: %.0f pounds \n", weightIbs);
            results.printf("Weight: %.0f pounds \n", weightIbs);

            System.out.printf("Activity Level: %d %n%n", actLevel);
            results.printf("Activity Level: %d %n%n", actLevel);

            System.out.printf("\nYour BMR is %.1f calories/day.%n", BMR);
            results.printf("Your BMR is %.1f calories/day.%n", BMR);

            System.out.printf("Your TEE is %.1f calories/day.%n%n", TEE);
            results.printf("Your TEE is %.1f calories/day.%n%n", TEE);

            promptUser();

            gender = sc.next().charAt(0);

        }

    }
// End of Main.

    public static void promptUser() {
        System.out.println("BMR/TEE Calculater");
        System.out.print("Enter the patient gender('F' or 'M' - Enter X to"
                + "stop): ");
    }

// This method use to convert the height from feet and inches to centimeters.
    public static double convertTOCM(int feet, int inches) {
        final double CM_TO_INCHES = 2.54;
        double heightIn, heightCm;

        heightIn = feet * 12 + inches;
        heightCm = heightIn * CM_TO_INCHES;

        return heightCm;
    }

// This method is used to convert the weight from pounds(Ibs) to Kilograms(kg).
    public static double convertToKg(double weightIbs) {
        final double KILO_GRAM = 2.205;
        double weightkg;

        weightkg = weightIbs / KILO_GRAM;

        return weightkg;

    }

    /*
     * This method is used to calculate the Basal Metabolic Rate (RMR) the
     * amount calories burned when you are asleep.
     */
    public static double bmrCalculator(double weightkg, double heightCm,
            double age, char gender) {

        double BMR = 0;

        if (gender == 'M') {

            BMR = (10 * weightkg) + (6.25 * heightCm) - (5 * age) + 5;
        } else if (gender == 'F') {

            BMR = (10 * weightkg) + (6.25 * heightCm) - (5 * age) - 161;
        }

        return BMR;
    }

    /* This method is used to calculate Total Energy Expenditure (TEE), by 
     * multiplaying BMR by the activty level. 
     */
    public static double tEECalculater(double BMR, int level) {

        double TEE = 0;

        if (level == 1) {
            TEE = BMR * 1.53;
        } else if (level == 2) {
            TEE = BMR * 1.76;
        } else if (level == 3) {
            TEE = BMR * 2.25;
        }

        return TEE;
    }

}