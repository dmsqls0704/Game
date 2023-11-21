package Game;

import javax.swing.*;
import java.awt.*;

/**
 * 난이도를 선택하지 않고 게임시작 버튼을 클릭했을 때 Label객체를 생성하는 클래스
 */
class UnselectedMessage extends JLabel {
    /**이 객체에 적용할 폰트를 저장한 변수*/
    private Font unselectedMessageFont = Utility.yeongdeok_sea(18);

    /**
     * UnselectedMessage클래스의 생성자
     * 문구를 전달받아 해당 문구로 이루어진 Label객체를 생성
     * @param unselectedmessage 생성될 Label에 쓰일 문구를 매개변수로 전달받는다.
     */
    public UnselectedMessage(String unselectedmessage){
        super(unselectedmessage);
        setForeground(Utility.pointcolor);
        setFont(unselectedMessageFont);
    }
}
