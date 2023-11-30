package Game;

import javax.swing.*;

//import static Game.MainPage.leftPanel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

/** mainpage를 만드는 클래스
 * main메소드를 포함하고 있는 클래스로 frame을 생성함
 *
 * @author hojeong kang
 */
public class MainPage extends JPanel {
    /**mainpage에서 왼쪽 Panel객체*/
    public static JPanel leftPanel;
//    static LabelStartEvent label_gameStart;
//    MainPageLabels label_infoPage;
    /**난이도 하에 해당하는 버튼*/
    static LevelRadiobutton level1;
    /**난이도 중에 해당하는 버튼*/
    static LevelRadiobutton level2;
    /**난이도 상에 해당하는 버튼*/
    static LevelRadiobutton level3;
    static JLabel bgm_label;
    /**bgm을 재생할 수 있는 버튼*/
    static OnOffButton bgm_on;
    /**bgm을 멈출 수 있는 버튼*/
    static OnOffButton bgm_off;
    private int laptopWidth;
    private int laptopHeight;

    private CardLayout cardLayout;
	private JPanel cardPanel;
	protected LoginScreen loginScreen;
	
	
    /**MainPage클래스의 생성자
     *
     * @param titlename frame의 title에 해당하는 변수
     */
    public MainPage(CardLayout layout, JPanel panel,LoginScreen loginScreen) {
        cardLayout = layout;
	    cardPanel = panel;
	    this.loginScreen = loginScreen;
    		
		setLayout(cardLayout);
		Utility.playMusic();

		JPanel panelM = new JPanel();
		panelM.setLayout(new GridBagLayout());
		panelM.setBackground(Utility.backcolor);
		
        // 메인페이지 왼쪽 panel
        leftPanel = new JPanel();
        leftPanel.setBackground(Utility.backcolor);
        leftPanel.setLayout(new GridBagLayout()); // GridBagLayout을 사용
        GridBagConstraints gbc = new GridBagConstraints();
    
        //난이도 선택 라디오 버튼
        level1 = new LevelRadiobutton("하");
        level2 = new LevelRadiobutton("중");
        level3 = new LevelRadiobutton("상");

        gbc.gridx = 0;
        gbc.gridy=0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 70, 0, 0);
        leftPanel.add(level1, gbc);
        gbc.insets = new Insets(0, 130, 0, 0);
        leftPanel.add(level2, gbc);
        gbc.insets = new Insets(0, 190, 0,0);
        leftPanel.add(level3, gbc);

        //게임 시작 label
        LabelStartEvent label_gameStart = new LabelStartEvent("게임 시작", layout, panel, MainPage.this);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        leftPanel.add(label_gameStart, gbc);
       
        

        //정보열람 label
        MainPageLabels label_infoPage = new MainPageLabels("정보 열람");
        gbc.gridy = 3;
        leftPanel.add(label_infoPage, gbc);
        
        label_infoPage.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e) {
    		        String[] userData = loginScreen.getUser();
    		        CardInfo cardInfo = new CardInfo(cardLayout, cardPanel, MainPage.this, userData);
    				cardPanel.add(cardInfo, "infoPanel");
    				cardLayout.show(cardPanel, "infoPanel");
        	}
        });
        
        //설정 label
        LabelSettingEvent label_setting = new LabelSettingEvent("설정");
        gbc.gridy = 4;
        leftPanel.add(label_setting, gbc);
        addBgmControls();

        GridBagConstraints gbc2 = new GridBagConstraints();
       

        gbc2.weightx = 1.0; // 다른 컴포넌트와의 상대적인 크기 비율 조정
        gbc2.weighty = 1.0;
        gbc2.fill = GridBagConstraints.BOTH; // 두 가지 방향으로 확장
       
        panelM.add(leftPanel, gbc2);
        createRightPanel(panelM, gbc2);
        leftPanel.revalidate();
        leftPanel.repaint();
	    add(panelM, "mainPanel");
        setVisible(true);
    }

    /**frame의 크기를 지정하고 아이콘을 변경하는 메소드
     *프레임의 크기는 사용자의 모니터 크기의 비율에 맞춰 지정한다.
     *
     */
//    private void initializeFrame() {
//        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        Dimension screenSize = toolkit.getScreenSize();
//        laptopWidth = (int) (screenSize.getWidth() * 0.7);
//        laptopHeight = (int) (screenSize.getHeight() * 0.8);
//
//        // title 아이콘 변경
//        setIconImage(loadIconImage());
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(laptopWidth, laptopHeight);
//        Container contentPane = getContentPane();
//        contentPane.setLayout(new GridBagLayout());
//    }

    /**frame 아이콘의 사진을 불러오는 메소드
     *
     * @return 프레임 아이콘에 대한 불러온 이미지
     * @throws RuntimeException 이미지를 찾을 수 없거나 로딩 중 I/O오류가  발생한 경우 처리
     */
//    private Image loadIconImage() {
//        try {
//            return ImageIO.read(MainPage.class.getResource("../image/logo.jpg"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    /**bgm을 제어할 수 있는 객체를 생성하는 메소드
     * 이 메소드는 bgm이라는 걸 알 수 있는 Label과 bgm을 제어할 수 있는 버튼을 생성한다.
     *폰트와 색은 utility 클래스의 객체를 사용한다.
     *
     */
    private void addBgmControls() {
        bgm_label = new JLabel("bgm");
        Font font_bgmLabel = Utility.yeongdeok_sea(19);
        bgm_label.setFont(font_bgmLabel);

        bgm_on = new OnOffButton("On", Utility.maincolor);
        bgm_off = new OnOffButton("Off", Utility.basecolor);

        bgm_on.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 버튼을 클릭했을 때 배경색을 변경합니다.
            	Utility.startMusic();
                bgm_on.setBackground(Utility.maincolor); // 색 변경
                bgm_off.setBackground(Utility.basecolor);
            }
        });

        bgm_off.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 버튼을 클릭했을 때 배경색을 변경합니다.
            	Utility.stopMusic();
                bgm_off.setBackground(Utility.maincolor); // 색 변경
                bgm_on.setBackground(Utility.basecolor);
            }
        });
    }

    /**mainpage의 오른쪽 Panel객체를 생성함
     * 만들어진 오른쪽 Panel객체는 addLogoImage메소드에 매개변수로 전달한다.
     *
     */
    private void createRightPanel(JPanel panel, GridBagConstraints gbc) {
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Utility.backcolor);
        rightPanel.setLayout(new GridBagLayout());

        // 이미지 추가
        addLogoImage(rightPanel);

        panel.add(rightPanel, gbc);
    }

    /**매개변수로 전달받은 Panel 객체에 이미지 삽입
     *
     * @param rightPanel createRightPanel()에서 생성된 panel객체
     */
    private void addLogoImage(JPanel rightPanel) {
        try {
            Image main_page_logo = ImageIO.read(MainPage.class.getResource("/image/yongyong1.png"));
            Image img_logo_scal = main_page_logo.getScaledInstance(370, 400, Image.SCALE_SMOOTH);
            ImageIcon img_logo = new ImageIcon(img_logo_scal);
            JLabel main_logo = new JLabel(img_logo);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            rightPanel.add(main_logo, gbc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
