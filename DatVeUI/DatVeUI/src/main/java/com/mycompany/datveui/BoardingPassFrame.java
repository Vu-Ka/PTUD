package com.mycompany.datveui;


import javax.swing.*;
import java.awt.*;

public class BoardingPassFrame extends JFrame {

    public BoardingPassFrame(String hoTen, String cccd, String loaiVe, String toa, String choNgoi, String giaVe) {
        setTitle("Phiếu đi tàu");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // === Header logo + tiêu đề ===
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);

        JLabel logoLabel = new JLabel(new ImageIcon("train_icon.png")); // thay bằng path logo nếu có
        JLabel titleLabel = new JLabel("Đặt vé: In vé", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        headerPanel.add(logoLabel, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // === Phiếu vé panel ===
        JPanel passPanel = new JPanel();
        passPanel.setLayout(new BoxLayout(passPanel, BoxLayout.Y_AXIS));
        passPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        passPanel.setBackground(Color.WHITE);
        passPanel.setPreferredSize(new Dimension(400, 400));
        passPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        passPanel.add(Box.createVerticalStrut(15));
        JLabel gaLabel = new JLabel("<html><center><b>NHÀ GA VĨNH HÀNH</b><br/>PHIẾU ĐI TÀU / BOARDING PASS</center></html>", JLabel.CENTER);
        gaLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passPanel.add(gaLabel);

        passPanel.add(Box.createVerticalStrut(10));
        JLabel ticketId = new JLabel("Mã vé/Ticket ID: 123456789");
        ticketId.setAlignmentX(Component.CENTER_ALIGNMENT);
        passPanel.add(ticketId);
        passPanel.add(Box.createVerticalStrut(15));

        // === Thông tin vé ===
        String[][] thongTin = {
                {"Ga đi:", "Sài Gòn"},
                {"Ga đến:", "Bình Thuận"},
                {"Tàu/Train:", "SE8"},
                {"Ngày đi/Date:", "23/03/2025"},
                {"Giờ đi/Time:", "06:00"},
                {"Toa/Coach:", toa},
                {"Loại chỗ/Class:", choNgoi},
                {"Loại vé/Ticket:", loaiVe},
                {"Họ tên/Name:", hoTen},
                {"CMND/CCCD:", cccd},
                {"Giá/Price:", giaVe}
        };

        for (String[] info : thongTin) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.setBackground(Color.WHITE);
            JLabel key = new JLabel(info[0]);
            JLabel val = new JLabel("<html><b>" + info[1] + "</b></html>");
            key.setPreferredSize(new Dimension(120, 20));
            row.add(key);
            row.add(val);
            passPanel.add(row);
        }

        // === Nút In vé ===
        JButton btnIn = new JButton("In vé");
        btnIn.setBackground(new Color(255, 102, 102));
        btnIn.setForeground(Color.WHITE);
        btnIn.setFocusPainted(false);
        btnIn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Bạn có thể xử lý in tại đây nếu cần
        btnIn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Đang in vé..."));

        // === Add vào frame ===
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(Color.WHITE);
        wrapper.add(Box.createVerticalStrut(20));
        wrapper.add(passPanel);
        wrapper.add(Box.createVerticalStrut(20));
        wrapper.add(btnIn);

        add(headerPanel, BorderLayout.NORTH);
        add(wrapper, BorderLayout.CENTER);
    }
}
