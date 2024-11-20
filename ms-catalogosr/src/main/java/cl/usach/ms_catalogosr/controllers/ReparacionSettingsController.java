package cl.usach.ms_catalogosr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.usach.ms_catalogosr.entities.ReparacionSettingsEntity;
import cl.usach.ms_catalogosr.services.ReparacionSettingsService;

@RestController
@RequestMapping("/cats")
@CrossOrigin("*")
public class ReparacionSettingsController {

	@Autowired
	private ReparacionSettingsService reparacionSettingsService;

	@GetMapping("/reparacionsettings")
	public ResponseEntity<List<ReparacionSettingsEntity>> getAllSettings() {
		List<ReparacionSettingsEntity> settingsList = reparacionSettingsService.getAllSettings();
		if (settingsList == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(settingsList);
	}

	@GetMapping("/reparacionsettings/{id}")
	public ResponseEntity<ReparacionSettingsEntity> getSettingById(@PathVariable("id") Long id) {
		ReparacionSettingsEntity set = reparacionSettingsService.getSettingById(id);
		if (set == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(set);
	}

	@PostMapping("/reparacionsettings")
	public ResponseEntity<ReparacionSettingsEntity> saveSetting(@RequestBody ReparacionSettingsEntity setting) {
		ReparacionSettingsEntity set = reparacionSettingsService.saveSetting(setting);
		if (set == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(set);
	}
	
	
}
