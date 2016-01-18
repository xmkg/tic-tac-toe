package Definition;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * File 			: 	BoardPanel.java
 * Author			:	Mustafa K. GILOR (PENTAGRAM)
 * Purpose			: 	An individual tile of the tic-tac-toe board.
 * Created on 		:	14.11.2015
 * Last modified	:	14.11.2015
 * Status			:	Complete
 */


@SuppressWarnings("serial")
public class BoardPanel extends JPanel
{
	private JLabel _textLabel;
	
	private int _x,_y;
	public BoardPanel(int x, int y)
	{
		_x = x;
		_y = y;
		final Font _defaultFont = new Font("Verdana",Font.PLAIN,36);
		_textLabel = new JLabel();
		_textLabel.setFont(_defaultFont);
		
		this.setLayout(new FlowLayout());
		this.add(_textLabel);
	}
	
	public int GetX()
	{
		return _x;
	}
	
	public int GetY()
	{
		return _y;
	}
	
	public void MarkByTurn(int val)
	{
		if( val == -1)
			MarkX();
		else if(val == 1)
			MarkO();
	}
	
	public void MarkX()
	{
		_textLabel.setForeground(Color.RED);
		_textLabel.setText("X");
	}
	
	public void MarkO()
	{
		_textLabel.setForeground(Color.BLUE);
		_textLabel.setText("O");
	}
}
