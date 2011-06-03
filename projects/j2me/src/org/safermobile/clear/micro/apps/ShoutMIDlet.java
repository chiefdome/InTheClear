/* Copyright (c) 2011, SaferMobile/MobileActive - https://safermobile.org */
/* See LICENSE for licensing information */

package org.safermobile.clear.micro.apps;
import java.io.IOException;
import java.util.Date;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStoreException;

import org.safermobile.clear.micro.L10nConstants;
import org.safermobile.clear.micro.L10nResources;
import org.safermobile.clear.micro.data.PhoneInfo;
import org.safermobile.clear.micro.sms.SMSManager;
import org.safermobile.clear.micro.ui.LargeStringCanvas;
import org.safermobile.micro.ui.DisplayManager;
import org.safermobile.micro.utils.Logger;
import org.safermobile.micro.utils.Preferences;
import org.safermobile.micro.utils.StringTokenizer;

// hasLocationCapability=false
//release.build = false
public class ShoutMIDlet extends MIDlet implements CommandListener, Runnable {

	
	private DisplayManager _manager;
	private Display _display;

	private TextBox _tbMain;
	private LargeStringCanvas _lsCanvas;
	
	private Command	 _cmdSend;	
	private Command	 _cmdExit;
	
	//private SMSManager _smsServer;
	//private int SMS_SERVER_PORT = 0;
	
	
	/*
	 * the thread which manages the panic sending
	 */
	private Thread _thread;
	
	/*
	 * used to cancel the panic loop
	 */
	private boolean _keepPanicing = false; 
	
	/*
	 * stores the user data between the config app and this one
	 */
	private Preferences _prefs = null; 
	
	/*
	 * localized resources
	 */
	L10nResources l10n = L10nResources.getL10nResources("en-US");
	
	/**
	 * Creates Panic Activate app
	 */
	public ShoutMIDlet() {
		
		_display = Display.getDisplay(this);
		_manager = new DisplayManager(_display);
		
		_cmdSend = new Command("Shout!", Command.OK,0);
		_cmdExit = new Command("Exit", Command.EXIT,1);
		
		_tbMain = new TextBox(l10n.getString(L10nConstants.keys.KEY_PANIC_TITLE_MAIN), "", 500, TextField.ANY);
		
		_tbMain.setCommandListener(this);
		
		_tbMain.addCommand(_cmdSend);
		_tbMain.addCommand(_cmdExit);

	}

	

	
	/* (non-Javadoc)
	 * @see javax.microedition.midlet.MIDlet#startApp()
	 */
	protected void startApp() throws MIDletStateChangeException {
		
		showAlert("Shout!","Enter the message you want to shout to your configured friends.",_tbMain);

	
	}
	
	public void showAlert (String title, String msg, Displayable next)
	{
		Alert alert = new Alert(title);
		alert.setString(msg);
        _manager.next(alert, next);
        
	}
	
	/* (non-Javadoc)
	 * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command, javax.microedition.lcdui.Displayable)
	 */
	public void commandAction(Command command, Displayable displayable) {
		
		
	}
	
	/* (non-Javadoc)
	 * @see javax.microedition.midlet.MIDlet#destroyApp(boolean)
	 */
	protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
		
		
		
	}

	/* (non-Javadoc)
	 * @see javax.microedition.midlet.MIDlet#pauseApp()
	 */
	protected void pauseApp() {}
	
	
	private void doSecPause (int secs)
	{
		try { Thread.sleep(secs * 1000);}
		catch(Exception e){}
	}
	
	public void run ()
	{
		
		
	}
	
	
	
	private void showMessage (String msg)
	{
		Logger.debug(PanicConstants.TAG, "msg: " + msg);

		if (_display.getCurrent() == _tbMain)
		{
			try
			{
				_tbMain.setString(msg);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else if (_display.getCurrent() == _lsCanvas)
		{
			_lsCanvas.setLargeString(msg);
		}
	}
	
	
	
	
}
