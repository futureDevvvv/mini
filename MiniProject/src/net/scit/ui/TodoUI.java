package net.scit.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.scit.dao.TodoDAO;
import net.scit.dao.UserDAO;
import net.scit.vo.TodoVO;
import net.scit.vo.UserVO;

public class TodoUI extends JFrame {

	TodoDAO todoDao = new TodoDAO();
	UserDAO userDao = new UserDAO();

	List<UserVO> memberList = userDao.memberList(null);
	Vector<String> memVector = new Vector<String>();

	/**
	 * serialVersion
	 */
	private static final long serialVersionUID = 2721396482567223817L;

	TodoUI() {
		Container c = getContentPane();
		c.setLayout(null);

		// 창 닫기 버튼 클릭 시 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lb1 = new JLabel("조회 범위");
		lb1.setBounds(20, 20, 100, 30);
		c.add(lb1);

		// 라디오버튼 생성
		JRadioButton rd1 = new JRadioButton("개인별");
		JRadioButton rd2 = new JRadioButton("팀별");

		rd1.setBounds(100, 20, 70, 30);
		rd2.setBounds(170, 20, 80, 30);

		// 1번 라디오 버튼 눌려져 있도록
		rd1.setSelected(true);

		// 라디오 버튼을 그룹화하기 위한 객체 생성
		ButtonGroup groupRd = new ButtonGroup();

		// 그룹에 라디오 버튼 포함
		groupRd.add(rd1);
		groupRd.add(rd2);

		c.add(rd1);
		c.add(rd2);

		JLabel lb2 = new JLabel("진행도별");
		lb2.setBounds(20, 50, 100, 30);
		c.add(lb2);

		// 체크박스 생성
		JCheckBox chk1 = new JCheckBox("진행전", true);
		JCheckBox chk2 = new JCheckBox("진행중", true);
		JCheckBox chk3 = new JCheckBox("완료", true);

		chk1.setBounds(100, 50, 70, 30);
		chk2.setBounds(200, 50, 70, 30);
		chk3.setBounds(300, 50, 100, 30);

		c.add(chk1);
		c.add(chk2);
		c.add(chk3);

		// 할 일 검색
		JLabel lb3 = new JLabel("검색어");
		lb3.setBounds(20, 90, 100, 30);
		c.add(lb3);

		// 검색창
		JTextField tf1 = new JTextField();
		tf1.setBounds(100, 90, 170, 30);
		c.add(tf1);

		// 조회 버튼
		JButton btn1 = new JButton("조회");
		btn1.setBounds(300, 90, 100, 30);
		c.add(btn1);

		// textArea / 진행전
		JTextArea jtx1 = new JTextArea();

		JScrollPane jsp1 = new JScrollPane(jtx1);
		jsp1.setBounds(20, 150, 200, 300);
		c.add(jsp1);

		// textArea / 진행중
		JTextArea jtx2 = new JTextArea();
		JScrollPane jsp2 = new JScrollPane(jtx2);
		jsp2.setBounds(230, 150, 200, 300);
		c.add(jsp2);

		// textArea / 진행완료
		JTextArea jtx3 = new JTextArea();
		JScrollPane jsp3 = new JScrollPane(jtx3);
		jsp3.setBounds(440, 150, 200, 300);
		c.add(jsp3);

		// 버튼 입력 이벤트 : 조회 조건 설정 및 출력
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (rd1.isSelected()) {

					if (tf1.getText().length() == 0 || tf1.getText().equals("")) {

						List<TodoVO> list = todoDao.listByPerson(null);

						for (int i = 0; i < list.size(); i++) {

							if (list.get(i).getT_state() == 0) {
								jtx1.append(list.get(i).getT_content() + "\n" + list.get(i).getUsrid() + "\n"
										+ list.get(i).getT_regdate() + "\n");
								jtx1.append("------------------------------");
							} else if (list.get(i).getT_state() == 1) {
								jtx2.append(list.get(i).getT_content() + "\n" + list.get(i).getUsrid() + "\n"
										+ list.get(i).getT_regdate() + "\n");
								jtx2.append("------------------------------");
							} else {
								jtx3.append(list.get(i).getT_content() + "\n" + list.get(i).getUsrid() + "\n"
										+ list.get(i).getT_regdate() + "\n");
								jtx3.append("------------------------------");
							}
						}
					}

				} else {
					String searchWord = tf1.getText();

					List<TodoVO> list = todoDao.listByPersonSearch(null);

					for (int i = 0; i < list.size(); i++) {

						if (list.get(i).getT_state() == 0) {
							jtx1.append(list.get(i).getT_content() + "\n" + list.get(i).getUsrid() + "\n"
									+ list.get(i).getT_regdate() + "\n");
							jtx1.append("------------------------------");
						} else if (list.get(i).getT_state() == 1) {
							jtx2.append(list.get(i).getT_content() + "\n" + list.get(i).getUsrid() + "\n"
									+ list.get(i).getT_regdate() + "\n");
							jtx2.append("------------------------------");
						} else {
							jtx3.append(list.get(i).getT_content() + "\n" + list.get(i).getUsrid() + "\n"
									+ list.get(i).getT_regdate() + "\n");
							jtx3.append("------------------------------");
						}
					}
				}

				if (rd2.isSelected()) {
					/*
					 * chk1.isSelected(); chk2.isSelected(); chk3.isSelected();
					 */

					if (tf1.getText().length() == 0 || tf1.getText().equals("")) {
						List<TodoVO> list = todoDao.listByTeam(null);

						for (int i = 0; i < list.size(); i++) {

							if (list.get(i).getT_state() == 0) {
								jtx1.append(list.get(i).getT_content() + "\n" + list.get(i).getUsrid() + "\n"
										+ list.get(i).getT_regdate() + "\n");

								jtx1.append("------------------------------");
							} else if (list.get(i).getT_state() == 1) {
								jtx2.append(list.get(i).getT_content() + "\n" + list.get(i).getUsrid() + "\n"
										+ list.get(i).getT_regdate() + "\n");
								jtx2.append("------------------------------");
							} else {
								jtx3.append(list.get(i).getT_content() + "\n" + list.get(i).getUsrid() + "\n"
										+ list.get(i).getT_regdate() + "\n");
								jtx3.append("------------------------------");
							}
						}

					} else {
						tf1.getText();
						List<TodoVO> list = todoDao.listByTeamSearch(null);

						for (int i = 0; i < list.size(); i++) {

							if (list.get(i).getT_state() == 0) {
								jtx1.append(list.get(i).getT_content() + "\n" + list.get(i).getUsrid() + "\n"
										+ list.get(i).getT_regdate() + "\n");
								jtx1.append("------------------------------");
							} else if (list.get(i).getT_state() == 1) {
								jtx2.append(list.get(i).getT_content() + "\n" + list.get(i).getUsrid() + "\n"
										+ list.get(i).getT_regdate() + "\n");
								jtx2.append("------------------------------");
							} else {
								jtx3.append(list.get(i).getT_content() + "\n" + list.get(i).getUsrid() + "\n"
										+ list.get(i).getT_regdate() + "\n");
								jtx3.append("------------------------------");
							}
						}
					}
				}
			}
		});

		// 삽입 : 할 일, 담당자, 현재 상태
		// 할 일 입력 테스트
		JLabel lb4 = new JLabel("할 일");
		lb4.setBounds(20, 470, 100, 30);
		c.add(lb4);

		JTextField tf2 = new JTextField();
		tf2.setBounds(100, 470, 530, 30);
		c.add(tf2);

		JLabel lb5 = new JLabel("담당자");
		lb5.setBounds(20, 500, 100, 30);
		c.add(lb5);

		for (int i = 0; i < memberList.size(); i++) {
			memVector.add(memberList.get(i).getUsrname());
		}

		JComboBox<String> memCombo = new JComboBox<String>(memVector);

		// JList<String> memberNameList = new JList<String>(memVector);
		// JScrollPane jsp4 = new JScrollPane(memCombo);
		memCombo.setBounds(100, 530, 100, 30);
		c.add(memCombo);

		JLabel lb6 = new JLabel("상태");
		lb6.setBounds(20, 530, 100, 30);
		c.add(lb6);
		JRadioButton rd3 = new JRadioButton("진행전");
		JRadioButton rd4 = new JRadioButton("진행중");
		JRadioButton rd5 = new JRadioButton("완료");

		// 1번 라디오 버튼 눌려져 있도록
		rd3.setSelected(true);

		// 라디오 버튼을 그룹화하기 위한 객체 생성
		ButtonGroup groupRd1 = new ButtonGroup();

		groupRd1.add(rd3);
		groupRd1.add(rd4);
		groupRd1.add(rd5);

		rd3.setBounds(100, 530, 70, 30);
		rd4.setBounds(200, 530, 70, 30);
		rd5.setBounds(300, 530, 100, 30);

		c.add(rd3);
		c.add(rd4);
		c.add(rd5);

		JButton btn2 = new JButton("입력");
		btn2.setBounds(550, 570, 100, 30);
		c.add(btn2);
		
		JButton btn3 = new JButton("수정");
		btn3.setBounds(550, 570, 100, 30);
		c.add(btn3);
		
		JButton btn4 = new JButton("삭제");
		btn4.setBounds(550, 570, 100, 30);
		c.add(btn4);

		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String todo = tf2.getText();

				String selectedMem = memCombo.getSelectedItem().toString();

				// 이름으로 user객체 찾기
				UserVO user = userDao.findById(selectedMem);
				String memId = user.getUsrid();
				String teamnum = user.getTeamnum();

				int state;

				if (rd3.isSelected()) {
					state = 0;
				} else if (rd4.isSelected()) {
					state = 1;
				} else {
					state = 2;
				}

				int InsertResult = todoDao.insertTodo(new TodoVO(todo, memId, teamnum, state));

				if (InsertResult == 1) {
					JOptionPane.showMessageDialog(null, "할 일이 등록되었습니다.");

					tf2.setText("");
					rd3.setSelected(true);

				} else {
					JOptionPane.showMessageDialog(null, "오류가 발생했습니다.", "에러", JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		// 수정

		// 삭제

		// 윈도우 창 크기 설정
		setSize(700, 900);

		// 윈도우 창 나타나게.
		setVisible(true);

	}

	public static void main(String[] args) {
		new TodoUI();
	}
}
