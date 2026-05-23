**creating a simple text editor without using built-in javafx textboxes**

data structure options: gap buffer, ropey, piece table.
decision: gap buffer, easiest to implement.

TODO: replace StringBuilder with GapBuffer
TODO: implement up and down arrow key handlers 

features to implement (not ordered by importance):
- navigable cursor through text with keyboard
- adding and deleting text at cursor position
- text wrapping
- window resizing support
- text highlighting + manipulation of highlighted text
- saving, creating, and opening a text file.
- various shortcut support eg. ctrl + backspace
- enter key support
- font type, size, and color choices