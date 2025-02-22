package edu.grinnell.csc207.texteditor;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {
    private static final int INITIAL_CAPACITY = 10;

    private char[] buffer; // backing array for the buffer

    private int gapStart; // start index of the gap

    private int gapEnd; // end index of the gap

    private int cursor; // current position of the cursor

    /**
     * Constructor: Initialises the buffer with INITIAL_CAPACITY
     * Initialises the cursor position, gapstart with 0.
     */
    public GapBuffer() {
        buffer = new char[INITIAL_CAPACITY];
        gapStart = 0;
        gapEnd = buffer.length;
        cursor = 0;
    }

    /**
     * Inserts a character at the current cursor position.
     * 
     * @param ch the character to insert
     */
    public void insert(char ch) {
        // If the gap is empty, expand the buffer
        if (gapStart == gapEnd) {
            expandBuffer();
        }

        buffer[gapStart] = ch;
        gapStart++;
        cursor++;
    }

    /**
     * Deletes the character before the cursor.
     */
    public void delete() {
        if (cursor > 0) {
            gapStart--;
            cursor--;
        }
    }

    /**
     * Returns the current cursor position.
     * 
     * @return the cursor position
     */
    public int getCursorPosition() {
        return cursor;
    }

    /**
     * Moves the cursor one position to the left.
     */
    public void moveLeft() {
        if (cursor > 0) {
            buffer[gapEnd - 1] = buffer[gapStart - 1];
            gapStart--;
            gapEnd--;
            cursor--;
        }
    }

    /**
     * Moves the cursor one position to the right.
     */
    public void moveRight() {
        if (cursor < buffer.length - (gapEnd - gapStart)) {

            buffer[gapStart] = buffer[gapEnd];
            gapStart++;
            gapEnd++;
            cursor++;
        }
    }

    /**
     * Returns the size of the buffer (number of characters).
     * 
     * @return the size of the buffer
     */
    public int getSize() {
        return gapStart + (buffer.length - gapEnd);
    }

    /**
     * Returns the character at the specified index.
     * 
     * @param i the index of the character to retrieve
     * @return the character at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public char getChar(int i) {
        if (i < 0 || i >= getSize()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + i);
        }

        // If the index is before the gap, return the character from the left segment
        if (i < gapStart) {
            return buffer[i];
        }
        // else, return the character from the right segment
        return buffer[gapEnd + (i - gapStart)];
    }

    /**
     * Returns the contents of the buffer as a string.
     * 
     * @return the buffer contents
     */
    @Override
    public String toString() {
        return new String(buffer, 0, gapStart) + new String(buffer, gapEnd, buffer.length - gapEnd);
    }

    /**
     * Expands the buffer to make room for more characters.
     */
    private void expandBuffer() {
        // new buffer with double capacity
        char[] newBuffer = new char[buffer.length * 2];

        System.arraycopy(buffer, 0, newBuffer, 0, gapStart);

        System.arraycopy(buffer, gapEnd, newBuffer, newBuffer.length - (buffer.length - gapEnd),
                buffer.length - gapEnd);

        gapEnd = newBuffer.length - (buffer.length - gapEnd);

        buffer = newBuffer;
    }
}