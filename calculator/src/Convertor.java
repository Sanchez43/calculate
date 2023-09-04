import java.util.TreeMap;
public class Convertor {
    TreeMap<Character, Integer> romanKeyMap = new TreeMap<>();
    TreeMap<Integer, String> arabianKeyMap = new TreeMap<>();
    public Convertor(){
        romanKeyMap.put('I', 1);
        romanKeyMap.put('V', 5);
        romanKeyMap.put('X', 10);
        romanKeyMap.put('L', 50);
        romanKeyMap.put('C', 100);

        arabianKeyMap.put(100, "C");
        arabianKeyMap.put(90, "XC");
        arabianKeyMap.put(50, "L");
        arabianKeyMap.put(40, "XL");
        arabianKeyMap.put(10, "X");
        arabianKeyMap.put(9, "IX");
        arabianKeyMap.put(5, "V");
        arabianKeyMap.put(4, "IV");
        arabianKeyMap.put(1, "I");
    }

    public boolean isRoman(String number){
        return romanKeyMap.containsKey(number.charAt(0));
    }

    public String intToRoman(int number){
        String roman = "";
        int arabianKey;
        do {
            arabianKey = arabianKeyMap.floorKey(number);
            roman += arabianKeyMap.get(arabianKey);
            number -= arabianKey;
        }while(number!= 0);
        return roman;
    }

    public int romanToInt(String number){
        int lastSymbal = number.length()-1;
        char[] array = number.toCharArray();
        int arabian;
        int result = romanKeyMap.get(array[lastSymbal]);
        for(int i = lastSymbal - 1;i>=0;i--){
            arabian = romanKeyMap.get(array[i]);
            if(arabian< romanKeyMap.get(array[i+1])){
                result -= arabian;
            }else{
                result += arabian;
            }
        }
        return result;
    }
}
