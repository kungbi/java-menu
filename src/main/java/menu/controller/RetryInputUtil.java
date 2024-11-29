package menu.controller;


import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import menu.domain.Coaches;
import menu.domain.Menu;
import menu.view.InputView;
import menu.view.OutputView;

public class RetryInputUtil {
    private final InputParser inputParser;

    public RetryInputUtil(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    public Coaches getCoaches() {
        return retryLogics(InputView::getCoachesNames, inputParser::parseCoaches);
    }

    public List<Menu> getDislikeMenus(String coachName) {
        return retryLogics(() -> InputView.getDislikeMenuNames(coachName), inputParser::parseMenus);
    }


    private <T> T retryLogics(Supplier<String> userInputReader, Function<String, T> parser) {
        while (true) {
            try {
                String userInput = userInputReader.get();
                return parser.apply(userInput);
            } catch (IllegalArgumentException error) {
                OutputView.printError(error.getMessage());
            }
        }
    }

}
