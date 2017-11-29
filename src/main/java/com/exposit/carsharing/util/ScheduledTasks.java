package com.exposit.carsharing.util;

import com.exposit.carsharing.domain.AdStatus;
import com.exposit.carsharing.domain.Deal;
import com.exposit.carsharing.domain.DealStatus;
import com.exposit.carsharing.repository.DealRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
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
        LocalDateTime estimatedBookingEndTime = deal.getBookingStartTime().plusHours(1);
        if (LocalDateTime.now().isAfter(estimatedBookingEndTime)) {
            deal.setStatus(DealStatus.OVERDUE_BOOKING);
            deal.getAd().setStatus(AdStatus.ACTUAL);
            deal.getCustomer().setCountOfOverdueBooking(deal.getCustomer().getCountOfOverdueBooking() + 1);
            log.debug("User with id {} overdue booking in deal with id {}", deal.getCustomer().getId(), deal.getId());
        }
    }
}