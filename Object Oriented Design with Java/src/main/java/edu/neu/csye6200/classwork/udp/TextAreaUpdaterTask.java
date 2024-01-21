package edu.neu.csye6200.classwork.udp;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author 
 */
public class TextAreaUpdaterTask<T extends JComponent> implements Runnable {

    /**
     * construct an runnable updater for the text of one GUI component
     *
     * @param guiComponent targeted GUI component for text update
     */
    public TextAreaUpdaterTask(T guiComponent) {
        this.guiComponent = guiComponent;
    }
    JButton b;
    JComboBox c;
    JLabel l;
    JTextArea t;
    JTextField f;

    String message;     // message to update gui component
    ArrayList list;        // list of messages to update gui component
    T guiComponent;     // gui components to update with message

    /**
     * provide text for updating GUI component
     *
     * @param message text for GUI component update
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 
     * @param aList list of messages to update GUI component 
     */
    public void setMessage(ArrayList aList) {
        this.list = aList;
    }
    
    private <S> S convert(Class<S> cls, Object o) {
        return cls.cast(o);
    }

    @Override
    public void run() {
        if (guiComponent instanceof JButton) {
            b = (JButton) guiComponent;
            b.setText(message);
        }
        if (guiComponent instanceof JComboBox) {
            c = convert(JComboBox.class, guiComponent) ;
            c.removeAllItems();
            for (Object item : list) {                
                c.addItem(item);
            }
        }
        if (guiComponent instanceof JLabel) {
            l = (JLabel) guiComponent;
            l.setText(message);
        }
        if (guiComponent instanceof JTextArea) {
            t = (JTextArea) guiComponent;
            t.setText(message);
        }
        if (guiComponent instanceof JTextField) {
            f = (JTextField) guiComponent;
            f.setText(message);
        }
    }
}
