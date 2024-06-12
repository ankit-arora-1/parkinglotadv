package repositories;

import models.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketRepository {
    private Map<Long, Ticket> tickets = new HashMap<>();
    private int id = 0;
    public Ticket save(Ticket ticket) {
        id = id + 1;
        ticket.setId((long) id);
        tickets.put((long) id, ticket);
        return ticket;
    }
}
