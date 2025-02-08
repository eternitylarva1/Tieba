package Zhenghuo.utils;

import java.util.Random;

public class Calculate {

    // 添加加法方法
    public int add(int a, int b) {
        return a + b;
    }

    // 生成一道100以内的加减法，和20以内乘除法题目的函数
    public static Object[] generateMathQuestion() {
        Random random = new Random();
        int num1, num2, answer;
        char operator;

        int operation = random.nextInt(4); // 0: +, 1: -, 2: *, 3: /

        switch (operation) {
            case 0:
                num1 = random.nextInt(100);
                num2 = random.nextInt(100);
                answer = num1 + num2;
                operator = '+';
                break;
            case 1:
                num1 = random.nextInt(100);
                num2 = random.nextInt(100);
                answer = num1 - num2;
                operator = '-';
                break;
            case 2:
                num1 = random.nextInt(20);
                num2 = random.nextInt(20) + 1; // Ensure num2 is not zero
                answer = num1 * num2;
                operator = '*';
                break;
            case 3:
                num2 = random.nextInt(20) + 1; // Ensure num2 is not zero
                num1 = (num2 * random.nextInt(20)) + 1; // Ensure num1 is a multiple of num2 and not zero
                answer = num1 / num2;
                operator = '/';
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operation);
        }

        return new Object[]{num1, num2, answer, operator};
    }

    // 把数组转化为题目（String）的函数
    public static String convertArrayToQuestion(Object[] questionArray) {
        if (questionArray.length != 4) {
            throw new IllegalArgumentException("Invalid question array");
        }
        int num1 = (int) questionArray[0];
        int num2 = (int) questionArray[1];
        char operator = (char) questionArray[3];
        return num1 + " " + operator + " " + num2 + " = ?";
    }
}
