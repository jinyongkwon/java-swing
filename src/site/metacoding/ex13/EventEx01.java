package site.metacoding.ex13;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EventEx01 extends JFrame {

	JPanel myPanel;
	JButton btn1, btn2;
	JCheckBox checkBox1;
	JLabel labelText, labelImg; // div

	public EventEx01() {
		initSetting();
		initObject();
		initAssign();
		initListener();

		setVisible(true);
	}

	private void initSetting() {
		setSize(300, 300);
		setLocationRelativeTo(null); // 프레임 화면 중앙 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼 클릭시 main 종료

	}

	private void initObject() {
		myPanel = new JPanel();
		btn1 = new JButton("버튼1");
		btn2 = new JButton("버튼2");
		checkBox1 = new JCheckBox();
		labelText = new JLabel("레이블1");
		// 내 프로젝트 위치부터 상대경로가 지정되어 있다.
		labelImg = new JLabel(new ImageIcon("image/dog.jpg"));
	}

	private void initAssign() {
		add(myPanel); // container class가 가지고 있는 add()를 실행.
		myPanel.add(btn1);
		myPanel.add(btn2);
		myPanel.add(checkBox1);
		myPanel.add(labelText);
		myPanel.add(labelImg);
	}

	private void initListener() {
		// 클릭하는지만 지켜보는걸로는 안되서 다른 리스너를 사용.
		checkBox1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				System.out.println(e.toString()); // statechange로 구분하는거 확인.
				System.out.println(e.getStateChange()); // 체크하면 1, 해제하면 2 확인.

				if (e.getStateChange() == 1) {
					System.out.println("체크 되었습니다.");
				} else {
					System.out.println("체크 해제 되었습니다.");
				}
			}
		});

		// 윈도우야 지켜봐줘!! 이 버튼을!!
		btn1.addActionListener(new ActionListener() {

			// 버튼이 클릭되면 윈도우가 해당 메서드를 콜백해준다.
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("버튼1이 클릭되었습니다.");
			}
		});

		btn2.addActionListener((ActionEvent e) -> {
			System.out.println("버튼2이 클릭되었습니다.");
		});
	}

	public static void main(String[] args) {
		new EventEx01();
	}

}
