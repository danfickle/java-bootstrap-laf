package com.github.danfickle.jbootstrap.jbootstraplplusf;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.plaf.basic.BasicLabelUI;
import javax.swing.text.View;

import com.github.danfickle.jbootstrap.jbootstraplplusf.StyleLabel.LabelBaseStyle;

public class JBootstrapLabelUI extends BasicLabelUI implements PropertyChangeListener
{
	private static final String FONT_ORIGINAL = "jbootstrap.labelfontoriginal";
	private static final String BACKGROUND_COLOR_ORIGINAL = "jbootstrap.labelbackgroundoriginal";
	private static final String TEXT_COLOR_ORIGINAL = "jbootstrap.labelcolororiginal";
	private static final String BORDER_ORIGINAL = "jbootstrap.borderoriginal";

	private LabelBaseStyle baseStyle;
	private final JLabel lbl;
	private Rectangle paintIconR = new Rectangle();
	private Rectangle paintTextR = new Rectangle();
	private Rectangle paintViewR = new Rectangle();
	private Insets paintViewInsets = new Insets(0, 0, 0, 0);
	
	public static ComponentUI createUI(JComponent comp) 
	{
		return new JBootstrapLabelUI((JLabel) comp);
	}

	private boolean isJBootstrapLabel(JLabel b)
	{
		String clss = (String) b.getClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS);
		return clss != null;
	}
	
	private JBootstrapLabelUI(JLabel lbl)
	{
		this.lbl = lbl;
		this.baseStyle = StyleLabel.applyStyles("label");
	}
	
	@Override
	protected void installDefaults(JLabel b) 
	{
		b.putClientProperty(FONT_ORIGINAL, b.getFont());
		b.putClientProperty(BACKGROUND_COLOR_ORIGINAL, b.getBackground());
		b.putClientProperty(TEXT_COLOR_ORIGINAL, b.getForeground());
		b.putClientProperty(BORDER_ORIGINAL, b.getBorder());
		
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
	public void update(Graphics g, JComponent c) 
	{
		paint(g, c);
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
	
	private void paintBackground(JLabel b, Graphics2D g2d, int x, int y, int width, int height)
	{
		g2d.setColor(b.getBackground());
		g2d.fillRoundRect(x, y, width, height, (int) (getCornerRadius(b.getFont()) * baseStyle.getBorderArcSize()),
				(int) (getCornerRadius(b.getFont()) * baseStyle.getBorderArcSize()));
	}

	@Override
	public void paint(Graphics g, JComponent c)
	{
		JLabel label = (JLabel) c;

		if (!isJBootstrapLabel(label))
		{
			super.paint(g, c);
			return;
		}
		
		String text = label.getText();

		Icon icon = label.isEnabled() ? label.getIcon() : label.getDisabledIcon();

		Insets insets = label.getInsets(paintViewInsets);
		paintViewR.x = insets.left;
		paintViewR.y = insets.top;
		paintViewR.width = c.getWidth() - (insets.left + insets.right);
		paintViewR.height = c.getHeight() - (insets.top + insets.bottom);
		paintIconR.x = paintIconR.y = paintIconR.width = paintIconR.height = 0;
		paintTextR.x = paintTextR.y = paintTextR.width = paintTextR.height = 0;

		String clippedText = SwingUtilities.layoutCompoundLabel(label, g
				.getFontMetrics(), text, icon, label.getVerticalAlignment(),
				label.getHorizontalAlignment(),
				label.getVerticalTextPosition(), label
						.getHorizontalTextPosition(), paintViewR, paintIconR,
				paintTextR, label.getIconTextGap());

		Graphics2D g2d = (Graphics2D) g.create();

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		paintBackground(label, g2d, 0, 0, label.getWidth(), label.getHeight());

		if (icon != null) {
			icon.paintIcon(c, g2d, paintIconR.x, paintIconR.y);
		}

		if (text != null) {
			final View v = (View) c.getClientProperty(BasicHTML.propertyKey);
			if (v != null) {
				v.paint(g2d, paintTextR);
			} else {
				paintText(g, label, paintTextR, clippedText, label.getDisplayedMnemonicIndex(),
						lbl.getFont(), c.getForeground(), null, null);
			}
		}
		g2d.dispose();
	}
	
	private static void paintText(Graphics g, JComponent comp, Rectangle textRect,
			String text, int mnemonicIndex, Font font, Color color, Rectangle clip,
			AffineTransform transform) 
	{
		if ((text == null) || (text.isEmpty()))
			return;

		Graphics2D g2d = (Graphics2D) g.create();

		g2d.setFont(font);
		g2d.setColor(color);
		// fix for issue 420 - call clip() instead of setClip() to
		// respect the currently set clip shape
		if (clip != null)
			g2d.clip(clip);
		if (transform != null)
			g2d.transform(transform);
		BasicGraphicsUtils.drawStringUnderlineCharAt(g2d, text, mnemonicIndex,
				textRect.x, textRect.y + g2d.getFontMetrics().getAscent());
		g2d.dispose();
	}
	
	private float getCornerRadius(Font f) {
		return StyleUtil.getAdjustedSize(f.getSize(), 2, 6, 1, false);
	}
}
