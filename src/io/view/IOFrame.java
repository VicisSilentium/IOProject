package io.view;

import io.controller.IOController;

import javax.swing.JFrame;

/**
 * Frame for the IO Project.
 * 
 * @author Kyler Jensen
 * @version 1.0 13/12/2013 Created setup method and controller.
 */
public class IOFrame extends JFrame
{
	// Declaration Section
	/**
	 * IOPanel for the Frame
	 */
	private IOPanel basePanel;

	/**
	 * Constructor for the IOFrame.
	 * 
	 * @param baseController
	 *            IOController passed in for MVC relationship.
	 */
	public IOFrame(IOController baseController)
	{
		basePanel = new IOPanel(baseController);

		setupFrame();
	}

	/**
	 * Sets up the frame size and loads the content panel.
	 */
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setSize(500, 300);
		this.setVisible(true);
	}
}
