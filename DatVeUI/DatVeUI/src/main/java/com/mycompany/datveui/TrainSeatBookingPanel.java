package com.mycompany.datveui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TrainSeatBookingPanel extends JPanel {
    private JPanel seatPanelContainer;
    private JPanel tierPanel;
    private JButton btnDatVe, btnSelectAll, btnClearAll;
    private Set<JButton> selectedSeats = new HashSet<>();
    private Set<JButton> bookedSeats = new HashSet<>();
    private boolean isDragging = false;
    private Boolean dragSelectMode = null;

    public TrainSeatBookingPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        add(Box.createVerticalStrut(10));
        add(createSeatTypePanel());
        add(Box.createVerticalStrut(15));
        add(createCoachSelectionPanel());
        add(Box.createVerticalStrut(15));

        tierPanel = createTierPanel();
        tierPanel.setVisible(false);
        add(tierPanel);

        seatPanelContainer = new JPanel(null);
        seatPanelContainer.setPreferredSize(new Dimension(400, 200));
        seatPanelContainer.setBackground(Color.WHITE);
        seatPanelContainer.setVisible(false);

        seatPanelContainer.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                isDragging = true;
                Component comp = seatPanelContainer.getComponentAt(e.getPoint());
                if (comp instanceof JButton btn && !bookedSeats.contains(btn)) {
                    dragSelectMode = !selectedSeats.contains(btn);
                    applyDragSelection(btn);
                }
            }

            public void mouseReleased(MouseEvent e) {
                isDragging = false;
                dragSelectMode = null;
            }
        });

        seatPanelContainer.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (!isDragging || dragSelectMode == null) return;

                Component comp = seatPanelContainer.getComponentAt(e.getPoint());
                if (comp instanceof JButton btn && !bookedSeats.contains(btn)) {
                    applyDragSelection(btn);
                }
            }
        });

        add(seatPanelContainer);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);

        btnSelectAll = new JButton("Chọn tất cả");
        styleLightButton(btnSelectAll);
        btnSelectAll.addActionListener(e -> selectAllSeats());
        buttonPanel.add(btnSelectAll);

        btnClearAll = new JButton("Bỏ chọn tất cả");
        styleLightButton(btnClearAll);
        btnClearAll.addActionListener(e -> clearSelectedSeats());
        buttonPanel.add(btnClearAll);

        add(buttonPanel);

        btnDatVe = new JButton("Đặt vé");
        btnDatVe.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnDatVe.setBackground(new Color(255, 140, 0));
        btnDatVe.setForeground(Color.WHITE);
        btnDatVe.setFocusPainted(false);
        btnDatVe.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnDatVe.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnDatVe.setVisible(false);
        btnDatVe.addActionListener(e -> bookSelectedSeats());
        add(Box.createVerticalStrut(15));
        add(btnDatVe);
    }

    private JPanel createSeatTypePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panel.setBackground(Color.WHITE);

        String[] seatTypes = {"Ghế cứng", "Ghế mềm", "Giường nằm"};
        ButtonGroup group = new ButtonGroup();

        for (String type : seatTypes) {
            JToggleButton btn = createOptionButton(type);
            group.add(btn);
            panel.add(btn);

            btn.addActionListener(e -> {
                tierPanel.setVisible(type.equals("Giường nằm"));
                showSeatSelection(type);
            });
        }

        return panel;
    }

    private JPanel createCoachSelectionPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Chọn toa:");
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel.add(label);

        ButtonGroup group = new ButtonGroup();
        for (int i = 1; i <= 4; i++) {
            JToggleButton btn = createOptionButton("Toa " + i);
            group.add(btn);
            panel.add(btn);
            btn.addActionListener(e -> btnDatVe.setVisible(true));
        }
        return panel;
    }

    private JPanel createTierPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Chọn tầng:");
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel.add(label);

        String[] tiers = {"Tầng 1", "Tầng 2"};
        ButtonGroup group = new ButtonGroup();

        for (String tier : tiers) {
            JToggleButton btn = createOptionButton(tier);
            group.add(btn);
            panel.add(btn);

            btn.addActionListener(e -> {
                seatPanelContainer.setVisible(true);
                populateSeatLayout(tier.equals("Tầng 1") ? 1 : 2);
            });
        }
        return panel;
    }

    private void showSeatSelection(String seatType) {
        seatPanelContainer.removeAll();
        seatPanelContainer.setVisible(true);
        selectedSeats.clear();
        bookedSeats.clear();

        if (!seatType.equals("Giường nằm")) {
            for (int i = 1; i <= 10; i++) {
                JButton seat = createSeatButton("Ghế " + i);
                seat.setBounds(10 + (i - 1) * 90, 10, 80, 30);
                seatPanelContainer.add(seat);
            }
        } else {
            seatPanelContainer.setVisible(false);
        }

        revalidate();
        repaint();
    }

    private void populateSeatLayout(int tier) {
        seatPanelContainer.removeAll();
        selectedSeats.clear();
        bookedSeats.clear();

        for (int i = 1; i <= 6; i++) {
            JButton bed = createSeatButton("Giường " + tier + "-" + i);
            bed.setBounds(10 + (i - 1) * 90, 10, 80, 30);
            seatPanelContainer.add(bed);
        }
        revalidate();
        repaint();
    }

    private JToggleButton createOptionButton(String text) {
        JToggleButton btn = new JToggleButton(text);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 13));
        btn.setBackground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 165, 0)));
        btn.setPreferredSize(new Dimension(100, 35));
        return btn;
    }

    private JButton createSeatButton(String label) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(255, 243, 200));
        btn.setForeground(Color.DARK_GRAY);
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 165, 0)));

        btn.addActionListener(e -> {
            if (bookedSeats.contains(btn)) return;
            if (selectedSeats.contains(btn)) {
                selectedSeats.remove(btn);
                btn.setBackground(new Color(255, 243, 200));
            } else {
                selectedSeats.add(btn);
                btn.setBackground(new Color(255, 165, 0));
            }
        });

        return btn;
    }

    private void applyDragSelection(JButton btn) {
        if (dragSelectMode == null || bookedSeats.contains(btn)) return;

        if (dragSelectMode) {
            if (!selectedSeats.contains(btn)) {
                btn.setBackground(new Color(255, 165, 0));
                selectedSeats.add(btn);
            }
        } else {
            if (selectedSeats.contains(btn)) {
                btn.setBackground(new Color(255, 243, 200));
                selectedSeats.remove(btn);
            }
        }
    }

    private void selectAllSeats() {
        for (Component comp : seatPanelContainer.getComponents()) {
            if (comp instanceof JButton seat && !bookedSeats.contains(seat)) {
                selectedSeats.add(seat);
                seat.setBackground(new Color(255, 165, 0));
            }
        }
    }

    private void clearSelectedSeats() {
        for (JButton seat : selectedSeats) {
            seat.setBackground(new Color(255, 243, 200));
        }
        selectedSeats.clear();
    }

    private void bookSelectedSeats() {
        for (JButton seat : selectedSeats) {
            seat.setBackground(Color.GRAY);
            bookedSeats.add(seat);
        }
        selectedSeats.clear();
    }

    // ✨ Style mới cho button trắng viền mảnh ✨
    private void styleLightButton(JButton button) {
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.PLAIN, 13));
        button.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        button.setPreferredSize(new Dimension(130, 35));
    }
}
