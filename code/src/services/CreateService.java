package services;

import models.TicketModel;
import repository.TicketRepository;

public abstract class CreateService implements CRUDService {
    private TicketRepository repository;

    public CreateService(TicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(TicketModel ticket) {
        repository.createTicket(ticket);
    }

    // Unused methods from interface - demonstrate polymorphism
    @Override public TicketModel read(int id) { return null; }
    @Override public void update(TicketModel ticket) {}
    @Override public boolean delete(int id) { return false; }
}