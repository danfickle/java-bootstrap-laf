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

import com.github.danfickle.jbootstrap.jbootstraplplusf.JBootstrapFactory.JBootstrapButtonSize;
import com.github.danfickle.jbootstrap.jbootstraplplusf.JBootstrapFactory.JBootstrapButtonType;

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
				
				JButton btn = JBootstrapFactory.createButton("btn", JBootstrapButtonType.DEFAULT, JBootstrapButtonSize.DEFAULT);
				comp.add(btn);

				btn = JBootstrapFactory.createButton("btn btn-primary", JBootstrapButtonType.PRIMARY, JBootstrapButtonSize.DEFAULT);
				comp.add(btn);

				btn = JBootstrapFactory.createButton("btn btn-warning", JBootstrapButtonType.WARNING, JBootstrapButtonSize.DEFAULT);
				comp.add(btn);

				btn = JBootstrapFactory.createButton("btn btn-danger", JBootstrapButtonType.DANGER, JBootstrapButtonSize.DEFAULT);
				comp.add(btn);

				btn = JBootstrapFactory.createButton("btn btn-info", JBootstrapButtonType.INFO, JBootstrapButtonSize.DEFAULT);
				comp.add(btn);

				btn = JBootstrapFactory.createButton("btn btn-inverse", JBootstrapButtonType.INVERSE, JBootstrapButtonSize.DEFAULT);
				comp.add(btn);

				btn = JBootstrapFactory.createButton("btn btn-success", JBootstrapButtonType.SUCCESS, JBootstrapButtonSize.DEFAULT);
				comp.add(btn);

				btn = JBootstrapFactory.createButton("btn btn-primary btn-mini", JBootstrapButtonType.PRIMARY, JBootstrapButtonSize.MINI);
				comp.add(btn);
				
				btn = JBootstrapFactory.createButton("btn btn-primary btn-small", JBootstrapButtonType.PRIMARY, JBootstrapButtonSize.SMALL);
				comp.add(btn);
				
				btn = JBootstrapFactory.createButton("btn btn-primary btn-default (disabled)", JBootstrapButtonType.PRIMARY, JBootstrapButtonSize.DEFAULT);
				btn.setEnabled(false);
				comp.add(btn);

				btn = JBootstrapFactory.createButton("btn btn-primary btn-large", JBootstrapButtonType.PRIMARY, JBootstrapButtonSize.LARGE);
				comp.add(btn);
				
				btn = JBootstrapFactory.createButton("btn btn-success btn-large (with icon)", JBootstrapButtonType.SUCCESS, JBootstrapButtonSize.LARGE);
				btn.setIcon(new ImageIcon(getClass().getResource("/dialog-information.png")));

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
