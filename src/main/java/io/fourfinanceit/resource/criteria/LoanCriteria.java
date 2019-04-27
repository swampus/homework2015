package io.fourfinanceit.resource.criteria;

import com.google.common.collect.Lists;
import io.fourfinanceit.model.Loan;
import io.fourfinanceit.model.LoanApplication;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanCriteria {

    private final SessionFactory sessionFactory;

    @Autowired
    public LoanCriteria(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Loan> getAllLoanByPhoneNum(String phoneNum) {
        return listAndCast(sessionFactory.openSession().
                createCriteria(LoanApplication.class)
                .add(Restrictions.eq("phoneNumber", phoneNum)));
    }

    @SuppressWarnings("unchecked")
    private List<Loan> listAndCast(Criteria criteria) {
        return Lists.newArrayList(criteria.list());
    }
}
