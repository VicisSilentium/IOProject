package io.model;

import java.util.ArrayList;

/**
 * The Game superclass for the IO Project.
 * 
 * @author Kyler Jensen
 * @version 1.0 13/12/2013 Added data members, constructors and default play
 *          method.
 * 
 */
public class Game
{
	// Declaration Section
	/**
	 * An ArrayList of Strings made of game rules.
	 */
	public ArrayList<String> gameRules;
	/**
	 * A counter for ranking.
	 */
	int funRanking;
	/**
	 * The game title.
	 */
	private String gameTitle;

	/**
	 * The constructor of the Game superclass.
	 */
	public Game()
	{
		gameRules = new ArrayList<String>();
		funRanking = 0;
		gameTitle = "";
	}

	/**
	 * The overloaded constructor for the Game superclass
	 * 
	 * @param gameRules
	 *            A list of rules for the game.
	 * @param funRanking
	 *            A rank of funness in the game.
	 * @param gameTitle
	 *            The title of the game.
	 */
	public Game(ArrayList<String> gameRules, int funRanking, String gameTitle)
	{
		this.gameRules = gameRules;
		this.funRanking = funRanking;
		this.gameTitle = gameTitle;
	}

	/**
	 * The getter for the gameRules ArrayList.
	 * 
	 * @return
	 */
	public ArrayList<String> getGameRules()
	{
		return gameRules;
	}

	/**
	 * the getter for the funRanking int.
	 * 
	 * @return
	 */
	public int getFunRanking()
	{
		return funRanking;
	}

	/**
	 * The getter for the gameTitle String.
	 * 
	 * @return
	 */
	public String getGameTitle()
	{
		return gameTitle;
	}

	/**
	 * The setter for the gameRules ArrayList.
	 * 
	 * @param gameRules
	 */
	public void setGameRules(ArrayList<String> gameRules)
	{
		this.gameRules = gameRules;
	}

	/**
	 * The setter for the funRanking int.
	 * 
	 * @param funRanking
	 */
	public void setFunRanking(int funRanking)
	{
		this.funRanking = funRanking;
	}

	/**
	 * The setter for the gameTitle String.
	 * 
	 * @param gameTitle
	 */
	public void setGameTitle(String gameTitle)
	{
		this.gameTitle = gameTitle;
	}

	public void play()
	{

	}
}
