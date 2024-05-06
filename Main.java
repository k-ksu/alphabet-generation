//Ksenia Korchagina
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfSymbols = scanner.nextInt();
        List<String> alphabet = new ArrayList<>();
        for (int i = 0; i < numberOfSymbols; i++) {
            alphabet.add(scanner.next());
        }
        List<Integer> amounts = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            amounts.add(Integer.parseInt(scanner.next()));
        }
        List<String> first = new ArrayList<>();
        List<String> second = new ArrayList<>();
        List<String> third = new ArrayList<>();
        if (numberOfSymbols == 1) {
            third.add("_");
        }
        List<String> permutations1 = generatePermutations(alphabet, 1, amounts.get(0), amounts.get(1), amounts.get(2), first, second, third);
    }

    public static List<String> generatePermutations(List<String> alphabet, int length, int amount1, int amount2,
                                                    int amount3, List<String> readyPermutations1,
                                                    List<String> readyPermutations2, List<String> readyPermutations3) {
        generate("", alphabet, length, readyPermutations1, readyPermutations2, readyPermutations3);
        if (readyPermutations1.size() >= amount1 && readyPermutations2.size() >= amount2
                && readyPermutations3.size() >= amount3) {
            System.out.print("_");
            for (int k1 = 0; k1 < (amount1 - 1); k1++) {
                System.out.print(" " + readyPermutations1.get(k1));
            }
            System.out.println();

            for (int k2 = 0; k2 < (amount2); k2++) {
                System.out.print(readyPermutations2.get(k2) + " ");
            }
            System.out.println();

            for (int k3 = 0; k3 < (amount3); k3++) {
                System.out.print(readyPermutations3.get(k3) + " ");
            }
            return null;
        } else {
            return generatePermutations(alphabet, length + 1, amount1, amount2, amount3, readyPermutations1,
                    readyPermutations2, readyPermutations3); // Recursive call with updated amount
        }
    }

    private static void generate(String current, List<String> alphabet, int length, List<String> permutations1, List<String> permutations2, List<String> permutations3) {
        if (current.length() == length) {
            int count1 = 0;
            int count2 = 0;
            int count3 = 0;
            for (String letter : alphabet) {
                char myChar = letter.charAt(0);
                int thisCase = countOccurrences(current, myChar);
                if (thisCase % 2 == 1) {
                    count1 = 1;
                }
                if (thisCase == 0) {
                    count2 = 1;
                    count3 += 1;
                }
            }
            if (count1 == 0 && !current.isEmpty()) {
                permutations1.add(current);
            }
            if (count2 == 0) {
                permutations2.add(current);
            }
            if (count3 == 1) {
                permutations3.add(current);
            }
            return;
        }

        for (String letter : alphabet) {
            generate(current + letter, alphabet, length, permutations1, permutations2, permutations3);
        }
    }
    public static int countOccurrences(String word, char target) {
        return (int) word.chars().filter(ch -> ch == target).count();
    }
}