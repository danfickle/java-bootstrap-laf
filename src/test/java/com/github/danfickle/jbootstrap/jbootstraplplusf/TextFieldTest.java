package com.github.danfickle.jbootstrap.jbootstraplplusf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
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

				JBootstrapTextField txt = JBootstrapFactory.createTextField("placeholder", JBootstrapTextFieldType.DEFAULT);
				txt.setText("This is a test");
				comp.add(txt);
				
				txt = JBootstrapFactory.createTextField("placeholder", JBootstrapTextFieldType.DANGER);
				comp.add(txt);

				txt = JBootstrapFactory.createTextField("placeholder", JBootstrapTextFieldType.WARNING);
				comp.add(txt);

				txt = JBootstrapFactory.createTextField("placeholder", JBootstrapTextFieldType.INFO);
				comp.add(txt);

				txt = JBootstrapFactory.createTextField("placeholder", JBootstrapTextFieldType.SUCCESS);
				txt.setSearch(true);
				comp.add(txt);

				JTextField txt2 = new JTextField();
				txt2.setText("test text");
				comp.add(txt2);
				
				JFrame frame = new JFrame("Test");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(comp);
				frame.pack();
				frame.setVisible(true);
			}
		} );
	}
}
