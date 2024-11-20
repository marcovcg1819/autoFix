package cl.usach.ms_reparaciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestClass {

//	public static void main(String[] args) {
//		Float sumaReparaciones = 240000f;
//		Long porcentaje = Long.valueOf(25);
//		String porcentajeStr = porcentaje.toString();
//		if(porcentajeStr.length() == 1) {
//			porcentajeStr = "0.0"+porcentajeStr;
//		}else {
//			porcentajeStr = "0."+porcentajeStr;
//		}
//		
//		Float por = Float.valueOf(porcentajeStr);
//		
//		Float valor = sumaReparaciones - (sumaReparaciones * por);
//		
//		System.out.println(valor);
//	}
	
//	public static void main(String[] args) {
//		Calendar calendar = Calendar.getInstance();
//		Date date = calendar.getTime();
//		Locale spanish = new Locale("es", "ES");
//		String dia = new SimpleDateFormat("EE", spanish).format(date.getTime());
//		dia = dia.substring(0,2);
//		String intervaloDia = "lu-ma-mi-ju-sá";
//		String[] intervaloDiaArray = intervaloDia.split("-");
//		for(String str:intervaloDiaArray) {
//			System.out.println(str);
//			if(str.equals(dia)) {
//				System.out.println("aplica descuento");
//				
//				Date datea = new Date();   // given date
//				Calendar calendara = GregorianCalendar.getInstance(); // creates a new calendar instance
//				calendara.setTime(datea);   // assigns calendar to given date 
////				calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
////				calendar.get(Calendar.HOUR);        // gets hour in 12h format
////				calendar.get(Calendar.MONTH); 
//				Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
//				
//				String intervaloHora = "9-20";
//				String[] intervaloHoraArray = intervaloHora.split("-");
//				Integer horaI = Integer.valueOf(intervaloHoraArray[0]);
//				Integer horaF = Integer.valueOf(intervaloHoraArray[1]);
//				
//				if(hour >= horaI && hour <= horaF) {
//					System.out.println("aplica descuento por hora");
//				}
//				
//				System.out.println(hour);
//			}
//		}
//		System.out.println(dia);
////		System.out.println(new SimpleDateFormat("EEEE", spanish).format(date.getTime()));
//	}
	
	
	
	
	public static void main(String[] args) {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
		String inputString1 = "10 04 2024";
		String inputString2 = "20 04 2024";

		try {
		    Date date1 = myFormat.parse(inputString1);
		    Date date2 = new Date();
		    long diff = date2.getTime() - date1.getTime();
		    System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	}
	
}
