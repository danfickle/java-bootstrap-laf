package com.github.danfickle.jbootstrap.jbootstraplplusf;

import java.awt.Font;
import java.awt.Color;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.github.danfickle.jbootstrap.jbootstraplplusf.StyleUtil.ComponentState;
import com.github.danfickle.jbootstrap.jbootstraplplusf.StyleUtil.StyleQuad;
import com.github.danfickle.jbootstrap.jbootstraplplusf.StyleUtil.StyleSimpleGradient;

public class StyleButton
{
	static class BtnBaseStyle
	{
		protected StyleSimpleGradient gradient;
		protected float borderArcSize;
		protected Font font;
		protected Color textColor;
		protected StyleQuad<Color> borderColor;
		protected StyleQuad<Integer> padding;
		protected StyleQuad<Integer> margin;
		protected StyleQuad<Integer> focusPadding;

		protected static final Color ALPHA1 = new Color(0, 0, 0, 0.1f);
		protected static final Color ALPHA2 = new Color(0, 0, 0, 0.25f);
		protected static final Color BOX_SHADOW_COLOR = new Color(1, 1, 1, 0.2f);
		
		StyleSimpleGradient getGradient()
		{
			return this.gradient;
		}

		float getBorderArcSize()
		{
			return this.borderArcSize;
		}

		Font getFont()
		{
			return this.font;
		}

		Color getTextColor()
		{
			return this.textColor;
		}

		StyleQuad<Color> getBorderColor()
		{
			return this.borderColor;
		}
		
		StyleQuad<Integer> getPadding()
		{
			return this.padding;
		}
		
		StyleQuad<Integer> getMargin()
		{
			return this.margin;
		}
		
		StyleQuad<Integer> getFocusPadding()
		{
			return this.focusPadding;
		}
		
		void applyTo(BtnBaseStyle btn) { };
	}

	private static class BtnDefaultStyle extends BtnBaseStyle
	{
		private static final StyleSimpleGradient GRADIENT = 
				new StyleSimpleGradient(Color.white, new Color(0xE6, 0xE6, 0xE6));

		private static final StyleQuad<Color> BORDER_COLOR = 
				new StyleQuad<Color>(ALPHA1, new Color(0xB3, 0xB3, 0xB3), ALPHA2, ALPHA1); 

		private static final StyleQuad<Integer> PADDING = new StyleQuad<Integer>(3, 5, 3, 5);
		
		private static final StyleQuad<Integer> MARGIN = new StyleQuad<Integer>(2, 2, 2, 2);

		private static final StyleQuad<Integer> FOCUS_PADDING = new StyleQuad<Integer>(3, 3, 3, 3);
		
		private static final Color TEXT_COLOR = new Color(0x33, 0x33, 0x33);
		
		private static final Font BTN_FONT = new Font("SansSerif", Font.PLAIN, StyleUtil.getComponentFontSize(12));
		
		static void apply(BtnBaseStyle override) 
		{
			override.gradient = GRADIENT;
			override.borderColor = BORDER_COLOR;
			override.textColor = TEXT_COLOR;
			override.padding = PADDING;
			override.margin = MARGIN;
			override.font = BTN_FONT;
			override.borderArcSize = 4.0f;
			override.focusPadding = FOCUS_PADDING;
		}

		static String appliesTo() 
		{
			return "btn";
		}

		@Override
		void applyTo(BtnBaseStyle btn) 
		{
			apply(btn);
		}
	}
	
	private static class BtnPrimaryStyle extends BtnBaseStyle
	{
		private static final StyleSimpleGradient GRADIENT = 
				new StyleSimpleGradient(new Color(0x00, 0x88, 0xCC), new Color(0x00, 0x44, 0xCC));
		
		private static final StyleQuad<Color> BORDER_COLOR = 
				new StyleQuad<Color>(ALPHA1, ALPHA1, ALPHA2, ALPHA1);
		
		static void apply(BtnBaseStyle override) 
		{
			override.textColor = Color.white;
			override.gradient = GRADIENT;
			override.borderColor = BORDER_COLOR;
		}
		
		static String appliesTo()
		{
			return "btn-primary";
		}

		@Override
		void applyTo(BtnBaseStyle btn) 
		{
			apply(btn);
		}
	}
	
	private static class BtnInfoStyle extends BtnBaseStyle
	{
		private static final StyleSimpleGradient GRADIENT = 
				new StyleSimpleGradient(new Color(0x5BC0DE), new Color(0x2F96B4));
		
		private static final StyleQuad<Color> BORDER_COLOR = 
				new StyleQuad<Color>(ALPHA1, ALPHA1, ALPHA2, ALPHA1);
		
		static void apply(BtnBaseStyle override) 
		{
			override.textColor = Color.white;
			override.gradient = GRADIENT;
			override.borderColor = BORDER_COLOR;
		}
		
		static String appliesTo()
		{
			return "btn-info";
		}

		@Override
		void applyTo(BtnBaseStyle btn) 
		{
			apply(btn);
		}
	}
	
	private static class BtnSuccessStyle extends BtnBaseStyle
	{
		private static final StyleSimpleGradient GRADIENT = 
				new StyleSimpleGradient(new Color(0x62C462), new Color(0x51A351));
		
		private static final StyleQuad<Color> BORDER_COLOR = 
				new StyleQuad<Color>(ALPHA1, ALPHA1, ALPHA2, ALPHA1);
		
		static void apply(BtnBaseStyle override) 
		{
			override.textColor = Color.white;
			override.gradient = GRADIENT;
			override.borderColor = BORDER_COLOR;
		}
		
		static String appliesTo()
		{
			return "btn-success";
		}

		@Override
		void applyTo(BtnBaseStyle btn) 
		{
			apply(btn);
		}
	}
	
	private static class BtnWarningStyle extends BtnBaseStyle
	{
		private static final StyleSimpleGradient GRADIENT = 
				new StyleSimpleGradient(new Color(0xFBB450), new Color(0xF89406));
		
		private static final StyleQuad<Color> BORDER_COLOR = 
				new StyleQuad<Color>(ALPHA1, ALPHA1, ALPHA2, ALPHA1);
		
		static void apply(BtnBaseStyle override) 
		{
			override.textColor = Color.white;
			override.gradient = GRADIENT;
			override.borderColor = BORDER_COLOR;
		}
		
		static String appliesTo()
		{
			return "btn-warning";
		}

		@Override
		void applyTo(BtnBaseStyle btn) 
		{
			apply(btn);
		}
	}
	
	private static class BtnInverseStyle extends BtnBaseStyle
	{
		private static final StyleSimpleGradient GRADIENT = 
				new StyleSimpleGradient(new Color(0x444444), new Color(0x222222));
		
		private static final StyleQuad<Color> BORDER_COLOR = 
				new StyleQuad<Color>(ALPHA1, ALPHA1, ALPHA2, ALPHA1);
		
		static void apply(BtnBaseStyle override) 
		{
			override.textColor = Color.white;
			override.gradient = GRADIENT;
			override.borderColor = BORDER_COLOR;
		}
		
		static String appliesTo()
		{
			return "btn-inverse";
		}

		@Override
		void applyTo(BtnBaseStyle btn) 
		{
			apply(btn);
		}
	}
	private static class BtnDangerStyle extends BtnBaseStyle
	{
		private static final StyleSimpleGradient GRADIENT = 
				new StyleSimpleGradient(new Color(0xEE5F5B), new Color(0xBD362F));
		
		private static final StyleQuad<Color> BORDER_COLOR = 
				new StyleQuad<Color>(ALPHA1, ALPHA1, ALPHA2, ALPHA1);
		
		static void apply(BtnBaseStyle override) 
		{
			override.textColor = Color.white;
			override.gradient = GRADIENT;
			override.borderColor = BORDER_COLOR;
		}
		
		static String appliesTo()
		{
			return "btn-danger";
		}

		@Override
		void applyTo(BtnBaseStyle btn) 
		{
			apply(btn);
		}
	}

	private static class BtnDangerHoverStyle extends BtnBaseStyle
	{
		private static final StyleSimpleGradient GRADIENT = 
				new StyleSimpleGradient(new Color(0xBD362F), null);
		
		public void apply(BtnBaseStyle override) 
		{
			override.gradient = GRADIENT;
		}
		
		static String appliesTo()
		{
			return "btn-danger:hover";
		}

		@Override
		void applyTo(BtnBaseStyle btn) 
		{
			apply(btn);
		}
	}
	
	private static class BtnDefaultHoverStyle extends BtnBaseStyle
	{
		private static final StyleSimpleGradient GRADIENT = 
				new StyleSimpleGradient(new Color(0xE6E6E6), null);
		
		public void apply(BtnBaseStyle override) 
		{
			override.gradient = GRADIENT;
		}
		
		static String appliesTo()
		{
			return "btn:hover";
		}

		@Override
		void applyTo(BtnBaseStyle btn) 
		{
			apply(btn);
		}
	}
	
	private static class BtnInfoHoverStyle extends BtnBaseStyle
	{
		private static final StyleSimpleGradient GRADIENT = 
				new StyleSimpleGradient(new Color(0x2F96B4), null);
		
		public void apply(BtnBaseStyle override) 
		{
			override.gradient = GRADIENT;
		}
		
		static String appliesTo()
		{
			return "btn-info:hover";
		}

		@Override
		void applyTo(BtnBaseStyle btn) 
		{
			apply(btn);
		}
	}
	
	private static class BtnSuccessHoverStyle extends BtnBaseStyle
	{
		private static final StyleSimpleGradient GRADIENT = 
				new StyleSimpleGradient(new Color(0x51A351), null);
		
		public void apply(BtnBaseStyle override) 
		{
			override.gradient = GRADIENT;
		}
		
		static String appliesTo()
		{
			return "btn-success:hover";
		}

		@Override
		void applyTo(BtnBaseStyle btn) 
		{
			apply(btn);
		}
	}
	
	private static class BtnWarningHoverStyle extends BtnBaseStyle
	{
		private static final StyleSimpleGradient GRADIENT = 
				new StyleSimpleGradient(new Color(0xF89406), null);
		
		public void apply(BtnBaseStyle override) 
		{
			override.gradient = GRADIENT;
		}
		
		static String appliesTo()
		{
			return "btn-warning:hover";
		}

		@Override
		void applyTo(BtnBaseStyle btn) 
		{
			apply(btn);
		}
	}
	
	private static class BtnInverseHoverStyle extends BtnBaseStyle
	{
		private static final StyleSimpleGradient GRADIENT = 
				new StyleSimpleGradient(new Color(0x222222), null);
		
		public void apply(BtnBaseStyle override) 
		{
			override.gradient = GRADIENT;
		}
		
		static String appliesTo()
		{
			return "btn-inverse:hover";
		}

		@Override
		void applyTo(BtnBaseStyle btn) 
		{
			apply(btn);
		}
	}
	
	private static class BtnPrimaryHoverStyle extends BtnBaseStyle
	{
		private static final StyleSimpleGradient GRADIENT = 
				new StyleSimpleGradient(new Color(0x0044CC), null);
		
		public void apply(BtnBaseStyle override) 
		{
			override.gradient = GRADIENT;
		}
		
		static String appliesTo()
		{
			return "btn-primary:hover";
		}

		@Override
		void applyTo(BtnBaseStyle btn) 
		{
			apply(btn);
		}
	}
	
	private static class BtnMiniStyle extends BtnBaseStyle
	{
		private static final StyleQuad<Integer> PADDING = new StyleQuad<Integer>(1, 1, 1, 1);
		private static final StyleQuad<Integer> MARGIN = new StyleQuad<Integer>(0, 0, 0, 0);
		private static final Font FONT = new Font("SansSerif", Font.PLAIN, StyleUtil.getComponentFontSize(9));

		public void apply(BtnBaseStyle override) 
		{
			override.padding = PADDING;
			override.margin = MARGIN;
			override.font = FONT;
		}
		
		static String appliesTo()
		{
			return "btn-mini";
		}

		@Override
		void applyTo(BtnBaseStyle btn) 
		{
			apply(btn);
		}
	}
	
	private static class BtnSmallStyle extends BtnBaseStyle
	{
		private static final StyleQuad<Integer> PADDING = new StyleQuad<Integer>(1, 1, 1, 1);
		private static final StyleQuad<Integer> MARGIN = new StyleQuad<Integer>(1, 1, 1, 1);
		private static final Font FONT = new Font("SansSerif", Font.PLAIN, StyleUtil.getComponentFontSize(11));

		public void apply(BtnBaseStyle override) 
		{
			override.padding = PADDING;
			override.margin = MARGIN;
			override.font = FONT;
		}
		
		static String appliesTo()
		{
			return "btn-small";
		}

		@Override
		void applyTo(BtnBaseStyle btn) 
		{
			apply(btn);
		}
	}
	
	private static class BtnLargeStyle extends BtnBaseStyle
	{
		private static final StyleQuad<Integer> PADDING = new StyleQuad<Integer>(5, 5, 5, 5);
		private static final StyleQuad<Integer> MARGIN = new StyleQuad<Integer>(3, 3, 3, 3);
		private static final Font FONT = new Font("SansSerif", Font.PLAIN, StyleUtil.getComponentFontSize(14));

		public void apply(BtnBaseStyle override) 
		{
			override.padding = PADDING;
			override.margin = MARGIN;
			override.font = FONT;
		}
		
		static String appliesTo()
		{
			return "btn-large";
		}

		@Override
		void applyTo(BtnBaseStyle btn) 
		{
			apply(btn);
		}
	}
	
	
	private static Map<String, BtnBaseStyle> styleMap = new HashMap<String, BtnBaseStyle>();
	
	public static void registerStyles()
	{
		styleMap.clear();
		styleMap.put(BtnDefaultStyle.appliesTo(), new BtnDefaultStyle());
		styleMap.put(BtnPrimaryStyle.appliesTo(), new BtnPrimaryStyle());
		styleMap.put(BtnInfoStyle.appliesTo(), new BtnInfoStyle());
		styleMap.put(BtnWarningStyle.appliesTo(), new BtnWarningStyle());
		styleMap.put(BtnSuccessStyle.appliesTo(), new BtnSuccessStyle());
		styleMap.put(BtnInverseStyle.appliesTo(), new BtnInverseStyle());
		styleMap.put(BtnDangerStyle.appliesTo(), new BtnDangerStyle());

		styleMap.put(BtnDangerHoverStyle.appliesTo(), new BtnDangerHoverStyle());
		styleMap.put(BtnPrimaryHoverStyle.appliesTo(), new BtnPrimaryHoverStyle());
		styleMap.put(BtnInfoHoverStyle.appliesTo(), new BtnInfoHoverStyle());
		styleMap.put(BtnSuccessHoverStyle.appliesTo(), new BtnSuccessHoverStyle());
		styleMap.put(BtnInverseHoverStyle.appliesTo(), new BtnInverseHoverStyle());
		styleMap.put(BtnWarningHoverStyle.appliesTo(), new BtnWarningHoverStyle());
		styleMap.put(BtnDefaultHoverStyle.appliesTo(), new BtnDefaultHoverStyle());
		
		styleMap.put(BtnMiniStyle.appliesTo(), new BtnMiniStyle());
		styleMap.put(BtnSmallStyle.appliesTo(), new BtnSmallStyle());
		styleMap.put(BtnLargeStyle.appliesTo(), new BtnLargeStyle());
	}
	
	private static BtnBaseStyle getStyle(String clz)
	{
		return styleMap.get(clz);
	}
	
	public static BtnBaseStyle applyStyles(String clss, EnumSet<ComponentState> state)
	{
		String[] clsz = clss.split("\\s+");

		BtnBaseStyle over = new BtnBaseStyle();
		
		for (String clsItem : clsz)
		{
			BtnBaseStyle base = StyleButton.getStyle(clsItem);
			
			if (base != null)
			{
				base.applyTo(over);
			}
		}

		applyExtra(over, clss, state);
		
		return over;
	}
	
	static void applyExtra(BtnBaseStyle over, String clss, EnumSet<ComponentState> state) 
	{
		String[] split = clss.split("\\s+");
		for (ComponentState st : state)
		{
			for (String cls : split)
			{
				BtnBaseStyle base = StyleButton.getStyle(cls + ':' + st.name);

				if (base != null)
					base.applyTo(over);
			}
		}
	}
}