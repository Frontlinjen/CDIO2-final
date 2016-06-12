package cdiofinal.client;

import javax.xml.transform.Templates;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safecss.shared.SafeStyles;
import com.google.gwt.safecss.shared.SafeStylesUtils;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.RootPanel;

public class CustomCell extends EditTextCell{
	
	public interface Templates extends SafeHtmlTemplates{
	
		@SafeHtmlTemplates.Template("<span style=\"{0}\">")
		SafeHtml cell(SafeStyles style);
	}
	
	private static Templates template = GWT.create(Templates.class);

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context, String value, SafeHtmlBuilder sb) {
		//SafeHtmlBuilder psb = new SafeHtmlBuilder();
		System.out.println("SbBuilder: \n" + sb.toSafeHtml());
		/*SafeStyles color = SafeStylesUtils.forTrustedColor("red");
		SafeHtml render = template.cell(color); //create a cell template with the value
		sb.append(render);*/
		super.render(context, value, sb);
		System.out.println("SbBuilder after super: \n" + sb.toSafeHtml());
		//SafeHtml sValue = SafeHtmlUtils.fromString(value); //transform value into safehtml value
		//sb.append(SafeHtmlUtils.fromSafeConstant("</span>")); //Append it?
	
			
	}
	
}
