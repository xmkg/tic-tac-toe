package Definition;
import java.util.concurrent.atomic.AtomicInteger;

import Definition.Game.PointState;

/*
 * File 			: 	Counter.java
 * Author			:	Mustafa K. GILOR (PENTAGRAM)
 * Purpose			: 	A basic thread-safe score counter.
 * Created on 		:	14.11.2015
 * Last modified	:	14.11.2015
 * Status			:	Complete
 */

public class Counter 
{
	/* 
	 * In order to make this class thread-safe, 
	 * we should declare _val as an atomic variable.
	 * */
	private AtomicInteger _val;
	private int _max = 0;
	public Counter(int max,int initial)
	{
		_val = new AtomicInteger(initial);
		//_val.set(initial);
		_max = max;
	}
	
	public void IncrementByOne()
	{
		/* If the value is already maxed, do not increment it.*/
		if(_val.get() < _max)
			_val.incrementAndGet();
	}
	
	public void reset(int newValue)
	{
		_val.set(newValue);
	}
	
	
	
	public boolean isAtMax()
	{
		return _val.get() == _max;
	}
	
	/*
	 * I would define a simple integer for score parameter in this situation,
	 * but Java does not allow me to pass parameters as references.
	 * In C#, there is a "ref" keyword for this purpose, but AFAIK there's no
	 * equivalent mechanism exist in java.
	 * So, as the java objects pass by reference, I created an counter object
	 * just for achieving this goal.
	 */
	public static boolean ComparePointAndCount(int statePrev,int stateCurr,Counter cnt)
	{
		if(stateCurr == PointState.POINT_STATE_NONE)
		{
			cnt.reset(0);
			return false;
		}
		else if (stateCurr != statePrev)
		{
			cnt.reset(1);
			return false;
		}

		cnt.IncrementByOne();
		return true;
		
	}
	
	/*
	 * This will be used by AI to decide movement priorities.
	 */
	public static boolean ComparePointAndCount_Move(int statePrev,int stateCurr,Counter cnt)
	{
		/* We shall not increase the count, and we should return true */
		if(stateCurr == PointState.POINT_STATE_NONE)
			return true;
		
		else if (stateCurr != statePrev)
		{
			cnt.reset(1);
			return false;
		}

		cnt.IncrementByOne();
		return true;
	}
}
