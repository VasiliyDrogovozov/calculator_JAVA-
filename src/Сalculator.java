import java.util.Scanner;

public class Сalculator {
    public static void main(String[] args) {
        System.out.println("Введите пример:");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        System.out.println(Main.calc(userInput));
    }
}




class Main {

    static Scanner scanner = new Scanner(System.in);
    static String operation;
    static int number1, number2;
    static String[] arrNums;
    static boolean flagNum = true;
    static String answer;

    public static String calc(String input) {
        try{
            checkOnEmpty(input);

            hasOneNum(input);
            chackingIntegers(input);

            arrNums = input.split("[+-/*]");
            ruleOperand(arrNums);
            checkingOperand(input);

            beRoman(arrNums);
            if (flagNum) {
                int res = calculated(number1, number2, operation);
                try{
                    String r = convertNumToRoman(res);
                    answer = r;
                } catch (ArrayIndexOutOfBoundsException e) {
                    answer = "В римской системе нет отрицательных чисел!";
                }
            } else {
                try {
                    number1 = Integer.parseInt(arrNums[0]);
                    number2 = Integer.parseInt(arrNums[1]);
                    if((number1 > 10 || number1 < 0) || (number2 > 10 || number2 < 0)) {
                        try{
                            throw  new IllegalArgumentException();
                        } catch (IllegalArgumentException e) {
                            answer = "Калькулятор принимает на вход числа от 1 до 10 включительно, не более.";
                        }
                    } else {
                        answer = Integer.toString(calculated(number1, number2, operation));
                    }
                } catch (IllegalArgumentException e) {
                    answer = "Одновременно используются разные системы счисления";
                } catch (OperendCalc e) {
                    answer = e.getM();
                }
            }

        } catch (IllegalArgumentException e) {
            answer = "Error";
        } catch (ExRuleOperand e) {
            answer = e.getM();
        } catch (ExhasOneNum e) {
            answer = e.getM();
        } catch (OperendCalc e) {
            answer = e.getM();
        } catch (RuleZnak e) {
            answer = e.getMes();
        } catch (EnyEx e) {
            answer = e.getMes();
        } catch (StringEmpty e) {
            answer = e.getMes();
        }
        return answer;
    }



    public static void checkOnEmpty(String input) throws StringEmpty {
        if(input.isEmpty()) {
            throw new StringEmpty();
        }
    }



    public static void beRoman(String[] arrN) {
        try {
            number1 = Integer.parseInt(Arab3.valueOf(arrN[0]).getTransNum());
            number2 = Integer.parseInt(Arab3.valueOf(arrN[1]).getTransNum());

        } catch (IllegalArgumentException e) {
            flagNum = false;
        }
    }
    public static void chackingIntegers(String input) throws EnyEx {
        if(input.contains(".")) {
            throw new EnyEx();
        }
    }
    public static void checkingOperand(String input) throws RuleZnak {
        if(input.contains("+")) {
            operation = "+";
        } else if(input.contains("-")) {
            operation = "-";
        } else if(input.contains("/")) {
            operation = "/";
        } else if(input.contains("*")) {
            operation = "*";
        } else {
            throw new RuleZnak();
        }
    }

    private static void hasOneNum(String input) throws ExhasOneNum {
        if(input.length() == 1) {
            throw new ExhasOneNum();
        }
    }

    private static void ruleOperand(String[] arrNums) throws ExRuleOperand {
        if(arrNums.length > 2) {
            throw new ExRuleOperand();
        }
    }
    private static String convertNumToRoman (int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String s = roman[numArabian];
        return s;
    }

    public static int calculated(int num1, int num2, String op) throws OperendCalc {
        int result = 0;
        switch (op) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                throw new OperendCalc();
        }
        return result;
    }

}
enum Arab3 {
    I("1"), II("2"), III("3"), IV("4"), V("5"), VI("6"), VII("7"), VIII("8"), IX("9"), X("10");

    private String num;

    Arab3(String n) {

        this.num = n;
    }

    public String getTransNum() {
        return num;
    }
}