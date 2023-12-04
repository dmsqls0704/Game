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

        panelF = new JPanel();

        panelF.setLayout(new GridBagLayout());
        panelF.setBackground(Utility.pointcolor);
        GridBagConstraints gbc = new GridBagConstraints();

        //윗 버튼을 인자로 준 경로의 이미지를 이용하여 생성
        topButton = new PauseFinishButton(buttonpath);
        gbc.gridy=1;
        gbc.insets = new Insets(0, 0, 10, 0);
        panelF.add(topButton,gbc);

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
        cardLayout.show(cardPanel, "mainPanel");
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

    public PausePage(CardLayout layout, JPanel panel, MainPage mainPage,Timer leveltimer,String panelname) {
        cardLayout = layout;
        cardPanel = panel;

        PauseFinishPage pause = new PauseFinishPage("엎어라 뒤집어라_Pause Page","/image/play.png", layout, panel, mainPage);

        // topButton_일시정지 화면일 때
        pause.gettopbutton().addActionListener(e -> {
            leveltimer.restart();
            cardLayout.show(cardPanel, panelname);
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

    public FinishPage(CardLayout layout, JPanel panel, MainPage mainPage,LoginScreen loginscreen, int finalscore) {
        cardLayout = layout;
        cardPanel = panel;

        PauseFinishPage finish = new PauseFinishPage("엎어라 뒤집어라_Finish Page", "/image/replay.png", cardLayout, cardPanel, mainPage);
        finishPanel = finish.getPausePanel();
        finishPanel.setBackground(Utility.pausefinishpagecolor);

        String scoreContent = String.valueOf(finalscore);

        // 패널에 내용 추가
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        Font scorefont = Utility.yeongdeok_haeparang(70);

        JLabel finishscore = new JLabel(scoreContent);
        finishscore.setForeground(Utility.maincolor);
        finishscore.setBackground(Utility.pausefinishpagecolor);
        finishscore.setFont(scorefont);

        finishPanel.add(finishscore, gbc);

        finish.gettopbutton().addActionListener(e -> {
            if(MainPage.level1.isSelected()){
                String[] userData = mainPage.loginscreen.getUser();
                CardMatchingEasy easy = new CardMatchingEasy(cardLayout, cardPanel, mainPage,loginscreen);
                cardPanel.add(easy, "easyPanel");
                cardLayout.show(cardPanel, "easyPanel");
            }else if (MainPage.level2.isSelected()) {
                String[] userData = mainPage.loginscreen.getUser();
                CardMatchingMedium medium = new CardMatchingMedium(cardLayout, cardPanel, mainPage, loginscreen);
                cardPanel.add(medium, "mediumPanel");
                cardLayout.show(cardPanel, "mediumPanel");
            } else if (MainPage.level3.isSelected()) {
                String[] userData = mainPage.loginscreen.getUser();
                CardMatchingHard hard = new CardMatchingHard(cardLayout, cardPanel, mainPage, loginscreen);
                cardPanel.add(hard, "hardPanel");
                cardLayout.show(cardPanel, "hardPanel");
            }
        });

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