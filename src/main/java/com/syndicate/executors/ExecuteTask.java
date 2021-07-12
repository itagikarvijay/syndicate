package com.syndicate.executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.syndicate.conversion.utility.WrapperClz;
import com.syndicate.master.product.IProductService;

@Component
public class ExecuteTask implements Runnable {

//	@Autowired
	IProductService iProductService;

	public ExecuteTask(IProductService iProductService) {
		this.iProductService = iProductService;
	}
	
	WrapperClz list;

	public WrapperClz getList() {
		return list;
	}

	public void setList(WrapperClz list) {
		this.list = list;
	}

	@Override
	public void run() {
		try {
//			iProductService.update(list);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" From ExecuteTask ");
		}
	}

}
