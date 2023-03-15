package main;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

public class UI {
	GamePanel gp;
	int screenWidth = 605;
	int screenHeight = 620;
	int commandNum = 0;
	
	Action upAction;
	Action downAction;
	Action enterAction;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		upAction = new UpAction();
		downAction = new DownAction();
		enterAction = new EnterAction();
		
		gp.getInputMap().put(KeyStroke.getKeyStroke("released UP"), "upAction");
        gp.getInputMap().put(KeyStroke.getKeyStroke("released W"), "wAction");
        
        
	}
	
	void setMenuActionMap() {
		gp.getActionMap().put("upAction", upAction);
        gp.getActionMap().put("wAction", upAction);
        
        gp.getActionMap().put("downAction", downAction);
        gp.getActionMap().put("sAction", downAction);
        
        gp.getActionMap().put("enterAction", enterAction);
	}
	
	void drawTitleScreen(Graphics g2) {
		//commandNum = 0;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 88F));
		String text = "Connect Four";
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = screenWidth/2 - length/2;
		int y = 100;
		
		//shadow
		g2.setColor(Color.black);
		g2.drawString(text, x+4, y+4);
		//main text
		g2.setColor(Color.blue);
		g2.drawString(text, x, y);
		
		//menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		
		text = "PLAYER VS PLAYER";
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = screenWidth/2 - length/2;
		y = 300;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.fillOval(x - 25, y - (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight()/2, 20, 20);
		}
		
		text = "PLAYER VS COMPUTER (W.I.P.)";
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = screenWidth/2 - length/2;
		y = 380;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.fillOval(x - 25, y - (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight()/2, 20, 20);
		}
		
		text = "EXIT";
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = screenWidth/2 - length/2;
		y = 460;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.fillOval(x - 25, y - (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight()/2, 20, 20);
		}
	}
	
	void drawPlayScreen(Graphics2D g2) {
		//board draw
        g2.setPaint(Color.BLUE);
        g2.fillRect(0,gp.boardY,screenWidth-1,gp.boardHeight);
        gp.board.drawBoard(g2, gp.boardY);
        //cursor draw
        gp.cursor.draw(g2);
	}
	
	void drawRetryScreen(Graphics2D g2) {
		//commandNum = 0;
		drawPlayScreen(g2);
		Color winColor = Color.blue;
		String text = "Draw!";
		switch (gp.win) {
		case 1:
			text = "PLAYER 1 HAS WON!";
			winColor = Color.red;
			break;
		case 2:
			text = "PLAYER 2 HAS WON";
			winColor = Color.yellow;
			break;
		}
		g2.setColor(winColor);
		g2.fillRect(50, 50, screenWidth - 100, screenHeight - 100);
		g2.setColor(Color.white);
		g2.fillRect(60, 60, screenWidth - 120, screenHeight - 120);
		
		g2.setColor(winColor);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = screenWidth/2 - length/2;
		int y = 125;
		g2.drawString(text, x, y);
		
		g2.setColor(Color.blue);
		
		text = "RETRY";
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = screenWidth/2 - length/2;
		y = 300;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.fillOval(x - 25, y - (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight()/2, 20, 20);
		}
		
		text = "MAIN MENU";
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = screenWidth/2 - length/2;
		y = 375;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.fillOval(x - 25, y - (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight()/2, 20, 20);
		}
		
		text = "EXIT";
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = screenWidth/2 - length/2;
		y = 450;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.fillOval(x - 25, y - (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight()/2, 20, 20);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public class UpAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(commandNum - 1 >= 0) {
				commandNum -= 1;
			}
			System.out.println("input Recognized in menu");
		}
    }
	
	public class DownAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(commandNum + 1 <= 2) {
				commandNum += 1;
			}
			System.out.println("input recognized in menu");
		}
    }
	
	public class EnterAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(gp.gameState == gp.titleState) {
				switch(commandNum) {
					case 0:
						gp.board = new Board();
						gp.cursor = new Cursor(gp, gp.board);
						gp.gameState = gp.playState;
						break;
					case 2:
						System.exit(0);
						break;
				}
			}
			if(gp.gameState == gp.retryMenuState) {
				switch(commandNum) {
				case 0:
					gp.board = new Board();
					gp.cursor = new Cursor(gp, gp.board);
					gp.gameState = gp.playState;
					break;
				case 1:
					commandNum = 0;
					gp.board = new Board();
					gp.cursor = new Cursor(gp, gp.board);
					gp.gameState = gp.titleState;
					break;
				case 2:
					System.exit(0);
				}
			}
			
			System.out.println("input recognized in menu");
		}
    }
}
