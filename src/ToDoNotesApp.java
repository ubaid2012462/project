import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class ToDoNotesApp {
    public static String registeredUsername; // Store registered username
    public static String registeredPassword; // Store registered password

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegistrationWindow::new); // Launch the registration window
    }
}

class RegistrationWindow extends JFrame {
    private JTextField userField; // Input field for username
    private JPasswordField passField, confirmPassField; // Password and confirmation input fields
    private JLabel infoLabel; // Label to show messages

    public RegistrationWindow() {
        setTitle("Register - ToDo Notes");
        setSize(400, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = createPanel();
        userField = createTextField(panel, "Enter Username:", 20);
        passField = createPasswordField(panel, "Enter Password:", 60);
        confirmPassField = createPasswordField(panel, "Confirm Password:", 100);
        JButton registerBtn = createButton(panel, "Register", 140);
        infoLabel = new JLabel("", SwingConstants.CENTER);
        infoLabel.setForeground(Color.RED);
        infoLabel.setBounds(20, 180, 350, 20);
        panel.add(infoLabel);
        registerBtn.addActionListener(e -> register());
        getContentPane().add(panel);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 228, 225)); // Light pink background
        return panel;
    }

    private JTextField createTextField(JPanel panel, String labelText, int y) {
        JLabel label = new JLabel(labelText);
        label.setBounds(20, y, 150, 25);
        panel.add(label);
        JTextField textField = new JTextField();
        textField.setBounds(170, y, 200, 25);
        panel.add(textField);
        return textField;
    }

    private JPasswordField createPasswordField(JPanel panel, String labelText, int y) {
        JLabel label = new JLabel(labelText);
        label.setBounds(20, y, 150, 25);
        panel.add(label);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(170, y, 200, 25);
        panel.add(passwordField);
        return passwordField;
    }

    private JButton createButton(JPanel panel, String text, int y) {
        JButton button = new JButton(text);
        button.setBounds(170, y, 200, 30);
        button.setBackground(new Color(100, 149, 237)); // Cornflower blue button
        button.setForeground(Color.WHITE); // White text
        panel.add(button);
        return button;
    }

    private void register() {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword());
        String confirmPass = new String(confirmPassField.getPassword());

        if (username.isEmpty()) {
            infoLabel.setText("Username cannot be empty.");
            return;
        }
        if (password.isEmpty()) {
            infoLabel.setText("Password cannot be empty.");
            return;
        }
        if (!password.equals(confirmPass)) {
            infoLabel.setText("Passwords do not match.");
            return;
        }

        ToDoNotesApp.registeredUsername = username;
        ToDoNotesApp.registeredPassword = password;
        dispose();
        new LoginWindow();
    }
}

class LoginWindow extends JFrame {
    private JTextField userField; // Input field for username
    private JPasswordField passField; // Input field for password
    private JLabel infoLabel; // Label to show messages

    public LoginWindow() {
        setTitle("Login - ToDo Notes");
        setSize(350, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = createPanel();
        userField = createTextField(panel, "Username:", 20);
        passField = createPasswordField(panel, "Password:", 55);
        JButton loginBtn = createButton(panel, "Login", 95);
        infoLabel = new JLabel("", SwingConstants.CENTER);
        infoLabel.setForeground(Color.RED);
        infoLabel.setBounds(20, 130, 290, 20);
        panel.add(infoLabel);
        loginBtn.addActionListener(e -> authenticate());
        getContentPane().add(panel);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 228, 225)); // Light pink background
        return panel;
    }

    private JTextField createTextField(JPanel panel, String labelText, int y) {
        JLabel label = new JLabel(labelText);
        label.setBounds(20, y, 80, 25);
        panel.add(label);
        JTextField textField = new JTextField();
        textField.setBounds(110, y, 200, 25);
        panel.add(textField);
        return textField;
    }

    private JPasswordField createPasswordField(JPanel panel, String labelText, int y) {
        JLabel label = new JLabel(labelText);
        label.setBounds(20, y, 80, 25);
        panel.add(label);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(110, y, 200, 25);
        panel.add(passwordField);
        return passwordField;
    }

    private JButton createButton(JPanel panel, String text, int y) {
        JButton button = new JButton(text);
        button.setBounds(110, y, 200, 30);
        button.setBackground(new Color(100, 149, 237)); // Cornflower blue button
        button.setForeground(Color.WHITE); // White text
        panel.add(button);
        return button;
    }

    private void authenticate() {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword());
        if (username.equals(ToDoNotesApp.registeredUsername) && password.equals(ToDoNotesApp.registeredPassword)) {
            dispose();
            new MainWindow();
        } else {
            infoLabel.setText("Invalid username or password.");
        }
    }
}

class Task {
    private static int idCounter = 1; // Counter for unique task IDs
    private int id; // Task ID
    private String description; // Task description
    private LocalDateTime dueDateTime; // Task due date and time
    private boolean completed; // Task completion status
    private LocalDateTime createdDateTime; // Task creation date and time

    public Task(String description, LocalDateTime dueDateTime) {
        this.id = idCounter++;
        this.description = description;
        this.dueDateTime = dueDateTime;
        this.completed = false;
        this.createdDateTime = LocalDateTime.now();
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public void setDescription(String desc) { this.description = desc; }
    public LocalDateTime getDueDateTime() { return dueDateTime; }
    public void setDueDateTime(LocalDateTime dateTime) { this.dueDateTime = dateTime; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean val) { this.completed = val; }
    public LocalDateTime getCreatedDateTime() { return createdDateTime; }
}

// ========== SORTING ALGORITHMS CLASS ==========
class SortingAlgorithms {

    // Bubble Sort - sorts tasks by ID
    public static void bubbleSort(List<Task> tasks) {
        int n = tasks.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (tasks.get(j).getId() > tasks.get(j + 1).getId()) {
                    // Swap tasks
                    Task temp = tasks.get(j);
                    tasks.set(j, tasks.get(j + 1));
                    tasks.set(j + 1, temp);
                }
            }
        }
    }

    // Quick Sort - sorts tasks by description (alphabetically)
    public static void quickSort(List<Task> tasks, int low, int high) {
        if (low < high) {
            int pi = partition(tasks, low, high);
            quickSort(tasks, low, pi - 1);
            quickSort(tasks, pi + 1, high);
        }
    }

    private static int partition(List<Task> tasks, int low, int high) {
        String pivot = tasks.get(high).getDescription();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (tasks.get(j).getDescription().compareToIgnoreCase(pivot) < 0) {
                i++;
                Task temp = tasks.get(i);
                tasks.set(i, tasks.get(j));
                tasks.set(j, temp);
            }
        }

        Task temp = tasks.get(i + 1);
        tasks.set(i + 1, tasks.get(high));
        tasks.set(high, temp);
        return i + 1;
    }

    // Merge Sort - sorts tasks by due date
    public static void mergeSort(List<Task> tasks, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(tasks, left, mid);
            mergeSort(tasks, mid + 1, right);
            merge(tasks, left, mid, right);
        }
    }

    private static void merge(List<Task> tasks, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        List<Task> leftList = new ArrayList<>();
        List<Task> rightList = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
            leftList.add(tasks.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            rightList.add(tasks.get(mid + 1 + j));
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            LocalDateTime date1 = leftList.get(i).getDueDateTime();
            LocalDateTime date2 = rightList.get(j).getDueDateTime();

            if (date1 == null || (date2 != null && date1.isAfter(date2))) {
                tasks.set(k, rightList.get(j));
                j++;
            } else {
                tasks.set(k, leftList.get(i));
                i++;
            }
            k++;
        }

        while (i < n1) {
            tasks.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            tasks.set(k, rightList.get(j));
            j++;
            k++;
        }
    }

    // Selection Sort - sorts tasks by created date
    public static void selectionSort(List<Task> tasks) {
        int n = tasks.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (tasks.get(j).getCreatedDateTime().isBefore(tasks.get(minIdx).getCreatedDateTime())) {
                    minIdx = j;
                }
            }
            Task temp = tasks.get(minIdx);
            tasks.set(minIdx, tasks.get(i));
            tasks.set(i, temp);
        }
    }
}

// ========== BINARY SEARCH CLASS ==========
class BinarySearch {

    // Binary Search by Task ID (assumes tasks are sorted by ID)
    public static Task searchById(List<Task> tasks, int targetId) {
        // First, sort by ID using bubble sort
        List<Task> sortedTasks = new ArrayList<>(tasks);
        SortingAlgorithms.bubbleSort(sortedTasks);

        int left = 0;
        int right = sortedTasks.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midId = sortedTasks.get(mid).getId();

            if (midId == targetId) {
                return sortedTasks.get(mid);
            } else if (midId < targetId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; // Not found
    }

    // Binary Search by Description (assumes tasks are sorted alphabetically)
    public static Task searchByDescription(List<Task> tasks, String targetDesc) {
        List<Task> sortedTasks = new ArrayList<>(tasks);
        if (sortedTasks.isEmpty()) return null;

        SortingAlgorithms.quickSort(sortedTasks, 0, sortedTasks.size() - 1);

        int left = 0;
        int right = sortedTasks.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = sortedTasks.get(mid).getDescription().compareToIgnoreCase(targetDesc);

            if (comparison == 0) {
                return sortedTasks.get(mid);
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; // Not found
    }
}

class MainWindow extends JFrame {
    private List<Task> tasks = new ArrayList<>(); // List of tasks
    private List<Task> recentlyDeleted = new ArrayList<>(); // List of recently deleted tasks
    private List<Task> completedTasks = new ArrayList<>(); // List of completed tasks
    private JTable taskTable; // Table to display tasks
    private DefaultTableModel tableModel; // Table model for tasks
    private JComboBox<String> sortComboBox; // Combo box for sorting options

    private JTextField expDescField; // Input field for expense description
    private JTextField expAmountField; // Input field for expense amount
    private DefaultTableModel expTableModel; // Table model for expenses
    private JLabel totalLabel; // Label to display total expenditure

    private static final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // Date formatter

    public MainWindow() {
        setTitle("ToDo Notes - Main");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents(); // Initialize components
        setVisible(true);
    }

    private void initComponents() {
        JTabbedPane tabbedPane = new JTabbedPane(); // Tabbed pane for tasks and financials
        tabbedPane.addTab("Tasks", createTaskPanel()); // Add tasks tab
        tabbedPane.addTab("Financial Expenditure", createFinancialPanel()); // Add financial tab
        getContentPane().add(tabbedPane); // Add tabbed pane to the frame
    }

    private JPanel createTaskPanel() {
        JPanel panel = new JPanel(new BorderLayout()); // Main panel for tasks
        JPanel topPanel = new JPanel(); // Panel for top buttons
        addTopButtons(topPanel); // Add buttons to top panel
        sortComboBox = new JComboBox<>(new String[]{"Sort by Due Date", "Sort by Created Date",
                "Sort by ID (Bubble)", "Sort by Description (Quick)", "Sort by Due Date (Merge)", "Sort by Created (Selection)"}); // Sorting options
        topPanel.add(sortComboBox); // Add sorting combo box
        panel.add(topPanel, BorderLayout.NORTH); // Add top panel to main panel

        String[] columns = {"ID", "Description", "Due Date & Time", "Completed", "Created Date & Time"}; // Table columns
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) { return false; } // Disable editing
        };
        taskTable = new JTable(tableModel); // Create task table
        taskTable.setFillsViewportHeight(true); // Fill viewport height
        panel.add(new JScrollPane(taskTable), BorderLayout.CENTER); // Add table to main panel

        sortComboBox.addActionListener(e -> refreshTaskTable()); // Refresh table on sort change
        refreshTaskTable(); // Initial table refresh
        return panel; // Return task panel
    }

    private void addTopButtons(JPanel panel) {
        panel.add(createButton("Add Task", e -> addTaskDialog())); // Add task button
        panel.add(createButton("Edit Task", e -> editTaskDialog())); // Edit task button
        panel.add(createButton("Delete Task", e -> deleteSelectedTask())); // Delete task button
        panel.add(createButton("Mark Completed", e -> markSelectedTaskCompleted())); // Mark completed button
        panel.add(createButton("Search Task", e -> searchTaskDialog())); // Search task button (NEW)
        panel.add(createButton("View Recently Deleted", e -> showRecentlyDeletedDialog())); // View deleted button
        panel.add(createButton("View Completed Tasks", e -> showCompletedTasksWindow())); // View completed button
    }

    private JPanel createFinancialPanel() {
        JPanel panel = new JPanel(new BorderLayout()); // Main panel for financials
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Input panel for expenses
        inputPanel.add(new JLabel("Description:")); // Description label
        expDescField = new JTextField(15); // Description input field
        inputPanel.add(expDescField);
        inputPanel.add(new JLabel("Amount (USD):")); // Amount label
        expAmountField = new JTextField(10); // Amount input field
        inputPanel.add(expAmountField);
        inputPanel.add(createButton("Add Expense", e -> addExpense())); // Add expense button
        panel.add(inputPanel, BorderLayout.NORTH); // Add input panel to main panel

        String[] columns = {"Description", "Amount (USD)"}; // Expense table columns
        expTableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) { return false; } // Disable editing
        };
        JTable expTable = new JTable(expTableModel); // Create expense table
        panel.add(new JScrollPane(expTable), BorderLayout.CENTER); // Add table to main panel

        totalLabel = new JLabel("Total Expenditure: $0.00"); // Total expenditure label
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Set font
        totalLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Set border
        panel.add(totalLabel, BorderLayout.SOUTH); // Add total label to main panel
        return panel; // Return financial panel
    }

    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text); // Create button
        button.setBackground(new Color(100, 149, 237)); // Cornflower blue button
        button.setForeground(Color.WHITE); // White text
        button.addActionListener(action); // Add action listener
        return button; // Return created button
    }

    private void addExpense() {
        String desc = expDescField.getText().trim(); // Get description
        String amtStr = expAmountField.getText().trim(); // Get amount
        if (desc.isEmpty()) {
            showMessage("Please enter a description for the expense."); // Show error if empty
            return;
        }
        double amount;
        try {
            amount = Double.parseDouble(amtStr); // Parse amount
            if (amount < 0) throw new NumberFormatException(); // Check for negative
        } catch (NumberFormatException ex) {
            showMessage("Please enter a valid positive number for amount."); // Show error if invalid
            return;
        }
        expTableModel.addRow(new Object[]{desc, String.format("%.2f", amount)}); // Add expense to table
        expDescField.setText(""); // Clear input field
        expAmountField.setText(""); // Clear input field
        updateTotalExpenditure(); // Update total expenditure
    }

    private void updateTotalExpenditure() {
        double total = 0; // Initialize total
        for (int i = 0; i < expTableModel.getRowCount(); i++) {
            total += Double.parseDouble(expTableModel.getValueAt(i, 1).toString()); // Sum amounts
        }
        totalLabel.setText(String.format("Total Expenditure: $%.2f", total)); // Update total label
    }

    private void addTaskDialog() {
        TaskDialog dialog = new TaskDialog(this, "Add Task", null); // Open task dialog
        dialog.setVisible(true);
        Task newTask = dialog.getTask(); // Get new task
        if (newTask != null) {
            tasks.add(newTask); // Add task to list
            refreshTaskTable(); // Refresh task table
        }
    }

    private void editTaskDialog() {
        int selectedRow = taskTable.getSelectedRow(); // Get selected row
        if (selectedRow == -1) {
            showMessage("Please select a task to edit."); // Show error if no selection
            return;
        }
        int taskId = (int) taskTable.getValueAt(selectedRow, 0); // Get task ID
        Task taskToEdit = getTaskById(taskId); // Get task to edit
        if (taskToEdit == null) return;

        TaskDialog dialog = new TaskDialog(this, "Edit Task", taskToEdit); // Open edit dialog
        dialog.setVisible(true);
        Task editedTask = dialog.getTask(); // Get edited task
        if (editedTask != null) {
            taskToEdit.setDescription(editedTask.getDescription()); // Update description
            taskToEdit.setDueDateTime(editedTask.getDueDateTime()); // Update due date
            refreshTaskTable(); // Refresh task table
        }
    }

    private void deleteSelectedTask() {
        int selectedRow = taskTable.getSelectedRow(); // Get selected row
        if (selectedRow == -1) {
            showMessage("Please select a task to delete."); // Show error if no selection
            return;
        }
        int taskId = (int) taskTable.getValueAt(selectedRow, 0); // Get task ID
        Task taskToDelete = getTaskById(taskId); // Get task to delete
        if (taskToDelete == null) return;

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this task?", "Confirm Delete",
                JOptionPane.YES_NO_OPTION); // Confirm delete
        if (confirm == JOptionPane.YES_OPTION) {
            tasks.remove(taskToDelete); // Remove task from list
            recentlyDeleted.add(0, taskToDelete); // Add to recently deleted
            refreshTaskTable(); // Refresh task table
        }
    }

    private void markSelectedTaskCompleted() {
        int selectedRow = taskTable.getSelectedRow(); // Get selected row
        if (selectedRow == -1) {
            showMessage("Please select a task to mark as completed."); // Show error if no selection
            return;
        }
        int taskId = (int) taskTable.getValueAt(selectedRow, 0); // Get task ID
        Task taskToMark = getTaskById(taskId); // Get task to mark
        if (taskToMark == null) return;
        taskToMark.setCompleted(true); // Mark task as completed
        tasks.remove(taskToMark); // Remove from tasks
        completedTasks.add(taskToMark); // Add to completed tasks
        refreshTaskTable(); // Refresh task table
        showMessage("Task moved to Completed Tasks."); // Show success message
    }

    // NEW: Search Task Dialog using Binary Search
    private void searchTaskDialog() {
        String[] options = {"Search by ID", "Search by Description"};
        int choice = JOptionPane.showOptionDialog(this, "Choose search method:", "Search Task",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            // Search by ID
            String idStr = JOptionPane.showInputDialog(this, "Enter Task ID:");
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Task found = BinarySearch.searchById(tasks, id);
                    if (found != null) {
                        showMessage(String.format("Task Found!\nID: %d\nDescription: %s\nDue: %s\nCreated: %s",
                                found.getId(), found.getDescription(),
                                found.getDueDateTime() != null ? found.getDueDateTime().format(dtFormatter) : "N/A",
                                found.getCreatedDateTime().format(dtFormatter)));
                    } else {
                        showMessage("Task with ID " + id + " not found.");
                    }
                } catch (NumberFormatException ex) {
                    showMessage("Invalid ID format.");
                }
            }
        } else if (choice == 1) {
            // Search by Description
            String desc = JOptionPane.showInputDialog(this, "Enter Task Description:");
            if (desc != null && !desc.trim().isEmpty()) {
                Task found = BinarySearch.searchByDescription(tasks, desc.trim());
                if (found != null) {
                    showMessage(String.format("Task Found!\nID: %d\nDescription: %s\nDue: %s\nCreated: %s",
                            found.getId(), found.getDescription(),
                            found.getDueDateTime() != null ? found.getDueDateTime().format(dtFormatter) : "N/A",
                            found.getCreatedDateTime().format(dtFormatter)));
                } else {
                    showMessage("Task with description '" + desc + "' not found.");
                }
            }
        }
    }

    private void showRecentlyDeletedDialog() {
        if (recentlyDeleted.isEmpty()) {
            showMessage("No recently deleted tasks."); // Show message if none
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Task t : recentlyDeleted) {
            sb.append(String.format("ID: %d | %s | Due: %s | Completed: %s%n", t.getId(), t.getDescription(),
                    t.getDueDateTime() != null ? t.getDueDateTime().format(dtFormatter) : "N/A",
                    t.isCompleted() ? "Yes" : "No")); // Build string for deleted tasks
        }
        JTextArea ta = new JTextArea(sb.toString());
        ta.setEditable(false);
        ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(ta);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        JOptionPane.showMessageDialog(this, scrollPane, "Recently Deleted Tasks", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showCompletedTasksWindow() {
        if (completedTasks.isEmpty()) {
            showMessage("No completed tasks.");
            return;
        }
        new CompletedTasksWindow(this, completedTasks);
    }

    private Task getTaskById(int id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    private void refreshTaskTable() {
        tableModel.setRowCount(0);
        List<Task> sortedTasks = new ArrayList<>(tasks);

        String selectedSort = (String) sortComboBox.getSelectedItem();

        // Apply selected sorting algorithm
        if ("Sort by ID (Bubble)".equals(selectedSort)) {
            SortingAlgorithms.bubbleSort(sortedTasks);
        } else if ("Sort by Description (Quick)".equals(selectedSort)) {
            if (!sortedTasks.isEmpty()) {
                SortingAlgorithms.quickSort(sortedTasks, 0, sortedTasks.size() - 1);
            }
        } else if ("Sort by Due Date (Merge)".equals(selectedSort)) {
            if (!sortedTasks.isEmpty()) {
                SortingAlgorithms.mergeSort(sortedTasks, 0, sortedTasks.size() - 1);
            }
        } else if ("Sort by Created (Selection)".equals(selectedSort)) {
            SortingAlgorithms.selectionSort(sortedTasks);
        } else if ("Sort by Due Date".equals(selectedSort)) {
            sortedTasks.sort(Comparator.comparing(t -> Optional.ofNullable(t.getDueDateTime()).orElse(LocalDateTime.MAX)));
        } else {
            sortedTasks.sort(Comparator.comparing(Task::getCreatedDateTime));
        }

        for (Task t : sortedTasks) {
            tableModel.addRow(new Object[]{
                    t.getId(),
                    t.getDescription(),
                    t.getDueDateTime() != null ? t.getDueDateTime().format(dtFormatter) : "",
                    t.isCompleted() ? "Yes" : "No",
                    t.getCreatedDateTime().format(dtFormatter)
            });
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}

class CompletedTasksWindow extends JDialog {
    private JTable completedTable;
    private DefaultTableModel completedTableModel;

    public CompletedTasksWindow(Frame owner, List<Task> completedTasks) {
        super(owner, "Completed Tasks", true);
        initComponents(completedTasks);
        setSize(700, 400);
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    private void initComponents(List<Task> completedTasks) {
        String[] columns = {"ID", "Description", "Due Date & Time", "Completed", "Created Date & Time"};
        completedTableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        completedTable = new JTable(completedTableModel);
        completedTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(completedTable);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        refreshCompletedTasks(completedTasks);
    }

    private void refreshCompletedTasks(List<Task> completedTasks) {
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        completedTableModel.setRowCount(0);
        for (Task t : completedTasks) {
            completedTableModel.addRow(new Object[]{
                    t.getId(),
                    t.getDescription(),
                    t.getDueDateTime() != null ? t.getDueDateTime().format(dtFormatter) : "",
                    t.isCompleted() ? "Yes" : "No",
                    t.getCreatedDateTime().format(dtFormatter)
            });
        }
    }
}

class TaskDialog extends JDialog {
    private JTextField descField, dateField, timeField;
    private Task task;
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

    public TaskDialog(Frame owner, String title, Task taskToEdit) {
        super(owner, title, true);
        this.task = null;
        initComponents(taskToEdit);
        pack();
        setLocationRelativeTo(owner);
    }

    private void initComponents(Task taskToEdit) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addLabelAndField(panel, gbc, 0, "Description:");
        descField = new JTextField(25);
        addField(panel, gbc, 1, descField);

        addLabelAndField(panel, gbc, 2, "Due Date (yyyy-MM-dd):");
        dateField = new JTextField(10);
        addField(panel, gbc, 3, dateField);

        addLabelAndField(panel, gbc, 4, "Due Time (HH:mm):");
        timeField = new JTextField(6);
        addField(panel, gbc, 5, timeField);

        JButton okBtn = new JButton("OK");
        JButton cancelBtn = new JButton("Cancel");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okBtn);
        buttonPanel.add(cancelBtn);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        getContentPane().add(panel);

        if (taskToEdit != null) {
            descField.setText(taskToEdit.getDescription());
            LocalDateTime due = taskToEdit.getDueDateTime();
            if (due != null) {
                dateField.setText(due.toLocalDate().format(dateFormat));
                timeField.setText(due.toLocalTime().format(timeFormat));
            }
        }

        okBtn.addActionListener(e -> okAction());
        cancelBtn.addActionListener(e -> {
            task = null;
            dispose();
        });
    }

    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, int row, String labelText) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(labelText), gbc);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int row, JTextField field) {
        gbc.gridx = 1;
        gbc.gridy = row;
        panel.add(field, gbc);
    }

    private void okAction() {
        String desc = descField.getText().trim();
        if (desc.isEmpty()) {
            showError("Description cannot be empty.");
            return;
        }

        LocalDateTime dueDateTime = null;
        String dateStr = dateField.getText().trim();
        String timeStr = timeField.getText().trim();

        if (!dateStr.isEmpty() && !timeStr.isEmpty()) {
            try {
                dueDateTime = LocalDateTime.parse(dateStr + "T" + timeStr);
            } catch (Exception ex) {
                showError("Invalid date or time format. Use yyyy-MM-dd for date and HH:mm for time.");
                return;
            }
        } else if (!dateStr.isEmpty() || !timeStr.isEmpty()) {
            showError("Both date and time must be provided or both left empty.");
            return;
        }

        task = new Task(desc, dueDateTime);
        dispose();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    public Task getTask() {
        return task;
    }
}