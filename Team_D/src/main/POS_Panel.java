package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;







public class POS_Panel extends JPanel {
	
	JButton[] Button = new JButton[6];
	String[] menu = {"HOT 아메리카노", "ICE 아메리카노", "HOT 카페라떼", "ICE 카페라떼", "HOT 카라멜 마끼야또", "ICE 카라멜 마끼야또"}; 
	int [] price = {1500,1500,2000,2000,25000,2500};
	JButton[] Home = new JButton[1];
	JButton[] Obtn = new JButton[2];
	String[] otr = {"전체취소", "결제하기"};
	String[] ColName = {"제품명","수량","가격"};
	int count = 1;
	String [][] Data ;
	DefaultTableModel model = new DefaultTableModel(Data,ColName);
	JTable table = new JTable(model);
	JTextField tf = new JTextField(30);			
	
	
	
	class Menubtn extends JPanel{
		Menubtn(){
			setLayout(new GridLayout(3,2,10,10));
			setBackground(Color.white);
			for (int i = 0; i < Button.length; i++) {
				Button[i] = new JButton(menu[i]);
				add(Button[i]);
				
			}
		}
	}
		
	class Optionbtn extends JPanel{
		Optionbtn(){
			setLayout(new GridLayout(2,1,10,10));
			setBackground(Color.white);
			for (int i = 0; i < Obtn.length; i++) {
				Obtn[i] = new JButton(otr[i]);
				add(Obtn[i]);
				
			}
			
		}
	}
		
	class Screen extends JPanel{
		Screen(){
			setBackground(Color.white);
			DefaultTableModel m = (DefaultTableModel)table.getModel();
			table.setRowHeight(50);
			table.getTableHeader().setFont(new Font("맑은고딕", Font.BOLD,15));
			add(new JScrollPane(table));
		}
	}
    
	public POS_Panel() {
		// home 버튼
		setLayout(null);
		setBackground(Color.white);
		Menubtn mbtn = new Menubtn();
		Optionbtn obtn = new Optionbtn();
		Screen sc = new Screen();
		
		// 결제 금액 
		tf.setSize(450, 70);
		tf.setLocation(160,880);
		add(tf);
		
		
		
		// 메뉴버튼 커스텀
		mbtn.setSize(500, 500);
		mbtn.setLocation(140, 60);
		add(mbtn);
		
		// 옵션 버튼 커스텀
		obtn.setSize(100, 150);
		obtn.setLocation(650, 605);
		add(obtn);
		
		// 메뉴 주문내역 창
		sc.setSize(600, 300);
		sc.setLocation(90, 570);
		add(sc);
		
		for(int i=0;i<Button.length;i++) {
			final int index =i;
			Button[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton Button = (JButton)e.getSource();
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.addRow(new Object[] {menu[index],count,price[index]});
					
				}
			}); 
		
		
		
		}
		
		//전체취소
				Obtn[0].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton Button = (JButton)e.getSource();
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						
						m.setRowCount(0);
						tf.setText(String.valueOf(""));
					}
				});
		
		//결제버튼
				Obtn[1].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton Button = (JButton)e.getSource();
						int rowCont = table.getRowCount();
						int sum =0;
						for(int i=0;i<rowCont;i++) {
							sum += (int)table.getValueAt(i, 2);
						}
						tf.setText(String.valueOf(" 총 금액 : "+sum));
						tf.setFont(new Font("맑은고딕", Font.BOLD, 40));
					}
				});	
				
		}
    		
}
