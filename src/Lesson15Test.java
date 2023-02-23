import by.tms.lesson15.homework.task1.BlrPassport;
import by.tms.lesson15.homework.task1.Gender;
import by.tms.lesson15.homework.task2.TextBlackListFilter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Lesson15Test {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("*********** MENU *************");
            System.out.println("1. BLR passport");
            System.out.println("2. Bad words");
            System.out.println("0. Exit");
            System.out.println("******************************");
            System.out.print("Input task number (0 for exit): ");

            System.out.print("Choose task: ");
            int key = Integer.parseInt(scanner.nextLine());

            switch (key) {
                case 1 -> {
                    System.out.print("Input passport id (format 1234567H123PB7): ");
                    String passId = scanner.nextLine();
                    System.out.print("Input passport number (format AB1234567): ");
                    String passNumber = scanner.nextLine();
                    System.out.print("Input name: ");
                    String name = scanner.nextLine();
                    System.out.print("Input surname: ");
                    String surname = scanner.nextLine();
                    System.out.print("Input gender (MALE or FEMALE): ");
                    Gender gender = Gender.valueOf(scanner.nextLine());
                    System.out.print("Input birth date (dd.mm.YYYY): ");
                    LocalDate birthDay = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    System.out.print("Input start date (dd.mm.YYYY): ");
                    LocalDate beginDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    System.out.print("Input end date (dd.mm.YYYY): ");
                    LocalDate endDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));

                    BlrPassport blrPassport = new BlrPassport(passId, name, surname, gender, birthDay, passNumber, beginDate, endDate);
                    System.out.println(blrPassport);
                    System.out.println("Is input passport expired: " + blrPassport.isExpared(LocalDate.now(ZoneId.of("Europe/Minsk"))));

                }
                case 2 -> {
                    System.out.println("Input array of bad words and phrases, delimited by comma: ");
                    String inputString = scanner.nextLine();

                    String[] inputArrayOfBadWords = inputString.split(", ");
                    TextBlackListFilter tblf = new TextBlackListFilter(inputArrayOfBadWords);
                    System.out.print("Input string for work: ");
                    String inputStr = scanner.nextLine();

                    boolean flag = true;
                    do {

                        System.out.println("What we will to do? \n");
                        System.out.println("***** 21. Contains bad words");
                        System.out.println("***** 22. Number of bad words in string");
                        System.out.println("***** 23. Censure string");
                        System.out.println("0. Exit");

                        int subKey = Integer.parseInt(scanner.nextLine());
                        switch (subKey) {
                            case 21 -> System.out.println(tblf.isContainsBadWords(inputStr));
                            case 22 -> System.out.println(tblf.badWordsCounter(inputStr));
                            case 23 -> System.out.println(tblf.modificateBadWords(inputStr));
                            case 0 -> flag = false;

                            default -> System.out.println("Wrong key!!!");
                        }
                    }
                    while (flag);
                }

                case 0 -> {
                    System.out.println("See you!!!");
                    return;
                }
                default -> System.out.println("Wrong key!!!");
            }
        } while (true);
    }
}
