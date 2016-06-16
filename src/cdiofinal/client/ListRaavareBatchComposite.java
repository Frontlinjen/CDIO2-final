package cdiofinal.client;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import cdiofinal.shared.InsufficientAccessException;
import cdiofinal.shared.RaavareBatchDTO;


public class ListRaavareBatchComposite extends Composite implements AsyncCallback<RaavareBatchDTO[]>,NewElementCreatedCallback<RaavareBatchDTO> {
	
	private final RaavareBatchRPCInterfaceAsync database = (RaavareBatchRPCInterfaceAsync)GWT.create(RaavareBatchRPCInterface.class);
	
	interface ListRaavareBatchUiBinder extends UiBinder<Widget, ListRaavareBatchComposite> {}
	private static ListRaavareBatchUiBinder listRaavareBatchUiBinder = GWT.create(ListRaavareBatchUiBinder.class);
	public @UiField(provided=true) CellTable<RaavareBatchDTO> vPanel;
	private List<RaavareBatchDTO> gui;
	
	public ListRaavareBatchComposite()
	{
		vPanel = new CellTable<RaavareBatchDTO>();
		vPanel.setVisibleRange(0, 1000000);
		initWidget(listRaavareBatchUiBinder.createAndBindUi(this));
		gui = getLayoutList();
	}
	
	@UiHandler("newElement")
	public void onClick(ClickEvent e)
	{
		Popupcontainer p = new Popupcontainer(new NewRaavareBatchComposite(this));
		p.show();
	}
	
	public List<RaavareBatchDTO> getLayoutList() { //TODO: Show users when clicked
		Column<RaavareBatchDTO, String> raavareBatchIDColumn = getRaavareBatchIDColumn();
		//CPRColumn.setSortable(true);
		Column<RaavareBatchDTO, String> raavareIDColumn = getRaavareIDColumn();
		//nameColumn.setSortable(true);
		Column<RaavareBatchDTO, String> leverandoerIDColumn = getLeverandoerIDColumn();
		//nameColumn.setSortable(true);
		Column<RaavareBatchDTO, String> maengdeColumn = getMaengdeColumn();
		//nameColumn.setSortable(true);
		Column<RaavareBatchDTO, String> saveColumn = getButtonColumn("Gem");
		saveColumn.setFieldUpdater(new FieldUpdater<RaavareBatchDTO, String>() {
					@Override
					  public void update(final int index, RaavareBatchDTO object, String value) {
							database.updateRaavareBatch(object, Token.getToken(), new AsyncCallback<Integer>() {
								@Override
								public void onFailure(Throwable caught) {
									Window.alert(ErrorHandling.getError(caught));
									if(caught instanceof InsufficientAccessException)
									{
										gui.clear();
									}
								}

								@Override
								public void onSuccess(Integer result) {
									Window.alert("Succesfuldt opdateret");
								}
								
							});
					  }
				});
				
		vPanel.addColumn(raavareBatchIDColumn, "R\u00E5varebatch ID");
		vPanel.addColumn(raavareIDColumn, "R\u00E5vare ID");
		vPanel.addColumn(leverandoerIDColumn, "Leverand\u00F8rID");
		vPanel.addColumn(maengdeColumn, "M\u00E6ngde");
		vPanel.addColumn(saveColumn, "");
		
		
		ListDataProvider<RaavareBatchDTO> userList = new ListDataProvider<RaavareBatchDTO>();
		
		
		
		userList.addDataDisplay(vPanel);		
		
		return userList.getList();
	}

	private Column<RaavareBatchDTO, String> getButtonColumn(final String value) {
		ButtonCell button = new ButtonCell();
		Column<RaavareBatchDTO, String> buttonColumn = new Column<RaavareBatchDTO, String>(button)
				{
					@Override
					public String getValue(RaavareBatchDTO user)
					{
						return value;
					}
				};
				
		return buttonColumn;
	}
		
	private Column<RaavareBatchDTO, String> getRaavareBatchIDColumn() {
		TextCell raavareBatchIDCell = new TextCell();
		Column<RaavareBatchDTO, String> raavareBatchIDColumn = new Column<RaavareBatchDTO, String>(raavareBatchIDCell)
				{
					@Override
					public String getValue(RaavareBatchDTO RaavareBatch) {
						return Integer.toString(RaavareBatch.getRaavarebatchId());
					}
				};
		
		return raavareBatchIDColumn;
	}
	
	private Column<RaavareBatchDTO, String> getRaavareIDColumn() {
		TextCell raavareIDCell = new TextCell();
		Column<RaavareBatchDTO, String> raavareIDColumn = new Column<RaavareBatchDTO, String>(raavareIDCell)
				{
					@Override
					public String getValue(RaavareBatchDTO RaavareBatch) {
						return Integer.toString(RaavareBatch.getRaavareId());
					}
				};
		
		return raavareIDColumn;
	}
	
	private Column<RaavareBatchDTO, String> getLeverandoerIDColumn() {
		TextCell leverandoerIDCell = new TextCell();
		Column<RaavareBatchDTO, String> leverandoerIDColumn = new Column<RaavareBatchDTO, String>(leverandoerIDCell)
				{
					@Override
					public String getValue(RaavareBatchDTO RaavareBatch) {
						return Integer.toString(RaavareBatch.getLeverandoerId());
					}
				};
		
		return leverandoerIDColumn;
	}
	
	private Column<RaavareBatchDTO, String> getMaengdeColumn() {
		EditTextCell maengdeCell = new EditTextCell();
		Column<RaavareBatchDTO, String> maengdeColumn = new Column<RaavareBatchDTO, String>(maengdeCell)
				{
					@Override
					public String getValue(RaavareBatchDTO RaavareBatch) {
						return Double.toString(RaavareBatch.getMaengde());
					}
				};
			maengdeColumn.setFieldUpdater(new FieldUpdater<RaavareBatchDTO, String>(){

			  @Override
			public void update(int index, final RaavareBatchDTO RaavareBatch, final String value) {
				  	RaavareBatch.setMaengde(Double.parseDouble(value));
			  }});
		return maengdeColumn;
	}

	//Fired when the user clicks "list users"
	@Override
	public void onLoad() {
		database.getRaavareBatchList(Token.getToken(), this);
	
	}

	@Override
	public void onFailure(Throwable caught) {
		Window.alert(ErrorHandling.getError(caught));
		gui.clear();
	}
	@Override
	public void onSuccess(RaavareBatchDTO[] result) {
		if(result==null)
		{
			Window.alert("Ingen data modtaget.");
		}
		gui.clear();
		for (RaavareBatchDTO RaavareBatchDTO : result) {
			gui.add(RaavareBatchDTO);
		}
	}

	@Override
	public void onElementCreated(RaavareBatchDTO object) {
		gui.add(object);
		
	}
	


}
