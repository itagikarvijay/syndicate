package com.syndicate.master.party;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/party")
public class PartyResource {

	@Autowired
	IPartyService partyService;

	@GetMapping("/find")
	public ResponseEntity<PartyDTO> find(@RequestParam("gstNo") String gstNo){
		PartyDTO partyDTO = null;
		partyDTO = partyService.findByGstNo(gstNo);
		return new ResponseEntity<PartyDTO>(partyDTO, HttpStatus.OK);
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(1).toUri();
//		return ResponseEntity.created(location).build();
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<PartyDTO>> findAll() {
		List<PartyDTO> partyDTO = null;
		try {
			partyDTO = partyService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<PartyDTO>>(partyDTO, HttpStatus.OK);
	}
	
	@GetMapping("/findAll/{partyTypeId}")
	public ResponseEntity<List<PartyDTO>> findAll(@PathVariable Integer partyTypeId) {
		List<PartyDTO> partyDTO = null;
		try {
			partyDTO = partyService.findAll(partyTypeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<PartyDTO>>(partyDTO, HttpStatus.OK);
	}

	@PutMapping("/save")
	public ResponseEntity<PartyDTO> save(@RequestBody PartyDTO partyDTO) {
		System.out.println(partyDTO);
		return ResponseEntity.ok().body(partyService.update(partyDTO));
	}

}
