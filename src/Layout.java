import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

public class Layout extends JFrame {
    JLabel processLabel, inputLabel;
    boolean isReset = false;
    boolean isClean = false;
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

        processLabel = new JLabel("", JLabel.RIGHT); //과정
        inputLabel = new JLabel("0", JLabel.RIGHT); //입력 및 결과
        processLabel.setFont(new Font("고딕", Font.PLAIN, 15));
        inputLabel.setFont(new Font("고딕", Font.BOLD, 30));
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
        JButton[] numberButtonArr = new JButton[10];
        for (int i=0; i<10; i++) {
            numberButtonArr[i] = new JButton(String.valueOf(i));
            numberButtonArr[i].setBackground(new Color(Color.WHITE.getRGB()));
            numberButtonArr[i].setFont(new Font("고딕", Font.BOLD, 20));
            String num = String.valueOf(i);
            numberButtonArr[i].addActionListener(e -> {
                inputInputLabel(num);
            });
        }

        JButton[] functionButtonArr = new JButton[14];

        JButton per = new JButton("%"); functionButtonArr[0] = per;
        JButton ce = new JButton("CE"); functionButtonArr[1] = ce;
        JButton c = new JButton("C"); functionButtonArr[2] = c;
        JButton backSpace = new JButton("⌫"); functionButtonArr[3] = backSpace;
        JButton xSquare = new JButton("x²"); functionButtonArr[4] = xSquare;
        JButton leftParenthesis = new JButton("("); functionButtonArr[5] = leftParenthesis;
        JButton rightParenthesis = new JButton(")"); functionButtonArr[6] = rightParenthesis;
        JButton divide = new JButton("/"); functionButtonArr[7] = divide;
        JButton multiply = new JButton("*"); functionButtonArr[8] = multiply;
        JButton minus = new JButton("-"); functionButtonArr[9] = minus;
        JButton plus = new JButton("+"); functionButtonArr[10] = plus;
        JButton plusMinus = new JButton("+/-"); functionButtonArr[11] = plusMinus;
        JButton dot = new JButton("."); functionButtonArr[12] = dot;
        JButton result = new JButton("="); functionButtonArr[13] = result;

        for (JButton jButton : Arrays.asList(
                per, ce, c, backSpace, xSquare, leftParenthesis, rightParenthesis,
                divide, multiply, minus, plus, plusMinus, dot)) {
            jButton.setBackground(new Color(Color.LIGHT_GRAY.getRGB()));
            jButton.setFont(new Font("고딕", Font.PLAIN, 20));
        }

        result.setBackground(new Color(0, 90, 158));
        result.setFont(new Font("고딕", Font.PLAIN, 20));


        for (JButton button : functionButtonArr) {
            button.addActionListener(e -> {
                String inputText = inputLabel.getText();
                String processText = processLabel.getText();
                switch (button.getText()) {
                    case "CE":
                        inputLabel.setText("0");
                        break;
                    case "C":
                        processLabel.setText("");
                        inputLabel.setText("0");
                        break;
                    case "⌫":
                        inputText = inputText.substring(0, inputText.length() - 1);
                        if (inputText.isEmpty()) {
                            inputText = "0";
                        }
                        inputLabel.setText(inputText);
                        break;
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                    case "(":
                    case ")":
                        inputProcessLabel(button.getText());
                        break;
                    case ".":
                        inputInputLabel(button.getText());
                        break;
                    case "=":
                        calculate();
                        break;
                }
            });
        }

        buttonPanel.add(per);
        buttonPanel.add(ce);
        buttonPanel.add(c);
        buttonPanel.add(backSpace);

        buttonPanel.add(xSquare);
        buttonPanel.add(leftParenthesis);
        buttonPanel.add(rightParenthesis);
        buttonPanel.add(divide);

        buttonPanel.add(numberButtonArr[7]);
        buttonPanel.add(numberButtonArr[8]);
        buttonPanel.add(numberButtonArr[9]);
        buttonPanel.add(multiply);

        buttonPanel.add(numberButtonArr[4]);
        buttonPanel.add(numberButtonArr[5]);
        buttonPanel.add(numberButtonArr[6]);
        buttonPanel.add(minus);

        buttonPanel.add(numberButtonArr[1]);
        buttonPanel.add(numberButtonArr[2]);
        buttonPanel.add(numberButtonArr[3]);
        buttonPanel.add(plus);

        buttonPanel.add(plusMinus);
        buttonPanel.add(numberButtonArr[0]);
        buttonPanel.add(dot);
        buttonPanel.add(result);
    }

    void inputInputLabel(String text) {
        if (text.equals(".") && inputLabel.getText().contains(".")) {
            return;
        }

        if ((inputLabel.getText().equals("0") && !text.equals(".")) || isReset || isClean) {
            inputLabel.setText(text);
            isReset = false;
            isClean = false;
        } else {
            inputLabel.setText(inputLabel.getText() + text);
        }
    }

    void inputProcessLabel(String text) {
        if (isReset) {
            return;
        } else if (text.equals(")") && !processLabel.getText().contains("(")) {
            return;
        }

        String[] inputArr = inputLabel.getText().split(" ");
        String lastInputText = inputArr[inputArr.length - 1];

        String[] processArr = processLabel.getText().split(" ");
        String lastProcessText = processArr[processArr.length - 1];

        if (text.equals("(") && !inputLabel.getText().equals("0") && lastInputText.matches("[0-9]+")) {;
            text = "* " + text;
        }

        if (!text.equals(")")) {
            isReset = true;
        }

        if (text.equals("(") && processLabel.getText().isEmpty()) {
            processLabel.setText("(");
        } else if (processLabel.getText().isEmpty()) {
            processLabel.setText(inputLabel.getText() + " " + text);
        } else if (lastProcessText.equals(")")) {
            processLabel.setText(processLabel.getText() + " " + text);
        } else {
            processLabel.setText(processLabel.getText() + " " + inputLabel.getText() + " " + text);
        }
    }

    void calculate() {
        String lastLabel, value;
        if (!processLabel.getText().isEmpty()) {
            if (!processLabel.getText().substring(processLabel.getText().length() - 1).equals(")")) {
                lastLabel = processLabel.getText() + " " + inputLabel.getText();
            } else {
                lastLabel = processLabel.getText();
            }

            int leftParenthesisCount = 0;
            int rightParenthesisCount = 0;
            for (String s : lastLabel.split(" ")) {
                if (s.equals("(")) leftParenthesisCount++;
                if (s.equals(")")) rightParenthesisCount++;
            }

            if (leftParenthesisCount != rightParenthesisCount) {
                JOptionPane.showMessageDialog(null, "괄호의 개수가 맞지 않습니다.", "에러!", JOptionPane.ERROR_MESSAGE);
            }

            Stack<String> numberStack = new Stack<>(); //숫자 스택
            Stack<String> operatorStack = new Stack<>(); //연산자 스택
            Map<String, Integer> operatorPrecedenceMap = Map.of("+", 1, "-", 1, "*", 2, "/", 2);

            for (String word : lastLabel.split(" ")) {
                if (word.matches("[0-9]+(\\.[0-9]+)?")) {
                    numberStack.push(word);
                } else if (word.equals("(")) {
                    operatorStack.push(word);
                } else if (word.equals(")")) {
                    while(!operatorStack.peek().equals("(")) {
                        numberStack.push(priorityCalculation(operatorStack.pop(), numberStack.pop(), numberStack.pop()));
                    }
                    operatorStack.pop();
                } else if (operatorPrecedenceMap.containsKey(word)) {
                    while (!operatorStack.isEmpty()
                            && !operatorStack.peek().equals("(")
                            && operatorPrecedenceMap.get(word) <= operatorPrecedenceMap.get(operatorStack.peek())) {
                        numberStack.push(priorityCalculation(operatorStack.pop(), numberStack.pop(), numberStack.pop()));
                    }
                    operatorStack.push(word);
                }
            }

            while (!operatorStack.isEmpty()) {
                numberStack.push(priorityCalculation(operatorStack.pop(), numberStack.pop(), numberStack.pop()));
            }

            value = numberStack.pop();
        } else {
            value = inputLabel.getText();
        }

        //소수점 4자리 반올림 및 .0 제거
        double result = Math.round(Double.parseDouble(value) * 10000.0) / 10000.0;
        String resultStr = Double.toString(result);

        if (resultStr.endsWith(".0")) {
            resultStr = resultStr.substring(0, resultStr.length() - 2);
        }

        processLabel.setText("");
        inputLabel.setText(resultStr);
        isClean = true;
    }

    String priorityCalculation(String operator, String str1, String str2) {
        double num1 = Double.parseDouble(str1);
        double num2 = Double.parseDouble(str2);
        switch (operator) {
            case "+":
                return String.valueOf(num1 + num2);
            case "-":
                return String.valueOf(num2 - num1);
            case "*":
                return String.valueOf(num1 * num2);
            case "/":
                return String.valueOf(num2 / num1);
            default:
                return null;
        }
    }
}