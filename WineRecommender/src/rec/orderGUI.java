package rec;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import rec.collaborative.RecCreation;
import rec.content.ContentBuyHistory;
import rec.content.SimilarityList;
import rec.database.GetBuyHistory;

/*
 * Graphische Oberfl�che
 * 
 * GUI-Methoden:
 * aktuellen User (als String): gui.getCurrentUser()
 * aktuellen Wein (als Wine): gui.getCurrentWine()
 * aktuellen Warenkorb (als Vector von Wine): gui.getCurrentOrder()
 *
 */
public class orderGUI {
	private final static Vector<Wine> order = new Vector<Wine>();
	private static JComboBox<User> userDropDown = normalGUI.getUserDropDown();
	private static JComboBox<Wine> wineDropDown = normalGUI.getWineDropDown();
	private final Vector<Wine> wineList = normalGUI.getWineList();
	private final Vector<User> userList = normalGUI.getUserList();
	RecCreation CRBuyHistory;
	Vector <Wine> orderRecColl ; 
	public orderGUI(int width) {

		JFrame frame = new JFrame("Intelligente Weinempfehlung");
		frame.setSize((int) (1.5 * width), (int) (width * 0.85));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.lightGray);

		new SimilarityList(getCurrentUser());

		// Allgemeine Beschriftung

		JLabel orderText = new JLabel("Warenkorb Empfehlung");
		orderText.setBounds((int) (width * 1.07 - 80), (int) (width * 0.02),
				200, 20);
		orderText.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(orderText);

		// DropDownBox von Usern
		// JLabel userText = new JLabel("User ausw�hlen");
		// userText.setBounds((int) (width * 0.17 - 50), (int) (width * 0.07),
		// 200, 20);
		// panel.add(userText);
		//
		// userDropDown.setBounds((int) (width * 0.02), (int) (width * 0.1),
		// (int) (width * 0.3), 20);
		// panel.add(userDropDown);

		// ScrollBox des Warenkorbs
		JLabel WarenkorbText = new JLabel("Warenkorb des Users");
		WarenkorbText.setBounds((int) (width * 0.91 - 65),
				(int) (width * 0.07), 200, 20);
		panel.add(WarenkorbText);

		final JPanel orderPanel = new JPanel();
		JScrollPane orderScrollPane = new JScrollPane(orderPanel);
		orderScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		orderScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		orderPanel.setLayout(null);
		orderPanel.setPreferredSize(new Dimension(400, 170));
		orderScrollPane.setBounds((int) (width * 0.76), (int) (width * 0.1),
				(int) (width * 0.3), (int) (width * 0.17));
		orderPanel.setBackground(Color.GRAY);
		orderPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		panel.add(orderScrollPane);

		// DropdownBox + Button zum Hinzuf�gen/L�schen eines Weines
		JLabel chooseWineText = new JLabel("Wein ausw�hlen");
		chooseWineText.setBounds((int) (width * 1.23 - 50),
				(int) (width * 0.11), 200, 20);
		panel.add(chooseWineText);

		final JComboBox<Wine> chooseWineDropDown = new JComboBox<Wine>(wineList);
		chooseWineDropDown.setBounds((int) (width * 1.08),
				(int) (width * 0.14), (int) (width * 0.3), 20);
		panel.add(chooseWineDropDown);

		JButton addWineButton = new JButton("Hinzuf�gen");
		addWineButton.setBounds((int) (width * 1.08), (int) (width * 0.19),
				(int) (width * 0.15), (int) (width * 0.04));
		panel.add(addWineButton);

		JButton deleteWineButton = new JButton("L�schen");
		deleteWineButton.setBounds((int) (width * 1.23), (int) (width * 0.19),
				(int) (width * 0.15), (int) (width * 0.04));
		panel.add(deleteWineButton);

		// ScrollBox mit Warenkorb Content-Based Empfehlungen
		JLabel orderContentText = new JLabel("Content-Based Empfehlungen");
		orderContentText.setBounds((int) (width * 0.91 - 85),
				(int) (width * 0.3), 200, 20);
		panel.add(orderContentText);

		final JPanel orderContentPanel = new JPanel();
		JScrollPane orderContentScrollPane = new JScrollPane(orderContentPanel);
		orderContentScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		orderContentScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		orderContentPanel.setLayout(null);
		orderContentScrollPane.setBounds((int) (width * 0.76),
				(int) (width * 0.33), (int) (width * 0.3), (int) (width * 0.2));
		orderContentPanel.setBackground(Color.GRAY);
		orderContentPanel
				.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// TODO wineList2 ersetzen mit der Warenkorb content Empfehlungsliste
		paintPanel(ContentBuyHistory.getBuyHistory(), orderContentPanel,
				"content");

		panel.add(orderContentScrollPane);

		// ScrollBox mit Warenkorb Collaborativen Empfehlungen
		JLabel orderCollaborativeText = new JLabel("Collaborative Empfehlungen");
		orderCollaborativeText.setBounds((int) (width * 1.23 - 80),
				(int) (width * 0.3), 200, 20);
		panel.add(orderCollaborativeText);

		final JPanel orderCollaborativePanel = new JPanel();
		JScrollPane orderCollaborativeScrollPane = new JScrollPane(
				orderCollaborativePanel);
		orderCollaborativeScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		orderCollaborativeScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		orderCollaborativePanel.setLayout(null);
		orderCollaborativeScrollPane.setBounds((int) (width * 1.08),
				(int) (width * 0.33), (int) (width * 0.3), (int) (width * 0.2));
		orderCollaborativePanel.setBackground(Color.GRAY);
		orderCollaborativePanel.setBorder(BorderFactory
				.createLineBorder(Color.BLACK));

		// TODO wineList2 ersetzen mit der Warenkorb collaborativen
		// Empfehlungsliste
		CRBuyHistory = new RecCreation(getCurrentUser().getId());
		orderRecColl=CRBuyHistory.createRecOrderHistory(getCurrentOrder());
		paintPanel(orderRecColl,orderCollaborativePanel, "collaborative");

		panel.add(orderCollaborativeScrollPane);

		// ScrollBox mit Warenkorb Hybriden Empfehlungen
		JLabel orderHybridText = new JLabel("Hybride Empfehlungen");
		orderHybridText.setBounds((int) (width * 1.08 - 65),
				(int) (width * 0.55), 200, 20);
		panel.add(orderHybridText);

		final JPanel orderHybridPanel = new JPanel();
		JScrollPane orderHybridScrollPane = new JScrollPane(orderHybridPanel);
		orderHybridScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		orderHybridScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		orderHybridPanel.setLayout(null);
		orderHybridScrollPane.setBounds((int) (width * 0.92),
				(int) (width * 0.58), (int) (width * 0.3), (int) (width * 0.2));
		orderHybridPanel.setBackground(Color.GRAY);
		orderHybridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// TODO wineList2 ersetzen mit der Warenkorb hybriden Empfehlungsliste
		paintPanel(Hybrid.warenkorbHybrid(ContentBuyHistory.getBuyHistory(), orderRecColl), orderHybridPanel, "gemischt");
		panel.add(orderHybridScrollPane);

		/*
		 * 
		 * Aktionlistener
		 */

		// // Aktionlistener f�r userDropDown
		// // TODO collaborative und hybrid erg�nzen
		// userDropDown.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// CRBuyHistory = new RecCreation(getCurrentUser().getId());
		// paintPanel(CRBuyHistory.createRecBuyHistory(),
		// normalCollaborativePanel, "collaborative");
		// normalCollaborativePanel.repaint();
		// }
		// });
		//
		// Warenkorb ActionListener f�r Hinzuf�gen Button
		addWineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wine tmp = (Wine) chooseWineDropDown.getSelectedItem();
				if (!order.contains(tmp)) {
					order.add(tmp);
					paintPanel(order, orderPanel, "normal");
					paintPanel(ContentBuyHistory.getBuyHistory(),
							orderContentPanel, "content");
					CRBuyHistory = new RecCreation(-1);
					orderRecColl=CRBuyHistory.createRecOrderHistory(getCurrentOrder());
					paintPanel(orderRecColl,orderCollaborativePanel, "collaborative");
					paintPanel(Hybrid.warenkorbHybrid(ContentBuyHistory.getBuyHistory(), orderRecColl), orderHybridPanel, "gemischt");
					orderPanel.repaint();
					orderContentPanel.repaint();
					orderCollaborativePanel.repaint();
					orderHybridPanel.repaint();
				}
			}
		});
		// Warenkorb ActionListener f�r L�schen Button
		deleteWineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order.remove(chooseWineDropDown.getSelectedItem());
				paintPanel(order, orderPanel, "normal");
				paintPanel(ContentBuyHistory.getBuyHistory(),
						orderContentPanel, "content");
				CRBuyHistory = new RecCreation(-1);
				orderRecColl=CRBuyHistory.createRecOrderHistory(getCurrentOrder());
				paintPanel(orderRecColl,orderCollaborativePanel, "collaborative");
				paintPanel(Hybrid.warenkorbHybrid(ContentBuyHistory.getBuyHistory(), orderRecColl), orderHybridPanel, "gemischt");
				orderPanel.repaint();
				orderContentPanel.repaint();
				orderCollaborativePanel.repaint();
				orderHybridPanel.repaint();
			}
		});
		frame.add(panel);
		frame.setVisible(true);
	}

	// Zeichnet die Empfehlungs-Vektoren
	private void paintPanel(final Vector<Wine> vector, JPanel panel,
			String source) {
		panel.removeAll();
		DecimalFormat f = new DecimalFormat("#0.00");
		int y;
		y = 0;
		JLabel tmp = null;
		for (int i = 0; i < vector.size(); i++) {
			Wine tmpWine = vector.elementAt(i);
			if (source.equals("normal")) {
				tmp = new JLabel(tmpWine.toString());
			}
			if (source.equals("content")) {
				tmp = new JLabel(tmpWine.getId() + ": ("
						+ tmpWine.getSimilarity() + " %) " + tmpWine.getName());
			}
			if (source.equals("collaborative")) {
				tmp = new JLabel(tmpWine.getId() + ": ("
						+ f.format(tmpWine.getWineScore()) + ") "
						+ tmpWine.getName());
			}

			if (source.equals("gemischt")) {
				
				if (tmpWine.isContent()){
					tmp = new JLabel(tmpWine.getId() + ": ("
							+ tmpWine.getSimilarity() + " %) " + tmpWine.getName());
				}
				if (tmpWine.isKaufhistorie()){
					tmp = new JLabel(tmpWine.getId() + ": ("
							+ f.format(tmpWine.getWineScore()) + ") "
							+ tmpWine.getName());
				}}

			tmp.setBounds(5, y, 400, 20);
			y += 20;
			panel.add(tmp);
		}
		if (y > 170)
			panel.setPreferredSize(new Dimension(400, y));
	}

	public static User getCurrentUser() {
		return (User) userDropDown.getSelectedItem();
	}

	public static Wine getCurrentWine() {
		return (Wine) wineDropDown.getSelectedItem();
	}

	public static Vector<Wine> getCurrentOrder() {
		return order;
	}

	// Suche nach Wein mit Hilfe der ID
	private static Wine search(Vector<Wine> wineVector, int id) {
		Wine result = null;

		for (int i = 0; i < wineVector.size(); i++) {
			Wine tmp = wineVector.elementAt(i);
			if (tmp.getId() == id)
				result = tmp;
		}

		return result;
	}
}
