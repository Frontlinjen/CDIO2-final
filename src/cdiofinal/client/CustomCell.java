package cdiofinal.client;

import javax.xml.transform.Templates;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.AbstractEditableCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.RootPanel;

public class CustomCell extends EditTextCell{
	
	public interface Templates extends SafeHtmlTemplates{
	
		@SafeHtmlTemplates.Template("<span>{0}</span>")
		SafeHtml cell(SafeHtml value);
	}
	
	private static Templates template = GWT.create(Templates.class);

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context, C value, SafeHtmlBuilder sb) {
	{
		if(value == null){
			return;
		}
		
		SafeHtml sValue = SafeHtmlUtils.fromString(value); //transform value into safehtml value
		SafeHtml render = template.cell(sValue); //create a cell template with the value
		sb.append(sValue); //Append it?
			
	}
	
}
