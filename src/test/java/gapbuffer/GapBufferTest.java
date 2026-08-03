package gapbuffer;

import com.captchapro.texteditor.model.GapBuffer;
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
        assertEquals(32, gapBuffer.getGapLength(), "new length of gap");
    }

    @Test
    @DisplayName("deleteGlyphBehind test")
    void testDeleteGlyphBehind() {
        String input = "Hello_World";
        for (int i = 0; i < input.length(); i++) {
            gapBuffer.insertGlyph(input.charAt(i));
        }

        gapBuffer.deleteGlyphBehind();
        System.out.println(gapBuffer.getBuffer());
    }
}