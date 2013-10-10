package com.github.danfickle.jbootstrap.jbootstraplplusf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class LabelTest
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

				JLabel lbl = new JLabel("label");
				lbl.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "label");
				comp.add(lbl);

				lbl = new JLabel("label label-info");
				lbl.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "label label-info");
				comp.add(lbl);

				lbl = new JLabel("label label-success");
				lbl.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "label label-success");
				comp.add(lbl);
				
				lbl = new JLabel("label label-warning");
				lbl.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "label label-warning");
				comp.add(lbl);
				
				lbl = new JLabel("label label-important");
				lbl.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "label label-important");
				comp.add(lbl);
				
				lbl = new JLabel("label label-inverse");
				lbl.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "label label-inverse");
				comp.add(lbl);
				
				lbl = new JLabel("badge badge-info");
				lbl.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "badge badge-info");
				comp.add(lbl);

				lbl = new JLabel("badge badge-success");
				lbl.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "badge badge-success");
				comp.add(lbl);
				
				lbl = new JLabel("badge badge-warning");
				lbl.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "badge badge-warning");
				comp.add(lbl);
				
				lbl = new JLabel("badge badge-inverse");
				lbl.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "badge badge-inverse");
				comp.add(lbl);
				
				lbl = new JLabel("badge badge-important");
				lbl.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "badge badge-important");
				comp.add(lbl);
				
				lbl = new JLabel("badge");
				lbl.putClientProperty(JBootstrapLF.JBOOTSTRAP_CLASS, "badge");
				comp.add(lbl);
				
				JFrame frame = new JFrame("Test");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//SwingUtilities.updateComponentTreeUI(frame);
				frame.getContentPane().add(comp);
				frame.pack();
				frame.setVisible(true);
			}
		} );
	}
}

