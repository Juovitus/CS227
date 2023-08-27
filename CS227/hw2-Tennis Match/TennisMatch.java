package hw2;

/**
 * @author Noah Hoss
 * 
 *         Creates a Tennis Match
 *         @param player1 - object of player 1
 *         @param player2 - object of player 2
 *         @param server - object of server for ease of use
 *         @param receiver - object of receiver for ease of use
 *         @param ballTo - object of ballTo for ease of use
 *         @param ballFrom - object of ballFrom for ease of use
 *         @param score - string containing the weird convenctions of scorekeeping in tennis
 *         @param winner - string of the winner
 *         @param bestOfThree - is the game best of three
 *         @param ballInPlay - is the ball in play
 *         @param ballInServe - is the ball in serve
 *         @param fault - is the ball faulted
 *         @param gameOver- is the game over
 *         @param faultCounter - number of faults
 */

public class TennisMatch {

	private TennisPlayer player1;
	private TennisPlayer player2;
	private TennisPlayer server;
	private TennisPlayer receiver;
	private TennisPlayer ballTo;
	private TennisPlayer ballFrom;
	private String score;
	private String winner;
	private boolean bestOfThree;
	private boolean ballInPlay;
	private boolean ballInServe;
	private boolean fault;
	private boolean gameOver;
	private int faultCounter;

	/**
	 * Creates a new Tennis Match
	 * 
	 * @param p1Name           - The name of the first player
	 * @param p2Name           - The name of the second player
	 * @param playBestOfThree  - Match is best-of-3 sets if true; else best-of-5
	 * @param initialServer    - Specifies which player serves first
	 * @param initialServerEnd - Specifies which end the initial server starts on
	 */
	public TennisMatch(String p1Name, String p2Name, boolean playBestOfThree, int initialServer, int initialServerEnd) {
		TennisPlayer pl1 = new TennisPlayer(p1Name, 1);
		TennisPlayer pl2 = new TennisPlayer(p2Name, 2);
		// I needed to do this otherwise I was getting nullpointer exceptions?
		player1 = pl1;
		player2 = pl2;
		this.bestOfThree = playBestOfThree;
		// Figure I could just use a switch here instead of an if statement
		switch (initialServer) {
		case 1:
			server = player1;
			receiver = player2;
			break;
		case 2:
			server = player2;
			receiver = player1;
			break;
		}
	}

	/**
	 * Swaps the ends of the two players
	 */
	public void changeEnds() {
		tennisPlayerHelper("End");
	}

	/**
	 * Swaps the server and receiver
	 */
	public void changeServer() {
		tennisPlayerHelper("Server");
	}

	/**
	 * Takes the ball out of play. The player who last served or returned the ball
	 * scores a game point.
	 */
	public void failedReturn() {
		// If the ball is in serve, well then the server should get the point eh.
		if (ballInServe) {
			incrementGamePoints(server, receiver);
		} else {
			incrementGamePoints(receiver, server);
		}
		// Let's do what this does and take it out of play, but make sure to do it after
		// the if because I didn't originally >_>
		ballInPlay = false;
		ballInServe = false;
	}

	/**
	 * Registers a serve fault. Does nothing if the ball is not being served. Two
	 * serve faults yield a game point for the receiver.
	 */
	public void fault() {
		if (ballInServe) {
			// Every 2 faults(even number) increments a point.
			if (faultCounter % 2 == 0) {
				if (getReceiver() == player1.getName()) {
					incrementGamePoints(player1, player2);
				} else {
					incrementGamePoints(player2, player1);
				}
			}
			// Increment for this^
			faultCounter++;
		}
		// They faulted man, gotta set it to true.
		fault = true;
	}

	/**
	 * Returns the name of the player who last successfully served or returned the
	 * ball or "Ball not in play"
	 * 
	 * @return ballFrom's name
	 */
	public String getBallFrom() {
		return ballFrom.getName();
	}

	/**
	 * Returns ballInPlay
	 * 
	 * @return ballInPlay
	 */
	public boolean getBallInPlay() {
		return ballInPlay;
	}

	/**
	 * returns ballServed
	 * 
	 * @return ballServed
	 */
	public boolean getBallServed() {
		return ballInServe;
	}

	/**
	 * Returns the name of the player whom the ball is heading toward or "Ball not
	 * in play"
	 * 
	 * @return ballTo's name
	 */
	public String getBallTo() {
		return ballTo.getName();
	}

	/**
	 * returns bestOfThree
	 * 
	 * @return bestOfThree
	 */
	public boolean getBestOfThree() {
		return bestOfThree;
	}

	/**
	 * returns gameOver
	 * 
	 * @return gameOver
	 */
	public boolean getGameOver() {
		return gameOver;
	}

	/**
	 * Returns the game score p1-p1, Advantage name or Deuce. See section 5 of the
	 * Friend of the Court.
	 * 
	 * @return The game score
	 */
	public String getGameScore() {
		return formatScore("Game");
	}

	/**
	 * Returns the match score p1-p2
	 * 
	 * @return The match score
	 */
	public String getMatchScore() {
		return formatScore("Match");
	}

	/**
	 * Returns player's name
	 * 
	 * @param player - the player
	 * @param name - name of player
	 * @return player's name
	 */
	public String getName(int player) {
		String name = "";
		switch (player) {
		case 1:
			name = player1.getName();
			break;
		case 2:
			name = player2.getName();
			break;
		}
		return name;
	}

	/**
	 * Returns p1's end
	 * 
	 * @return p1's end
	 */
	public int getP1End() {
		return player1.getEnd();
	}

	/**
	 * Returns p2's end
	 * 
	 * @return p2's end
	 */
	public int getP2End() {
		return player2.getEnd();
	}

	/**
	 * Returns the reveiver's name or "No receiver"
	 * 
	 * @return the receiver's name
	 */
	public String getReceiver() {
		return receiver.getName();
	}

	/**
	 * Returns the full game, set, and match score
	 * 
	 * @return The score
	 */
	public String getScore() {
		return tennisScore("All");
	}

	/**
	 * Returns serve fault status
	 * 
	 * @return serve fault status
	 */
	public boolean getServeFault() {
		return fault;
	}

	/**
	 * Returns the server's name or "No server"
	 * 
	 * @return the server's name
	 */
	public String getServer() {
		return server.getName();
	}

	/**
	 * Returns the set score p1-p2
	 * 
	 * @return The set score
	 */
	public String getSetScore() {
		return formatScore("Set");
	}

	/**
	 * Returns the winner's name, or an error message if the game is not over.
	 * 
	 * @return the winner
	 */
	public String getWinner() {
		if (gameOver) {
			return winner;
		} else {
			winner = "Game not over";
			return winner;
		}
	}

	/**
	 * Adds one game point to addTo's game total. Zeros game score and increments
	 * set score if game has ended. Removes ball from play. Clears faults.
	 * 
	 * @param addTo - The player who has scored a point
	 * @param noAdd - The other player
	 */
	public void incrementGamePoints(TennisPlayer addTo, TennisPlayer noAdd) {
		// If this method is ever called we increment Game Points by the value given by
		// addTo
		addTo.incrementGamePoints();
		// Javadoc explains the purpose of these if's pretty well.
		if (addTo.getGamePoints() == 4 && noAdd.getGamePoints() == 3
				|| addTo.getGamePoints() == 3 && noAdd.getGamePoints() == 4) {
			// Game hasn't ended yet so no need to do anything else
			return;
		} else if (addTo.getGamePoints() == 4 || addTo.getGamePoints() == 5) {
			incrementSetPoints(addTo, noAdd);
			// Set time
			addTo.setGamePoints(0);
			noAdd.setGamePoints(0);
			ballInPlay = false;
			faultCounter = 0;
			// swap the server
			changeServer();
		}
	}

	/**
	 * Adds one match point to addTo's total. Sets game over if match has ended.
	 * 
	 * @param addTo - The player who has scored a point
	 * @param noAdd - The other player
	 */
	public void incrementMatchPoints(TennisPlayer addTo, TennisPlayer noAdd) {
		addTo.incrementMatchPoints();
		// Gotta do stuff differently if it's a best of three.
		if (bestOfThree) {
			// if player 1 wins then we set that, if not then player 2 won
			if (player1.getMatchPoints() > player2.getMatchPoints()) {
				winner = player1.getName();
				gameOver = true;
			} else if (player2.getMatchPoints() > player1.getMatchPoints()) {
				winner = player2.getName();
				gameOver = true;
			}
		} else {
			if (player1.getMatchPoints() >= 3 && player1.getMatchPoints() > player2.getMatchPoints()) {
				winner = player1.getName();
				gameOver = true;
			} else {
				winner = player2.getName();
				gameOver = true;
			}
		}
	}

	/**
	 * Adds one set point to addTo's total. Zeros set score and increments match
	 * score if set has ended. Changes server. Changes ends after odd numbered sets.
	 * 
	 * @param addTo - The player who has scored a point
	 * @param noAdd - The other player
	 */
	public void incrementSetPoints(TennisPlayer addTo, TennisPlayer noAdd) {
		// If this method is ever called, we should increment setpoints by value given
		addTo.incrementSetPoints();
		// Javadoc explains these if-statements sufficiently I believe.
		if (addTo.getSetPoints() % 2 != 0 || addTo.getSetPoints() == 1 || addTo.getSetPoints() == 3) {
			changeEnds();
		}
		if (addTo.getSetPoints() >= 6 && addTo.getSetPoints() != noAdd.getSetPoints() + 1) {
			addTo.setSetPoints(0);
			noAdd.setSetPoints(0);
			incrementMatchPoints(addTo, noAdd);
		}
	}

	/**
	 * Ends the current point early without a point being scored.
	 */
	public void let() {
		// Literally just reset
		ballInPlay = false;
		ballInServe = false;
	}

	/**
	 * Reverses the direction of the ball. Ball is no longer being served. Does
	 * nothing if the ball is not in play.
	 */
	public void returnBall() {
		if (ballInPlay) {
			ballInServe = false;
			fault = false;
			tennisPlayerHelper("Ball-Direction");
		}
	}

	/**
	 * Serves the ball. Does nothing if the game is over.
	 */
	public void serve() {
		if (!gameOver) {
			ballInPlay = true;
			ballInServe = true;
			fault = false;
			tennisPlayerHelper("Ball-Direction");
		}
	}

	/**
	 * Sets the game score
	 * 
	 * @param p1Game - Player 1's new game score
	 * @param p2Game - Player 1's new game score
	 */
	public void setGameScore(int p1Game, int p2Game) {
		player1.setGamePoints(p1Game);
		player2.setGamePoints(p2Game);
	}

	/**
	 * Sets the match score
	 * 
	 * @param p1Match - Player 1's new match score
	 * @param p2Match - Player 1's new match score
	 */
	public void setMatchScore(int p1Match, int p2Match) {
		player1.setMatchPoints(p1Match);
		player2.setMatchPoints(p2Match);
	}

	/**
	 * Set the game, set, and match scores
	 * 
	 * @param p1Game  - Player 1's new game score
	 * @param p2Game  - Player 1's new game score
	 * @param p1Set   - Player 1's new set score
	 * @param p2Set   - Player 1's new set score
	 * @param p1Match - Player 1's new match score
	 * @param p2Match - Player 1's new match score
	 */
	public void setScore(int p1Game, int p2Game, int p1Set, int p2Set, int p1Match, int p2Match) {
		player1.setGamePoints(p1Game);
		player2.setGamePoints(p2Game);

		player1.setSetPoints(p1Set);
		player2.setSetPoints(p2Set);

		player1.setMatchPoints(p1Match);
		player2.setMatchPoints(p2Match);
	}

	/**
	 * Sets the server
	 * 
	 * @param player - the new server
	 */
	public void setServe(int player) {
		// I really wanna use a switch here but im not going to because its less lines
		// as an if-else statement
		if (player == 1) {
			server = player1;
		} else {
			server = player2;
		}
	}

	/**
	 * Sets the server's end
	 * 
	 * @param end - the new end
	 */
	public void setServerEnd(int end) {
		server.setEnd(end);
	}

	/**
	 * Sets the set score
	 * 
	 * @param p1Set - Player 1's new set score
	 * @param p2Set - Player 1's new set score
	 */
	public void setSetScore(int p1Set, int p2Set) {
		player1.setSetPoints(p1Set);
		player2.setSetPoints(p2Set);
	}

	/*
	 * Private method to help with changing server, receiver, ballFrom, BallTo, and
	 * the end.
	 * 
	 * @param swapTennisPositions command of what to change in the tennis game.
	 */
	private void tennisPlayerHelper(String swapTennisPositions) {
		switch (swapTennisPositions) {
		case "Direction":
			if (ballInServe) {
				if (getServer() == player1.getName()) {
					ballFrom = player1;
					ballTo = player2;
				} else if (getServer() == player2.getName()) {
					ballFrom = player2;
					ballTo = player1;
				} else {
					// If none of the above is true swap ballFrom and ballTo, temp value t to do so
					TennisPlayer tempPlayer = ballTo;
					ballTo = ballFrom;
					ballFrom = tempPlayer;
				}
			}
			break;
		case "Server":
			// Temp value to swap two values
			TennisPlayer tempPlayer = server;
			server = receiver;
			receiver = tempPlayer;
			break;
		case "End":
			// Temp value to swap two values
			int tempInt = player1.getEnd();
			player1.setEnd(player2.getEnd());
			player2.setEnd(tempInt);
			break;
		}
	}

	/*
	 * Private method to help make the score into what spec-checker wants it to be.
	 * more formatting.
	 * 
	 * @param scoreInstruction Instruction on what to format in what way
	 */
	private String tennisScore(String scoreInstruction) {
		switch (scoreInstruction) {
		case "Match":
			formatScore("Match");
			break;
		case "Game":
			formatScore("Game");
			break;
		case "Set":
			formatScore("Set");
			break;
		case "All":
			String player1FormattedScore = "";
			String player2FormattedScore = "";
			int player1GameScore = player1.getGamePoints();
			int player2GameScore = player2.getGamePoints();

			switch (player1GameScore) {
			case 0:
				player1FormattedScore = "Love";
				break;
			case 1:
				player1FormattedScore = "15";
				break;
			case 2:
				player1FormattedScore = "30";
				break;
			case 3:
				player1FormattedScore = "40";
				break;
			case 4:
				player1FormattedScore = "Game";
				break;
			}
			switch (player2GameScore) {
			case 0:
				player2FormattedScore = "Love";
				break;
			case 1:
				player2FormattedScore = "15";
				break;
			case 2:
				player2FormattedScore = "30";
				break;
			case 3:
				player2FormattedScore = "40";
				break;
			case 4:
				player2FormattedScore = "Game";
				break;
			}
			if (player1FormattedScore == player2FormattedScore) {
				switch (player1FormattedScore) {
				// I was tempted to create a method for these returns but I really just don't
				// wanna
				case "Love":
					score = "Game Score: Love-Love" + "\r\nSet score: " + formatScore("Set") + "\r\nMatch Score: "
							+ formatScore("Match");
				case "15":
					score = "Game Score: 15-15" + "\r\nSet score: " + formatScore("Set") + "\r\nMatch Score: "
							+ formatScore("Match");
				case "30":
					score = "Game Score: 30-30" + "\r\nSet score: " + formatScore("Set") + "\r\nMatch Score: "
							+ formatScore("Match");
				case "40":
					score = "Game Score: Deuce" + "\r\nSet score: " + formatScore("Set") + "\r\nMatch Score: "
							+ formatScore("Match");
				case "Game":
					score = "Game Score: Deuce" + "\r\nSet score: " + formatScore("Set") + "\r\nMatch Score: "
							+ formatScore("Match");
				}
			} else {
				if (player1.getGamePoints() == 4 && player2.getGamePoints() == 3) {
					score = "Game score: Advantage " + player1.getName() + "\r\nSet Score: " + formatScore("Set")
							+ "\r\nMatchScore: " + formatScore("Match");
				} else if (player2.getGamePoints() == 4 && player1.getGamePoints() == 3) {
					score = "Game score: Advantage " + player2.getName() + "\r\nSet Score: " + formatScore("Set")
							+ "\r\nMatchScore: " + formatScore("Match");
				} else {
					score = "Game score: " + player1FormattedScore + "-" + player2FormattedScore + "\r\nSet Score: "
							+ formatScore("Set") + "\r\n" + formatScore("Match");
				}
			}
		}
		return score;
	}

	/*
	 * Private method to help with formatting of the score because its used multiple
	 * places
	 * 
	 * @param s for determining if we're getting Match, Game or Set score.
	 * 
	 * @return score formatted "match" score
	 */
	private String formatScore(String s) {
		String player1Score, player2Score;
		switch (s) {
		case "Match":
			player1Score = Integer.toString(player1.getMatchPoints());
			player2Score = Integer.toString(player2.getMatchPoints());
			score = s + " score: " + player1Score + "-" + player2Score;
			break;
		case "Game":
			player1Score = Integer.toString(player1.getGamePoints());
			player2Score = Integer.toString(player2.getGamePoints());
			score = s + " score: " + player1Score + "-" + player2Score;
			break;
		case "Set":
			player1Score = Integer.toString(player1.getSetPoints());
			player2Score = Integer.toString(player2.getSetPoints());
			score = s + " score: " + player1Score + "-" + player2Score;
			break;
		}
		return score;
	}
}