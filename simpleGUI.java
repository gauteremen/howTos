package no.uib.gre002.info233.v2015;

import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener, ListSelectionListener { //lurt å implementere GUI, om ikke flere vindu
	
	private DefaultListModel<String> categoryListModel;
	private Data data;
	private JButton selectButton;
	private JList<String> categoryList;
	private JTextArea answerArea;

	public GUI () {
		
		data = new Data();
		
		setBounds(new Rectangle(1000, 700));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		JPanel panel = new JPanel(new GridLayout(1,3));
		JPanel buttonPanel = new JPanel();
		add(panel);
		
		categoryListModel = new DefaultListModel<String>();
		addToList(data.getStatements());
		
		categoryList = new JList<>(categoryListModel);
		
		setupTheAnswers();
		setupThePanel(panel, buttonPanel);
		setupButtons();

		buttonPanel.add(new JLabel("Popeye Answers!"));
		buttonPanel.add(selectButton);
		
		selectButton.addActionListener(this);
		categoryList.addListSelectionListener(this);
		
		setVisible(true);
	}
	
	/**
	 * Create the buttons needed for the GUI
	 */
	private void setupButtons() {
		selectButton = new JButton();
		selectButton.setSize(100, 50);
		selectButton.setIcon(new ImageIcon("popeye.jpg")); //sett ikon på knapp
	}

	/**
	 * Create the answerArea in a JTextArea
	 */
	private void setupTheAnswers() {
		answerArea = new JTextArea(); //stort tekstfelt
		answerArea.setLineWrap(true); //ny linje
		answerArea.setWrapStyleWord(true); //skiller på ord
	}
	
	/**
	 * 
	 * @param panel
	 * @param buttonPanel
	 * Add the panels needed in the window
	 */
	private void setupThePanel(JPanel panel, JPanel buttonPanel) {
		panel.add(categoryList); //rekkefølge har noe å si på når man tar inn ting
		panel.add(buttonPanel);
		panel.add(answerArea);
	}
	
	/**
	 * 
	 * @param thingsInList
	 */
	public void addToList(HashMap<String, String> thingsInList) {
		for(String questions : thingsInList.keySet()) {
			categoryListModel.addElement(questions);
		}
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getFromList(String key) {
		return data.getStatements().get(key);
	}
	
	/**
	 * Defines what happens when pushing the button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == selectButton) {
			String key = categoryList.getSelectedValue();
			answerArea.setText(getFromList(key));
		}
	}
	
	/**
	 * Shows answers without pushing the button
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		String key = categoryList.getSelectedValue();
		answerArea.setText(getFromList(key));
		
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		GUI gui = new GUI();
	}
	
}

