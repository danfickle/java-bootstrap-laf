package com.github.danfickle.jbootstrap.jbootstraplplusf;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.Highlight;
import javax.swing.text.JTextComponent;
import javax.swing.text.Highlighter.HighlightPainter;

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
		txt.setHighlighter(new MyHl());
	}
	
	private static class HL implements Highlight
	{
		int end;
		int start;
		HighlightPainter p;

		HL(int s, int e, HighlightPainter pp)
		{
			start = s;
			end = e;
			p = pp;
		}
		
		@Override
		public int getEndOffset() 
		{
			return end;
		}

		@Override
		public HighlightPainter getPainter() 
		{
			return p;
		}

		@Override
		public int getStartOffset() 
		{
			return start;
		}
		
		@Override
		public boolean equals(Object obj) 
		{
			if (!(obj instanceof HL))
				return false;
			
			HL b = (HL) obj;
			
			return start == b.start && end == b.end && p.equals(b.p);
		}
	}
	
	static class MyHl implements Highlighter
	{
		private JTextComponent t;
		private final List<Highlight> hls = new ArrayList<Highlight>(1);
		private static final HighlightPainter hlp = new DefaultHighlighter.DefaultHighlightPainter(new Color(0xFF9238));
		
		@Override
		public void install(JTextComponent c) 
		{
			t = c;
		}

		@Override
		public Object addHighlight(final int p0, final int p1, final HighlightPainter p)
				throws BadLocationException
		{
			HL h = new HL(p0, p1, p);
			hls.add(h);
			t.repaint();
			return h;
		}
		
		@Override
		public void paint(Graphics g)
		{
			if (hls.isEmpty())
				return;
		    
		    Shape bounds = t.getBounds();

		    for (Highlight hl : hls)
		    {
		    	hlp.paint(g, hl.getStartOffset(), hl.getEndOffset(), bounds, t);
		    }
		}
		
		@Override
		public void changeHighlight(Object tag, int p0, int p1)
				throws BadLocationException
		{
			((HL) tag).end = p1;
			((HL) tag).start = p0;
			t.repaint();
		}
		
		@Override
		public Highlight[] getHighlights()
		{
			return hls.toArray(new Highlight[hls.size()]);
		}

		@Override
		public void deinstall(JTextComponent c) { }

		@Override
		public void removeAllHighlights() 
		{
			hls.clear();
			t.repaint();
		}

		@Override
		public void removeHighlight(Object tag) 
		{
			hls.remove(tag);
			t.repaint();
		}
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
			return new Insets(8, 12, 8, 12);
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
	protected void paintBackground(Graphics g) 
	{
		super.paintBackground(g);
		
		Insets s = txt.getInsets();

		if (txt instanceof JBootstrapTextField && txt.getText().isEmpty() && !txt.hasFocus())
		{
			JLabel lbl = new JLabel(" " + ((JBootstrapTextField) txt).getPlaceholder());
			lbl.setFont(new Font(txt.getFont().getFontName(), Font.ITALIC, txt.getFont().getSize() - 1));
			lbl.setForeground(Color.gray);
			lbl.setBackground(txt.getBackground());
			
			SwingUtilities.paintComponent(g, lbl, txt, s.left, s.top, txt.getWidth() - s.left - s.right, txt.getHeight() - s.top - s.bottom);
		}
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
