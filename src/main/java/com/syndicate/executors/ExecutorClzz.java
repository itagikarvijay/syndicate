package com.syndicate.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.syndicate.conversion.utility.WrapperClz;
import com.syndicate.master.product.IProductService;

@Component
public class ExecutorClzz {

	@Autowired
	IProductService iProductService;
	
	ExecutorService executor = Executors.newFixedThreadPool(2);

	public void executeSaveTask(WrapperClz list) {
		ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;
		ExecuteTask et = new ExecuteTask(iProductService);
		et.setList(list);
		Future<?>  f = pool.submit(et);
		
		if(f.isDone())
			executor.shutdown();
		
	}
}
