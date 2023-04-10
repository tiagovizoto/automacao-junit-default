package br.com.quality.general.support;

import br.com.quality.report.RobotReport;
import br.com.quality.report.RobotTest;
import br.com.quality.robots.SeleniumRobotsTool;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GeneralUtils {

    private static int stepPosition;
    private static String errorMessage;
    private static RobotReport report;
    private static RobotTest test;
    private static RobotTest step;
    private static boolean validateTreatedError;
    static Faker faker = new Faker(new Locale("pt-BR"));
    static Random random = new Random();
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static int getStepPosition() {
        return stepPosition;
    }

    public void setStepPosition(int stepPosition) {
        this.stepPosition = stepPosition;
    }

    public static String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static boolean getValidateTreatedError() {
        return validateTreatedError;
    }

    public void setValidateTreatedError(boolean validateTreatedError) {
        this.validateTreatedError = validateTreatedError;
    }

    public static RobotReport getReport() {
        return report;
    }

    public void setReport(RobotReport report) {
        this.report = report;
    }

    public static RobotTest getTest() {
        return test;
    }

    public void setTest(RobotTest test) {
        this.test = test;
    }

    public static RobotTest getStep() {
        return step;
    }

    public void setStep(RobotTest step) {
        this.step = step;
    }

    public void testBase(String testCase, String testDescription) {
        test = getReport().createTestCase("Caso " + (getStepPosition() + 1) + " - " + testCase + " - " + testDescription, testCase + " - " + testDescription);
        setTest(test);
    }

    public void stepBase(String scenarioName, String scenarioDescription) {
        step = test.createTestStep(scenarioName, scenarioDescription);
        setStep(step);
    }


    public void waitForObjectDisappear(SeleniumRobotsTool driverWEB, By object, int milliseconds, int maxCount) throws InterruptedException {
        int size;
        int cont = 0;

        do {
            Thread.sleep(milliseconds);
            size = driverWEB.elemntCount(object);
            cont += 1;
        } while (size > 0 && cont < maxCount);

    }

    public String dateNow() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

    public String dateNowBrFormat() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

    public String dateCancellation(int rangeCancellation) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(simpleDateFormat.parse(dateNow()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        calendar.add(Calendar.DAY_OF_MONTH, rangeCancellation);
        String dateCancellation = simpleDateFormat.format(calendar.getTime());

        return dateCancellation;
    }

    public String now(String formatStr) {
        DateFormat dateFormat = new SimpleDateFormat(formatStr); //$NON-NLS-1$
        java.util.Date date = new java.util.Date();

        return dateFormat.format(date);
    }

    public static String removeAccents(String wordWithAccent) {
        wordWithAccent = Normalizer.normalize(wordWithAccent, Normalizer.Form.NFD);
        wordWithAccent = wordWithAccent.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        return wordWithAccent;
    }

    public static String removeSpaces(String args) {
        String result = args.replaceAll("\\s+","");

        return result;
    }

    public static String nameGenerator() {
        String name = removeAccents(faker.name().firstName());

        // Remover segundo nome (Caso tenha)
        String[] firstName = name.split("\\s");
        name = firstName[0];

        return name;
    }

    public static String lastNameGenerator() {
        String[] lastNameList = {"Teste1", "Teste2", "Teste3", "Teste4", "Teste5", "Teste6"};
        int position = random.nextInt(lastNameList.length);
        String randomLastname = removeSpaces(removeAccents(faker.name().lastName()));
        String lastName = lastNameList[position] + randomLastname;

        return lastName;
    }

    public static String emailGenerator() {
        String[] providerList = {"@outlook.com", "@ig.com", "@hotmail.com", "@gmail.com"};
        int position = random.nextInt(providerList.length);
        String name = nameGenerator();
        String lastName = lastNameGenerator();
        String email = removeSpaces(name.toLowerCase()) + "." + lastName.toLowerCase() + providerList[position];

        return email;
    }

    public static String zipCodeGenerator() {
        String[] zipCodeList = {"20511330", "05541040", "01452002", "69152055", "13500110", "79010210", "38408254", "12242460", "74946651"};
        int position = random.nextInt(zipCodeList.length);
        String zipCode = zipCodeList[position];

        return zipCode;
    }

    public static String birthdayGenerator(int minimumAge, int maximumAge) throws Exception {
        String birthday = String.valueOf(dateFormat.format(faker.date().birthday(minimumAge, maximumAge)));

        return birthday;
    }

    public static String phoneNumberGenerator() {
        String phoneNumber = faker.phoneNumber().cellPhone();;
        return phoneNumber;
    }

    public static String cpfGenerator() {
        String recurrence = "";
        int number = 10;
        int number1 = random.nextInt(number);
        int number2 = random.nextInt(number);
        int number3 = random.nextInt(number);
        int number4 = random.nextInt(number);
        int number5 = random.nextInt(number);
        int number6 = random.nextInt(number);
        int number7 = random.nextInt(number);
        int number8 = random.nextInt(number);
        int number9 = random.nextInt(number);
        int division1 = number9 * 2 + number8 * 3 + number7 * 4 + number6 * 5 + number5 * 6 + number4 * 7 + number3 * 8 + number2 * 9 + number1 * 10;

        division1 = (int) (11 - (Math.round(division1 - (Math.floor(division1 / 11) * 11))));

        if (division1 >= 10)
            division1 = 0;

        int division2 = division1 * 2 + number9 * 3 + number8 * 4 + number7 * 5 + number6 * 6 + number5 * 7 + number4 * 8 + number3 * 9 + number2 * 10 + number1 * 11;

        division2 = (int) (11 - Math.round(division2 - (Math.floor(division2 / 11) * 11)));

        if (division2 >= 10)
            division2 = 0;

        recurrence = "" + number1 + number2 + number3 + '.' + number4 + number5 + number6 + '.' + number7 + number8 + number9 + '-' + division1 + division2;

        return recurrence;
    }

    public static String creditCardGenerator(String creditCardBrand) throws Exception {
        int length = 16;
        String[] prefixList;

        switch (creditCardBrand.toUpperCase(Locale.ROOT)){
            case "MASTERCARD":
                prefixList = new String[]{ "51", "52", "53", "54", "55" };
                break;
            case "VISA":
                prefixList = new String[]{ "4539","4556", "4916", "4532", "4929", "40240071", "4485", "4716", "4" };
                break;
            case "AMEX":
                prefixList = new String[]{ "34", "37" };
                break;
            case "DINERS":
                prefixList = new String[]{ "300","301", "302", "303", "36", "38" };
                break;
            default:
                throw new Exception("O cartão desejado não esta dentro das escolhas dísponiveis, favor verificar as opções de bandeira");
        }

        int position = random.nextInt(prefixList.length);
        String creditCardNumber = prefixList[position];

        while (creditCardNumber.length() < (length - 1)) {
            creditCardNumber += new Double(Math.floor(Math.random() * 10)).intValue();
        }
        String reverseString = "";

        if (creditCardNumber != null) {
            for (int i = creditCardNumber.length() - 1; i >= 0; i--) {
                reverseString += creditCardNumber.charAt(i);
            }
        }

        List<Integer> reversedCCnumberList = new Vector<Integer>();
        for (int i = 0; i < reverseString.length(); i++) {
            reversedCCnumberList.add(new Integer(String.valueOf(reverseString.charAt(i))));
        }

        int sum = 0;
        int pos = 0;

        Integer[] reversedCCnumber = reversedCCnumberList.toArray(new Integer[reversedCCnumberList.size()]);
        while (pos < length - 1) {
            int odd = reversedCCnumber[pos] * 2;
            if (odd > 9) {
                odd -= 9;
            }
            sum += odd;
            if (pos != (length - 2)) {
                sum += reversedCCnumber[pos + 1];
            }
            pos += 2;
        }
        int checkdigit = new Double(
                ((Math.floor(sum / 10) + 1) * 10 - sum) % 10).intValue();
        creditCardNumber += checkdigit;
        return creditCardNumber;
    }

    public static ArrayList distributionOfGuestsIntoRoom(String quantityRoom, String quantityAdult, String quantityChild, String quantityinfant) throws Exception {

        int numberOfRoom = Integer.parseInt(quantityRoom);
        int numberOfAdult = Integer.parseInt(quantityAdult);
        int numberOfChild = Integer.parseInt(quantityChild);
        int numberOfInfant = Integer.parseInt(quantityinfant);

        ArrayList<ArrayList> passenger = new ArrayList<>();
        ArrayList<Integer> adult = new ArrayList<>();
        ArrayList<Integer> child = new ArrayList<>();
        ArrayList<Integer> infant = new ArrayList<>();


        switch (numberOfRoom) {
            case 1:
                System.out.println("Entrou na opção de 1 quarto, com isso, não haverá distribuição");
                Collections.addAll(adult, numberOfAdult);
                Collections.addAll(child, numberOfChild);
                Collections.addAll(infant, numberOfInfant);
                break;
            case 2:
                System.out.println("Entrou na opção de 2 quartos");

                if(numberOfAdult < 2) {
                    throw new Exception("Não é possível escolher a opção de 2 quartos tendo apenas um adulto [EDITAR BDD]");
                } else if (numberOfInfant > numberOfAdult) {
                    throw new Exception("Não é possível viajar com uma quantidade de bebês (" + numberOfInfant + ") maior do que a quantidade de adultos (" + numberOfAdult + ")");
                }

                if (numberOfAdult == 2 && numberOfChild == 0 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1,1);
                    Collections.addAll(child, 0,0);
                    Collections.addAll(infant, 0,0);
                    break;
                } else if (numberOfAdult == 2 && numberOfChild == 1 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1,1);
                    Collections.addAll(child, 1,0);
                    Collections.addAll(infant, 0,0);
                    break;
                } else if (numberOfAdult == 2 && numberOfChild == 0 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1,1);
                    Collections.addAll(child, 0,0);
                    Collections.addAll(infant, 0,1);
                    break;
                } else if (numberOfAdult == 2 && numberOfChild == 1 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1,1);
                    Collections.addAll(child, 0,1);
                    Collections.addAll(infant, 1,0);
                    break;
                } else if (numberOfAdult == 2 && numberOfChild == 2 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1,1);
                    Collections.addAll(child, 1,1);
                    Collections.addAll(infant, 0,0);
                    break;
                } else if (numberOfAdult == 2 && numberOfChild == 2 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1,1);
                    Collections.addAll(child, 2,0);
                    Collections.addAll(infant, 0,1);
                    break;
                } else if (numberOfAdult == 2 && numberOfChild == 0 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1,1);
                    Collections.addAll(child, 0,0);
                    Collections.addAll(infant, 1,1);
                    break;
                } else if (numberOfAdult == 2 && numberOfChild == 1 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1,1);
                    Collections.addAll(child, 0,1);
                    Collections.addAll(infant, 1,1);
                    break;
                } else if (numberOfAdult == 2 && numberOfChild == 2 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1,1);
                    Collections.addAll(child, 1,1);
                    Collections.addAll(infant, 1,1);
                    break;
                } else if (numberOfAdult == 2 && numberOfChild == 3 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1,1);
                    Collections.addAll(child, 2,1);
                    Collections.addAll(infant, 0,0);
                    break;
                } else if (numberOfAdult == 2 && numberOfChild == 3 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1,1);
                    Collections.addAll(child, 1,2);
                    Collections.addAll(infant, 1,0);
                    break;
                } else if (numberOfAdult == 2 && numberOfChild == 3 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1,1);
                    Collections.addAll(child, 1,2);
                    Collections.addAll(infant, 1,1);
                    break;
                } else if (numberOfAdult == 2 && numberOfChild == 4 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1,1);
                    Collections.addAll(child, 2,2);
                    Collections.addAll(infant, 0,0);
                    break;
                } else if (numberOfAdult == 2 && numberOfChild == 4 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1,1);
                    Collections.addAll(child, 3,1);
                    Collections.addAll(infant, 0,1);
                    break;
                } else if (numberOfAdult == 2 && numberOfChild == 4 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1,1);
                    Collections.addAll(child, 1,3);
                    Collections.addAll(infant, 2,0);
                    break;
                } else if (numberOfAdult == 2 && numberOfChild == 5 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1,1);
                    Collections.addAll(child, 3,2);
                    Collections.addAll(infant, 0,0);
                    break;
                } else if (numberOfAdult == 2 && numberOfChild == 5 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1,1);
                    Collections.addAll(child, 2,3);
                    Collections.addAll(infant, 1,0);
                    break;
                } else if (numberOfAdult == 2 && numberOfChild == 5 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1,1);
                    Collections.addAll(child, 3,2);
                    Collections.addAll(infant, 1,1);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 0 && numberOfInfant == 0) {
                    Collections.addAll(adult, 2,1);
                    Collections.addAll(child, 0,0);
                    Collections.addAll(infant, 0,0);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 1 && numberOfInfant == 0) {
                    Collections.addAll(adult, 2,1);
                    Collections.addAll(child, 1,0);
                    Collections.addAll(infant, 0,0);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 0 && numberOfInfant == 1) {
                    Collections.addAll(adult, 2,1);
                    Collections.addAll(child, 0,0);
                    Collections.addAll(infant, 0,1);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 1 && numberOfInfant == 1) {
                    Collections.addAll(adult, 2,1);
                    Collections.addAll(child, 0,1);
                    Collections.addAll(infant, 1,0);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 2 && numberOfInfant == 0) {
                    Collections.addAll(adult, 2,1);
                    Collections.addAll(child, 1,1);
                    Collections.addAll(infant, 0,0);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 2 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1,2);
                    Collections.addAll(child, 1,1);
                    Collections.addAll(infant, 1,0);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 0 && numberOfInfant == 2) {
                    Collections.addAll(adult, 2,1);
                    Collections.addAll(child, 0,0);
                    Collections.addAll(infant, 2,0);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 1 && numberOfInfant == 2) {
                    Collections.addAll(adult, 2,1);
                    Collections.addAll(child, 1,0);
                    Collections.addAll(infant, 1,1);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 2 && numberOfInfant == 2) {
                    Collections.addAll(adult, 2,1);
                    Collections.addAll(child, 1,1);
                    Collections.addAll(infant, 1,1);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 3 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1,2);
                    Collections.addAll(child, 2,1);
                    Collections.addAll(infant, 0,0);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 3 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1,2);
                    Collections.addAll(child, 2,1);
                    Collections.addAll(infant, 0,1);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 3 && numberOfInfant == 2) {
                    Collections.addAll(adult, 2,1);
                    Collections.addAll(child, 1,2);
                    Collections.addAll(infant, 1,1);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 0 && numberOfInfant == 3) {
                    Collections.addAll(adult, 2,1);
                    Collections.addAll(child, 0,0);
                    Collections.addAll(infant, 2,1);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 1 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1,2);
                    Collections.addAll(child, 1,0);
                    Collections.addAll(infant, 1,2);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 2 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1,2);
                    Collections.addAll(child, 1,1);
                    Collections.addAll(infant, 1,2);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 3 && numberOfInfant == 3) {
                    Collections.addAll(adult, 2,1);
                    Collections.addAll(child, 1,2);
                    Collections.addAll(infant, 2,1);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 4 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1,2);
                    Collections.addAll(child, 2,2);
                    Collections.addAll(infant, 0,0);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 4 && numberOfInfant == 1) {
                    Collections.addAll(adult, 2,1);
                    Collections.addAll(child, 1,3);
                    Collections.addAll(infant, 1,0);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 4 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1,2);
                    Collections.addAll(child, 2,2);
                    Collections.addAll(infant, 1,1);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 4 && numberOfInfant == 3) {
                    Collections.addAll(adult, 2,1);
                    Collections.addAll(child, 2,2);
                    Collections.addAll(infant, 2,1);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 5 && numberOfInfant == 0) {
                    Collections.addAll(adult, 2,1);
                    Collections.addAll(child, 2,3);
                    Collections.addAll(infant, 0,0);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 5 && numberOfInfant == 1) {
                    Collections.addAll(adult, 2,1);
                    Collections.addAll(child, 4,1);
                    Collections.addAll(infant, 0,1);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 5 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1,2);
                    Collections.addAll(child, 3,2);
                    Collections.addAll(infant, 1,1);
                    break;
                } else if (numberOfAdult == 3 && numberOfChild == 5 && numberOfInfant == 3) {
                    Collections.addAll(adult, 2,1);
                    Collections.addAll(child, 2,3);
                    Collections.addAll(infant, 2,1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 0 && numberOfInfant == 0) {
                    Collections.addAll(adult, 2, 2);
                    Collections.addAll(child, 0, 0);
                    Collections.addAll(infant, 0, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 1 && numberOfInfant == 0) {
                    Collections.addAll(adult, 3, 1);
                    Collections.addAll(child, 0, 1);
                    Collections.addAll(infant, 0, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 0 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1, 3);
                    Collections.addAll(child, 0, 0);
                    Collections.addAll(infant, 1, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 1 && numberOfInfant == 1) {
                    Collections.addAll(adult, 2, 2);
                    Collections.addAll(child, 1, 0);
                    Collections.addAll(infant, 0, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 2 && numberOfInfant == 0) {
                    Collections.addAll(adult, 2, 2);
                    Collections.addAll(child, 1, 1);
                    Collections.addAll(infant, 0, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 2 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1, 3);
                    Collections.addAll(child, 1, 1);
                    Collections.addAll(infant, 0, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 2 && numberOfInfant == 2) {
                    Collections.addAll(adult, 3, 1);
                    Collections.addAll(child, 1, 1);
                    Collections.addAll(infant, 1, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 2 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 3);
                    Collections.addAll(child, 2, 0);
                    Collections.addAll(infant, 1, 2);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 2 && numberOfInfant == 4) {
                    Collections.addAll(adult, 3, 1);
                    Collections.addAll(child, 1, 1);
                    Collections.addAll(infant, 3, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 0 && numberOfInfant == 2) {
                    Collections.addAll(adult, 2, 2);
                    Collections.addAll(child, 0, 0);
                    Collections.addAll(infant, 2, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 1 && numberOfInfant == 2) {
                    Collections.addAll(adult, 2, 2);
                    Collections.addAll(child, 1, 0);
                    Collections.addAll(infant, 0, 2);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 3 && numberOfInfant == 2) {
                    Collections.addAll(adult, 2, 2);
                    Collections.addAll(child, 1, 2);
                    Collections.addAll(infant, 2, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 4 && numberOfInfant == 2) {
                    Collections.addAll(adult, 3, 1);
                    Collections.addAll(child, 2, 2);
                    Collections.addAll(infant, 1, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 3 && numberOfInfant == 0) {
                    Collections.addAll(adult, 3, 1);
                    Collections.addAll(child, 0, 3);
                    Collections.addAll(infant, 0, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 3 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1, 3);
                    Collections.addAll(child, 2, 1);
                    Collections.addAll(infant, 1, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 3 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 3);
                    Collections.addAll(child, 2, 1);
                    Collections.addAll(infant, 1, 2);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 3 && numberOfInfant == 4) {
                    Collections.addAll(adult, 3, 1);
                    Collections.addAll(child, 1, 2);
                    Collections.addAll(infant, 3, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 0 && numberOfInfant == 3) {
                    Collections.addAll(adult, 3, 1);
                    Collections.addAll(child, 0, 0);
                    Collections.addAll(infant, 2, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 1 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 3);
                    Collections.addAll(child, 1, 0);
                    Collections.addAll(infant, 1, 2);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 4 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 3);
                    Collections.addAll(child, 3, 1);
                    Collections.addAll(infant, 1, 2);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 4 && numberOfInfant == 0) {
                    Collections.addAll(adult, 2, 2);
                    Collections.addAll(child, 1, 3);
                    Collections.addAll(infant, 0, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 4 && numberOfInfant == 1) {
                    Collections.addAll(adult, 2, 2);
                    Collections.addAll(child, 2, 2);
                    Collections.addAll(infant, 1, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 4 && numberOfInfant == 4) {
                    Collections.addAll(adult, 2, 2);
                    Collections.addAll(child, 2, 2);
                    Collections.addAll(infant, 2, 2);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 0 && numberOfInfant == 4) {
                    Collections.addAll(adult, 2, 2);
                    Collections.addAll(child, 0, 0);
                    Collections.addAll(infant, 2, 2);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 1 && numberOfInfant == 4) {
                    Collections.addAll(adult, 3, 1);
                    Collections.addAll(child, 0, 1);
                    Collections.addAll(infant, 3, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 5 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1, 3);
                    Collections.addAll(child, 3, 2);
                    Collections.addAll(infant, 0, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 5 && numberOfInfant == 1) {
                    Collections.addAll(adult, 3, 1);
                    Collections.addAll(child, 2, 3);
                    Collections.addAll(infant, 0, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 5 && numberOfInfant == 2) {
                    Collections.addAll(adult, 3, 1);
                    Collections.addAll(child, 1, 4);
                    Collections.addAll(infant, 1, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 5 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 3);
                    Collections.addAll(child, 4, 1);
                    Collections.addAll(infant, 1, 2);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 5 && numberOfInfant == 4) {
                    Collections.addAll(adult, 2, 2);
                    Collections.addAll(child, 3, 2);
                    Collections.addAll(infant, 2, 2);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 0 && numberOfInfant == 0) {
                    Collections.addAll(adult, 3, 2);
                    Collections.addAll(child, 0, 0);
                    Collections.addAll(infant, 0, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 1 && numberOfInfant == 0) {
                    Collections.addAll(adult, 2, 3);
                    Collections.addAll(child, 1, 0);
                    Collections.addAll(infant, 0, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 0 && numberOfInfant == 1) {
                    Collections.addAll(adult, 2, 3);
                    Collections.addAll(child, 0, 0);
                    Collections.addAll(infant, 1, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 1 && numberOfInfant == 1) {
                    Collections.addAll(adult, 3, 2);
                    Collections.addAll(child, 0, 1);
                    Collections.addAll(infant, 1, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 0 && numberOfInfant == 2) {
                    Collections.addAll(adult, 3, 2);
                    Collections.addAll(child, 0, 0);
                    Collections.addAll(infant, 2, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 1 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1, 4);
                    Collections.addAll(child, 1, 0);
                    Collections.addAll(infant, 0, 2);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 0 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 4);
                    Collections.addAll(child, 0, 0);
                    Collections.addAll(infant, 1, 2);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 1 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 4);
                    Collections.addAll(child, 1, 0);
                    Collections.addAll(infant, 1, 2);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 0 && numberOfInfant == 4) {
                    Collections.addAll(adult, 4, 1);
                    Collections.addAll(child, 0, 0);
                    Collections.addAll(infant, 3, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 1 && numberOfInfant == 4) {
                    Collections.addAll(adult, 4, 1);
                    Collections.addAll(child, 0, 1);
                    Collections.addAll(infant, 3, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 0 && numberOfInfant == 5) {
                    Collections.addAll(adult, 4, 1);
                    Collections.addAll(child, 0, 0);
                    Collections.addAll(infant, 4, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 1 && numberOfInfant == 5) {
                    Collections.addAll(adult, 3, 2);
                    Collections.addAll(child, 1, 0);
                    Collections.addAll(infant, 3, 2);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 2 && numberOfInfant == 0) {
                    Collections.addAll(adult, 2, 3);
                    Collections.addAll(child, 2, 0);
                    Collections.addAll(infant, 0, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 2 && numberOfInfant == 1) {
                    Collections.addAll(adult, 2, 3);
                    Collections.addAll(child, 1, 1);
                    Collections.addAll(infant, 1, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 2 && numberOfInfant == 2) {
                    Collections.addAll(adult, 2, 3);
                    Collections.addAll(child, 1, 1);
                    Collections.addAll(infant, 1, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 2 && numberOfInfant == 3) {
                    Collections.addAll(adult, 3, 2);
                    Collections.addAll(child, 2, 0);
                    Collections.addAll(infant, 0, 3);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 2 && numberOfInfant == 4) {
                    Collections.addAll(adult, 3, 2);
                    Collections.addAll(child, 1, 1);
                    Collections.addAll(infant, 3, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 2 && numberOfInfant == 5) {
                    Collections.addAll(adult, 4, 1);
                    Collections.addAll(child, 0, 2);
                    Collections.addAll(infant, 4, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 3 && numberOfInfant == 0) {
                    Collections.addAll(adult, 4, 1);
                    Collections.addAll(child, 3, 0);
                    Collections.addAll(infant, 0, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 3 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1, 4);
                    Collections.addAll(child, 2, 1);
                    Collections.addAll(infant, 1, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 3 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1, 4);
                    Collections.addAll(child, 2, 1);
                    Collections.addAll(infant, 0, 2);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 3 && numberOfInfant == 3) {
                    Collections.addAll(adult, 2, 3);
                    Collections.addAll(child, 2, 1);
                    Collections.addAll(infant, 2, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 3 && numberOfInfant == 4) {
                    Collections.addAll(adult, 2, 3);
                    Collections.addAll(child, 1, 2);
                    Collections.addAll(infant, 1, 3);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 3 && numberOfInfant == 5) {
                    Collections.addAll(adult, 2, 3);
                    Collections.addAll(child, 1, 2);
                    Collections.addAll(infant, 2, 3);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 4 && numberOfInfant == 0) {
                    Collections.addAll(adult, 3, 2);
                    Collections.addAll(child, 1, 3);
                    Collections.addAll(infant, 0, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 4 && numberOfInfant == 1) {
                    Collections.addAll(adult, 3, 2);
                    Collections.addAll(child, 3, 1);
                    Collections.addAll(infant, 0, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 4 && numberOfInfant == 2) {
                    Collections.addAll(adult, 3, 2);
                    Collections.addAll(child, 2, 2);
                    Collections.addAll(infant, 1, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 4 && numberOfInfant == 3) {
                    Collections.addAll(adult, 2, 3);
                    Collections.addAll(child, 2, 2);
                    Collections.addAll(infant, 2, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 4 && numberOfInfant == 4) {
                    Collections.addAll(adult, 3, 2);
                    Collections.addAll(child, 1, 3);
                    Collections.addAll(infant, 3, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 4 && numberOfInfant == 5) {
                    Collections.addAll(adult, 1, 4);
                    Collections.addAll(child, 2, 2);
                    Collections.addAll(infant, 1, 4);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 5 && numberOfInfant == 0) {
                    Collections.addAll(adult, 4, 1);
                    Collections.addAll(child, 4, 1);
                    Collections.addAll(infant, 0, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 5 && numberOfInfant == 1) {
                    Collections.addAll(adult, 2, 3);
                    Collections.addAll(child, 2, 3);
                    Collections.addAll(infant, 1, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 5 && numberOfInfant == 2) {
                    Collections.addAll(adult, 4, 1);
                    Collections.addAll(child, 2, 3);
                    Collections.addAll(infant, 1, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 5 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 4);
                    Collections.addAll(child, 3, 2);
                    Collections.addAll(infant, 1, 2);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 5 && numberOfInfant == 4) {
                    Collections.addAll(adult, 2, 3);
                    Collections.addAll(child, 4, 1);
                    Collections.addAll(infant, 2, 2);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 5 && numberOfInfant == 5) {
                    Collections.addAll(adult, 3, 2);
                    Collections.addAll(child, 2, 3);
                    Collections.addAll(infant, 3, 2);
                    break;
                } else {
                    throw new Exception("Este cenário com " + numberOfRoom + " quartos, " + numberOfAdult + " adulto(s)," + numberOfChild + " criança(s) e " + numberOfInfant + " bebê(s) não está mapeado na automação");
                }

            case 3:
                System.out.println("Entrou na opção de 3 quartos");

                if(numberOfAdult < 3) {
                    throw new Exception("Não é possível escolher a opção de 3 quartos contendo apenas um ou dois adultos [EDITAR BDD]");
                } else if (numberOfInfant > numberOfAdult) {
                    throw new Exception("Não é possível viajar com uma quantidade de bebês (" + numberOfInfant + ") maior do que a quantidade de adultos (" + numberOfAdult + ")");
                }

                if (numberOfAdult == 3 && numberOfChild == 0 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 0, 0, 0);
                    Collections.addAll(infant, 0, 0, 0);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 1 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 1, 0, 0);
                    Collections.addAll(infant, 0, 0, 0);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 0 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 0, 0, 0);
                    Collections.addAll(infant, 0, 1, 0);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 1 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 0, 0, 1);
                    Collections.addAll(infant, 1, 0, 0);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 2 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 0, 1, 1);
                    Collections.addAll(infant, 0, 0, 0);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 2 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 1, 1, 0);
                    Collections.addAll(infant, 0, 0, 1);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 0 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 0, 0, 0);
                    Collections.addAll(infant, 1, 0, 1);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 1 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 0, 0, 1);
                    Collections.addAll(infant, 1, 1, 0);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 2 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 1, 0, 1);
                    Collections.addAll(infant, 0, 1, 1);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 3 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 0, 0, 3);
                    Collections.addAll(infant, 0, 0, 0);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 3 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 3, 0, 0);
                    Collections.addAll(infant, 0, 1, 0);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 3 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 1, 1, 1);
                    Collections.addAll(infant, 0, 1, 1);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 0 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 0, 0, 0);
                    Collections.addAll(infant, 1, 1, 1);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 1 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 1, 0, 0);
                    Collections.addAll(infant, 1, 1, 1);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 2 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 1, 1, 0);
                    Collections.addAll(infant, 1, 1, 1);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 3 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 1, 1, 1);
                    Collections.addAll(infant, 1, 1, 1);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 4 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 1, 1, 2);
                    Collections.addAll(infant, 0, 0, 0);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 4 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 1, 2, 1);
                    Collections.addAll(infant, 1, 0, 0);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 4 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 2, 1, 1);
                    Collections.addAll(infant, 0, 1, 1);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 4 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 0, 2, 2);
                    Collections.addAll(infant, 1, 1, 1);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 5 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 1, 2, 2);
                    Collections.addAll(infant, 0, 0, 0);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 5 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 2, 2, 1);
                    Collections.addAll(infant, 0, 0, 1);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 5 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 0, 2, 3);
                    Collections.addAll(infant, 1, 1, 0);
                    break;
                } else if  (numberOfAdult == 3 && numberOfChild == 5 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 1, 1);
                    Collections.addAll(child, 3, 2, 0);
                    Collections.addAll(infant, 1, 1, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 0 && numberOfInfant == 0) {
                    Collections.addAll(adult, 2, 1, 1);
                    Collections.addAll(child, 0, 0, 0);
                    Collections.addAll(infant, 0, 0, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 1 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1, 1, 2);
                    Collections.addAll(child, 0, 1, 0);
                    Collections.addAll(infant, 0, 0, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 0 && numberOfInfant == 1) {
                    Collections.addAll(adult, 2, 1, 1);
                    Collections.addAll(child, 0, 0, 0);
                    Collections.addAll(infant, 0, 0, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 1 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1, 1, 2);
                    Collections.addAll(child, 0, 1, 0);
                    Collections.addAll(infant, 1, 0, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 2 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1, 2, 1);
                    Collections.addAll(child, 2, 0, 0);
                    Collections.addAll(infant, 0, 0, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 2 && numberOfInfant == 1) {
                    Collections.addAll(adult, 2, 1, 1);
                    Collections.addAll(child, 0, 1, 1);
                    Collections.addAll(infant, 0, 1, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 2 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1, 2, 1);
                    Collections.addAll(child, 1, 0, 1);
                    Collections.addAll(infant, 1, 0, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 2 && numberOfInfant == 3) {
                    Collections.addAll(adult, 2, 1, 1);
                    Collections.addAll(child, 0, 1, 1);
                    Collections.addAll(infant, 1, 1, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 2 && numberOfInfant == 4) {
                    Collections.addAll(adult, 1, 1, 2);
                    Collections.addAll(child, 1, 1, 0);
                    Collections.addAll(infant, 1, 1, 2);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 0 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1, 2, 1);
                    Collections.addAll(child, 0, 0, 0);
                    Collections.addAll(infant, 0, 2, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 1 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1, 2, 1);
                    Collections.addAll(child, 0, 1, 0);
                    Collections.addAll(infant, 1, 0, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 3 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1, 1, 2);
                    Collections.addAll(child, 1, 1, 1);
                    Collections.addAll(infant, 0, 1, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 4 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1, 1, 2);
                    Collections.addAll(child, 2, 2, 0);
                    Collections.addAll(infant, 1, 1, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 3 && numberOfInfant == 0) {
                    Collections.addAll(adult, 2, 1, 1);
                    Collections.addAll(child, 1, 1, 1);
                    Collections.addAll(infant, 0, 0, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 3 && numberOfInfant == 1) {
                    Collections.addAll(adult, 2, 1, 1);
                    Collections.addAll(child, 1, 1, 1);
                    Collections.addAll(infant, 0, 0, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 3 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 2, 1);
                    Collections.addAll(child, 0, 0, 3);
                    Collections.addAll(infant, 1, 2, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 3 && numberOfInfant == 4) {
                    Collections.addAll(adult, 1, 2, 1);
                    Collections.addAll(child, 3, 0, 0);
                    Collections.addAll(infant, 1, 2, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 0 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 1, 2);
                    Collections.addAll(child, 0, 0, 0);
                    Collections.addAll(infant, 1, 0, 2);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 1 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 1, 2);
                    Collections.addAll(child, 1, 0, 0);
                    Collections.addAll(infant, 1, 1, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 4 && numberOfInfant == 3) {
                    Collections.addAll(adult, 2, 1, 1);
                    Collections.addAll(child, 0, 2, 2);
                    Collections.addAll(infant, 1, 1, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 4 && numberOfInfant == 0) {
                    Collections.addAll(adult, 2, 1, 1);
                    Collections.addAll(child, 1, 2, 1);
                    Collections.addAll(infant, 0, 0, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 4 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1, 2, 1);
                    Collections.addAll(child, 1, 1, 2);
                    Collections.addAll(infant, 1, 0, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 4 && numberOfInfant == 4) {
                    Collections.addAll(adult, 2, 1, 1);
                    Collections.addAll(child, 0, 2, 2);
                    Collections.addAll(infant, 2, 1, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 0 && numberOfInfant == 4) {
                    Collections.addAll(adult, 1, 1, 2);
                    Collections.addAll(child, 0, 0, 0);
                    Collections.addAll(infant, 1, 1, 2);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 1 && numberOfInfant == 4) {
                    Collections.addAll(adult, 1, 2, 1);
                    Collections.addAll(child, 1, 0, 0);
                    Collections.addAll(infant, 1, 2, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 5 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1, 1, 2);
                    Collections.addAll(child, 2, 2, 1);
                    Collections.addAll(infant, 0, 0, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 5 && numberOfInfant == 1) {
                    Collections.addAll(adult, 2, 1, 1);
                    Collections.addAll(child, 1, 2, 2);
                    Collections.addAll(infant, 0, 1, 0);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 5 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1, 2, 1);
                    Collections.addAll(child, 2, 1, 2);
                    Collections.addAll(infant, 1, 0, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 5 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 1, 2);
                    Collections.addAll(child, 1, 1, 3);
                    Collections.addAll(infant, 1, 1, 1);
                    break;
                } else if  (numberOfAdult == 4 && numberOfChild == 5 && numberOfInfant == 4) {
                    Collections.addAll(adult, 2, 1, 1);
                    Collections.addAll(child, 2, 3, 0);
                    Collections.addAll(infant, 2, 1, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 0 && numberOfInfant == 0) {
                    Collections.addAll(adult, 2, 2, 1);
                    Collections.addAll(child, 0, 0, 0);
                    Collections.addAll(infant, 0, 0, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 1 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1, 1, 3);
                    Collections.addAll(child, 1, 0, 0);
                    Collections.addAll(infant, 0, 0, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 0 && numberOfInfant == 1) {
                    Collections.addAll(adult, 3, 1, 1);
                    Collections.addAll(child, 0, 0, 0);
                    Collections.addAll(infant, 0, 1, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 1 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1, 3, 1);
                    Collections.addAll(child, 1, 0, 0);
                    Collections.addAll(infant, 0, 0, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 0 && numberOfInfant == 2) {
                    Collections.addAll(adult, 2, 1, 2);
                    Collections.addAll(child, 0, 0, 0);
                    Collections.addAll(infant, 2, 0, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 1 && numberOfInfant == 2) {
                    Collections.addAll(adult, 2, 1, 2);
                    Collections.addAll(child, 0, 1, 0);
                    Collections.addAll(infant, 0, 1, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 0 && numberOfInfant == 3) {
                    Collections.addAll(adult, 2, 2, 1);
                    Collections.addAll(child, 0, 0, 0);
                    Collections.addAll(infant, 2, 0, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 1 && numberOfInfant == 3) {
                    Collections.addAll(adult, 2, 2, 1);
                    Collections.addAll(child, 0, 0, 1);
                    Collections.addAll(infant, 1, 1, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 0 && numberOfInfant == 4) {
                    Collections.addAll(adult, 1, 2, 2);
                    Collections.addAll(child, 0, 0, 0);
                    Collections.addAll(infant, 1, 1, 2);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 1 && numberOfInfant == 4) {
                    Collections.addAll(adult, 1, 2, 2);
                    Collections.addAll(child, 1, 0, 0);
                    Collections.addAll(infant, 1, 2, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 0 && numberOfInfant == 5) {
                    Collections.addAll(adult, 2, 1, 2);
                    Collections.addAll(child, 0, 0, 0);
                    Collections.addAll(infant, 2, 1, 2);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 1 && numberOfInfant == 5) {
                    Collections.addAll(adult, 2, 1, 2);
                    Collections.addAll(child, 0, 1, 0);
                    Collections.addAll(infant, 2, 1, 2);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 2 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1, 3, 1);
                    Collections.addAll(child, 1, 0, 1);
                    Collections.addAll(infant, 0, 0, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 2 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1, 3, 1);
                    Collections.addAll(child, 1, 0, 1);
                    Collections.addAll(infant, 0, 0, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 2 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1, 1, 3);
                    Collections.addAll(child, 1, 0, 1);
                    Collections.addAll(infant, 1, 1, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 2 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 1, 3);
                    Collections.addAll(child, 1, 1, 0);
                    Collections.addAll(infant, 1, 1, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 2 && numberOfInfant == 4) {
                    Collections.addAll(adult, 3, 1, 1);
                    Collections.addAll(child, 0, 2, 0);
                    Collections.addAll(infant, 2, 1, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 2 && numberOfInfant == 5) {
                    Collections.addAll(adult, 3, 1, 1);
                    Collections.addAll(child, 0, 1, 1);
                    Collections.addAll(infant, 3, 1, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 3 && numberOfInfant == 0) {
                    Collections.addAll(adult, 2, 1, 2);
                    Collections.addAll(child, 1, 1, 1);
                    Collections.addAll(infant, 0, 0, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 3 && numberOfInfant == 1) {
                    Collections.addAll(adult, 2, 1, 2);
                    Collections.addAll(child, 0, 2, 1);
                    Collections.addAll(infant, 1, 0, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 3 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1, 2, 2);
                    Collections.addAll(child, 0, 2, 1);
                    Collections.addAll(infant, 1, 0, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 3 && numberOfInfant == 3) {
                    Collections.addAll(adult, 1, 2, 2);
                    Collections.addAll(child, 1, 0, 2);
                    Collections.addAll(infant, 2, 1, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 3 && numberOfInfant == 4) {
                    Collections.addAll(adult, 2, 2, 1);
                    Collections.addAll(child, 2, 1, 0);
                    Collections.addAll(infant, 1, 2, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 3 && numberOfInfant == 5) {
                    Collections.addAll(adult, 2, 2, 1);
                    Collections.addAll(child, 1, 1, 1);
                    Collections.addAll(infant, 2, 2, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 4 && numberOfInfant == 0) {
                    Collections.addAll(adult, 1, 3, 1);
                    Collections.addAll(child, 2, 1, 1);
                    Collections.addAll(infant, 0, 0, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 4 && numberOfInfant == 1) {
                    Collections.addAll(adult, 1, 3, 1);
                    Collections.addAll(child, 1, 1, 2);
                    Collections.addAll(infant, 1, 0, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 4 && numberOfInfant == 2) {
                    Collections.addAll(adult, 3, 1, 1);
                    Collections.addAll(child, 1, 2, 1);
                    Collections.addAll(infant, 0, 1, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 4 && numberOfInfant == 3) {
                    Collections.addAll(adult, 3, 1, 1);
                    Collections.addAll(child, 2, 1, 1);
                    Collections.addAll(infant, 3, 0, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 4 && numberOfInfant == 4) {
                    Collections.addAll(adult, 1, 1, 3);
                    Collections.addAll(child, 1, 1, 2);
                    Collections.addAll(infant, 1, 1, 2);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 4 && numberOfInfant == 5) {
                    Collections.addAll(adult, 1, 1, 3);
                    Collections.addAll(child, 2, 1, 1);
                    Collections.addAll(infant, 1, 1, 3);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 5 && numberOfInfant == 0) {
                    Collections.addAll(adult, 2, 1, 2);
                    Collections.addAll(child, 3, 1, 1);
                    Collections.addAll(infant, 0, 0, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 5 && numberOfInfant == 1) {
                    Collections.addAll(adult, 2, 1, 2);
                    Collections.addAll(child, 1, 3, 1);
                    Collections.addAll(infant, 1, 0, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 5 && numberOfInfant == 2) {
                    Collections.addAll(adult, 1, 2, 2);
                    Collections.addAll(child, 1, 1, 3);
                    Collections.addAll(infant, 1, 0, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 5 && numberOfInfant == 3) {
                    Collections.addAll(adult, 2, 2, 1);
                    Collections.addAll(child, 3, 1, 1);
                    Collections.addAll(infant, 1, 1, 1);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 5 && numberOfInfant == 4) {
                    Collections.addAll(adult, 3, 1, 1);
                    Collections.addAll(child, 1, 2, 2);
                    Collections.addAll(infant, 3, 1, 0);
                    break;
                } else if  (numberOfAdult == 5 && numberOfChild == 5 && numberOfInfant == 5) {
                    Collections.addAll(adult, 1, 1, 3);
                    Collections.addAll(child, 2, 2, 1);
                    Collections.addAll(infant, 1, 1, 3);
                    break;
                } else {
                    throw new Exception("Este cenário com " + numberOfRoom + " quartos, " + numberOfAdult + " adulto(s)," + numberOfChild + " criança(s) e " + numberOfInfant + " bebê(s) não está mapeado na automação");
                }

            default:
                throw new Exception("Os cenários com " + numberOfRoom + " quartos ainda não estão mapeados");
        }


        Collections.addAll(passenger, adult, child, infant);
        System.out.println("ArrayList dos passageiros: " + passenger);

        return passenger;
    }

}
