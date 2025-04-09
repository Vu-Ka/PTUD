package com.mycompany.datveui;


import javax.swing.*;
import java.awt.*;

public class SeatMapPanel extends JPanel {
    private final int[][] seatData = {
            {1, 2, -1, 3, 4}, {5, 6, -2, 7, 8}, {9, 10, -2, 11, 12},
            {13, 14, -2, 15, 16}, {17, 18, -2, 19, 20}, {21, 22, -2, 23, 24},
            {25, 26, -2, 27, 28}, {29, 30, -2, 31, 32}, {33, 34, -2, 35, 36},
            {-1, -1, -3, -1, -1}
    };

    public SeatMapPanel(JComboBox<String> toaComboBox) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel seatGrid = new JPanel(new GridLayout(10, 5, 5, 5));
        seatGrid.setBackground(Color.WHITE);
        add(seatGrid, BorderLayout.CENTER);

        toaComboBox.addActionListener(e -> {
            seatGrid.removeAll();
            for (int[] row : seatData) {
                for (int val : row) {
                    JButton seat = new JButton();
                    seat.setMargin(new Insets(0, 0, 0, 0));
                    seat.setFont(new Font("Arial", Font.PLAIN, 12));
                    seat.setFocusPainted(false);

                    if (val > 0) {
                        seat.setText(String.valueOf(val));
                        if (val == 3 || val == 6 || val == 10 || val == 25 || val == 29) {
                            seat.setBackground(new Color(255, 153, 51));
                            seat.setEnabled(false);
                        } else {
                            seat.setBackground(new Color(144, 238, 144));
                            seat.addActionListener(ev -> seat.setBackground(new Color(100, 200, 255)));
                        }
                    } else {
                        seat.setEnabled(false);
                        seat.setBackground(val == -3 ? Color.GRAY : Color.WHITE);
                        seat.setBorder(null);
                    }

                    seatGrid.add(seat);
                }
            }
            seatGrid.revalidate();
            seatGrid.repaint();
        });
    }
}
