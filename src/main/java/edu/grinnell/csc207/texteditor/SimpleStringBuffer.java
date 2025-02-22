package edu.grinnell.csc207.texteditor;

/**
 * SimpleStringBuffer Class: Uses String data structure to make text editor.
 */

public class SimpleStringBuffer {
    // backing string for the buffer
    private String buffer;

    private int cursor;

    /**
     * Constructor: Initialises the buffer and set the cursor to the initial
     * position(0).
     */
    public SimpleStringBuffer() {
        this.buffer = "";
        this.cursor = 0;
    }

    /**
     * Adds the given character at the current cursor position and increases the
     * cursor position by one.
     *
     * @param ch the character to insert
     */
    public void insert(char ch) {
        buffer = buffer.substring(0, cursor) + ch + buffer.substring(cursor);
        cursor++;
    }

    /**
     * Deletes the character before the current cursor position and moves the cursor
     * backward.
     */
    public void delete() {
        if (cursor > 0 && buffer.length() > 0) {
            buffer = buffer.substring(0, cursor - 1) + buffer.substring(cursor);
            cursor--;
        }
    }

    /**
     * Returns the current cursor position.
     *
     * @return the current position of the cursor
     */
    public int getCursorPosition() {
        return cursor;
    }

    /**
     * Moves the cursor back by one position.
     */
    public void moveLeft() {
        if (cursor > 0) {
            cursor--;
        }
    }

    /**
     * Moves the cursor forward by one position.
     */
    public void moveRights() {
        if (cursor < buffer.length()) {
            cursor++;
        }
    }

    /**
     * Returns the character at the specified index in the buffer.
     *
     * @param i the index of the character to retrieve
     * @return the character at the specified index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public char getChar(int i) {
        if (i < 0 || i >= buffer.length()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + i);
        }
        return buffer.charAt(i);
    }

    /**
     * Returns the contents of the buffer as a string.
     *
     * @return the buffer as a string
     */
    @Override
    public String toString() {
        return buffer;
    }

    /**
     * Returns the size of the buffer, which is the number of characters currently
     * stored.
     *
     * @return the number of characters in the buffer
     */
    public int getSize() {
        return buffer.length();
    }
}