package gapbuffer;

import com.captchapro.texteditor.model.GapBuffer;
import com.captchapro.texteditor.model.LineData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Gap buffer test")
public class GapBufferTest {
    private GapBuffer gapBuffer;

    @BeforeEach
    void setup() {
        gapBuffer = new GapBuffer();
    }

    @Test
    @DisplayName("insertGlyph test")
    void testInsertGlyph() {
        String input = "This_is_a_random_long_test_case";
        for (int i = 0; i < input.length(); i++) {
            gapBuffer.insertGlyph(input.charAt(i));
        }

        System.out.println(gapBuffer.getBuffer());

        assertEquals(input.length(), gapBuffer.getGapStart());
        System.out.println(gapBuffer.getGapStart() + ", " + gapBuffer.getGapEnd() + ", " + gapBuffer.getBuffer().length + "\n");

        String extraInput = "added_string";
        gapBuffer.moveCursor(13);
        for (int i = 0; i < extraInput.length(); i++) {
            gapBuffer.insertGlyph(extraInput.charAt(i));
        }

        System.out.println(gapBuffer.getBuffer());
        System.out.println(gapBuffer.getGapStart() + ", " + gapBuffer.getGapEnd() + ", " + gapBuffer.getBuffer().length);
    }

    @Test
    @DisplayName("growBuffer test")
    void testGrowBuffer() {
        String input = "Extra_long_Over_buffer_size_yo";
        gapBuffer.setBuffer(input);
        gapBuffer.setGapStart(17);
        gapBuffer.setGapEnd(17);
        gapBuffer.growBuffer();

        System.out.println(gapBuffer.getBuffer());

        assertEquals(64, gapBuffer.getBuffer().length, "new length of entire buffer");
        assertEquals(32, gapBuffer.gapLength(), "new length of gap");
    }

    @Test
    @DisplayName("deleteGlyphBehind test")
    void testDeleteGlyphBehind() {
        String input = "Hello_World";
        for (int i = 0; i < input.length(); i++) {
            gapBuffer.insertGlyph(input.charAt(i));
        }

        gapBuffer.deleteGlyphBehind(gapBuffer.getGapStart());
        System.out.println(gapBuffer.getBuffer());
    }

    @Test
    @DisplayName("getPreviousLineLength test")
    void testGetPreviousLineLength() {
        String input = "12345\nabc\n12345";
        for (int i = 0; i < input.length(); i++) {
            gapBuffer.insertGlyph(input.charAt(i));
        }

        LineData result = gapBuffer.getPreviousLineLength(8);
        assertEquals(5, result.getLength(), "length");
        assertEquals(0, result.getStart(), "second encountered newline");
        assertEquals(5, result.getEnd(), "first encountered newline");
        //assertEquals(12, gapBuffer.getGoalColumn(), "goal column");
    }

    @Test
    @DisplayName("getNextLineLength test")
    void testGetNextLineLength() {
        String input = "blah blah\nHello_World\nanother line";
        for (int i = 0; i < input.length(); i++) {
            gapBuffer.insertGlyph(input.charAt(i));
        }

        LineData result = gapBuffer.getNextLineLength(gapBuffer.getCursorPosition() / 2);
        assertEquals(12, result.getLength(), "length");
    }
}