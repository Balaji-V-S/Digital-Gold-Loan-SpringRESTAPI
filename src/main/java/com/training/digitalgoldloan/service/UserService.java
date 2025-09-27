package com.training.digitalgoldloan.service;

import java.util.List;

import com.training.digitalgoldloan.entity.Payment;
import com.training.digitalgoldloan.entity.TransactionHistory;
import com.training.digitalgoldloan.entity.User;
import com.training.digitalgoldloan.exception.AddressException;
import com.training.digitalgoldloan.exception.UserException;

public interface UserService {

	public List<User> getAllUsers();

	public User getUserById(Integer userId) throws UserException;

	public User getUserByUserName(String name) throws UserException;

	public List<User> getUsersByCity(String city) throws UserException;

	public List<User> getUsersByState(String state) throws UserException;

	public Double getUserBalanceById(Integer userId) throws UserException;

	public Double getTotalVirtualGoldHoldingsByUserId(Integer userId) throws UserException;

	public Double getTotalPhysicalGoldHoldingsByUserId(Integer userId) throws UserException;

	public List<TransactionHistory> getUserTransactionHistory(Integer userId) throws UserException;

	public List<Payment> getUserPayments(Integer userId) throws UserException;

	public void createUser(User newUser) throws UserException;

	public void updateUser(Integer userId, User userDetails) throws UserException;

	public void updateUserBalance(Integer userId, Double amount) throws UserException;

	public void updateUserAddress(Integer userId, Integer addressId) throws UserException, AddressException;
}
