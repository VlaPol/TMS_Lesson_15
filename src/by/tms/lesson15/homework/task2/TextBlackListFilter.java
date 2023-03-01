package by.tms.lesson15.homework.task2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextBlackListFilter {

    private final Pattern[] pattern;

    public TextBlackListFilter(String... badWordsOrPhrases) {

        pattern = new Pattern[badWordsOrPhrases.length];

        for (int i = 0; i < badWordsOrPhrases.length; i++) {
            pattern[i] = getPattern(badWordsOrPhrases[i]);
        }

    }

    public boolean isContainsBadWords(String inputString) {

        for (Pattern itm : pattern) {
            Matcher matcher = itm.matcher(inputString);
            if (matcher.find()) {
                return true;
            }
        }
        return false;
    }

    public int badWordsCounter(String inputString) {

        int badWordsCounter = 0;

        for (Pattern itm : pattern) {
            Matcher matcher = itm.matcher(inputString);

            while (matcher.find()) {
                badWordsCounter++;
            }
        }

        return badWordsCounter;
    }

    public String modificationBadWords(String inputString) {

        for (Pattern itm : pattern) {
            Matcher matcher = itm.matcher(inputString);
            inputString = matcher.replaceAll("#####");
        }
        return inputString;
    }

    private Pattern getPattern(String badWordOrPhrase) {
        return Pattern.compile(
                "\\b" + Pattern.quote(badWordOrPhrase) + "\\b",
                Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
        );
    }

}
