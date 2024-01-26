// followed the tutorial here: https://youtu.be/dfhmTyRTCSQ?si=wcU5xD6QHpYAcolb
// added custom code to make it more realistic like disallowing more than one
// period (which breaks the various operations) and removing decimals from both
// positive and negative whole numbers. I have a few TODOs, but I want to add
// more things to my GitHub before I revisit these.

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textfield;
    JButton[] numButtons = new JButton[10];
    JButton[] funcButtons = new JButton[9];
    JButton addButton, subButton, multButton, divButton;
    JButton decButton, equalButton, delButton, clearButton, negButton;
    JPanel panel;
    double num1 = 0, num2 = 0, res = 0;
    char operator;
    Color bgColor = new Color(47,47,47);
    Border emptyBorder = BorderFactory.createEmptyBorder();

    Font myfont = new Font("Serif", Font.PLAIN, 30);

    Calculator (){
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame size and colors
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.getContentPane().setBackground(bgColor);
        //center window
        frame.setLocationRelativeTo(null);

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myfont);
        textfield.setBackground(Color.darkGray);
        textfield.setForeground(Color.white);
        textfield.setBorder(emptyBorder);
        frame.add(textfield);
        //prevents users from typing numbers/letters
        textfield.setEditable(false);

        //button texts
        addButton = new JButton("+");
        subButton = new JButton("-");
        multButton = new JButton("×");
        divButton = new JButton("÷");
        decButton = new JButton(".");
        equalButton = new JButton("=");
        delButton = new JButton("⌫");
        clearButton = new JButton("C");
        negButton = new JButton("(-)");

        //loading array with order layout
        funcButtons[0] = addButton;
        funcButtons[1] = subButton;
        funcButtons[2] = multButton;
        funcButtons[3] = divButton;
        funcButtons[4] = decButton;
        funcButtons[5] = equalButton;
        funcButtons[6] = delButton;
        funcButtons[7] = clearButton;
        funcButtons[8] = negButton;

        for (int i = 0; i < 9; i++){
            funcButtons[i].addActionListener(this);
            funcButtons[i].setFont(myfont);
            funcButtons[i].setBackground(Color.darkGray);
            funcButtons[i].setForeground(Color.white);
            funcButtons[i].setBorder(emptyBorder);
            funcButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++){
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].addActionListener(this);
            numButtons[i].setFont(myfont);
            numButtons[i].setBackground(Color.darkGray);
            numButtons[i].setForeground(Color.white);
            numButtons[i].setBorder(emptyBorder);
            numButtons[i].setFocusable(false);
        }

        //custom button alignments and sizes; centers the misc buttons
        clearButton.setBounds(55, 430, 90, 50);
        delButton.setBounds(155, 430, 90, 50);
        equalButton.setBounds(255, 430, 90, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setBackground(bgColor);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        //ordering layout
        panel.add(numButtons[7]);
        panel.add(numButtons[8]);
        panel.add(numButtons[9]);
        panel.add(divButton);
        panel.add(numButtons[4]);
        panel.add(numButtons[5]);
        panel.add(numButtons[6]);
        panel.add(multButton);
        panel.add(numButtons[1]);
        panel.add(numButtons[2]);
        panel.add(numButtons[3]);
        panel.add(subButton);
        panel.add(negButton);
        panel.add(numButtons[0]);
        panel.add(decButton);        
        panel.add(addButton);

        //misc buttons
        frame.add(panel);
        frame.add(clearButton);
        frame.add(delButton);
        frame.add(equalButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public static void main(String[] args) throws Exception {
        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO make all inputted numbers visible until equalButton is hit
        //TODO add keyboard functionality
        //TODO don't let anything happen if a decimal is the only char in
        //textfield; unnecessary but cleaner in the backend

        for (int i = 0; i < 10; i++){
            if (e.getSource() == numButtons[i]){
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton){     
            // custom code; made it so users cannot add a period if one
            // is already present in the textfield
            if (!(textfield.getText().toString().indexOf('.') > -1)){
                textfield.setText(textfield.getText().concat("."));
            }
        }
        if (e.getSource() == addButton){
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            textfield.setText("");
        }
        if (e.getSource() == subButton){
            num1 = Double.parseDouble(textfield.getText());
            operator = '-';
            textfield.setText("");
        }
        if (e.getSource() == multButton){
            num1 = Double.parseDouble(textfield.getText());
            operator = '×';
            textfield.setText("");
        }
        if (e.getSource() == divButton){
            num1 = Double.parseDouble(textfield.getText());
            operator = '÷';
            textfield.setText("");
        }
        if (e.getSource() == equalButton){
            num2 = Double.parseDouble(textfield.getText());
            switch (operator){
                case '+':
                    res = num1 + num2;
                    break;
                case '-':
                    res = num1 - num2;
                    break;
                case '×':
                    res = num1 * num2;
                    break;
                case '÷':
                    res = num1 / num2;
                    break;
            }
            // custom code; if the number is whole, assign new
            // variable as int version
            if (res % 1 == 0){
                int resW = (int)res;
                //set textfield to new int var 'resE'
                textfield.setText(String.valueOf(resW));
            // set textfield as the original double
            } else {
                textfield.setText(String.valueOf(res));
            }
            //set the result as num1 for next calculation
            num1 = res;
        }
        if (e.getSource() == clearButton){
            textfield.setText("");
        }
        if (e.getSource() == delButton){
            String delStr = textfield.getText();
            textfield.setText("");
            for (int i = 0; i < delStr.length() - 1; i++){
                textfield.setText(textfield.getText() + delStr.charAt(i));
            }
        }
        if (e.getSource() == negButton){
            double temp = Double.parseDouble(textfield.getText());
            temp *= -1;
            // custom code; same thing with previous code
            if (temp % 1 == 0){
                int tempW = (int)temp;
                textfield.setText(String.valueOf(tempW));
            } else {
                textfield.setText(String.valueOf(temp));
            }
        }
    }
}
