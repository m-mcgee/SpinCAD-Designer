/* SpinCAD Designer - DSP Development Tool for the Spin FV-1 
 * Mixer_2_to_1ControlPanel.java
 * Copyright (C) 2015 - Gary Worsham 
 * Based on ElmGen by Andrew Kilpatrick 
 * 
 *   This program is free software: you can redistribute it and/or modify 
 *   it under the terms of the GNU General Public License as published by 
 *   the Free Software Foundation, either version 3 of the License, or 
 *   (at your option) any later version. 
 * 
 *   This program is distributed in the hope that it will be useful, 
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of 
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
 *   GNU General Public License for more details. 
 * 
 *   You should have received a copy of the GNU General Public License 
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>. 
 *     
 */ 
		package com.holycityaudio.SpinCAD.ControlPanel;
		import javax.swing.JFrame;
		import javax.swing.SwingUtilities;
		import javax.swing.event.ChangeEvent;
		import javax.swing.event.ChangeListener;
		import java.awt.event.ActionEvent;
		import java.awt.event.WindowEvent;
		import java.awt.event.WindowListener;
		import java.awt.event.ItemEvent;
		import javax.swing.BoxLayout;
		import javax.swing.JSlider;
		import javax.swing.JLabel;
		import javax.swing.JCheckBox;
		import javax.swing.JComboBox;
		import javax.swing.Box;
		import java.awt.Dimension;
		import com.holycityaudio.SpinCAD.spinCADControlPanel;
		import com.holycityaudio.SpinCAD.CADBlocks.Mixer_2_to_1CADBlock;

		public class Mixer_2_to_1ControlPanel extends spinCADControlPanel {
		private JFrame frame;

		private Mixer_2_to_1CADBlock gCB;
		// declare the controls
			JSlider gain1Slider;
			JLabel  gain1Label;	
			JSlider gain2Slider;
			JLabel  gain2Label;	

		public Mixer_2_to_1ControlPanel(Mixer_2_to_1CADBlock genericCADBlock) {
		
		gCB = genericCADBlock;

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				frame = new JFrame();
				frame.setTitle("Mixer_2_1");
				frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

			
			// dB level slider goes in steps of 1 dB
				gain1Slider = new JSlider(JSlider.HORIZONTAL, (int)(-24),(int) (0), (int) (20 * Math.log10(gCB.getgain1())));
				gain1Slider.addChangeListener(new Mixer_2_to_1SliderListener());
				gain1Label = new JLabel();
				updategain1Label();
				frame.add(Box.createRigidArea(new Dimension(5,4)));			
				frame.getContentPane().add(gain1Label);
				frame.add(Box.createRigidArea(new Dimension(5,4)));			
				frame.getContentPane().add(gain1Slider);		
			
			// dB level slider goes in steps of 1 dB
				gain2Slider = new JSlider(JSlider.HORIZONTAL, (int)(-24),(int) (0), (int) (20 * Math.log10(gCB.getgain2())));
				gain2Slider.addChangeListener(new Mixer_2_to_1SliderListener());
				gain2Label = new JLabel();
				updategain2Label();
				frame.add(Box.createRigidArea(new Dimension(5,4)));			
				frame.getContentPane().add(gain2Label);
				frame.add(Box.createRigidArea(new Dimension(5,4)));			
				frame.getContentPane().add(gain2Slider);		
				frame.addWindowListener(new MyWindowListener());
				frame.setVisible(true);		
				frame.pack();
				frame.setResizable(false);
				frame.setLocation(gCB.getX() + 100, gCB.getY() + 100);
				frame.setAlwaysOnTop(true);
			}
		});
		}

		// add change listener for Sliders 
		class Mixer_2_to_1SliderListener implements ChangeListener { 
		public void stateChanged(ChangeEvent ce) {
			if(ce.getSource() == gain1Slider) {
			gCB.setgain1((double) (gain1Slider.getValue()/1.0));
				updategain1Label();
			}
			if(ce.getSource() == gain2Slider) {
			gCB.setgain2((double) (gain2Slider.getValue()/1.0));
				updategain2Label();
			}
			}
		}

		// add item listener 
		class Mixer_2_to_1ItemListener implements java.awt.event.ItemListener { 
		public void stateChanged(ChangeEvent ce) {
			}
			
		@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
			}
		}
		
		// add action listener 
		class Mixer_2_to_1ActionListener implements java.awt.event.ActionListener { 
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		}
		private void updategain1Label() {
		gain1Label.setText("Input_Gain_1 " + String.format("%4.1f dB", (20 * Math.log10(gCB.getgain1()))));		
		}		
		private void updategain2Label() {
		gain2Label.setText("Input_Gain_2 " + String.format("%4.1f dB", (20 * Math.log10(gCB.getgain2()))));		
		}		
		
		class MyWindowListener implements WindowListener
		{
		@Override
			public void windowActivated(WindowEvent arg0) {
			}

		@Override
			public void windowClosed(WindowEvent arg0) {
			}

		@Override
			public void windowClosing(WindowEvent arg0) {
				gCB.clearCP();
			}

		@Override
			public void windowDeactivated(WindowEvent arg0) {
			}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
		}

		@Override
		public void windowIconified(WindowEvent arg0) {

		}

			@Override
			public void windowOpened(WindowEvent arg0) {
			}
		}
		
	}
