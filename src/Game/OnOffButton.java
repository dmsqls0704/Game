package Game;

import javax.swing.*;
import java.awt.*;

/**
 * bgm을 제어할 수 있는 버튼객체를 생성하는 클래스
 */
class OnOffButton extends JButton {
    /**bgm 제어 버튼에 쓰일 폰트 저장한 변수*/
    private Font onOffFont = Utility.yeongdeok_sea(12f);
    /**bgm제어 버튼을 묶을 그룹 객체 생성*/
    private ButtonGroup bgmGroup = new ButtonGroup();

    /**
     * OnOffButton클래스의 생성자
     * bgm제어 버튼(on,off) 객체를 생성하는 메소드이다.
     * @param buttonText 버튼에 쓰일 text 받은 매개변수
     * @param onOffBakColor 버튼의 배경색 받은 매개변수
     */
    public OnOffButton(String buttonText,Color onOffBakColor){
        super(buttonText);
        setRolloverEnabled(false);
        setFocusPainted(false);
        setBackground(onOffBakColor);
        setFont(onOffFont);

        bgmGroup.add(this);
    }
}