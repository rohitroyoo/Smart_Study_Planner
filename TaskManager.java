import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class TaskManager extends JPanel {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private File taskFile = new File("tasks.txt");

    public TaskManager() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Task Manager"));

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        taskField = new JTextField();
        JButton addBtn = new JButton("Add Task");
        JButton removeBtn = new JButton("Remove Selected");

        addBtn.addActionListener(e -> addTask());
        removeBtn.addActionListener(e -> removeSelectedTask());

        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(addBtn, BorderLayout.EAST);
        add(inputPanel, BorderLayout.NORTH);
        add(removeBtn, BorderLayout.SOUTH);

        loadTasks();
    }

    private void addTask() {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            taskListModel.addElement(task);
            saveTasks();
            taskField.setText("");
        }
    }

    private void removeSelectedTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            taskListModel.remove(index);
            saveTasks();
        }
    }

    private void loadTasks() {
        try {
            if (!taskFile.exists()) taskFile.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(taskFile));
            String line;
            while ((line = reader.readLine()) != null) {
                taskListModel.addElement(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(taskFile))) {
            for (int i = 0; i < taskListModel.size(); i++) {
                writer.write(taskListModel.getElementAt(i));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
