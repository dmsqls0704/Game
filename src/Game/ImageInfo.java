package Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class ImageInfo extends JLabel{
	private ImageIcon image;
	private String name;
	private String add;
	private String club;
	private Font namefont = Utility.yeongdeok_sea(25);
	private Font addfont = Utility.yeongdeok_sea(15);
	private JLabel textLabel;
	private JLabel clubLabel;
	private JLabel addLabel;
	
	public ImageInfo(ImageIcon image, String name) {
		super(image);
		this.image = image;
		this.name = name;
	   
		createComponents();
	}
	
	public ImageInfo(ImageIcon image, String name, String add) {
		super(image);
		this.image = image;
		this.name = name;
		this.add= add;
	   
		createComponentsAdd();	
	}
	
	private void createComponents() {
        setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel(image);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(imageLabel, BorderLayout.CENTER);

        textLabel = new JLabel(name);
        textLabel.setFont(namefont);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setForeground(Color.WHITE);
        textLabel.setOpaque(true);
        textLabel.setBackground(Utility.maincolor); // Adjust the alpha value for transparency
        textLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        add(textLabel, BorderLayout.SOUTH);
        textLabel.setVisible(true);
    }
	
	private void createComponentsAdd() {
        setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel(image);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(imageLabel, BorderLayout.CENTER);

        textLabel = new JLabel(name);
        textLabel.setFont(namefont);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setForeground(Color.WHITE);
        textLabel.setOpaque(true);
        textLabel.setBackground(Utility.maincolor);
        textLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        
        addLabel = new JLabel(add);
        addLabel.setFont(addfont);
        addLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addLabel.setForeground(Color.WHITE);
        addLabel.setOpaque(true);
        addLabel.setBackground(Utility.maincolor);
        addLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
    
        bottomPanel.setBackground(Utility.maincolor);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.insets = new Insets(0,0,5,0);
        bottomPanel.add(textLabel,gbc);

        gbc.gridy=1;
        gbc.insets = new Insets(0,0,0,0);
        bottomPanel.add(addLabel,gbc);

        add(bottomPanel, BorderLayout.SOUTH);

        textLabel.setVisible(true);
        addLabel.setVisible(true);
    } 
}
