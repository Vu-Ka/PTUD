package com.mycompany.datveui;


import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EmptyBorder;

public class BookingFormUI extends JFrame {
    public BookingFormUI() {
        setTitle("Đặt vé: Nhập thông tin khách hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 15));
        mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(Color.WHITE);

        // ===== Title =====
        JLabel titleLabel = new JLabel("Đặt vé: Nhập thông tin khách hàng", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // ===== Form =====
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin người đặt vé"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Dimension fieldSize = new Dimension(200, 28);

        JTextField nameField = new JTextField();
        JTextField idField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField emailConfirmField = new JTextField();
        JTextField phoneField = new JTextField();

        nameField.setPreferredSize(fieldSize);
        idField.setPreferredSize(fieldSize);
        emailField.setPreferredSize(fieldSize);
        emailConfirmField.setPreferredSize(fieldSize);
        phoneField.setPreferredSize(fieldSize);

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Họ và tên*", JLabel.LEFT), gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Số CMND/Hộ chiếu*", JLabel.LEFT), gbc);
        gbc.gridx = 3;
        formPanel.add(idField, gbc);

        row++;
        gbc.gridy = row; gbc.gridx = 0;
        formPanel.add(new JLabel("Email", JLabel.LEFT), gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Xác nhận email", JLabel.LEFT), gbc);
        gbc.gridx = 3;
        formPanel.add(emailConfirmField, gbc);

        row++;
        gbc.gridy = row; gbc.gridx = 0;
        formPanel.add(new JLabel("Số di động*", JLabel.LEFT), gbc);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        /// ===== Table Panel =====
JPanel ticketPanel = new JPanel(new BorderLayout(10, 10));
ticketPanel.setBorder(BorderFactory.createTitledBorder("Thông tin vé"));
ticketPanel.setBackground(Color.WHITE);

// Table
String[] columns = {"STT", "Thông tin hành trình", "Thông tin chỗ", "Thông tin hành khách", "Giá vé", "Giảm giá", "Khuyến mãi", "Thành tiền"};
Object[][] data = {
        {1, "<html>SE8<br>Ga đi: Sài Gòn<br>Ga đến: Bình Thuận<br>23/03/2025 06:00</html>",
                "Giường nằm, toa 9 chỗ 17", "Sinh viên", "250.000 VND", "10%", "Không có khuyến mãi", "225.000 VND"},
        {2, "<html>SE8<br>Ga đi: Sài Gòn<br>Ga đến: Bình Thuận<br>23/03/2025 06:00</html>",
                "Giường nằm, toa 18 chỗ 1", "Người lớn", "250.000 VND", "0%", "Không có khuyến mãi", "250.000 VND"}
};

JTable table = new JTable(new DefaultTableModel(data, columns)) {
    public boolean isCellEditable(int row, int column) {
        return false;
    }
};
table.setRowHeight(60);
table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
table.getTableHeader().setBackground(new Color(220, 235, 250));
table.getTableHeader().setOpaque(true);
table.setGridColor(new Color(200, 200, 200));
table.setShowGrid(true);

JScrollPane tableScroll = new JScrollPane(table);
ticketPanel.add(tableScroll, BorderLayout.CENTER);

// Tổng tiền panel
JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
totalPanel.setBackground(new Color(220, 235, 250));
totalPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));

JLabel totalLabel = new JLabel("Tổng tiền: ");
totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
JLabel totalAmount = new JLabel("475.000 VND");
totalAmount.setFont(new Font("Segoe UI", Font.BOLD, 15));
totalAmount.setForeground(new Color(0, 102, 204));

totalPanel.add(totalLabel);
totalPanel.add(totalAmount);
ticketPanel.add(totalPanel, BorderLayout.SOUTH);

// Add vào giao diện chính
mainPanel.add(ticketPanel, BorderLayout.SOUTH);


        // ===== Buttons =====
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton backButton = new JButton("<< Quay lại");
        JButton payButton = new JButton("Thanh toán >>");

        Font btnFont = new Font("Segoe UI", Font.BOLD, 13);
        Color btnColor = new Color(0, 173, 239);
        Color textColor = Color.WHITE;

        backButton.setFont(btnFont);
        backButton.setBackground(btnColor);
        backButton.setForeground(textColor);

        payButton.setFont(btnFont);
        payButton.setBackground(btnColor);
        payButton.setForeground(textColor);

        backButton.addActionListener(e -> {
            this.dispose();
            SwingUtilities.invokeLater(() -> new MainFrame()); // thay MainFrame nếu tên khác
        });

        payButton.addActionListener(e -> {
    // Tạm thời truyền thông tin giả định, sau sẽ thay bằng dữ liệu thực
    String hoTen = "Nguyễn Văn A";
    String cccd = "123456789";
    String loaiVe = "Người lớn";
    String toa = "Toa 9";
    String choNgoi = "Chỗ 17";
    String giaVe = "250.000 VND";

    // Mở khung in vé
    BoardingPassFrame frame = new BoardingPassFrame(hoTen, cccd, loaiVe, toa, choNgoi, giaVe);
    frame.setVisible(true);
});


        buttonPanel.add(backButton, BorderLayout.WEST);
        buttonPanel.add(payButton, BorderLayout.EAST);
        mainPanel.add(buttonPanel, BorderLayout.PAGE_END);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BookingFormUI::new);
    }
}
