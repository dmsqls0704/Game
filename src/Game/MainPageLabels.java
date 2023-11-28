package Game;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static Game.MainPage.leftPanel;

/**MainPage에 생성되는 Label객체를 생성하는 클래스
 * JLabel을 상속받음
 * MainPage의 Label들의 크기를 동일하게 함
 *
 */
public class MainPageLabels extends JLabel {
    /** MainPage의 Label과 같이 쓰이는 이미지파일 경로가 저장된 변수 */
    private final String imagepath = "/image/button.png";
    /** MainPage의 Label에 적용될 폰트가 저장된 변수*/
    private Font MainPageLabelfont = Utility.yeongdeok_haeparang(45);

    /**MainPage의 Label객체를 생성하는 MainPageLabels의 생성자
     *
     * @param text 각 객체의 Label에 쓰일 텍스트를 받음
     */
    public MainPageLabels(String text){
        super(text);
        try {
            Image imageFile = ImageIO.read(MainPage.class.getResource(imagepath));
            Image imgButton = imageFile.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            ImageIcon image_button = new ImageIcon(imgButton);
            setIcon(image_button);
            setHorizontalAlignment(SwingConstants.CENTER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setFont(MainPageLabelfont);
    }
}

/**
 *Label에 이벤트를 추가하는 클래스_게임시작 Label
 *해당 메소드는 게임시작 Label에 추가하는 이벤트를 정의해놓은 클래스이다.
 * MainPageLabels클래스를 상속받음
 */
class LabelStartEvent extends MainPageLabels {
    /**난이도를 선택하지 않고 해당 이벤트가 추가된 Label을 클릭할 시 나타날 문구를 저장한 변수*/
    public static UnselectedMessage message;

    private CardLayout cardLayout;
   	private JPanel cardPanel;
   	private MainPage mainPage;
//   	private LoginScreen loginScreen;
   	
    /**
     * LabelStartEvent 클래스의 생성자
     * @param text Label에 쓰일 텍스트를 매개변수로 받음
     */
    public LabelStartEvent(String text, CardLayout layout, JPanel panel, MainPage mainPage) {
        super(text);
        cardLayout = layout;
        cardPanel = panel;
        this.mainPage = mainPage;
    	
        addEventListenersUnselectedMessage();
    }

    /**Label에 추가할 이벤트를 묶어 놓은 메소드*/
    public void addEventListenersUnselectedMessage() {
        addMouseListener(new MouseAdapter() {
            //마우스로 클릭할시 발생하는 이벤트
            @Override
            public void mouseClicked(MouseEvent e) {
                //leftPanel에 있는 모든 컴포넌트 리스트로 묶음
                Component[] components = MainPage.leftPanel.getComponents();
                boolean containsMessage = false;

                for (Component component : components) {
                    if (component == message) {
                        containsMessage = true;
                        break;
                    }
                }
                // 라디오 버튼이 선택되지 않거나 message가 leftPanel에 있지 않는 경우 아래에 메시지 생성
                if (!MainPage.level1.isSelected() && !MainPage.level2.isSelected() && !MainPage.level3.isSelected() && !containsMessage) {
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridy = 2; // label_gameStart 아래에 위치하도록 함
                    gbc.anchor = GridBagConstraints.WEST;
                    gbc.insets = new Insets(-13, 80, 10, 0);
                    message = new UnselectedMessage("난이도를 선택해주세요.");
                    leftPanel.add(message, gbc);
                    leftPanel.revalidate();
                    leftPanel.repaint();
                }
                else if(MainPage.level1.isSelected()) {
                	String[] userData = mainPage.loginScreen.getUser();
    		        CardMatchingEasy easy = new CardMatchingEasy(cardLayout, cardPanel, mainPage, userData);
    				cardPanel.add(easy, "easyPanel");
    				cardLayout.show(cardPanel, "easyPanel");
                }
                else if(MainPage.level2.isSelected()) {
                	String[] userData = mainPage.loginScreen.getUser();
    		        CardMatchingMedium medium = new CardMatchingMedium(cardLayout, cardPanel, mainPage, userData);
    				cardPanel.add(medium, "mediumPanel");
    				cardLayout.show(cardPanel, "mediumPanel");
                }
                else if(MainPage.level3.isSelected()) {
                	String[] userData = mainPage.loginScreen.getUser();
    		        CardMatchingHard hard = new CardMatchingHard(cardLayout, cardPanel, mainPage, userData);
    				cardPanel.add(hard, "hardPanel");
    				cardLayout.show(cardPanel, "hardPanel");
                }
            }
        });
    }
}

/**
 *Label에 이벤트를 추가하는 클래스_설정 Label
 *해당 메소드는 설정 Label에 추가하는 이벤트를 정의해놓은 메소드이다.
 * MainPageLabels클래스를 상속받음
 */
class LabelSettingEvent extends MainPageLabels {
    /**
     * LabelSettingEvent 클래스의 생성자
     * @param text Label에 쓰일 텍스트를 매개변수로 받음
     */
    public LabelSettingEvent(String text) {
        super(text);
        addEventListeners();
    }

    /**
     * 해당 Label객체를 클릭하면 bgm을 제어할 수 있는 컴포넌트를 추가하는 메소드
     */
    public void addEventListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (MainPage.bgm_on.getParent() == null) {
                    GridBagConstraints gbc3 = new GridBagConstraints();
                    gbc3.gridx = 0;
                    gbc3.gridy = 4;
                    gbc3.anchor = GridBagConstraints.NORTH;
                    gbc3.insets = new Insets(0, 100, 0, 0);
                    leftPanel.add(MainPage.bgm_label, gbc3);

                    // bgm_on, bgm_off 버튼 추가
                    gbc3.anchor = GridBagConstraints.SOUTH;
                    gbc3.insets = new Insets(0, 105, 15, 0);
                    leftPanel.add(MainPage.bgm_on, gbc3);

                    gbc3.insets = new Insets(0, 190, 15, 0);
                    leftPanel.add(MainPage.bgm_off, gbc3);
                    leftPanel.revalidate();
                    leftPanel.repaint();
                }else{
                    leftPanel.remove(MainPage.bgm_on);
                    leftPanel.remove(MainPage.bgm_off);
                    leftPanel.remove(MainPage.bgm_label);
                    leftPanel.revalidate();
                    leftPanel.repaint();
                }
            }
        });
    }
}

