package cl.usach.ms_reparaciones.util;

import org.springframework.stereotype.Component;

@Component
public class UtilsClass {
	
	public Float valueMinusPercent(Float value, Long percent) {
//		Float sumaReparaciones = 240000f;
//		Long porcentaje = Long.valueOf(25);
		String porcentajeStr = percent.toString();
		if(porcentajeStr.length() == 1) {
			porcentajeStr = "0.0"+porcentajeStr;
		}else {
			porcentajeStr = "0."+porcentajeStr;
		}
		
		System.out.println("porcentajeStr::: "+porcentajeStr);
		
		Float por = Float.valueOf(porcentajeStr);
		
		Float valor = value - (value * por);
		
		return valor;
	}

}
