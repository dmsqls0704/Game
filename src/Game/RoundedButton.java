package Game;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 버튼을 둥글게 만들기 위한 클래스
 */
public class RoundedButton extends JButton {
    /**
     * RoundedButton클래스 생성자
     * @param text 버튼의 써질 글자
     */
    public RoundedButton(String text) {
        super(text);

        // 둥근 버튼 모양을 위해 UI 설정
        setUI(new BasicButtonUI());
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);

        // 마우스 이벤트 처리
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                // 마우스가 버튼 위에 올라갔을 때의 처리
                setBackground(new Color(105, 184, 109));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                // 마우스가 버튼에서 나갔을 때의 처리
                setBackground(new Color(125, 159, 104));
            }
        });
    }

    /**
     * 버튼을 둥글게 만들기 위한 메소드
     * @param g 그래픽 객체
     */
    // 둥근 버튼을 그리기 위한 paintComponent 메소드 오버라이드
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            // 마우스 버튼을 누르고 있을 때의 처리
            g.setColor(Utility.pointcolor);
        } else {
            // 기본 색상
            g.setColor(Utility.maincolor);
        }

        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 둥글게 만들기
        graphics.fillRoundRect(0, 0, width, height, 20, 20);

        super.paintComponent(g);
    }
}