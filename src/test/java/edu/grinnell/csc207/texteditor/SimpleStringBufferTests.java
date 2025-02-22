package edu.grinnell.csc207.texteditor;

import org.junit.jupiter.api.Test;
import net.jqwik.api.*;
import net.jqwik.api.constraints.CharRange;
import net.jqwik.api.constraints.IntRange;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleStringBufferTests {

    // UNIT TESTS

    @Test
    public void testEmptyBuffer() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        assertEquals("", buffer.toString());
        assertEquals(0, buffer.getCursorPosition());
    }

    @Test
    public void testInsertAtBeginning() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.insert('a');
        buffer.insert('b');
        assertEquals("ab", buffer.toString());
        assertEquals(2, buffer.getCursorPosition());
    }

    @Test
    public void testInsertAtMiddle() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.moveBackwards();
        buffer.insert('c');
        assertEquals("acb", buffer.toString());
        assertEquals(2, buffer.getCursorPosition());
    }

    @Test
    public void testDeleteAtBeginning() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.moveBackwards();
        buffer.delete();
        assertEquals("b", buffer.toString());
        assertEquals(0, buffer.getCursorPosition());
    }

    @Test
    public void testDeleteAtEnd() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.delete();
        assertEquals("a", buffer.toString());
        assertEquals(1, buffer.getCursorPosition());
    }

    @Test
    public void testMoveCursorOutOfBounds() {
        SimpleStringBuffer buffer = new SimpleStringBuffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.moveForwards();
        assertEquals(2, buffer.getCursorPosition()); // cursor shouldn't go off the edge
        buffer.moveBackwards();
        buffer.moveBackwards();
        buffer.moveBackwards();
        assertEquals(0, buffer.getCursorPosition());
    }

    // Property-Based Test
    @Property
    void testInsertAndDeleteProperty(
            @ForAll @CharRange(from = 'a', to = 'z') char ch,
            @ForAll @IntRange(min = 1, max = 100) int insertCount,
            @ForAll @IntRange(min = 0, max = 100) int deleteCount) {
        SimpleStringBuffer buffer = new SimpleStringBuffer();

        for (int i = 0; i < insertCount; i++) {
            buffer.insert(ch);
        }

        for (int i = 0; i < deleteCount && buffer.getCursorPosition() > 0; i++) {
            buffer.delete();
        }

        int expectedSize = Math.max(0, insertCount - deleteCount);

        // Verifying the buffer size and contents
        assertEquals(expectedSize, buffer.toString().length());
        if (expectedSize > 0) {
            assertEquals(ch, buffer.getChar(0)); // First character should be `ch`
        }
    }
}