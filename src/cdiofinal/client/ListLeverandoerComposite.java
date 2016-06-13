package cdiofinal.client;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import cdiofinal.shared.FieldVerifier;
import cdiofinal.shared.LeverandoerDTO;
import cdiofinal.shared.RaavareDTO;

public class ListLeverandoerComposite extends Composite implements AsyncCallback<LeverandoerDTO[]>, NewElementCreatedCallback<LeverandoerDTO> {
	
	final LeverandoerRPCInterfaceAsync database = (LeverandoerRPCInterfaceAsync)GWT.create(LeverandoerRPCInterface.class);
	
	interface ListLeverandoerUiBinder extends UiBinder<Widget, ListLeverandoerComposite> {}
	private static ListLeverandoerUiBinder listLeverandoerUiBinder = GWT.create(ListLeverandoerUiBinder.class);
	@UiField(provided=true) CellTable<LeverandoerDTO> vPanel;
	List<LeverandoerDTO> gui;
	
	public ListLeverandoerComposite()
	{
		vPanel = new CellTable<LeverandoerDTO>();
		initWidget(listLeverandoerUiBinder.createAndBindUi(this));
		gui = getLayoutList();	
	}
	
	@UiHandler("newElement")
	public void onClick(ClickEvent e)
	{
		Popupcontainer p = new Popupcontainer(new NewLeverandoerComposite(this));
		p.show();
	}
	
	public List<LeverandoerDTO> getLayoutList() { //TODO: Show users when clicked
		Column<LeverandoerDTO, String> IDColumn = getIDColumn();
		//IDColumn.setSortable(true);
		Column<LeverandoerDTO, String> nameColumn = getNameColumn();
		//nameColumn.setSortable(true);
		Column<LeverandoerDTO, String> saveColumn = getButtonColumn("save");
		saveColumn.setFieldUpdater(new FieldUpdater<LeverandoerDTO, String>() {
					@Override
					  public void update(final int index, LeverandoerDTO object, String value) {
						if(FieldVerifier.isValidName(object.getLeverandoerNavn())==true)	
						database.updateLeverandoer(object, Token.getToken(), new AsyncCallback<Integer>() {
								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Update unsuccessful");
								}

								@Override
								public void onSuccess(Integer result) {
									Window.alert("Successfully updated");
								}
								
							});
					  }
				});
				
		vPanel.addColumn(IDColumn, "ID");
		vPanel.addColumn(nameColumn, "Name");
		vPanel.addColumn(saveColumn, "");
		
		
		ListDataProvider<LeverandoerDTO> leverandoerList = new ListDataProvider<LeverandoerDTO>();
		
		
		
		leverandoerList.addDataDisplay(vPanel);		
		
		return leverandoerList.getList();
	}

	private Column<LeverandoerDTO, String> getButtonColumn(final String value) {
		ButtonCell button = new ButtonCell();
		Column<LeverandoerDTO, String> buttonColumn = new Column<LeverandoerDTO, String>(button)
				{
					@Override
					public String getValue(LeverandoerDTO user)
					{
						return value;
					}
				};
				
		return buttonColumn;
	}



	private Column<LeverandoerDTO, String> getIDColumn() {
		EditTextCell idCell = new EditTextCell();
		Column<LeverandoerDTO, String> idColumn = new Column<LeverandoerDTO, String>(idCell)
				{
					@Override
					public String getValue(LeverandoerDTO leverandoer) {
						return Integer.toString(leverandoer.getLeverandoerId());
					}
				};
				idColumn.setFieldUpdater(new FieldUpdater<LeverandoerDTO, String>(){

					  @Override
					public void update(int index, final LeverandoerDTO leverandoer, final String value) {
						  		leverandoer.setLeverandoerId(Integer.parseInt(value));
					  }});
		return idColumn;
	}
	
	private Column<LeverandoerDTO, String> getNameColumn() {
		EditTextCell nameCell = new EditTextCell();
		Column<LeverandoerDTO, String> nameColumn = new Column<LeverandoerDTO, String>(nameCell)
				{
					@Override
					public String getValue(LeverandoerDTO leverandoer) {
						return leverandoer.getLeverandoerNavn();
					}
				};
		nameColumn.setFieldUpdater(new FieldUpdater<LeverandoerDTO, String>(){

			  @Override
			public void update(int index, final LeverandoerDTO leverandoer, final String value) {
				  		leverandoer.setLeverandoerNavn(value);
			  }});
		return nameColumn;
	}

	//Fired when the user clicks "list leverandoer"
	@Override
	public void onLoad() {
		database.getLeverandoerList(Token.getToken(), this);
	
	}

	@Override
	public void onFailure(Throwable caught) {
		ErrorHandling.getError(caught);
	}
	
	@Override
	public void onSuccess(LeverandoerDTO[] result) {
		if(result==null)
		{
			Window.alert("No data recieved.");
		}
		gui.clear();
		for (LeverandoerDTO leverandoerDTO : result) {
			gui.add(leverandoerDTO);
		}
	}

	@Override
	public void onElementCreated(LeverandoerDTO object) {
		gui.add(object);
		
	}
	


}
