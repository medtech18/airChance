package controller.common;

import javax.swing.JOptionPane;

public class AlertMessages {


	    public static void ErrorBox(String infoMessage, String titleBar)
	    {
	        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.ERROR_MESSAGE);

	    }
	}