package com.anton.expo.services;

import com.anton.expo.repository.dao.ExpositionDao;
import com.anton.expo.repository.dao.PaymentDao;
import com.anton.expo.repository.dto.TicketDto;
import com.anton.expo.repository.entity.Exposition;
import com.anton.expo.repository.entity.Payment;
import com.anton.expo.repository.entity.Ticket;
import com.anton.expo.services.impl.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    private static final long ID = 1L;
    private static final double PRICE1 = 50.0;
    private static final double PRICE2 = 15.15;
    private static final int QUANTITY1 = 2;
    private static final int QUANTITY2 = 1;
    private static final double TOTAL = 115.15;
    private static final Map<String, TicketDto> cart = new HashMap<>();
    @Mock
    ExpositionDao expositionDao;
    @Mock
    PaymentDao paymentDao;
    PaymentService paymentService;

    @BeforeAll
    static void before() {
        Exposition exposition1 = Exposition.builder().price(PRICE1).build();
        Exposition exposition2 = Exposition.builder().price(PRICE2).build();
        Ticket ticket1 = Ticket.builder().quantity(QUANTITY1).build();
        Ticket ticket2 = Ticket.builder().quantity(QUANTITY2).build();
        TicketDto ticketDto1 = new TicketDto();
        TicketDto ticketDto2 = new TicketDto();

        ticketDto1.setExposition(exposition1);
        ticketDto1.setTicket(ticket1);
        ticketDto2.setExposition(exposition2);
        ticketDto2.setTicket(ticket2);

        cart.put("1", ticketDto1);
        cart.put("2", ticketDto2);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        paymentService = new PaymentServiceImpl(expositionDao, paymentDao);
    }

    @Test
    void createTicketTest() throws Exception {
        Exposition exposition = Exposition.builder().id(ID).startDate(LocalDate.now()).build();

        when(expositionDao.get(anyLong())).thenReturn(exposition);

        TicketDto ticketDto = paymentService.createTicketDto(ID);

        assertNotNull(ticketDto);
        assertEquals(exposition, ticketDto.getExposition());
        assertEquals(exposition.getStartDate(), ticketDto.getTicket().getDate());
        assertEquals(paymentService.ONE_TICKET, ticketDto.getTicket().getQuantity());

        verify(expositionDao).get(anyLong());
    }

    @Test
    void createPaymentTest() throws Exception {
        Payment payment = paymentService.createPayment(TOTAL, ID);

        assertNotNull(payment);
        assertEquals(ID, payment.getUsedId());
        assertEquals(TOTAL, payment.getTotal());
    }

    @Test
    void getCartTotalTest() throws Exception {
        assertEquals(TOTAL, paymentService.getCartTotal(cart));
    }

    @Test
    void savePaymentTest() throws Exception {
        when(paymentDao.savePaymentWithTickets(any(Payment.class), anyList())).thenReturn(ID);

        assertEquals(ID, paymentService.savePayment(TOTAL, ID, cart));

        verify(paymentDao).savePaymentWithTickets(any(Payment.class), anyList());
    }
}