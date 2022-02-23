package SubFrame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dao.CustomDao;
import dao.OrderDao;
import dao.ProductDao;
import db.vo.Custom;
import db.vo.Order;
import db.vo.Product;


public class Test {
	private JFrame frame;
	private JFrame countF;
	private JFrame cart;
	
	   String[] menu = {"아메리카노","카페 라떼","돌체 라떼","바닐라 라떼","녹차 케익" };			// 메뉴 배열
	   String[] change = {"아이스로 변경","사이즈업"};										// 추가항목 명칭 배열
	   int[] cost = {2500,3000,3500,4000,4500};										// 가격 배열
	   
	   String store;																// 매장,포장 선택시 받을 대입될 변수
	   int cnt;													//고객번호 저장
	   int[] side_cost = {500,1000};												// 추가항목 가격 배열
	   int total_price = 0;															// 총 가격 합계
	  															
	   	
	   String btnString1 = "";														// menu[0]에 대한 문자열 합계를 담는 변수
	   String btnString2 = "";														// menu[1]에 대한 문자열 합계를 담는 변수
	   String btnString3 = "";														// menu[2]에 대한 문자열 합계를 담는 변수
	   String btnString4 = "";														// menu[3]에 대한 문자열 합계를 담는 변수
	   String btnString5 = "";														// menu[4]에 대한 문자열 합계를 담는 변수
	   
	   
	   JLabel lbltotalP = new JLabel("원");									// 총 합계를 보여주는 Label
	   
	   
	   
	public Test() {
	      initialize();
	}
	
	private void initialize() {
		CustomDao cd = CustomDao.getCustomDao();		
		cnt=cd.selectNum();								//고객번호 불러오기(껐다가 실행했을 때), 없을 경우 0 가져옴
		ProductDao pd = ProductDao.getProductDao();
		if(pd.selectAll().size()==0) {					//Product 테이블에 값이 없을 경우 배열의 값을 insert
		for(int i=0;i<menu.length;i++)
		pd.insert(new Product(menu[i],cost[i]));
		}
		
		frame = new JFrame();									//기본 Frame 설정 시작
		frame.setSize(650,1000);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().setLayout(null);
	    frame.setResizable(false);
	    frame.setLocationRelativeTo(null);
	    frame.setTitle("D Coffee");								//기본 Frame 설정 끝

	    countF = new JFrame();									//결제 Frame 설정 시작
	    countF.setSize(300,140);
	    countF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    countF.getContentPane().setLayout(null);
	    countF.setResizable(false);
	    countF.setLocationRelativeTo(null);
	    countF.setVisible(false);
	    countF.setTitle("D Coffee");							// 결제 Frame 설정 끝
	    
	    JPanel panel_yn = new JPanel();							// 결제 Frame에 panel_yn 추가 시작
	    panel_yn.setBounds(0,0,300,140);
	    panel_yn.setLayout(null);
	    countF.add(panel_yn);									// 결제 Frame에 panel_yn 추가 시작
	    
	    JButton btn_yes = new JButton("예");						// 예 버튼 설정 시작
	    btn_yes.setBounds(165,60,100,30);						// 예 버튼 설정 끝
	    panel_yn.add(btn_yes);									// panel_yn에 btn_yes 담기
	    
	    JButton btn_no = new JButton("아니요");					// 아니요 버튼 설정 시작
	    btn_no.setBounds(20,60,100,30);							// 아니요 버튼 설정 끝
	    panel_yn.add(btn_no);									// panel_yn에 btn_no 담기
	    
	    JLabel lab_ask = new JLabel("주문 하시겠습니까?");			// 구매확정 시 머릿말 설정 시작
	    lab_ask.setBounds(0,0,280,60);
	    lab_ask.setHorizontalAlignment(SwingConstants.CENTER);	
	    lab_ask.setFont(new Font("바탕", Font.ITALIC, 25));	 	// 구매확정 시 머릿말 설정 끝 
	    
	    panel_yn.add(lab_ask);									//panel_yn에 lab_ask 담기
	    
	    JPanel panel_main = new JPanel();						// 홈화면 Panel 설정 시작
	    panel_main.setBounds(0,0,650,1000);
	    frame.add(panel_main);
	    panel_main.setLayout(null);
	    panel_main.setVisible(true);							// 홈화면 Panel 설정 끝
	    
	    JPanel panel_order = new JPanel();						// 주문화면 Panel 설정 시작
	    panel_order.setBounds(0,0,650,1000);
	    frame.add(panel_order);
	    panel_order.setLayout(null);
	    panel_order.setVisible(false);							// 주문화면 Panel 설정 끝
	    
	    JPanel panel_cart = new JPanel();						// 장바구니 panel 설정 시작
	    panel_cart.setBounds(0, 0, 650, 1000);
	    frame.add(panel_cart);
	    panel_cart.setLayout(null);
	    panel_cart.setVisible(false);							// 장바구니 panel 설정 끝
	    
	    JButton btnconfirm = new JButton("결제하기");				// 결제하기 버튼 설정 시작
	    btnconfirm.setBounds(500, 900, 97, 35);
	    panel_cart.add(btnconfirm);								// 결제하기 버튼 설정 끝
	    
	    lbltotalP.setFont(new Font("굴림", Font.BOLD, 30));				// 장바구니에 총 합계 Font 설정 시작
	    lbltotalP.setHorizontalAlignment(SwingConstants.RIGHT);
	    lbltotalP.setBounds(435, 830, 142, 37);
	    panel_cart.add(lbltotalP);										// 장바구니에 총 합계 Font 설정 시작
	      
//	    JPanel panel_change = new JPanel();								// 추가사항 panel 설정 시작
//	    panel_change.setBackground(Color.white);
//	    panel_change.setBounds(0, 0, 474, 477);
//	    frame.getContentPane().add(panel_change);
//	    panel_change.setLayout(null);
//	    panel_change.setVisible(false);									// 추가사항 panel 설정 끝
	    
	    JTextArea textArea = new JTextArea(TextArea.SCROLLBARS_VERTICAL_ONLY, 0);		// 장바구니 관련 설정 시작
	    textArea.setBounds(0, 0, 650, 790);
	    textArea.setEditable(false);
	    textArea.setText("제품명\t\t가격\t\t수량\n\n");
	    textArea.setFont(new Font("기본", Font.ITALIC, 18));
	    panel_cart.add(textArea);														// 장바구니 관련 설정 시작
	      

	      
	    JButton btn_eat = new JButton("매 장");				 	 // 홈화면 매장버튼 설정 시작
	    btn_eat.setBounds(18,90,600,320);
	    btn_eat.setFont(new Font("바탕", Font.ITALIC, 150));   	// 홈화면 매장버튼 설정 끝	
	    panel_main.add(btn_eat);									
	    JButton btn_wrap = new JButton("포 장"); 			      	// 홈화면 포장버튼 설정 시작
	    btn_wrap.setBounds(18,535,600,320);
	    btn_wrap.setFont(new Font("바탕", Font.ITALIC, 150));	 	 // 홈화면 포장버튼 설정 끝
	    panel_main.add(btn_wrap);					
	    
	    btn_eat.addActionListener(new ActionListener() {		// 매장버튼 클릭시 이벤트 시작
			public void actionPerformed(ActionEvent e) {
				store = "매 장";
				panel_order.setVisible(true);
				panel_main.setVisible(false);
				CustomDao cd = CustomDao.getCustomDao();
				if(cnt==0)										//고객 번호 없을 시 생성,매장
					cd.insert(new Custom(++cnt,store));			//	있을 시 변경,매장
				else cd.update(new Custom(cnt,store));
			}	
	    });														// 매장버튼 클릭시 이벤트 끝
	    
	    btn_wrap.addActionListener(new ActionListener() {		// 포장버튼 클릭시 이벤트 시작
	    	public void actionPerformed(ActionEvent e) {
	    		store = "포 장";
	    		panel_order.setVisible(true);
	    		panel_main.setVisible(false);
	    		CustomDao cd = CustomDao.getCustomDao();
	    		if(cnt==0)										//고객 번호 없을 시 생성,매장
					cd.insert(new Custom(++cnt,store));			//	있을 시 변경,매장
				else cd.update(new Custom(cnt,store));
	    	}
	    });														// 포장버튼 클릭시 이벤트 끝
	    
	    JLabel hlb = new JLabel("D Coffee");								// 머릿글 설정 시작
//	    hlb.setHorizontalAlignment(SwingConstants.CENTER);
	    hlb.setBounds(210, 23, 500, 120);
	    hlb.setFont(hlb.getFont().deriveFont(55.0f));						// 머릿글 설정 끝
	    panel_order.add(hlb);
	    
	    
	    JButton btn_hot = new JButton(new ImageIcon("./image./coffee/11.jpg"));		// 아메리카노 이미지 설정 시작 
	    btn_hot.setBounds(30,170,240,200);
	    panel_order.add(btn_hot);													// 아메리카노 이미지 설정 끝	
	    
	    JButton btn_latte = new JButton(new ImageIcon("./image./coffee/22.jpg"));	// 카페 라떼 이미지 설정 시작
	    btn_latte.setBounds(365,170,240,200);
	    panel_order.add(btn_latte);													// 카페 라떼 이미지 설정 끝
	    
	    JButton btn_dolce = new JButton(new ImageIcon("./image./coffee/33.jpg"));	// 돌체 라떼 이미지 설정 시작
	    btn_dolce.setBounds(30,435,240,200);
	    panel_order.add(btn_dolce);													// 돌체 라떼 이미지 설정 끝
	    
	    JButton btn_Vanilla = new JButton(new ImageIcon("./image./coffee/44.jpg"));	// 바닐라 라떼 이미지 설정 시작
	    btn_Vanilla.setBounds(365,435,240,200);	
	    panel_order.add(btn_Vanilla);												// 바닐라 라떼 이미지 설정 끝
	    
	    JButton btn_cake = new JButton(new ImageIcon("./image./coffee/55.jpg"));	// 녹차 케익 이미지 설정 시작
	    btn_cake.setBounds(30,700,240,200);	
	    panel_order.add(btn_cake);													// 녹차 케익 이미지 설정 끝
	    
	    JLabel hot = new JLabel();													// 아메리카노 글자 설정 시작
	    hot.setText(String.valueOf(menu[0] + "  " + cost[0] + "원") );
	    hot.setHorizontalAlignment(SwingConstants.CENTER);
	    hot.setBounds(47,286,200,200);
	    hot.setFont(hlb.getFont().deriveFont(20.0f));					 				
	    panel_order.add(hot);											 			// 아메리카노 글자 설정 끝
	    
	    JLabel latte = new JLabel();												// 카페 라떼 아메리카노 글자 설정 시작
	    latte.setText(String.valueOf(menu[1] + "  " + cost[1] + "원") );
	    latte.setHorizontalAlignment(SwingConstants.CENTER);
	    latte.setBounds(311,286,350,200);
	    latte.setFont(hlb.getFont().deriveFont(20.0f));					 				
	    panel_order.add(latte);														// 카페 라떼 글자 설정 끝	
	    		
	    JLabel dolce = new JLabel();												// 돌체 라떼 글자 설정 시작
	    dolce.setText(String.valueOf(menu[2] + "  " + cost[2] + "원") );
	    dolce.setHorizontalAlignment(SwingConstants.CENTER);
	    dolce.setBounds(52,550,200,200);
	    dolce.setFont(hlb.getFont().deriveFont(20.0f));
	    panel_order.add(dolce);														// 돌체 라떼 글자 설정 끝	
	    
	    JLabel Vanilla = new JLabel();												// 바닐라 라떼 글자 설정 시작
	    Vanilla.setText(String.valueOf(menu[3] + "  " + cost[3] + "원") );
	    Vanilla.setHorizontalAlignment(SwingConstants.CENTER);
	    Vanilla.setBounds(335,550,280,200);
	    Vanilla.setFont(hlb.getFont().deriveFont(20.0f));					 				
	    panel_order.add(Vanilla);													// 바닐라 라떼 글자 설정 끝
	    
	    JLabel cake = new JLabel();													// 녹차 케익 글자 설정 시작
	    cake.setText(String.valueOf(menu[4] + "  " + cost[4] + "원") );
	    cake.setHorizontalAlignment(SwingConstants.CENTER);
	    cake.setBounds(15,815,280,200);
	    cake.setFont(hlb.getFont().deriveFont(20.0f));					 				
	    panel_order.add(cake);														// 녹차 케익 글자 설정 끝
	    
	    JSpinner spinner1 = new JSpinner();								//menu에 spinner1 설정 시작
	      spinner1.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
	      spinner1.setBounds(242, 370, 44, 29);
	      spinner1.addChangeListener(new ChangeListener() {				//menu에 spinner1 설정 끝
	         
	         @Override
	         public void stateChanged(ChangeEvent e) {            		//menu에 spinner1 클릭시 이벤트 발생 시작
	            if((int)spinner1.getValue()>0) {
	            	btnString1 = menu[0]+"\t\t"+textLavel(cost[0])+"\t\t"+(int)spinner1.getValue()+"\n\n";  
	            } else {
	            	btnString1 = "";
	            }
	            
	         }
	      });															//menu에 spinner1 클릭시 이벤트 발생 끝
	      
	      panel_order.add(spinner1);										//panel_order에 spinner1 추가
	      
	      JSpinner spinner2 = new JSpinner();								//menu에 spinner2 설정 시작
	      spinner2.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
	      spinner2.setBounds(578, 370, 44, 29);
	      spinner2.addChangeListener(new ChangeListener() {					//menu에 spinner2설정 끝
	         
	         @Override
	         public void stateChanged(ChangeEvent e) {					//menu에 spinner2 클릭시 이벤트 발생 시작
	            if((int)spinner2.getValue()>0) {
	            	btnString2 = menu[1]+"\t\t"+textLavel(cost[1])+"\t\t"+(int)spinner2.getValue()+"\n\n";
	            } else {
	            	btnString2 = "";
	            }
	         }
	      });															//menu에 spinner2클릭시 이벤트 발생 끝
	     
	      panel_order.add(spinner2);									//panel_order에 spinner2 추가
	      
	      JSpinner spinner3 = new JSpinner();							//menu에 spinner3 설정 시작
	      spinner3.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
	      spinner3.setBounds(242, 635, 44, 29);	
	      spinner3.addChangeListener(new ChangeListener() {				//menu에 spinner3 설정 끝
	         
	         @Override	
	         public void stateChanged(ChangeEvent e) {					//menu에 spinner3 클릭시 이벤트 발생 시작
	            if((int)spinner3.getValue()>0) {
	            	btnString3 = menu[2]+"\t\t"+textLavel(cost[2])+"\t\t"+(int)spinner3.getValue()+"\n\n";
	            } else {
	            	btnString3 = "";
	            }
	            
	         }
	      });															//menu에 spinner3 클릭시 이벤트 발생 끝
	      
	      panel_order.add(spinner3);										//panel_order에 spinner3 추가
	      
	      JSpinner spinner4 = new JSpinner();							//menu에 spinner4 설정 시작
	      spinner4.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
	      spinner4.setBounds(578, 635, 44, 29);
	      spinner4.addChangeListener(new ChangeListener() {				//menu에 spinner4 설정 끝
	         
	         @Override
	         public void stateChanged(ChangeEvent e) {						//menu에 spinner4 클릭시 이벤트 발생 시작
	            if((int)spinner4.getValue()>0) {
	            	btnString4 = menu[3]+"\t\t"+textLavel(cost[3])+"\t\t"+(int)spinner4.getValue()+"\n\n";
	            } else {
	            	btnString4 = "";
	            }
	         }
	      });																//menu에 spinner4 클릭시 이벤트 발생 끝
	      
	      panel_order.add(spinner4);										//panel_order에 spinner4 추가
	      
	      JSpinner spinner5 = new JSpinner();								//menu에 spinner5 설정 시작
	      spinner5.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
	      spinner5.setBounds(242, 900, 44, 29);								//menu에 spinner5 설정 끝				
	      	
	      spinner5.addChangeListener(new ChangeListener() {					//menu에 spinner5 클릭시 이벤트 발생 시작
	         @Override
	         public void stateChanged(ChangeEvent e) {
	            if((int)spinner5.getValue()>0) {
	            	btnString5 = menu[4]+"\t\t"+textLavel(cost[4])+"\t\t"+(int)spinner5.getValue()+"\n\n";
	            } else {
	            	btnString5 = "";
	         }
	        }
	      });																//menu에 spinner5 클릭시 이벤트 발생 끝
	      
	      panel_order.add(spinner5);								
	       
	       
	    
	    JButton btn_home = new JButton(new ImageIcon("./image./coffee/red.jpg"));		// 초기 화면 설정 시작
	    btn_home.setBounds(430,680,182,130);
	    btn_home.setLayout(null);
	    
	    JLabel lab_revoke = new JLabel();
	    lab_revoke.setText("초기 화면");
	    lab_revoke.setBounds(48,28,100,80);
	    lab_revoke.setFont(new Font("바탕", Font.BOLD, 22));   
	    btn_home.add(lab_revoke);
	    panel_order.add(btn_home);														// 조기 화면 설정 끝
	    
	    JButton btn_pay = new JButton(new ImageIcon("./image./coffee/green.jpg"));		// 주문하기 설정 시작
	    btn_pay.setBounds(430,820,182,130);
	    btn_pay.setLayout(null);														// 주문하기 설정 끝
	    
	    JLabel lab_pay = new JLabel();													// 주문하기 버튼 설정 시작
	    lab_pay.setText("주문하기");
	    lab_pay.setBounds(48,20,100,100);
	    lab_pay.setFont(new Font("바탕", Font.BOLD, 22));   
	    btn_pay.add(lab_pay);
	    panel_order.add(btn_pay);														// 주문하기 버튼 설정 끝
	    
	    btn_pay.addActionListener(new ActionListener() { 				// 주문하기 클릭시, 장바구니 화면이 출력되고, 주문한 메뉴의 총 금액이 출력되도록 설정 시작
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            panel_cart.setVisible(true);
	            panel_order.setVisible(false);
	            
	            
	            total_price =(cost[0]*(int)spinner1.getValue())         
	                      +(cost[1]*(int)spinner2.getValue())
	                      +(cost[2]*(int)spinner3.getValue())
	                      +(cost[3]*(int)spinner4.getValue())
	                      +(cost[4]*(int)spinner5.getValue());
	                      
	            
	            textArea.append(btnString1+btnString2+btnString3+btnString4+btnString5);
	            lbltotalP.setText(textLavel(total_price));
	         }
	      });															// 주문하기 클릭시, 장바구니 화면이 출력되고, 주문한 메뉴의 총 금액이 출력되도록 설정 끝
	    
	    btn_home.addActionListener(new ActionListener() {					// 홈버튼 클릭시 홈화면 이동 시작
			public void actionPerformed(ActionEvent e) {
				btnString1 = "";
                btnString2 = "";
                btnString3 = "";
                btnString4 = "";
                btnString5 = "";
                total_price =0;
                
                
                spinner1.setValue(0);
                spinner2.setValue(0);
                spinner3.setValue(0);
                spinner4.setValue(0);
                spinner5.setValue(0);
                textArea.setText("제품명\t\t가격\t\t수량\n\n");
				panel_main.setVisible(true);
				panel_order.setVisible(false);
			}			
	    });																	// 홈버튼 클릭시 홈화면 이동 끝
	    
	    JButton btnClean = new JButton("취소 하기");      					// 취소 하기 버튼 클릭시 데이터 초기화 작업 시작
	      btnClean.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 spinner1.setValue(0);
	             spinner2.setValue(0);
	             spinner3.setValue(0);
	             spinner4.setValue(0);
	             spinner5.setValue(0);
//	             spinner_ice.setValue(0);
//		        spinner_sizeup.setValue(0);
	            panel_cart.setVisible(false);
	            panel_order.setVisible(true);
	            panel_cart.setVisible(false);
	            textArea.setText("제품명\t\t가격\t\t수량\n\n");
	            btnString1 = "";
	            btnString2 = "";
	            btnString3 = "";
	            btnString4 = "";
	            btnString5 = "";
                total_price =0;
              
	            textArea.append(btnString1+btnString2+btnString3+btnString4+btnString5);
	         }
	         
	      });															// 취소 하기 버튼 클릭시 데이터 초기화 작업 시작
	      
	      btnClean.setBounds(47, 900, 97, 35);							// 취소 하기 버튼 
	      panel_cart.add(btnClean);										// 취소 하기 버튼 panel_cart에 추가
	      
	      btnconfirm.addActionListener(new ActionListener() { 			// 결제 하기 버튼 클릭시 countF frame 출력하는 이벤트 발생 시작
	             @Override
	             public void actionPerformed(ActionEvent e) {       
	            	 countF.setVisible(true);
	           }
	    });																// 결제 하기 버튼 클릭시 countF frame 출력하는 이벤트 발생 끝			
	      
	      btn_yes.addActionListener(new ActionListener() {					// 결제 후 모든 데이터 초기화작업 시작
		         public void actionPerformed(ActionEvent e) {
		            JOptionPane.showMessageDialog(null, "결제 완료 되었습니다.\n"
		         + "결제 금액은 "+ total_price+" 원 입니다.\n 주문 번호 : "+cnt);
		            OrderDao od = OrderDao.getOrderDao();
		            if(btnString1!="")											//결제 항목 Order 객체에 저장하여 insert
		            	od.insert(new Order(cnt,menu[0],(int)spinner1.getValue()));
		            if(btnString2!="")
		            	od.insert(new Order(cnt,menu[1],(int)spinner2.getValue()));
		            if(btnString3!="")
		            	od.insert(new Order(cnt,menu[2],(int)spinner3.getValue()));
		            if(btnString4!="")
		            	od.insert(new Order(cnt,menu[3],(int)spinner4.getValue()));
		            if(btnString5!="")
		            	od.insert(new Order(cnt,menu[4],(int)spinner5.getValue()));
		              btnString1 = "";
		              btnString2 = "";
		              btnString3 = "";
		              btnString4 = "";
		              btnString5 = "";
		              total_price =0;
		              
		              spinner1.setValue(0);
		              spinner2.setValue(0);
		              spinner3.setValue(0);
		              spinner4.setValue(0);
		              spinner5.setValue(0);
		              textArea.setText("제품명\t\t제품단가\t수량\n\n");
		              panel_cart.setVisible(false);
		              panel_main.setVisible(true);
		              countF.setVisible(false);
		              CustomDao cd = CustomDao.getCustomDao();		//결제 완료 후 고객번호+1
		              cd.insert(new Custom(++cnt,""));  
		                }
		          });														// 결제 후 모든 데이터 초기화작업 끝
	      
	      btn_no.addActionListener(new ActionListener() {					// 결제frame에서 아니요 버튼 클릭시 countF종료 작업 시작
		         public void actionPerformed(ActionEvent e) {
		        	 countF.setVisible(false);
		         }		
	      });																// 결제frame에서 아니요 버튼 클릭시 countF종료 작업 시작
	    
	}
	
	 String textLavel(int num) {         		//출력되는 숫자 3개마다 ,로 나뉘어 출력되도록 설정 시작
	      DecimalFormat dc = new DecimalFormat("#,###,###,###");
	      String d = dc.format(num);
	      return d+"원";
	   }										//출력되는 숫자 3개마다 ,로 나뉘어 출력되도록 설정 끝

		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
		         public void run() {
		            try {
		               Test kiosk = new Test();
		               kiosk.frame.setVisible(true);
		           } catch (Exception e) {
		               e.printStackTrace();
		           }
		       }
		  });
			
			
	}
}