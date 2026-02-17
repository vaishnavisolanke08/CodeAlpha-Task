import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// ===== Chatbot Logic Class =====
class ChatbotLogic {

    HashMap<String, String> faq = new HashMap<>();

    ChatbotLogic() {
        // Training the bot (FAQ)
        faq.put("hi", "Hello! How can I help you?");
        faq.put("hello", "Hi there! 😊");
        faq.put("how are you", "I'm doing great! Thanks for asking.");
        faq.put("your name", "I am JavaBot 🤖");
        faq.put("help", "You can ask me about Java, projects, or general questions.");
        faq.put("bye", "Goodbye! Have a nice day 👋");
    }

    // NLP processing + response
    String getResponse(String input) {

        // Basic NLP
        input = input.toLowerCase();
        input = input.replaceAll("[^a-z ]", "");

        // Keyword matching
        for (String key : faq.keySet()) {
            if (input.contains(key)) {
                return faq.get(key);
            }
        }

        return "Sorry, I didn't understand that 😕";
    }
}

// ===== GUI Class =====
class ChatbotGUI extends JFrame implements ActionListener {

    JTextArea chatArea;
    JTextField inputField;
    JButton sendButton;
    ChatbotLogic bot;

    ChatbotGUI() {
        bot = new ChatbotLogic();

        setTitle("Java Chatbot");
        setSize(400, 500);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(chatArea);

        inputField = new JTextField();
        sendButton = new JButton("Send");

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(scroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(this);
        inputField.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userText = inputField.getText();
        chatArea.append("You: " + userText + "\n");

        String botReply = bot.getResponse(userText);
        chatArea.append("Bot: " + botReply + "\n\n");

        inputField.setText("");
    }
}

// ===== Main Class =====
public class ChatbotApp {
    public static void main(String[] args) {
        new ChatbotGUI();
    }
}
