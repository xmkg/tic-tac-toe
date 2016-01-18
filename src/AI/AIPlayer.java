package AI;

import Definition.Counter;
import Definition.Game;
import Definition.IntegerPair;


/*
 * File 			: 	AIPlayer.java
 * Author			:	Mustafa K. GILOR (PENTAGRAM)
 * Purpose			: 	Artificial Intelligence for the Tic-Tac-Toe game.
 * Created on 		:	14.11.2015
 * Last modified	:	14.11.2015
 * Status			:	Unfinished
 */

public class AIPlayer 
{
	
	
	private int _aiTeam = Game.PointState.POINT_STATE_NONE;
	private Game _theGame = null;
	
	AIPlayer(Game g, int team)
	{
		_aiTeam = team;
		_theGame = g;
	}
	
	/*
	 * Decide the best move for the round.
	 * AI player will follow a set of series 
	 * linearly to out-play the human player.
	 */
	public IntegerPair decide()
	{
		
		checkWin();
		checkOpponentBlock();
		
		return null;
		
	}
	
	private IntegerPair getDiagonal(int team)
	{
		return null;
	}
	private IntegerPair getHorizontal(int team)
	{
		Counter _occurrence = new Counter(2,0);

		IntegerPair _firstOccurrence = new IntegerPair(-1,-1);
		IntegerPair _secondOccurrence = new IntegerPair(-1,-1);
		
		for(int baseY = 0; baseY < _theGame.getBoardHeight(); baseY++)
		{
			/* Reset the integer pairs to their initial condition */
			_firstOccurrence.reset();
			_secondOccurrence.reset();
			
			for( int x = 0; x < _theGame.getBoardWidth(); x++)
			{
				int _currentTile = _theGame.getPointState(x, baseY);
				
				if(Counter.ComparePointAndCount_Move(team, _currentTile, _occurrence))
				{
					if(_firstOccurrence.hasValue())
					{
						if(!_secondOccurrence.hasValue())
							_secondOccurrence.set(x, baseY);
						
							/* Then we should determine the third. */
							if(_firstOccurrence.isHorizontallySequential(_secondOccurrence))
							{
								/* Then, the third tile is either before first occurrence,
								 * or after the second occurrence.
								 * We should check both tiles.
								 */
								
								IntegerPair _before = new IntegerPair(_firstOccurrence.first(),_firstOccurrence.second() -1 );
								if(_before.hasValue())
								{
									int _beforePointState = _theGame.getPointState(_before.first(), _before.second());
									if(_beforePointState == Game.PointState.POINT_STATE_NONE)
										return _before;
								}
								
								IntegerPair _after = new IntegerPair(_secondOccurrence.first(),_secondOccurrence.second() + 1);
								if(_after.hasValue())
								{
									int _afterPointState = _theGame.getPointState(_after.first(), _after.second());
									if(_afterPointState == Game.PointState.POINT_STATE_NONE)		
										return _after;
									
								}
								
								/* If both of the tiles are occupied, so we should reset and continue */
								_firstOccurrence.reset();
								_secondOccurrence.reset();
								_occurrence.reset(0);
							} /** end of if(_firstOccurrence.isSequential(_secondOccurrence)) **/
							else
							{
								/* The third tile is between first and second occurrence. */
								IntegerPair midpoint = IntegerPair.getHorizontalMidpoint(_firstOccurrence, _secondOccurrence);
								if(midpoint == null)
									continue;
								
								return midpoint;					
							}
					
					} /** end of if(_firstOccurrence.hasValue()) **/
					else
						_firstOccurrence.set(x, baseY);
					
					/* Otherwise, keep going. */
					
				} /** end of if(Counter.ComparePointAndCount_Move(team, _currentTile, _occurrence)) **/
				else
				{
					/* Reset the integer pairs, because some tile is breaking the sequence.*/
					_firstOccurrence.reset();
					_secondOccurrence.reset();
				}
			}
		}
		
		return null;
	}
	
	/*
	 * Returns an integer pair if there is a possible move in vertical, 
	 * for the specified team.
	 * 
	 * Returns null otherwise.
	 */
	private IntegerPair getVertical(int team)
	{
		Counter _occurrence = new Counter(2,0);
		
		IntegerPair _firstOccurrence = new IntegerPair(-1,-1);
		IntegerPair _secondOccurrence = new IntegerPair(-1,-1);
		
	
		for(int baseX = 0; baseX < _theGame.getBoardWidth(); baseX++)
		{
			/* Reset the integer pairs to their initial condition */
			_firstOccurrence.reset();
			_secondOccurrence.reset();
			
			for(int y = 0; y < _theGame.getBoardHeight(); y++)
			{
				int _currentTile = _theGame.getPointState(baseX, y);
					
				if(Counter.ComparePointAndCount_Move(team, _currentTile, _occurrence))
				{
					if(_firstOccurrence.hasValue())
					{
						if(!_secondOccurrence.hasValue())
							_secondOccurrence.set(baseX, y);
						
							/* Then we should determine the third. */
							if(_firstOccurrence.isVerticallySequential(_secondOccurrence))
							{
								/* Then, the third tile is either before first occurrence,
								 * or after the second occurrence.
								 * We should check both tiles.
								 */
								
								IntegerPair _before = new IntegerPair(_firstOccurrence.first(),_firstOccurrence.second() -1 );
								if(_before.hasValue())
								{
									int _beforePointState = _theGame.getPointState(_before.first(), _before.second());
									if(_beforePointState == Game.PointState.POINT_STATE_NONE)
										return _before;
								}
								
								IntegerPair _after = new IntegerPair(_secondOccurrence.first(),_secondOccurrence.second() + 1);
								if(_after.hasValue())
								{
									int _afterPointState = _theGame.getPointState(_after.first(), _after.second());
									if(_afterPointState == Game.PointState.POINT_STATE_NONE)		
										return _after;
									
								}
								
								/* If both of the tiles are occupied, so we should reset and continue */
								_firstOccurrence.reset();
								_secondOccurrence.reset();
								_occurrence.reset(0);
							} /** end of if(_firstOccurrence.isSequential(_secondOccurrence)) **/
							else
							{
								/* The third tile is between first and second occurrence. */
								IntegerPair midpoint = IntegerPair.getVerticalMidpoint(_firstOccurrence, _secondOccurrence);
								if(midpoint == null)
									continue;
								
								return midpoint;					
							}
					
					} /** end of if(_firstOccurrence.hasValue()) **/
					else
						_firstOccurrence.set(baseX, y);
					
					/* Otherwise, keep going. */
					
				} /** end of if(Counter.ComparePointAndCount_Move(team, _currentTile, _occurrence)) **/
				else
				{
					/* Reset the integer pairs, because some tile is breaking the sequence.*/
					_firstOccurrence.reset();
					_secondOccurrence.reset();
				}
			}
			
		}
		
		return null;
	}
	

	/*
	 * Move 1 : Check if we can complete a double to the triple.
	 */
	private void checkWin()
	{

		/*
		 * The logic is simple as follows;
		 * We will check if we have got two tiles in horizontal,vertical or diagonal
		 * and one more box in either end is unoccupied.
		 * 
		 * In such condition, we can put the final piece and win the game.
		 */
		
		IntegerPair theWinningTile = getVertical(_aiTeam);
		
		if(theWinningTile == null)
		{
			// check horizontal.
		}
	}
	
	/*
	 * Move 2 : Check if the opponent has double and we can block it.
	 */
	
	private void checkOpponentBlock()
	{
		/*
		 * If the opponent has chance to win, we should block him 
		 * {	because we're evil :)	}
		 * No human shall pass!
		 */
	}
	

	
	

}
