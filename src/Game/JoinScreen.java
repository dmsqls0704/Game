package Game;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/** 
 * 
 * 회원가입 화면을 실행하는 클래스이다. 
 * 
 */
public class JoinScreen extends JPanel{
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JTextField nickname;
	private JTextField password;
	private DataManager dataManager;
	/**
	 * JoinScreen 클래스의 생성자이다.
	 * @param layout	CardLayout을 설정하는데 사용되는 객체
	 * @param panel 	CardLayout이 포함된 패널
	 */
	public JoinScreen(CardLayout layout, JPanel panel) {
		cardLayout = layout;
        cardPanel = panel;
        dataManager = new DataManager();
        
        setLayout(cardLayout);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        
        JPanel panelJ = new JPanel();
        panelJ.setLayout(new GridBagLayout());
        panelJ.setBackground(new Color(248,244,235));
        
        Font titleFont = Utility.yeongdeok_haeparang(50);
        Font font = Utility.yeongdeok_sea(40);
        
        /** 회원가입 화면임을 알려주는 제목을 생성한다. */
        JLabel titleJoin = new JLabel("회원가입");
        titleJoin.setFont(titleFont);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10,10,10,100);
        panelJ.add(titleJoin, gbc);
        
        /** 닉네임을 입력 받는 필드임을 알려주는 제목을 생성한다. */
        JLabel titleName = new JLabel("닉네임");
        gbc.gridy=1;
        titleName.setFont(font);
        gbc.insets = new Insets(10,10,10,100);
        panelJ.add(titleName, gbc);
        /** 닉네임을 입력 받을 수 있는 필드를 생성한다. */
        nickname = new JTextField(10);
        gbc.gridy=2;
        nickname.setFont(font);
        gbc.insets = new Insets(10,10,10,100);
        panelJ.add(nickname,gbc);
        
        /** 비밀번호를 입력 받는 필드임을 알려주는 제목을 생성한다. */
        JLabel titlePW = new JLabel("비밀번호");
        gbc.gridy=3;
        titlePW.setFont(font);
        gbc.insets = new Insets(10,10,10,100);
        panelJ.add(titlePW, gbc);
        /** 비밀번호를 입력 받을 수 있는 필드를 생성한다. */
        password = new JPasswordField(10);
        gbc.gridy=4;
        password.setFont(font);
        gbc.insets = new Insets(10,10,10,100);
        panelJ.add(password,gbc);
       
        Font fontB = Utility.yeongdeok_sea(30);
       
        JButton backButton = new RoundedButton("");
        backButton.setPreferredSize(new Dimension(80, 80));
        backButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(cardPanel,"startPanel");
        	}
        });
        backButton.setBackground(new Color(125, 159, 104));
        backButton.setBorder(BorderFactory.createLineBorder(new Color(80, 102, 67)));
    	UIManager.put("Button.focus", new ColorUIResource(new Color(125, 159, 104)));
    	
    	ImageIcon homeImage = new ImageIcon(getClass().getResource("/image/yongyonghome.png"));
        Image originalhomeImage = homeImage.getImage();
        Image scaledhomeImage = originalhomeImage.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        homeImage= new ImageIcon(scaledhomeImage);
        
        backButton.setIcon(homeImage);
        
    	gbc.anchor = GridBagConstraints.EAST;
    	gbc.gridx=1;
    	gbc.gridy=0;
    	gbc.insets = new Insets(10,10,10,10);
    	panelJ.add(backButton, gbc);
    	
        /** 확인 버튼을 생성하고, 입력한 닉네임과 비밀번호를 저장한다. */
        JButton checkButton = new RoundedButton("확인");
        checkButton.setPreferredSize(new Dimension(150, 50));
        checkButton.setFont(fontB);
        checkButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                if (nickname.getText().trim().isEmpty() || password.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(JoinScreen.this, "닉네임과 비밀번호를 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
                } 
                else {
                	if(dataManager.dataCheck(nickname.getText(),password.getText())) {
                		JOptionPane.showMessageDialog(JoinScreen.this, "이미 가입되었습니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
                		cardLayout.show(cardPanel, "startPanel");
                	}
                	else if(!dataManager.pwCheck(password.getText())){
                		JOptionPane.showMessageDialog(JoinScreen.this, "비밀번호는 4자리로 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
                		password.setText("");
                	}
                	else {
                        dataManager.saveData(nickname.getText(), password.getText());
                        dataManager.clear(nickname,password);
                        cardLayout.show(cardPanel, "startPanel");
                	}
                }  
        	}	
        });
        checkButton.setBackground(new Color(125, 159, 104));
        checkButton.setBorder(BorderFactory.createLineBorder(new Color(80, 102, 67)));
		UIManager.put("Button.focus", new ColorUIResource(new Color(125, 159, 104)));
		
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx=1;
		gbc.gridy=4;
		gbc.insets = new Insets(10,10,10,10);
		panelJ.add(checkButton, gbc);
    
        add(panelJ, "joinPanel");
	}
	
}
