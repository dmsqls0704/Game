package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timers{
    private static final int BAR_WIDTH = 300;
    private static final int BAR_HEIGHT = 30;

    private int timerValue; // 초기 타이머 값 (초)
    private Timer timer;

    private JProgressBar progressBar;

    protected MainPage mainPage;
    protected LoginScreen loginscreen;
    private CardLayout cardLayout;
    private JPanel panel;
    private Score gameScore;
    private String gameovermessage;

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
    public JProgressBar getProgressBar(){
        return this.progressBar;
    }
    public int getTimerValue(){
        timer.stop();
        return this.timerValue;}

    public void stopTimer(){
        timer.stop();
    }
    public Timer getThisTimer(){return this.timer;}
    public void addfinishPanel(CardLayout layout, JPanel panel, MainPage mainPage,LoginScreen loginscreen,int finalscore) {

        cardLayout = layout;
        this.panel = panel;
        this.mainPage = mainPage;
        
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
class Level1Timer extends Timers{
    public Level1Timer(Score gameScore){
        super(40,gameScore);
    }
}
class Level2Timer extends Timers{
    public Level2Timer(Score gameScore){
        super(100, gameScore);
    }
}
class Level3Timer extends Timers{
    public Level3Timer(Score gameScore){
        super(180, gameScore);
    }
}

