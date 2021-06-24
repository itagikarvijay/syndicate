package com.syndicate.master.state;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gst")
public class StateGstResource {

	@Autowired
	IStateGstService stateGstService;

	@GetMapping("/findAll")
	public ResponseEntity<List<StateGstDTO>> findAll() {
		return new ResponseEntity<List<StateGstDTO>>(stateGstService.findAll(), HttpStatus.OK);
	}

}
