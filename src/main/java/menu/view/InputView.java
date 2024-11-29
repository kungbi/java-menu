package menu.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static String getCoachesNames() {
        System.out.println("코치의 이름을 입력해 주세요. (, 로 구분)");
        return getUserInput();
    }

    public static String getDislikeMenuNames(String coachName) {
        System.out.printf("%S(이)가 못 먹는 메뉴를 입력해 주세요.", coachName);
        printNewLine();
        return getUserInput();
    }

    private static void printNewLine() {
        System.out.println();
    }

    private static String getUserInput() {
        return Console.readLine();
    }

}
