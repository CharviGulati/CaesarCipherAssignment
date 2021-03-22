package ui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class WindowPane extends JFrame {
    JEditorPane ed;
    JScrollPane jsp;

    public WindowPane() {
        // Set the size of the main interface, and layout
        this.setSize(new Dimension(700, 500));
        this.setLayout(new BorderLayout());

        /// Add a scroll bar to JEditorPane
        ed = new JEditorPane();
        jsp = new JScrollPane(ed);
        jsp.setPreferredSize(new Dimension(500, 500));

        /// Set the vertical horizontal scroll bar to display
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        //Add the plate into it and set the position
        this.add(jsp, BorderLayout.CENTER);

        // Set the visible, exit the program when closed
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
