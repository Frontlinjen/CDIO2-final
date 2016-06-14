package cdiofinal.client;


import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import cdiofinal.shared.AnsatDTO;


	public class pbRapportContainerComposite extends Composite {
		private final AnsatRPCInterfaceAsync database = (AnsatRPCInterfaceAsync)GWT.create(AnsatRPCInterface.class);
		interface pbRapportContainerUIBinder extends UiBinder<Widget, pbRapportContainerComposite> {}
		private static pbRapportContainerUIBinder pbRapportContainerUIBinder = GWT.create(pbRapportContainerUIBinder.class);
		
		private NewElementCreatedCallback<AnsatDTO> callback;
		
		@UiField TextBox print_date;
		@UiField TextBox pb_nr;
		@UiField TextBox recept_nr;
		@UiField TextBox sum_tara;
		@UiField TextBox sum_netto;
		@UiField TextBox status;
		@UiField TextBox start_date;
		@UiField TextBox end_date;
		public pbRapportContainerComposite(NewElementCreatedCallback<AnsatDTO> callback) {
			initWidget(pbRapportContainerUIBinder.createAndBindUi(this));
			this.callback = callback;
		}
		
}
