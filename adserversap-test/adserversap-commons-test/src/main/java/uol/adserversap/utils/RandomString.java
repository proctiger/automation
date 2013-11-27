package uol.adserversap.utils;

import java.util.Random;

/**
 *
 * @author dvrocha
 */
public class RandomString {

    public static final String[] number = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    public static final String[] lower = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d",
        "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m"};
    public static final String[] upper = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D",
        "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M"};
    /*public static final String[] accented = {"�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
        "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
        "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�",
        "�", "�", "�", "�", "�", "�", "�", "�", "�"};*/
    public static final String[] special = {"-", "_", " ", "(", ")", ".", ":", "?", "!", "@", "#", "&", "%", "'", "\""};

    public static String getRandomText(int textSize, String[]... arrays) {
        String[] charList = mergeArrays(arrays);
        String text = "";
        Random random = new Random();
        for (int i = 0; i < textSize; i++) {
            String randomChar = charList[random.nextInt(charList.length)];
            if (i == 0 || i == textSize - 1) {
                while (randomChar.equals(" ") || randomChar.equals(".") || randomChar.
                        equals("-") || randomChar.equals("_")) {
                    randomChar = charList[random.nextInt(charList.length)];
                }
            }
            text += randomChar;
        }
        return text;
    }
    
    private static String[] mergeArrays(String[]... arrays) {
        int arraySize = sumArraySize(arrays);
        String[] array = new String[arraySize];
        int index = 0;
        for (String[] strings : arrays) {
            for (String string : strings) {
                array[index] = string;
                index++;
            }
        }
        return array;
    }

    private static int sumArraySize(String[]... arrays) {
        int sum = 0;
        for (String[] strings : arrays) {
            sum += strings.length;
        }
        return sum;
    }
}
