package cdiofinal.client;

import java.util.Arrays;
import java.util.List;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import cdiofinal.client.ListUsersComposite.ListUsersUiBinder;
import cdiofinal.shared.ReceptDTO;

public class ListReceptComposite extends Composite implements AsyncCallback<ReceptDTO[]> {
	
	final RecRPCInterfaceAsync database = (RecRPCInterfaceAsync)GWT.create(RecRPCInterface.class);
	
	interface ListReceptUiBinder extends UiBinder<Widget, ListReceptComposite> {}
	private static ListReceptUiBinder listReceptUiBinder = GWT.create(ListReceptUiBinder.class);
	@UiField(provided=false) CellTable<ReceptDTO> vPanel;
	List<ReceptDTO> gui;
	
	public ListReceptComposite()
	{
		initWidget(listReceptUiBinder.createAndBindUi(this));
	}
	public List<ReceptDTO> getLayoutList() { //TODO: Show users when clicked
		vPanel = new CellTable<ReceptDTO>();
		Column<ReceptDTO, String> CPRColumn = getCPRColumn();
		//CPRColumn.setSortable(true);
		Column<ReceptDTO, String> nameColumn = getNameColumn();
		//nameColumn.setSortable(true);
		Column<ReceptDTO, String> iniColumn = getIniColumn();
		//nameColumn.setSortable(true);
		Column<ReceptDTO, String> rankColumn = getRankColumn();
		//nameColumn.setSortable(true);
		Column<ReceptDTO, String> saveColumn = getButtonColumn("save");
		saveColumn.setFieldUpdater(new FieldUpdater<ReceptDTO, String>() {
					@Override
					  public void update(final int index, ReceptDTO object, String value) {
							database.updateRecept(object, new AsyncCallback<Integer>() {
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
				
				Column<ReceptDTO, String> removeColumn = getButtonColumn("remove");
				removeColumn.setFieldUpdater(new FieldUpdater<ReceptDTO, String>() {
					@Override
					  public void update(final int index, ReceptDTO object, String value) {
						if(object.getTitel() != 0){
							database.deleteRecept(object, new AsyncCallback<Integer>() {
								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Deletion unsuccessful");
								}

								@Override
								public void onSuccess(Integer result) {
									gui.remove(index);
									Window.alert("Successfully deleted user");
								}
								
							});
						}
						else Window.alert("You cannot delete operators!");
					  }
				});
				
		vPanel.addColumn(CPRColumn, "CPR");
		vPanel.addColumn(nameColumn, "Name");
		vPanel.addColumn(iniColumn, "Ini");
		vPanel.addColumn(rankColumn, "Rank");
		vPanel.addColumn(saveColumn, "");
		vPanel.addColumn(removeColumn, "");
		
		
		ListDataProvider<ReceptDTO> userList = new ListDataProvider<ReceptDTO>();
		
		
		
		userList.addDataDisplay(vPanel);		
		
		return userList.getList();
	}

	private Column<ReceptDTO, String> getButtonColumn(final String value) {
		ButtonCell button = new ButtonCell();
		Column<ReceptDTO, String> buttonColumn = new Column<ReceptDTO, String>(button)
				{
					@Override
					public String getValue(ReceptDTO user)
					{
						return value;
					}
				};
				
		return buttonColumn;
	}

	private Column<ReceptDTO, String> getRankColumn() {
		final String[] ranks = new String[] {"Operat\u00F8r", "V\u00E6rkf\u00F8rer", "Farmaceut", "Administrator"};
		SelectionCell rankCell = new SelectionCell(Arrays.asList(ranks));
		Column<ReceptDTO, String> rankColumn = new Column<ReceptDTO, String>(rankCell)
				{
					@Override
		            public String getValue(ReceptDTO object) {
		                return ranks[object.getTitel()];  //pass integer as i here at runtime
		            }
				};
				rankColumn.setFieldUpdater(new FieldUpdater<ReceptDTO, String>(){

					  @Override
					public void update(int index, final ReceptDTO Recept, final String value) {
						switch(value)
						{
						case "Operat\u00F8r":
							Recept.setTitel(0);
							break;
						case "V\u00E6rkf\u00F8rer":
							Recept.setTitel(1);
							break;
						case "Farmaceut":
							Recept.setTitel(2);
							break;
						case "Administrator":
							Recept.setTitel(3);
							break;
						
						}
						  		
					  }});
		return rankColumn;
	}

	private Column<ReceptDTO, String> getIniColumn() {
		EditTextCell iniCell = new EditTextCell();
		Column<ReceptDTO, String> iniColumn = new Column<ReceptDTO, String>(iniCell)
				{
					@Override
					public String getValue(ReceptDTO user) {
						return user.getIni();
					}
				};
				iniColumn.setFieldUpdater(new FieldUpdater<ReceptDTO, String>(){

					  @Override
					public void update(int index, final ReceptDTO Recept, final String value) {
						  		Recept.setIni(value);
					  }});
		return iniColumn;
	}

	private Column<ReceptDTO, String> getCPRColumn() {
		EditTextCell cprCell = new EditTextCell();
		Column<ReceptDTO, String> cprColumn = new Column<ReceptDTO, String>(cprCell)
				{
					@Override
					public String getValue(ReceptDTO user) {
						return user.getCpr();
					}
				};
		return cprColumn;
	}
	
	private Column<ReceptDTO, String> getNameColumn() {
		EditTextCell nameCell = new EditTextCell();
		Column<ReceptDTO, String> nameColumn = new Column<ReceptDTO, String>(nameCell)
				{
					@Override
					public String getValue(ReceptDTO user) {
						return user.getOprNavn();
					}
				};
		nameColumn.setFieldUpdater(new FieldUpdater<ReceptDTO, String>(){

			  @Override
			public void update(int index, final ReceptDTO Recept, final String value) {
				  		Recept.setOprNavn(value);
			  }});
		return nameColumn;
	}

	//Fired when the user clicks "list users"
	@Override
	public void onLoad() {
		gui = getLayoutList();
		database.getReceptList(this);
	
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
	public void onSuccess(ReceptDTO[] result) {
		if(result==null)
		{
			Window.alert("No data recieved.");
		}
		gui.clear();
		for (ReceptDTO ReceptDTO : result) {
			gui.add(ReceptDTO);
		}
	}
	


}


}
