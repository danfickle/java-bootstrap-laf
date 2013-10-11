package com.github.danfickle.jbootstrap.jbootstraplplusf;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

class StyleLabel
{
	static class LabelBaseStyle
	{
		protected ColorUIResource backgroundColor;
		protected ColorUIResource textColor;
		protected FontUIResource font;
		protected float borderArcSize;
		
		Color getBackgroundColor()
		{
			return backgroundColor;
		}
		
		Color getTextColor()
		{
			return textColor;
		}
		
		Font getFont()
		{
			return font;
		}
		
		float getBorderArcSize()
		{
			return borderArcSize;
		}
		
		void applyTo(LabelBaseStyle btn) {}
	}
	
	static class LabelDefaultStyle extends LabelBaseStyle
	{
		private static ColorUIResource BACKGROUND_COLOR = new ColorUIResource(0x999999);
		private static ColorUIResource TEXT_COLOR = new ColorUIResource(Color.WHITE);
		private static FontUIResource FONT = new FontUIResource(Font.SANS_SERIF, Font.BOLD, StyleUtil.getComponentFontSize(9));
		
		@Override
		void applyTo(LabelBaseStyle lbl) 
		{ 
			lbl.backgroundColor = BACKGROUND_COLOR;
			lbl.textColor = TEXT_COLOR;
			lbl.font = FONT;
			lbl.borderArcSize = 3;
		}

		static String appliesTo() 
		{
			return "label";
		}
	}
	
	static class LabelInfoStyle extends LabelBaseStyle
	{
		private static ColorUIResource BACKGROUND_COLOR = new ColorUIResource(0x3A87AD);
		
		@Override
		void applyTo(LabelBaseStyle lbl) 
		{ 
			lbl.backgroundColor = BACKGROUND_COLOR;
		}

		static String appliesTo() 
		{
			return "label-info";
		}
	}
	
	static class LabelWarningStyle extends LabelBaseStyle
	{
		private static ColorUIResource BACKGROUND_COLOR = new ColorUIResource(0xF89406);
		
		@Override
		void applyTo(LabelBaseStyle lbl) 
		{ 
			lbl.backgroundColor = BACKGROUND_COLOR;
		}

		static String appliesTo() 
		{
			return "label-warning";
		}
	}
	
	static class LabelImportantStyle extends LabelBaseStyle
	{
		private static ColorUIResource BACKGROUND_COLOR = new ColorUIResource(0xB94A48);
		
		@Override
		void applyTo(LabelBaseStyle lbl) 
		{ 
			lbl.backgroundColor = BACKGROUND_COLOR;
		}

		static String appliesTo() 
		{
			return "label-important";
		}
	}
	
	static class LabelInverseStyle extends LabelBaseStyle
	{
		private static ColorUIResource BACKGROUND_COLOR = new ColorUIResource(0x333333);
		
		@Override
		void applyTo(LabelBaseStyle lbl) 
		{ 
			lbl.backgroundColor = BACKGROUND_COLOR;
		}

		static String appliesTo() 
		{
			return "label-inverse";
		}
	}
	
	static class LabelSuccessStyle extends LabelBaseStyle
	{
		private static ColorUIResource BACKGROUND_COLOR = new ColorUIResource(0x468847);
		
		@Override
		void applyTo(LabelBaseStyle lbl) 
		{ 
			lbl.backgroundColor = BACKGROUND_COLOR;
		}

		static String appliesTo() 
		{
			return "label-success";
		}
	}

	static class BadgeDefaultStyle extends LabelBaseStyle
	{
		private static ColorUIResource BACKGROUND_COLOR = new ColorUIResource(0x999999);
		private static ColorUIResource TEXT_COLOR = new ColorUIResource(Color.WHITE);
		private static FontUIResource FONT = new FontUIResource(Font.SANS_SERIF, Font.BOLD, StyleUtil.getComponentFontSize(9));

		@Override
		void applyTo(LabelBaseStyle lbl) 
		{ 
			lbl.backgroundColor = BACKGROUND_COLOR;
			lbl.textColor = TEXT_COLOR;
			lbl.font = FONT;
			lbl.borderArcSize = 8;
		}

		static String appliesTo() 
		{
			return "badge";
		}
	}
	
	static class BadgeInfoStyle extends LabelBaseStyle
	{
		private static ColorUIResource BACKGROUND_COLOR = new ColorUIResource(0x3A87AD);
		
		@Override
		void applyTo(LabelBaseStyle lbl) 
		{ 
			lbl.backgroundColor = BACKGROUND_COLOR;
		}

		static String appliesTo() 
		{
			return "badge-info";
		}
	}
	
	static class BadgeWarningStyle extends LabelBaseStyle
	{
		private static ColorUIResource BACKGROUND_COLOR = new ColorUIResource(0xF89406);
		
		@Override
		void applyTo(LabelBaseStyle lbl) 
		{ 
			lbl.backgroundColor = BACKGROUND_COLOR;
		}

		static String appliesTo() 
		{
			return "badge-warning";
		}
	}
	
	static class BadgeImportantStyle extends LabelBaseStyle
	{
		private static ColorUIResource BACKGROUND_COLOR = new ColorUIResource(0xB94A48);
		
		@Override
		void applyTo(LabelBaseStyle lbl) 
		{ 
			lbl.backgroundColor = BACKGROUND_COLOR;
		}

		static String appliesTo() 
		{
			return "badge-important";
		}
	}
	
	static class BadgeInverseStyle extends LabelBaseStyle
	{
		private static ColorUIResource BACKGROUND_COLOR = new ColorUIResource(0x333333);
		
		@Override
		void applyTo(LabelBaseStyle lbl) 
		{ 
			lbl.backgroundColor = BACKGROUND_COLOR;
		}

		static String appliesTo() 
		{
			return "badge-inverse";
		}
	}
	
	static class BadgeSuccessStyle extends LabelBaseStyle
	{
		private static ColorUIResource BACKGROUND_COLOR = new ColorUIResource(0x468847);
		
		@Override
		void applyTo(LabelBaseStyle lbl) 
		{ 
			lbl.backgroundColor = BACKGROUND_COLOR;
		}

		static String appliesTo() 
		{
			return "badge-success";
		}
	}
	
	
	private static Map<String, LabelBaseStyle> styleMap = new HashMap<String, LabelBaseStyle>();
	
	static void registerStyles()
	{
		styleMap.clear();
		styleMap.put(LabelDefaultStyle.appliesTo(), new LabelDefaultStyle());
		styleMap.put(LabelInfoStyle.appliesTo(), new LabelInfoStyle());
		styleMap.put(LabelSuccessStyle.appliesTo(), new LabelSuccessStyle());
		styleMap.put(LabelWarningStyle.appliesTo(), new LabelWarningStyle());
		styleMap.put(LabelImportantStyle.appliesTo(), new LabelImportantStyle());
		styleMap.put(LabelInverseStyle.appliesTo(), new LabelInverseStyle());

		styleMap.put(BadgeDefaultStyle.appliesTo(), new BadgeDefaultStyle());
		styleMap.put(BadgeInfoStyle.appliesTo(), new BadgeInfoStyle());
		styleMap.put(BadgeSuccessStyle.appliesTo(), new BadgeSuccessStyle());
		styleMap.put(BadgeWarningStyle.appliesTo(), new BadgeWarningStyle());
		styleMap.put(BadgeImportantStyle.appliesTo(), new BadgeImportantStyle());
		styleMap.put(BadgeInverseStyle.appliesTo(), new BadgeInverseStyle());
	}
	
	private static LabelBaseStyle getStyle(String clz)
	{
		return styleMap.get(clz);
	}
	
	static LabelBaseStyle applyStyles(String clss)
	{
		String[] clsz = clss.split("\\s+");

		LabelBaseStyle over = new LabelBaseStyle();
		
		for (String clsItem : clsz)
		{
			LabelBaseStyle base = getStyle(clsItem);
			
			if (base != null)
			{
				base.applyTo(over);
			}
		}

		return over;
	}
}
