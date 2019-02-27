package com.longfor.longjian.measure.app.bgtask;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class TaskApplicationRunner implements ApplicationRunner{
	ExecutorService threadPool = Executors.newFixedThreadPool(20);
	
	@Resource
	private List<ExportTask> exportTasks;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		if(CollectionUtils.isEmpty(exportTasks)) {
			log.warn("exportTasks is empty.threadPool not start.");
			
		}
		log.info("exportTasks threadPool start,size:"+exportTasks.size());
		for(ExportTask task:exportTasks) {
			//threadPool.execute(task);

		}
		log.info("exportTasks threadPool start done,size:"+exportTasks.size());
	}

}
