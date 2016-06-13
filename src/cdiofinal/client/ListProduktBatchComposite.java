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

import cdiofinal.shared.ProduktBatchDTO;
import cdiofinal.shared.RaavareDTO;


public class ListProduktBatchComposite extends Composite implements AsyncCallback<ProduktBatchDTO[]>, NewElementCreatedCallback<ProduktBatchDTO> {
	
	final ProduktBatchRPCInterfaceAsync database = (ProduktBatchRPCInterfaceAsync)GWT.create(ProduktBatchRPCInterface.class);
	interface ListProduktBatchUiBinder extends UiBinder<Widget, ListProduktBatchComposite> {}
	private static ListProduktBatchUiBinder listProduktBatchUiBinder = GWT.create(ListProduktBatchUiBinder.class);
	
	@UiField(provided=true) CellTable<ProduktBatchDTO> vPanel;
	List<ProduktBatchDTO> gui;
	
	public ListProduktBatchComposite()
	{
		vPanel = new CellTable<ProduktBatchDTO>();
		initWidget(listProduktBatchUiBinder.createAndBindUi(this));
		gui = getLayoutList();
	}
	
	@UiHandler("newElement")
	public void onClick(ClickEvent e)
	{
		Popupcontainer p = new Popupcontainer(new NewProduktbatchComposite(this));
		p.show();
	}
	
	public List<ProduktBatchDTO> getLayoutList() { //TODO: Show users when clicked
		Column<ProduktBatchDTO, String> pbIDColumn = getPbIDColumn();
		//IDColumn.setSortable(true);
		Column<ProduktBatchDTO, String> statusColumn = getStatusColumn();
		//nameColumn.setSortable(true);
		Column<ProduktBatchDTO, String> receptIDColumn = getReceptIDColumn();
		//IDColumn.setSortable(true);
		Column<ProduktBatchDTO, String> saveColumn = getButtonColumn("save");
		saveColumn.setFieldUpdater(new FieldUpdater<ProduktBatchDTO, String>() {
					@Override
					  public void update(final int index, ProduktBatchDTO object, String value) {
							database.updateProduktBatch(object, Token.getToken(), new AsyncCallback<Integer>() {
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
				
		vPanel.addColumn(pbIDColumn, "pb_id");
		vPanel.addColumn(statusColumn, "status");
		vPanel.addColumn(receptIDColumn, "recept_id");
		vPanel.addColumn(saveColumn, "");
		
		
		ListDataProvider<ProduktBatchDTO> produktbatchList = new ListDataProvider<ProduktBatchDTO>();
		
		
		
		produktbatchList.addDataDisplay(vPanel);		
		
		return produktbatchList.getList();
	}

	private Column<ProduktBatchDTO, String> getButtonColumn(final String value) {
		ButtonCell button = new ButtonCell();
		Column<ProduktBatchDTO, String> buttonColumn = new Column<ProduktBatchDTO, String>(button)
				{
					@Override
					public String getValue(ProduktBatchDTO user)
					{
						return value;
					}
				};
				
		return buttonColumn;
	}



	private Column<ProduktBatchDTO, String> getPbIDColumn() {
		EditTextCell pbIDCell = new EditTextCell();
		Column<ProduktBatchDTO, String> pbIDColumn = new Column<ProduktBatchDTO, String>(pbIDCell)
				{
					@Override
					public String getValue(ProduktBatchDTO produktbatch) {
						return Integer.toString(produktbatch.getPbId());
					}
				};
				pbIDColumn.setFieldUpdater(new FieldUpdater<ProduktBatchDTO, String>(){

					@Override
					public void update(int index, final ProduktBatchDTO produktbatch, final String value) {
						produktbatch.setPbId(Integer.parseInt(value));
					}});
				return pbIDColumn;
	}
	
	private Column<ProduktBatchDTO, String> getStatusColumn() {
		EditTextCell statusCell = new EditTextCell();
		Column<ProduktBatchDTO, String> statusColumn = new Column<ProduktBatchDTO, String>(statusCell)
				{
					@Override
					public String getValue(ProduktBatchDTO produktbatch) {
						return Integer.toString(produktbatch.getStatus());
					}
				};
		statusColumn.setFieldUpdater(new FieldUpdater<ProduktBatchDTO, String>(){

			  @Override
			public void update(int index, final ProduktBatchDTO produktbatch, final String value) {
				  		produktbatch.setStatus(Integer.parseInt(value));
			  }});
		return statusColumn;
	}
	
	private Column<ProduktBatchDTO, String> getReceptIDColumn() {
		EditTextCell receptIDCell = new EditTextCell();
		Column<ProduktBatchDTO, String> receptIDColumn = new Column<ProduktBatchDTO, String>(receptIDCell)
				{
					@Override
					public String getValue(ProduktBatchDTO produktbatch) {
						return Integer.toString(produktbatch.getReceptId());
					}
				};
				receptIDColumn.setFieldUpdater(new FieldUpdater<ProduktBatchDTO, String>(){

					  @Override
					public void update(int index, final ProduktBatchDTO produktbatch, final String value) {
						  		produktbatch.setReceptId(Integer.parseInt(value));
					  }});
		return receptIDColumn;
	}

	//Fired when the user clicks "list produktbatch"
	@Override
	public void onLoad() {
		database.getProduktBatchList(Token.getToken(), this);
	
	}

	@Override
	public void onFailure(Throwable caught) {
		Window.alert(ErrorHandling.getError(caught));
		gui.clear();
	}
	
	@Override
	public void onSuccess(ProduktBatchDTO[] result) {
		if(result==null)
		{
			Window.alert("No data recieved.");
		}
		gui.clear();
		for (ProduktBatchDTO produktbatchDTO : result) {
			gui.add(produktbatchDTO);
		}
	}

	@Override
	public void onElementCreated(ProduktBatchDTO object) {
		gui.add(object);
		
	}
	


}
