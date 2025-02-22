package edu.grinnell.csc207.texteditor;

import java.io.IOException;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.TerminalPosition;

/**
 * A simple terminal-based text editor using the Lanterna library.
 */
public class TextEditor {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java TextEditor <filename>");
            System.exit(1);
        }

        GapBuffer buffer = new GapBuffer();
        try {
            Screen screen = new DefaultTerminalFactory().createScreen();
            System.out.println("testing");
            System.out.println("testing 02");
            screen.startScreen();
            boolean isRunning = true;

            while (isRunning) {
                drawBuffer(buffer, screen);

                System.out.println("Waiting for input...");
                KeyStroke stroke = screen.readInput();
                System.out.println("Key Pressed: " + stroke);

                System.out.println("Current Buffer: " + buffer.toString());

                switch (stroke.getKeyType()) {
                    case Character:
                        buffer.insert(stroke.getCharacter());
                        break;
                    case Backspace:
                        buffer.delete();
                        break;
                    case ArrowLeft:
                        buffer.moveLeft();
                        break;
                    case ArrowRight:
                        buffer.moveRight();
                        break;
                    case Escape:
                        isRunning = false;
                        break;
                    default:
                        break;
                }
            }
            screen.stopScreen();
        } catch (IOException e) {
            System.err.println("Error initializing the screen.");
        }

        System.out.println("Current Buffer: " + buffer.toString());

    }

    /**
     * Renders the text buffer onto the screen.
     * 
     * @param buffer The gap buffer containing text.
     * @param screen The screen to render to.
     * @throws IOException If an error occurs while updating the screen.
     */
    private static void drawBuffer(GapBuffer buffer, Screen screen) throws IOException {
        System.out.println("drawBuffer called!"); // Debugging line

        screen.clear();
        String content = buffer.toString();

        System.out.println("Buffer content: '" + content + "'"); // Check if buffer has content

        for (int i = 0; i < content.length(); i++) {
            screen.setCharacter(i, 0, com.googlecode.lanterna.TextCharacter.fromCharacter(content.charAt(i)));
        }
        screen.setCursorPosition(new TerminalPosition(buffer.getCursorPosition(), 0));
        screen.refresh();
    }
}

// Existing GapBuffer and SimpleStringBuffer classes below...
