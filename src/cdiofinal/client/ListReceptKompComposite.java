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
import cdiofinal.shared.ReceptKompDTO;

public class ListReceptKompComposite extends Composite implements AsyncCallback<ReceptKompDTO[]>, NewElementCreatedCallback<ReceptKompDTO> {
	
	private final ReceptKomponentRPCInterfaceAsync database = (ReceptKomponentRPCInterfaceAsync)GWT.create(ReceptKomponentRPCInterface.class);
	
	interface ListReceptKompUiBinder extends UiBinder<Widget, ListReceptKompComposite> {}
	private static ListReceptKompUiBinder listReceptUiBinder = GWT.create(ListReceptKompUiBinder.class);
	public @UiField(provided=true) CellTable<ReceptKompDTO> vPanel;
	private List<ReceptKompDTO> gui;
	
	private int recept;
	
	public ListReceptKompComposite(int recept)
	{
		this.recept = recept;
		vPanel = new CellTable<ReceptKompDTO>();
		vPanel.setVisibleRange(0, 1000000);
		initWidget(listReceptUiBinder.createAndBindUi(this));
		gui = getLayoutList(recept);
	}
	
	@UiHandler("newElement")
	public void onClick(ClickEvent e)
	{
		Popupcontainer p = new Popupcontainer(new NewReceptKompComposite(this));
		p.show();
	}
	
	public List<ReceptKompDTO> getLayoutList(int recept) {
		Column<ReceptKompDTO, String> receptIDColumn = getReceptIDColumn();
		//CPRColumn.setSortable(true);
		Column<ReceptKompDTO, String> raavareIdColumn = getRaavareIdColumn();
		Column<ReceptKompDTO, String> nettoColumn = getNetto();
		Column<ReceptKompDTO, String> tolColumn = getTolerance();
		Column<ReceptKompDTO, String> saveColumn = getButtonColumn("Gem");
		saveColumn.setFieldUpdater(new FieldUpdater<ReceptKompDTO, String>() {
					@Override
					  public void update(final int index, ReceptKompDTO recKomp, String value) {
							database.updateReceptKomp(recKomp, Token.getToken(), new AsyncCallback<Integer>() {
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
									Window.alert("Successfuldt opdateret!");
								}
								
							});
					  }
				});
				
		vPanel.addColumn(receptIDColumn, "Recept ID");
		vPanel.addColumn(raavareIdColumn, "R\u00E5vare ID");
		vPanel.addColumn(nettoColumn, "Nom. Netto");
		vPanel.addColumn(tolColumn, "Tolerance");
		vPanel.addColumn(saveColumn, "");
		
		
		ListDataProvider<ReceptKompDTO> receptList = new ListDataProvider<ReceptKompDTO>();
		
		
		
		receptList.addDataDisplay(vPanel);		
		
		return receptList.getList();
	}

	private Column<ReceptKompDTO, String> getButtonColumn(final String value) {
		ButtonCell button = new ButtonCell();
		Column<ReceptKompDTO, String> buttonColumn = new Column<ReceptKompDTO, String>(button)
				{
					@Override
					public String getValue(ReceptKompDTO recept)
					{
						return value;
					}
				};
				
		return buttonColumn;
	}

	

	private Column<ReceptKompDTO, String> getReceptIDColumn() {
		TextCell idCell = new TextCell();
		Column<ReceptKompDTO, String> idColumn = new Column<ReceptKompDTO, String>(idCell)
				{
					@Override
					public String getValue(ReceptKompDTO recept) {
						return Integer.toString(recept.getReceptId());
					}
				};
		return idColumn;
	}

	private Column<ReceptKompDTO, String> getRaavareIdColumn() {
		EditTextCell nameCell = new EditTextCell();
		Column<ReceptKompDTO, String> idColumn = new Column<ReceptKompDTO, String>(nameCell)
				{
					@Override
					public String getValue(ReceptKompDTO recept) {
						return Integer.toString(recept.getRaavareId());
					}
				};
				idColumn.setFieldUpdater(new FieldUpdater<ReceptKompDTO, String>(){

					  @Override
					public void update(int index, final ReceptKompDTO Recept, final String value) {
						  		Recept.setRaavareId(Integer.parseInt(value));
					  }});
		return idColumn;
	}
	
	private Column<ReceptKompDTO, String> getNetto() {
		EditTextCell nameCell = new EditTextCell();
		Column<ReceptKompDTO, String> nettoColumn = new Column<ReceptKompDTO, String>(nameCell)
				{
					@Override
					public String getValue(ReceptKompDTO recept) {
						return Double.toString(recept.getNomNetto());
					}
				};
				nettoColumn.setFieldUpdater(new FieldUpdater<ReceptKompDTO, String>(){

					  @Override
					public void update(int index, final ReceptKompDTO Recept, final String value) {
						  		Recept.setNomNetto(Double.parseDouble(value));
					  }});
		return nettoColumn;
	}
	
	private Column<ReceptKompDTO, String> getTolerance() {
		EditTextCell nameCell = new EditTextCell();
		Column<ReceptKompDTO, String> tolColumn = new Column<ReceptKompDTO, String>(nameCell)
				{
					@Override
					public String getValue(ReceptKompDTO recept) {
						return Double.toString(recept.getTolerance());
					}
				};
				tolColumn.setFieldUpdater(new FieldUpdater<ReceptKompDTO, String>(){

					  @Override
					public void update(int index, final ReceptKompDTO Recept, final String value) {
						  		Recept.setTolerance(Double.parseDouble(value));
					  }});
		return tolColumn;
	}

	//Fired when the recept clicks "list receptcomponents"
	@Override
	public void onLoad() {
		database.getReceptKompList(recept, Token.getToken(), this);
	
	}

	@Override
	public void onFailure(Throwable caught) {
		Window.alert(ErrorHandling.getError(caught));
		gui.clear();
	}
	@Override
	public void onSuccess(ReceptKompDTO[] result) {
		if(result==null)
		{
			Window.alert("Ingen data modtaget.");
		}
		gui.clear();
		for (ReceptKompDTO ReceptKompDTO : result) {
			gui.add(ReceptKompDTO);
		}
	}

	@Override
	public void onElementCreated(ReceptKompDTO object) {
		gui.add(object);
		
	}
	


}

