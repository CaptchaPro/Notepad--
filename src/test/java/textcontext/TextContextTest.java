package textcontext;

import com.captchapro.texteditor.model.LineData;
import com.captchapro.texteditor.model.TextContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TextContext class test")
public class TextContextTest {
    private TextContext context;

    @BeforeEach
    void setup() {
        context = new TextContext();
        String input = "12345\nabc\n12345";
        for (int i = 0; i < input.length(); i++) {
            context.insertGlyph(input.charAt(i));
        }
    }

    @Test
    @DisplayName("getPreviousLineLength test")
    void testGetPreviousLineLength() {
        LineData result = context.getPreviousLineLength(8);
        assertEquals(5, result.getLength(), "length");
        assertEquals(0, result.getStart(), "second encountered newline");
        assertEquals(5, result.getEnd(), "first encountered newline");
        //assertEquals(12, gapBuffer.getGoalColumn(), "goal column");
    }

    @Test
    @DisplayName("getNextLineLength test")
    void testGetNextLineLength() {
        LineData result = context.getNextLineLength(context.getCursorPosition() / 2);
        assertEquals(5, result.getLength(), "length");
    }

    @Test
    @DisplayName("charAt test")
    void testCharAt() {
        //context.moveCursor(2);
        char result = context.charAt(context.getCursorPosition() - 1);
        assertEquals('5', result);
    }
}