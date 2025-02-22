package edu.grinnell.csc207.texteditor;

public class SimpleStringBuffer {
    // backing string for the buffer
    private String buffer;
    private int cursor;

    public SimpleStringBuffer() {
        this.buffer = "";
        this.cursor = 0;
    }

    // Inserts a character at the cursor position and moves the cursor forward
    public void insert(char ch) {
        buffer = buffer.substring(0, cursor) + ch + buffer.substring(cursor);
        cursor++;
    }

    // Deletes the character before the cursor and moves the cursor backward
    public void delete() {
        if (cursor > 0 && buffer.length() > 0) {
            buffer = buffer.substring(0, cursor - 1) + buffer.substring(cursor);
            cursor--;
        }
    }

    // Returns the current cursor position
    public int getCursorPosition() {
        return cursor;
    }

    // Moves cursor backward
    public void moveBackwards() {
        if (cursor > 0) {
            cursor--;
        }
    }

    // Moves cursor forward
    public void moveForwards() {
        if (cursor < buffer.length()) {
            cursor++;
        }
    }

    // Returns the character at the specified index
    public char getChar(int i) {
        if (i < 0 || i >= buffer.length()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + i);
        }
        return buffer.charAt(i);
    }

    // Returns the contents of the buffer as a string
    @Override
    public String toString() {
        return buffer;
    }
}