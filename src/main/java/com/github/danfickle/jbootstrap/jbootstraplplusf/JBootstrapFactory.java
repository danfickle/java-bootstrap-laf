package com.github.danfickle.jbootstrap.jbootstraplplusf;

import java.awt.Color;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.github.danfickle.jbootstrap.jbootstraplplusf.JBootstrapTextField.JBootstrapTextFieldType;

public class JBootstrapFactory
{
	public static enum JBootstrapLabelType
	{
		DEFAULT("label"),
		INFO("label label-info"),
		SUCCESS("label label-success"),
		WARNING("label label-warning"),
		IMPORTANT("label label-important"),
		INVERSE("label label-inverse");
		
		final String cls;
		
		private JBootstrapLabelType(String clz) 
		{
			cls = clz;
		}
	}

	public static enum JBootstrapBadgeType
	{
		DEFAULT("badge"),
		INFO("badge badge-info"),
		SUCCESS("badge badge-success"),
		WARNING("badge badge-warning"),
		IMPORTANT("badge badge-important"),
		INVERSE("badge badge-inverse");
		
		final String cls;
		
		private JBootstrapBadgeType(String clz) 
		{
			cls = clz;
		}
	}
	
	public static enum JBootstrapTextType
	{
		DEFAULT(Color.BLACK, "black"),
		INFO(new Color(0x3A87AD), "#3A87AD"),
		SUCCESS(new Color(0x468847), "#468847"),
		WARNING(new Color(0xC09853), "#C09853"),
		ERROR(new Color(0xB94A48), "#B94A48");
		
		final Color clr;
		final String hClr;
		
		private JBootstrapTextType(Color clz, String htmlColor) 
		{
			clr = clz;
			hClr = htmlColor;
		}
	}	
	
	public static enum JBootstrapButtonType
	{
		DEFAULT("btn"),
		INFO("btn btn-info"),
		SUCCESS("btn btn-success"),
		WARNING("btn btn-warning"),
		DANGER("btn btn-danger"),
		INVERSE("btn btn-inverse"),
		PRIMARY("btn btn-primary");

		final String cls;
		
		private JBootstrapButtonType(String clz) 
		{
			cls = clz;
		}
	}
	
	public static enum JBootstrapButtonSize
	{
		DEFAULT("btn-default"),
		MINI("btn-mini"),
		SMALL("btn-small"),
		LARGE("btn-large");
		
		final String cls;
		
		private JBootstrapButtonSize(String clz) 
		{
			cls = clz;
		}
	}
	
	public static JLabel createLabel(String text, JBootstrapLabelType type)
	{
		JLabel lbl = new JLabel(text);
		lbl.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, type.cls);
		return lbl;
	}
	
	public static JLabel createBadge(String text, JBootstrapBadgeType type)
	{
		JLabel lbl = new JLabel(text);
		lbl.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, type.cls);
		return lbl;
	}
	
	public static JLabel createText(String text, JBootstrapTextType type)
	{
		JLabel lbl = new JLabel(text);
		lbl.setBackground(Color.WHITE);
		lbl.setForeground(type.clr);
		return lbl;
	}

	/**
	 * WARNING: Remember to resize when window, frame, etc is resized.
	 */
	public static JLabel createWrappedText(String text, JBootstrapTextType type, int pxWidth)
	{
		JLabel lbl = new JLabel("<html><body width=\"" + pxWidth + "\" style=\"color:" + type.hClr + ";\">" + text.replaceAll(Pattern.quote("<"), "&lt;").replaceAll(Pattern.quote(">"), "&gt;") + "</body></html>");
		lbl.setBackground(Color.WHITE);
		return lbl;
	}

	public static JButton createButton(String text, JBootstrapButtonType type, JBootstrapButtonSize sz)
	{
		JButton btn = new JButton(text);
		btn.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, type.cls + " " + sz.cls);
		return btn;
	}
	
	public static JBootstrapTextField createTextField(int cols, String placeholder, JBootstrapTextFieldType tp)
	{
		JBootstrapTextField txt = new JBootstrapTextField();
		txt.setPlaceholder(placeholder);
		txt.setType(tp);
		txt.setColumns(cols);
		return txt;
	}
}
