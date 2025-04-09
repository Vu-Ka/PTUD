package com.mycompany.datveui;


import javax.swing.*;
import java.awt.*;

public class DatVeFrame extends JFrame {

    private Sidebar sidebar;
    private JPanel contentPanel;
    private RightPanel rightPanel;

    public DatVeFrame() {
        setTitle("Giao diện đặt vé");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        rightPanel = new RightPanel();
        rightPanel.setVisible(false); 
        add(rightPanel, BorderLayout.EAST);

        TopBar topBar = new TopBar(sidebar, rightPanel);
        add(topBar, BorderLayout.NORTH);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        add(new JScrollPane(contentPanel), BorderLayout.CENTER);

        for (int i = 0; i < 6; i++) {
            Ticket ticket = new Ticket(
                "SE" + (i + 1),          
                "Tàu nhanh",             
                "Ga Sài Gòn",            
                "06:00",                 
                "Ga Bình Thuận",         
                "09:41",                 
                "3h41",                  
                "23/03/2025",            
                146000,                  
                20 - i                   
            );
            TicketCard card = new TicketCard(ticket);
            contentPanel.add(card);
        }
    }
}
