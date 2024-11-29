package menu;

import menu.config.DependencyInjector;
import menu.controller.Controller;

public class Application {
    public static void main(String[] args) {
        DependencyInjector dependencyInjector = new DependencyInjector();
        Controller controller = dependencyInjector.createController();

        controller.run();
    }
}
