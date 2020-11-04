package backtobackswe.power_of_4;

public class Solution {
    public boolean powerOfFour(int input) {
        System.out.println("-----");
        System.out.println("Input = " + input + ". Checking if it is power of 4.");
        if (input <= 0) {
            return false;
        }

        if (input == 1 || input == 4) {
            System.out.println("True");
            return true;
        }

        if (((input & (input - 1)) == 0) && ((input & 0x55555555) == input)) {
            System.out.println("True");
            return true;
        } else {
            System.out.println("False");
            return false;
        }
    }

    public static void main(String... args) {
        Solution s = new Solution();
        s.powerOfFour(1);
        s.powerOfFour(2);
        s.powerOfFour(4);
        s.powerOfFour(8);
        s.powerOfFour(16);
        s.powerOfFour(32);
        s.powerOfFour(64);
    }

}
