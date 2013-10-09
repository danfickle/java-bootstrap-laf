package com.github.danfickle.jbootstrap.jbootstraplplusf;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EnumSet;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.*;
import javax.swing.text.View;

import com.github.danfickle.jbootstrap.jbootstraplplusf.StyleButton.BtnBaseStyle;
import com.github.danfickle.jbootstrap.jbootstraplplusf.StyleUtil.BorderSide;
import com.github.danfickle.jbootstrap.jbootstraplplusf.StyleUtil.ComponentState;
import com.github.danfickle.jbootstrap.jbootstraplplusf.StyleUtil.StyleQuad;

public class JBootstrapButtonUI extends BasicButtonUI implements MouseListener, FocusListener, PropertyChangeListener
{
	private static final String BORDER_ORIGINAL = "jbootstrap.buttonborderoriginal";
	private static final String OPACITY_ORIGINAL = "jbootstrap.buttonopacityoriginal";
	private static final String FONT_ORIGINAL = "jbootstrap.buttonfontoriginal";

	private final AbstractButton button;
	private BtnBaseStyle baseStyle;

	private Rectangle viewRect = new Rectangle();
	private Rectangle iconRect = new Rectangle();
	private Rectangle textRect = new Rectangle();
	
	private EnumSet<ComponentState> state = EnumSet.noneOf(ComponentState.class);

	public static ComponentUI createUI(JComponent comp) 
	{
		return new JBootstrapButtonUI((AbstractButton) comp);
	}

	public JBootstrapButtonUI(AbstractButton button) 
	{
		this.button = button;
		this.baseStyle = StyleButton.applyStyles("btn", state);
	}

	private boolean isJBootstrapButton(AbstractButton b)
	{
		String clss = (String) b.getClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS);
		return clss != null;
	}
	
	@Override
	protected void installDefaults(AbstractButton b) 
	{
		super.installDefaults(b);

		b.putClientProperty(FONT_ORIGINAL, b.getFont());
		b.setFont(baseStyle.getFont());

		b.putClientProperty(BORDER_ORIGINAL, b.getBorder());
		b.setBorder(getButtonBorder(b, baseStyle));

		b.putClientProperty(OPACITY_ORIGINAL, b.isOpaque());
		b.setOpaque(false);

		b.setFocusable(true);
		b.setRequestFocusEnabled(true);
		b.setRolloverEnabled(true);
	}

	@Override
	protected void uninstallDefaults(AbstractButton b) 
	{
		super.uninstallDefaults(b);

		if (!isJBootstrapButton(b))
			return;
		
		b.setBorder((Border) b.getClientProperty(JBootstrapButtonUI.BORDER_ORIGINAL));
		b.setOpaque((Boolean) b.getClientProperty(JBootstrapButtonUI.OPACITY_ORIGINAL));
		b.setFont((Font) b.getClientProperty(FONT_ORIGINAL));
		b.setOpaque((Boolean) b.getClientProperty(OPACITY_ORIGINAL));
	}

	@Override
	protected BasicButtonListener createButtonListener(AbstractButton b)
	{
		return null;
	}

	@Override
	protected void installListeners(final AbstractButton b)
	{
		super.installListeners(b);
		
		b.addMouseListener(this);
		b.addFocusListener(this);
		b.addPropertyChangeListener(this);
	}

	@Override
	protected void uninstallListeners(AbstractButton b) 
	{
		b.removeMouseListener(this);
		b.removeFocusListener(this);
		b.removePropertyChangeListener(this);

		super.uninstallListeners(b);
	}

    private Border getButtonBorder(final AbstractButton button, final BtnBaseStyle style)
    {
		return new AbstractBorder() 
		{
			private static final long serialVersionUID = 1L;

			@Override
            public Insets getBorderInsets(Component c) 
			{
				int left = style.getMargin().getLeft() + style.getFocusPadding().getLeft() + style.getPadding().getLeft();
				int right = style.getMargin().getRight() + style.getFocusPadding().getRight() + style.getPadding().getRight();
				int top = style.getMargin().getTop() + style.getFocusPadding().getTop() + style.getPadding().getTop();
				int bottom = style.getMargin().getBottom() + style.getFocusPadding().getBottom() + style.getPadding().getTop();
				
				return new Insets(top, left, bottom, right);
			}

			@Override
			public boolean isBorderOpaque() 
			{
				return false;
			}

			@Override
			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) 
			{
				Graphics2D g2d = (Graphics2D) g.create();
				
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				drawRoundedBorder(g2d,
						x + style.getFocusPadding().getLeft(),
						y + style.getFocusPadding().getTop(),
						width  - style.getFocusPadding().getLeft() - style.getFocusPadding().getRight(),
						height - style.getFocusPadding().getTop() - style.getFocusPadding().getBottom(), 
						style.getBorderColor(),
						(int) (getCornerRadius((AbstractButton) c, ((AbstractButton) c).getInsets()) * style.getBorderArcSize()),
						!state.contains(ComponentState.ACTIVE));
				
				g2d.dispose();
			}
		};
	}

	static void drawRoundedBorder(Graphics2D g2, int x, int y, int w, int h,
			StyleQuad<Color> borderColor, int arc, boolean boxShadow)
	{
		g2.setStroke(new BasicStroke(0.5f));
		
		drawBorder(g2, arc, x, y, w, h, borderColor.getTop(),    BorderSide.TOP);
		drawBorder(g2, arc, x, y, w, h, borderColor.getBottom(), BorderSide.BOTTOM);
		drawBorder(g2, arc, x, y, w, h, borderColor.getLeft(),   BorderSide.LEFT);
		drawBorder(g2, arc, x, y, w, h, borderColor.getRight(),  BorderSide.RIGHT);

		if (boxShadow)
		{
			drawBorder(g2, arc, x, y + 2, w, 1, BtnBaseStyle.BOX_SHADOW_COLOR, BorderSide.TOP);
		}
		
		g2.setColor(borderColor.getTop());
		g2.setClip(x, y, w, arc);
		g2.drawRoundRect(x, y, w - 1, h - 1, arc, arc);
		g2.drawRoundRect(x, y, w - 1, h - 1, arc, arc);

		g2.setColor(borderColor.getBottom());
		g2.setClip(x, y + h - arc, w, arc);
		g2.drawRoundRect(x, y, w - 1, h - 1, arc, arc);
		g2.drawRoundRect(x, y, w - 1, h - 1, arc, arc);
	}
	
	static void drawFocusRing(Graphics2D g2, int width, int height)
	{
		Stroke bs = new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, new float[] { 0f, 3f }, 10.0f);
		g2.setStroke(bs);
		g2.setColor(Color.BLACK);
		g2.drawRect(0, 0, width - 1, height - 1);
	}
	
	static void drawBorder(Graphics2D g2, int arc, int xxx, int yyy, int width, int height, Color color, BorderSide border)
	{
		int x, y, x2, y2;
		
		if (border == BorderSide.LEFT)
		{
			x  = xxx;
			x2 = xxx;
			y  = arc + yyy;
			y2 = height - arc + yyy;
		}
		else if (border == BorderSide.RIGHT)
		{
			x  = width + xxx - 1;
			x2 = width + xxx - 1;
			y  = arc + yyy;
			y2 = height - arc + yyy;
		}
		else if (border == BorderSide.BOTTOM)
		{
			x  = arc + xxx;
			x2 = width - arc + xxx;
			y  = height + yyy - 1;
			y2 = height + yyy - 1; 
		}
		else //if (border == BorderSide.TOP)
		{
			x  = arc + xxx;
			x2 = width - arc + xxx;
			y  = yyy;
			y2 = yyy;
		}

		g2.setColor(color);
		g2.drawLine(x, y, x2, y2);
	}
    
	public static float getClassicButtonCornerRadius(int fontSize)
	{
		return StyleUtil.getAdjustedSize(fontSize, 2, 6, 1, false);
	}
    
	private void paintBackground(AbstractButton b, Graphics2D g2d, BtnBaseStyle style, int x, int y, int width, int height)
	{
		if (!style.getGradient().isSolid())
		{
			LinearGradientPaint gradient = new LinearGradientPaint(x, y, x, y + height, 
				new float[] { 0.0f, 1.0f },
				new Color[] { style.getGradient().getStart(), style.getGradient().getEnd() });

			g2d.setPaint(gradient);
		}
		else
		{
			g2d.setColor(style.getGradient().getStart());
		}
		
		Insets insets = b.getInsets();
		
		g2d.fillRoundRect(x, y, width, height, (int) (getCornerRadius(b, insets) * style.getBorderArcSize()),
				(int) (getCornerRadius(b, insets) * style.getBorderArcSize()));
	}

	@Override
	public void paint(Graphics g, JComponent c) 
	{
		final AbstractButton b = (AbstractButton) c;

		BtnBaseStyle style = StyleButton.applyStyles((String) b.getClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS), state);
		
		FontMetrics fm = g.getFontMetrics();

		Insets i = c.getInsets();

		viewRect.x = i.left;
		viewRect.y = i.top;
		viewRect.width = b.getWidth() - (i.right + viewRect.x);
		viewRect.height = b.getHeight() - (i.bottom + viewRect.y);

		textRect.x = textRect.y = textRect.width = textRect.height = 0;
		iconRect.x = iconRect.y = iconRect.width = iconRect.height = 0;

		Font f = c.getFont();

		// layout the text and icon
		String text = SwingUtilities.layoutCompoundLabel(c, fm, b.getText(), b.getIcon(),
				b.getVerticalAlignment(), b.getHorizontalAlignment(),
				b.getVerticalTextPosition(), b.getHorizontalTextPosition(), 
				viewRect, iconRect, textRect,
				b.getText() == null ? 0 : b.getIconTextGap());

		Graphics2D g2d = (Graphics2D) g.create();

		View v = (View) c.getClientProperty(BasicHTML.propertyKey);
		g2d.setFont(f);

		paintBackground(b, g2d, style,
				style.getFocusPadding().getLeft(), 
				style.getFocusPadding().getTop(),
				b.getWidth() - style.getFocusPadding().getLeft() - style.getFocusPadding().getRight(),
				b.getHeight() - style.getFocusPadding().getTop() - style.getFocusPadding().getBottom());

		if (v != null)
			v.paint(g2d, textRect);
		else
			paintButtonText(g2d, b, textRect, text, style);

		// Paint the Icon
		if (b.getIcon() != null) {
			paintIcon(g2d, c, iconRect);
		}

		if (b.isFocusPainted() && state.contains(ComponentState.FOCUSED))
			drawFocusRing(g2d, b.getWidth(), b.getHeight());
	}

	@Override
	public Dimension getPreferredSize(JComponent c) 
	{
		AbstractButton button = (AbstractButton) c;

		if (!isJBootstrapButton(button))
			return super.getPreferredSize(c);
		
		// fix for defect 263
		Dimension superPref = super.getPreferredSize(button);
		if (superPref == null)
			return null;

		Dimension result = getPreferredSize(button, superPref);
		return result;
	}

	private static int getMinButtonWidth(int fontSize) {
		return 5 * fontSize + 12;
	}
	
    private Dimension getPreferredSize(AbstractButton button, Dimension uiPreferredSize) 
    {
		Dimension result;
		boolean toTweakWidth = false;
		boolean toTweakHeight = false;

		Icon icon = button.getIcon();
		boolean hasIcon = (icon != null);
		boolean hasText = (button.getText() != null && !button.getText().isEmpty());
		Insets margin = button.getMargin();

		result = uiPreferredSize;

		if (hasText) 
		{
			int baseWidth = uiPreferredSize.width;

			baseWidth = Math.max(baseWidth, getMinButtonWidth(baseStyle.getFont().getSize() - 12));

			result = new Dimension(baseWidth, uiPreferredSize.height);

			int baseHeight = result.height + baseStyle.getFocusPadding().getTop() + baseStyle.getFocusPadding().getBottom();

			result = new Dimension(result.width, baseHeight);
		}

		int iconPaddingWidth = 6 + 2 * 1 + baseStyle.getFocusPadding().getLeft() + baseStyle.getFocusPadding().getRight();
		int iconPaddingHeight = 6 + baseStyle.getFocusPadding().getBottom() + baseStyle.getFocusPadding().getTop();

		if (margin != null) 
		{
			iconPaddingWidth = Math.max(iconPaddingWidth, margin.left + margin.right);
			iconPaddingHeight = Math.max(iconPaddingHeight, margin.top + margin.bottom);
		}
		
		if (hasIcon) 
		{
			// check the icon height
			int iconHeight = icon.getIconHeight();
			
			if (iconHeight > (result.getHeight() - iconPaddingHeight))
			{
				result = new Dimension(result.width, iconHeight);
				toTweakHeight = true;
			}
			
			int iconWidth = icon.getIconWidth();
			
			if (iconWidth > (result.getWidth() - iconPaddingWidth)) {
				result = new Dimension(iconWidth, result.height);
				toTweakWidth = true;
			}
		}

		if (toTweakWidth) {
			result = new Dimension(result.width + iconPaddingWidth, result.height);
		}
		if (toTweakHeight) {
			result = new Dimension(result.width, result.height + iconPaddingHeight);
		}

		return result;
	}
	
	public float getCornerRadius(AbstractButton button, Insets insets) {
		return StyleUtil.getAdjustedSize(baseStyle.getFont().getSize(), 2, 6, 1, false);
	}
	
	@Override
	public boolean contains(JComponent c, int x, int y) {
		// TODO: More accurate test.
		return super.contains(c, x, y);
	}

	@Override
	protected void paintIcon(Graphics g, JComponent c, Rectangle iconRect) 
	{
		Graphics2D graphics = (Graphics2D) g.create();
		AbstractButton b = (AbstractButton) c;
		Icon icon = b.getIcon();
		icon.paintIcon(b, graphics, iconRect.x, iconRect.y);
		graphics.dispose();
	}

	private void paintButtonText(Graphics g, AbstractButton button,
			Rectangle textRect, String text, BtnBaseStyle style) 
	{
		paintText(g, button, textRect, text, button.getDisplayedMnemonicIndex(), style.getFont(), style.getTextColor(), null, null);
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

	@Override
	public void update(Graphics g, JComponent c) 
	{
		this.paint(g, c);
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		button.requestFocusInWindow();
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		state.add(ComponentState.HOVER);
		button.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		state.remove(ComponentState.HOVER);
		button.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		state.add(ComponentState.ACTIVE);
		button.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		state.remove(ComponentState.ACTIVE);
		button.repaint();
	}

	@Override
	public void focusGained(FocusEvent e) 
	{
		state.add(ComponentState.FOCUSED);
		button.repaint();
	}

	@Override
	public void focusLost(FocusEvent e) 
	{
		state.remove(ComponentState.FOCUSED);
		button.repaint();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (!evt.getPropertyName().equals(JBootstrapLF.JBOOTSTRAP_CLASS))
			return;
		
		String clss = (String) button.getClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS);
		
		if (clss != null)
		{
			this.baseStyle = StyleButton.applyStyles(clss, state);
			
			SwingUtilities.invokeLater(new Runnable() 
			{
				@Override
				public void run() 
				{
					button.setBorder(getButtonBorder(button, baseStyle));
					button.setFont(baseStyle.getFont());
					button.repaint();
				}
			});
			
		}
	}
}
