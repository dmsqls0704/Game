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
//    private JLabel timerLabel;

    public Timers(int timelimit) {
        // 프로그레스 바 초기화
        progressBar = new JProgressBar(0, timelimit);
        progressBar.setValue(timelimit);
        progressBar.setBackground(Utility.basecolor);  // 타이머바 배경 색
        progressBar.setForeground(Utility.pointcolor); // 진행 상태 색깔
        progressBar.setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));// 타이머 사이즈, FlowLayout()이라서 이렇게 조정해야됨
        //progressBar.setBorder(new RoundBorder(15));

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
                    //JOptionPane.showMessageDialog(, "Game Over!");
                }
//                } else {
//                    timerLabel.setText("Time: " + timerValue + " seconds");
//                }
            }
        });

        timer.start();
        // 시작 버튼 초기화
        //JButton startButton = new JButton("Start");
        //startButton.addActionListener(new ActionListener() {
        //    @Override
        //    public void actionPerformed(ActionEvent e) {
        //        timer.start();
        //    }
        //});

        //add(progressBar);
//        add(timerLabel);
        //add(startButton);
    }
    public JProgressBar getProgressBar(){
        return this.progressBar;
    }
}
class Level1Timer extends Timers{
    public Level1Timer(){
        super(30);
    }
}
class Level2Timer extends Timers{
    public Level2Timer(){
        super(90);
    }
}
class Level3Timer extends Timers{
    public Level3Timer(){
        super(180);
    }
}

