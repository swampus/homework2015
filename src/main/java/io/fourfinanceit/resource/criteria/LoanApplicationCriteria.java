package io.fourfinanceit.resource.criteria;

import com.google.common.collect.Lists;
import io.fourfinanceit.model.LoanApplication;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanApplicationCriteria {

    private final SessionFactory sessionFactory;

    @Autowired
    public LoanApplicationCriteria(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<LoanApplication> getAllLoanApplicationByPhoneNum(String phoneNum) {
        return listAndCast(sessionFactory.openSession().
                createCriteria(LoanApplication.class)
                .add(Restrictions.eq("phoneNumber", phoneNum)));
    }

    public List<LoanApplication> getAllLoanApplicationByIpToday(String ip, long currentTimestampInDays) {
        return listAndCast(sessionFactory.openSession().
                createCriteria(LoanApplication.class)
                .add(Restrictions.eq("remoteIp", ip))
                .add(Restrictions.eq("timestampDayCreatedAt",
                        currentTimestampInDays)));
    }

    @SuppressWarnings("unchecked")
    private List<LoanApplication> listAndCast(Criteria criteria) {
        return Lists.newArrayList(criteria.list());
    }
}
