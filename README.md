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
- [try-catch in java](https://www.w3schools.com/java/java_try_catch.asp)

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

Author: ad0rshcodes <adarsharma.me@gmail.com>
Date: Fri Feb 21 22:30:43 2025 -0600

    added explanation for time complexity.

commit 6f239dc2d475531172a6e63889a24f7f57adf694
Author: ad0rshcodes <adarsharma.me@gmail.com>
Date: Fri Feb 21 22:20:48 2025 -0600

    add test cases for GapBuffer Class

commit a2aead15e7a916d120883c97c897237345946e27
Author: ad0rshcodes <adarsharma.me@gmail.com>
Date: Fri Feb 21 22:15:05 2025 -0600

    add test cases for SimpleStringBuffer Class

commit 6e1f46788d52a26eb6f94e40175bed99dc8c30b6
Author: ad0rshcodes <adarsharma.me@gmail.com>
Date: Fri Feb 21 22:05:30 2025 -0600

    update TextEditor Class

commit aa9943427735a50ac9da474e2e9be64fe3e6b6f9
Author: ad0rshcodes <adarsharma.me@gmail.com>
Date: Fri Feb 21 21:37:49 2025 -0600

    update GapBuffer Class

commit 9930b3b351c3cd95e7829cdb45530aa0eb0df259
Author: ad0rshcodes <adarsharma.me@gmail.com>
Date: Fri Feb 21 21:33:54 2025 -0600

    update SimpleStringBuffer class to use string instead of StringBuilder

commit 26075eda08527fa00d3070fa23963a4dbcc45e02 (origin/main)
Author: ad0rshcodes <adarsharma.me@gmail.com>
Date: Fri Feb 21 20:59:51 2025 -0600

    finish TextEditor.java class

commit 4ce4c9663f58604668fe59b09372dffa11b41fb2
Author: ad0rshcodes <adarsharma.me@gmail.com>
Date: Fri Feb 21 11:18:52 2025 -0600

    add GapBuffer Class, update README, fix SimpleStringBuffer Class

commit 11c9edbb50e2e1492f99e85d30ec7a1f6c229011
Author: ad0rshcodes <adarsharma.me@gmail.com>
Date: Fri Feb 21 11:06:52 2025 -0600

    Add SimpleStringBuffer functions

commit 32a90495f40bd92ce905d4d78fbdab4dbaa6d5f9
Author: Peter-Michael Osera <osera@cs.grinnell.edu>
Date: Thu Feb 13 12:40:05 2025 -0600

    Project files

commit 02dc92144ecc088bcefb4a9798df0934efe300c1
Author: Peter-Michael Osera <osera@cs.grinnell.edu>
Date: Thu Feb 13 12:39:53 2025 -0600

    initial commit
