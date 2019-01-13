package org.inferred.freebuilder.processor.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * API for parsing Java source with callbacks.
 */
class SourceParser {

  /**
   * Receive notifications of gross Java structure events.
   */
  interface EventHandler {
    void onTypeBlockStart(String keyword, String simpleName);
    void onOtherBlockStart();
    void onBlockEnd();
  }

  private static final Pattern TYPE = Pattern.compile(
      "\\b(class|interface|enum|@interface)\\s*(\\w+)");

  private enum State {
    CODE,
    SLASH,
    STRING_LITERAL,
    STRING_LITERAL_ESCAPE,
    CHAR_LITERAL,
    CHAR_LITERAL_ESCAPE,
    LINE_COMMENT,
    BLOCK_COMMENT,
    BLOCK_COMMENT_STAR
  }

  private final EventHandler eventHandler;
  private final StringBuilder statement;
  private State state;

  SourceParser(EventHandler eventHandler) {
    this.eventHandler = eventHandler;
    statement = new StringBuilder();
    state = State.CODE;
  }

  public void parse(char c) {
    switch (state) {
      case CODE:
      case SLASH:
        statement.append(c);
        switch (c) {
          case '{':
            statement.deleteCharAt(statement.length() - 1);
            onBlockStart(statement.toString().trim());
            statement.setLength(0);
            break;
          case '}':
            eventHandler.onBlockEnd();
            statement.setLength(0);
            break;
          case ';':
            statement.setLength(0);
            break;
          case '"':
            state = State.STRING_LITERAL;
            break;
          case '/':
            if (state == State.CODE) {
              state = State.SLASH;
            } else {
              state = State.LINE_COMMENT;
              statement.delete(statement.length() - 2, statement.length());
            }
            break;
          case '*':
            if (state == State.CODE) {
              state = State.CODE;
            } else {
              state = State.BLOCK_COMMENT;
              statement.delete(statement.length() - 2, statement.length());
            }
            break;
        }
        break;

      case STRING_LITERAL:
        switch (c) {
          case '\\':
            state = State.STRING_LITERAL_ESCAPE;
            break;

          case '"':
            statement.append(c);
            state = State.CODE;
            break;
        }
        break;

      case STRING_LITERAL_ESCAPE:
        statement.append(c);
        state = State.STRING_LITERAL;
        break;

      case CHAR_LITERAL:
        switch (c) {
          case '\\':
            state = State.CHAR_LITERAL_ESCAPE;
            break;

          case '\'':
            statement.append(c);
            state = State.CODE;
            break;
        }
        break;

      case CHAR_LITERAL_ESCAPE:
        state = State.CHAR_LITERAL;
        break;

      case LINE_COMMENT:
        if (c == '\n') {
          state = State.CODE;
        }
        break;

      case BLOCK_COMMENT:
      case BLOCK_COMMENT_STAR:
        switch (c) {
          case '*':
            state = State.BLOCK_COMMENT_STAR;
            break;

          case '/':
            if (state == State.BLOCK_COMMENT_STAR) {
              state = State.CODE;
            }
            break;

          default:
            state = State.BLOCK_COMMENT;
            break;
        }
        break;
    }
  }

  private void onBlockStart(CharSequence chars) {
    Matcher typeMatcher = TYPE.matcher(chars);
    if (typeMatcher.find()) {
      eventHandler.onTypeBlockStart(typeMatcher.group(1), typeMatcher.group(2));
      return;
    }
    eventHandler.onOtherBlockStart();
  }
}