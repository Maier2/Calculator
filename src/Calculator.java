import java.io.IOException;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        String[] data2 = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};


        while (true) {
            String read = scan.nextLine();
            String[] dataInput = null;

            char sign = 0;                               // сохранение знака
            int[] dataNumber = new int[100];             // дата слогаемых
            int arabSwitch = 0;                          // переключатель на арабский кальк. переключает при показатель 2, если больше выдаёт ошибку большего кличества введенных операций
            int romanSwitch = 0;                         // переключатель на римский кальк. переключает при показатель 2, если больше выдаёт ошибку большего кличества введенных операций
            int exit;                                    // переменная итогового значения


            ////   распознование операци

            if (read.contains("+")) {
                read = read.replace(" ", "");
                dataInput = read.split("\\+");
                sign = '+';
            } else if (read.contains("-")) {
                read = read.replace(" ", "");
                dataInput = read.split("-");
                sign = '-';
            } else if (read.contains("*")) {
                read = read.replace(" ", "");
                dataInput = read.split("\\*");
                sign = '*';
            } else if (read.contains("/")) {
                read = read.replace(" ", "");

                dataInput = read.split("/");
                sign = '/';
            } else {
                System.out.println("ОШИБКА: Строка не является математической операцией");                                                      // ОШИБКА: с введением не матиматической операции ( одна цифра без знака )
                System.exit(0);
            }


            // переключатель калькулятора

            for (int i = 0; i <= dataInput.length - 1; i++) {
                Scanner scan2 = new Scanner(dataInput[i]);
                if (scan2.hasNextInt()) {                                    // определение типа вводимой информации ( араб )
                    arabSwitch++;
                    dataNumber[i] = Integer.parseInt(dataInput[i]);          // перевожу
                } else if (scan2.hasNextLine()) {                             // определение типа вводимой информации  ( рим )
                    romanSwitch++;
                }
            }


            //////// АРАБСКИЙ КАЛЬКУЛЯТОР ::::::::


            if (arabSwitch > 1) {                                                                                                          // 1.0) (Поиск нужного калькулятора), калькулятор арабский. число для переключения - ( 2 )
                if (dataNumber[2] > 0) {                                                                                                   // 1.1) определение количества слогаемых элементов с помощью массива, заполенны нежелательные ячайки
                    System.out.println("ОШИБКА: Формат математической операции не удовлетворяет заданию, колличество операндов превышено. ");    //ОШИБКА: колличество операндов превышено
                    System.exit(0);
                } else if ((dataNumber[0] > 10) || (dataNumber[1] > 10)) {                                                                       //ОШИБКА: число больше 10
                    System.out.println("ОШИБКА: Число является больше 10, математическая операция не удовлетворяет заданию");
                    System.exit(0);


                } else if ((dataNumber[0] <= 10) && (dataNumber[1] <= 10)) {                                                                  // 1.2) вход в араб. калькулятор.
                    if (sign == '+') {
                        exit = dataNumber[0] + dataNumber[1];
                        System.out.println(dataNumber[0] + "+" + dataNumber[1] + "=" + exit);
                    } else if (sign == '-') {
                        exit = dataNumber[0] - dataNumber[1];
                        System.out.println(dataNumber[0] + "-" + dataNumber[1] + "=" + exit);
                    } else if (sign == '*') {
                        exit = dataNumber[0] * dataNumber[1];
                        System.out.println(dataNumber[0] + "*" + dataNumber[1] + "=" + exit);
                    } else if (sign == '/') {
                        if (dataNumber[1] == 0){
                            System.out.println("На ноль делить нельзя!");
                            System.exit(0);
                        }else {
                            exit = dataNumber[0] / dataNumber[1];
                            System.out.println(dataNumber[0] + "/" + dataNumber[1] + "=" + exit);
                        }
                    }
                }


            }
            if ((arabSwitch == 1) && (romanSwitch == 1)) {                                                                               // Ошибка: разные системы счисления
                System.out.println("Ошибка: Используются одновременно разные системы счисления");
                System.exit(0);
            } else if ((arabSwitch == 1) || (romanSwitch == 1) || (dataInput.length <= 1)) {                                             // ОШИБКА: нехватает операнда ( переключатель выбора калькулятора не переключился изза нехватки второго числа )
                System.out.println("Ошибка: не хватает операнда.");
                System.exit(0);


                //////// РИМСКИЙ КАЛЬКУЛЯТОР  ::::::::::

            } else if (romanSwitch > 0) {                                                                                              // 2.0) (Поиск нужного калькулятора), вход в рим. калькулятор
                for (int i = 0; i <= data2.length - 1; i++) {                // 2.1) Инициализация рим. чисел
                    if (dataInput[0].equals(data2[i])) {                     // определяет число, сравнивая его с номером массива
                        dataNumber[0] = i;
                    }
                    if (dataInput[1].equals(data2[i])) {
                        dataNumber[1] = i;
                    }
                }

                if (dataInput.length > 2) {                                                                                                // 2.1) определение количества слогаемых элементов с помощью массива, заполенны нежелательные ячайки
                    System.out.println("ОШИБКА: Формат математической операции не удовлетворяет заданию, колличество операндов превышено. ");
                    System.exit(0);
                } else if ((dataNumber[0] > 10) || (dataNumber[1] > 10)) {                                                                        //ОШИБКА: число больше 10
                    System.out.println("ОШИБКА: Число является больше 10, математическая операция не удовлетворяет заданию");
                    System.exit(0);
                } else if ((arabSwitch <= 1) && (romanSwitch <= 1)) {                                                                               // Ошибка: разные системы счисления
                    System.out.println("Ошибка: Используются одновременно разные системы счисления");
                    System.exit(0);
                } else if ((dataNumber[0] == 0) || (dataNumber[1] == 0)) {
                    System.out.println("Ошибка: Не является матиматической операцией");
                    System.exit(0);


                } else if ((dataNumber[0] <= 10) && (dataNumber[1] <= 10)) {
                    if (sign == '+') {                                                                                                    // 2.2) получены все разрешения на работу калькулятора
                        exit = dataNumber[0] + dataNumber[1];
                        System.out.println(dataInput[0] + "+" + dataInput[1] + "=" + data2[exit]);


                    } else if (sign == '-') {
                        exit = dataNumber[0] - dataNumber[1];
                        if (exit <= 0) {
                            System.out.println("ОШИБКА: В римской системе нет отрицательных чисел");                                              //ОШИБКА: В римской системе нет отрицательных чисел
                            System.exit(0);
                        } else if ((dataNumber[0] >= 0) && (dataNumber[1] >= 0)) {
                            System.out.println(dataInput[0] + "-" + dataInput[1] + "=" + data2[exit]);


                        }
                    } else if (sign == '*') {
                        exit = dataNumber[0] * dataNumber[1];
                        System.out.println(dataInput[0] + "*" + dataInput[1] + "=" + data2[exit]);


                    } else if (sign == '/') {
                        exit = dataNumber[0] / dataNumber[1];
                        if (exit <= 0) {
                            System.out.println("ОШИБКА: В римской системе нет отрицательных чисел");                                              //ОШИБКА: В римской системе нет отрицательных чисел
                            System.exit(0);
                        } else if ((dataNumber[0] >= 0) && (dataNumber[1] >= 0)) {
                            System.out.println(dataInput[0] + "/" + dataInput[1] + "=" + data2[exit]);
                        }
                    }
                }
            }
        }
    }
}


