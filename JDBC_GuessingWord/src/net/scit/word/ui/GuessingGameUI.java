package net.scit.word.ui;

import java.util.List;
import java.util.Scanner;

import net.scit.word.dao.WordDAO;
import net.scit.word.service.GuessingGame;
import net.scit.word.vo.Word;

public class GuessingGameUI{
	Scanner keyin = new Scanner(System.in);
	WordDAO dao = new WordDAO();

	
	
	public GuessingGameUI() {
		String choice;
		while(true) {
			mainMenu();
			choice = keyin.nextLine();
			
			switch(choice) {
			
			 
			case "1" : list();  	break;
			case "2" : add();   	break;
			case "3" : search();   	break;
			case "4" : update(); 	break;
			case "5" : delete(); 	break;
			case "6" : guessingGame(); break;
			case "0" : 
				System.out.println("** ���α׷��� �����մϴ�.");
				System.exit(0);
			default :
				System.out.println("err) �޴��� �ٽ� ������ �ּ���");
			}
			//keyin.nextLine(); // ���� ����
		}
	}
	
	// ���� �޴�
	private void mainMenu() {
		System.out.println("===== [�Ϻ��� �ܾ� �ܿ��] =====");
		System.out.println("       1) ��ü ��� ��ȸ");
		System.out.println("       2) �ܾ� �߰�");
		System.out.println("       3) �ܾ� ã��");
		System.out.println("       4) �ܾ� ��gggggg��");
		System.out.println("       5) �ܾ� ����22222222222222");
		System.out.println("       6) �ܾ� ���߱� ����");
		System.out.println("       0) ���α׷� ����");
		System.out.println("============================");
		System.out.print  ("          ����> ");
	}
	

	// ��ü �ܾ� ��ȸ
	private void list() {
		System.out.println("\n<< ��ü �ܾ� ��ȸ >>");
		
		List<Word> list = dao.listWord();
		
		list.forEach(x -> System.out.println(x));
	}

	// ���ο� �ܾ� �߰�
	private void add() {
		String word, mean;
		System.out.println("\n<< ���ο� �ܾ� ��� >>");
		
		System.out.print("> �ܾ� : ");
		word = keyin.nextLine();
		if(word.trim().equals("")) {
			System.out.println("err) �ܾ �Է����ּ���");
			return;
		}
		
		System.out.print("> �� : ");
		mean = keyin.nextLine();

		if(mean.trim().equals("")) {
			System.out.println("err) �ܾ��� ���� �Է����ּ���");
			return;
		}
		
		Word w = new Word(0, word, mean, false);
		
		int result = dao.appendWord(w);
		if(result == 0)
			System.out.println("** ���ο� �ܾ ��ϵǾ����ϴ�.\n");
	}

	// �ܾ� ã��
	private void search() {
		String word;
		System.out.println("\n<< �ܾ� ã�� >>");
		
		System.out.print("> �ܾ� : ");
		word = keyin.nextLine();
		if(word.trim().equals("")) {
			System.out.println("err) �ܾ �Է����ּ���");
			return;
		}		
	
		Word findWord = dao.findByWord(word);
		if(findWord == null) {
			System.out.println("** �ܾ �����ϴ�.");
			return;
		}
		
		System.out.println(findWord);
	}
	
	// �ܾ� ����
	private void update() {
		String word, mean;
		
		System.out.println("\n<< �ܾ� ���� >>");
		
		System.out.print("> ������ �ܾ� : ");
		word = keyin.nextLine();
		if(word.trim().equals("")) {
			System.out.println("err) �ܾ �Է����ּ���");
			return;
		}		
	
		Word findWord = dao.findByWord(word);
		if(findWord == null) {
			System.out.println("** �ܾ �����ϴ�.");
			return;
		}
		System.out.println("* �˻��� �ܾ� : " + findWord);
		
		System.out.print("> �ܾ� : ");
		word = keyin.nextLine();
		if(word.trim().equals("")) {
			System.out.println("err) �ܾ �Է����ּ���");
			return;
		}	
		
		System.out.print("> �� : ");
		mean = keyin.nextLine();

		if(mean.trim().equals("")) {
			System.out.println("err) �ܾ��� ���� �Է����ּ���");
			return;
		}
		
		Word w = new Word(findWord.getSeq(), word, mean, false);
		
		int result = dao.updateWord(w);
		if(result == 1)
			System.out.println("** �ܾ �����Ǿ����ϴ�.\n");
	}
	
	// �ܾ� ����
	private void delete() {
		String word, answer;
		
		System.out.println("\n<< �ܾ� ���� >>");
		
		System.out.print("> ������ �ܾ� : ");
		word = keyin.nextLine();
		if(word.trim().equals("")) {
			System.out.println("err) �ܾ �Է����ּ���");
			return;
		}		
	
		Word findWord = dao.findByWord(word);
		if(findWord == null) {
			System.out.println("** �ܾ �����ϴ�.");
			return;
		}
		
		System.out.println(findWord);
		
		System.out.print("** �ܾ �����Ͻðڽ��ϱ�?");
		answer = keyin.nextLine();
		
		if(!answer.equals("y") ) {
			System.out.println("** �����۾��� ��ҵǾ����ϴ�.");
			return;
		}
		int result = dao.deleteWord(findWord.getSeq());
		
		System.out.println(result +"�� �ܾ �����Ǿ����ϴ�.");
	}
	// �ܾ� ���߱� ����
	private void guessingGame() {
		GuessingGame game = new GuessingGame();
	}
}