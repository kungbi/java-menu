package menu.config;


import menu.controller.Controller;
import menu.controller.InputParser;
import menu.controller.RetryInputUtil;
import menu.file.parser.MenuField;
import menu.file.parser.MenuParser;
import menu.file.reader.CsvReader;
import menu.initializer.MenuInitializer;
import menu.repository.MenuRepository;

public class DependencyInjector {
    public Controller createController() {

        MenuRepository menuRepository = new MenuRepository();
        InputParser inputParser = new InputParser(menuRepository);
        RetryInputUtil retryInputUtil = new RetryInputUtil(inputParser);

        MenuParser menuParser = new MenuParser(CsvReader.of("menu.md", false), MenuField.class);
        MenuInitializer menuInitializer = new MenuInitializer(menuRepository, menuParser);
        menuInitializer.init();

        return new Controller(retryInputUtil, menuRepository);
    }
}
