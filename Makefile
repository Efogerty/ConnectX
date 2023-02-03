default: Cpsc2150/extendedConnectX/AbsGameBoard.java Cpsc2150/extendedConnectX/BoardPosition.java Cpsc2150/extendedConnectX/GameBoard.java Cpsc2150/extendedConnectX/GameScreen.java Cpsc2150/extendedConnectX/IGameBoard.java Cpsc2150/extendedConnectX/GameScreen.java Cpsc2150/extendedConnectX/GameBoardMem.java
	javac Cpsc2150/extendedConnectX/AbsGameBoard.java Cpsc2150/extendedConnectX/BoardPosition.java Cpsc2150/extendedConnectX/GameBoard.java Cpsc2150/extendedConnectX/GameScreen.java Cpsc2150/extendedConnectX/IGameBoard.java Cpsc2150/extendedConnectX/GameBoardMem.java

run: Cpsc2150/extendedConnectX/AbsGameBoard.class Cpsc2150/extendedConnectX/BoardPosition.class Cpsc2150/extendedConnectX/GameBoard.class Cpsc2150/extendedConnectX/GameScreen.class Cpsc2150/extendedConnectX/IGameBoard.class Cpsc2150/extendedConnectX/GameBoardMem.class
	java Cpsc2150.extendedConnectX.GameScreen

test: Cpsc2150/extendedConnectX/TestGameBoardMem.java Cpsc2150/extendedConnectX/AbsGameBoard.java Cpsc2150/extendedConnectX/BoardPosition.java Cpsc2150/extendedConnectX/GameBoard.java Cpsc2150/extendedConnectX/TestGameBoard.java Cpsc2150/extendedConnectX/IGameBoard.java Cpsc2150/extendedConnectX/GameBoardMem.java
	javac -cp .:/usr/share/java/junit4.jar Cpsc2150/extendedConnectX/TestGameBoardMem.java Cpsc2150/extendedConnectX/AbsGameBoard.java Cpsc2150/extendedConnectX/BoardPosition.java Cpsc2150/extendedConnectX/GameBoard.java Cpsc2150/extendedConnectX/TestGameBoard.java Cpsc2150/extendedConnectX/IGameBoard.java Cpsc2150/extendedConnectX/GameBoardMem.java

testGB: Cpsc2150/extendedConnectX/AbsGameBoard.class Cpsc2150/extendedConnectX/BoardPosition.class Cpsc2150/extendedConnectX/GameBoard.class Cpsc2150/extendedConnectX/TestGameBoard.class Cpsc2150/extendedConnectX/IGameBoard.class Cpsc2150/extendedConnectX/GameBoardMem.java
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore Cpsc2150.extendedConnectX.TestGameBoard

testGBMem: Cpsc2150/extendedConnectX/AbsGameBoard.class Cpsc2150/extendedConnectX/BoardPosition.class Cpsc2150/extendedConnectX/GameBoard.class Cpsc2150/extendedConnectX/TestGameBoardMem.class Cpsc2150/extendedConnectX/IGameBoard.class Cpsc2150/extendedConnectX/GameBoardMem.java
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore Cpsc2150.extendedConnectX.TestGameBoardMem

clean:
	rm -f Cpsc2150/extendedConnectX/*.class
