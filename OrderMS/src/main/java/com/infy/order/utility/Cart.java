package com.infy.order.utility;

import lombok.Data;
import java.util.List;


@Data
public class Cart {
	
	Long customerId;
	List<SelectedItem> selectedItems;
}
