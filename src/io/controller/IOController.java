package io.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import io.model.Game;
import io.view.IOFrame;

/**
 * Controller class for the IO Project.
 * 
 * @author Kyler Jensen
 * @version 1.0 13/12/2013 Added start method.
 * 
 */
public class IOController
{
	private IOFrame appFrame;
	/**
	 * All games that can be retrieved form the save file.
	 */
	private ArrayList<Game> projectGames;

	/**
	 * Constructor for the Controller, initializes the ArrayList of Games
	 */
	public IOController()
	{
		projectGames = new ArrayList<Game>();
	}

	/**
	 * Getter for the ArrayList of Games
	 * @return
	 */
	public ArrayList<Game> getProjectGames()
	{
		return projectGames;
	}

	/**
	 * Starts the program so it can run effectively.
	 */
	public void start()
	{
		appFrame = new IOFrame(this);
	}

	/**
	 * A method that reads the saved game information.
	 * @return Saved game information
	 */
	public Game readGameInformation()
	{
		String FileName = "save file.txt"; // Without a path it will look to the
											// directory of the project.
		File currentSaveFile = new File(FileName);
		Scanner fileReader;
		Game currentGame = null;
		int gameRanking = 0;
		String gameTitle = "";
		ArrayList<String> gameRules = new ArrayList<String>();

		try
		{
			fileReader = new Scanner(currentSaveFile);
			gameTitle = fileReader.nextLine();
			gameRanking = fileReader.nextInt();
			while (fileReader.hasNext())
			{
				gameRules.add(fileReader.nextLine());
			}

			currentGame = new Game(gameRules, gameRanking, gameTitle);
			fileReader.close();
		}
		catch (FileNotFoundException currentFileDoesNotExist)
		{
			JOptionPane.showMessageDialog(appFrame, currentFileDoesNotExist.getMessage());

		}

		return currentGame;
	}

	/**
	 * A method that reads all the saved game information.
	 * @return
	 */
	private String readAllGameInformation()
	{
		String fileContents = "";
		String fileName = "save file.txt";
		File currentSaveFile = new File(fileName);
		Scanner fileReader;

		try
		{
			fileReader = new Scanner(currentSaveFile);
			while (fileReader.hasNext())
			{
				fileContents += fileReader.nextLine();
			}
			fileReader.close();
		}
		catch (FileNotFoundException fileDoesNotExist)
		{
			JOptionPane.showMessageDialog(appFrame, fileDoesNotExist.getMessage());
		}

		return fileContents;
	}

	/**
	 * Converts the text into data that can be saved and loaded.
	 * @param currentInfo
	 */
	private void convertTextToGames(String currentInfo)
	{

		String[] gameChunks = currentInfo.split(";");
		for (String currentBlock : gameChunks)
		{
			int currentIndex = currentBlock.indexOf("\n");
			String title = currentBlock.substring(0, currentIndex);
			int nextIndex = currentBlock.indexOf("\n", currentIndex);
			String ranking = currentBlock.substring(currentIndex + 1, nextIndex);
			String rules = currentBlock.substring(nextIndex + 1);
			Game currentGame = makeGameFromInput(title, ranking, rules);
		}
	}

	/**
	 * Loads a random game from save file.txt.
	 * @return
	 */
	public Game pickRandomGameFromSaveFile()
	{
		Game currentGame = null;

		String allInfo = readAllGameInformation();
		convertTextToGames(allInfo);

		return currentGame;
	}
	
	/**
	 * 
	 * @param gameTitle
	 * @param gameRanking
	 * @param gameRules
	 * @return
	 */
	public Game makeGameFromInput(String gameTitle, String gameRanking, String gameRules)
	{
		Game currentGame = new Game();
		currentGame.setGameTitle(gameTitle);

		if (checkNumberFormat(gameRanking))
		{
			currentGame.setFunRanking(Integer.parseInt(gameRanking));
		}
		else
		{
			return null;
		}

		String[] temp = gameRules.split("\n");
		ArrayList<String> tempRules = new ArrayList<String>();

		for (String tempWord : temp)
		{
			tempRules.add(tempWord);
		}
		currentGame.setGameRules(tempRules);

		return currentGame;
	}

	private boolean checkNumberFormat(String toBeParsed)
	{
		boolean isNumber = false;

		try
		{
			int valid = Integer.parseInt(toBeParsed);
			isNumber = true;
		}
		catch (NumberFormatException error)
		{
			JOptionPane.showMessageDialog(appFrame, "Pleas try again with an actual Number.");
		}

		return isNumber;
	}

	/**
	 * Saves a Game object to the drive and separates each Game object with a
	 * semicolon
	 * 
	 * @param currentGame
	 */
	public void saveGameInformation(Game currentGame)
	{
		PrintWriter gameWriter;
		String saveFile = "save file.txt";

		try
		{
			gameWriter = new PrintWriter(saveFile); // Creates the save file.

			gameWriter.println(currentGame.getGameTitle() + "\n");
			gameWriter.println(currentGame.getFunRanking() + "\n");
			for (int count = 0; count < currentGame.getGameRules().size(); count++)
			{
				gameWriter.println(currentGame.getGameRules().get(count));
			}
			gameWriter.println(";" + "\n"); // Used to delineate each individual
											// game
			// in the save file.

			gameWriter.close(); // required to prevent corruption of data and
								// maintain security of the file.
		}
		catch (FileNotFoundException noFileExists)
		{
			JOptionPane.showMessageDialog(appFrame, "Could not create the save file :(");
			JOptionPane.showMessageDialog(appFrame, noFileExists.getMessage());
		}

	}
}
