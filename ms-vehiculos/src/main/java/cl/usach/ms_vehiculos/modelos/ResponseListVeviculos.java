package cl.usach.ms_vehiculos.modelos;

public interface ResponseListVeviculos {
	Long getId();
	String getN_patente();
	Long getId_marca();
	String getMarca();
	Long getId_modelo();
	String getModelos();
	Long getId_tipo_auto();
	String getTipo();
	String getAnio_fabricacion();
	Integer getN_asientos();
	Integer getKilometraje();
}
