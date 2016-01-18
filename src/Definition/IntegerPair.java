package Definition;

public class IntegerPair 
{
	private int _f = -1, _s = -1;
	public IntegerPair(int _f,int _s){ set(_f,_s);}
	public int first(){return _f;}
	public int second() { return _s;}
	
	public boolean hasValue() { return _f > -1 && _s > -1 ;}
	public void set(int _f,int _s) {  this._f = _f; this._s = _s;}
	public void reset(){ _f = -1; _s = -1; }
	
	
	
	public static IntegerPair getVerticalMidpoint(IntegerPair _fp, IntegerPair _sp)
	{	
		if(!_fp.hasValue() || !_sp.hasValue())
			return null;
		
		if(_fp.first() != _sp.first())
			return null;
		
		if(_fp.second() != _sp.second() -1)
			/* I <3 ternary! :) */
			return _fp.second() != (_sp.second() + 1) ? null:new IntegerPair(_fp.second(),_sp.second() -1 );
		else 
			return new IntegerPair(_fp.second(),_sp.second() -1 );			
	}
	
	public static IntegerPair getHorizontalMidpoint(IntegerPair _fp, IntegerPair _sp)
	{
		if(!_fp.hasValue() || !_sp.hasValue())
			return null;
		
		if(_fp.second() != _sp.second())
			return null;
		
		if(_fp.first() != _sp.first() -1)
			/* I <3 ternary! :) */
			return _fp.first() != (_sp.first() + 1) ? null:new IntegerPair(_fp.first(),_sp.first() -1 );
		else 
			return new IntegerPair(_fp.first(),_sp.first() -1 );	
		
	}
	
	public boolean isVerticallySequential(IntegerPair that)
	{
		/* In order to compare sequentiality, we must ensure both pairs has value.*/
		if(!hasValue() || !that.hasValue())
			return false;
		/*
		 * In vertical, the X values should be same, the Y value should only differ in one unit in
		 * up or down.
		 */
		
		if(that.first() == first() && (second() == (that.second() -1) || second() == (that.second() +1)))
			return true;
		
		return false;
	}
	
	public boolean isHorizontallySequential(IntegerPair that)
	{
		/* In order to compare sequentiality, we must ensure both pairs has value.*/
		if(!hasValue() || !that.hasValue())
			return false;
		/* We should check if the pairs are horizontally, vertically or diagonally sequential */
		
		/* In horizontal, the Y values should be same, the X value should only differ in one unit in
		 * either direction (left or right)
		 */
		
		if(that.second() == second() && (first() == (that.first() -1) || first() == (that.first() + 1)))
			return true;
		
		return false;
	}
	
	public boolean isDiagonallySequential(IntegerPair that)
	{
		
		/*
		 * In diagonal, both X and Y should only differ in one unit in either direction.
		 */
		
		/* In order to compare sequentiality, we must ensure both pairs has value.*/
		if(!hasValue() || !that.hasValue())
			return false;
		
		/* diagonal 1 */
		if(first() == (that.first() - 1) && second() == (that.second() - 1))
			return true;
		
		/* diagonal 2 */
		
		if(first() == (that.first() + 1) && second() == (that.second() + 1))
			return true;
		
		/* diagonal 3 */
		if(first() == (that.first() - 1) && second() == (that.second() + 1))
			return true;
		
		/* diagonal 4 */
		if(first() == (that.first() + 1) && second() == (that.second() - 1))
			return true;
		
		return false;
	}
	
	public boolean isSequential(IntegerPair that)
	{
		/* In order to compare sequentiality, we must ensure both pairs has value.*/
		if(!hasValue() || !that.hasValue())
			return false;
		
	
		return isVerticallySequential(that) || isHorizontallySequential(that) || isDiagonallySequential(that);
	}
}
