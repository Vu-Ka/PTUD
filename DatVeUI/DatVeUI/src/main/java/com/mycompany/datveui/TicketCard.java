package com.mycompany.datveui;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class TicketCard extends JPanel {
    private boolean isExpanded = false; // trạng thái xổ xuống
    private JPanel detailsPanel; // panel chi tiết xổ xuống

    public TicketCard(Ticket ticket) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(220, 220, 220))
        ));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 1000)); // để panel xổ xuống không bị cắt

        // --- LEFT ---
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        JLabel typeLabel = new JLabel(ticket.trainType);
        typeLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        leftPanel.add(typeLabel);

        JPanel routePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        routePanel.setOpaque(false);

        JLabel codeLabel = new JLabel(ticket.trainCode);
        codeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        routePanel.add(codeLabel);

        JLabel seatLeft = new JLabel("Còn " + ticket.seatsLeft + " chỗ");
        seatLeft.setOpaque(true);
        seatLeft.setBackground(new Color(255, 243, 200));
        seatLeft.setForeground(Color.ORANGE.darker());
        seatLeft.setFont(new Font("SansSerif", Font.PLAIN, 12));
        seatLeft.setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 6));
        routePanel.add(seatLeft);

        leftPanel.add(routePanel);

        // --- CENTER ---
        JPanel centerPanel = new JPanel(new GridLayout(2, 3, 10, 2));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10));

        centerPanel.add(new JLabel("Ga " + ticket.fromStation));
        centerPanel.add(new JLabel(""));
        centerPanel.add(new JLabel("Ga " + ticket.toStation));

        centerPanel.add(new JLabel(ticket.fromTime));
        JLabel duration = new JLabel(ticket.duration + " ⟶");
        duration.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(duration);
        centerPanel.add(new JLabel(ticket.toTime));

        // --- BOTTOM ---
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        JLabel date = new JLabel(ticket.date);
        date.setFont(new Font("SansSerif", Font.PLAIN, 12));
        bottomPanel.add(date, BorderLayout.WEST);

        JLabel price = new JLabel("Từ " + ticket.price + " VNĐ");
        price.setFont(new Font("SansSerif", Font.BOLD, 14));
        price.setForeground(new Color(0, 0, 0));
        bottomPanel.add(price, BorderLayout.EAST);

        // --- DROPDOWN BUTTON ---
        JButton dropDownBtn = new JButton("▼");
        dropDownBtn.setFocusPainted(false);
        dropDownBtn.setContentAreaFilled(false);
        dropDownBtn.setBorderPainted(false);
        dropDownBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        rightPanel.add(dropDownBtn, BorderLayout.NORTH);

        // --- MAIN CONTENT ---
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BorderLayout());
        mainContent.setOpaque(false);
        mainContent.add(leftPanel, BorderLayout.WEST);
        mainContent.add(centerPanel, BorderLayout.CENTER);
        mainContent.add(rightPanel, BorderLayout.EAST);

        add(mainContent, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // --- CHI TIẾT ĐẶT VÉ (ẩn lúc đầu) ---
        detailsPanel = new TrainSeatBookingPanel(); // class bạn đã có
        detailsPanel.setVisible(false); // ẩn ban đầu
        add(detailsPanel, BorderLayout.SOUTH); // gắn vào dưới cùng

        // --- BẮT SỰ KIỆN CLICK ---
        dropDownBtn.addActionListener(e -> {
            isExpanded = !isExpanded;
            detailsPanel.setVisible(isExpanded);
            dropDownBtn.setText(isExpanded ? "▲" : "▼");
            revalidate();
            repaint();
        });
    }
}
