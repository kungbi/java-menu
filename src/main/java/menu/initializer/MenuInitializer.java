package menu.initializer;

import menu.domain.Menu;
import menu.file.parser.GenericParser;
import menu.file.parser.MenuField;
import menu.repository.Repository;

public class MenuInitializer extends AbstractInitializer<Menu, MenuField> {
    public MenuInitializer(Repository<Menu> repository, GenericParser<MenuField> parser) {
        super(repository, parser);
    }

    @Override
    protected Menu mapToDomain(MenuField field) {
        return new Menu(field.name(), field.category());
    }
}
