package florinczi.projects.chessgame.pieces;

public enum PlayerColor {
  WHITE,
  BLACK;

  public PlayerColor getOpponent() {
    return this == WHITE ? BLACK : WHITE;
}
}
