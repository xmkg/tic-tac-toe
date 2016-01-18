package Interface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;

import Definition.BoardPanel;
import Definition.Game;


/*
 * File 			: 	MainInterface.java
 * Author			:	Mustafa K. GILOR (PENTAGRAM)
 * Purpose			: 	Graphical user interface of the tic-tac-toe game.
 * Created on 		:	14.11.2015
 * Last modified	:	15.11.2015
 * Status			:	Incomplete
 */

/*
 * TODO : Add AI player.
 */


@SuppressWarnings("serial")
public class MainInterface extends JFrame implements ActionListener
{
	private Game _game;
	private JMenuBar _mainMenuBar;
	private JMenu	 _aboutMenu;
	private JMenu	 _gameMenu;
	 
	private JPanel _mainPanel;
	private JLabel _statusLabel;
	
	private BoardPanel[][] _boardPanels;
	
	private int _turn = -1;
	
	
	private MouseListener _mouseEventListener = new MouseListener()
	{

		@Override
		public void mouseClicked(MouseEvent mouseEvent) {}
		@Override
		public void mouseReleased(MouseEvent mouseEvent) {}

		@Override
		public void mouseEntered(MouseEvent mouseEvent) {
			// TODO Auto-generated method stub
			if(mouseEvent.getSource() instanceof JPanel)
			{
				JPanel jp = (JPanel) mouseEvent.getSource();
				
				jp.setBackground(Color.CYAN);
			}
		}

		@Override
		public void mouseExited(MouseEvent mouseEvent) {
			// TODO Auto-generated method stub
			if(mouseEvent.getSource() instanceof JPanel)
			{
				JPanel jp = (JPanel) mouseEvent.getSource();
				
				jp.setBackground(Color.GRAY);
			}
		}

		@Override
		public void mousePressed(MouseEvent mouseEvent) {
			// TODO Auto-generated method stub
			
			if(mouseEvent.getSource() instanceof BoardPanel)
			{
				BoardPanel bp = (BoardPanel)mouseEvent.getSource();
				_game.setPointState(bp.GetX(), bp.GetY(), _turn);
				bp.MarkByTurn(_turn);
				_turn *= -1;
				if(_game.isGameOver())
				{
					JOptionPane.showMessageDialog(null, _game.getWinner() == Game.PointState.POINT_STATE_O ? "Player 2 has won the game.":"Player 1 has won the game.");		
					if(JOptionPane.showConfirmDialog(null, "Do you want to start over?") == JOptionPane.YES_OPTION)
					{
						newPVPGame();
					}
				}
				
			
				_statusLabel.setText(_turn == 1 ? "Player 2's turn":"Player 1's turn");
				
				System.out.println("asd : " + _turn);
			}
			
		}

		
		
	};
	
	public MainInterface()
	{ 
		initializeComponents();
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setLayout(new FlowLayout());
		this.setTitle("Tic-tac-toe, eh whatever.");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(320, 240);
		this.setVisible(true);
		this.setJMenuBar(_mainMenuBar);
		this.setLocationRelativeTo(null);	
		
	}
	
	private void initializeComponents()
	{
		_gameMenu = new JMenu("Game");
		_aboutMenu = new JMenu("About..");
		_mainPanel = new JPanel();
		_statusLabel = new JLabel();
		_statusLabel.setText("Start a new game from the menu");
		
	//	_mainPanel.setBackground(Color.RED);
		
		 JMenuItem _pvpMenuItem = new JMenuItem("Player vs player");

		 JMenuItem _pvaMenuItem = new JMenuItem("Player vs AI");
		 JMenuItem _resetGameMenuItem = new JMenuItem("Reset current game");
		 JMenuItem _aboutMenuItem = new JMenuItem("About..");
		 
		 
		 KeyStroke keyStrokeResetGame = KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);
		 KeyStroke keyStrokeNewPVP = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
		 KeyStroke keyStrokeNewPVA = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.SHIFT_DOWN_MASK);
		 _pvpMenuItem.setAccelerator(keyStrokeNewPVP);
		 _pvaMenuItem.setAccelerator(keyStrokeNewPVA);
		 _resetGameMenuItem.setAccelerator(keyStrokeResetGame);
		 
		 _aboutMenuItem.setActionCommand("AC_MI_ABOUT");
		 _aboutMenuItem.addActionListener(this);
		 _pvpMenuItem.setActionCommand("AC_MI_PVP");
		 _pvpMenuItem.addActionListener(this);
		 _pvaMenuItem.setActionCommand("AC_MI_PVA");
		 _pvaMenuItem.addActionListener(this);
		 _resetGameMenuItem.setActionCommand("AC_MI_RESET_GAME");
		 _resetGameMenuItem.addActionListener(this);
		
		_gameMenu.add(_pvpMenuItem);
		_gameMenu.add(_pvaMenuItem);
		_aboutMenu.add(_aboutMenuItem);
		_mainMenuBar = new JMenuBar();
		_mainMenuBar.add(_gameMenu);
		_mainMenuBar.add(_aboutMenu);
		_mainMenuBar.add(_resetGameMenuItem);
		this.add(_statusLabel);
		this.add(_mainPanel);
		_game = new Game(1,1);
	}
	
	private void initializeBoard(int width,int height)
	{
		
		final int panelSize = 70; // panel size with border included
		_game.reInitialize(width, height);
		
		_mainPanel.setLayout(new FlowLayout());
		_mainPanel.removeAll();
		
		_mainPanel.setSize(new Dimension((width * panelSize) + 50,(height * panelSize)+50));
		_mainPanel.setPreferredSize(new Dimension((width * panelSize) + 50,(height * panelSize)+50));
		
		this.setLocationRelativeTo(null);
		this.setSize(new Dimension((width * panelSize) + 150,(height * panelSize)+150));
		
		_boardPanels = null;
		_boardPanels = new BoardPanel[width][height];

		for(int x = 0; x < width;x++)
			for(int y = 0; y< height; y++)
			{
				_boardPanels[x][y] = new BoardPanel(x,y);
				
				_boardPanels[x][y].setPreferredSize(new Dimension(64,64));
				_boardPanels[x][y].addMouseListener(_mouseEventListener);
				_boardPanels[x][y].setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
				_boardPanels[x][y].setBackground(Color.GRAY);
				//System.out.println("sd" + _boardPanels[x][y].);
				_mainPanel.add(_boardPanels[x][y]);
			}
		
		_statusLabel.setText("This is the turn of the Player 1");
		this.paintComponents(this.getGraphics());
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		System.out.println(action.getActionCommand());
		switch(action.getActionCommand())
		{
			case "AC_MI_PVP":
				newPVPGame();
			break;
			case "AC_MI_PVA":
				JOptionPane.showMessageDialog(this,"This is not implemented, due to the laziness. :)");
				break;
			case "AC_MI_ABOUT":
				about();
				 break;
			case "AC_MI_RESET_GAME":
				initializeBoard(_game.getBoardWidth(),_game.getBoardHeight());
				break;
		}
	}
	
	private void newPVPGame()
	{
		String size = JOptionPane.showInputDialog(this, "Size?");
		
		int iw = 0 ;
		try {
			iw = Integer.parseInt(size);
			initializeBoard(iw,iw);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "The given value is not an integer." + e.getMessage());
		}
	}
	
	private void about()
	{
		JOptionPane.showMessageDialog(this, 
		"Tic-tac-toe game, in java\n" +
		"Coded by Mustafa K. GILOR\n" +
		"Cyprus International University, Department of Computer Engineering\n\n"+
		"This application and its' source code is freely available under GNU/GPL license.\n"+
		"You may acquire the source code at https://github.com/mustify address if you desire.\n"+
		"You may freely distribute and modify the application itself, and the source code.\n"+
		"(crediting the original author is always appreciated.) \n\n"+
		
		"November,2015."
		);
	}
	public static void main(String[] args) 
	{
		new MainInterface();
	}
}
	
	
	
	


