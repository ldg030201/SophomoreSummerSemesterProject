import javax.swing.*;
import java.awt.*;

class Layout extends JFrame {
    Layout() {
        setTitle("2022531046_이동건_계산기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 500);

        //Main 패널 설정
        JPanel mainPanel = new JPanel(); //합쳐질 Main 패널
        mainPanel.setLayout(new FlowLayout()); //왼쪽 -> 오른쪽으로 배치되는 레이아웃

        //입력 패널 설정
        JPanel textPanel = new JPanel(); //입력 및 결과 뜨는 패널
        textPanel.setLayout(new GridLayout(2, 1)); //과정 창과 입력 및 결과 창 2개 넣을 예정
        textPanel.setPreferredSize(new Dimension(300, 100));

        JLabel processLabel = new JLabel("", JLabel.RIGHT); //과정
        JLabel inputLabel = new JLabel("0", JLabel.RIGHT); //입력 및 결과
        textPanel.add(processLabel);
        textPanel.add(inputLabel);

        //버튼 패널 설정
        JPanel buttonPanel = new JPanel(); //버튼이 모여져 있는 패널
        buttonPanel.setLayout(new GridLayout(6, 4));
        buttonPanel.setPreferredSize(new Dimension(300, 300));

        setButton(buttonPanel);

        mainPanel.add(textPanel);
        mainPanel.add(buttonPanel);
        add(mainPanel);

        setVisible(true);
    }

    void setButton(JPanel buttonPanel) {
        JButton per = new JButton("%"); buttonPanel.add(per);
        JButton ce = new JButton("CE"); buttonPanel.add(ce);
        JButton c = new JButton("C"); buttonPanel.add(c);
        JButton backSpace = new JButton("<-"); buttonPanel.add(backSpace);

        JButton divideByOne = new JButton("1/x"); buttonPanel.add(divideByOne);
        JButton xSquare = new JButton("x²"); buttonPanel.add(xSquare);
        JButton rootX = new JButton("√x"); buttonPanel.add(rootX);
        JButton divide = new JButton("÷"); buttonPanel.add(divide);

        JButton seven = new JButton("7"); buttonPanel.add(seven);
        JButton eight = new JButton("8"); buttonPanel.add(eight);
        JButton nine = new JButton("9"); buttonPanel.add(nine);
        JButton multiply = new JButton("x"); buttonPanel.add(multiply);

        JButton four = new JButton("4"); buttonPanel.add(four);
        JButton five = new JButton("5"); buttonPanel.add(five);
        JButton six = new JButton("6"); buttonPanel.add(six);
        JButton minus = new JButton("-"); buttonPanel.add(minus);

        JButton one = new JButton("1"); buttonPanel.add(one);
        JButton two = new JButton("2"); buttonPanel.add(two);
        JButton three = new JButton("3"); buttonPanel.add(three);
        JButton plus = new JButton("+"); buttonPanel.add(plus);

        JButton plusMinus = new JButton("+/-"); buttonPanel.add(plusMinus);
        JButton zero = new JButton("0"); buttonPanel.add(zero);
        JButton dot = new JButton("."); buttonPanel.add(dot);
        JButton result = new JButton("="); buttonPanel.add(result);
    }
}
public class Main {
    public static void main(String[] args) {
        new Layout();
    }
}