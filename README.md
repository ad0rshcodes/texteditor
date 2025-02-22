# CSC 207: Text Editor

**Author**: _Adarsh Sharma_

## Resources Used

- git version 2.39.5 (Apple Git-154)
- github.com
- VS Code 1.97.2
- Apache Maven 3.9.9
- java 21.0.1 2023-10-17 LTS
- [TTAP: Data Structures - Lab Manual](https://osera.cs.grinnell.edu/ttap/data-structures-labs/text-editor.html)
- [TTAP: Data Structures](https://osera.cs.grinnell.edu/ttap/data-structures/)
- try-catch in java

## Part 2: Analyzing the Simple String Buffer

### `insert` Method:

1. It creates a new `String` by concatenating the substring before the cursor, the new character, and the substring after the cursor.
2. This operation requires copying the entire contents of the buffer into a new `String`.

#### Runtime Analysis

- **Input**: A character `ch` to insert and the current buffer of size `n`.
- **Critical Operations**:
  - Creating substrings: O(n).
  - Concatenating strings: O(n).
- **Total Runtime**: O(n).

The `insert` method has a time complexity of O(n) , where n is the size of the buffer.

### Reasoning

Since Java strings are immutable, every insertion operation creates a new `String` object, copying all characters from the original buffer. For a buffer of size `n`, this results in O(n) time per insertion. In contrast, a more efficient data structure (like a gap buffer) can perform insertions in O(1) amortized time by avoiding unnecessary copying.

## Changelog

_(TODO: fill me in with a log of your committed changes)_
