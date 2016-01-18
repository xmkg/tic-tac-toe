package Definition;

/*
 * File 			: 	Game.java
 * Author			:	Mustafa K. GILOR (PENTAGRAM)
 * Purpose			: 	Providing flexible game base facility for a tic-tac-toe game.
 * Created on 		:	12.11.2015
 * Last modified	:	15.11.2015
 * Status			:	Complete
 */

public class Game 
{
	/*
	 * No regions in java :( 
	 * That's a frustration for humanity..
	 */
	
	public static class PointState 
	{
		public static int POINT_STATE_INVALID 	= 2;
		public static int POINT_STATE_NONE  	= 0;
		public static int POINT_STATE_X 		= -1;
		public static int POINT_STATE_O 		= 1;
	};
	
	/*
	 * The width of the tic-tac-toe board.
	 * Default value is 3.
	 */
	private int BOARD_WIDTH = 3;
	
	/*
	 * The height of the tic-tac-toe board.
	 * Default value is 3.
	 */
	private int BOARD_HEIGHT = 3;
	
	/* The tic-tac-toe board. */
	private int[][] _board; 
	
	/* Returns the width of the tic-tac-toe board.*/
	public int getBoardWidth() 		{ return BOARD_WIDTH;  }
	/* Returns the height of the tic-tac-toe board*/
	public int getBoardHeight() 	{ return BOARD_HEIGHT; } 
	
	private int _winner = PointState.POINT_STATE_NONE;
	
	
	/*
	 * @brief 	The default constructor of the Game class.
	 * @purpose Initializes the required value and variables.
	 * 			Default width and height is 3x3.
	 */
	public Game(int boardWidth, int boardHeight ) 
	{
		reInitialize(boardWidth,boardHeight);
	}
	
/*	private void debug_test()
	{
		System.out.println("starting vert.");
		for(int i = 0; i < BOARD_WIDTH; i++)
		{
			setPointState(i,0,PointState.POINT_STATE_O);
			setPointState(i,1,PointState.POINT_STATE_O);
			setPointState(i,2,PointState.POINT_STATE_O);
	
			resetGameState();
		}
		
		for(int i = 0; i < BOARD_HEIGHT; i++)
		{
			setPointState(2,i,PointState.POINT_STATE_X);
			setPointState(1,i,PointState.POINT_STATE_X);
			setPointState(0,i,PointState.POINT_STATE_X);
			resetGameState();
		}
		

		resetGameState();
		System.out.println("starting diagonal");

		setPointState(0,0,PointState.POINT_STATE_O);
		setPointState(1,1,PointState.POINT_STATE_O);
		setPointState(2,2,PointState.POINT_STATE_O);
		
		resetGameState();
		
		setPointState(2,0,PointState.POINT_STATE_O);
		setPointState(1,1,PointState.POINT_STATE_O);
		setPointState(0,2,PointState.POINT_STATE_O);
	}*/
	
	/*
	 * @brief   Sets the given point's state to specified state value.
	 * @return  none
	 */
	public int setPointState(int pX,int pY,int state)	
	{	
		_board[pX][pY] = state;
		if(!CheckHorizontal() && !CheckVertical())
				CheckDiagonal();
		return 0;
	}
	
	public void reInitialize(int width,int height)
	{
		BOARD_WIDTH = width;
		BOARD_HEIGHT = height;
		_board = new int[BOARD_WIDTH][BOARD_HEIGHT];
		_winner = PointState.POINT_STATE_NONE;
		resetGameState();
	}
	
	public void resetGameState()
	{
		for(int p = 0;p < BOARD_WIDTH;p++)
			for(int z = 0; z < BOARD_HEIGHT; z++)
				_board[p][z] = PointState.POINT_STATE_NONE;
	}
	
	
	
	
	public boolean isGameOver() { return _winner != PointState.POINT_STATE_NONE; }
	public int getWinner() { return _winner;}
	
	private boolean CheckDiagonal()
	{
		if(isGameOver())
			return true;
		System.out.println("Diagonal");
		Counter _occurrence = new Counter(3,0);
		/***********************************************/
		/** Diagonal lines starting from topmost row  **/
		/***********************************************/
		/** Stage 1 - Right bottom diagonal check      */
		/***********************************************/
		/** - Starting from leftmost				   */
		/** - Iterating to rightmost point			   */
		/***********************************************/
		for(int baseX=0; baseX < BOARD_WIDTH; baseX++)
		{
			/* Assign starting point's state to the previous state */
			int _prevState =  _board[baseX][0];
			_occurrence.reset(0);
			for(int x = baseX, y = 0; (x < BOARD_WIDTH) && (y < BOARD_HEIGHT); x++,y++)
			{
				int _currentState = _board[x][y];
				
				if(Counter.ComparePointAndCount(_prevState,_currentState,_occurrence))
				{
					if(_occurrence.isAtMax())
					{
						System.out.println("Game over, DLTOPSTAGE1");
						GameOver(_prevState);
						return true;
					}
				}
				_prevState = _currentState;
	
			}
		}
		
		_occurrence.reset(0);
		/***********************************************/
		/** Stage 2 - Left bottom diagonal check       */
		/***********************************************/
		/** - Starting from rightmost				   */
		/** - Iterating to leftmost point			   */
		/***********************************************/
		for(int baseX = BOARD_WIDTH - 1; baseX >= 0; baseX--)
		{
			/* Assign starting point's state to the previous state */
			int _prevState =  _board[baseX][0];
			_occurrence.reset(0);
			for(int x = baseX, y = 0; (x >=0) && (y < BOARD_HEIGHT); x--,y++)
			{
				int _currentState = _board[x][y];
				if(Counter.ComparePointAndCount(_prevState,_currentState,_occurrence))
				{
					if(_occurrence.isAtMax())
					{
						System.out.println("Game over, DLTOPSTAGE2");
						GameOver(_prevState);
						return true;
					}
				}
				_prevState = _currentState;
			}
		}
		
		_occurrence.reset(0);
		/***********************************************/
		/** Diagonal lines starting from bottom row   **/
		/***********************************************/
		/** Stage 1 - Right top diagonal check         */
		/***********************************************/
		/** - Starting from leftmost				   */
		/** - Iterating to rightmost point			   */
		/***********************************************/
		
		for(int baseX = 0; baseX < BOARD_WIDTH; baseX++)
		{
			/* Assign starting point's state to the previous state */
			int _prevState =  _board[baseX][BOARD_HEIGHT - 1];
			_occurrence.reset(0);
			for(int x = baseX, y = BOARD_HEIGHT - 1; (x < BOARD_WIDTH) && (y > 0); x++, y--)
			{
				int _currentState = _board[x][y];
				
				if(Counter.ComparePointAndCount(_prevState,_currentState,_occurrence))
				{
					if(_occurrence.isAtMax())
					{
						System.out.println("Game over, DLBOTSTAGE1");
						GameOver(_prevState);
						return true;
					}
				}
				_prevState = _currentState;
			}
		}
		
		_occurrence.reset(0);
		/***********************************************/
		/** Stage 2 - Left top diagonal check          */
		/***********************************************/
		/** - Starting from leftmost				   */
		/** - Iterating to rightmost point			   */
		/***********************************************/
		
		for(int baseX = BOARD_WIDTH -1; baseX > 0; baseX--)
		{
			/* Assign starting point's state to the previous state */
			int _prevState =  _board[baseX][BOARD_HEIGHT - 1];
			_occurrence.reset(0);
			for(int x = baseX, y = BOARD_HEIGHT - 1; (x> 0) && (y > 0); x--,y--)
			{

				int _currentState = _board[x][y];
				
				if(Counter.ComparePointAndCount(_prevState,_currentState,_occurrence))
				{
					if(_occurrence.isAtMax())
					{
						System.out.println("Game over, DLBOTSTAGE2");
						GameOver(_prevState);
						return true;
					}
				}
				_prevState = _currentState;
			}
		}
		return false;
	}
	
	
	private boolean CheckHorizontal()
	{
		if(isGameOver())
			return true;
		System.out.println("Horizontal");
		Counter _occurrence = new Counter(3,0);
		
		/*
		 * Vertical scan
		 */
		for(int x = 0; x < BOARD_WIDTH; x++)
		{
			_occurrence.reset(0);
			int _prevState = _board[x][0];
			for(int y = 0; y < BOARD_HEIGHT; y++)
			{
				
				int _currentState = _board[x][y];
				if(Counter.ComparePointAndCount(_prevState,_currentState,_occurrence))
				{
					if(_occurrence.isAtMax())
					{
						System.out.println("Game over, HORIZONTALSTAGE0");
						GameOver(_prevState);
						return true;
					}
				}
				_prevState = _currentState;
			}
		}
		return false;
	}
	
	private boolean CheckVertical()
	{
		if(isGameOver())
			return true;
		System.out.println("Vertical");
		Counter _occurrence = new Counter(3,0);
		/*
		 * Horizontal scan
		 */
		for(int y = 0; y < BOARD_HEIGHT; y++)
		{	
			_occurrence.reset(0);
			int _prevState = _board[0][y];
			for(int x= 0; x < BOARD_HEIGHT; x++)
			{
				int _currentState = _board[x][y];
				
				if(Counter.ComparePointAndCount(_prevState,_currentState,_occurrence))
				{
					if(_occurrence.isAtMax())
					{
						System.out.println("Game over, VERTICALSTAGE0");
						GameOver(_prevState);
						return true;
					}
				}
				_prevState = _currentState;
			}
		}
		return false;
	}
	
	private void GameOver(int winner)
	{
		if(_winner == PointState.POINT_STATE_NONE)
		{
			_winner = winner;
			System.out.println("Game is over.");
		}
	}
	
	/*
	 * @brief   Returns the given point's state
	 * @return  none
	 */
	public int  getPointState(int pX,int pY) 			
	{ 
		if(pX < 0 || pX >= BOARD_HEIGHT || pY < 0 || pY >= BOARD_WIDTH )
			return PointState.POINT_STATE_INVALID;
		return _board[pX][pY];
	}
	

}
