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

import cdiofinal.shared.RaavareBatchDTO;
import cdiofinal.shared.RaavareDTO;






public class ListRaavareBatchComposite extends Composite implements AsyncCallback<RaavareBatchDTO[]> {
	
	final RaavareBatchRPCInterfaceAsync database = (RaavareBatchRPCInterfaceAsync)GWT.create(RaavareBatchRPCInterface.class);
	
	interface ListRaavareBatchUiBinder extends UiBinder<Widget, ListRaavareBatchComposite> {}
	private static ListRaavareBatchUiBinder listRaavareBatchUiBinder = GWT.create(ListRaavareBatchUiBinder.class);
	@UiField(provided=true) CellTable<RaavareBatchDTO> vPanel;
	List<RaavareBatchDTO> gui;
	
	public ListRaavareBatchComposite()
	{
		vPanel = new CellTable<RaavareBatchDTO>();
		initWidget(listRaavareBatchUiBinder.createAndBindUi(this));
		gui = getLayoutList();
	}
	
	@UiHandler("newElement")
	public void onClick(ClickEvent e)
	{
		Popupcontainer p = new Popupcontainer(new NewRaavareBatchComposite());
		p.center();
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
		Column<RaavareBatchDTO, String> saveColumn = getButtonColumn("save");
		saveColumn.setFieldUpdater(new FieldUpdater<RaavareBatchDTO, String>() {
					@Override
					  public void update(final int index, RaavareBatchDTO object, String value) {
							database.updateRaavareBatch(object, new AsyncCallback<Integer>() {
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
				
		vPanel.addColumn(raavareBatchIDColumn, "raavareBatchID");
		vPanel.addColumn(raavareIDColumn, "RaavareID");
		vPanel.addColumn(leverandoerIDColumn, "LeverandoerID");
		vPanel.addColumn(maengdeColumn, "Maengde");
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
		EditTextCell raavareBatchIDCell = new EditTextCell();
		Column<RaavareBatchDTO, String> raavareBatchIDColumn = new Column<RaavareBatchDTO, String>(raavareBatchIDCell)
				{
					@Override
					public String getValue(RaavareBatchDTO RaavareBatch) {
						return Integer.toString(RaavareBatch.getRaavarebatchId());
					}
				};
		raavareBatchIDColumn.setFieldUpdater(new FieldUpdater<RaavareBatchDTO, String>(){

			  @Override
			public void update(int index, final RaavareBatchDTO RaavareBatch, final String value) {
				  	RaavareBatch.setRaavarebatchId(Integer.parseInt(value));
			  }});
		return raavareBatchIDColumn;
	}
	
	private Column<RaavareBatchDTO, String> getRaavareIDColumn() {
		EditTextCell raavareIDCell = new EditTextCell();
		Column<RaavareBatchDTO, String> raavareIDColumn = new Column<RaavareBatchDTO, String>(raavareIDCell)
				{
					@Override
					public String getValue(RaavareBatchDTO RaavareBatch) {
						return Integer.toString(RaavareBatch.getRaavareId());
					}
				};
		raavareIDColumn.setFieldUpdater(new FieldUpdater<RaavareBatchDTO, String>(){

			  @Override
			public void update(int index, final RaavareBatchDTO RaavareBatch, final String value) {
				  	RaavareBatch.setRaavareId(Integer.parseInt(value));
			  }});
		return raavareIDColumn;
	}
	
	private Column<RaavareBatchDTO, String> getLeverandoerIDColumn() {
		EditTextCell leverandoerIDCell = new EditTextCell();
		Column<RaavareBatchDTO, String> leverandoerIDColumn = new Column<RaavareBatchDTO, String>(leverandoerIDCell)
				{
					@Override
					public String getValue(RaavareBatchDTO RaavareBatch) {
						return Integer.toString(RaavareBatch.getLeverandoerId());
					}
				};
		leverandoerIDColumn.setFieldUpdater(new FieldUpdater<RaavareBatchDTO, String>(){

			  @Override
			public void update(int index, final RaavareBatchDTO RaavareBatch, final String value) {
				  	RaavareBatch.setLeverandoerId(Integer.parseInt(value));
			  }});
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
		database.getRaavareBatchList(this);
	
	}

	@Override
	public void onFailure(Throwable caught) {
		 try {
		       throw caught;
		     } catch (IncompatibleRemoteServiceException e) {
		       Window.alert("Incompatible");
		     } catch (InvocationException e) {
		       Window.alert("Failed to connect to database\n" + e.getMessage());
		     } catch (Throwable e) {
		       // last resort -- a very unexpected exception
		    	 Window.alert("Unknown error");
		     }
	}
	@Override
	public void onSuccess(RaavareBatchDTO[] result) {
		if(result==null)
		{
			Window.alert("No data recieved.");
		}
		gui.clear();
		for (RaavareBatchDTO RaavareBatchDTO : result) {
			gui.add(RaavareBatchDTO);
		}
	}
	


}
