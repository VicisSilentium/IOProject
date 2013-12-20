package io.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import io.controller.IOController;
import io.model.Game;

/**
 * The Panel for the IO Project.
 * 
 * @author Kyler Jensen
 * @version 1.0 13/12/2013
 * 
 */
public class IOPanel extends JPanel
{
	private IOController baseController;
	private JButton saveButton;
	private JButton loadButton;
	private JTextField titleField;
	private JTextField rankingField;
	private JTextArea rulesArea;
	private JLabel rulesLabel;
	private JLabel rankingLabel;
	private JLabel titleLabel;
	private SpringLayout baseLayout;
	private JLabel gameCountLabel;

	public IOPanel(IOController baseController)
	{
		this.baseController = baseController;

		saveButton = new JButton("Save the game stuff");
		loadButton = new JButton("load the game stuff");
		titleField = new JTextField(15);
		titleLabel = new JLabel("Game Title: ");
		rankingField = new JTextField(5);
		rankingLabel = new JLabel("Game Ranking: ");
		rulesArea = new JTextArea(5, 20);
		rulesLabel = new JLabel("Game Rules: ");
		gameCountLabel = new JLabel("Current game count: ");
		baseLayout = new SpringLayout();

		setupPanel();
		setupLayout();
		setupListeners();
	}

	private void setupPanel()
	{
		this.setLayout(baseLayout);

		this.add(rankingField);
		this.add(rankingLabel);
		this.add(rulesArea);
		this.add(rulesLabel);
		this.add(saveButton);
		this.add(titleField);
		this.add(titleLabel);
		this.add(loadButton);
		this.add(gameCountLabel);
	}

	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, titleLabel, 45, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, titleLabel, 91, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, rankingLabel, 75, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, rulesLabel, 87, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, titleField, -3, SpringLayout.NORTH, titleLabel);
		baseLayout.putConstraint(SpringLayout.WEST, titleField, 6, SpringLayout.EAST, titleLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, rulesArea, 16, SpringLayout.SOUTH, rankingField);
		baseLayout.putConstraint(SpringLayout.WEST, rulesArea, 4, SpringLayout.EAST, rulesLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, rankingLabel, 9, SpringLayout.SOUTH, titleLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, rankingField, 0, SpringLayout.NORTH, rankingLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rankingField, 0, SpringLayout.WEST, rulesArea);
		baseLayout.putConstraint(SpringLayout.NORTH, rulesLabel, 17, SpringLayout.SOUTH, rankingLabel);
		baseLayout.putConstraint(SpringLayout.EAST, rulesLabel, 0, SpringLayout.EAST, rankingLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 0, SpringLayout.NORTH, saveButton);
		baseLayout.putConstraint(SpringLayout.WEST, loadButton, 6, SpringLayout.EAST, saveButton);
		baseLayout.putConstraint(SpringLayout.NORTH, saveButton, 22, SpringLayout.SOUTH, rulesArea);
		baseLayout.putConstraint(SpringLayout.WEST, saveButton, 87, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, saveButton, 18, SpringLayout.EAST, rankingField);
		baseLayout.putConstraint(SpringLayout.SOUTH, gameCountLabel, -6, SpringLayout.NORTH, titleLabel);
		baseLayout.putConstraint(SpringLayout.EAST, gameCountLabel, 0, SpringLayout.EAST, rankingLabel);

	}

	private void setupListeners() // Check for null when passing parameters in
									// case of Null Pointer Exceptions.
	{
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Game tempGame = baseController.makeGameFromInput(titleField.getText(), rankingField.getText(), rulesArea.getText());
				if (tempGame != null)
				{
					baseController.saveGameInformation(tempGame);
					gameCountLabel.setText("Current game Count: " + baseController.getProjectGames().size());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Try again with a valid number");
				}

			}
		});
		loadButton.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent click)
			{
				Game tempGame = baseController.readGameInformation();
				if (tempGame != null)
				{
					titleField.setText(tempGame.getGameTitle());
					rankingField.setText(Integer.toString(tempGame.getFunRanking()));
					String tempRules = "";
					for (String currentRule : tempGame.getGameRules())
					{
						tempRules += currentRule + "\n";
					}
					rulesArea.setText(tempRules);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Check the save file make sure it is in order.");
				}
			}
		});
	}

}
