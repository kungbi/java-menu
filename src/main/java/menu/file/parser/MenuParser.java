package menu.file.parser;

import menu.file.reader.Reader;

public class MenuParser extends AbstractFieldParser<MenuField> {
    protected MenuParser(Reader reader, Class<MenuField> targetClass) {
        super(reader, targetClass);
    }
}
