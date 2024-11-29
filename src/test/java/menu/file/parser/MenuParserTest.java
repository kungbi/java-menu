package menu.file.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import menu.file.reader.CsvReader;
import org.junit.jupiter.api.Test;

class MenuParserTest {

    @Test
    void 상품_정보_읽기() throws IOException {
        MenuParser menuParser = new MenuParser(CsvReader.of("menu.md", false), MenuField.class);

        System.out.println(menuParser.nextLine());
    }

}