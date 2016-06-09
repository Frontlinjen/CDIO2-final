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

import cdiofinal.shared.AnsatDTO;






public class ListUsersComposite extends Composite implements AsyncCallback<AnsatDTO[]> {
	
	final AnsatRPCInterfaceAsync database = (AnsatRPCInterfaceAsync)GWT.create(AnsatRPCInterface.class);
	
	interface ListUsersUiBinder extends UiBinder<Widget, ListUsersComposite> {}
	private static ListUsersUiBinder listUsersUiBinder = GWT.create(ListUsersUiBinder.class);
	@UiField(provided=false) CellTable<AnsatDTO> vPanel;
	List<AnsatDTO> gui;
	
	public ListUsersComposite()
	{
		initWidget(listUsersUiBinder.createAndBindUi(this));
	}
	public List<AnsatDTO> getLayoutList() { //TODO: Show users when clicked
		vPanel = new CellTable<AnsatDTO>();
		Column<AnsatDTO, String> CPRColumn = getCPRColumn();
		//CPRColumn.setSortable(true);
		Column<AnsatDTO, String> nameColumn = getNameColumn();
		//nameColumn.setSortable(true);
		Column<AnsatDTO, String> iniColumn = getIniColumn();
		//nameColumn.setSortable(true);
		Column<AnsatDTO, String> rankColumn = getRankColumn();
		//nameColumn.setSortable(true);
		Column<AnsatDTO, String> saveColumn = getButtonColumn("save");
		saveColumn.setFieldUpdater(new FieldUpdater<AnsatDTO, String>() {
					@Override
					  public void update(final int index, AnsatDTO object, String value) {
							database.updateAnsat(object, new AsyncCallback<Integer>() {
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
				
				Column<AnsatDTO, String> removeColumn = getButtonColumn("remove");
				removeColumn.setFieldUpdater(new FieldUpdater<AnsatDTO, String>() {
					@Override
					  public void update(final int index, AnsatDTO object, String value) {
						if(object.getTitel() != 0){
							database.deleteAnsat(object, new AsyncCallback<Integer>() {
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
		
		
		ListDataProvider<AnsatDTO> userList = new ListDataProvider<AnsatDTO>();
		
		
		
		userList.addDataDisplay(vPanel);		
		
		return userList.getList();
	}

	private Column<AnsatDTO, String> getButtonColumn(final String value) {
		ButtonCell button = new ButtonCell();
		Column<AnsatDTO, String> buttonColumn = new Column<AnsatDTO, String>(button)
				{
					@Override
					public String getValue(AnsatDTO user)
					{
						return value;
					}
				};
				
		return buttonColumn;
	}

	private Column<AnsatDTO, String> getRankColumn() {
		final String[] ranks = new String[] {"Operat\u00F8r", "V\u00E6rkf\u00F8rer", "Farmaceut", "Administrator"};
		SelectionCell rankCell = new SelectionCell(Arrays.asList(ranks));
		Column<AnsatDTO, String> rankColumn = new Column<AnsatDTO, String>(rankCell)
				{
					@Override
		            public String getValue(AnsatDTO object) {
		                return ranks[object.getTitel()];  //pass integer as i here at runtime
		            }
				};
				rankColumn.setFieldUpdater(new FieldUpdater<AnsatDTO, String>(){

					  @Override
					public void update(int index, final AnsatDTO ansat, final String value) {
						switch(value)
						{
						case "Operat\u00F8r":
							ansat.setTitel(0);
							break;
						case "V\u00E6rkf\u00F8rer":
							ansat.setTitel(1);
							break;
						case "Farmaceut":
							ansat.setTitel(2);
							break;
						case "Administrator":
							ansat.setTitel(3);
							break;
						
						}
						  		
					  }});
		return rankColumn;
	}

	private Column<AnsatDTO, String> getIniColumn() {
		EditTextCell iniCell = new EditTextCell();
		Column<AnsatDTO, String> iniColumn = new Column<AnsatDTO, String>(iniCell)
				{
					@Override
					public String getValue(AnsatDTO user) {
						return user.getIni();
					}
				};
				iniColumn.setFieldUpdater(new FieldUpdater<AnsatDTO, String>(){

					  @Override
					public void update(int index, final AnsatDTO ansat, final String value) {
						  		ansat.setIni(value);
					  }});
		return iniColumn;
	}

	private Column<AnsatDTO, String> getCPRColumn() {
		EditTextCell cprCell = new EditTextCell();
		Column<AnsatDTO, String> cprColumn = new Column<AnsatDTO, String>(cprCell)
				{
					@Override
					public String getValue(AnsatDTO user) {
						return user.getCpr();
					}
				};
		return cprColumn;
	}
	
	private Column<AnsatDTO, String> getNameColumn() {
		EditTextCell nameCell = new EditTextCell();
		Column<AnsatDTO, String> nameColumn = new Column<AnsatDTO, String>(nameCell)
				{
					@Override
					public String getValue(AnsatDTO user) {
						return user.getOprNavn();
					}
				};
		nameColumn.setFieldUpdater(new FieldUpdater<AnsatDTO, String>(){

			  @Override
			public void update(int index, final AnsatDTO ansat, final String value) {
				  		ansat.setOprNavn(value);
			  }});
		return nameColumn;
	}

	//Fired when the user clicks "list users"
	@Override
	public void onLoad() {
		gui = getLayoutList();
		database.getAnsatList(this);
	
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
	public void onSuccess(AnsatDTO[] result) {
		if(result==null)
		{
			Window.alert("No data recieved.");
		}
		gui.clear();
		for (AnsatDTO ansatDTO : result) {
			gui.add(ansatDTO);
		}
	}
	


}
