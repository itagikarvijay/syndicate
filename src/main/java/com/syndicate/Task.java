package com.syndicate;

import java.util.List;
import java.util.concurrent.RecursiveAction;

import org.springframework.beans.factory.annotation.Autowired;

import com.syndicate.master.product.IProductService;
import com.syndicate.master.product.UploadProductDTO;

public class Task extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	IProductService iProductService;

//	private List<String> a1;
	private List<UploadProductDTO> successList;
	private boolean blnValue;

	public Task(List<UploadProductDTO> successList, boolean blnValue, IProductService iProductService) {
		this.successList = successList;
		this.blnValue = blnValue;
		this.iProductService = iProductService;
	}

	@Override
	protected void compute() {
		System.out.println(Thread.currentThread());
//		System.out.println("compute method...");
//		System.out.println("Start..."+this.start);
//		System.out.println("End..."+this.end);
		int mid = (successList.size()) / 2;
		if (blnValue) {
			// business code
//			System.out.println("execute business code.!");
			convertToUpper(successList);
		} else {
//			System.out.println("create threads.!");
			Task t1 = new Task(successList.subList(0, mid + 1), true, iProductService);
			Task t2 = new Task(successList.subList(mid + 1, successList.size()), true, iProductService);
//			Task t1 = new Task(successList.subList(0, 500), true, iProductService);
//			Task t2 = new Task(successList.subList(501, 1000), true, iProductService);
//			Task t3 = new Task(successList.subList(1001, 1500), true, iProductService);
//			Task t4 = new Task(successList.subList(1501, successList.size()), true, iProductService);
//			invokeAll(t1, t2, t3, t4);
			invokeAll(t1, t2);
		}
	}

	private void convertToUpper(List<UploadProductDTO> successList) {
//		System.out.println("Converting to upper case.!");
		successList.forEach(e -> {
//			System.out.println(e.toUpperCase());
			iProductService.update(e);
		});

	}

}
