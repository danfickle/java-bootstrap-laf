package com.github.danfickle.jbootstrap.jbootstraplplusf;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicLabelUI;

import com.github.danfickle.jbootstrap.jbootstraplplusf.StyleLabel.LabelBaseStyle;

public class JBootstrapLabelUI extends BasicLabelUI implements PropertyChangeListener
{
	private LabelBaseStyle baseStyle;
	private final JLabel lbl;

	public static ComponentUI createUI(JComponent comp) 
	{
		return new JBootstrapLabelUI((JLabel) comp);
	}

	private boolean isJBootstrapLabel(JLabel b)
	{
		String clss = (String) b.getClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS);

		if (baseStyle == null && clss != null)
			baseStyle = StyleLabel.applyStyles(clss);
		
		return clss != null;
	}
	
	private JBootstrapLabelUI(JLabel lbl)
	{
		this.lbl = lbl;
	}
	
	@Override
	protected void uninstallDefaults(JLabel c) 
	{
		if (lbl.getBorder() != null)
			lbl.setBorder(null);
		
		if ((lbl.getForeground() instanceof UIResource))
			lbl.setForeground(null);

		if ((lbl.getFont() instanceof UIResource))
			lbl.setFont(null);
		
		if ((lbl.getBackground() instanceof UIResource))
			lbl.setBackground(null);
	}
	
	@Override
	protected void installDefaults(JLabel b) 
	{
		if (isJBootstrapLabel(b))
			installMyDefaults();
	}
	
	private void installMyDefaults()
	{
		lbl.setBorder(new EmptyBorder(new Insets(4, 4, 4, 4)));
		lbl.setForeground(baseStyle.getTextColor());
		lbl.setFont(baseStyle.getFont());
		lbl.setBackground(baseStyle.getBackgroundColor());
		lbl.repaint();
	}
	
	@Override
	protected void installListeners(JLabel c) 
	{
		c.addPropertyChangeListener(this);
	}
	
	@Override
	protected void uninstallListeners(JLabel c) 
	{
		c.removePropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) 
	{
		if (!evt.getPropertyName().equals(JBootstrapLF.JBOOTSTRAP_CLASS))
			return;
		
		String clss = (String) lbl.getClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS);
		
		if (clss != null)
		{
			this.baseStyle = StyleLabel.applyStyles(clss);
			
			SwingUtilities.invokeLater(new Runnable() 
			{
				@Override
				public void run() 
				{
					installMyDefaults();
				}
			});
		}
	}
	
	@Override
	public void paint(Graphics g, JComponent c) 
	{
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if (!isJBootstrapLabel((JLabel) c))
		{
			super.paint(g, c);
			return;
		}
		
		Graphics2D g2 = (Graphics2D) g.create();
		Color bgSaved = c.getBackground();
		
		//g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		paintBackground((JLabel) c, g2, 0, 0, c.getWidth(), c.getHeight());

		c.setBackground(null);
		super.paint(g2, c);
		c.setBackground(bgSaved);
	}
	
	private void paintBackground(JLabel b, Graphics2D g2d, int x, int y, int width, int height)
	{
		g2d.setColor(b.getBackground());
		g2d.fillRoundRect(x, y, width, height, (int) (getCornerRadius(b.getFont()) * baseStyle.getBorderArcSize()),
				(int) (getCornerRadius(b.getFont()) * baseStyle.getBorderArcSize()));
	}
	
	private float getCornerRadius(Font f) 
	{
		return StyleUtil.getAdjustedSize(f.getSize(), 2, 6, 1, false);
	}
}
