package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 게임의 시간을 관리하는 클래스
 * @author hojeong
 */
public class Timers{
    /**프로그래바 너비*/
    private static final int BAR_WIDTH = 300;
    /**프로그래바 높이*/
    private static final int BAR_HEIGHT = 30;
    /**타이머의 초기 값을 저장하는 변수*/
    private int timerValue; // 초기 타이머 값 (초)
    /**게임 타이머*/
    private Timer timer;
    /**타이머의 시각적 표현을 위한 프로그래스바*/
    private JProgressBar progressBar;
    protected MainPage mainPage;
    protected LoginScreen loginscreen;
    private CardLayout cardLayout;
    private JPanel panel;
    private Score gameScore;
    /**시간이 다 된 후 추가하기 위한 gameover글씨*/
    private String gameovermessage;

    /**
     * Timers클래스의 생성자
     * @param timelimit 타이머의 초기값
     * @param gameScore Score클래스의 객체
     */
    public Timers(int timelimit,Score gameScore) {
        this.gameScore = gameScore;
        // 프로그레스 바 초기화
        progressBar = new JProgressBar(0, timelimit);
        progressBar.setValue(timelimit);
        progressBar.setBackground(Utility.basecolor);  // 타이머바 배경 색
        progressBar.setForeground(Utility.pointcolor); // 진행 상태 색깔
        progressBar.setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));// 타이머 사이즈, FlowLayout()이라서 이렇게 조정해야됨

        progressBar.setBorderPainted(false);

        timerValue=timelimit;

        // 타이머 설정
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerValue--;
                progressBar.setValue(timerValue);

                if (timerValue <= 0) {
                    timer.stop();
                    addfinishPanel(cardLayout, panel, mainPage,loginscreen,gameScore.getScore());
                    
                }
            }
        });

        timer.start();
    }

    /**
     * 타이머의 진행을 시각적으로 보이기 위해 프로그래스바를 반환하는 메소드
     * @return 해당 타이머 객체의 프로그래스바
     */
    public JProgressBar getProgressBar(){
        return this.progressBar;
    }

    /**
     * 타이머 시간을 멈추고 남은 시간을 반환하는 메소드
     * @return 타이머의 남은 시간을 반환
     */
    public int getTimerValue(){
        timer.stop();
        return this.timerValue;}

    /**타이머를 멈추는 메소드*/
    public void stopTimer(){
        timer.stop();
    }

    /**
     * 타이머 객체를 반환하는 메소드
     * @return 해당 게임의 타이머 객체
     */
    public Timer getThisTimer(){return this.timer;}

    /**
     * 타이머가 끝났을 때 뜨는 종료 화면을 관리하는 메소드
     * @param layout 종료화면의 레이아웃
     * @param panel 초기 패널
     * @param loginscreen 정보 저장을 위한 로그인화면 객체
     * @param finalscore 게임의 최종 점수
     */
    public void addfinishPanel(CardLayout layout, JPanel panel, MainPage mainPage,LoginScreen loginscreen,int finalscore) {

        cardLayout = layout;
        this.panel = panel;
        this.mainPage = mainPage;
        this.loginscreen=loginscreen;
        
        // 새로운 패널 생성
        FinishPage finishPanel = new FinishPage(layout, panel, mainPage,loginscreen,finalscore);

        // 기존 패널에 새로운 패널 추가
        panel.add(finishPanel.getFinishPanel(), "finishPanel");
        
        gameovermessage = "Game Over!";
        JLabel gameoverMessage = new JLabel(gameovermessage);
        gameoverMessage.setFont(Utility.yeongdeok_haeparang(15));
        gameoverMessage.setForeground(Utility.pointcolor);

        //GameOver메시지 뜸
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 3;
        finishPanel.getFinishPanel().add(gameoverMessage,gbc);
        
    // 카드를 새로운 패널로 전환
        cardLayout = (CardLayout) panel.getLayout();
        cardLayout.show(panel, "finishPanel");
    }
}

/**
 * 난이도 하의 타이머 객체를 생성하는 클래스
 * @author hojeong
 */
class Level1Timer extends Timers{
    public Level1Timer(Score gameScore){
        super(40,gameScore);
    }
}

/**
 * 난이도 중의 타이머 객체를 생성하는 클래스
 * @author hojeong
 */
class Level2Timer extends Timers{
    public Level2Timer(Score gameScore){
        super(100, gameScore);
    }
}

/**
 * 난이도 상의 타이머 객체를 생성하는 클래스
 * @author hojeong
 */
class Level3Timer extends Timers{
    public Level3Timer(Score gameScore){
        super(180, gameScore);
    }
}
