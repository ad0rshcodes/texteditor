package edu.grinnell.csc207.texteditor;

import org.junit.jupiter.api.Test;
import net.jqwik.api.*;
import net.jqwik.api.constraints.CharRange;
import net.jqwik.api.constraints.IntRange;
import static org.junit.jupiter.api.Assertions.*;

public class GapBufferTests {

    // Unit Tests

    @Test
    public void testEmptyBuffer() {
        GapBuffer buffer = new GapBuffer();
        assertEquals("", buffer.toString());
        assertEquals(0, buffer.getCursorPosition());
    }

    @Test
    public void testInsertAtBeginning() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('a');
        buffer.insert('b');
        assertEquals("ab", buffer.toString());
        assertEquals(2, buffer.getCursorPosition());
    }

    @Test
    public void testInsertAtMiddle() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.moveLeft();
        buffer.insert('c');
        assertEquals("acb", buffer.toString());
        assertEquals(2, buffer.getCursorPosition());
    }

    @Test
    public void testDeleteAtBeginning() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.moveLeft();
        buffer.delete();
        assertEquals("b", buffer.toString());
        assertEquals(0, buffer.getCursorPosition());
    }

    @Test
    public void testDeleteAtEnd() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.delete();
        assertEquals("a", buffer.toString());
        assertEquals(1, buffer.getCursorPosition());
    }

    @Test
    public void testMoveCursorOutOfBounds() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.moveRight(); // Trying to move cursor past the end
        assertEquals(2, buffer.getCursorPosition()); // Cursor will stay at position 2
        buffer.moveLeft();
        buffer.moveLeft();
        buffer.moveLeft(); // Trying to move cursor before the beginning
        assertEquals(0, buffer.getCursorPosition());
    }

    // Property-Based Test

    @Property
    void testInsertAndDeleteProperty(
            @ForAll @CharRange(from = 'a', to = 'z') char ch,
            @ForAll @IntRange(min = 1, max = 100) int insertCount,
            @ForAll @IntRange(min = 0, max = 100) int deleteCount) {
        GapBuffer buffer = new GapBuffer();

        for (int i = 0; i < insertCount; i++) {
            buffer.insert(ch);
        }

        // Delete `deleteCount` times (but not more than the buffer size)
        for (int i = 0; i < deleteCount && buffer.getCursorPosition() > 0; i++) {
            buffer.delete();
        }

        // Calculate the expected buffer size
        int expectedSize = Math.max(0, insertCount - deleteCount);

        // Verify the buffer size and contents
        assertEquals(expectedSize, buffer.toString().length());
        if (expectedSize > 0) {
            assertEquals(ch, buffer.toString().charAt(0)); // First character should be `ch`
        }
    }
}