package main;

import javax.swing.JFrame;

public class POS_Frame extends JFrame {
	public POS_Frame() {
		setTitle("D cafe 키오스크");			// 프레임 제목
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// 프레임 종료
		
		setContentPane(new POS_Panel());
		
		setSize(800, 1000);		// 프레임 사이즈
		setVisible(true);		// 프레임을 보여줘라.
		setResizable(false);	// 프레임 사이즈 건들지마.
	}
}
