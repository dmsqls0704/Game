package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

/**
 * 일시정지화면과 종료화면을 Panel로 만드는 클래스
 */
class PauseFinishPage extends JPanel {
//    private JPanel pauseFinishPanel;
    /**Panel에서 만들어진 버튼 중 위에 해당하는 객체*/
    private PauseFinishButton topButton;
    /**Panel에서 만들어진 홈버튼 객체*/
    private PauseFinishButton homeButton;
    private JPanel panelF;

    public CardLayout cardLayout;
    public JPanel cardPanel;
    public MainPage mainPage;

    /**
     * PauseFinishPage클래스의 생성자
     * 객체의 크기와 배경색을 지정하고 버튼 객체를 생성을 한다.
     * @param buttonpath 버튼에 놓일 이미지 경로를 매개변수로 받음
     */
    public PauseFinishPage(String titlename, String buttonpath, CardLayout layout, JPanel panel, MainPage mainPage) {

        cardLayout = layout;
        cardPanel = panel;

        //타이틀 아이콘 변경
//        try {
//            // 이미지 파일을 읽어와서 아이콘 이미지로 설정
//            Image iconImage = ImageIO.read(MainPage.class.getResource("../image/logo.jpg"));
//            setIconImage(iconImage);
//        } catch (Exception e) {
//            // 예외 처리
//            e.printStackTrace();
//        }
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        Container contentPane = getContentPane();
//        contentPane.setLayout(new BorderLayout()); // GridBagLayout을 사용
//        setLayout(cardLayout);

        panelF = new JPanel();

        panelF.setLayout(new GridBagLayout());
//        pauseFinishPanel = new JPanel();
//        panelF.setBackground(Utility.pausefinishpagecolor);
//        add(pauseFinishPanel, BorderLayout.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();

        //윗 버튼을 인자로 준 경로의 이미지를 이용하여 생성
        topButton = new PauseFinishButton(buttonpath);
        gbc.gridy=1;
        gbc.insets = new Insets(0, 0, 10, 0);
        panelF.add(topButton,gbc);
//        // topButton 액션 리스너 내에 패널 전환 로직 추가
//        topButton.addActionListener(e -> {
//                cardLayout.show(cardPanel, "easyPanel");
//        });


        homeButton = new PauseFinishButton("/image/homebutton.png");
        gbc.gridy=2;
        //homebutton 이벤트 적용
        homeButton.addActionListener(e -> openNewFrame());
        panelF.add(homeButton,gbc);


        add(panelF,"pausefinishPage");
        setVisible(true);
    }
    //객체의 Panel반환

    /**
     * 객체 자신을 반환하는 메소드
     * @return 객체 자신을 반환
     */
    public JPanel getPausePanel(){
        return this.panelF;
    }

    public JButton gettopbutton(){return this.topButton;}

    /**
     * homebutton에 적용되는 이벤트 메소드
     * 이벤트가 발생할 시 새로운 프레임이 생성된다.
     */
    private void openNewFrame() {
        //현재 객체의 부모요소 찾기
//        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
//        currentFrame.setVisible(false);
        // 새로운 프레임 생성
//        MainPage homeFrame = new MainPage ("엎어라 뒤집어라_Main Page");
        cardLayout.show(cardPanel, "mainPanel");

        // 현재 프레임 닫기
//        WindowEvent windowClosing = new WindowEvent((Window) SwingUtilities.getWindowAncestor(this), WindowEvent.WINDOW_CLOSING);
//        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(windowClosing);

        // 현재 프레임을 닫지 않고 숨기기만 하기 때문에 새로운 프레임을 닫을 때의 동작을 정의해줍니다.
//        homeFrame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                // 새로운 프레임이 닫힐 때 현재 프레임을 다시 보이게 합니다.
//                PauseFinishPage.this.setVisible(true);
//            }
//        });
    }
}

/**일시정지 Panel 객체와 관련된 클래스*/
class PausePage {
    /**일시정지화면 Panel 객체를 저장하는 변수*/
    private JPanel pausePanel;
    /**PausePage의 생성자로 Panel객체를 생성한다.
     *PauseFinishPage클래스로 생성된 객체를 받아 변수에 저장
     */

    public CardLayout cardLayout;
    public JPanel cardPanel;
    public MainPage mainPage;

    public PausePage(CardLayout layout, JPanel panel, MainPage mainPage,Timer leveltimer,String panelName) {
        cardLayout = layout;
        cardPanel = panel;

        PauseFinishPage pause = new PauseFinishPage("엎어라 뒤집어라_Pause Page","/image/play.png", layout, panel, mainPage);

        // topButton_일시정지 화면일 때
        pause.gettopbutton().addActionListener(e -> {
            leveltimer.restart();
            cardLayout.show(cardPanel, panelName);
        });


        pausePanel = pause.getPausePanel();
        pausePanel.setBackground(Utility.pausefinishpagecolor);
    }

    /**
     * 객체의 생성된 Panel을 반환하는 메소드
     * @return 자기자신의 Panel반환
     */
    public JPanel getPausePanel(){
        return this.pausePanel;
    }
}
/**종료 Panel 객체와 관련된 클래스*/
class FinishPage{
    /**종료화면 Panel 객체를 저장하는 변수*/
    private JPanel finishPanel;
    /**FinishPage의 생성자로 Panel객체를 생성한다.
     *PauseFinishPage클래스로 생성된 객체를 받아 변수에 저장
     *또한 파일에 저장된 정보를 불러와 Panel에 추가하는 메소드
     */
    public CardLayout cardLayout;
    public JPanel cardPanel;
    public MainPage mainPage;

    public FinishPage(CardLayout layout, JPanel panel, MainPage mainPage,int finalscore) {
        cardLayout = layout;
        cardPanel = panel;

        PauseFinishPage finish = new PauseFinishPage("엎어라 뒤집어라_Finish Page", "/image/replay.png", cardLayout, cardPanel, mainPage);
        finishPanel = finish.getPausePanel();
        finishPanel.setBackground(Utility.pausefinishpagecolor);

        // 여기서 패널을 투명하게 설정
        finishPanel.setOpaque(false);

//        File infofile = new File("user_data.txt");
//        String scoreContent = "";
        String scoreContent = String.valueOf(finalscore);

//        try (BufferedReader br = new BufferedReader(new FileReader(infofile))) {
//            String line;
//            int currentLine = 1;
//
//            while ((line = br.readLine()) != null) {
//                if (currentLine == 3) {
//                    // 특정 줄의 내용을 출력하거나 필요한 작업 수행
//                    scoreContent = line;
//                    System.out.println(line);
//                    break; // 찾았으면 반복문 종료
//                }
//                currentLine++;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 패널에 내용 추가
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        Font scorefont = Utility.yeongdeok_haeparang(70);

        JLabel finishscore = new JLabel(scoreContent);
        finishscore.setForeground(Utility.maincolor);
        finishscore.setBackground(Utility.pausefinishpagecolor);
        finishscore.setFont(scorefont);

        finishPanel.add(finishscore, gbc);

        if(MainPage.level1.isSelected()){
            // topButton_종료 화면일 때
            finish.gettopbutton().addActionListener(e -> {
                String[] userData = mainPage.loginScreen.getUser();
                CardMatchingEasy easy = new CardMatchingEasy(cardLayout, cardPanel, mainPage, userData);
                cardPanel.add(easy, "easyPanel");
                cardLayout.show(cardPanel, "easyPanel");
            });
        }else if (MainPage.level2.isSelected()) {
            finish.gettopbutton().addActionListener(e -> {
                String[] userData = mainPage.loginScreen.getUser();
                CardMatchingMedium medium = new CardMatchingMedium(cardLayout, cardPanel, mainPage, userData);
                cardPanel.add(medium, "mediumPanel");
                cardLayout.show(cardPanel, "mediumPanel");
            });
        } else if (MainPage.level3.isSelected()) {
            finish.gettopbutton().addActionListener(e -> {
                String[] userData = mainPage.loginScreen.getUser();
                CardMatchingHard hard = new CardMatchingHard(cardLayout, cardPanel, mainPage, userData);
                cardPanel.add(hard, "hardPanel");
                cardLayout.show(cardPanel, "hardPanel");
            });
        }


        cardPanel.add(finishPanel, "finishPanel");

        // CardLayout을 이용하여 cardPanel에 finishPanel을 추가
        cardLayout.addLayoutComponent(finishPanel, "finishPanel");
    }

    /**
     * 객체의 생성된 Panel을 반환하는 메소드
     * @return 자기자신의 Panel반환
     */
    public JPanel getFinishPanel(){
        return this.finishPanel;
    }


}