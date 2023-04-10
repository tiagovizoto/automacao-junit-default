// created by Rafael Tulio
package br.com.quality.robots.key;
import java.awt.event.KeyEvent;

public enum RnKeys {
	ENTER('\uE007', KeyEvent.VK_ENTER, "{ENTER}");


	private char keyChar;
	private int keyCode;
	private String keyAutoIt;

	RnKeys(char keyChar, int keyCode, String keyAutoIt) {
		this.keyChar = keyChar;
		this.keyCode = keyCode;
		this.keyAutoIt = keyAutoIt;
	}

	public char getKeyChar() {
		return keyChar;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public String getKeyAutoIt() {
		return keyAutoIt;
	}
}
