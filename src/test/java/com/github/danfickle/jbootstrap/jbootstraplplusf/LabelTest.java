package com.github.danfickle.jbootstrap.jbootstraplplusf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.github.danfickle.jbootstrap.jbootstraplplusf.JBootstrapFactory.JBootstrapBadgeType;
import com.github.danfickle.jbootstrap.jbootstraplplusf.JBootstrapFactory.JBootstrapLabelType;
import com.github.danfickle.jbootstrap.jbootstraplplusf.JBootstrapFactory.JBootstrapTextType;

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

				JLabel lbl = JBootstrapFactory.createLabel("label", JBootstrapLabelType.DEFAULT);
				comp.add(lbl);

				lbl = JBootstrapFactory.createLabel("label label-info", JBootstrapLabelType.INFO);
				comp.add(lbl);

				lbl = JBootstrapFactory.createLabel("label label-success", JBootstrapLabelType.SUCCESS);
				comp.add(lbl);
				
				lbl = JBootstrapFactory.createLabel("label label-warning", JBootstrapLabelType.WARNING);
				comp.add(lbl);
				
				lbl = JBootstrapFactory.createLabel("label label-important", JBootstrapLabelType.IMPORTANT);
				comp.add(lbl);
				
				lbl = JBootstrapFactory.createLabel("label label-inverse", JBootstrapLabelType.INVERSE);
				comp.add(lbl);
				
				lbl = JBootstrapFactory.createBadge("badge badge-info", JBootstrapBadgeType.INFO);
				comp.add(lbl);

				lbl = JBootstrapFactory.createBadge("badge badge-success", JBootstrapBadgeType.SUCCESS);
				comp.add(lbl);
				
				lbl = JBootstrapFactory.createBadge("badge badge-warning", JBootstrapBadgeType.WARNING);
				comp.add(lbl);
				
				lbl = JBootstrapFactory.createBadge("badge badge-inverse", JBootstrapBadgeType.INVERSE);
				comp.add(lbl);
				
				lbl = JBootstrapFactory.createBadge("badge badge-important", JBootstrapBadgeType.IMPORTANT);
				comp.add(lbl);
				
				lbl = JBootstrapFactory.createBadge("badge (with icon)", JBootstrapBadgeType.DEFAULT);
				lbl.setIcon(new ImageIcon(getClass().getResource("/dialog-information.png")));
				comp.add(lbl);

				lbl = new JLabel("plain label");
				comp.add(lbl);
				
				lbl = JBootstrapFactory.createText("default", JBootstrapTextType.DEFAULT);
				comp.add(lbl);
				
				lbl = JBootstrapFactory.createText("info", JBootstrapTextType.INFO);
				comp.add(lbl);
				
				lbl = JBootstrapFactory.createText("warning", JBootstrapTextType.WARNING);
				comp.add(lbl);
				
				lbl = JBootstrapFactory.createText("error", JBootstrapTextType.ERROR);
				comp.add(lbl);
				
				lbl = JBootstrapFactory.createWrappedText("This is some really long text " +
						"This is some really long text " +
						"This is some really long text " +
						"This is some really long text " +
						"This is some really long text " +
						"This is some really long text " +
						"This is some really long text " +
						"This is some really long text " +
						"This is some really long text ", JBootstrapTextType.SUCCESS, 450);
				comp.add(lbl);
				
				JFrame frame = new JFrame("Test");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(comp);
				frame.pack();
				frame.setVisible(true);
			}
		} );
	}
}
