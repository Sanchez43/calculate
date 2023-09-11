import javax.xml.transform.Result;
import java.util.prefs.BackingStoreException;

public class Calculator {
    public static String calc(String input) {
        String[] actions = {"+", "-", "/", "*"};
        String[] splitActions = {"\\+", "-", "/", "\\*"};

        String expression = input;
        int actionIndex = -1;
        String expressionTwo = expression.replace(" ", "");
        if (expressionTwo.isEmpty()) {
            throw new RuntimeException("Пустая строка");
        }
        String[] expressionArray = expressionTwo.split("");
        int count = 0;
        for (int i = 0; i < actions.length; i++) {
            for (int j = 0; j < expressionArray.length; j++) {
                if (actions[i].equals(expressionArray[j])) {
                    count += 1;
                }
            }
        }
        if(count>1){
            throw new RuntimeException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }else{
            for(int i = 0; i<actions.length;i++ ){
                if(expressionTwo.contains(actions[i])){
                    actionIndex = i;
                    break;
                }
            }
            if(actionIndex == -1) {
                throw new RuntimeException("Неккоректное арифметическое действие");
            }


            Convertor convertor = new Convertor();
            String[] data = expressionTwo.split(splitActions[actionIndex]);
            if(convertor.isRoman(data[0]) == convertor.isRoman(data[1])){
                int number1,number2;
                boolean isRomanNumber = convertor.isRoman(data[0]);
                if(isRomanNumber){
                    number1 = convertor.romanToInt(data[0]);
                    number2= convertor.romanToInt(data[1]);
                    if ((number1>10) || (number2 > 10)) {
                        throw new RuntimeException("Вводимые числа не должны быть больше 10(X)");
                    }
                }else{
                    try{
                    number2 = Integer.parseInt(data[1]);
                    number1 = Integer.parseInt(data[0]);
                    } catch (NumberFormatException e){
                        throw new NumberFormatException("Разрешено вводить только целые числа");
                    }
                    if((number1>10) || (number2 > 10)){
                        throw new RuntimeException("Вводимые числа не должны быть больше 10(X)");
                    }
                }

                int result = 0;
                switch (actions[actionIndex]){
                    case "+":
                        result = number1+number2;
                        break;
                    case "-":
                        if (number1<number2){
                        throw new RuntimeException("Римские чила не могут быть отрицательные");
                    }
                        result = number1 - number2;
                        break;
                    case "*":
                        result = number1*number2;
                        break;
                    case "/":
                        result = number1/number2;
                        break;
                }

                if(isRomanNumber){
                    input = convertor.intToRoman(result);

                }else {
                    input = Integer.toString(result);
                }
            }else {
                throw new RuntimeException("Вводимые числа должны быть одинаковой системы счисления");
            }
        }




        return input;
    }
}
