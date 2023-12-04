package Game;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

//레벨버튼 클래스

/**
 * 레벨 선택 버튼을 생성하는 클래스
 */
class LevelRadiobutton extends JRadioButton {
    /**레벨 선택 버튼에 적용될 기본폰트*/
    private Font radioButtonFont = Utility.yeongdeok_sea(18f);
    /**레벨을 선택하거나 마우스르 올렸을 때 적용되는 폰트*/
    private Font radioButtonScalFont = Utility.yeongdeok_sea(22f);
    //난이도 버튼 그룹 생성
    /**레벨 선택 버튼들의 그룹객체*/
    static ButtonGroup levelGroup = new ButtonGroup();

    /**
     * LevelRadiobutton클래스의 생성자
     * 객체의 폰트와 배경색을 지정하고 그룹에 포함시킨다.
     * @param text text를 매개변수로 받아 라디오버튼 생성
     */
    public LevelRadiobutton(String text) {
        super(text);
        // 라디오 버튼 글꼴
        setFont(radioButtonFont);
        setFocusPainted(false);
        //난이도 버튼 배경색
        setBackground(Utility.backcolor);
        //난이도 그룹에 라디오 버튼 추가
        levelGroup.add(this);

        addEventListeners();

    }
    //라디오 버튼 관련 이벤트들

    /**난이도 선택 버튼에 적용할 이벤트들을 묶어 놓은 메소드*/
    private void addEventListeners() {
        // 마우스 이벤트 처리
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // 마우스를 올렸을 때 bold체로 설정
                setFont(radioButtonScalFont);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // 마우스가 벗어났을 때 원래대로 설정
                ButtonModel model = getModel();
                if (!model.isSelected()) {
                    setFont(radioButtonFont);
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                setFont(radioButtonScalFont);
            }
        });

        // 이벤트 리스너 추가
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 클릭 시 글꼴을 bold체로 설정
                setFont(radioButtonScalFont);
                //난이도 선택시 문구 사라짐
                Component[] components = MainPage.leftPanel.getComponents();
                for (Component component : components) {
                    if (component == LabelStartEvent.message) {
                        MainPage.leftPanel.remove(LabelStartEvent.message);
                        MainPage.leftPanel.revalidate();
                        MainPage.leftPanel.repaint();
                        break;
                    }
                }
            }
        });

        // 라디오버튼 다른거 선택 시 글꼴을 원래대로 설정
        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!getModel().isSelected()) {
                    setFont(radioButtonFont);
                }
            }
        });
    }
}
