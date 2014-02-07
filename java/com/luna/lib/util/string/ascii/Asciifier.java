package com.luna.lib.util.string.ascii;

import com.luna.lib.util.string.ascii.characters.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: audrey
 * Date: 9/26/13
 * Time: 5:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Asciifier {

    private static Map<Character, IAsciiCharacter> charMap;

    static {
        charMap = new HashMap<>();
        charMap.put('A', new AsciiA());
        charMap.put('B', new AsciiB());
        charMap.put('C', new AsciiC());
        charMap.put('D', new AsciiD());
        charMap.put('E', new AsciiE());
        charMap.put('F', new AsciiF());
        charMap.put('G', new AsciiG());
        charMap.put('H', new AsciiH());
        charMap.put('I', new AsciiI());
        charMap.put('J', new AsciiJ());
        charMap.put('K', new AsciiK());
        charMap.put('L', new AsciiL());
        charMap.put('M', new AsciiM());
        charMap.put('N', new AsciiN());
        charMap.put('O', new AsciiO());
        charMap.put('P', new AsciiP());
        charMap.put('Q', new AsciiQ());
        charMap.put('R', new AsciiR());
        charMap.put('S', new AsciiS());
        charMap.put('T', new AsciiT());
        charMap.put('U', new AsciiU());
        charMap.put('V', new AsciiV());
        charMap.put('W', new AsciiW());
        charMap.put('X', new AsciiX());
        charMap.put('Y', new AsciiY());
        charMap.put('Z', new AsciiZ());
        charMap.put('0', new Ascii0());
        charMap.put('1', new Ascii1());
        charMap.put('2', new Ascii2());
        charMap.put('3', new Ascii3());
        charMap.put('4', new Ascii4());
        charMap.put('5', new Ascii5());
        charMap.put('6', new Ascii6());
        charMap.put('7', new Ascii7());
        charMap.put('8', new Ascii8());
        charMap.put('9', new Ascii9());
        charMap.put('"', new AsciiQuote());
        charMap.put(' ', null);
    }

    public static void print(String s) {
        IAsciiCharacter[] charArr = stringToAsciiArray(s);

        print(charArr);
    }

    private static IAsciiCharacter[] stringToAsciiArray(String s) {
        List<IAsciiCharacter> chars = new ArrayList<>();
        char[] ch = s.toCharArray();
        for(char c : ch) {
            chars.add(getCharFromChar(c));
        }

        return chars.toArray(new IAsciiCharacter[chars.size()]);
    }

    private static IAsciiCharacter getCharFromChar(char c) {
        for(Map.Entry<Character, IAsciiCharacter> e : charMap.entrySet()) {
            if(e.getKey().equals(Character.toUpperCase(c))) {
                return e.getValue();
            }
        }

        throw new IllegalArgumentException(String.format("Unknown character \'%c\'!", c));
    }

    private static void print(IAsciiCharacter... characters) {

        int maxHeight = 0;
        for (IAsciiCharacter ch : characters) {
            if(ch == null) {
                continue;
            }
            maxHeight = Math.max(ch.getLineCount(), maxHeight);
        }

        for (int index = 0; index < maxHeight; index++) {
            IAsciiCharacter prev = new AsciiI();
            for (IAsciiCharacter ch : characters) {
                if(ch == null) {
                    System.out.print(emptyLine(prev.getWidth() + 1));
                    continue;
                }
                String line = ch.getLineAt(index);
                if (line != null) {
                    System.out.print(line + " ");
                } else {
                    System.out.print(emptyLine(ch.getWidth() + 1));
                }
                prev = ch;
            }
            System.out.println();
        }
        System.out.println();
    }

    public static String emptyLine(int width) {
        StringBuilder sb = new StringBuilder(width);
        while (sb.length() < width) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
