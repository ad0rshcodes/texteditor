package edu.grinnell.csc207.texteditor;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TerminalPosition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The driver for the TextEditor Application.
 */
public class TextEditor {

    private GapBuffer buffer;
    private Screen screen;

    /**
     * Constructs a new TextEditor with the given file path.
     * 
     * @param filePath the path to the file to load (or create)
     * @throws IOException if I/O error occurs
     */
    public TextEditor(String filePath) throws IOException {
        buffer = new GapBuffer();

        // Load the file contents into the buffer
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            String fileContents = Files.readString(path);
            for (char ch : fileContents.toCharArray()) { // used a different format of for loop for concise code.
                buffer.insert(ch);
            }
        }

        // Initialize the Lanterna screen
        DefaultTerminalFactory factory = new DefaultTerminalFactory();
        screen = factory.createScreen();
        screen.startScreen();
    }

    /**
     * Renders the buffer contents to the screen.
     * 
     * @throws IOException if an I/O error occurs
     */
    private void drawBuffer() throws IOException {
        screen.clear();

        String text = buffer.toString();
        for (int i = 0; i < text.length(); i++) {
            screen.setCharacter(i, 0, TextCharacter.fromCharacter(text.charAt(i))[0]);
        }

        screen.setCursorPosition(new TerminalPosition(buffer.getCursorPosition(), 0));

        // Refresh the screen to display changes
        screen.refresh();
    }

    /**
     * Runs the text editor loop.
     * 
     * @throws IOException if an I/O error occurs
     */
    public void run() throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            drawBuffer();

            // user input
            KeyStroke keyStroke = screen.readInput();

            switch (keyStroke.getKeyType()) {
                case Character:
                    // Insert into the buffer
                    buffer.insert(keyStroke.getCharacter());
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
    }

    /**
     * Saves the buffer contents to the given file path.
     * 
     * @param filePath the path to the file to save
     * @throws IOException if an I/O error occurs
     */
    public void save(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.writeString(path, buffer.toString());
    }

    /**
     * The main entry point for the TextEditor application.
     * 
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java TextEditor <filename>");
            System.exit(1);
        }

        // Have to use try-catch for the program to compile.
        try {
            // Create and run the text editor
            TextEditor editor = new TextEditor(args[0]);
            editor.run();
            editor.save(args[0]);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
