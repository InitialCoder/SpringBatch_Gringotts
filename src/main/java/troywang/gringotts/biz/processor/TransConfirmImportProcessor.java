package troywang.gringotts.biz.processor;

import org.springframework.batch.item.ItemProcessor;

import troywang.gringotts.model.TransConfirmModel;

/**
 * Created by wangzhijian02 on 2016/11/25.
 */
public class TransConfirmImportProcessor implements ItemProcessor<TransConfirmModel, Object> {

    public Object process(TransConfirmModel transConfirmModel) throws Exception {
        System.out.println("ÿ����ϸ������˿�, transId=" + transConfirmModel.getTransId());
        return null;
    }

}
