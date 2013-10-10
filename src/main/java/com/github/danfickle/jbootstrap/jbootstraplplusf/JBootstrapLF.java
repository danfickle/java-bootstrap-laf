package com.github.danfickle.jbootstrap.jbootstraplplusf;

import javax.swing.UIDefaults;
import javax.swing.plaf.basic.BasicLookAndFeel;

@SuppressWarnings("serial")
public class JBootstrapLF extends BasicLookAndFeel
{
	public static final String JBOOTSTRAP_CLASS = "jbootstrap-class";
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Swing Look and Feel based on Twitter Bootstrap";
	}

	@Override
	public String getID() {
		return "JBootstrap";
	}

	@Override
	public String getName() {
		return "JBootstrap";
	}

	@Override
	public boolean isNativeLookAndFeel() {
		return false;
	}

	@Override
	public boolean isSupportedLookAndFeel() {
		return true;
	}

	@Override
	protected void initClassDefaults(UIDefaults table) {
		super.initClassDefaults(table);
		StyleButton.registerStyles();
		StyleLabel.registerStyles();

		table.put("ButtonUI", JBootstrapButtonUI.class.getCanonicalName());
		table.put("LabelUI", JBootstrapLabelUI.class.getCanonicalName());
	}
}
