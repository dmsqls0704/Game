package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/** 전체 게임 실행을 담당하는 클래스 */ 
public class Screen
{
	public static void main(String[] args)
	{
		GameScreen screen = new GameScreen();
		
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setVisible(true);
	}
}

/** 게임 시작 화면을 실행하는 클래스이다. */
class GameScreen extends JFrame {
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private int laptopWidth;
    private int laptopHeight;
    /**버튼의 가로 길이 저장한 변수*/
    private int buttonwidth;
    /**버튼의 세로 길이 저장한 변수*/
    private int buttonheight;

    /**
     * GameScreen클래스의 생성자이다.
     */
    public GameScreen() {
    	/** 전체 게임이름 설정 */
        setTitle("엎어라 뒤집어라");
        /** 게임 프레임 크기 설정 */
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        laptopWidth = (int) (screenSize.getWidth() * 0.7);
        laptopHeight = (int) (screenSize.getHeight() * 0.8);
        
        setSize(laptopWidth, laptopHeight);
        setResizable(false);
        setLocationRelativeTo(null);


        buttonwidth = (int)(laptopWidth*0.25);
        buttonheight = (int)(laptopHeight*0.07);
        /** 게임 아이콘 변경 */
        try {
            Image iconImage = ImageIO.read(Screen.class.getResource("/image/logo.jpg"));
            setIconImage(iconImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new GridBagLayout());
        
        startPanel.setBackground(new Color(248,244,235));
        
        /** 게임 제목 생성 */
        JLabel title1 = new JLabel("엎어라");
        JLabel title2 = new JLabel("뒤집어라");
        title1.setHorizontalAlignment(SwingConstants.CENTER);
        title2.setHorizontalAlignment(SwingConstants.CENTER);
        /** 게임 제목 폰트 설정 */
        Font titleFont = Utility.yeongdeok_haeparang(90);
        title1.setFont(titleFont);
        title2.setFont(titleFont);
        
        /** 타이틀 이미지 생성 */
        ImageIcon titleI = new ImageIcon(getClass().getResource("/image/yongyong3.png"));
        Image originaltitleImage = titleI.getImage();
        Image scaledtitleImage = originaltitleImage.getScaledInstance(200,200, Image.SCALE_SMOOTH);
        titleI = new ImageIcon(scaledtitleImage);

        JLabel titleImage = new JLabel(titleI); 
        gbc.insets = new Insets(10,400,200,50);
        startPanel.add(titleImage, gbc);
        
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.insets = new Insets(10,10,100,150);
        startPanel.add(title1, gbc);
        gbc.insets = new Insets(150,100,10,10);
        startPanel.add(title2, gbc);

        Font font = Utility.yeongdeok_sea(30);
        
        /** 로그인 버튼을 생성 */
        JButton loginButton = new RoundedButton("로그인");
        loginButton.setPreferredSize(new Dimension(buttonwidth, buttonheight));
        loginButton.setFont(font);
        /** 버튼을 누르면 로그인 화면으로 넘어가도록 한다.*/
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "loginPanel");
            }
        });
        /** 로그인 버튼에 마우스 커서를 올렸을 때 타이틀 이미지가 변경되도록 한다. */
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
        	public void mouseEntered(java.awt.event.MouseEvent e) {
        		ImageIcon changeI = new ImageIcon(getClass().getResource("/image/yongyong4.png"));
        		Image originalchangeImage = changeI.getImage();
        		Image scaledchangeImage = originalchangeImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        		changeI = new ImageIcon(scaledchangeImage);
        		titleImage.setIcon(changeI);
        	}
        	public void mouseExited(java.awt.event.MouseEvent e) {
        		ImageIcon previousI = new ImageIcon(getClass().getResource("/image/yongyong3.png"));
        		Image originalpreviousImage = previousI.getImage();
        		Image scaledpreviousImage = originalpreviousImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        		previousI = new ImageIcon(scaledpreviousImage);
        		titleImage.setIcon(previousI);
        	}
        });
        
        loginButton.setBackground(new Color(125, 159, 104));
		loginButton.setBorder(BorderFactory.createLineBorder(new Color(80, 102, 67)));
		UIManager.put("Button.focus", new ColorUIResource(new Color(125, 159, 104)));
		
	/** 회원가입 버튼을 생성 */
        JButton joinButton = new RoundedButton("회원가입");
        joinButton.setPreferredSize(new Dimension(buttonwidth, buttonheight));
        joinButton.setFont(font);
        joinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {           	
            	cardLayout.show(cardPanel, "joinPanel");
          }
        });
        /** 버튼을 누르면 화면가입 화면으로 넘어가도록 한다. */
        joinButton.addMouseListener(new java.awt.event.MouseAdapter() {
        	public void mouseEntered(java.awt.event.MouseEvent e) {
        		ImageIcon changeI = new ImageIcon(getClass().getResource("/image/yongyong4.png"));
        		Image originalchangeImage = changeI.getImage();
        		Image scaledchangeImage = originalchangeImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        		changeI = new ImageIcon(scaledchangeImage);
        		titleImage.setIcon(changeI);
        	}
        	public void mouseExited(java.awt.event.MouseEvent e) {
        		ImageIcon previousI = new ImageIcon(getClass().getResource("/image/yongyong3.png"));
        		Image originalpreviousImage = previousI.getImage();
        		Image scaledpreviousImage = originalpreviousImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        		previousI = new ImageIcon(scaledpreviousImage);
        		titleImage.setIcon(previousI);
        	}
        });
        
        joinButton.setBackground(new Color(125, 159, 104));
		joinButton.setBorder(BorderFactory.createLineBorder(new Color(80, 102, 67)));
		UIManager.put("Button.focus", new ColorUIResource(new Color(125, 159, 104)));
		
		gbc.gridwidth = 1;
		gbc.gridy=1;
		gbc.insets = new Insets(10,10,10,10);
		startPanel.add(loginButton, gbc);
        
        gbc.gridy=2;
        gbc.insets = new Insets(10,10,10,10);
        startPanel.add(joinButton, gbc);

        add(cardPanel);
        cardPanel.add(startPanel, "startPanel");

        LoginScreen loginScreen = new LoginScreen(cardLayout, cardPanel);
        cardPanel.add(loginScreen, "loginPanel");

        JoinScreen joinScreen = new JoinScreen(cardLayout, cardPanel);
        cardPanel.add(joinScreen, "joinPanel");
        
//        cardLayout.show(cardPanel, "startPanel");
    }
   
}
