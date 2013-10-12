package com.github.danfickle.jbootstrap.jbootstraplplusf;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTextFieldUI;

public class JBootstrapTextFieldUI extends BasicTextFieldUI implements FocusListener
{
	private final JTextField txt; 
	
	public static ComponentUI createUI(JComponent c)
	{
		return new JBootstrapTextFieldUI((JTextField) c);
	}
	
	private JBootstrapTextFieldUI(JTextField t)
	{
		txt = t;
	}

	@Override
	protected void installDefaults() 
	{
		super.installDefaults();
		if (txt instanceof JBootstrapTextField)
			installMyDefaults();
	}
	
	@Override
	protected void uninstallDefaults() 
	{
		txt.setBorder(null);
		super.uninstallDefaults();
	}
	
	@Override
	protected void installListeners() 
	{
		super.installListeners();
		txt.addFocusListener(this);
	}
	
	@Override
	protected void uninstallListeners() 
	{
		txt.removeFocusListener(this);
		super.uninstallListeners();
	}
	
	
	private void installMyDefaults()
	{
		txt.setBorder(new MyBorder());
	}
	
	private class MyBorder extends AbstractBorder
	{
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isBorderOpaque() 
		{
			return false;
		}
		
		@Override
        public Insets getBorderInsets(Component c) 
		{
			return new Insets(8, 8, 8, 8);
		}
		
		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) 
		{
			Graphics2D g2d = (Graphics2D) g.create();
			
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			drawRoundedBorder(g2d,
					x + 1,
					y + 1,
					width  - 4,
					height - 4, 
					((JBootstrapTextField) txt).getType().clr,
					(int) (getCornerRadius() * (((JBootstrapTextField) txt).isSearch() ? 9 : 4)),
					txt.hasFocus(), ((JBootstrapTextField) txt).getType().box);
			
			g2d.dispose();
		}
	}
	
	private static void drawRoundedBorder(Graphics2D g2, int x, int y, int w, int h,
			Color borderColor, int arc, boolean boxShadow, Color boxShadowColor)
	{
		if (boxShadow)
		{
			g2.setStroke(new BasicStroke(1.2f));
			g2.setColor(boxShadowColor);
			g2.drawRoundRect(x + 3, y + 3, w - 6, h - 6, arc, arc);
			
			g2.setColor(new Color(boxShadowColor.getRed(), boxShadowColor.getGreen(), boxShadowColor.getBlue(), 80));
			g2.drawRoundRect(x + 2, y + 2, w - 4, h - 4, arc, arc);

			g2.setColor(new Color(boxShadowColor.getRed(), boxShadowColor.getGreen(), boxShadowColor.getBlue(), 40));
			g2.drawRoundRect(x + 1, y + 1, w - 2, h - 2, arc, arc);			

			g2.setColor(new Color(boxShadowColor.getRed(), boxShadowColor.getGreen(), boxShadowColor.getBlue(), 20));
			g2.drawRoundRect(x, y, w - 1, h - 1, arc, arc);	
		}
		else
		{
			g2.setStroke(new BasicStroke(1.0f));
			g2.setColor(borderColor);
			g2.drawRoundRect(x + 1, y + 1, w - 2, h - 2, arc, arc);
		}
	}
	
	@Override
	public Dimension getPreferredSize(JComponent c) 
	{
		if (!(txt instanceof JBootstrapTextField))
			return super.getPreferredSize(c);
		
		Dimension superPref = super.getPreferredSize(txt);
		if (superPref == null)
			return null;

		int width = Math.max(getMinTextFieldWidth(txt.getFont().getSize()), superPref.width);
		int height = superPref.height;
		
		return new Dimension(width, height);
	}
	
	private static int getMinTextFieldWidth(int fontSize)
	{
		return 12 * fontSize + 12;
	}
	
	private float getCornerRadius() 
	{
		return StyleUtil.getAdjustedSize(txt.getFont().getSize(), 2, 6, 1, false);
	}

	@Override
	public void focusGained(FocusEvent e) 
	{
		txt.repaint();
	}

	@Override
	public void focusLost(FocusEvent e) 
	{
		txt.repaint();
	}
}
