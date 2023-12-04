package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * 일시정지 화면과 종료 화면에 만들어질 버튼 객체를 생성하는 클래스
 * 일시정지 화면과 종료 화면에 생성되는 버튼의 모양이 똑같아서 하나의 클래스로 정의함
 * @author hojeong
 */
class PauseFinishButton extends JButton {
    /**버튼의 가로 길이 저장한 변수*/
    private final int buttonwidth = 200;
    /**버튼의 세로 길이 저장한 변수*/
    private final int buttonheight = 50;
    /**버튼의 아이콘에 쓰일 아이콘 객체*/
    private ImageIcon buttonIcon;

    /**
     * PauseFinishButton클래스의 생성자
     * 매개변수로 받은 경로로 아이콘을 저장하고 버튼을 생성
     * @param imagepath 버튼 아이콘에 쓰일 이미지파일의 경로를 매개변수로 받음
     */
    public PauseFinishButton(String imagepath){
        setPreferredSize(new Dimension(buttonwidth,buttonheight)); // 크기 조절
        setRolloverEnabled(false);
        setFocusPainted(false);

        // 이미지 파일을 읽어와서 ImageIcon으로 설정
        try {
            buttonIcon = new ImageIcon(ImageIO.read(MainPage.class.getResource(imagepath)));
            setIcon(buttonIcon);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //버튼 커스텀
    /**버튼의 모양을 둥글게 만들기 위한 메소드*/
    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        Graphics2D graphics = (Graphics2D) g;

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        setBackground(Utility.maincolor);

        //버튼 눌러진 상태
        if (getModel().isArmed()) {
            graphics.setColor(Utility.pointcolor);
        }
        //버튼 올려진 상태
        else if (getModel().isRollover()) {
            graphics.setColor(Utility.maincolor);
        } else {
            graphics.setColor(getBackground());
        }

        //가로세로 길이만큼 20의 둥글기 가지는 배경
        graphics.fillRoundRect(0, 0, width, height, 20, 20);

        Icon icon = getIcon();
        if (icon != null) {
            int iconX = (width - icon.getIconWidth()) / 2;
            int iconY = (height - icon.getIconHeight()) / 2;
            icon.paintIcon(this, graphics, iconX, iconY);
        }

        graphics.dispose();
    }
}