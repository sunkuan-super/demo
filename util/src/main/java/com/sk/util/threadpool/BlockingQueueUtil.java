package com.sk.util.threadpool;

import com.sk.util.bean.ExportTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Title: BlockingQueueUtil
 * @Package: com.sk.util.threadpool
 * @Description:
 * @Author: sunkuan
 * @Date: 2021/1/21 - 16:22
 */

public class BlockingQueueUtil {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<ExportTask> queue = new LinkedBlockingQueue<ExportTask>(10);
//        ExportTask exportTask = new ExportTask(1001L, "20200102-20200208", "20200110", "20200110", 2);
        ExportTask exportTask = new ExportTask();
        queue.put(exportTask);

//        for (ExportTask task : queue) {
//            System.out.println(task);
//        }

//        List<Object> objects = Arrays.asList(queue.toArray());

        List<ExportTask> exportTasks = dealFile(queue);
        for (ExportTask task : exportTasks) {
            System.out.println(task);
        }
    }

    public static List<ExportTask> dealFile(BlockingQueue<ExportTask> queue){
        List<ExportTask> exportTasks = new ArrayList<>(queue);

        return exportTasks;
    }
}
