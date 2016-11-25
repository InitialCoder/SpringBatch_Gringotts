package troywang.gringotts.biz.processor;

import org.springframework.batch.item.ItemProcessor;

import troywang.gringotts.base.exception.MoneyNotEnoughException;
import troywang.gringotts.model.Bill;
import troywang.gringotts.model.PayRecord;
import troywang.gringotts.model.User;

/**
 * Created by wangzhijian02 on 2016/11/25.
 */
public class PaymentProcessor implements ItemProcessor<Bill, PayRecord> {
    public PayRecord process(Bill bill) throws Exception {
        System.out.println("start payment processor......., billId=" + bill.getId() + ", userBalance=" + bill.getUser()
                .getBalance());

        // �û����С��0
        if (bill.getUser().getBalance() <= 0) {
            System.out.println("user ballance<0, user=" + bill.getUser().getUserId());
        }
        // �û�����������֧���Ľ������֧��/�ۿ�
        if (bill.getUser().getBalance() >= bill.getUnpaidFees()) {
            System.out.println("user ballance enough, user=" + bill.getUser().getUserId());
            // ����֧����¼
            PayRecord pr = new PayRecord();
            Bill bill2 = new Bill();
            pr.setPaidFees(bill.getUnpaidFees());
            // �����û����
            User u = new User();
            u.setAge(bill.getUser().getAge());
            u.setUserId(bill.getUser().getUserId());
            u.setUserName(bill.getUser().getUserName());
            u.setBalance(bill.getUser().getBalance() - bill.getUnpaidFees());
            // �����û�֧�����
            bill2.setPaidFees(bill.getUnpaidFees());
            bill2.setUnpaidFees(0.0);
            bill2.setPayStatus(1);/* paid */
            bill2.setUser(u);
            bill2.setId(bill.getId());
            bill2.setFees(bill.getFees());
            pr.setBill(bill2);
            return pr;
        } else {
            System.out.println("user ballance not enough, user=" + bill.getUser().getUserId());
            throw new MoneyNotEnoughException();
        }
    }
}
