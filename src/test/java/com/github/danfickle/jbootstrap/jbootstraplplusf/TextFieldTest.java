package com.github.danfickle.jbootstrap.jbootstraplplusf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.github.danfickle.jbootstrap.jbootstraplplusf.JBootstrapTextField.JBootstrapTextFieldType;

public class TextFieldTest
{
	public static void main(String...strings)
	{
		JBootstrapLF lf = new JBootstrapLF();
		
		UIManager.LookAndFeelInfo lfInfo = new UIManager.LookAndFeelInfo(lf.getName(), lf.getClass().getName());
		UIManager.installLookAndFeel(lfInfo);
		try {
			UIManager.setLookAndFeel(lf.getClass().getCanonicalName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater( new Runnable() {
			
			@Override
			public void run() {
				JPanel comp = new JPanel(new FlowLayout());
				comp.setPreferredSize(new Dimension(500, 400));
				comp.setBackground(Color.white);		

				JBootstrapTextField txt = JBootstrapFactory.createTextField(15, "default", JBootstrapTextFieldType.DEFAULT);
				txt.setText("default text");
				comp.add(txt);
				
				txt = JBootstrapFactory.createTextField(15, "danger", JBootstrapTextFieldType.DANGER);
				comp.add(txt);

				txt = JBootstrapFactory.createTextField(15, "warning", JBootstrapTextFieldType.WARNING);
				comp.add(txt);

				txt = JBootstrapFactory.createTextField(15, "info", JBootstrapTextFieldType.INFO);
				comp.add(txt);

				txt = JBootstrapFactory.createTextField(15, "success search", JBootstrapTextFieldType.SUCCESS);
				txt.setSearch(true);
				comp.add(txt);

				JTextField txt2 = new JTextField(10);
				txt2.setText("plain");
				comp.add(txt2);
				
				JPasswordField psw = new JPasswordField(10);
				comp.add(psw);
				
				JFrame frame = new JFrame("Test");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(comp);
				frame.pack();
				frame.setVisible(true);
			}
		} );
	}
}
