
package dietcalculater2;
*
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
