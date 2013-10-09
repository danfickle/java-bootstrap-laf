package com.github.danfickle.jbootstrap.jbootstraplplusf;

import java.awt.Color;
import java.awt.Toolkit;

class StyleUtil
{
	enum ComponentState
	{
		HOVER("hover"),
		ACTIVE("active"),
		DISABLED("disabled"),
		FOCUSED("focus");
		
		final String name;
		
		private ComponentState(String display)
		{
			this.name = display;
		}
	}
	
	
	static class StyleSimpleGradient
	{
		private final Color start;
		private final Color end;

		StyleSimpleGradient(Color start, Color end) 
		{
			this.start = start;
			this.end = end;
		}

		Color getStart() 
		{
			return this.start;
		}

		Color getEnd() 
		{
			return this.end;
		}

		boolean isSolid()
		{
			return this.end == null || this.start.equals(this.end);
		}
	}
	
	static class StyleQuad<T> 
	{
		private final T top;
		private final T bottom;
		private final T left;
		private final T right;
		
		StyleQuad(T top, T right, T bottom, T left)
		{
			this.top = top;
			this.right = right;
			this.bottom = bottom;
			this.left = left;
		}
		
		T getTop()
		{
			return this.top;
		}
		
		T getRight()
		{
			return this.right;
		}

		T getBottom()
		{
			return this.bottom;
		}

		T getLeft()
		{
			return this.left;
		}
	}
	
	enum BorderSide 
	{
		TOP, LEFT, RIGHT, BOTTOM;
	}

	static int getComponentFontSize(int ptSize)
	{
		// TODO: Headless.
		int screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
		int fontSize = (int) Math.round(ptSize * screenRes / 72.0);
		return fontSize;
	}
	
	/**
	 * Gets the adjusted size. The basic functionality of this method is as
	 * follows:
	 * 
	 * <ul>
	 * <li>The <code>baseSize</code> parameter specifies the base value</li>
	 * <li>The <code>forEachBase</code> and <code>toAdjustBy</code> specify how
	 * to adjust the resulting value based on the passed <code>fontSize</code>.</li>
	 * </ul>
	 * 
	 * For example, if you want base value to be 1.2 pixels, and have it grow by
	 * 0.1 pixel for every additional pixel in the font size, call this method
	 * with the following values:
	 * 
	 * <ul>
	 * <li><code>baseSize</code> = 1.2</li>
	 * <li><code>forEachBase</code> = 1</li>
	 * <li><code>toAdjustBy</code> = 0.1</li>
	 * </ul>
	 * 
	 * @param fontSize
	 *            Font size.
	 * @param baseSize
	 *            The base value.
	 * @param forEachBase
	 *            Base units for computing the adjustment.
	 * @param toAdjustBy
	 *            Adjustment amount for computing the adjustment.
	 * @return Adjusted size.
	 */
	static float getAdjustedSize(int fontSize, float baseSize, int forEachBase, float toAdjustBy)
	{
		int delta = fontSize - 11;

		if (delta <= 0)
			return baseSize;

		return baseSize + delta * toAdjustBy / forEachBase;
	}
	
	/**
	 * Gets the adjusted size. The basic functionality of this method is as
	 * follows:
	 * 
	 * <ul>
	 * <li>The <code>baseSize</code> parameter specifies the base value</li>
	 * <li>The <code>forEachBase</code> and <code>toAdjustBy</code> specify how
	 * to adjust the resulting value based on the passed <code>fontSize</code>.</li>
	 * </ul>
	 * 
	 * For example, if you want base value to be 4 pixels, and have it grow by 1
	 * pixel for every 3 additional pixels in the font size, call this method
	 * with the following values:
	 * 
	 * <ul>
	 * <li><code>baseSize</code> = 4</li>
	 * <li><code>forEachBase</code> = 3</li>
	 * <li><code>toAdjustBy</code> = 1</li>
	 * </ul>
	 * 
	 * @param fontSize
	 *            Font size.
	 * @param baseSize
	 *            The base value.
	 * @param forEachBase
	 *            Base units for computing the adjustment.
	 * @param toAdjustBy
	 *            Adjustment amount for computing the adjustment.
	 * @param toRoundAsEven
	 *            If <code>true</code>, the final value will be rounded down to
	 *            the closest even value.
	 * @return Adjusted size.
	 */
	public static int getAdjustedSize(int fontSize, int baseSize,
			int forEachBase, int toAdjustBy, boolean toRoundAsEven)
	{
		int delta = fontSize - 11;
		
		if (delta <= 0)
			return baseSize;
		
		int result = baseSize + delta * toAdjustBy / forEachBase;

		if (toRoundAsEven && (result % 2 != 0))
			result--;

		return result;
	}
}
