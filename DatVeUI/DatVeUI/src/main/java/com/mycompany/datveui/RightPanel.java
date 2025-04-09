package com.mycompany.datveui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class RightPanel extends JPanel {
    public RightPanel() {
        setPreferredSize(new Dimension(240, 700));
        setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());

        // Tạo "card" chứa toàn bộ nội dung
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
                new EmptyBorder(10, 10, 10, 10),
                new LineBorder(new Color(200, 200, 200), 1, true) // viền bo tròn nhẹ
        ));

        // Header
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(new Color(240, 240, 240));
        header.setBorder(new MatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));

        JLabel icon = new JLabel("🛒");
        JLabel title = new JLabel("Giỏ vé");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        header.add(icon);
        header.add(Box.createRigidArea(new Dimension(5, 0)));
        header.add(title);
        card.add(header, BorderLayout.NORTH);

        // Nội dung giỏ vé
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel totalLabel = new JLabel("Tổng: 500.000 VNĐ");
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        totalLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(totalLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        for (int i = 1; i <= 2; i++) {
            JPanel ticketPanel = new JPanel(new BorderLayout());
            ticketPanel.setBackground(Color.WHITE);
            ticketPanel.setBorder(new MatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));

            JLabel indexLabel = new JLabel(String.valueOf(i));
            indexLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            indexLabel.setPreferredSize(new Dimension(25, 40));
            ticketPanel.add(indexLabel, BorderLayout.WEST);

            JTextArea detail = new JTextArea("SE8 Sài Gòn - Bình Thuận\n23/03/2025 06:00\nGN toa 9 chỗ " + (16 + i) + ", tầng 1");
            detail.setFont(new Font("SansSerif", Font.PLAIN, 12));
            detail.setEditable(false);
            detail.setOpaque(false);
            detail.setLineWrap(true);
            detail.setWrapStyleWord(true);
            ticketPanel.add(detail, BorderLayout.CENTER);

            JButton deleteBtn = new JButton("🗑");
            deleteBtn.setFocusPainted(false);
            deleteBtn.setContentAreaFilled(false);
            deleteBtn.setBorderPainted(false);
            deleteBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
            ticketPanel.add(deleteBtn, BorderLayout.EAST);

            contentPanel.add(ticketPanel);
        }

        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton bookBtn = new JButton("Đặt vé");
        bookBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookBtn.setBackground(new Color(0, 102, 204));
        bookBtn.setForeground(Color.WHITE);
        bookBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        bookBtn.setFocusPainted(false);
        bookBtn.setPreferredSize(new Dimension(100, 35));
        contentPanel.add(bookBtn);

        // ======= THÊM XỬ LÝ SỰ KIỆN NHẤN "ĐẶT VÉ" =======
        bookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        BookingFormUI form = new BookingFormUI(); // <-- dùng class bạn đã tạo
                        form.setVisible(true);
                        // Nếu đang chạy trong JFrame có thể đóng frame hiện tại tại đây nếu cần
                        // JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(RightPanel.this);
                        // topFrame.dispose();
                    }
                });
            }
        });
        // ===============================================

        card.add(contentPanel, BorderLayout.CENTER);

        // Canh giữa card
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(new EmptyBorder(20, 10, 20, 10));
        wrapper.add(card, BorderLayout.NORTH);

        add(wrapper, BorderLayout.CENTER);
    }
}
