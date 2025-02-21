package edu.grinnell.csc207.texteditor;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {
    private char[] buffer;
    private int gapStart;
    private int gapEnd;

    /**
     * Constructs a new, empty GapBuffer with an initial capacity.
     */
    public GapBuffer() {
        buffer = new char[10];
        gapStart = 0;
        gapEnd = buffer.length;
    }

    /**
     * Inserts a character at the cursor position.
     * 
     * @param ch The character to insert.
     */
    public void insert(char ch) {
        if (gapStart == gapEnd) {
            expandBuffer();
        }
        buffer[gapStart++] = ch;
        System.out.println("Inserted: " + ch + ", Buffer: " + this.toString()); // Debug line

    }

    /**
     * Deletes the character before the cursor.
     */
    public void delete() {
        if (gapStart > 0) {
            gapStart--;
        }
    }

    /**
     * Moves the cursor one position to the left.
     */
    public void moveLeft() {
        if (gapStart > 0) {
            buffer[--gapEnd] = buffer[--gapStart];
        }
    }

    /**
     * Moves the cursor one position to the right.
     */
    public void moveRight() {
        if (gapEnd < buffer.length) {
            buffer[gapStart++] = buffer[gapEnd++];
        }
    }

    /**
     * Returns the cursor position.
     * 
     * @return The current cursor position.
     */
    public int getCursorPosition() {
        return gapStart;
    }

    /**
     * Returns the size of the text in the buffer.
     * 
     * @return The size of the buffer excluding the gap.
     */
    public int getSize() {
        return buffer.length - (gapEnd - gapStart);
    }

    /**
     * Retrieves the character at the specified index.
     * 
     * @param i The index of the character.
     * @return The character at the given index.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public char getChar(int i) {
        if (i < 0 || i >= getSize()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return i < gapStart ? buffer[i] : buffer[i + (gapEnd - gapStart)];
    }

    /**
     * Returns the contents of the buffer as a string.
     * 
     * @return The string representation of the buffer.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < gapStart; i++) {
            sb.append(buffer[i]);
        }
        for (int i = gapEnd; i < buffer.length; i++) {
            sb.append(buffer[i]);
        }
        System.out.println("Generated String: " + sb.toString()); // Debug

        return sb.toString();
    }

    /**
     * Expands the buffer when the gap is full.
     */
    private void expandBuffer() {
        int newSize = buffer.length * 2;
        char[] newBuffer = new char[newSize];
        System.arraycopy(buffer, 0, newBuffer, 0, gapStart);
        System.arraycopy(buffer, gapEnd, newBuffer, newSize - (buffer.length - gapEnd), buffer.length - gapEnd);
        gapEnd = newSize - (buffer.length - gapEnd);
        buffer = newBuffer;
    }
}
