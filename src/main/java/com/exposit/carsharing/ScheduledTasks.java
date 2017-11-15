package com.exposit.carsharing;

import com.exposit.carsharing.domain.AdStatus;
import com.exposit.carsharing.domain.Deal;
import com.exposit.carsharing.domain.DealStatus;
import com.exposit.carsharing.repository.DealRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Transactional
public class ScheduledTasks {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);
    private final DealRepository dealRepository;

    public ScheduledTasks(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    @Scheduled(cron = "*/60 * * * * *")
    public void checkDealBooking() {
        List<Deal> deals = dealRepository.findAllByStatus(DealStatus.BOOKING);
        deals.forEach(this::checkOverdueBooking);
    }

    private void checkOverdueBooking(Deal deal) {
        long bookingEndTime = deal.getBookingStartTime() + TimeUnit.MINUTES.toMillis(30);
        if (System.currentTimeMillis() > bookingEndTime) {
            deal.setStatus(DealStatus.OVERDUE_BOOKING);
            deal.getAd().setStatus(AdStatus.ACTUAL);
            deal.getCustomer().setCountOfOverdueBooking(deal.getCustomer().getCountOfOverdueBooking() + 1);
            LOGGER.info("Profile with id %d overdue booking in deal with id %d", deal.getCustomer().getId(),
                    deal.getId());
        }
    }
}