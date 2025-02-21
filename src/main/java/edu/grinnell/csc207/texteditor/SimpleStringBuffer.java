package edu.grinnell.csc207.texteditor

/**
 * A naive implementation of a text buffer using a String.
 */
class SimpleStringBuffer {
    private StringBuilder buffer;
    private int cursor;

    /**
     * Constructs a new, empty SimpleStringBuffer.
     */
    public SimpleStringBuffer() {
        buffer = new StringBuilder();
        cursor = 0;
    }

    /**
     * Inserts a character at the cursor position.
     * 
     * @param ch The character to insert.
     */
    public void insert(char ch) {
        buffer.insert(cursor, ch);
        cursor++;
    }

    /**
     * Deletes the character before the cursor.
     */
    public void delete() {
        if (cursor > 0) {
            buffer.deleteCharAt(--cursor);
        }
    }

    /**
     * Moves the cursor one position to the left.
     */
    public void moveLeft() {
        if (cursor > 0) {
            cursor--;
        }
    }

    /**
     * Moves the cursor one position to the right.
     */
    public void moveRight() {
        if (cursor < buffer.length()) {
            cursor++;
        }
    }

    /**
     * Returns the cursor position.
     * 
     * @return The current cursor position.
     */
    public int getCursorPosition() {
        return cursor;
    }

    /**
     * Returns the size of the buffer.
     * 
     * @return The size of the buffer.
     */
    public int getSize() {
        return buffer.length();
    }

    /**
     * Retrieves the character at the specified index.
     * 
     * @param i The index of the character.
     * @return The character at the given index.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public char getChar(int i) {
        if (i < 0 || i >= buffer.length()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return buffer.charAt(i);
    }

    /**
     * Returns the contents of the buffer as a string.
     * 
     * @return The string representation of the buffer.
     */
    @Override
    public String toString() {
        return buffer.toString();
    }
}
