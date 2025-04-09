package com.mycompany.datveui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Sidebar extends JPanel {
    private JPanel subMenu;
    private JButton btnVeTau;
    private JLabel arrowIcon;
    private JPanel menuContainer;
    private ArrayList<JButton> menuButtons = new ArrayList<>();
    private ArrayList<JButton> subButtons = new ArrayList<>();

    public Sidebar() {
        setPreferredSize(new Dimension(220, 700));
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Container chính cho phần menu
        menuContainer = new JPanel();
        menuContainer.setLayout(new BoxLayout(menuContainer, BoxLayout.Y_AXIS));
        menuContainer.setBackground(Color.WHITE);

        // Logo
        ImageIcon logoIcon = new ImageIcon("src/resources/train.png");
        Image img = logoIcon.getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(img));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        menuContainer.add(logo);

        // Search box bo tròn
        JTextField searchField = new JTextField("🔍 Tra cứu");
        searchField.setEnabled(false);
        searchField.setDisabledTextColor(Color.BLACK);
        searchField.setMaximumSize(new Dimension(180, 35));
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setBackground(new Color(240, 240, 240));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        searchField.setAlignmentX(Component.LEFT_ALIGNMENT);
        menuContainer.add(searchField);
        menuContainer.add(Box.createVerticalStrut(10));

        // Vé tàu + submenu
        btnVeTau = createMainButton("🖨️  Vé tàu");
        arrowIcon = new JLabel(">");
        arrowIcon.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnVeTau.setLayout(new BorderLayout());
        btnVeTau.add(arrowIcon, BorderLayout.EAST);
        btnVeTau.addActionListener(e -> toggleSubMenu());
        menuContainer.add(btnVeTau);

        subMenu = new JPanel();
        subMenu.setLayout(new BoxLayout(subMenu, BoxLayout.Y_AXIS));
        subMenu.setBackground(Color.WHITE);
        subMenu.setVisible(false);

        String[] subItems = { "➕ Đặt vé", "🔁 Đổi thông tin vé", "❌ Hủy vé" };
        for (String sub : subItems) {
            JButton subBtn = createSubButton(sub);
            subButtons.add(subBtn);
            subMenu.add(subBtn);
        }
        menuContainer.add(subMenu);

        // Các menu chính khác
        String[] menuItems = {
                "📊 Thống kê...", "👥 Quản lý nhân viên", "🚌 Quản lý chuyến",
                "👤 Quản lý khách hàng", "📅 Quản lý ca làm việc"
        };

        for (String item : menuItems) {
            JButton btn = createMainButton(item);
            menuButtons.add(btn);
            menuContainer.add(btn);
        }

        add(menuContainer, BorderLayout.NORTH);
    }

    // Mở / đóng submenu
    private void toggleSubMenu() {
        subMenu.setVisible(!subMenu.isVisible());
        arrowIcon.setText(subMenu.isVisible() ? "▼" : ">");
    }

    // Nút menu chính
    private JButton createMainButton(String text) {
        JButton button = new JButton(text);
        button.setName(text);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMaximumSize(new Dimension(200, 40));
        button.setFont(new Font("SansSerif", Font.BOLD, 14)); // Font in đậm
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setToolTipText(text);
        return button;
    }

    // Nút submenu
    private JButton createSubButton(String text) {
        JButton button = new JButton(text);
        button.setName(text);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMaximumSize(new Dimension(200, 35));
        button.setFont(new Font("SansSerif", Font.BOLD, 13)); // Font in đậm
        button.setBackground(new Color(245, 245, 245));
        button.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 10));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        return button;
    }

    // MAIN test
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sidebar Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        Sidebar sidebar = new Sidebar();
        frame.add(sidebar, BorderLayout.WEST);

        frame.setSize(300, 700);
        frame.setVisible(true);
    }
}
