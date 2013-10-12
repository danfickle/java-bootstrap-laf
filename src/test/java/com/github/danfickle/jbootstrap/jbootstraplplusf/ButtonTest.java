package com.github.danfickle.jbootstrap.jbootstraplplusf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ButtonTest
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
				
				JButton btn = new JButton("btn");
				btn.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "btn");
				comp.add(btn);

				btn = new JButton("btn btn-primary");
				btn.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "btn btn-primary");
				comp.add(btn);

				btn = new JButton("btn btn-warning");
				btn.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "btn btn-warning");
				comp.add(btn);

				btn = new JButton("btn btn-danger");
				btn.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "btn btn-danger");
				comp.add(btn);

				btn = new JButton("btn btn-info");
				btn.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "btn btn-info");
				comp.add(btn);

				btn = new JButton("btn btn-inverse");
				btn.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "btn btn-inverse");
				comp.add(btn);

				btn = new JButton("btn btn-success");
				btn.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "btn btn-success");
				comp.add(btn);

				btn = new JButton("btn btn-primary btn-mini");
				btn.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "btn btn-primary btn-mini");
				comp.add(btn);
				
				btn = new JButton("btn btn-primary btn-small");
				btn.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "btn btn-primary btn-small");
				comp.add(btn);
				
				btn = new JButton("btn btn-primary btn-default (disabled)");
				btn.setEnabled(false);
				btn.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "btn btn-primary btn-default");
				comp.add(btn);

				btn = new JButton("btn btn-primary btn-large");
				btn.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "btn btn-primary btn-large");
				comp.add(btn);
				
				btn = new JButton("btn btn-success btn-large (with icon)");
				btn.setIcon(new ImageIcon(getClass().getResource("/dialog-information.png")));
				btn.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "btn btn-success btn-large");

				btn.getModel().addActionListener(new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						System.out.println("button works");
					}
				});
				
				comp.add(btn);

				btn = new JButton("plain button");
				comp.add(btn);
				
				JFrame frame = new JFrame("Test");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(comp);
				frame.pack();
				frame.setVisible(true);
			}
		} );
	}
}

