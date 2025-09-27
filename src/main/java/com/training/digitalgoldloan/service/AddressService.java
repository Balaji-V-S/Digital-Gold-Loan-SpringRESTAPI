package com.training.digitalgoldloan.service;

import java.util.List;

import com.training.digitalgoldloan.entity.Address;
import com.training.digitalgoldloan.exception.AddressException;

public interface AddressService {

	public List<Address> getAllAddresses();

	public Address getAddressById(Integer addressId) throws AddressException;

	public void addAddress(Address address);

	public void updateAddressById(Integer addressId, Address updatedAddress) throws AddressException;
}
