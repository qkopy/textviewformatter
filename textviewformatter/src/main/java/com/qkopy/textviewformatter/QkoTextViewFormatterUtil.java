package com.qkopy.textviewformatter;

import java.util.ArrayList;

public class QkoTextViewFormatterUtil {

    /**
     * Flag marking a sequence of character to be a specific formatting type.
     */
    static class Flag {

        int start; int end; char flag;

        Flag(int start, int end, char flag) {
            this.start = start;
            this.end = end;
            this.flag = flag;
        }
    }

    private static final char NEW_LINE = '\n';
    private static final char SPACE = ' ';
    static final char BOLD_FLAG = '*';
    static final char STRIKE_FLAG = '~';
    static final char ITALIC_FLAG = '_';


    static final int INVALID_INDEX = -1;

    /**
     * Checks whether the character present at the index of the CharSequence is a flagged character.
     * @param text - input text.
     * @param index - index to check.
     * @return boolean true if the character is flagged, false otherwise.
     */
    public static boolean isFlagged(CharSequence text, int index) {
        if(index > INVALID_INDEX && index < text.length()) {
            char c = text.charAt(index);
            return c == SPACE || c == NEW_LINE || c == BOLD_FLAG || c == ITALIC_FLAG || c == STRIKE_FLAG;
        }
        return true;
    }

    /**
     * Converts List of Characters to String.
     * @param characters input list.
     * @return String value.
     */
    static String getText(ArrayList<Character> characters) {
        char[] chars = new char[characters.size()];

        for (int i = 0; i < chars.length; i++) {
            chars[i] = characters.get(i);
        }
        return new String(chars);
    }

    /**
     * Tells whether has a flag in the same line as mentioned by fromIndex character.
     * @param sequence - text
     * @param flag - expected flag.
     * @param fromIndex - index representing the line.
     * @return boolean
     */
    static boolean hasFlagSameLine(CharSequence sequence, char flag, int fromIndex) {

        for(int i=fromIndex;i<sequence.length();i++) {
            char c = sequence.charAt(i);
            if(c == NEW_LINE) {
                return false;
            }

            if(c == flag ) {
                return i != fromIndex;
            }
        }

        return false;
    }
}