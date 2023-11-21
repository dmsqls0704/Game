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
				
		JPanel panelI = new JPanel();
        panelI.setLayout(new GridBagLayout());
        panelI.setBackground(new Color(248,244,235));
        
        JPanel userRank = new JPanel();
        userRank.setLayout(new GridBagLayout());
        userRank.setBackground(new Color(237, 227, 206));
        
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 4; 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelI.add(userRank,gbc);
        
        gbc.gridwidth =1;
        gbc.fill = GridBagConstraints.NONE;
//        Font font = new Font("Yeongdeok sea", Font.PLAIN, 30);
        Font font = Utility.yeongdeok_sea(50);
        
        ImageIcon rankI = new ImageIcon(getClass().getResource("/image/trophy.png"));
        Image originalImage = rankI.getImage();
        Image scaledImage = originalImage.getScaledInstance(85,90,Image.SCALE_SMOOTH);
        rankI = new ImageIcon(scaledImage);
        JLabel rankImage = new JLabel(rankI);
        gbc.insets = new Insets(10,10,10,10);
        userRank.add(rankImage);
        
        gbc.gridwidth = 2;
        JLabel userNick = new JLabel(data[0]);
        JLabel userScore = new JLabel(data[1]);
        userNick.setMaximumSize(new Dimension(200, 80)); 
//        userNick.setPreferredSize(new Dimension(200, 80)); 
        
        userNick.setFont(font);
        userScore.setFont(font);
        userScore.setForeground(new Color(125, 159, 104));
        
        gbc.gridx=1;
        gbc.insets = new Insets(10,10,10,10);
        userRank.add(userNick,gbc);
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10,340,10,10);
        userRank.add(userScore,gbc);
        
        JButton backButton = new RoundedButton("");
        ImageIcon homeImage = new ImageIcon(getClass().getResource("/image/yongyonghome.png"));
        Image originalhomeImage = homeImage.getImage();
        Image scaledhomeImage = originalhomeImage.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        homeImage= new ImageIcon(scaledhomeImage);
        
        backButton.setIcon(homeImage);
        backButton.setPreferredSize(new Dimension(80,80));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "mainPanel"); 
            }
        });
        backButton.setBackground(new Color(125, 159, 104));
        backButton.setBorder(BorderFactory.createLineBorder(new Color(80, 102, 67)));
        UIManager.put("Button.focus", new ColorUIResource(new Color(125, 159, 104)));

        gbc.gridwidth = 1;
        gbc.gridx=3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10,50,10,10);
        userRank.add(backButton,gbc);
       
        JScrollPane scroll = new JScrollPane();
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new GridBagLayout());
        imagePanel.setBackground(new Color(248,244,235));
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        
        gbc.anchor = GridBagConstraints.WEST;
        
        addImage(imagePanel, "/image/bongji.jpg",0,1,gbc);
        addImage(imagePanel, "/image/clockt.jpg",1,1,gbc);
        addImage(imagePanel, "/image/baekdo.jpg",2,1,gbc);
        addImage(imagePanel, "/image/dido.jpg",0,2,gbc);
        addImage(imagePanel, "/image/hongdo.jpg",1,2,gbc);
        addImage(imagePanel, "/image/playground.jpg",2,2,gbc);
        addImage(imagePanel, "/image/yongbongt.jpg",0,3,gbc);
        addImage(imagePanel, "/image/yongji.jpg",1,3,gbc);
        
        addImage(imagePanel, "/image/club1.jpg",0,4,gbc);
        addImage(imagePanel, "/image/club2.jpg",1,4,gbc);
        addImage(imagePanel, "/image/club3.jpg",2,4,gbc);
        addImage(imagePanel, "/image/club4.jpg",0,5,gbc);
        addImage(imagePanel, "/image/club5.jpg",1,5,gbc);
        addImage(imagePanel, "/image/club6.jpg",2,5,gbc);
        addImage(imagePanel, "/image/club7.jpg",0,6,gbc);
        addImage(imagePanel, "/image/club8.jpg",1,6,gbc);
        addImage(imagePanel, "/image/club9.jpg",2,6,gbc);
        addImage(imagePanel, "/image/club10.jpg",0,7,gbc);
        addImage(imagePanel, "/image/club11.jpg",1,7,gbc);
        addImage(imagePanel, "/image/club12.jpg",2,7,gbc);
        addImage(imagePanel, "/image/club13.jpg",0,8,gbc);
        addImage(imagePanel, "/image/club14.jpg",1,8,gbc);
        addImage(imagePanel, "/image/club15.jpg",2,8,gbc);
        addImage(imagePanel, "/image/club16.jpg",0,9,gbc);
        addImage(imagePanel, "/image/club17.jpg",1,9,gbc);
        addImage(imagePanel, "/image/club18.jpg",2,9,gbc);
        addImage(imagePanel, "/image/club19.jpg",0,10,gbc);
        addImage(imagePanel, "/image/club20.jpg",1,10,gbc);
        addImage(imagePanel, "/image/club21.jpg",2,10,gbc);
        addImage(imagePanel, "/image/club22.jpg",0,10,gbc);
        addImage(imagePanel, "/image/club23.jpg",1,10,gbc);
        addImage(imagePanel, "/image/club24.jpg",2,10,gbc);
        
        
        scroll.setViewportView(imagePanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getVerticalScrollBar().setBackground(new Color(248, 244, 235));
        scroll.getHorizontalScrollBar().setBackground(new Color(248, 244, 235));

       
        panelI.add(scroll,gbc);
//       add(scroll, "InfoPanel");
        add(panelI, "InfoPanel");
	}
	
	 private void addImage(JPanel panel, String imagePath, int gridX, int gridY, GridBagConstraints gbc) {
		ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
		Image originalImage = imageIcon.getImage();
		Image scaledImage = originalImage.getScaledInstance(225,300, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		
		JLabel imageLabel = new JLabel(imageIcon);
		gbc.weightx = 1.0; 
	    gbc.weighty = 1.0;
		gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.insets = new Insets(10,10, 10, 10);
        panel.add(imageLabel, gbc);
	}
	 
	
}
