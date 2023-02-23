package by.tms.lesson15.homework.task1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BlrPassport {

    private String idNumber;
    private String name;
    private String surname;
    private Gender gender;
    private LocalDate birthDate;
    private String passNumber;
    private LocalDate beginDate;
    private LocalDate endDate;


    public BlrPassport(String idNumber, String name, String surname, Gender gender, LocalDate birthDate,
                       String passNumber, LocalDate beginDate, LocalDate endDate) {

        if (isValidPassport(idNumber, name, surname, passNumber, beginDate, endDate)) {
            this.idNumber = idNumber;
            this.name = name;
            this.surname = surname;
            this.gender = gender;
            this.birthDate = birthDate;
            this.passNumber = passNumber;
            this.beginDate = beginDate;
            this.endDate = endDate;
        } else {
            throw new RuntimeException("Data in field(s) is incorrect!");
        }

    }


    public boolean isExpared(LocalDate now) {
        return LocalDate.now().isAfter(endDate);
    }

    private boolean isValidPassport(String idNumber, String name, String surname,
                                    String passNumber, LocalDate beginDate, LocalDate endDate) {

        return isValidIdNumber(idNumber) && isValidNameAndSurname(name, surname) && isGoodPassNumber(passNumber)
               && isGoodDataPassDuration(beginDate, endDate);

    }

    private boolean isGoodDataPassDuration(LocalDate beginDate, LocalDate endDate) {
        return beginDate.isBefore(endDate);
    }

    private boolean isGoodPassNumber(String passNumber) {
        if (passNumber.length() != 9) {
            throw new IllegalArgumentException("Passport number should contains 9 signs");
        }
        return passNumber.matches("(AB|BM|HB|KH|MP|MC|KB|PP|SP|DP)\\d{7}");
    }

    private boolean isValidNameAndSurname(String name, String surname) {

        if (name == null || surname == null) {
            throw new IllegalArgumentException("Name and surname are needed");
        }
        return name.matches("^[a-zA-Z]+$") && surname.matches("^[a-zA-Z]+$");
    }

    private boolean isValidIdNumber(String idNumber) {

        if (idNumber.length() != 14) {
            throw new IllegalArgumentException("Passport identification number should contains 14 signs");
        }
        return idNumber.matches("\\d{7}[ABCKEMH]\\d{3}(PB|BA|BI)\\d");
    }


    @Override
    public String toString() {

        return "Belarusian Passport: \n" +
               "\t\t passport number = " + idNumber + "\n" +
               "\t\t owners name = " + name + "\n" +
               "\t\t owners surname = " + surname + "\n" +
               "\t\t owners gender = " + gender + "\n" +
               "\t\t owners birthDate = " + birthDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\n" +
               "\t\t passport personal number = " + passNumber + "\n" +
               "\t\t passport valid: from = " + beginDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
               " to " + endDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

}
