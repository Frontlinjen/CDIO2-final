package cdiofinal.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import cdiofinal.shared.AnsatDTO;
import cdiofinal.shared.FieldVerifier;

//class CPRBox extends TextBox implements KeyPressHandler
//{
//	CPRBox()
//	{
//		this.addKeyPressHandler(this);
//		this.setMaxLength(10);
//	}
//
//	@Override
//	public void onKeyPress(KeyPressEvent event) {
//		if(Character.isWhitespace(event.getCharCode()))
//		{	
//			((TextBox) event.getSource()).cancelKey();
//		}
//	}
//	
//}


public class NewUserComposite extends Composite {
	AnsatRPCInterfaceAsync AnsatRPC = GWT.create(AnsatRPCInterface.class);
	
	public void onLoad(ClickEvent event) {
		RootPanel panel = RootPanel.get("contents");
		panel.clear();
		
		labeledTextBox CPR = new labeledTextBox("Inds\u00E6t CPR:", 10); 
		panel.add(CPR);
		
		labeledTextBox navn = new labeledTextBox("Inds\u00E6t navn:", 20);
		panel.add(navn);
		
		labeledTextBox ini = new labeledTextBox("Inds\u00E6t Ini:", 4);
		panel.add(ini);
		
		
		Label rankText = new Label("Rank nr:");
		final ListBox ranklist = new ListBox();
		ranklist.addItem("Operat\u00F8r");
		ranklist.addItem("Farmaceut");
		ranklist.addItem("V\u00E6rkf\u00F8rer");
		ranklist.addItem("Administrator");
		panel.add(rankText);
		panel.add(ranklist);
		
		labeledTextBox pass = new labeledTextBox("Inds\u00E6t password:", 20);
		panel.add(pass);
		
		final Label status = new Label("Enter fields");
		Button button = new Button("Create");
		panel.add(status);
		panel.add(button);
/*		button.addClickHandler(new ClickHandler() { 

			@Override
			public void onClick(ClickEvent event) {
				if(navn.getValue().length()==0)
				{
					status.setText("Name not long enough");
				}
				else if(!FieldVerifier.isValidName(input.getValue()))
				{
					status.setText("CPR invalid");
				}
				else if(ini.getText().length() < 3)
				{
					status.setText("Ini much be 3 characters");
				}
				else
				{
					
					AnsatRPC.createAnsat(new AnsatDTO(input.getValue(), navn.getText(), ini.getText(), pass.getText(), Integer.parseInt(ranklist.getSelectedValue())), new AsyncCallback<Integer>()
							{

								@Override
								public void onFailure(Throwable caught) {
									status.setText("Failed to create user");
									
								}

								@Override
								public void onSuccess(Integer result) {
									status.setText("Successfully created user");		
									input.setValue("");
									navn.setValue("");
									ini.setValue("");
									pass.setValue("");
									ranklist.setItemSelected(0, false);
								}
						
							});
				}
			}
			
		});
*/
	}
	
	private class labeledTextBox extends Composite{
		private Label label = new Label();
		private TextBox textBox = new TextBox();
				
		public labeledTextBox(String label, int length){
			VerticalPanel panel = new VerticalPanel();
			this.label.setText(label);
			panel.add(this.label);
			panel.add(textBox);
			textBox.setMaxLength(length);
			
			initWidget(panel);
		}
	}

}
