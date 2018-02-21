package converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class ConvertisseurDate implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("On est dedans : "+value);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateStr = new Date();
		try {
			dateStr = simpleDateFormat.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateStr;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		String	strDate = "pas de date";
		
		if (object instanceof Date) {
			Date	date = (Date)object;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			strDate = ""+calendar.get(Calendar.DAY_OF_MONTH)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR)+" !!";
		}
		
		return strDate;
	}

}
