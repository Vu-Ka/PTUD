package com.mycompany.datveui;


import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {
    public TopBar(Sidebar sidebar, JPanel rightPanel) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Tiêu đề
        JLabel title = new JLabel("Đặt vé", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24)); 
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.CENTER);
        
        // Avatar nhân viên
        JLabel avatar = new JLabel("👤");
        avatar.setFont(new Font("SansSerif", Font.PLAIN, 26)); 
        avatar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(avatar, BorderLayout.EAST);

        // Nút toggle menu (hamburger)
        JButton toggleSidebarBtn = new JButton("☰");
        toggleSidebarBtn.setFocusPainted(false);
        toggleSidebarBtn.setFont(new Font("SansSerif", Font.PLAIN, 20));
        toggleSidebarBtn.setBackground(Color.WHITE);
        toggleSidebarBtn.setBorderPainted(false);
        toggleSidebarBtn.setPreferredSize(new Dimension(50, 50));
        add(toggleSidebarBtn, BorderLayout.WEST);

        // Toggle Sidebar ↔ RightPanel
        toggleSidebarBtn.addActionListener(e -> {
            boolean sidebarVisible = sidebar.isVisible();
            sidebar.setVisible(!sidebarVisible);
            rightPanel.setVisible(sidebarVisible); // đảo trạng thái
        });
    }
}
