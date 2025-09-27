package com.training.digitalgoldloan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.digitalgoldloan.entity.VirtualGoldHolding;
import com.training.digitalgoldloan.exception.VirtualGoldHoldingException;
import com.training.digitalgoldloan.service.VirtualGoldHoldingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v3/virtual_gold_holding")
public class VirtualGoldHoldingRestController {

	@Autowired
	private VirtualGoldHoldingService virtualGoldHoldingService;

	@Autowired
	private Environment env;

	@GetMapping
	public ResponseEntity<List<VirtualGoldHolding>> getAllHoldings() {
		return ResponseEntity.ok(virtualGoldHoldingService.getAllVirtualGoldHoldings());
	}

	@GetMapping("/users/{user_id}")
	public ResponseEntity<List<VirtualGoldHolding>> getHoldingsByUser(@PathVariable("user_id") Integer userId)
			throws VirtualGoldHoldingException {
		return ResponseEntity.ok(virtualGoldHoldingService.getAllVirtualGoldHoldingByUserId(userId));
	}

	@GetMapping("/{holding_id}")
	public ResponseEntity<VirtualGoldHolding> getHoldingById(@PathVariable("holding_id") Integer holdingId)
			throws VirtualGoldHoldingException {
		return ResponseEntity.ok(virtualGoldHoldingService.getVirtualGoldHoldingById(holdingId));
	}

	@GetMapping("/byUserAndVendor/{user_id}/{vendor_id}")
	public ResponseEntity<List<VirtualGoldHolding>> getHoldingsByUserAndVendor(@PathVariable("user_id") Integer userId,
			@PathVariable("vendor_id") Integer vendorId) throws VirtualGoldHoldingException {
		return ResponseEntity.ok(virtualGoldHoldingService.getVirtualGoldHoldingByUserAndVendor(userId, vendorId));
	}

	@PostMapping("/add")
	public ResponseEntity<String> addHolding(@Valid @RequestBody VirtualGoldHolding holding)
			throws VirtualGoldHoldingException {
		virtualGoldHoldingService.addVirtualGoldHolding(holding);
		return ResponseEntity.status(HttpStatus.CREATED).body(env.getProperty("VirtualGoldService.SUCCESS"));
	}

	@PutMapping("/update/{holding_id}")
	public ResponseEntity<String> updateHolding(@PathVariable("holding_id") Integer holdingId,
			@Valid @RequestBody VirtualGoldHolding updatedHolding) throws VirtualGoldHoldingException {
		Integer id = virtualGoldHoldingService.updateVirtualGoldHolding(holdingId, updatedHolding);
		return ResponseEntity.ok(env.getProperty("VirtualGoldService.UPDATE") + id);
	}

	@PostMapping("/convertToPhysical/{holding_id}")
	public ResponseEntity<String> convertToPhysical(@PathVariable("holding_id") Integer holdingId)
			throws VirtualGoldHoldingException {
		virtualGoldHoldingService.convertVirtualToPhysical(holdingId);
		return ResponseEntity.ok(env.getProperty("VirtualGoldService.CONVERT_SUCCESS"));
	}
}
