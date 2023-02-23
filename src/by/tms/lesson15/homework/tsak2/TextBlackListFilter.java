package by.tms.lesson15.homework.tsak2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextBlackListFilter {

    private final String[] BAD_WORDS_OR_PHRASES;

    public TextBlackListFilter(String... badWordsOrPhrases) {
        this.BAD_WORDS_OR_PHRASES = badWordsOrPhrases;
    }

    public boolean isContainsBadWords(String inputString) {

        for (String badWordsAndPhrase : BAD_WORDS_OR_PHRASES) {
            Pattern pattern = getPattern(badWordsAndPhrase);
            Matcher matcher = pattern.matcher(inputString);
            if (matcher.find()) {
                return true;
            }
        }
        return false;
    }

    public int badWordsCounter(String inputString) {

        int badWordsCounter = 0;

        for (String badWordsAndPhrase : BAD_WORDS_OR_PHRASES) {
            Pattern pattern = getPattern(badWordsAndPhrase);
            Matcher matcher = pattern.matcher(inputString);
            if (matcher.find()) {
                badWordsCounter++;
            }
        }
        return badWordsCounter;
    }

    public StringBuilder modificateBadWords(String inputString){

        StringBuilder tmp = new StringBuilder(inputString);

        for (String badWordsAndPhrase : BAD_WORDS_OR_PHRASES) {
            Pattern pattern = getPattern(badWordsAndPhrase);
            Matcher matcher = pattern.matcher(tmp);
            if (matcher.find()) {
                tmp.replace(matcher.start(), matcher.end(), "######");
            }
        }
        return tmp;
    }

    private Pattern getPattern(String badWordOrPhrase) {
        return Pattern.compile(
                "\\b" + Pattern.quote(badWordOrPhrase) + "\\b",
                Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
        );
    }


    public static void main(String[] args) {
        TextBlackListFilter tblf = new TextBlackListFilter("bad", "beatch", "cool", "to дурак", "asshole", "Car", "SEX");

        System.out.println(tblf.isContainsBadWords("It's bad great to дурак you SeX"));
        System.out.println(tblf.badWordsCounter("It's bad great to дурак you SeX"));
        System.out.println(tblf.modificateBadWords("It's bad great to дурак you SeX"));

    }

}
