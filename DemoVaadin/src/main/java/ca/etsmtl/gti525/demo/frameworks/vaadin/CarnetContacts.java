package ca.etsmtl.gti525.demo.frameworks.vaadin;

/**
 * Démonstration de Vaadin, basé sur l'exemple du tutoriel de Vaadin
 */
import java.sql.SQLException;

import ca.etsmtl.gti525.demo.frameworks.Personne;
import ca.etsmtl.gti525.demo.frameworks.PersonneDAO;
import ca.etsmtl.gti525.demo.frameworks.velocity.EnvoiCourriel;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Title("Contacts")
public class CarnetContacts extends UI {

	/* User interface components are stored in session. */
	private Table contactList = new Table();
	private TextField searchField = new TextField();
	private Button addNewContactButton = new Button("Nouveau");
	private Button removeContactButton = new Button("Enlever ce contact");
	private Button modifyContactButton = new Button("Modifier ce contact");
	private FormLayout editorLayout = new FormLayout();
	private FieldGroup editorFields = new FieldGroup();

	private static final String ID = "ID";
	private static final String PRENOM = "Prénom";
	private static final String NOMFAMILLE = "Nom de famille";
	private static final String COMPAGNIE = "Compagnie";
	private static final String[] fieldNames = new String[] { "PRENOM",
			"NOMFAMILLE", "COMPAGNIE" };

	/*
	 * Any component can be bound to an external data source. This example uses
	 * just a dummy in-memory list, but there are many more practical
	 * implementations.
	 */
	SQLContainer contactContainer = PersonneDAO.getInstance().obtenirDonnees();

	/*
	 * After UI class is created, init() is executed. You should build and wire
	 * up your user interface here.
	 */
	protected void init(VaadinRequest request) {
		initLayout();
		initContactList();
		initEditor();
		initSearch();
		initAddRemoveButtons();
	}

	/*
	 * In this example layouts are programmed in Java. You may choose use a
	 * visual editor, CSS or HTML templates for layout instead.
	 */
	private void initLayout() {

		/* Root of the user interface component tree is set */
		HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
		setContent(splitPanel);

		/* Build the component tree */
		VerticalLayout leftLayout = new VerticalLayout();
		splitPanel.addComponent(leftLayout);
		splitPanel.addComponent(editorLayout);
		leftLayout.addComponent(contactList);
		HorizontalLayout bottomLeftLayout = new HorizontalLayout();
		leftLayout.addComponent(bottomLeftLayout);
		bottomLeftLayout.addComponent(searchField);
		bottomLeftLayout.addComponent(addNewContactButton);

		/* Set the contents in the left of the split panel to use all the space */
		leftLayout.setSizeFull();

		/*
		 * On the left side, expand the size of the contactList so that it uses
		 * all the space left after from bottomLeftLayout
		 */
		leftLayout.setExpandRatio(contactList, 1);
		contactList.setSizeFull();

		/*
		 * In the bottomLeftLayout, searchField takes all the width there is
		 * after adding addNewContactButton. The height of the layout is defined
		 * by the tallest component.
		 */
		bottomLeftLayout.setWidth("100%");
		searchField.setWidth("100%");
		bottomLeftLayout.setExpandRatio(searchField, 1);

		/* Put a little margin around the fields in the right side editor */
		editorLayout.setMargin(true);
		editorLayout.setVisible(false);
	}

	private void initEditor() {

		// A button to commit the buffer

		/* User interface can be created dynamically to reflect underlying data. */
		for (String fieldName : fieldNames) {
			TextField field = new TextField(fieldName);
			editorLayout.addComponent(field);
			field.setWidth("100%");

			/*
			 * We use a FieldGroup to connect multiple components to a data
			 * source at once.
			 */
			editorFields.bind(field, fieldName);
		}
		editorLayout.addComponent(modifyContactButton);

		editorLayout.addComponent(removeContactButton);

		editorFields.addCommitHandler(new CommitHandler() {
			@Override
			public void preCommit(CommitEvent commitEvent)
					throws CommitException {
				// Pre-commit
			}

			@Override
			public void postCommit(CommitEvent commitEvent)
					throws CommitException {
				Item item = commitEvent.getFieldBinder().getItemDataSource();

				Personne modification = new Personne();
				modification.setPrenom(item.getItemProperty("PRENOM").getValue()
						.toString());
				modification.setNomDeFamille(item.getItemProperty("NOMFAMILLE")
						.getValue().toString());
				modification.setCompagnie(item.getItemProperty("COMPAGNIE")
						.getValue().toString());
				EnvoiCourriel envoi = new EnvoiCourriel();

				envoi.construireCourriel(modification);
			}
		});
	}

	private void initSearch() {

		/*
		 * We want to show a subtle prompt in the search field. We could also
		 * set a caption that would be shown above the field or description to
		 * be shown in a tooltip.
		 */
		searchField.setInputPrompt("Chercher");

		/*
		 * Granularity for sending events over the wire can be controlled. By
		 * default simple changes like writing a text in TextField are sent to
		 * server with the next Ajax call. You can set your component to be
		 * immediate to send the changes to server immediately after focus
		 * leaves the field. Here we choose to send the text over the wire as
		 * soon as user stops writing for a moment.
		 */
		searchField.setTextChangeEventMode(TextChangeEventMode.LAZY);

		/*
		 * When the event happens, we handle it in the anonymous inner class.
		 * You may choose to use separate controllers (in MVC) or presenters (in
		 * MVP) instead. In the end, the preferred application architecture is
		 * up to you.
		 */
		searchField.addTextChangeListener(new TextChangeListener() {
			public void textChange(final TextChangeEvent event) {

				/* Reset the filter for the contactContainer. */
				contactContainer.removeAllContainerFilters();
				
				int i=0;
				String[] filters = {"PRENOM","NOMFAMILLE","COMPAGNIE"};
				Filter[] filtersToAdd = new Filter[filters.length];
				for(String filterString : filters) {
				filtersToAdd[i++] = 
				    new Or(new SimpleStringFilter(filterString, event.getText(), true, false));
				}
				Filter f = new Or(filtersToAdd);
				contactContainer.addContainerFilter(f);
			}
		});
	}

	private void initAddRemoveButtons() {
		addNewContactButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {

				/*
				 * Rows in the Container data model are called Item. Here we add
				 * a new row in the beginning of the list.
				 */
				contactContainer.removeAllContainerFilters();
				Object contactId = contactContainer.addItem();

				/*
				 * Each Item has a set of Properties that hold values. Here we
				 * set a couple of those.
				 */
				contactList.getContainerProperty(contactId, "PRENOM").setValue(
						"Nouveau");
				contactList.getContainerProperty(contactId, "NOMFAMILLE")
						.setValue("Contact");
				contactList.getContainerProperty(contactId, "COMPAGNIE")
				.setValue("");

				/* Lets choose the newly created contact to edit it. */
				contactList.select(contactId);
				contactList.refreshRowCache();
			}
		});

		removeContactButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				Object contactId = contactList.getValue();
				contactList.removeItem(contactId);
			}
		});

		modifyContactButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					editorFields.commit();
					contactContainer.commit();
					contactList.refreshRowCache();
					Notification.show("Contact modifié");
				} catch (CommitException e) {
					Notification.show("Erreur");
				} catch (UnsupportedOperationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	private void initContactList() {
		contactList.setContainerDataSource(contactContainer);
		Object[] visibleProperties = new Object[] { "PRENOM", "NOMFAMILLE",
				"COMPAGNIE" };
		String[] columnsHeader = new String[] { PRENOM, NOMFAMILLE, COMPAGNIE };
		contactList.setVisibleColumns(visibleProperties);
		contactList.setColumnHeaders(columnsHeader);

		contactList.setSelectable(true);
		contactList.setImmediate(true);

		contactList.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object contactId = contactList.getValue();
				/*
				 * When a contact is selected from the list, we want to show
				 * that in our editor on the right. This is nicely done by the
				 * FieldGroup that binds all the fields to the corresponding
				 * Properties in our contact at once.
				 */
				if (contactId != null)
					editorFields.setItemDataSource(contactList
							.getItem(contactId));

				editorLayout.setVisible(contactId != null);
			}
		});
	}
}
