package com.github.danfickle.jbootstrap.jbootstraplplusf;

import java.awt.Color;

import javax.swing.JTextField;

public class JBootstrapTextField extends JTextField
{
	private static final long serialVersionUID = 1L;

	private JBootstrapTextFieldType tp = JBootstrapTextFieldType.DEFAULT;
	private String ph, pp, ap;
	private boolean search;
	
	public enum JBootstrapTextFieldType
	{
		DEFAULT(new Color(0xCCCCCC), new Color(82, 168, 236, 200)),
		WARNING(new Color(0xC09853), new Color(0xC0, 0x98, 0x53, 200)),
		SUCCESS(new Color(0x468847), new Color(0x46, 0x88, 0x47, 200)),
		INFO(new Color(0x3A87AD), new Color(0x3A, 0x87, 0xAD, 200)),
		DANGER(new Color(0xB94A48), new Color(0xB9, 0x4A, 0x48, 200));
		
		final Color clr;
		final Color box;
		
		JBootstrapTextFieldType(Color c, Color d)
		{
			clr = c;
			box = d;
		}
	}
	
	public void setType(JBootstrapTextFieldType type)
	{
		tp = type;
		repaint();
	}
	
	public void setPlaceholder(String placeholder)
	{
		ph = placeholder;
		repaint();
	}
	
	public JBootstrapTextFieldType getType()
	{
		return tp;
	}
	
	public String getPlaceholder()
	{
		return ph;
	}
	
	/**
	 * Adds an extra rounded border.
	 */
	public void setSearch(boolean isSearch)
	{
		search = isSearch;
		repaint();
	}

	public boolean isSearch()
	{
		return search;
	}

	public void setPrepend(String prepend)
	{
		pp = prepend;
		repaint();
	}

	public String getPrepend()
	{
		return pp;
	}

	public void setAppend(String append)
	{
		ap = append;
		repaint();
	}

	public String getAppend()
	{
		return ap;
	}
}
