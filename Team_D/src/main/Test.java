package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private JFrame frame;
    private JFrame countF;
    List<String> MS = new ArrayList<>();
    String[] menu = {"아메리카노","아이스 아메리카노","카페 라떼","캬라멜 마끼야또" };		// 메뉴 배열
    int[] cost = {2500,3000,3500,4000};										// 가격 배열

    String menuName = "";
    int count = 1;

    String result = "";
    int sum;
    String indexsplit[];
    String[] textsplit1;
    String[] textsplit2;
    String[] textsplit3;

    String btnString1 = "";
    String btnString2 = "";
    String btnString3 = "";
    String btnString4 = "";

    public Test() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();									//기본 Frame시작
        frame.setSize(700,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setTitle("D Coffee");								//기본 Frame끝

        countF = new JFrame();									//기본 Frame시작
        countF.setSize(450,180);
        countF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        countF.getContentPane().setLayout(null);
        countF.setResizable(false);
        countF.setLocationRelativeTo(null);
        countF.setVisible(false);
        countF.setTitle("D Coffee");							//기본 Frame끝

        JPanel panel_main = new JPanel();						// 홈화면 Panel 시작
        panel_main.setBounds(0,0,700,1000);
        frame.add(panel_main);
        panel_main.setLayout(null);
        panel_main.setVisible(true);							// 홈화면 Panel 끝

        JPanel panel_order = new JPanel();						// 주문화면 Panel 시작
        panel_order.setBounds(0,0,700,1000);
        frame.add(panel_order);
        panel_order.setLayout(null);
        panel_order.setVisible(false);							// 주문화면 Panel 끝

        JPanel panel_basket = new JPanel();						// 장바구니 Panel 시작
        panel_basket.setBounds(10,680,480,271);
        panel_basket.setBackground(Color.white);
        panel_basket.setLayout(null);
        panel_order.add(panel_basket);							// 장바구니 Panel 끝

        JTextArea textArea = new JTextArea(TextArea.SCROLLBARS_VERTICAL_ONLY, 0);
        textArea.setBounds(0, 0, 500, 20);
        textArea.setEditable(false);							//입력 못하고 데이터 출력용입니다.
        textArea.setText("제품명\t\t수량\t제품단가\n\n");
        panel_basket.add(textArea);

        JButton btn_eat = new JButton("매 장");				 	 // 홈화면 매장버튼 설정 시작
        btn_eat.setBounds(20,90,650,320);
        btn_eat.setFont(new Font("바탕", Font.ITALIC, 150));   	// 홈화면 매장버튼 설정 끝
        panel_main.add(btn_eat);
        JButton btn_wrap = new JButton("포 장"); 			      	// 홈화면 포장버튼 설정 시작
        btn_wrap.setBounds(20,535,650,320);
        btn_wrap.setFont(new Font("바탕", Font.ITALIC, 150));	 	 // 홈화면 포장버튼 설정 끝
        panel_main.add(btn_wrap);

        btn_eat.addActionListener(new ActionListener() {		// 매장버튼 클릭시 이벤트 시작
            public void actionPerformed(ActionEvent e) {
                panel_order.setVisible(true);
                panel_main.setVisible(false);
            }
        });														// 매장버튼 클릭시 이벤트 끝

        btn_wrap.addActionListener(new ActionListener() {		// 포장버튼 클릭시 이벤트 시작
            public void actionPerformed(ActionEvent e) {
                panel_order.setVisible(true);
                panel_main.setVisible(false);
            }
        });														// 포장버튼 클릭시 이벤트 끝

        JButton btn_home = new JButton(new ImageIcon("./image./coffee/7.jpg"));		// 메뉴화면 홈버튼 설정 시작
        btn_home.setBounds(550,30,100,100);
        panel_order.add(btn_home);											// 메뉴화면 홈버튼 설정 끝

        btn_home.addActionListener(new ActionListener() {					// 홈버튼 클릭시 홈화면 이동 시작
            public void actionPerformed(ActionEvent e) {
                panel_main.setVisible(true);
                panel_order.setVisible(false);
            }
        });																	// 홈버튼 클릭시 홈화면 이동 끝

        JLabel hlb = new JLabel("D Coffee");								// 머릿글 설정 시작
        hlb.setHorizontalAlignment(SwingConstants.CENTER);
        hlb.setBounds(142, 30, 300, 120);
        hlb.setFont(hlb.getFont().deriveFont(72.0f));						// 머릿글 설정 끝
        panel_order.add(hlb);

        JPanel panel_mcount = new JPanel();								// 수량 , 확인버튼 패널 설정 시작
        panel_mcount.setBounds(0,70,433,72);
        panel_mcount.setBackground(Color.white);
        panel_mcount.setLayout(null);
        countF.add(panel_mcount);										// 수량, 확인버튼 패널 설정 끝

        JLabel num = new JLabel("1");									// 수량 Text 설정 시작
        num.setFont(new Font("바탕", Font.ITALIC, 45));
        num.setHorizontalAlignment(JLabel.CENTER);
        num.setBounds(108,0,85,72);
        panel_mcount.add(num);											// 수량 Text 설정 끝


        JButton btn_minus = new JButton(new ImageIcon("./image./coffee/minus.jpg"));	// 수량 빼기(-) 설정 시작
        btn_minus.setBounds(6,0,85,72);
        panel_mcount.add(btn_minus);

        btn_minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(count >= 2) {
                    --count;
                    num.setText(Integer.toString(count));
                }
            }
        });																				// 수량 뺴기(-) 설정 끝

        JButton btn_plus = new JButton(new ImageIcon("./image./coffee/plus.jpg"));		// 수량 더하기(+) 설정 시작
        btn_plus.setBounds(213,0,85,72);
        panel_mcount.add(btn_plus);
        btn_plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(count >= 1) {
                    ++count;
                    num.setText(Integer.toString(count));
                }
            }
        });																				// 수량 더하기(+) 설정 시작


        JButton btn_decide = new JButton(new ImageIcon("./image./coffee/green.jpg"));	// 담기 버튼 설정 시작
        btn_decide.setBounds(305,0,123,72);
        btn_decide.setLayout(null);

        JLabel lab_decide = new JLabel();
        lab_decide.setText("담기");
        lab_decide.setBounds(40,0,100,80);
        lab_decide.setFont(new Font("바탕", Font.BOLD, 20));
        btn_decide.add(lab_decide);
        panel_mcount.add(btn_decide);													// 담기 버튼 설정 끝

        JButton btn_hot = new JButton(new ImageIcon("./image./coffee/11.jpg"));		// 아메리카노 이미지 설정 시작
        btn_hot.setBounds(70,170,240,200);
        panel_order.add(btn_hot);													// 아메리카노 이미지 설정 끝

        JButton btn_ice = new JButton(new ImageIcon("./image./coffee/22.jpg"));		// 아이스 아메리카노 이미지 설정 시작
        btn_ice.setBounds(380,170,240,200);
        panel_order.add(btn_ice);													// 아이스 아메리카노 이미지 설정 끝

        JButton btn_latte = new JButton(new ImageIcon("./image./coffee/33.jpg"));	// 카페라떼 이미지 설정 시작
        btn_latte.setBounds(70,435,240,200);
        panel_order.add(btn_latte);													// 카페라떼 이미지 설정 끝

        JButton btn_caramel = new JButton(new ImageIcon("./image./coffee/44.jpg"));	// 캬라멜 마끼야또 이미지 설정 시작
        btn_caramel.setBounds(380,435,240,200);
        panel_order.add(btn_caramel);												// 캬라멜 마끼야도 이미지 설정 끝

        JLabel hot = new JLabel();												// 아메리카노 글자 설정 시작
        hot.setText(String.valueOf(menu[0] + "  " + cost[0] + "원") );
        hot.setHorizontalAlignment(SwingConstants.CENTER);
        hot.setBounds(90,290,200,200);
        hot.setFont(hlb.getFont().deriveFont(20.0f));
        panel_order.add(hot);											 		// 아메리카노 글자 설정 끝

        JLabel ila = new JLabel();												// 아이스 아메리카노 글자 설정 시작
        ila.setText(String.valueOf(menu[1] + "  " + cost[1] + "원") );
        ila.setHorizontalAlignment(SwingConstants.CENTER);
        ila.setBounds(325,290,350,200);
        ila.setFont(hlb.getFont().deriveFont(20.0f));
        panel_order.add(ila);														// 아이스 아메리카노 글자 설정 끝

        JLabel latte = new JLabel();												// 카페라떼 글자 설정 시작
        latte.setText(String.valueOf(menu[2] + "  " + cost[2] + "원") );
        latte.setHorizontalAlignment(SwingConstants.CENTER);
        latte.setBounds(90,555,200,200);
        latte.setFont(hlb.getFont().deriveFont(20.0f));
        panel_order.add(latte);														// 카페라떼 글자 설정 끝

        JLabel caramel = new JLabel();												// 캬라멜 마끼아또 글자 설정 시작
        caramel.setText(String.valueOf(menu[3] + "  " + cost[3] + "원") );
        caramel.setHorizontalAlignment(SwingConstants.CENTER);
        caramel.setBounds(355,555,280,200);
        caramel.setFont(hlb.getFont().deriveFont(20.0f));
        panel_order.add(caramel);													// 캬라멜 마끼야또 글자 설정 끝

        JButton btn_revoke = new JButton(new ImageIcon("./image./coffee/red.jpg"));		// 전체취소 설정 시작
        btn_revoke.setBounds(500,680,172,130);
        btn_revoke.setLayout(null);

        JLabel lab_revoke = new JLabel();
        lab_revoke.setText("전체취소");
        lab_revoke.setBounds(42,28,100,80);
        lab_revoke.setFont(new Font("바탕", Font.BOLD, 22));
        btn_revoke.add(lab_revoke);
        panel_order.add(btn_revoke);													// 전체취소 설정 끝


        JButton btn_pay = new JButton(new ImageIcon("./image./coffee/green.jpg"));		// 주문하기 설정 시작
        btn_pay.setBounds(500,820,172,130);
        btn_pay.setLayout(null);

        JLabel lab_pay = new JLabel();
        lab_pay.setText("주문하기");
        lab_pay.setBounds(42,20,100,100);
        lab_pay.setFont(new Font("바탕", Font.BOLD, 22));
        btn_pay.add(lab_pay);
        panel_order.add(btn_pay);														// 주문하기 버튼 끝

        JPanel panel_mname = new JPanel();							// 메뉴 이름 출력 시작
        panel_mname.setSize(450,70);
        panel_mname.setBackground(Color.white);
        panel_mname.setLayout(null);
        countF.add(panel_mname);									// 메뉴 이름 출력 끝

        JLabel mname = new JLabel();								// 메뉴 이름 설정 시작
        mname.setFont(new Font("바탕", Font.ITALIC, 42));
        mname.setHorizontalAlignment(JLabel.CENTER);
        mname.setBounds(0,0,420,70);
        panel_mname.add(mname);										// 메뉴 이름 설정 끝

        btn_hot.addActionListener(new ActionListener() {			// 이미지 클릭시 새창 생성 시작
            public void actionPerformed(ActionEvent e) {
                mname.setText(menu[0]);
                menuName = menu[0];
					result = menuName + "\t\t " + count + "\t" + (count*cost[0]);
                countF.setVisible(true);
            }
        });

        btn_ice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mname.setText(menu[1]);
                menuName = menu[1];
					result = menuName + "\t\t " + count + "\t" + (count*cost[1]);
                countF.setVisible(true);
            }
        });

        btn_latte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mname.setText(menu[2]);
                menuName = menu[2];
					result = menuName + "\t\t " +count + "\t" + (count*cost[2]);
                countF.setVisible(true);
            }
        });

        btn_caramel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mname.setText(menu[3]);
                menuName = menu[3];
					result = menuName + "\t\t " + count + "\t" + (count*cost[3]);
                countF.setVisible(true);
            }
        });															// 이미지 클릭시 새창 생성 시작



        countF.addWindowListener(new WindowAdapter() {					// 닫기(X)버튼 클릭시 이벤트 발생 시작
            public void windowClosing(WindowEvent e) {
            	count = 1;
                countF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });																// 닫기(X)버튼 클릭시 이벤트 발생 끝


        JTextArea text1 = new JTextArea(TextArea.SCROLLBARS_VERTICAL_ONLY, 0);			// 장바구니 헤드라인 설정 시작
        text1.setBounds(0, 20, 500, 20);
        text1.setEditable(false);				//입력 못하고 데이터 출력용입니다.
        panel_basket.add(text1);														// 장바구니 헤드라인 설정 끝

        JTextArea text2 = new JTextArea(TextArea.SCROLLBARS_VERTICAL_ONLY, 0);			// 장바구니 헤드라인 설정 시작
        text2.setBounds(0, 40, 500, 20);
        text2.setEditable(false);				//입력 못하고 데이터 출력용입니다.
        panel_basket.add(text2);														// 장바구니 헤드라인 설정 끝

        JTextArea text3 = new JTextArea(TextArea.SCROLLBARS_VERTICAL_ONLY, 0);			// 장바구니 헤드라인 설정 시작
        text3.setBounds(0, 120, 500, 20);
        text3.setEditable(false);				//입력 못하고 데이터 출력용입니다.
        panel_basket.add(text3);														// 장바구니 헤드라인 설정 끝

        JTextArea text4 = new JTextArea(TextArea.SCROLLBARS_VERTICAL_ONLY, 0);			// 장바구니 헤드라인 설정 시작
        text4.setBounds(0, 160, 500, 20);
        text4.setEditable(false);				//입력 못하고 데이터 출력용입니다.
        panel_basket.add(text4);														// 장바구니 헤드라인 설정 끝


        btn_decide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                text1.setText(result);
                indexsplit = result.split("\\s");
                textsplit1 = text1.getText().split("\\s");

//                    if(textsplit1[0]) {
//                        text2.setText(result);
//                        textsplit2 = text2.getText().split("\\s");
//                }
//                     else if(textsplit2[0].equals(indexsplit[0])) {
//                      text2.setText(result);
//                }    else if(textsplit2[0].equals(indexsplit[0]) == false) {
//                      text3.setText(result);
//                      textsplit3 = text3.getText().split("\\s");
//                }    else if(textsplit3[0].equals(indexsplit[0])) {
//                         text3.setText(result);
//                     } else if(textsplit3[0].equals(indexsplit[0]) == false) {
//                         text4.setText(result);
//                    } else {
//                         text1.setText(result);
//                    }
//                     count = 1;
//                num.setText("1");
//                countF.setVisible(false);
//
              }
        });


        JTextArea textMN = new JTextArea(TextArea.SCROLLBARS_VERTICAL_ONLY, 0);			// 장바구니 헤드라인 설정 시작
        textArea.setBounds(0, 0, 500, 20);
        textArea.setEditable(false);				//입력 못하고 데이터 출력용입니다.
        textArea.setText("제품명\t\t수량\t제품단가\n\n");
        panel_basket.add(textArea);														// 장바구니 헤드라인 설정 끝





    }


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
