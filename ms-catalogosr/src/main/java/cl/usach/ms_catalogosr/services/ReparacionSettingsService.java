package cl.usach.ms_catalogosr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.usach.ms_catalogosr.entities.ReparacionSettingsEntity;
import cl.usach.ms_catalogosr.repositories.ReparacionSettingsRepository;

@Service
public class ReparacionSettingsService {
	
	@Autowired
	private ReparacionSettingsRepository reparacionSettingsRepository;
	
	public List<ReparacionSettingsEntity> getAllSettings(){
		return reparacionSettingsRepository.findAll();
	}
	
	public ReparacionSettingsEntity getSettingById(Long id){
		return reparacionSettingsRepository.findById(id).get();
	}
	
	public ReparacionSettingsEntity saveSetting(ReparacionSettingsEntity tipo){
		return reparacionSettingsRepository.save(tipo);
	}
	

}
