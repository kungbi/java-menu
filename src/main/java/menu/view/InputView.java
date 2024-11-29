package menu.view;


import java.util.Scanner;

public class InputView {
    private static Scanner scanner;

    public static String getCoachesNames() {
        System.out.println("코치의 이름을 입력해 주세요. (, 로 구분)");
        return getUserInput();
    }

    public static String getDislikeMenuNames(String coachName) {
        System.out.printf("%s(이)가 못 먹는 메뉴를 입력해 주세요.", coachName);
        printNewLine();
        return getUserInput();
    }

    private static void printNewLine() {
        System.out.println();
    }

    private static String getUserInput() {
        String input = getScanner().nextLine();
        System.out.println();
        return input;
    }

    private static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

}
