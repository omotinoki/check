package sample;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Main extends JFrame implements KeyListener {
	int x= 200,y= 100,wide=1400,hei=770;
	int speed=5;
	boolean flag_W=false,flag_A=false,flag_S=false,flag_D=false,flag_end;
	Image img,buffer;
	//BufferedImage img;
	
	public static void main(String[] args) {
		Main main = new Main();
		while(true) {
			main.repaint();
		}
	}
	
	public Main(){
		super("Move"); //title
		Dimension d = getSize();
		flag_end = true;
		addKeyListener(this);  //キー入力を見るやつ
		setBackground(Color.GRAY); //背景
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(wide, hei); //画面サイズ
		setLocationRelativeTo(null); //画面の表示位置nullは中央
		setResizable(false); //画面サイズ変更ture:可,false:不可
		img = Toolkit.getDefaultToolkit().getImage("src/img/wood.png");
		buffer = createImage(d.width,d.height);
		
		/*
		try (InputStream inputStream = getClass().getResourceAsStream("img/dog.png");) {
			img = ImageIO.read(inputStream);
		} catch (IOException e) {
			System.out.println("画像の読み込みに失敗");
			e.printStackTrace();
		}
		*/
		
		setVisible(true); //画面を表示する
	}
	
	public void paint(Graphics g){
		move();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, wide, hei);
		
		g.drawImage(img, x, y, this);
		
		try{
			Thread.sleep(10);
		} catch(InterruptedException e) {
			
		}
		g.drawImage(buffer,0,0,this);
	}
	
	public void keyPressed(KeyEvent e){ //キーが押されたとき
		if(flag_end == true) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_W: flag_W=true; break;
		case KeyEvent.VK_D: flag_D=true; break;
		case KeyEvent.VK_S: flag_S=true; break;
		case KeyEvent.VK_A: flag_A=true; break;
		case KeyEvent.VK_ENTER: flag_end=false; break;
		}
	}
	}
	public void keyReleased(KeyEvent e) { //キーが離されたとき
		if(flag_end == true) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W: flag_W=false; break;
			case KeyEvent.VK_D: flag_D=false; break;
			case KeyEvent.VK_S: flag_S=false; break;
			case KeyEvent.VK_A: flag_A=false; break;
		}
		}
	}
	public void keyTyped(KeyEvent e) { //キーが文字を入力したとき
		
	}
	
	
	public void move() {
		if(y>=30) {
			if(flag_W) {
				y-=speed;
			}
		}
		if(x+20<wide) {
			if(flag_D) {
				x+=speed;
			}
		}
		if(x>=0) {
			if(flag_A) {
				x-=speed;
			}
		}
		if(y+20<hei) {
			if(flag_S) {
				y+=speed;
			}
		}
	}
}
