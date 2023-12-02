package Game;

import java.io.*;
import javax.swing.*;

/**
 * 
 * 로그인, 회원가입 시 유저의 정보를 저장하고 확인하는 클래스이다.
 *
 */
public class DataManager{
	private static final String fileName = "user_data.txt";

	/**
	 * 입력한 닉네임와 비밀번호를 저장해주는 메소드이다.
	 * @param nickname	유저 닉네임
	 * @param password	유저 비밀번호
	 */
	public void saveData(String nickname, String password) {
		
	try (FileWriter fileWriter = new FileWriter(fileName, true);
	    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	    PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
	            printWriter.println(nickname);
	            printWriter.println(password);
	            printWriter.println("0");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	/**
	 * 필드에 입력한 값을 지워주는 메소드이다.
	 * @param nicknameField	닉네임을 입력하는 필드
	 * @param passwordField 비밀번호를 입력하는 필드
	 */
	public void clear(JTextField nicknameField, JTextField passwordField) {
        nicknameField.setText("");
        passwordField.setText("");
    }
	/**
	 * 입력한 닉네임과 비밀번호와 일치하는 데이터가 있는지 확인해주는 메소드이다.
	 * @param inputN	입력받은 닉네임
	 * @param inputP	입력받은 비밀번호
	 * @return			일치하는 데이터가 있으면 true, 없으면 false
	 */
	public boolean dataCheck(String inputN, String inputP) {
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			String line;
			while((line = reader.readLine()) != null) {
				String dataName = line;
				String dataPW = reader.readLine();
				reader.readLine();
				
				if(inputN.equals(dataName) && inputP.equals(dataPW)) return true;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 비밀번호가 4자리로 입력되었는지 확인하는 메소드이다.
	 * @param inputP	입력받은 비밀번호
	 * @return			비밀번호가 4자리라면 true, 아니라면 false
	 */
	public boolean pwCheck(String inputP) {
		if(inputP.length()==4) {return true;}
		else {return false;}
		
	}
	/**
	 * 현재 유저의 데이터를 가져오는 메소드이다. 
	 * @param inputN	가져올 유저의 닉네임
	 * @return			유저의 닉네임과 점수를 담은 배열 반환
	 */
	public String[] getData(String inputN) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String dataName = line;
                String dataPW = reader.readLine();
                String score = reader.readLine();

                if (inputN.equals(dataName)) {
                    return new String[]{dataName,dataPW, score};
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; 
    }
	public void saveScore(String nickname, String pw, String newScore) {
	    try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
	         BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt"))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String dataName = line;
	            String dataPW = reader.readLine();
	            String score = reader.readLine();

	            if (nickname.equals(dataName)) {
	                // 특정 사용자의 점수를 업데이트합니다
	                writer.write(dataName + "\n" + dataPW + "\n" + newScore + "\n");
	            } else {
	                writer.write(dataName + "\n" + dataPW + "\n" + score + "\n");
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // 임시 파일을 원래 파일 이름으로 변경합니다
	    File originalFile = new File(fileName);
	    File tempFile = new File("temp.txt");
	      if (originalFile.delete() && tempFile.renameTo(originalFile)) {
	    } else {
	    }
	}
	
}
