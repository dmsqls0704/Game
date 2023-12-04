package Game;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * 
 * 정보 열람 화면을 실행하는 클래스이다. 
 * 사용자 닉네임, 최고 점수, 이미지를 보여준다.
 * @author dmsqls
 * 
 */
public class CardInfo extends JPanel{
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private String[] data;
	/**
	 * CardInfo 클래스의 생성자이다.
	 * @param layout	CardLayout을 설정하는데 사용되는 객체
	 * @param panel		CardLayout이 포함된 패널
	 * @param mainPage	메인 화면으로 돌아가기 위한 객체
	 * @param data		사용자 정보를 담은 배열 (닉네임, 최고 점수)
	 */
	public CardInfo(CardLayout layout, JPanel panel, MainPage mainPage, String[] data){
		cardLayout = layout;
        cardPanel = panel;
        this.data = data;
        
		setLayout(cardLayout);
		GridBagConstraints gbc = new GridBagConstraints();
		
		//정보 열람 화면의 전체 panel
		JPanel panelI = new JPanel();
        panelI.setLayout(new GridBagLayout());
        panelI.setBackground(Utility.backcolor);
        
        //유저의 최고 점수를 보여줄 panel
        JPanel userRank = new JPanel();
        userRank.setLayout(new GridBagLayout());
        userRank.setBackground(Utility.basecolor);
        
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 4; 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelI.add(userRank,gbc);
        
        gbc.gridwidth =1;
        gbc.fill = GridBagConstraints.NONE;
        Font font = Utility.yeongdeok_sea(50f);
        
        ImageIcon rankI = new ImageIcon(getClass().getResource("/image/trophy.png"));
        Image originalImage = rankI.getImage();
        Image scaledImage = originalImage.getScaledInstance(85,90,Image.SCALE_FAST);
        rankI = new ImageIcon(scaledImage);
        JLabel rankImage = new JLabel(rankI);
        gbc.insets = new Insets(10,10,10,10);
        userRank.add(rankImage);
        
        gbc.gridwidth = 2;
        JLabel userNick = new JLabel(data[0]);
        JLabel userScore = new JLabel(data[2]);
        userNick.setMaximumSize(new Dimension(200, 80)); 
      
        userNick.setFont(font);
        userScore.setFont(font);
        userScore.setForeground(Utility.maincolor);
        
        gbc.gridx=1;
        gbc.insets = new Insets(10,10,10,10);
        userRank.add(userNick,gbc);
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10,340,10,10);
        userRank.add(userScore,gbc);
        
        //뒤로가기 버튼 생성
        JButton backButton = new RoundedButton("");
        ImageIcon homeImage = new ImageIcon(getClass().getResource("/image/yongyonghome.png"));
        Image originalhomeImage = homeImage.getImage();
        Image scaledhomeImage = originalhomeImage.getScaledInstance(100,100, Image.SCALE_FAST);
        homeImage= new ImageIcon(scaledhomeImage);
        
        backButton.setIcon(homeImage);
        backButton.setPreferredSize(new Dimension(80,80));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "mainPanel"); 
            }
        });

        gbc.gridwidth = 1;
        gbc.gridx=3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10,50,10,10);
        userRank.add(backButton,gbc);
       
        //스크롤바 생성
        JScrollPane scroll = new JScrollPane();
        JScrollBar scrollBar = scroll.getVerticalScrollBar();
        scrollBar.setUnitIncrement(16); // 단위 스크롤링 속도 설정
        
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new GridBagLayout());
        imagePanel.setBackground(Utility.backcolor);
        
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridy=1;
        gbc.gridwidth=3;
        Font fontLabel = Utility.yeongdeok_haeparang(25f);
        
        JLabel easyLabel = new JLabel("난이도 하 : 전남대 건축물");
        easyLabel.setBackground(Utility.pointcolor);
        easyLabel.setFont(fontLabel);
        easyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        easyLabel.setForeground(Color.WHITE);
        easyLabel.setOpaque(true);
        easyLabel.setPreferredSize(new Dimension(0, 50));
        imagePanel.add(easyLabel,gbc);
        
        
        //난이도 하 이미지
        addImageEasy(imagePanel,imagePathEasy, nameEasy,2, gbc);
        
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.gridwidth=3;
        JLabel mediumLabel = new JLabel("난이도 중 : 주변 맛집");
        mediumLabel.setBackground(Utility.pointcolor);
        mediumLabel.setFont(fontLabel);
        mediumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mediumLabel.setForeground(Color.WHITE);
        mediumLabel.setOpaque(true);
        mediumLabel.setPreferredSize(new Dimension(0, 50));
        imagePanel.add(mediumLabel,gbc);
        
        //난이도 중 이미지
        addImageMedium(imagePanel, imagePathMedium, nameMedium, addr, 6, gbc);
        
        gbc.gridx=0;
        gbc.gridy=11;
        gbc.gridwidth=3;
        JLabel hardLabel = new JLabel("난이도 상 : 중앙 동아리");
        hardLabel.setBackground(Utility.pointcolor);
        hardLabel.setFont(fontLabel);
        hardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hardLabel.setForeground(Color.WHITE);
        hardLabel.setOpaque(true);
        hardLabel.setPreferredSize(new Dimension(0, 50));
        imagePanel.add(hardLabel,gbc);
        
        //난이도 상 이미지
        addImageHard(imagePanel,imagePathHard, nameHard,clubHard, 12, gbc);
        
        scroll.setViewportView(imagePanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getVerticalScrollBar().setBackground(Utility.backcolor);
       
        panelI.add(scroll,gbc);
        add(panelI, "InfoPanel");
	}
	/**
	 * 난이도 하 이미지 생성 메소드
	 * @param panel 이미지를 넣을 panel을 받아오기 위한 변수
	 * @param imagePathEasy 이미지 파일을 받아오기 위한 변수
	 * @param nameEasy 이미지 이름 지정을 위한 변수
	 * @param startGridy 시작 gridy를 받아오기 위한 변수
	 * @param gbc  이미지 위치 조정을 위한 변수
	 */

	 private void addImageEasy(JPanel panel, String[] imagePathEasy, String[] nameEasy, int startGridy, GridBagConstraints gbc) {
		    for (int i = 0; i < imagePathEasy.length; i++) {
		        String imagePath = imagePathEasy[i];
		        String name = nameEasy[i];

		        ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
		        Image originalImage = imageIcon.getImage();
		        Image scaledImage = originalImage.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_FAST);
		        imageIcon = new ImageIcon(scaledImage);

		        ImageInfo imageLabel = new ImageInfo(imageIcon, name);
		        gbc.weightx = 1.0;
		        gbc.weighty = 1.0;
		        gbc.gridwidth=1;
		        gbc.gridx = i % 3;  // 필요한 경우 열 수에 따라 조정
		        gbc.gridy = startGridy + i / 3;  // 필요한 경우 열 수에 따라 조정
		        gbc.insets = new Insets(10, 10, 10, 10);
		        panel.add(imageLabel, gbc);
		    }
		}
	 String[] imagePathEasy = { "/image/bongji.jpg", "/image/clockt.jpg", "/image/baekdo.jpg","/image/hongdo.jpg","/image/dido.jpg","/image/yongbongt.jpg","/image/yongji.jpg","/image/bongji.jpg"  };
	 String[] nameEasy = { "봉지", "시계탑", "백도", "홍도", "디도" ,"용봉탑", "용지", "봉지"};
	 /**
	  * 난이도 중 이미지 생성 메소드
	  * @param panel 이미지를 넣을 panel을 받아오기 위한 변수
	  * @param imagePathMedium 이미지 파일을 받아오기 위한 변수
	  * @param nameMedium 이미지 이름 지정을 위한 변수
	  * @param address 주소 정보를 지정하기 위한 변수
	  * @param startGridy 시작 gridy를 받아오기 위한 변수
	  * @param gbc 이미지 위치 조정을 위한 변수
	  */
	 private void addImageMedium(JPanel panel, String[] imagePathMedium, String[] nameMedium ,String[] address, int startGridy, GridBagConstraints gbc) {
		    for (int i = 0; i < imagePathMedium.length; i++) {
		        String imagePath = imagePathMedium[i];
		        String name = nameMedium[i];
		        String addr = address[i];

		        ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
		        Image originalImage = imageIcon.getImage();
		        Image scaledImage = originalImage.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_FAST);
		        imageIcon = new ImageIcon(scaledImage);

		        ImageInfo imageLabel = new ImageInfo(imageIcon, name, addr);
		        gbc.weightx = 1.0;
		        gbc.weighty = 1.0;
		        gbc.gridwidth=1;
		        gbc.gridx = i % 3;  // 필요한 경우 열 수에 따라 조정
		        gbc.gridy = startGridy + i / 3;  // 필요한 경우 열 수에 따라 조정
		        gbc.insets = new Insets(10, 10, 10, 10);
		        panel.add(imageLabel, gbc);
		    }
		}
	 String[] imagePathMedium = { "/image/food1.jpg", "/image/food2.jpg", "/image/food3.jpg","/image/food4.jpg","/image/food5.jpg","/image/food6.jpg","/image/food7.jpg","/image/food8.jpg",
			 					  "/image/food9.jpg", "/image/food10.jpg", "/image/food11.jpg","/image/food12.jpg","/image/food13.jpg","/image/food14.jpg","/image/food15.jpg"};
	 String[] nameMedium = { "알촌", "까동", "천지연삼겹살", "전대별식", "주디마리" ,"후토루", "정통집", "등촌", 
			 				 "골목", "로니로티", "미스터초밥왕", "예향정", "뼈대있는집" ,"그여자의밥상", "한우촌"};
	 String[] addr = {"광주 북구 설죽로214번길 122", "광주 북구 설죽로214번길 129", "광주 북구 설죽로214번길 90", "광주 북구 우치로90번길 17 1층", "광주 북구 우치로 86 지하1층" ,"광주 북구 호동로 6-3 1층 ", "광주 북구 우치로100번길 17", "광주 북구 우치로 142",
			     	  "광주 북구 용봉로 62-6 1층", "광주 북구 호동로15번길 13", "광주 북구 우치로100번길 10-6", "광주 북구 호동로 5", "광주 북구 호동로 28 1층" ,"광주 북구 설죽로214번길 123", "정광주 북구 용주로30번길 36"};
	 
	 /**
	  * 난이도 상 이미지 생성 메소드
	  * @param panel 이미지를 넣을 panel을 받아오기 위한 변수 
	  * @param imagePathHard 이미지 파일을 받아오기 위한 변수 
	  * @param nameHard 이미지 이름 지정을 위한 변수
	  * @param clubHard 동아리 정보를 지정하기 위한 변수
	  * @param startGridy 시작 gridy를 받아오기 위한 변수
	  * @param gbc 이미지 위치 조정을 위한 변수
	  */
	 private void addImageHard(JPanel panel, String[] imagePathHard, String[] nameHard,String[] clubHard, int startGridy, GridBagConstraints gbc) {
		    for (int i = 0; i < imagePathHard.length; i++) {
		        String imagePath = imagePathHard[i];
		        String name = nameHard[i];
		        String club = clubHard[i];

		        ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
		        Image originalImage = imageIcon.getImage();
		        Image scaledImage = originalImage.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_FAST);
		        imageIcon = new ImageIcon(scaledImage);

		        ImageInfo imageLabel = new ImageInfo(imageIcon, name, club);
		        gbc.weightx = 1.0;
		        gbc.weighty = 1.0;
		        gbc.gridwidth=1;
		        gbc.gridx = i % 3;  // 필요한 경우 열 수에 따라 조정
		        gbc.gridy = startGridy + i / 3;  // 필요한 경우 열 수에 따라 조정
		        gbc.insets = new Insets(10, 10, 10, 10);
		        panel.add(imageLabel, gbc);
		    }
		}

	String[] imagePathHard = { "/image/club1.jpg", "/image/club2.jpg", "/image/club3.jpg","/image/club4.jpg","/image/club5.jpg","/image/club6.jpg",
								"/image/club7.jpg","/image/club8.jpg","/image/club9.jpg","/image/club10.jpg","/image/club11.jpg","/image/club12.jpg",
								"/image/club13.jpg","/image/club14.jpg","/image/club15.jpg","/image/club16.jpg","/image/club17.jpg","/image/club18.jpg",
								"/image/club19.jpg","/image/club20.jpg","/image/club21.jpg","/image/club22.jpg","/image/club23.jpg","/image/club24.jpg"};
	String[] nameHard = { "맥킨토쉬", "열린만화창", "ZOOM", "Ctrl","당다라당","뉴에라","DOVE","일본 문화 연구회", "메이플","대학희망","호버링","ppp",
						  "블랙베이스","청불","전대극회","마음쉬는곳","관혁악반","SFC","KUSA","We'z","YMCA","별따오기","어푸어푸","유스호스텔"};
	String[] clubHard = {"(밴드)","(만화 창작)","(사진 예술)","(게임 문화)","(당구)","(댄스)","(축구)","(일본 문화)","(밴드)","(동물보호소 봉사)","(드론 축구)","(탁구)",
						 "(야구)","(영상)","(연극)","(불교)","(오케스트라)","(기독교)","(유네스코 이념)","(마술)","(영화 감상)","(천체 관측)","(수영)","(여행)"};
	
	
	 private int IMAGE_WIDTH =225;
	 private int IMAGE_HEIGHT = 300;
	
}
