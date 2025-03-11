package com.infy.billing.service;

import com.infy.billing.dto.BillingDto;
import com.infy.billing.exception.BillingException;
import com.infy.billing.utility.NewBillUtil;

public interface BillingService {

	Integer newBill(NewBillUtil newBill) throws BillingException;

}
